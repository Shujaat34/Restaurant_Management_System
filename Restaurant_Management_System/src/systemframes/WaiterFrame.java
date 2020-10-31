package systemframes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.CustomerDAO;
import dao.WaiterDAO;
import doaimpl.CustomerDAOImpl;
import doaimpl.WaiterDAOImpl;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WaiterFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnDelete;
	private JButton btnAdd;
	private JScrollPane scrollPane;
	private JLabel waiter_name; 
	private JButton btnClear;
	private JTextField txtWaiter;
	private JLabel lblSalary;
	private JTextField txtSalary;
	private JLabel lblShift;
	private JComboBox cmboshift;
	private JButton button;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WaiterFrame frame = new WaiterFrame();
					frame.setTitle("Waiter");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public WaiterFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 824, 460);
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
		lblNewLabel.setBounds(213, 11, 431, 47);
		contentPane.add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(354, 131, 431, 259);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnDelete.setEnabled(true);
				btnAdd.setEnabled(false);
				
			}
		});
		scrollPane.setViewportView(table);
		
		
		waiter_name = new JLabel("Name");
		waiter_name.setForeground(Color.BLUE);
		waiter_name.setFont(new Font("Segoe UI Black", Font.BOLD, 15));
		waiter_name.setBounds(21, 137, 84, 27);
		contentPane.add(waiter_name);
		
		
		JLabel lblUserLogin = new JLabel("Waiter");
		lblUserLogin.setForeground(Color.BLUE);
		lblUserLogin.setFont(new Font("Segoe Print", Font.BOLD, 15));
		lblUserLogin.setBounds(21, 90, 92, 24);
		contentPane.add(lblUserLogin);
		
		txtWaiter = new JTextField("");
		txtWaiter.setFont(new Font("Segoe UI Historic", Font.BOLD, 15));
		txtWaiter.setBounds(144, 136, 172, 27);
		contentPane.add(txtWaiter);
		txtWaiter.setColumns(10);
		
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Integer id = (Integer) table.getValueAt(table.getSelectedRow(), 0);
					WaiterDAO dao = new WaiterDAOImpl();
					//	checking the Textfields are empty
					
					int row = dao.deleteWaiter(id);
					if(row ==1) {
						JOptionPane.showMessageDialog(WaiterFrame.this, "Waiter Deleted Successfully", "Waiter Delete", JOptionPane.DEFAULT_OPTION);
					}
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(WaiterFrame.this, "Please Select a Row", "Waiter Delete", JOptionPane.DEFAULT_OPTION);
					}
					clear();

					populateTable();
					
				}
				
		
		});
		btnDelete.setForeground(Color.BLUE);
		btnDelete.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		btnDelete.setBounds(669, 86, 116, 33);
		contentPane.add(btnDelete);
		
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
		
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WaiterDAO dao = new WaiterDAOImpl();
			//	checking the Textfieds are empty
				if(!(txtWaiter.getText().toString().isEmpty() || txtSalary.getText().toString().isEmpty())) {
					
					String waiterName = txtWaiter.getText().toString();
					String waiterSalary = txtSalary.getText().toString(); 
					String shift = cmboshift.getSelectedItem().toString(); 
					
					// checking the inut for age is number
					if(Pattern.matches("[0-9]{1,20}",waiterSalary) == false) {
						JOptionPane.showMessageDialog(WaiterFrame.this, "Please Enter Number For Salary", "Waiter", JOptionPane.DEFAULT_OPTION);
						return;
					}
					int addSuccess =	dao.addWaiter(waiterName, waiterSalary, shift);
				
					if(addSuccess == 1) {
						JOptionPane.showMessageDialog(WaiterFrame.this, "Waiter Added Successfully", "Waiter", JOptionPane.DEFAULT_OPTION);
					}
					populateTable();
				}else {
					JOptionPane.showMessageDialog(WaiterFrame.this, "Please Fill All the TextFeilds", "Waiter", JOptionPane.DEFAULT_OPTION);
				}
				
				
			}
		});
		btnAdd.setForeground(Color.BLUE);
		btnAdd.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		btnAdd.setBounds(354, 87, 116, 33);
		contentPane.add(btnAdd);
		
		lblSalary = new JLabel("Salary");
		lblSalary.setForeground(Color.BLUE);
		lblSalary.setFont(new Font("Segoe UI Black", Font.BOLD, 15));
		lblSalary.setBounds(21, 189, 84, 27);
		contentPane.add(lblSalary);
		
		txtSalary = new JTextField("");
		txtSalary.setFont(new Font("Segoe UI Historic", Font.BOLD, 15));
		txtSalary.setColumns(10);
		txtSalary.setBounds(144, 188, 172, 27);
		contentPane.add(txtSalary);
		
		lblShift = new JLabel("Shift");
		lblShift.setForeground(Color.BLUE);
		lblShift.setFont(new Font("Segoe UI Black", Font.BOLD, 15));
		lblShift.setBounds(21, 239, 84, 27);
		contentPane.add(lblShift);
		
		cmboshift = new JComboBox();
		cmboshift.setModel(new DefaultComboBoxModel(new String[] {"Day", "Night"}));
		cmboshift.setFont(new Font("Segoe UI Historic", Font.BOLD, 15));
		cmboshift.setBounds(144, 244, 172, 27);
		contentPane.add(cmboshift);
		
		
		populateTable();
		btnDelete.setEnabled(false);
		
		button = new JButton("<= Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Opening the OrderFrame Window 
				WaiterFrame wiaterframe = new WaiterFrame();  
				wiaterframe.setVisible(false);
				
				Menu menu =new Menu();  
				menu.setVisible(true);
				dispose();
			}
		});
		button.setForeground(Color.BLUE);
		button.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		button.setBounds(10, 377, 116, 33);
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
		txtWaiter.setText("");
		txtSalary.setText("");
	}

}
