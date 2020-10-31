package systemframes;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import connection.DBConnection;
import dao.LoginDAO;
import doaimpl.LoginDAOImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	Connection con = DBConnection.getConnection();
	private JPanel contentPane;
	private JTextField txtusername;
	private JLabel lblstatus;
	private JPasswordField txtpass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setTitle("Login");
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 717, 478);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Restaurant Management System");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		lblNewLabel.setBounds(134, 27, 429, 47);
		contentPane.add(lblNewLabel);
		
		txtusername = new JTextField();
		txtusername.setFont(new Font("Segoe UI Black", Font.BOLD, 15));
		txtusername.setBounds(251, 150, 245, 27);
		contentPane.add(txtusername);
		txtusername.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("Segoe UI Black", Font.BOLD, 15));
		lblNewLabel_1.setBounds(115, 153, 92, 24);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.BLUE);
		lblPassword.setFont(new Font("Segoe UI Black", Font.BOLD, 15));
		lblPassword.setBounds(115, 219, 92, 24);
		contentPane.add(lblPassword);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//checking the textfields are empty
				if(txtusername.getText().toString().isEmpty() || txtpass.getText().toString().isEmpty()) {
					JOptionPane.showMessageDialog(Login.this, "Please Fill the Texfields", "Login View", JOptionPane.DEFAULT_OPTION);
					return;
				}
				
				// username and password getting from textfields
				String username = txtusername.getText().toString();
				String pass = txtpass.getText().toString();
				
				LoginDAO dao = new LoginDAOImpl();
				
				boolean loginSuccess = dao.loginSuccess(username, pass);
				
				// Checking the username and password is correct
				if(loginSuccess == true) {
					lblstatus.setText("");
					JOptionPane.showMessageDialog(Login.this, "Successfully Login", "Login Restaurant System", JOptionPane.DEFAULT_OPTION);
					
					// Opening new Window when the username and Password is Correct
					Login login =new Login(); 
					login.setVisible(false);
					
					Menu menu = new Menu();
					menu.setVisible(true);
					dispose();	
				}else {
					lblstatus.setText("Incorrect Username or Passwrod");
				}
			}
		});
		btnNewButton.setBounds(115, 280, 116, 33);
		contentPane.add(btnNewButton);
		
		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Login login =new Login();  
				login.setVisible(false);
				
				NewAccount newAccun = new NewAccount(); 
				newAccun.setVisible(true);
				dispose();
				
			}
		});
		btnCreateAccount.setForeground(Color.BLUE);
		btnCreateAccount.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		btnCreateAccount.setBounds(304, 280, 192, 33);
		contentPane.add(btnCreateAccount);
		
		lblstatus = new JLabel("");
		lblstatus.setFont(new Font("Segoe UI Black", Font.ITALIC, 15));
		lblstatus.setForeground(Color.RED);
		lblstatus.setBounds(292, 356, 267, 27);
		contentPane.add(lblstatus);
		
		JLabel lbldatabase = new JLabel("Connected");
		lbldatabase.setForeground(Color.GREEN);
		lbldatabase.setFont(new Font("Segoe UI Black", Font.ITALIC, 15));
		lbldatabase.setBounds(10, 407, 169, 21);
		contentPane.add(lbldatabase);
		
		JCheckBox showpass = new JCheckBox("Show Password");
		showpass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (showpass.isSelected()) {
				      txtpass.setEchoChar((char)0); //password = JPasswordField
				   } else {
				      txtpass.setEchoChar('*');
				   }
			}
		});
		showpass.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
		showpass.setForeground(Color.BLUE);
		showpass.setBounds(502, 220, 140, 23);
		contentPane.add(showpass);
		
		txtpass = new JPasswordField();
		txtpass.setFont(new Font("Segoe UI Black", Font.BOLD, 15));
		txtpass.setBounds(251, 216, 245, 27);
		contentPane.add(txtpass);
		
		JLabel lblUserLogin = new JLabel("User Login");
		lblUserLogin.setForeground(Color.BLUE);
		lblUserLogin.setFont(new Font("Segoe Print", Font.BOLD, 15));
		lblUserLogin.setBounds(21, 90, 92, 24);
		contentPane.add(lblUserLogin);
		
		if(con != null) {
			lbldatabase.setText("Connected");
		}else {
			lbldatabase.setText("Not Connected");
		}
	}
}
