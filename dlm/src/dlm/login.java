package dlm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.sql.*;
public class login {

	private static final mysqlconnection NULL = null;
	JFrame frmLoginPage;
	private JTextField textFieldu_name;
	private JPasswordField passwordFieldpwd;

	/**
	 * Launch the application.
	 */
	

		
		
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frmLoginPage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	Connection connection=null;
	public login() {
		initialize();
		connection=mysqlconnection.dbconnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLoginPage = new JFrame();
		frmLoginPage.setTitle("LOGIN PAGE");
		frmLoginPage.setBounds(100, 100, 545, 443);
		frmLoginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoginPage.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("ADMIN LOGIN");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setIcon(null);
		lblNewLabel_1.setBackground(Color.RED);
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setLabelFor(lblNewLabel_1);
		lblNewLabel_1.setBounds(179, 46, 116, 48);
		frmLoginPage.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("USERNAME");
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setForeground(new Color(0, 128, 128));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(22, 107, 94, 40);
		frmLoginPage.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("PASSWORD");
		lblNewLabel_2.setBackground(Color.BLACK);
		lblNewLabel_2.setForeground(new Color(0, 128, 128));
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_2.setBounds(22, 175, 94, 29);
		frmLoginPage.getContentPane().add(lblNewLabel_2);
		
		textFieldu_name = new JTextField();
		textFieldu_name.setBounds(155, 118, 148, 20);
		frmLoginPage.getContentPane().add(textFieldu_name);
		textFieldu_name.setColumns(10);
		
		passwordFieldpwd = new JPasswordField();
		passwordFieldpwd.setBounds(155, 180, 148, 20);
		frmLoginPage.getContentPane().add(passwordFieldpwd);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				try {
					int count=0;
					//Statement stmt=connection.createStatement();
					String query="select * from dlm.adm where u_name=? and pwd=?";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1,textFieldu_name.getText());
					pst.setString(2,passwordFieldpwd.getText());
					ResultSet rs=pst.executeQuery();
					while(rs.next()) {
						count++;
					}
					if(count==0)
					{
						JOptionPane.showMessageDialog(null,"INVALID ADMIN");
					}
					if(count==1)
					{
					frmLoginPage.dispose();
					JOptionPane.showMessageDialog(null,"login successfull");
					admin_view adm=new admin_view();
					adm.setVisible(true);
					}
						pst.close();
						rs.close();
					}catch(Exception E) {
						E.printStackTrace();
					}
				
				
			}
		});
		
		btnNewButton.setBounds(189, 252, 89, 23);
		frmLoginPage.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(login.class.getResource("/dlm/images/LABIMAGE.jpg")));
		lblNewLabel_4.setBounds(0, 0, 529, 404);
		frmLoginPage.getContentPane().add(lblNewLabel_4);
	}
}
