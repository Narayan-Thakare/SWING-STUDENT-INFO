package controllerr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Showdata {

	
 public void sho() throws SQLException{
	 
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
     JFrame frame = new JFrame("Database Data");
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     // Create a JTable to display the data
     JTable table = new JTable(buildTableModel(resultSet));
     // Add the JTable to the JFrame
     frame.add(new JScrollPane(table));
     // Display the JFrame
     frame.pack();
     frame.setVisible(true);
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
		
		
		
	

}
