package doaimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.DBConnection;
import dao.WaiterDAO;

public class WaiterDAOImpl implements WaiterDAO{
	
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
		public Integer addWaiter(String waiter_name, String waiter_Sal, String waiter_shift) { 
			String sql = "insert into waiter(waiter_name,waiter_salary,waiter_shift) values(?,?,?);";
			PreparedStatement ps = null;
			int row = 0;
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, waiter_name);
				ps.setString(2, waiter_Sal);
				ps.setString(3, waiter_shift);

				row = ps.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return row;
		}

		@Override
		public Integer deleteWaiter(Integer id) {
			PreparedStatement ps = null;
			String sql ="delete from waiter where id=?";
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
