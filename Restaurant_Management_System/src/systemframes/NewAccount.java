package systemframes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.LoginDAO;
import doaimpl.LoginDAOImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewAccount extends JFrame {

	private JPanel contentPane;
	private JTextField txtusername;
	private JTextField txtpass;
	private JTextField txtemail;
	private JComboBox cmbopost;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewAccount frame = new NewAccount();
					frame.setTitle("Sign Up");
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
	public NewAccount() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 739, 520);
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
		lblPassword.setBounds(115, 199, 92, 24);
		contentPane.add(lblPassword);
		
		txtpass = new JTextField();
		txtpass.setFont(new Font("Segoe UI Black", Font.BOLD, 15));
		txtpass.setColumns(10);
		txtpass.setBounds(251, 196, 245, 27);
		contentPane.add(txtpass);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(Color.BLUE);
		lblEmail.setFont(new Font("Segoe UI Black", Font.BOLD, 15));
		lblEmail.setBounds(115, 247, 92, 24);
		contentPane.add(lblEmail);
		
		txtemail = new JTextField();
		txtemail.setFont(new Font("Segoe UI Black", Font.BOLD, 15));
		txtemail.setColumns(10);
		txtemail.setBounds(251, 244, 245, 27);
		contentPane.add(txtemail);
		
		JLabel lblPost = new JLabel("Post");
		lblPost.setForeground(Color.BLUE);
		lblPost.setFont(new Font("Segoe UI Black", Font.BOLD, 15));
		lblPost.setBounds(115, 306, 92, 24);
		contentPane.add(lblPost);
		
		
		JLabel lblUserLogin = new JLabel("New Account");
		lblUserLogin.setForeground(Color.BLUE);
		lblUserLogin.setFont(new Font("Segoe Print", Font.BOLD, 15));
		lblUserLogin.setBounds(21, 90, 124, 24);
		contentPane.add(lblUserLogin);
	
		String post [] = new String[] {"Manager","Employee"};
		cmbopost = new JComboBox(post);
		cmbopost.setFont(new Font("Segoe UI Black", Font.BOLD, 15));
		cmbopost.setBounds(252, 302, 244, 33);
		contentPane.add(cmbopost);
		
		JButton btnCreateAccount = new JButton("Create");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//checking the textfields are empty
				if(txtusername.getText().toString().isEmpty() || txtpass.getText().toString().isEmpty() || txtemail.getText().toString().isEmpty()) {
					JOptionPane.showMessageDialog(NewAccount.this, "Please Fill the Texfields", "New Account", JOptionPane.DEFAULT_OPTION);
					return;
				}
				
				String username = txtusername.getText().toString();
				String pass = txtpass.getText().toString();
				String email = txtemail.getText().toString();
				String post = cmbopost.getSelectedItem().toString();
				
				LoginDAO dao = new LoginDAOImpl();
				int acountcreated = dao.createNewAccount(username, pass, email, post);
				
				if(acountcreated == 1) {
					JOptionPane.showMessageDialog(NewAccount.this, "Account is Created Successfully", "New Account", JOptionPane.DEFAULT_OPTION);
				}
				
			}
		});
		btnCreateAccount.setForeground(Color.BLUE);
		btnCreateAccount.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		btnCreateAccount.setBounds(380, 383, 116, 33);
		contentPane.add(btnCreateAccount);
		
		JButton button = new JButton("<= Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Going Back to Login Screen
				NewAccount newAccount =new NewAccount(); 
				newAccount.setVisible(false);
				
				Login login = new Login(); 
				login.setVisible(true);
				dispose();	
			}
		});
		button.setForeground(Color.BLUE);
		button.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		button.setBounds(74, 383, 116, 33);
		contentPane.add(button);
	}
}
