package gui2.admin;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DatabaseViewer extends JFrame {
    
    private JTable table;
    private JButton rentalButton;
    
    
    public  DatabaseViewer() {
        super("All Collections");

        // Create a DefaultTableModel and add the column names
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Serial Number");
        model.addColumn("Name");
        model.addColumn("REG Date");
        model.addColumn("Availability");
        model.addColumn("Author");
        model.addColumn("Pages");
        model.addColumn("Language");

        // Initialize database connection and retrieve data
        try {
            // Replace the following with your own database credentials
            String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL";
            String username = "root";
            String password = "";

            // Connect to the database
            Connection conn = DriverManager.getConnection(url, username, password);

            // Execute a SQL query to retrieve data from the employees table
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM materials ");

            // Add the data to the model
            while (rs.next()) {
                //int id = rs.getInt("id");
                String library = rs.getString("serial_number");
                String firstName = rs.getString("name");
                String lastName = rs.getString("registered_date");
                String address = rs.getString("availability");
                String phoneNumber = rs.getString("author");
                String designation = rs.getString("pages");
                String Language = rs.getString("Language");
                
                Object[] row = {library, firstName, lastName, address, phoneNumber, designation, Language};
                model.addRow(row);
            }

            // Close the database connection and statement
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

        // Create a JTable with the model
        table = new JTable(model);

        // Add the table to a JScrollPane and display it
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new  DatabaseViewer();
    }
}
