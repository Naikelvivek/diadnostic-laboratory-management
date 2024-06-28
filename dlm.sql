create database dlm;

use dlm;


create table adm(
u_name varchar(10) not null unique,
pwd varchar(10)not null unique,
primary key(u_name)
);

create table test(
t_id int not null unique,
test_name varchar(20),
test_charge int,
primary key(t_id)
) ;

create table patient(
p_id int not null,
t_id int ,
p_name varchar(20) not null,
age int,
address varchar(50),
ph_no bigint,
gender varchar(10),
primary key(p_id) ,
constraint fk__tid foreign key(t_id) references test(t_id) on update cascade on delete cascade
);


create table admits(
p_id int,
u_name varchar(10),
constraint fkp_id foreign key(p_id) references patient(p_id) on update cascade on delete cascade,
constraint fku_name foreign key(u_name) references adm(u_name)on update cascade on delete cascade);



create table report(
p_id int,
r_id int not null ,
t_id int,
dat date,
test_result varchar(20),
primary key(r_id),
constraint fkt__id foreign key(t_id) references test(t_id) on delete cascade on update cascade,
constraint fk_p_id_ foreign key(p_id)references patient(p_id) on update cascade on delete cascade
);




create table payment(
r_id int,
bill_no int not null ,
mode_of_pay varchar(20),
primary key(bill_no),
constraint fkr_id_ foreign key(r_id) references report(r_id) on delete cascade on update cascade);





create table t_generates(
r_id int,
t_id int,
constraint fkr__id foreign key(r_id)references report(r_id) on update cascade on delete cascade,
constraint fkt___id foreign key(t_id)references test(t_id) on update cascade on delete cascade);

create table holds (
p_id int,
r_id int,
constraint fkrid foreign key(r_id) references report(r_id) on delete cascade on update cascade,
constraint fkp__id foreign key(p_id) references patient(p_id)on update cascade on delete cascade);


create table generates(
r_id int,
bill_no int,
constraint fk_r_id foreign key(r_id) references report(r_id) on delete cascade on update cascade,
constraint fkbill__no foreign key(bill_no) references payment(bill_no) on delete cascade on update cascade
);





create table staff(
s_id int not null ,
t_id int,
s_name varchar(20),
address varchar(50),
age int,
ph_no bigint,
primary key(s_id),
constraint fkt_id__ foreign key(t_id)references test(t_id) on update cascade on delete cascade);


create table completed_by(
t_id int,
s_id int,
constraint fk_t_id foreign key(t_id) references test(t_id) on delete cascade on update cascade,
constraint fks_id foreign key(s_id) references staff(s_id) on delete cascade on update cascade);

use dlm;
insert into adm values("harshith","12345");
insert into adm values("lankesh","54321");
insert into adm values("abhishek","34215");

insert into test values(2000,"diabetes screen",1000);
insert into test values(2001,"lipidprofile",500);
insert into test values(2002,"blood count",200);
insert into test values(2003,"thyroid profile",200);
insert into test values(2004,"iron profile",300);
insert into test values(2005,"urine test",200);

insert into patient values(1000,2000,"naveen",26,"kuvempunagar mysore",7362574102,"male");
insert into patient values(1001,2001,"john",32,"hebbal mysore",6329831091,"male");
insert into patient values(1002,2002,"vicky",43,"indiranagar mysore",7822614301,"male");
insert into patient values(1003,2003,"vidya",27,"kuvempunagar mysore",9908643501,"female");
insert into patient values(1004,2004,"anjali",35,"vidyaranyapuram mysore",8346832091,"female");
insert into patient values(1005,2005,"anush",25,"hebbal mysore",8759456784,"male");

insert into admits values(1000,"harshith");
insert into admits values(1001,"harshith");
insert into admits values(1002,"abhishek");
insert into admits values(1003,"harshith");
insert into admits values(1004,"harshith");














insert into staff values(3001,2000,"rahul","hebbal mysore",24,9432890871);
insert into staff values(3002,2001,"rohith","vijaynagar mysore",26,7832689301);
insert into staff values(3003,2002,"anjana","hebbal mysore",25,9873478901);
insert into staff values(3004,2003,"sagar","indiranagar",27,783468908);
insert into staff values(3005,2004,"darshan","hootgalli",28,8643973890);
insert into staff values(3006,2005,"dhanush","hebbal",35,8675462124);



insert into completed_by values(2000,3001);
insert into completed_by values(2001,3002);
insert into completed_by values(2002,3003);
insert into completed_by values(2003,3004);
insert into completed_by values(2004,3005);
insert into completed_by values(2005,3006);









USE `dlm`;
DROP procedure IF EXISTS `get_count_for_patient`;

DELIMITER $$
USE `dlm`$$
CREATE PROCEDURE `get_count_for_patient` (OUT the_count int)

BEGIN
	select count(*) into the_count from patient;
END$$

DELIMITER ;

call get_count_for_patient(@count);

select @count ;






