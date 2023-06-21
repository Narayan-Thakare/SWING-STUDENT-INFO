package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Studentinfo {

	

	public static	void sho() throws SQLException {

		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Studentt", "root", "abc123");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Create a statement to execute SQL queries
		Statement statement = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Execute a SELECT query to get data from the database
		String sql = "SELECT * FROM stu";
		ResultSet resultSet = null;
		try {
			resultSet = statement.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Create a JFrame to display the data
		JFrame f = new JFrame("SHOW DATA");
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Create a JTable to display the data
		JTable table = new JTable(buildTableModel(resultSet));
		table.setFont(new Font("algerian", Font.BOLD, 14));

		// table.setBounds(100, 20, 190, 40);

		// Add the JTable to the JFrame
		f.add(new JScrollPane(table));

		// Display the JFrame
		f.pack();
		f.setBounds(00, 870, 400, 160);

		f.setVisible(true);
	}

	private static DefaultTableModel buildTableModel(ResultSet resultSet) throws SQLException {
		// Create a table model to hold the data
		DefaultTableModel tableModel = new DefaultTableModel();
		// Get the column names from the database
		ResultSetMetaData metaData = resultSet.getMetaData();
		for (int i = 1; i <= metaData.getColumnCount(); i++) {
			tableModel.addColumn(metaData.getColumnName(i));

		}
		// Get the data from the database
		while (resultSet.next()) {
			// Create a new row in the table model
			Object[] row = new Object[tableModel.getColumnCount()];
			for (int i = 0; i < row.length; i++) {
				row[i] = resultSet.getObject(i + 1);
			}
			tableModel.addRow(row);
		}
		return tableModel;

	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
       

		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver registered");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Studentt", "root", "abc123");
		Statement st = con.createStatement();
		SwingUtilities.invokeLater(() -> {

		
		
        // Create a custom JPanel with background image
       


		JFrame f = new JFrame("STUDENT INFO");
	//	f.setSize(400, 1050);

	
		
		
		
		 JPanel panel = new JPanel() {
             @Override
             protected void paintComponent(Graphics g) {
                 super.paintComponent(g);
                 Image image = new ImageIcon("C:\\Users\\ASUS\\Downloads\\for elcsipse.jpg").getImage();
                 g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
             }
         };

         // Set the layout manager for the panel
         panel.setLayout(null);
		

		JLabel ins = new JLabel(" INSERT  DATA");
		ins.setFont(new Font("Algerian", Font.BOLD, 20));
        ins.setForeground(Color.black); // Set the font color
		ins.setBounds(100, 20, 190, 40);

		JTextField tx = new JTextField();
		tx.setFont(new Font("Algerian", Font.BOLD, 17));
		tx.setBounds(130, 50, 100, 40);

		JLabel lb = new JLabel("ROLL NO. :");
		lb.setFont(new Font("Algerian", Font.BOLD, 17));
		lb.setBounds(40, 50, 120, 40);

		JTextField tx1 = new JTextField();
		tx1.setFont(new Font("Algerian", Font.BOLD, 17));
		tx1.setBounds(130, 100, 100, 40);

		JLabel lb1 = new JLabel("NAME :");
		lb1.setFont(new Font("Algerian", Font.BOLD, 17));
		lb1.setBounds(40, 100, 120, 40);

		JTextField tx2 = new JTextField();
		tx2.setFont(new Font("Algerian", Font.BOLD, 17));
		tx2.setBounds(130, 150, 100, 40);

		JLabel lb2 = new JLabel("ADDRESS :");
		lb2.setFont(new Font("Algerian", Font.BOLD, 17));
		lb2.setBounds(40, 150, 120, 40);

		JButton b = new JButton("INSERT");
		b.setFont(new Font("Algerian", Font.BOLD, 18));
		b.setBounds(50, 200, 200, 40);

		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				int num = Integer.parseInt(tx.getText());
				String name = tx1.getText();
				String address = tx2.getText();
				int a;
				try {
					a = st.executeUpdate("INSERT INTO stu VALUES(" + num + ",'" + name + "','" + address + "')");

					if (a > 0) {
						System.out.println("Data inserted");
						JOptionPane.showMessageDialog(b, "DATA INSERTED");

					} else {
						System.out.println("data not inserted");
						JOptionPane.showMessageDialog(b, "DATA NOT INSERTED");

					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(b, "DATA not INSERTED");
				}
				tx.setText("");
				tx1.setText("");
				tx2.setText("");

			}
		});

		JLabel sho = new JLabel("SHOW DATA");
		sho.setFont(new Font("Algerian", Font.BOLD, 20));
		sho.setBounds(100, 250, 120, 40);

		JButton sh = new JButton("SHOW");
		sh.setFont(new Font("Algerian", Font.BOLD, 18));
		sh.setBounds(50, 280, 200, 40);
		sh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ResultSet rs = null;
				try {
					rs = st.executeQuery("SELECT * FROM stu");
					while (rs.next()) {

						System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));

					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					sho();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			private TableModel buildTableModel(ResultSet rs) {
				// TODO Auto-generated method stub
				return null;
			}
		});

		// String data[][]={ {"101","Amit","670000"},
		// {"102","Jai","780000"},
		// {"101","Sachin","700000"}};
		// String column[]={"ID","NAME","SALARY"};
		// JTable jt=new JTable(data,column);
		// jt.setBounds(30,300,200,300);
		// JScrollPane sp=new JScrollPane(jt);

		// f.add(jt);
		// f.add(sp);
		JLabel sear = new JLabel("SEARCH DATA");
		sear.setFont(new Font("Algerian", Font.BOLD, 20));
		sear.setBounds(100, 330, 150, 40);

		JTextField tx3 = new JTextField();
		tx3.setFont(new Font("Algerian", Font.BOLD, 17));
		tx3.setBounds(130, 360, 100, 40);

		JLabel lb3 = new JLabel("ROLL NO. :");
		lb3.setFont(new Font("Algerian", Font.BOLD, 17));
		lb3.setBounds(50, 360, 120, 40);

		JButton search = new JButton("SEARCH");
		search.setFont(new Font("Algerian", Font.BOLD, 17));
		search.setBounds(50, 410, 200, 40);

		JLabel lb4 = new JLabel();
		lb4.setFont(new Font("Algerian", Font.BOLD, 17));
		lb4.setBounds(60, 450, 300, 40);

		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					boolean found = false;
					int num1 = Integer.parseInt(tx3.getText());
					ResultSet rs = st.executeQuery("SELECT * FROM stu WHERE id = " + num1 + "");
					while (rs.next()) {
						System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
						lb4.setText("DATA:" + (rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3)));
						JOptionPane.showMessageDialog(search, "DATA FOUND");

						found = true;

					}
					if (!found) {
						JOptionPane.showMessageDialog(search, "DATA NOT FOUND");
					}

				} catch (Exception p) {
					System.out.println("Enter the no. :" + p);

				}
				tx3.setText("");

			}
		});

		JLabel del = new JLabel("DELETE DATA");
		del.setFont(new Font("Algerian", Font.BOLD, 20));
		del.setBounds(100, 480, 150, 40);

		JTextField tx4 = new JTextField();
		tx4.setFont(new Font("Algerian", Font.BOLD, 17));
		tx4.setBounds(130, 520, 100, 40);

		JLabel lb5 = new JLabel("ROLL NO. :");
		lb5.setFont(new Font("Algerian", Font.BOLD, 17));
		lb5.setBounds(50, 520, 120, 40);

		JButton delete = new JButton("DELETE");
		delete.setFont(new Font("Algerian", Font.BOLD, 18));
		delete.setBounds(50, 570, 200, 40);

		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					boolean delet = false;
					int num2 = Integer.parseInt(tx4.getText());
					if (num2 == num2) {
						int d = st.executeUpdate("DELETE FROM stu WHERE id=" + num2 + "");
						System.out.println("data deleted");
						JOptionPane.showMessageDialog(delete, "DATA DELETED");

						delet = true;

					}
					if (!delet) {
						System.out.println("Student not found");
						JOptionPane.showMessageDialog(delete, "DATA NOT DELETED");

					}

				} catch (Exception p) {
					System.out.println("Enter the no. :" + p);

				}
				tx4.setText("");

			}
		});

		JLabel up = new JLabel("UPDTAE DATA");
		up.setFont(new Font("Algerian", Font.BOLD, 20));
		up.setBounds(100, 630, 150, 40);

		JTextField tx5 = new JTextField();
		tx5.setFont(new Font("Algerian", Font.BOLD, 17));
		tx5.setBounds(130, 670, 100, 40);

		JLabel lb6 = new JLabel("ROLL NO. :");
		lb6.setFont(new Font("Algerian", Font.BOLD, 17));
		lb6.setBounds(50, 670, 120, 40);

		JTextField tx6 = new JTextField();
		tx6.setFont(new Font("Algerian", Font.BOLD, 17));
		tx6.setBounds(130, 720, 100, 40);

		JLabel lb7 = new JLabel("NAME :");
		lb7.setFont(new Font("Algerian", Font.BOLD, 17));
		lb7.setBounds(40, 720, 120, 40);

		JTextField tx7 = new JTextField();
		tx7.setFont(new Font("Algerian", Font.BOLD, 17));
		tx7.setBounds(130, 770, 100, 40);

		JLabel lb8 = new JLabel("ADDRESS :");
		lb8.setFont(new Font("Algerian", Font.BOLD, 17));
		lb8.setBounds(40, 770, 120, 40);

		JButton upd = new JButton("UPDATE");
		upd.setFont(new Font("Algerian", Font.BOLD, 18));
		upd.setBounds(250, 720, 130, 40);

		upd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int num = Integer.parseInt(tx5.getText());
				String name = tx6.getText();

				try {

					int b = st.executeUpdate("UPDATE stu SET name = '" + name + "'  WHERE id = " + num + "");

					if (b > 0) {
						System.out.println("Data updated");
						JOptionPane.showMessageDialog(upd, "NAME  UPDATED");

					} else {
						System.out.println("Student not found");
						JOptionPane.showMessageDialog(upd, "DATA NOT UPDATED");

					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				tx5.setText("");
				tx6.setText("");

			}
		});

		JButton upd1 = new JButton("UPDATE");
		upd1.setFont(new Font("Algerian", Font.BOLD, 18));

		upd1.setBounds(250, 770, 130, 40);

		upd1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int num = Integer.parseInt(tx5.getText());
				String address = tx7.getText();

				try {

					int d = st.executeUpdate("UPDATE stu SET address = '" + address + "'  WHERE id = " + num + "");

					if (d > 0) {
						System.out.println("Data updated");
						JOptionPane.showMessageDialog(upd1, " ADDRESS UPDATED");

					} else {
						System.out.println("Student not found");
						JOptionPane.showMessageDialog(upd1, "DATA NOT UPDATED");

					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				tx5.setText("");
				tx7.setText("");

			}
		});

		panel.add(ins);
		panel.add(sho);
		panel.add(sear);
		panel.add(del);
		panel.add(up);

		panel.add(b);
		panel.add(delete);
		panel.add(sh);
		panel.add(search);
		panel.add(upd);
		panel.add(upd1);

		panel.add(tx);
		panel.add(tx1);
		panel.add(tx2);
		panel.add(tx3);
		panel.add(tx4);
		panel.add(tx5);
		panel.add(tx6);
		panel.add(tx7);

		panel.add(lb);
		panel.add(lb1);
		panel.add(lb2);
		panel.add(lb3);
		panel.add(lb4);
		panel.add(lb5);
		panel.add(lb6);
		panel.add(lb7);
		panel.add(lb8);

        f.add(panel);
        f.setSize(400, 1050);

		//f.setLayout(null);
		f.setVisible(true);
        });

	}

}
