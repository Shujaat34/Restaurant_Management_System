package dao;

import java.sql.ResultSet;

public interface CookDAO {

	public ResultSet getDataResultSet();
	public Integer addCook(String name, String cookSal, String cookShift);
	public Integer deleteCook(Integer id) ;
}
