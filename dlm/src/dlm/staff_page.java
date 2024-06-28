package dlm;
import java.sql.*;

import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.ImageIcon;

public class staff_page extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					staff_page frame = new staff_page();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 */Connection connection=null;
	 public void refreshtable() {
			try {
				String query="select * from staff";
				PreparedStatement pst=connection.prepareStatement(query);
				ResultSet rs=pst.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				
				pst.close();
				rs.close();
			}catch(Exception EX){
				EX.printStackTrace();
			}
		}
	 public void addstaff(String staff_id,String test_id, String staff_name, String address,String age, String phone_no) {
			
			try {
				
				String query = "insert into staff (s_id,t_id,s_name, address,age,ph_no) values (?,?,?,?,?,?)";
				PreparedStatement pst = connection.prepareStatement(query);
				pst.setString(1, staff_id);
				pst.setString(2, test_id);
				pst.setString(3, staff_name);
				pst.setString(4, address);
				pst.setString(5, age);
				pst.setString(6, phone_no);

				pst.execute();
				
				pst.close();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}
	 public void upstaff(String staff_id,String test_id, String staff_name, String address,String age, String phone_no) {
			
			try {
				
				String query = "update staff set t_id=?,s_name=?, address=?,age=?,ph_no=? where s_id=?";
				PreparedStatement pst = connection.prepareStatement(query);
				
				pst.setString(1, test_id);
				pst.setString(2, staff_name);
				pst.setString(3, address);
				pst.setString(4, age);
				pst.setString(5, phone_no);
				
				pst.setString(6, staff_id);

				pst.executeUpdate();
				
				pst.close();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}
	 public void completed_by(String test_id, String staff_id) {
			
			try {
				
				String query = "insert into completed_by (t_id,s_id) values (?,?)";
				PreparedStatement pst = connection.prepareStatement(query);
				pst.setString(1, test_id);
				pst.setString(2, staff_id);

				pst.execute();
				
				pst.close();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}
	private JTable table;
	public staff_page() {
		setTitle("staff info");
		connection=mysqlconnection.dbconnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 759, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 82, 626, 123);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Staffinfo");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel.setBounds(204, 29, 100, 27);
		contentPane.add(lblNewLabel);
		refreshtable();
		JButton btnNewButton = new JButton("Add  staff_info");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					JTextField staff_id = new JTextField(10);
					JTextField test_id = new JTextField(10);
				    JTextField staff_name = new JTextField(10);
				    JTextField address = new JTextField(10);
				    JTextField age = new JTextField(10);
				    JTextField phone = new JTextField(10);

				    JPanel myPanel = new JPanel();
				    myPanel.add(new JLabel("s_id:"));
				    myPanel.add(staff_id);
				    myPanel.add(Box.createVerticalStrut(15));
				    myPanel.add(new JLabel("t_id:"));
				    myPanel.add(test_id);
				    myPanel.add(Box.createVerticalStrut(15)); // a spacer
				    myPanel.add(new JLabel("s_name:"));
				    myPanel.add(staff_name);
				    myPanel.add(Box.createVerticalStrut(15)); // a spacer
				    myPanel.add(new JLabel("address:"));
				    myPanel.add(address);
				    myPanel.add(Box.createVerticalStrut(15)); // a spacer
				    myPanel.add(new JLabel("age:"));
				    myPanel.add(age);
				    myPanel.add(Box.createVerticalStrut(15)); // a spacer
				    myPanel.add(new JLabel("ph_no:"));
				    myPanel.add(phone);

				    int result = JOptionPane.showConfirmDialog(null, myPanel, 
				    		"Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
				    if (result == JOptionPane.OK_OPTION) {
				    	if(staff_id.getText().matches("[0-9]+") == false) {
							JOptionPane.showMessageDialog(null, "Enter A Valid staff_id");
						} else if(test_id.getText().matches("[0-9]+") == false) {
							JOptionPane.showMessageDialog(null, "Enter A Valid test_id");
						} else if(staff_name.getText().matches("[a-zA-Z]+")== false) {
							JOptionPane.showMessageDialog(null, "Enter A Valid staff name");
						} else if(age.getText().matches("[0-9]+")==false) {
							JOptionPane.showMessageDialog(null, "Enter A Valid age");
							
						} else if(phone.getText().matches("[0-9]+")==false) {
							JOptionPane.showMessageDialog(null, "Enter A Valid phone");
							
						} else if(phone.getText().length()!=10) {
							JOptionPane.showMessageDialog(null, "Enter A Valid phone");
							
						}else {
				    	addstaff(staff_id.getText(),test_id.getText(), staff_name.getText(), address.getText(), age.getText(), phone.getText());
				    	refreshtable();
				    	JOptionPane.showMessageDialog(null, "staffinfo added successfully");
				    	completed_by(test_id.getText(),staff_id.getText());
				    }	}
				    
					
				}catch(Exception Ec) {
					Ec.printStackTrace();
				}
				
			
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton.setBounds(20, 218, 213, 43);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Delete staff_info");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					String n=JOptionPane.showInputDialog("staff_id");
					String q="select s_id from staff where s_id=?";
					PreparedStatement pt=connection.prepareStatement(q);
					pt.setString(1,n);
					ResultSet s=pt.executeQuery();
					while(s.next()==false) {
						JOptionPane.showMessageDialog(null, "staffinfo not in database");
						break;
					}
					String que="delete from staff where s_id=? ";
					PreparedStatement pst=connection.prepareStatement(que);
					
					pst.setString(1,n);
					int rs=pst.executeUpdate();
					if(n.isEmpty()) {
						JOptionPane.showMessageDialog(null, "enter valid staff id");
						}
						else {
							JOptionPane.showMessageDialog(null, "staff info deleted");
						}
					
					refreshtable();
					
					pst.close();
					
				}catch(Exception ec) {
					ec.printStackTrace();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton_1.setBounds(285, 216, 213, 43);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("back to Admin page");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				admin_view ad=new admin_view();
				ad.setVisible(true);
			}
		});
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnNewButton_2.setBounds(273, 350, 213, 43);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Update Staff_info");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					JTextField staff_id = new JTextField(10);
					JTextField test_id = new JTextField(10);
				    JTextField staff_name = new JTextField(10);
				    JTextField address = new JTextField(10);
				    JTextField age = new JTextField(10);
				    JTextField phone = new JTextField(10);

				    JPanel myPanel = new JPanel();
				    myPanel.add(new JLabel("s_id:"));
				    myPanel.add(staff_id);
				    myPanel.add(Box.createVerticalStrut(15));
				    myPanel.add(new JLabel("t_id:"));
				    myPanel.add(test_id);
				    myPanel.add(Box.createVerticalStrut(15)); // a spacer
				    myPanel.add(new JLabel("s_name:"));
				    myPanel.add(staff_name);
				    myPanel.add(Box.createVerticalStrut(15)); // a spacer
				    myPanel.add(new JLabel("address:"));
				    myPanel.add(address);
				    myPanel.add(Box.createVerticalStrut(15)); // a spacer
				    myPanel.add(new JLabel("age:"));
				    myPanel.add(age);
				    myPanel.add(Box.createVerticalStrut(15)); // a spacer
				    myPanel.add(new JLabel("ph_no:"));
				    myPanel.add(phone);

				    int result = JOptionPane.showConfirmDialog(null, myPanel, 
				    		"Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
				    if (result == JOptionPane.OK_OPTION) {
				    	if(staff_id.getText().matches("[0-9]+") == false) {
							JOptionPane.showMessageDialog(null, "Enter A Valid staff_id");
						} else if(test_id.getText().matches("[0-9]+") == false) {
							JOptionPane.showMessageDialog(null, "Enter A Valid test_id");
						} else if(staff_name.getText().matches("[a-zA-Z]+")== false) {
							JOptionPane.showMessageDialog(null, "Enter A Valid staff name");
						} else if(age.getText().matches("[0-9]+")==false) {
							JOptionPane.showMessageDialog(null, "Enter A Valid age");
							
						} else if(phone.getText().matches("[0-9]+")==false) {
							JOptionPane.showMessageDialog(null, "Enter A Valid phone");
							
						} else if(phone.getText().length()==10) {
							JOptionPane.showMessageDialog(null, "Enter A Valid phone");
							
						}else {
				    	addstaff(staff_id.getText(),test_id.getText(), staff_name.getText(), address.getText(), age.getText(), phone.getText());
				    	refreshtable();
				    	JOptionPane.showMessageDialog(null, "staffinfo added successfully");
				    	completed_by(test_id.getText(),staff_id.getText());
				    }	}
				    
					
				}catch(Exception Ec) {
					Ec.printStackTrace();
				}
			}
		});
		btnNewButton_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton_3.setBounds(542, 216, 165, 43);
		contentPane.add(btnNewButton_3);
		
		
		JLabel lblNewLabel_13 = new JLabel("");
		lblNewLabel_13.setIcon(new ImageIcon(report.class.getResource("/dlm/images/staffinfobackgroundimage.jpg")));
		lblNewLabel_13.setBounds(0, 0, 900, 900);
		contentPane.add(lblNewLabel_13);
	}
}
