package dlm;
import java.sql.*;

import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JComboBox;
import javax.swing.JList;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

public class report extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBoxpatientname;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					report frame = new report();
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	Connection connection=null;
	private JTextField textFieldr_id;
	private JTextField textFields_name;
	private JTextField textFieldt_id;
	private JTextField textFieldt_name;
	private JTextField textFieldt_charge;
	private JTextField textFieldt_result;
	private JTextField textFieldpatient_name;
	private JTextField textFielddat;
	private JTextField textFieldmode_of_pay;
	private JTextField textFieldp_id;
	private JTextField textFieldbill_no;
	private JTextField textFieldmode;

	public void fillcomboboxpatient() {
		try {
			
			String qu="select * from patient";
			PreparedStatement p=connection.prepareStatement(qu);
			ResultSet r=p.executeQuery();
			
			while(r.next()) {
				comboBoxpatientname.addItem(r.getString("p_id"));
				
			}
			r.close();
			p.close();
		}catch(Exception es) {
			es.printStackTrace();
		}
	}
	
	public report() {
		connection=mysqlconnection.dbconnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 593);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		
		
		
		JLabel lblNewLabel = new JLabel("REPORT");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(272, 33, 90, 24);
		contentPane.add(lblNewLabel);
		
		comboBoxpatientname = new JComboBox();
		comboBoxpatientname.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					 
					String q=" select p.p_id,p.p_name,s.s_name,t.t_id,t.test_name,t.test_charge from patient p,test t,staff s,completed_by cb where p.p_id=? and t.t_id=p.t_id and cb.t_id=t.t_id and s.s_id=cb.s_id ";			
					
					PreparedStatement ps=connection.prepareStatement(q);
					ps.setString(1,(String)comboBoxpatientname.getSelectedItem());
					ResultSet r=ps.executeQuery();
					
					
					
					while(r.next()) {
						
						textFieldp_id.setText(r.getString("p_id"));
						textFieldpatient_name.setText(r.getString("p_name"));
						textFields_name.setText(r.getString("s_name"));
						textFieldt_id.setText(r.getString("t_id"));
						textFieldt_name.setText(r.getString("test_name"));
						textFieldt_charge.setText(r.getString("test_charge"));
						
						
					}
					
				}catch(Exception ec) {
					ec.printStackTrace();
				}
			}
		});
		comboBoxpatientname.setBounds(234, 83, 128, 20);
		contentPane.add(comboBoxpatientname);
		
		JLabel lblNewLabel_2 = new JLabel("Choose patient id");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_2.setBounds(67, 71, 157, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("report_id");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_4.setBounds(341, 154, 83, 20);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("staff name");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_5.setBounds(24, 245, 83, 24);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("test_id");
		lblNewLabel_6.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_6.setBounds(21, 289, 65, 24);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("test_name");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_7.setBounds(22, 324, 85, 24);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("test_charge");
		lblNewLabel_8.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_8.setBounds(22, 366, 83, 24);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("test_result");
		lblNewLabel_9.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_9.setBounds(341, 197, 98, 18);
		contentPane.add(lblNewLabel_9);
		
		textFieldr_id = new JTextField();
		textFieldr_id.setBounds(443, 155, 157, 20);
		contentPane.add(textFieldr_id);
		textFieldr_id.setColumns(10);
		
		textFields_name = new JTextField();
		textFields_name.setBounds(115, 248, 143, 20);
		contentPane.add(textFields_name);
		textFields_name.setColumns(10);
		
		textFieldt_id = new JTextField();
		textFieldt_id.setBounds(115, 292, 143, 20);
		contentPane.add(textFieldt_id);
		textFieldt_id.setColumns(10);
		
		textFieldt_name = new JTextField();
		textFieldt_name.setBounds(115, 327, 143, 20);
		contentPane.add(textFieldt_name);
		textFieldt_name.setColumns(10);
		
		textFieldt_charge = new JTextField();
		textFieldt_charge.setBounds(115, 369, 143, 20);
		contentPane.add(textFieldt_charge);
		textFieldt_charge.setColumns(10);
		
		textFieldt_result = new JTextField();
		textFieldt_result.setBounds(443, 195, 157, 20);
		contentPane.add(textFieldt_result);
		textFieldt_result.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("patient_name");
		lblNewLabel_10.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_10.setBounds(22, 152, 98, 24);
		contentPane.add(lblNewLabel_10);
		
		textFieldpatient_name = new JTextField();
		textFieldpatient_name.setBounds(118, 155, 140, 20);
		contentPane.add(textFieldpatient_name);
		textFieldpatient_name.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Date");
		lblNewLabel_11.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_11.setBounds(341, 254, 43, 14);
		contentPane.add(lblNewLabel_11);
		
		textFielddat = new JTextField();
		textFielddat.setBounds(443, 248, 157, 20);
		contentPane.add(textFielddat);
		textFielddat.setColumns(10);
		
		textFieldp_id = new JTextField();
		textFieldp_id.setBounds(115, 197, 143, 20);
		contentPane.add(textFieldp_id);
		textFieldp_id.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Patient_id");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_1.setBounds(22, 198, 83, 17);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("PAY BILL");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String g="insert into  report values(?,?,?,?,?)";
					
					PreparedStatement ps=connection.prepareStatement(g);
					ps.setString(1, textFieldp_id.getText());
					ps.setString(2, textFieldr_id.getText());
					ps.setString(3, textFieldt_id.getText());
					ps.setString(4, textFielddat.getText() );
					ps.setString(5, textFieldt_result.getText());
					
					ps.executeUpdate();
					ps.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
				try {
					String g="insert into  payment values(?,?,?)";
					
					PreparedStatement ps=connection.prepareStatement(g);
					ps.setString(1, textFieldr_id.getText());
					ps.setString(2, textFieldbill_no.getText());
					ps.setString(3, textFieldmode.getText());
					
					ps.executeUpdate();
					ps.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
				try {
					String g="insert into  holds values(?,?)";
					
					PreparedStatement ps=connection.prepareStatement(g);
					ps.setString(1, textFieldp_id.getText());
					ps.setString(2, textFieldr_id.getText());
					
					
					ps.executeUpdate();
					ps.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "payment successfully");
				int n=JOptionPane.showConfirmDialog(null, "do you want to go back to admin page","admin",JOptionPane.YES_NO_OPTION);
				if(n==0)
				{
					dispose();
					admin_view log=new admin_view();
					log.setVisible(true);
				}
				else {
					report r=new report();
					r.setVisible(true);
				}
			dispose();
			
				
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton.setBounds(312, 465, 157, 38);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("Bill_no");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_3.setBounds(341, 299, 66, 20);
		contentPane.add(lblNewLabel_3);
		
		textFieldbill_no = new JTextField();
		textFieldbill_no.setBounds(443, 292, 157, 20);
		contentPane.add(textFieldbill_no);
		textFieldbill_no.setColumns(10);
		
		JLabel lblNewLabel_12 = new JLabel("mode_of_payment");
		lblNewLabel_12.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_12.setBounds(341, 347, 128, 24);
		contentPane.add(lblNewLabel_12);
		
		textFieldmode = new JTextField();
		textFieldmode.setBounds(479, 350, 121, 20);
		contentPane.add(textFieldmode);
		textFieldmode.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("back to admin");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				admin_view ad=new admin_view();
				ad.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnNewButton_1.setBounds(80, 471, 121, 29);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_13 = new JLabel("");
		lblNewLabel_13.setIcon(new ImageIcon(report.class.getResource("/dlm/images/LABIMAGE.jpg")));
		lblNewLabel_13.setBounds(0, 0, 683, 554);
		contentPane.add(lblNewLabel_13);
		
		fillcomboboxpatient();
		
	
		
	}
}
