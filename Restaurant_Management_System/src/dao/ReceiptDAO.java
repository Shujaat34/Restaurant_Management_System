package dao;

public interface ReceiptDAO {

	// For Inserting the Data
	public Integer addOrder(int zinger_burger_price, int cheese_burder_price,int mayo_roll_price,
			int reshmi_kabab_price,int pizza_price,int drink,
			int spicy_biryani,int pulao_masala_price,int korma_karai_price,
			int fish_fry_price,int spicy_paratha_price,int tax,
			int delivery_charges,int total);
	
	
}
