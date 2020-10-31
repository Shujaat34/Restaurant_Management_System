package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DBConnection {
	
	static Connection con = null;
	public static Connection getConnection() {
		try {
			if (con == null) {
				// Connecting with the Database
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:/restaurantdb","root","");
		}
		}catch(SQLException e) {
			// If any SQLException
			System.out.println("SQLEXCEPTTION in Login Class");
			JOptionPane.showMessageDialog(null, "Open the Wamp/Xamp Server First","Alert",JOptionPane.DEFAULT_OPTION);
			
		}catch(ClassNotFoundException e) {
			// if clas is not found
			JOptionPane.showMessageDialog(null, "Import the Jar FIle First of Connection","Alert",JOptionPane.DEFAULT_OPTION);
			System.out.println("Class not Found in Login Class");
			
		}
		return con;
	}

}
