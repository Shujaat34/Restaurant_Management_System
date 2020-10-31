package systemframes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import javax.swing.event.CaretListener;

import dao.ReceiptDAO;
import doaimpl.ReceiptDAOImpl;

import javax.swing.event.CaretEvent;

public class OrderFrame extends JFrame {
	
	// Prices Portion Used For Calculation of the Amount
	
	// Fast food Prices
	private int zingerburgerprice = 140;
	private int cheeseburgerprice = 130;
	private int mayorollprice = 150;
	private int reshmikababprice = 160;
	private int pizzaMediumprice = 130;
	
	// Drinks
	private int nestleapple = 120;
	private int pepsi = 90;
	private int maranda = 100;
	private int sevenup = 80;
	private int sting = 60;
	private int coffee = 80;
	private int mangojuice = 120;
	private int bananajuice = 90;
	private int chocolatetea = 100;
	
	
	//Desi Food
	private int spicybiryaniprice = 150;
	private int pulaomasalaprice = 120;
	private int kormakaraiprice = 200;
	private int fishfryprice = 180;
	private int spicyparathaprice = 80;
	
	
	// Ordered Quantity of FastFood
	
	private int q_zinger = 0;
	private int q_cheese =0;
	private int q_mayo =0;
	private int q_reshmi =0;
	private int q_pizza =0;
	private int q_drink = 0;
	
	// Ordered Quantity of Desi Food
	
	private int q_spicaybiryani =0;
	private int q_pulaomasala =0;
	private int q_koramkarai =0;
	private int q_fishfry =0;
	private int q_spicyparath =0;
	
	
	
	
	private int grand_total_Food=0; 
	private int grand_toal_drink = 0;
	private int delivery_charges = 0;
	private int tax = 0;
	
	
	private JPanel contentPane;
	private JTextField txtzinger;
	private JTextField txtcheeseburger;
	private JTextField txtmayoroll;
	private JTextField txtreshmi;
	private JTextField txtspicybiryani;
	private JTextField txtpulaomasala;
	private JTextField txtkormakarai;
	private JTextField txtfishfry;
	private JTextField txtspicyparatha;
	private JTextField txtdrinks;
	private JTextField txtpizza;
	private JLabel lbldrinks;
	private JLabel lblFood;
	private JLabel lblDelivery;
	private JCheckBox checkboxzinger;
	private JCheckBox checkboxcheese;
	private JCheckBox checkboxmayo;
	private JCheckBox checkboxreshmi;
	private JCheckBox checkboxpulaomasala;
	private JCheckBox chckbxFishFry;
	private JCheckBox checkboxspicyparath;
	private JCheckBox checkboxkormakarai;
	private JCheckBox checkboxpizza;
	private JComboBox cmbodrinks;
	private JCheckBox checkboxspicybiryani;
	private JCheckBox checkboxtax;
	private JCheckBox checkboxhomdel;
	private JTextArea txtareareceipt;
	
	private JLabel lblTax;
	private JLabel lblsubTotal;
	private JLabel lblTotal;



	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderFrame frame = new OrderFrame();
					frame.setTitle("Order");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OrderFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 961, 681);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Restaurant Management System");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		lblNewLabel.setBounds(314, 11, 409, 45);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(694, 106, 231, 458);
		contentPane.add(scrollPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		scrollPane.setViewportView(tabbedPane);
		
		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("Receipt", null, panel_4, null);
		panel_4.setLayout(null);
		
		txtareareceipt = new JTextArea();
		txtareareceipt.setBounds(10, 11, 204, 406);
		panel_4.add(txtareareceipt);
		
		
		JLabel lblUserLogin = new JLabel("Manager Order");
		lblUserLogin.setForeground(Color.BLUE);
		lblUserLogin.setFont(new Font("Segoe Print", Font.BOLD, 15));
		lblUserLogin.setBounds(21, 75, 150, 24);
		contentPane.add(lblUserLogin);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel.setBounds(48, 106, 305, 313);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblFastFood = new JLabel("Fast Food");
		lblFastFood.setBounds(10, 11, 106, 24);
		panel.add(lblFastFood);
		lblFastFood.setForeground(Color.DARK_GRAY);
		lblFastFood.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		
		checkboxzinger = new JCheckBox("Zinger Burger");
		checkboxzinger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkboxzinger.isSelected()) {
					txtzinger.setEnabled(true);
				}
				else {
					txtzinger.setEnabled(false);
				}
			}
		});
		checkboxzinger.setBounds(20, 42, 123, 23);
		panel.add(checkboxzinger);
		checkboxzinger.setFont(new Font("Segoe UI Historic", Font.BOLD, 14));
		
		
		
		
		txtzinger = new JTextField();
		txtzinger.setBounds(172, 43, 106, 20);
		panel.add(txtzinger);
		txtzinger.setFont(new Font("Segoe UI Historic", Font.BOLD, 14));
		txtzinger.setColumns(10);
		
		checkboxcheese = new JCheckBox("Cheese Burger");
		checkboxcheese.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkboxcheese.isSelected()) {
					txtcheeseburger.setEnabled(true);
				}
				else {
					txtcheeseburger.setEnabled(false);
				}
			}
		});
		checkboxcheese.setFont(new Font("Segoe UI Historic", Font.BOLD, 14));
		checkboxcheese.setBounds(20, 76, 146, 23);
		panel.add(checkboxcheese);
		
		txtcheeseburger = new JTextField();
		txtcheeseburger.setFont(new Font("Segoe UI Historic", Font.BOLD, 14));
		txtcheeseburger.setColumns(10);
		txtcheeseburger.setBounds(172, 77, 106, 20);
		panel.add(txtcheeseburger);
		
		txtmayoroll = new JTextField();
		txtmayoroll.setFont(new Font("Segoe UI Historic", Font.BOLD, 14));
		txtmayoroll.setColumns(10);
		txtmayoroll.setBounds(172, 112, 106, 20);
		panel.add(txtmayoroll);
		
		checkboxmayo = new JCheckBox("Mayo Roll");
		checkboxmayo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkboxmayo.isSelected()) {
					txtmayoroll.setEnabled(true);
				}
				else {
					txtmayoroll.setEnabled(false);
				}
			}
		});
		checkboxmayo.setFont(new Font("Segoe UI Historic", Font.BOLD, 14));
		checkboxmayo.setBounds(20, 111, 146, 23);
		panel.add(checkboxmayo);
		
		checkboxreshmi = new JCheckBox("Reshmi Kabab");
		checkboxreshmi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkboxreshmi.isSelected()) {
					txtreshmi.setEnabled(true);
				}
				else {
					txtreshmi.setEnabled(false);
				}
			}
		});
		checkboxreshmi.setFont(new Font("Segoe UI Historic", Font.BOLD, 14));
		checkboxreshmi.setBounds(20, 150, 146, 23);
		panel.add(checkboxreshmi);
		
		txtreshmi = new JTextField();
		txtreshmi.setFont(new Font("Segoe UI Historic", Font.BOLD, 14));
		txtreshmi.setColumns(10);
		txtreshmi.setBounds(172, 151, 106, 20);
		panel.add(txtreshmi);
		
		txtpizza = new JTextField();
		txtpizza.setFont(new Font("Segoe UI Historic", Font.BOLD, 14));
		txtpizza.setColumns(10);
		txtpizza.setBounds(172, 188, 106, 20);
		panel.add(txtpizza);
		
		checkboxpizza = new JCheckBox("Pizza Medium");
		checkboxpizza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkboxpizza.isSelected()) {
					txtpizza.setEnabled(true);
				}
				else {
					txtpizza.setEnabled(false);
				}
			}
		});
		checkboxpizza.setFont(new Font("Segoe UI Historic", Font.BOLD, 14));
		checkboxpizza.setBounds(20, 187, 146, 23);
		panel.add(checkboxpizza);
		
		cmbodrinks = new JComboBox();
		cmbodrinks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!cmbodrinks.getSelectedItem().toString().equalsIgnoreCase("Select A Drink")) {
					txtdrinks.setEnabled(true);
				}
				else {
					txtdrinks.setEnabled(false);
				}
			}
		});
		cmbodrinks.setFont(new Font("Segoe UI Black", Font.BOLD, 13));
		cmbodrinks.setModel(new DefaultComboBoxModel(new String[] {"Select A Drink", "Nestle Apple Juice", "Pepsi", "Maranda", "7 UP", "Sting", "Coffee", "Mango Juice", "Banana Juice", "Choclate Tea"}));
		cmbodrinks.setBounds(74, 231, 131, 20);
		panel.add(cmbodrinks);
		
		txtdrinks = new JTextField();
		txtdrinks.setFont(new Font("Segoe UI Historic", Font.BOLD, 14));
		txtdrinks.setColumns(10);
		txtdrinks.setBounds(215, 230, 62, 20);
		panel.add(txtdrinks);
		
		checkboxtax = new JCheckBox("Tax");
		checkboxtax.setFont(new Font("Segoe UI Historic", Font.BOLD, 14));
		checkboxtax.setBounds(10, 283, 146, 23);
		panel.add(checkboxtax);
		
		checkboxhomdel = new JCheckBox("Home Delivery");
		checkboxhomdel.setFont(new Font("Segoe UI Historic", Font.BOLD, 14));
		checkboxhomdel.setBounds(158, 285, 146, 23);
		panel.add(checkboxhomdel);
		
		JLabel label = new JLabel("Drinks");
		label.setForeground(Color.BLACK);
		label.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
		label.setBounds(14, 227, 51, 24);
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_1.setBounds(379, 106, 305, 230);
		contentPane.add(panel_1);
		
		JLabel lblDesiFood = new JLabel("Desi Food");
		lblDesiFood.setForeground(Color.DARK_GRAY);
		lblDesiFood.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		lblDesiFood.setBounds(10, 11, 106, 24);
		panel_1.add(lblDesiFood);
		
		checkboxspicybiryani = new JCheckBox("Spicy Biryani");
		checkboxspicybiryani.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkboxspicybiryani.isSelected()) {
					txtspicybiryani.setEnabled(true);
				}
				else {
					txtspicybiryani.setEnabled(false);
				}
			}
		});
		checkboxspicybiryani.setFont(new Font("Segoe UI Historic", Font.BOLD, 14));
		checkboxspicybiryani.setBounds(20, 42, 123, 23);
		panel_1.add(checkboxspicybiryani);
		
		txtspicybiryani = new JTextField();
		txtspicybiryani.setFont(new Font("Segoe UI Historic", Font.BOLD, 14));
		txtspicybiryani.setColumns(10);
		txtspicybiryani.setBounds(172, 43, 106, 20);
		panel_1.add(txtspicybiryani);
		
		checkboxpulaomasala = new JCheckBox("Pulao Masala");
		checkboxpulaomasala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					if(checkboxpulaomasala.isSelected()) {
						txtpulaomasala.setEnabled(true);
					}
					else {
						txtpulaomasala.setEnabled(false);
					}
				}
		});
		checkboxpulaomasala.setFont(new Font("Segoe UI Historic", Font.BOLD, 14));
		checkboxpulaomasala.setBounds(20, 78, 146, 23);
		panel_1.add(checkboxpulaomasala);
		
		txtpulaomasala = new JTextField();
		txtpulaomasala.setFont(new Font("Segoe UI Historic", Font.BOLD, 14));
		txtpulaomasala.setColumns(10);
		txtpulaomasala.setBounds(172, 79, 106, 20);
		panel_1.add(txtpulaomasala);
		
		txtkormakarai = new JTextField();
		txtkormakarai.setFont(new Font("Segoe UI Historic", Font.BOLD, 14));
		txtkormakarai.setColumns(10);
		txtkormakarai.setBounds(172, 112, 106, 20);
		panel_1.add(txtkormakarai);
		
		checkboxkormakarai = new JCheckBox("Korma Karai");
		checkboxkormakarai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkboxkormakarai.isSelected()) {
					txtkormakarai.setEnabled(true);
				}
				else {
					txtkormakarai.setEnabled(false);
				}
			}
		});
		checkboxkormakarai.setFont(new Font("Segoe UI Historic", Font.BOLD, 14));
		checkboxkormakarai.setBounds(20, 111, 146, 23);
		panel_1.add(checkboxkormakarai);
		
		chckbxFishFry = new JCheckBox("Fish Fry");
		chckbxFishFry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxFishFry.isSelected()) {
					txtfishfry.setEnabled(true);
				}
				else {
					txtfishfry.setEnabled(false);
				}
			}
		});
		chckbxFishFry.setFont(new Font("Segoe UI Historic", Font.BOLD, 14));
		chckbxFishFry.setBounds(20, 150, 146, 23);
		panel_1.add(chckbxFishFry);
		
		txtfishfry = new JTextField();
		txtfishfry.setFont(new Font("Segoe UI Historic", Font.BOLD, 14));
		txtfishfry.setColumns(10);
		txtfishfry.setBounds(172, 151, 106, 20);
		panel_1.add(txtfishfry);
		
		txtspicyparatha = new JTextField();
		txtspicyparatha.setFont(new Font("Segoe UI Historic", Font.BOLD, 14));
		txtspicyparatha.setColumns(10);
		txtspicyparatha.setBounds(172, 188, 106, 20);
		panel_1.add(txtspicyparatha);
		
		checkboxspicyparath = new JCheckBox("Spicy Paratha");
		checkboxspicyparath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkboxspicyparath.isSelected()) {
					txtspicyparatha.setEnabled(true);
				}
				else {
					txtspicyparatha.setEnabled(false);
				}
			}
		});
		checkboxspicyparath.setFont(new Font("Segoe UI Historic", Font.BOLD, 14));
		checkboxspicyparath.setBounds(20, 187, 146, 23);
		panel_1.add(checkboxspicyparath);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_2.setBounds(48, 425, 305, 207);
		contentPane.add(panel_2);
		
		JLabel Cost = new JLabel("Cost");
		Cost.setForeground(Color.DARK_GRAY);
		Cost.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		Cost.setBounds(10, 11, 106, 24);
		panel_2.add(Cost);
		
		JLabel lblCostOfDrinks = new JLabel("Drinks");
		lblCostOfDrinks.setForeground(Color.BLACK);
		lblCostOfDrinks.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		lblCostOfDrinks.setBounds(30, 46, 84, 24);
		panel_2.add(lblCostOfDrinks);
		
		lbldrinks = new JLabel("");
		lbldrinks.setForeground(Color.BLACK);
		lbldrinks.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		lbldrinks.setBounds(161, 46, 84, 24);
		panel_2.add(lbldrinks);
		
		lblFood = new JLabel("");
		lblFood.setForeground(Color.BLACK);
		lblFood.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		lblFood.setBounds(161, 81, 84, 24);
		panel_2.add(lblFood);
		
		JLabel lblMeal = new JLabel("Food");
		lblMeal.setForeground(Color.BLACK);
		lblMeal.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		lblMeal.setBounds(30, 80, 84, 24);
		panel_2.add(lblMeal);
		
		lblDelivery = new JLabel("");
		lblDelivery.setForeground(Color.BLACK);
		lblDelivery.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		lblDelivery.setBounds(161, 120, 84, 24);
		panel_2.add(lblDelivery);
		
		JLabel LabelDelivery = new JLabel("Delivery");
		LabelDelivery.setForeground(Color.BLACK);
		LabelDelivery.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		LabelDelivery.setBounds(32, 120, 84, 24);
		panel_2.add(LabelDelivery);
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(!txtzinger.getText().toString().isEmpty() && checkboxzinger.isSelected()) {
					q_zinger = Integer.parseInt(txtzinger.getText().toString());
					grand_total_Food = grand_total_Food + (q_zinger*zingerburgerprice); 
				}
				
				if(!txtcheeseburger.getText().toString().isEmpty() && checkboxcheese.isSelected()) {
					q_cheese = Integer.parseInt(txtcheeseburger.getText().toString());
					grand_total_Food = grand_total_Food + (q_zinger*cheeseburgerprice);  
				}
				
				if(!txtmayoroll.getText().toString().isEmpty() && checkboxmayo.isSelected()) {
					q_mayo = Integer.parseInt(txtmayoroll.getText().toString());
					grand_total_Food = grand_total_Food + (q_mayo*mayorollprice);  
				}
				if(!txtreshmi.getText().toString().isEmpty() && checkboxreshmi.isSelected()) {
					q_reshmi = Integer.parseInt(txtreshmi.getText().toString());
					grand_total_Food = grand_total_Food + (q_reshmi*reshmikababprice);   
				}
				if(!txtpizza.getText().toString().isEmpty() && checkboxpizza.isSelected()) {
					q_pizza = Integer.parseInt(txtpizza.getText().toString());
					grand_total_Food = grand_total_Food + (q_pizza*pizzaMediumprice);    
				}
				if(!txtspicybiryani.getText().toString().isEmpty() && checkboxspicybiryani.isSelected()) {
					q_spicaybiryani = Integer.parseInt(txtspicybiryani.getText().toString());
					grand_total_Food = grand_total_Food + (q_spicaybiryani*spicybiryaniprice);     
				}
				if(!txtpulaomasala.getText().toString().isEmpty() && checkboxpulaomasala.isSelected()) {
					q_pulaomasala = Integer.parseInt(txtpulaomasala.getText().toString());
					grand_total_Food = grand_total_Food + (q_spicaybiryani*pulaomasalaprice);      
				}
				if(!txtkormakarai.getText().toString().isEmpty() && checkboxkormakarai.isSelected()) {
					q_koramkarai = Integer.parseInt(txtkormakarai.getText().toString());
					grand_total_Food = grand_total_Food + (q_koramkarai*kormakaraiprice);      
				}
				if(!txtfishfry.getText().toString().isEmpty() && chckbxFishFry.isSelected()) {
					q_fishfry = Integer.parseInt(txtfishfry.getText().toString());
					grand_total_Food = grand_total_Food + (q_fishfry*fishfryprice);        
				}
				if(!txtspicyparatha.getText().toString().isEmpty() && checkboxspicyparath.isSelected()) {
					q_spicyparath = Integer.parseInt(txtspicyparatha.getText().toString());
					grand_total_Food = grand_total_Food + (q_spicyparath*spicyparathaprice);       
				}
				
				lblFood.setText(Integer.toString(grand_total_Food));
				
				
				//drinks
				
				if(!txtdrinks.getText().toString().isEmpty() && !cmbodrinks.getSelectedItem().toString().equalsIgnoreCase("Select A Drink")) {
					q_drink = Integer.parseInt(txtdrinks.getText().toString());
					grand_toal_drink = grand_toal_drink + (q_drink*80);    
				}
				lbldrinks.setText(Integer.toString(grand_toal_drink));
				
				if(checkboxtax.isSelected()) {
					tax = tax + 8;
				}
				lblTax.setText(Integer.toString(tax));
				
				if(checkboxhomdel.isSelected()) {
					delivery_charges = delivery_charges + 50;
				}
				lblDelivery.setText(Integer.toString(delivery_charges));
			
				lblsubTotal.setText(Integer.toString(grand_total_Food+grand_toal_drink));
				
				lblTotal.setText(Integer.toString(grand_total_Food+grand_toal_drink+tax+delivery_charges));
				
//				grand_total_Food = 0;
//				grand_toal_drink = 0;
//				tax = 0;
//				delivery_charges = 0;
				
//				txtcheeseburger.getText().toString().isEmpty() 
//				txtmayoroll.getText().toString().isEmpty()
//				txtreshmi.getText().toString().isEmpty() 
//				txtpizza.getText().toString().isEmpty()
//				txtdrinks.getText().toString().isEmpty() ||
//				txtspicybiryani.getText().toString().isEmpty() ||
//				txtpulaomasala.getText().toString().isEmpty() ||
//				txtkormakarai.getText().toString().isEmpty() ||
//				txtfishfry.getText().toString().isEmpty() ||
//				txtspicyparatha.getText().toString().isEmpty()
				
			}
		});
		btnCalculate.setForeground(Color.BLUE);
		btnCalculate.setFont(new Font("Segoe UI Black", Font.BOLD, 13));
		btnCalculate.setBounds(171, 162, 109, 34);
		panel_2.add(btnCalculate);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_3.setBounds(379, 357, 305, 207);
		contentPane.add(panel_3);
		
		JLabel lblCostToPay = new JLabel("Cost to Pay");
		lblCostToPay.setForeground(Color.DARK_GRAY);
		lblCostToPay.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		lblCostToPay.setBounds(10, 11, 106, 24);
		panel_3.add(lblCostToPay);
		
		JLabel lblSubtotal = new JLabel("SubTotal");
		lblSubtotal.setForeground(Color.BLACK);
		lblSubtotal.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		lblSubtotal.setBounds(20, 55, 84, 24);
		panel_3.add(lblSubtotal);
		
		lblsubTotal = new JLabel("");
		lblsubTotal.setForeground(Color.BLACK);
		lblsubTotal.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		lblsubTotal.setBounds(161, 55, 84, 24);
		panel_3.add(lblsubTotal);
		
		lblTax = new JLabel("");
		lblTax.setForeground(Color.BLACK);
		lblTax.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		lblTax.setBounds(161, 104, 84, 24);
		panel_3.add(lblTax);
		
		JLabel Labelta = new JLabel("Tax");
		Labelta.setForeground(Color.BLACK);
		Labelta.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		Labelta.setBounds(20, 104, 84, 24);
		panel_3.add(Labelta);
		
		lblTotal = new JLabel("");
		lblTotal.setForeground(Color.BLACK);
		lblTotal.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		lblTotal.setBounds(161, 152, 84, 24);
		panel_3.add(lblTotal);
		
		JLabel LabelTotal = new JLabel("Total");
		LabelTotal.setForeground(Color.BLACK);
		LabelTotal.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		LabelTotal.setBounds(20, 152, 84, 24);
		panel_3.add(LabelTotal);
		
		JButton btnReceipt = new JButton("<=Back");
		btnReceipt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderFrame orderframe =new OrderFrame(); 
				orderframe.setVisible(false);
				
				Menu menu = new Menu();  
				menu.setVisible(true);
				dispose();	
			}
		});
		btnReceipt.setFont(new Font("Segoe UI Black", Font.BOLD, 13));
		btnReceipt.setForeground(Color.BLUE);
		btnReceipt.setBounds(379, 598, 109, 34);
		contentPane.add(btnReceipt);
		
		JButton button = new JButton("Receipt");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String title="Restaurant Management System\n\n";
				
				String Food = "Fast Food\n--------------------------\n";
				String zinger = "Zinger Burger "+(q_zinger*zingerburgerprice)+"\n";
				String chees = "Cheese Burger "+(q_cheese*cheeseburgerprice)+"\n";
				String mayo = "Mayo Roll "+(q_mayo * mayorollprice)+"\n";
				String reshmi ="Reshmi Kabab "+(q_reshmi * reshmikababprice)+"\n";
				String pizza = "Pizza Medium "+(q_pizza * pizzaMediumprice)+"\n";
				String drink = "Drink "+(q_drink * 80)+"\n";
				String desi = "Desi Food\n--------------------------\n";
				String spicybiryani = "Spicy Biryani "+(q_spicaybiryani* spicybiryaniprice)+"\n";
				String pualo = "Pulao Masala "+(q_pulaomasala*pulaomasalaprice)+"\n";
				String korma = "Korma Karai "+(q_koramkarai * kormakaraiprice)+"\n";
				String fish = "Fish Fry "+(q_fishfry*fishfryprice)+"\n";
				String parath = "Spicy Paratha "+(q_spicyparath*spicyparathaprice)+"\n";
				
				String total = "\n--------------------------\nTotal "+(grand_total_Food+grand_toal_drink+tax+delivery_charges)+"\n";
				
				String congr="--------------------------\nThanks for using our Service";
				
				txtareareceipt.setText(title+Food+zinger+chees+mayo+reshmi+pizza+
						drink+desi+spicybiryani+pualo+pualo+korma+fish+parath+total+congr);
			}
		});
		button.setFont(new Font("Segoe UI Black", Font.BOLD, 13));
		button.setForeground(Color.BLUE);
		button.setBounds(802, 598, 123, 34);
		contentPane.add(button);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetAll();
			}
		});
		btnReset.setFont(new Font("Segoe UI Black", Font.BOLD, 13));
		btnReset.setForeground(Color.BLUE);
		btnReset.setBounds(510, 598, 128, 34);
		contentPane.add(btnReset);
		
		JButton btnAddOrder = new JButton("Add Order");
		btnAddOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ReceiptDAO receipt = new ReceiptDAOImpl();
			int addSuccess  =	receipt.addOrder((q_zinger*zingerburgerprice), (q_cheese*cheeseburgerprice), (q_mayo*mayorollprice),
						(q_reshmi * reshmikababprice),
						(q_pizza * pizzaMediumprice), (q_drink * 80), 
						(q_spicaybiryani* spicybiryaniprice),
						(q_pulaomasala*pulaomasalaprice),
						(q_koramkarai * kormakaraiprice), (q_fishfry*fishfryprice), (q_spicyparath*spicyparathaprice), tax,
						delivery_charges, (grand_total_Food+grand_toal_drink+tax+delivery_charges));
			
			if(addSuccess == 1) {
				JOptionPane.showMessageDialog(OrderFrame.this, "Order Added Successfully", "Order", JOptionPane.DEFAULT_OPTION);
			}
			
			}
		});
		btnAddOrder.setForeground(Color.BLUE);
		btnAddOrder.setFont(new Font("Segoe UI Black", Font.BOLD, 13));
		btnAddOrder.setBounds(648, 598, 144, 34);
		contentPane.add(btnAddOrder);
		
		txtzinger.setEnabled(false);
		txtcheeseburger.setEnabled(false);
		txtmayoroll.setEnabled(false);
		txtreshmi.setEnabled(false);
		
		txtpizza.setEnabled(false);
		txtspicybiryani.setEnabled(false);
		txtpulaomasala.setEnabled(false);
		txtkormakarai.setEnabled(false);
		txtfishfry.setEnabled(false);
		txtspicyparatha.setEnabled(false);
		
		
		txtdrinks.setEnabled(false);
	}
	
	
	
	// Checking txt is Emptu or Not
		public boolean txtIsEmpty() {
			if((txtzinger.getText().toString().isEmpty() || 
					txtcheeseburger.getText().toString().isEmpty() ||
					txtmayoroll.getText().toString().isEmpty() || 
					txtreshmi.getText().toString().isEmpty() ||
					txtpizza.getText().toString().isEmpty() ||
					txtdrinks.getText().toString().isEmpty() ||
					txtspicybiryani.getText().toString().isEmpty() ||
					txtpulaomasala.getText().toString().isEmpty() ||
					txtkormakarai.getText().toString().isEmpty() ||
					txtfishfry.getText().toString().isEmpty() ||
					txtspicyparatha.getText().toString().isEmpty()
					)) {
				return true;
			}
			return false;
		}
		
		public boolean txtisNumber() {
			
			
			String zingers = txtzinger.getText().toString();  
			String chesseburgers = txtcheeseburger.getText().toString();
			String mayorolls = txtmayoroll.getText().toString();
			String reshmikababs = txtreshmi.getText().toString();
			String pizzas = txtpizza.getText().toString();
			String drinks = txtdrinks.getText().toString();
			String spicybiryani = txtspicybiryani.getText().toString();
			String pulaomasala = txtpulaomasala.getText().toString();
			String koramkarai = txtkormakarai.getText().toString();
			String fishfry = txtfishfry.getText().toString();
			String spicyparath = txtspicyparatha.getText().toString();
			
			boolean zingercheck = Pattern.matches("[0-9]{1,20}",zingers);
			boolean cheeseburgercheck = Pattern.matches("[0-9]{1,20}",chesseburgers);
			boolean mayorollceheck = Pattern.matches("[0-9]{1,20}",mayorolls);
			boolean reshmikabacheck = Pattern.matches("[0-9]{1,20}",reshmikababs);
			boolean pizzacheck = Pattern.matches("[0-9]{1,20}",pizzas);
			boolean drinkscheck = Pattern.matches("[0-9]{1,20}",drinks);
			boolean spicybirynaicheck = Pattern.matches("[0-9]{1,20}",spicybiryani);
			boolean pualomasalacheck = Pattern.matches("[0-9]{1,20}",pulaomasala);
			boolean kormakaraicheck = Pattern.matches("[0-9]{1,20}",koramkarai);
			boolean paycheck = Pattern.matches("[0-9]{1,20}",fishfry);
			boolean nhifcheck = Pattern.matches("[0-9]{1,20}",spicyparath);
			
			if(zingercheck && cheeseburgercheck && mayorollceheck && reshmikabacheck && pizzacheck && drinkscheck
					&& spicybirynaicheck && pualomasalacheck && kormakaraicheck && paycheck &&nhifcheck ) {
				return true;
			}
			else {
				return false;
			}
		}
		
		
		public void resetAll() {
			checkboxzinger.setSelected(false);
			checkboxcheese.setSelected(false);
			checkboxmayo.setSelected(false);
			checkboxreshmi.setSelected(false);
			checkboxpizza.setSelected(false);
			checkboxtax.setSelected(false);
			checkboxhomdel.setSelected(false);
			
			txtzinger.setText("");
			txtcheeseburger.setText("");
			txtmayoroll.setText("");
			txtreshmi.setText("");
			txtpizza.setText("");
			txtdrinks.setText("");
			
			
			lblDelivery.setText("");
			lblFood.setText("");
			lbldrinks.setText("");
			
			checkboxspicybiryani.setSelected(false);
			checkboxpulaomasala.setSelected(false);
			checkboxkormakarai.setSelected(false);
			chckbxFishFry.setSelected(false);
			checkboxspicyparath.setSelected(false);
		
			txtspicybiryani.setText("");
			txtpulaomasala.setText("");
			txtkormakarai.setText("");
			txtfishfry.setText("");
			txtspicyparatha.setText("");
			
			lblsubTotal.setText("");
			lblTax.setText("");
			lblTotal.setText("");
			
			txtareareceipt.setText("");
			
		
		}
		
}
		