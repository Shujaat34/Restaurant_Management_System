package systemframes;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.CustomerDAO;
import doaimpl.CustomerDAOImpl;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnAdd;
	private JTextField txtcustomer;
	private JTextField txtage;
	private JTextField txtreceiptID;
	private JButton btnClear;
	private JLabel lblAge;
	private JLabel lblReceipt;
	private JScrollPane scrollPane;
	private JButton btnDelete;
	private JButton button;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerFrame frame = new CustomerFrame();
					frame.setTitle("Customer");
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
	public CustomerFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 844, 490);
		contentPane = new JPanel();
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnDelete.setEnabled(false);
				btnAdd.setEnabled(true);
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Restaurant Management System");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 24));
		lblNewLabel.setBounds(206, 27, 429, 47);
		contentPane.add(lblNewLabel);
		
		
		JLabel lblUserLogin = new JLabel("Customer");
		lblUserLogin.setForeground(Color.BLUE);
		lblUserLogin.setFont(new Font("Segoe Print", Font.BOLD, 15));
		lblUserLogin.setBounds(21, 90, 92, 24);
		contentPane.add(lblUserLogin);
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(359, 144, 446, 275);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnAdd.setEnabled(false);
				btnDelete.setEnabled(true); 
			}
		});
		scrollPane.setViewportView(table);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
					CustomerDAO dao = new CustomerDAOImpl();
					//	checking the Textfields are empty
					
					int row = dao.deleteCustomer(id);
					if(row ==1) {
						JOptionPane.showMessageDialog(CustomerFrame.this, "Customer Deleted Successfully", "Customer Delete", JOptionPane.DEFAULT_OPTION);
					}
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(CustomerFrame.this, "Please Select a Row", "Customer Delete", JOptionPane.DEFAULT_OPTION);
					}
					clear();

					populateTable();
					
				}
				
		
		});
		btnDelete.setForeground(Color.BLUE);
		btnDelete.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		btnDelete.setBounds(689, 97, 116, 33);
		contentPane.add(btnDelete);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerDAO dao = new CustomerDAOImpl();
			//	checking the Textfieds are empty
				if(!(txtcustomer.getText().toString().isEmpty() || txtage.getText().toString().isEmpty() || 
						txtreceiptID.getText().toString().isEmpty())) {
					String customer = txtcustomer.getText().toString();
					String age = txtage.getText().toString();
					String receiptID = txtreceiptID.getText().toString();
					
					// checking the inut for age is number
					if(Pattern.matches("[0-9]{1,20}",age) == false) {
						JOptionPane.showMessageDialog(CustomerFrame.this, "Please Enter Number For Age", "Age", JOptionPane.DEFAULT_OPTION);
						return;
					}
					// checking the inut for age is number
					if(Pattern.matches("[0-9]{1,20}",receiptID) == false) {
						JOptionPane.showMessageDialog(CustomerFrame.this, "Please Enter Number For Receipt id", "Receipt", JOptionPane.DEFAULT_OPTION);
						return;
					}
					
					int addSuccess =	dao.addCustomer(customer, age, receiptID);
				
					if(addSuccess == 1) {
						JOptionPane.showMessageDialog(CustomerFrame.this, "Customer Added Successfully", "Customer", JOptionPane.DEFAULT_OPTION);
						
					}
					populateTable();
				}else {
					JOptionPane.showMessageDialog(CustomerFrame.this, "Please Fill All the TextFeilds", "Customer", JOptionPane.DEFAULT_OPTION);
				}
				
				
			}
		});
		btnAdd.setForeground(Color.BLUE);
		btnAdd.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		btnAdd.setBounds(359, 97, 116, 33);
		contentPane.add(btnAdd);
		
		txtcustomer = new JTextField("");
		txtcustomer.setFont(new Font("Segoe UI Historic", Font.BOLD, 15));
		txtcustomer.setBounds(144, 136, 172, 27);
		contentPane.add(txtcustomer);
		txtcustomer.setColumns(10);
		
		txtage = new JTextField("");
		txtage.setFont(new Font("Segoe UI Historic", Font.BOLD, 15));
		txtage.setColumns(10);
		txtage.setBounds(144, 187, 172, 27);
		contentPane.add(txtage);
		
		txtreceiptID = new JTextField("");
		txtreceiptID.setFont(new Font("Segoe UI Historic", Font.BOLD, 15));
		txtreceiptID.setColumns(10);
		txtreceiptID.setBounds(144, 241, 172, 27);
		contentPane.add(txtreceiptID);
		
		btnClear = new JButton("clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		btnClear.setForeground(Color.BLUE);
		btnClear.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		btnClear.setBounds(200, 292, 116, 33);
		contentPane.add(btnClear);
		
		
		JLabel lblcustomer = new JLabel("Customer"); 
		lblcustomer.setForeground(Color.BLUE);
		lblcustomer.setFont(new Font("Segoe UI Black", Font.BOLD, 15));
		lblcustomer.setBounds(25, 140, 84, 27);
		contentPane.add(lblcustomer);
		
		lblAge = new JLabel("Age");
		lblAge.setForeground(Color.BLUE);
		lblAge.setFont(new Font("Segoe UI Black", Font.BOLD, 15));
		lblAge.setBounds(25, 187, 84, 27);
		contentPane.add(lblAge);
		
		lblReceipt = new JLabel("Receipt No");
		lblReceipt.setForeground(Color.BLUE);
		lblReceipt.setFont(new Font("Segoe UI Black", Font.BOLD, 15));
		lblReceipt.setBounds(25, 241, 97, 27);
		contentPane.add(lblReceipt);
		
		populateTable();
		btnDelete.setEnabled(false);
		
		button = new JButton("<= Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerFrame customer =new CustomerFrame();  
				customer.setVisible(false);
				
				Menu menu = new Menu();   
				menu.setVisible(true);
				dispose();
			}
		});
		button.setForeground(Color.BLUE);
		button.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		button.setBounds(23, 395, 116, 33);
		contentPane.add(button);
		
	}
	
	
	private void populateTable() {
		CustomerDAO cdao = new	CustomerDAOImpl();
		ResultSet rs = cdao.getDataResultSet();
		table.setModel(buildTableModel(rs));
	}
	// For Data Population
	public static DefaultTableModel buildTableModel(ResultSet rs) {
		Vector <String> colNames=null;
		Vector <Vector<Object>> data = null;
		try {
			ResultSetMetaData metadata = rs.getMetaData();
			colNames = new Vector<String>();
			int colcount = metadata.getColumnCount();
			
			for(int col=1;col<=colcount;col++) {
				colNames.add(metadata.getColumnName(col));
			}
			data = new Vector <Vector<Object>>();
			
			while(rs.next()) {
				Vector<Object>vector = new Vector();
				for(int colindex = 1; colindex<=colcount;colindex++) {
					vector.add( rs.getObject(colindex));
				}
				data.add(vector);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new DefaultTableModel(data,colNames);
	}
	
	public void clear() {
		txtcustomer.setText("");
		txtage.setText("");
		txtreceiptID.setText("");
	}
}
