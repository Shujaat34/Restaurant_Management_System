package doaimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.DBConnection;
import dao.CustomerDAO;
public class CustomerDAOImpl implements CustomerDAO{
	// Establishing the Connection
	Connection con = DBConnection.getConnection();
	
		// For populating the Data into the table
		public ResultSet getDataResultSet() {
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = "Select * from waiter";
			try {
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return rs;
		}

		@Override
		public Integer addCustomer(String customer, String age, String receiptID) {
			String sql = "insert into waiter(customer_name,age,receipt_id) values(?,?,?);";
			PreparedStatement ps = null;
			int row = 0;
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, customer);
				ps.setString(2, age);
				ps.setString(3, receiptID);

				row = ps.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return row;
		}

		@Override
		public Integer deleteCustomer(Integer id) {
			PreparedStatement ps = null;
			String sql ="delete from customer where id=?";
			int row = 0;
			try {
				ps = con.prepareStatement(sql);
				ps.setInt(1, id);
				row = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return row;
		}
		
		
		
}
