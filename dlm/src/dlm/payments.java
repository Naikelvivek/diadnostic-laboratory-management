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
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

public class payments extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					payments frame = new payments();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void refreshtable() {
		try {
			String query="select p.p_id,p.p_name,s.s_name,t.t_id,t.test_name,t.test_charge,r.r_id,r.test_result,z.bill_no,z.mode_of_pay from patient p,test t,staff s,completed_by cb,report r,payment z,holds h where h.p_id=p.p_id and r.r_id=h.r_id and t.t_id=r.t_id and cb.t_id=t.t_id and s.s_id=cb.s_id ";
					
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			pst.close();
			rs.close();
		}catch(Exception EX){
			EX.printStackTrace();
		}
	}
	/**
	 * Create the frame.
	 */
	Connection connection=null;
	private JTable table;
	public payments() {
		connection=mysqlconnection.dbconnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 849, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 82, 813, 80);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("payments");
		lblNewLabel.setForeground(Color.GREEN);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(354, 25, 87, 22);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("back to admin page");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				admin_view ad=new admin_view();
				ad.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnNewButton.setBounds(346, 373, 214, 52);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(payments.class.getResource("/dlm/images/staffupdateimage.jpg")));
		lblNewLabel_1.setBounds(0, 0, 833, 464);
		contentPane.add(lblNewLabel_1);
		refreshtable();
	}
}
