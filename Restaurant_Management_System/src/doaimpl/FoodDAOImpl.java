package doaimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.DBConnection;
import dao.FoodDAO;

public class FoodDAOImpl implements FoodDAO{
	
	Connection con = DBConnection.getConnection();
	@Override
	public ResultSet getDataResultSet() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "Select f.id , f.food_name,f.price,f.food_size,c.category from food f inner join category c on f.food_category=c.id";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public Integer addFood(String foodName, String price, String foodsize,String categoryID) {
		String sql = "insert into food(food_name,price,food_size,food_category) values(?,?,?,?);";
		PreparedStatement ps = null;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, foodName);
			ps.setString(2, price);
			ps.setString(3, foodsize);
			ps.setString(4, categoryID);

			row = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return row;
	}

	@Override
	public Integer deleteFood(Integer id) {
		PreparedStatement ps = null;
		String sql ="delete from food where id=?";
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

	@Override
	public Integer getCategoryIdByName(String categoryName) { 
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "Select id from category where name=?";
		int id = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, categoryName);
			rs =ps.executeQuery();
			while(rs.next()) {
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

}
