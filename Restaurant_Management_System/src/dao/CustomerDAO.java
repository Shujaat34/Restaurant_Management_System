package dao;

import java.sql.ResultSet;

public interface CustomerDAO {

	// For populating the Data into the table
	public ResultSet getDataResultSet();
	public Integer addCustomer(String customer, String age, String receiptID);
	public Integer deleteCustomer(Integer id) ;
}
