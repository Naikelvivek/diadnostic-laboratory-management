package dlm;
import java.sql.*;

import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class admin_view extends JFrame {

	private JPanel contentPane;
	private JFrame frmLoginPage;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin_view frame = new admin_view();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */Connection connection=null;
	public admin_view() {
		connection=mysqlconnection.dbconnector();
		setTitle("diagnostic laboratory");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 764, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton patientbutton = new JButton("PATIENT");
		
		patientbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				patient_view patient=new patient_view();
				patient.setVisible(true);
			}
		});
		patientbutton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		patientbutton.setBounds(55, 93, 185, 44);
		contentPane.add(patientbutton);
		
		JButton testbutton = new JButton("TEST");
		testbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				test t=new test();
				t.setVisible(true);
				
			}
		});
		testbutton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		testbutton.setBounds(55, 180, 185, 44);
		contentPane.add(testbutton);
		
		JButton staffbutton = new JButton("STAFF");
		staffbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				staff_page st=new staff_page();
				st.setVisible(true);
			}
		});
		staffbutton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		staffbutton.setBounds(55, 266, 185, 44);
		contentPane.add(staffbutton);
		
		JButton return_loginbutton = new JButton("LOGOUT");
		return_loginbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n=JOptionPane.showConfirmDialog(null, "do you want to logout","logout",JOptionPane.YES_NO_OPTION);
				if(n==0)
				{
					dispose();
					login log=new login();
					log.frmLoginPage.setVisible(true);
				}
				
				
		
			}
		});
		return_loginbutton.setFont(new Font("Times New Roman", Font.BOLD, 14));
		return_loginbutton.setBounds(259, 391, 122, 23);
		contentPane.add(return_loginbutton);
		
		JLabel lblNewLabel = new JLabel("welcome to diagnostic laboratory");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblNewLabel.setBounds(121, 30, 344, 33);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("REPORT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				report r=new report();
				r.setVisible(true);
//				try {
//					String n=JOptionPane.showInputDialog("patient_id");
//					String que="select h.p_id,p.p_name,h.r_id,r.test_result,tg.t_id,t.test_name,t.test_charge,cb.s_id,s.s_name from holds h,report r,patient p,staff s,t_generates tg,test t,completed_by cb where h.p_id=? and p.p_id=h.p_id and r.r_id=h.r_id and tg.r_id=r.r_id and t.t_id=tg.t_id and cb.t_id=t.t_id and s.s_id=cb.s_id;";
//					PreparedStatement pst=connection.prepareStatement(que);
//					pst.setString(1,n);
//					ResultSet rs=pst.executeQuery();
//					dispose();
//					report r=new report(rs);
//					r.setVisible(true);
//					
//					
//				}catch(Exception ec) {
//					ec.printStackTrace();
//				}
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton.setBounds(455, 180, 137, 44);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("payments");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				payments p=new payments();
				p.setVisible(true);
				
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton_1.setBounds(455, 278, 137, 32);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(admin_view.class.getResource("/dlm/images/adminpage.jpg")));
		lblNewLabel_1.setBounds(0, 0, 748, 441);
		contentPane.add(lblNewLabel_1);
	}
}
