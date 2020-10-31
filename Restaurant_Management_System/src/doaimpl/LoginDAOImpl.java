package doaimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.DBConnection;
import dao.LoginDAO;

public class LoginDAOImpl implements LoginDAO{
	
	// Establishing the Connection with the DATABASE
	Connection con = DBConnection.getConnection();

	// For user Login checking whether the data exists in the database or not
	@Override
	public boolean loginSuccess(String username, String pass) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "Select * from login where username=? and password=?;";
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, pass);
			
			rs = ps.executeQuery();
			
			if(rs.next())
				return true;
			else
				return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}

	// For creating new Account Inserting the input data into the Databasse
	@Override
	public int createNewAccount(String username, String pass, String email, String post) {
		String sql = "insert into login(username,password,email,post) values(?,?,?,?)";
		
		
		
		PreparedStatement ps = null;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, pass);
			ps.setString(3, email);
			ps.setString(4, post);
		
			row = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return row;
	}

}
