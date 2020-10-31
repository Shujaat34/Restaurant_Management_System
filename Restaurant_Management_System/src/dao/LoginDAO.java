package dao;

public interface LoginDAO {
	
	public boolean loginSuccess(String username, String pass);
	public int createNewAccount(String username , String pass, String email, String post);
}
