package dlm;
import java.sql.*;

import javax.swing.JOptionPane;

public class mysqlconnection {
	
	
	Connection con=null;
	public static Connection dbconnector(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dlm","root","Madhu@0704");
//			JOptionPane.showMessageDialog(null, "connected");
			
			return con;
	}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}

}
}