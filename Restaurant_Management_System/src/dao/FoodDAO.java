package dao;

import java.sql.ResultSet;

public interface FoodDAO {
	public ResultSet getDataResultSet();
	public Integer addFood(String foodName, String price, String foodsize,String categoryID);
	public Integer deleteFood(Integer id) ;

	public Integer getCategoryIdByName(String category);
}
