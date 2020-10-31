package doaimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.DBConnection;
import dao.CookDAO;

public class CookDAOImpl implements CookDAO{
	
	Connection con = DBConnection.getConnection();

	@Override
	public ResultSet getDataResultSet() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "Select * from cook";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public Integer addCook(String name, String cokkSal, String cookShift) {
		String sql = "insert into cook(name,cook_sal,cook_shift) values(?,?,?);";
		PreparedStatement ps = null;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, cokkSal);
			ps.setString(3, cookShift);

			row = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return row;
	}

	@Override
	public Integer deleteCook(Integer id) {
		PreparedStatement ps = null;
		String sql ="delete from cook where id=?";
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
