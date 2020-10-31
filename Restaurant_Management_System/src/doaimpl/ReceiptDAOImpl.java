package doaimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connection.DBConnection;
import dao.ReceiptDAO;

public class ReceiptDAOImpl implements ReceiptDAO {
	
	Connection con = DBConnection.getConnection();
	
	public Integer addOrder(int zinger_burger_price, int cheese_burder_price,int mayo_roll_price,
			int reshmi_kabab_price,int pizza_price,int drink,
			int spicy_biryani,int pulao_masala_price,int korma_karai_price,
			int fish_fry_price,int spicy_paratha_price,int tax,
			int delivery_charges,int total) {
		String sql = "insert into receipt (zinger_burger_price,cheese_burger_price,mayo_roll_price,reshmi_kabab_price,pizza_price,drink,spicy_biryani,pulao_masala_price,korma_karai_price,fish_fry_price,spicy_paratha_price,tax,delivery_charges,total) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		PreparedStatement ps = null;
		int row = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, zinger_burger_price);
			ps.setInt(2, cheese_burder_price);
			ps.setInt(3, mayo_roll_price);
			ps.setInt(4, reshmi_kabab_price);
			ps.setInt(5, pizza_price);
			ps.setInt(6, drink);
			ps.setInt(7, spicy_biryani);
			ps.setInt(8, pulao_masala_price);
			ps.setInt(9, korma_karai_price);
			ps.setInt(10, fish_fry_price);
			ps.setInt(11, spicy_paratha_price);
			ps.setInt(12, tax);
			ps.setInt(13, delivery_charges);
			ps.setInt(14, total);
			
			
			
			row = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return row;
	}
}
