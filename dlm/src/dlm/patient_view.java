package dlm;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JButton;
import java.sql.*;

import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JTextField;


public class patient_view extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					patient_view frame = new patient_view();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection=null;
	private JTextField textFieldsearch;
	/**
	 * Create the frame.
	 */
	public void refreshtable() {
		try {
			String query="select * from patient";
					
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			pst.close();
			rs.close();
		}catch(Exception EX){
			EX.printStackTrace();
		}
	}
public void addpat(String p_id,String test_id,String patient_name, String age, String address, String phone_no,String gender) {
		
		try {
			
			String query = "insert into patient (p_id,t_id,p_name, age, address, ph_no, gender) values (?,?,?,?,?,?,?)";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setString(1, p_id);
			pst.setString(2, test_id);
			pst.setString(3, patient_name);
			pst.setString(4, age);
			pst.setString(5, address);
			pst.setString(6, phone_no);
			pst.setString(7, gender);

			pst.execute();
			
			pst.close();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}
public void uppat(String p_id,String test_id,String patient_name, String age, String address, String phone_no,String gender) {
	
	try {
		
		String query = "update patient set t_id=?,p_name=?, age=?, address=?, ph_no=?, gender=? where p_id=?";
		PreparedStatement pst = connection.prepareStatement(query);
		
		pst.setString(1, test_id);
		pst.setString(2, patient_name);
		pst.setString(3, age);
		pst.setString(4, address);
		pst.setString(5, phone_no);
		pst.setString(6, gender);
		pst.setString(7, p_id);

		pst.executeUpdate();
		
		pst.close();
		
	} catch (SQLException e1) {
		e1.printStackTrace();
	}
	
}
	public patient_view() {
		setTitle("patient_view");
		connection=mysqlconnection.dbconnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 827, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 74, 791, 124);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Add patientinfo");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					JTextField p_id = new JTextField(10);
					JTextField test_id = new JTextField(10);
					JTextField patient_name = new JTextField(10);
				    JTextField age = new JTextField(10);
				    JTextField address = new JTextField(10);
				    JTextField phone = new JTextField(10);
				    JTextField gender = new JTextField(10);

				    JPanel myPanel = new JPanel();
				    myPanel.add(new JLabel("p_id:"));
				    myPanel.add(p_id);
				    myPanel.add(Box.createVerticalStrut(15));
				    myPanel.add(new JLabel("test_id:"));
				    myPanel.add(test_id);
				    myPanel.add(Box.createVerticalStrut(15));
				    myPanel.add(new JLabel("patient_name:"));
				    myPanel.add(patient_name);
				    myPanel.add(Box.createVerticalStrut(15)); // a spacer
				    myPanel.add(new JLabel("age:"));
				    myPanel.add(age);
				    myPanel.add(Box.createVerticalStrut(15)); // a spacer
				    myPanel.add(new JLabel("address:"));
				    myPanel.add(address);
				    myPanel.add(Box.createVerticalStrut(15)); // a spacer
				    myPanel.add(new JLabel("Phone:"));
				    myPanel.add(phone);
				    myPanel.add(Box.createVerticalStrut(15)); // a spacer
				    myPanel.add(new JLabel("gender:"));
				    myPanel.add(gender);

				    int result = JOptionPane.showConfirmDialog(null, myPanel, 
				    		"Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
				   
				   
				    	
				    	
						 
				    if (result == JOptionPane.OK_OPTION) {
				    	if(p_id.getText().matches("[0-9]+") == false) {
							JOptionPane.showMessageDialog(null, "Enter A Valid patient_id");
						} else if(test_id.getText().matches("[0-9]+") == false) {
							JOptionPane.showMessageDialog(null, "Enter A Valid test_id");
						} else if(patient_name.getText().matches("[a-zA-Z]+")== false) {
							JOptionPane.showMessageDialog(null, "Enter A Valid patient name");
						} else if(age.getText().matches("[0-9]+") == false) {
							JOptionPane.showMessageDialog(null, "Enter A Valid age");
						} else if(phone.getText().matches("[0-9]+")==false) {
							JOptionPane.showMessageDialog(null, "Enter A Valid phone");
							
						} 
						else if(phone.getText().length()!=10) {
							JOptionPane.showMessageDialog(null, "Enter A Valid phone");
							}
						else if(gender.getText().matches("[a-zA-Z]+") == false) {
							JOptionPane.showMessageDialog(null, "Enter A Valid gender");	
						}else {
				    	addpat(p_id.getText(),test_id.getText(),patient_name.getText(), age.getText(), address.getText(), phone.getText(), gender.getText());
				    	JOptionPane.showMessageDialog(null, "patientinfo added successfully");
				    	refreshtable();
						}
				    }	
				    }
				
				catch(Exception Ec) {
					Ec.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(27, 227, 137, 43);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Delete patientinfo");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				contentPane.removeAll();
//				delete del= new delete();
//				del.setVisible(true);s
				
				try {
					String n=JOptionPane.showInputDialog("patient_id");
					String q="select p_id from patient where p_id=?";
					PreparedStatement pt=connection.prepareStatement(q);
					pt.setString(1,n);
					ResultSet s=pt.executeQuery();
					while(s.next()==false) {
						JOptionPane.showMessageDialog(null, "patient not in database");
						break;
					}
//						if(s!=n) {
//							JOptionPane.showMessageDialog(null, "patient not in database");
//						}
				
					String que="delete from patient where p_id=? ";
					PreparedStatement pst=connection.prepareStatement(que);
					
					pst.setString(1,n);
					int rs=pst.executeUpdate();
					if(n.isEmpty()) {
					JOptionPane.showMessageDialog(null, "enter valid patient id");
					}
					else {
						JOptionPane.showMessageDialog(null, "patient info deleted");
					}
					
					
					
					
					refreshtable();
					
				}catch(Exception ec) {
					ec.printStackTrace();
				}
				
				
				
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnNewButton_1.setBounds(224, 227, 137, 43);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("patient table");
		lblNewLabel.setForeground(new Color(255, 255, 0));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel.setBounds(155, 27, 149, 36);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_2 = new JButton("back to admin_view");
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				admin_view adm=new admin_view();
				adm.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(598, 359, 150, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("search patient through p_id");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query="select * from patient where p_id=?";
							
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1,textFieldsearch.getText());
					ResultSet rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					pst.close();
					rs.close();
				}catch(Exception EX){
					EX.printStackTrace();
				}
				
			}
		});
		btnNewButton_3.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnNewButton_3.setBounds(611, 235, 190, 43);
		contentPane.add(btnNewButton_3);
		
		textFieldsearch = new JTextField();
		textFieldsearch.setBounds(451, 250, 137, 20);
		contentPane.add(textFieldsearch);
		textFieldsearch.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("refresh");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				refreshtable();
			}
		});
		btnNewButton_4.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnNewButton_4.setBounds(565, 27, 137, 36);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("total number of patients");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int co=0;
				try {
					String query="call get_count_for_patient(@count);";
					
					PreparedStatement pst=connection.prepareStatement(query);
					ResultSet r=pst.executeQuery();
					String q="select @count";
					PreparedStatement ps=connection.prepareStatement(q);
					ResultSet n=ps.executeQuery();
					while(n.next())
		{
			co=n.getInt(1);
						
		}
					JOptionPane.showMessageDialog(null, co);
					pst.close();
					r.close();
					n.close();
				}catch(Exception EX){
					EX.printStackTrace();
				}
			}
		});
		btnNewButton_5.setBounds(250, 359, 253, 23);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Update patientinfo");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					JTextField p_id = new JTextField(10);
					JTextField test_id = new JTextField(10);
					JTextField patient_name = new JTextField(10);
				    JTextField age = new JTextField(10);
				    JTextField address = new JTextField(10);
				    JTextField phone = new JTextField(10);
				    JTextField gender = new JTextField(10);

				    JPanel myPanel = new JPanel();
				    myPanel.add(new JLabel("p_id:"));
				    myPanel.add(p_id);
				    myPanel.add(Box.createVerticalStrut(15));
				    myPanel.add(new JLabel("test_id:"));
				    myPanel.add(test_id);
				    myPanel.add(Box.createVerticalStrut(15));
				    myPanel.add(new JLabel("patient_name:"));
				    myPanel.add(patient_name);
				    myPanel.add(Box.createVerticalStrut(15)); // a spacer
				    myPanel.add(new JLabel("age:"));
				    myPanel.add(age);
				    myPanel.add(Box.createVerticalStrut(15)); // a spacer
				    myPanel.add(new JLabel("address:"));
				    myPanel.add(address);
				    myPanel.add(Box.createVerticalStrut(15)); // a spacer
				    myPanel.add(new JLabel("Phone:"));
				    myPanel.add(phone);
				    myPanel.add(Box.createVerticalStrut(15)); // a spacer
				    myPanel.add(new JLabel("gender:"));
				    myPanel.add(gender);

				    int result = JOptionPane.showConfirmDialog(null, myPanel, 
				    		"Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
				   
				   
				    	
				    	
						 
				    if (result == JOptionPane.OK_OPTION) {
				    	if(p_id.getText().matches("[0-9]+") == false) {
							JOptionPane.showMessageDialog(null, "Enter A Valid patient_id");
						} else if(test_id.getText().matches("[0-9]+") == false) {
							JOptionPane.showMessageDialog(null, "Enter A Valid test_id");
						} else if(patient_name.getText().matches("[a-zA-Z]+")== false) {
							JOptionPane.showMessageDialog(null, "Enter A Valid patient name");
						} else if(age.getText().matches("[0-9]+") == false) {
							JOptionPane.showMessageDialog(null, "Enter A Valid age");
						} else if(phone.getText().matches("[0-9]+")==false) {
							JOptionPane.showMessageDialog(null, "Enter A Valid phone");
							
						} 
						else if(phone.getText().length()!=10) {
							JOptionPane.showMessageDialog(null, "Enter A Valid phone");
							}
						else if(gender.getText().matches("[a-zA-Z]+") == false) {
							JOptionPane.showMessageDialog(null, "Enter A Valid gender");	
						}else {
				    	uppat(p_id.getText(),test_id.getText(),patient_name.getText(), age.getText(), address.getText(), phone.getText(), gender.getText());
				    	JOptionPane.showMessageDialog(null, "patientinfo added successfully");
				    	refreshtable();
						}
				    }	
				    }
				
				catch(Exception Ec) {
					Ec.printStackTrace();
				}
				
				
			}
		});
		btnNewButton_6.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnNewButton_6.setBounds(127, 299, 149, 30);
		contentPane.add(btnNewButton_6);
		JLabel lblNewLabel_13 = new JLabel("");
		lblNewLabel_13.setIcon(new ImageIcon(report.class.getResource("/dlm/images/patient_viewpage image.jpg")));
		lblNewLabel_13.setBounds(0, 0, 900, 650);
		contentPane.add(lblNewLabel_13);
		refreshtable();
	}
}
