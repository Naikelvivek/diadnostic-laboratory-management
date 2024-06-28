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
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;

public class test extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test frame = new test();
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
	public void refreshtable() {
		try {
			String query="select * from test";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			String q="";
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			pst.close();
			rs.close();
		}catch(Exception EX){
			EX.printStackTrace();
		}
	}
public void addtest(String test_id,String test_name, String test_charge) {
		
		try {
			
			String query = "insert into test (t_id,test_name,test_charge) values (?,?,?)";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, test_id);
			pst.setString(2, test_name);
			pst.setString(3, test_charge);
			

			pst.execute();
			
			pst.close();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
}
public void uptest(String test_id,String test_name, String test_charge) {
	
	try {
		
		String query = "update test set test_name=?,test_charge=? where t_id=?";
		PreparedStatement pst = connection.prepareStatement(query);
		
		pst.setString(1, test_name);
		pst.setString(2, test_charge);
		pst.setString(3, test_id);
		

		pst.executeUpdate();
		
		pst.close();
		
	} catch (SQLException e1) {
		e1.printStackTrace();
	}
	
}

	public test() {
		setTitle("test view");
		connection=mysqlconnection.dbconnector();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 551, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("TEST");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 19));
		lblNewLabel.setBounds(252, 27, 59, 23);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 117, 516, 127);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_2 = new JButton("back to admin page");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				admin_view ad=new admin_view();
				ad.setVisible(true);
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton_2.setBounds(360, 327, 165, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton = new JButton("Add Test");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					JTextField test_id = new JTextField(10);
					JTextField test_name = new JTextField(10);
				    JTextField test_charge = new JTextField(10);
				    

				    JPanel myPanel = new JPanel();
				    myPanel.add(new JLabel("test_id:"));
				    myPanel.add(test_id);
				    myPanel.add(Box.createVerticalStrut(15));
				    myPanel.add(new JLabel("test_name:"));
				    myPanel.add(test_name);
				    myPanel.add(Box.createVerticalStrut(15)); // a spacer
				    myPanel.add(new JLabel("test_charge:"));
				    myPanel.add(test_charge);
				    

				    int result = JOptionPane.showConfirmDialog(null, myPanel, 
				    		"Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
				    if (result == JOptionPane.OK_OPTION) {
				    	
					    	if(test_id.getText().matches("[0-9]+") == false) {
								JOptionPane.showMessageDialog(null, "Enter A Valid test_id");
							} else if(test_name.getText().matches("[a-zA-Z]+") == false) {
								JOptionPane.showMessageDialog(null, "Enter A Valid test_name");
							} else if(test_charge.getText().matches("[0-9]+")== false) {
								JOptionPane.showMessageDialog(null, "Enter A Valid test_charge");
							} else {
				    	addtest(test_id.getText(), test_name.getText(), test_charge.getText());
				    	JOptionPane.showMessageDialog(null, "testinfo added successfully");
				    	refreshtable();
							}
				    	
				    }
				}catch(Exception Ec) {
					Ec.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnNewButton.setBounds(57, 286, 105, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Delete Test");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
try {
	String n=JOptionPane.showInputDialog("test_id");
	String q="select t_id from test where t_id=?";
	PreparedStatement pt=connection.prepareStatement(q);
	pt.setString(1,n);
	ResultSet s=pt.executeQuery();
	while(s.next()==false) {
		JOptionPane.showMessageDialog(null, "testinfo not in database");
		break;
	}
					String que="delete from test where t_id=? ";
					PreparedStatement pst=connection.prepareStatement(que);
					
					pst.setString(1,n);
					int rs=pst.executeUpdate();
					if(n.isEmpty()) {
						JOptionPane.showMessageDialog(null, "enter valid test id");
						}
						else {
							JOptionPane.showMessageDialog(null, "test info deleted");
						}
					
					refreshtable();
					
				}catch(Exception ec) {
					ec.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnNewButton_1.setBounds(195, 286, 105, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_3 = new JButton("Update Test");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					JTextField test_id = new JTextField(10);
					JTextField test_name = new JTextField(10);
				    JTextField test_charge = new JTextField(10);
				    

				    JPanel myPanel = new JPanel();
				    myPanel.add(new JLabel("test_id:"));
				    myPanel.add(test_id);
				    myPanel.add(Box.createVerticalStrut(15));
				    myPanel.add(new JLabel("test_name:"));
				    myPanel.add(test_name);
				    myPanel.add(Box.createVerticalStrut(15)); // a spacer
				    myPanel.add(new JLabel("test_charge:"));
				    myPanel.add(test_charge);
				    

				    int result = JOptionPane.showConfirmDialog(null, myPanel, 
				    		"Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
				    if (result == JOptionPane.OK_OPTION) {
				    	if (result == JOptionPane.OK_OPTION) {
					    	if(test_id.getText().matches("[0-9]+") == false) {
								JOptionPane.showMessageDialog(null, "Enter A Valid patient_id");
							} else if(test_name.getText().matches("[a-zA-Z]+") == false) {
								JOptionPane.showMessageDialog(null, "Enter A Valid test_id");
							} else if(test_charge.getText().matches("[0-9]+")== false) {
								JOptionPane.showMessageDialog(null, "Enter A Valid patient name");
							} else {
				    	uptest(test_id.getText(), test_name.getText(), test_charge.getText());
				    	JOptionPane.showMessageDialog(null, "testinfo added successfully");
				    	refreshtable();
							}
				    }	
				    }
				}catch(Exception Ec) {
					Ec.printStackTrace();
				}
			}
		});
		btnNewButton_3.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnNewButton_3.setBounds(360, 286, 105, 23);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel_13 = new JLabel("");
		lblNewLabel_13.setIcon(new ImageIcon(report.class.getResource("/dlm/images/image.jpg")));
		lblNewLabel_13.setBounds(0, 0, 650, 554);
		contentPane.add(lblNewLabel_13);
		
		refreshtable();
		}
	}

