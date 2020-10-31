package systemframes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setTitle("Menu");
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
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 965, 506);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLogout = new JButton("<= Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Menu menu =new Menu();    
				menu.setVisible(false);
				
				Login login = new Login();  
				login.setVisible(true);
				dispose();
			}
		});
		btnLogout.setForeground(Color.BLUE);
		btnLogout.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		btnLogout.setBounds(10, 412, 116, 33);
		contentPane.add(btnLogout);
		
		JLabel lblNewLabel = new JLabel("Restaurant Management System");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		lblNewLabel.setBounds(246, 26, 429, 47);
		contentPane.add(lblNewLabel);
		
		JLabel lblUserLogin = new JLabel("Menu");
		lblUserLogin.setForeground(Color.BLUE);
		lblUserLogin.setFont(new Font("Segoe Print", Font.BOLD, 18));
		lblUserLogin.setBounds(21, 90, 84, 47);
		contentPane.add(lblUserLogin);
		
		JButton btnCreateAccount = new JButton("Manage Order");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Opening the OrderFrame Window
				Menu menu =new Menu();  
				menu.setVisible(false);
				
				OrderFrame orderManage = new OrderFrame();  
				orderManage.setVisible(true);
				dispose();
				
			}
		});
		btnCreateAccount.setForeground(Color.BLUE);
		btnCreateAccount.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		btnCreateAccount.setBounds(105, 208, 192, 79);
		contentPane.add(btnCreateAccount);
		
		JButton btnOrder = new JButton("Manage Customer");
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Opening the OrderFrame Window
				Menu menu =new Menu();  
				menu.setVisible(false);
				
				CustomerFrame customerframe = new CustomerFrame();   
				customerframe.setVisible(true);
				dispose();
			}
		});
		btnOrder.setForeground(Color.BLUE);
		btnOrder.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		btnOrder.setBounds(323, 208, 243, 79);
		contentPane.add(btnOrder);
		
		JButton btnReceipt = new JButton("Manage Waiter");
		btnReceipt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Opening the OrderFrame Window
				Menu menu =new Menu();  
				menu.setVisible(false);
				
				WaiterFrame waiterFrame = new WaiterFrame();   
				waiterFrame.setVisible(true);
				dispose();
			}
		});
		btnReceipt.setForeground(Color.BLUE);
		btnReceipt.setFont(new Font("Segoe UI Black", Font.BOLD, 18)); 
		btnReceipt.setBounds(589, 208, 243, 79);
		contentPane.add(btnReceipt);
		
		JButton btnManageCook = new JButton("Manage Cook");
		btnManageCook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu menu =new Menu();  
				menu.setVisible(false);
				
				CookFrame cookFrame = new CookFrame();    
				cookFrame.setVisible(true);
				dispose();
			}
		});
		btnManageCook.setForeground(Color.BLUE);
		btnManageCook.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		btnManageCook.setBounds(196, 310, 243, 79);
		contentPane.add(btnManageCook);
		
		JButton btnManageGuard = new JButton("Manage Food");
		btnManageGuard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu menu =new Menu();  
				menu.setVisible(false);
				
				FoodFrame foodframe = new FoodFrame();      
				foodframe.setVisible(true);
				dispose();
			}
		});
		btnManageGuard.setForeground(Color.BLUE);
		btnManageGuard.setFont(new Font("Segoe UI Black", Font.BOLD, 18));
		btnManageGuard.setBounds(469, 310, 243, 79);
		contentPane.add(btnManageGuard);

	}
}
