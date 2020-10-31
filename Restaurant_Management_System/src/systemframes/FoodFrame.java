package systemframes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.FoodDAO;
import doaimpl.FoodDAOImpl;

public class FoodFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnAdd;
	private JTextField txtfood;
	private JTextField txtprice;
	private JButton btnClear;
	private JLabel lblAge;
	private JLabel lblReceipt;
	private JScrollPane scrollPane;
	private JButton btnDelete;
	private JButton button;
	private JLabel lblFoodCategory;
	
	private JComboBox cmboFoodSize;
	private JComboBox cmboCategory;
	


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FoodFrame frame = new FoodFrame();
					frame.setTitle("Food");
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
	public FoodFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 844, 486);
		contentPane = new JPanel();
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnAdd.setEnabled(true);
				btnDelete.setEnabled(false);
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
		
		
		JLabel lblUserLogin = new JLabel("Food");
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
					FoodDAO dao = new FoodDAOImpl(); 
					//	checking the Textfields are empty
					
					int row = dao.deleteFood(id);
					if(row ==1) {
						JOptionPane.showMessageDialog(FoodFrame.this, "Food Deleted Successfully", "Food Delete", JOptionPane.DEFAULT_OPTION);
					}
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(FoodFrame.this, "Please Select a Row", "Food Delete", JOptionPane.DEFAULT_OPTION);
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
				FoodDAO dao = new FoodDAOImpl();
			//	checking the Textfieds are empty
				if(!(txtfood.getText().toString().isEmpty() || txtprice.getText().toString().isEmpty())) {
					String foodName = txtfood.getText().toString(); 
					String price = txtprice.getText().toString();
					String foodsize = cmboFoodSize.getSelectedItem().toString();
					
					String categoryName = cmboCategory.getSelectedItem().toString();
					
					int cateID = dao.getCategoryIdByName(categoryName);
				
					String categoryID = Integer.toString(cateID);
					// checking the inut for age is number
					if(Pattern.matches("[0-9]{1,20}",price) == false) {
						JOptionPane.showMessageDialog(FoodFrame.this, "Please Enter Number For Price", "Food", JOptionPane.DEFAULT_OPTION);
						return;
					}					
					int addSuccess =	dao.addFood(foodName, price, foodsize, categoryID);
				
					if(addSuccess == 1) {
						JOptionPane.showMessageDialog(FoodFrame.this, "Food Added Successfully", "Food", JOptionPane.DEFAULT_OPTION);
					}
					populateTable();
				}else {
					JOptionPane.showMessageDialog(FoodFrame.this, "Please Fill All the TextFeilds", "Food", JOptionPane.DEFAULT_OPTION);
				}
				populateTable();
				
			}
		});
		btnAdd.setForeground(Color.BLUE);
		btnAdd.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		btnAdd.setBounds(359, 97, 116, 33);
		contentPane.add(btnAdd);
		
		txtfood = new JTextField("");
		txtfood.setFont(new Font("Segoe UI Historic", Font.BOLD, 15));
		txtfood.setBounds(171, 136, 158, 27);
		contentPane.add(txtfood);
		txtfood.setColumns(10);
		
		txtprice = new JTextField("");
		txtprice.setFont(new Font("Segoe UI Historic", Font.BOLD, 15));
		txtprice.setColumns(10);
		txtprice.setBounds(171, 186, 158, 27);
		contentPane.add(txtprice);
		
		btnClear = new JButton("clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		btnClear.setForeground(Color.BLUE);
		btnClear.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		btnClear.setBounds(200, 386, 116, 33);
		contentPane.add(btnClear);
		
		
		JLabel lblcustomer = new JLabel("Food"); 
		lblcustomer.setForeground(Color.BLUE);
		lblcustomer.setFont(new Font("Segoe UI Black", Font.BOLD, 15));
		lblcustomer.setBounds(25, 140, 84, 27);
		contentPane.add(lblcustomer);
		
		lblAge = new JLabel("Price");
		lblAge.setForeground(Color.BLUE);
		lblAge.setFont(new Font("Segoe UI Black", Font.BOLD, 15));
		lblAge.setBounds(25, 187, 84, 27);
		contentPane.add(lblAge);
		
		lblReceipt = new JLabel("Food Size");
		lblReceipt.setForeground(Color.BLUE);
		lblReceipt.setFont(new Font("Segoe UI Black", Font.BOLD, 15));
		lblReceipt.setBounds(25, 241, 97, 27);
		contentPane.add(lblReceipt);
		
		populateTable();
		btnDelete.setEnabled(false);
		
		button = new JButton("<= Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FoodFrame food =new FoodFrame();   
				food.setVisible(false);
				
				Menu menu = new Menu();   
				menu.setVisible(true);
				dispose();
			}
		});
		button.setForeground(Color.BLUE);
		button.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		button.setBounds(21, 386, 116, 33);
		contentPane.add(button);
		
		lblFoodCategory = new JLabel("Food Category");
		lblFoodCategory.setForeground(Color.BLUE);
		lblFoodCategory.setFont(new Font("Segoe UI Black", Font.BOLD, 15));
		lblFoodCategory.setBounds(25, 292, 144, 27);
		contentPane.add(lblFoodCategory);
		
		cmboFoodSize = new JComboBox();
		cmboFoodSize.setModel(new DefaultComboBoxModel(new String[] {"Small", "Medium", "Large"}));
		cmboFoodSize.setFont(new Font("Segoe UI Historic", Font.BOLD, 15));
		cmboFoodSize.setBounds(171, 240, 158, 27);
		contentPane.add(cmboFoodSize);
		
		cmboCategory = new JComboBox();
		cmboCategory.setModel(new DefaultComboBoxModel(new String[] {"Fast Food", "Desi Food", "Cold Drinks"}));
		cmboCategory.setFont(new Font("Segoe UI Historic", Font.BOLD, 15));
		cmboCategory.setBounds(171, 291, 158, 27);
		contentPane.add(cmboCategory);
		
		btnDelete.setEnabled(false);
		populateTable();
	}
	
	
	private void populateTable() {
		FoodDAO cdao = new	FoodDAOImpl();
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
		txtfood.setText("");
		txtprice.setText("");
	}

	}
