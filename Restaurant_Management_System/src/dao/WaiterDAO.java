package dao;

import java.sql.ResultSet;

public interface WaiterDAO {
	
	// For populating the Data into the table
	public ResultSet getDataResultSet();
	public Integer addWaiter(String waitername, String waitersal, String waitershift);
	public Integer deleteWaiter(Integer id) ;

}
