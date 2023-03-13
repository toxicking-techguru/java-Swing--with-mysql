package gui2.admin;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class RentalHistoryForm extends JFrame {

    private JTable rentalHistoryTable;
    private DefaultTableModel tableModel;

    public RentalHistoryForm() {
        // Set up frame
        setTitle("Rental History");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set up table
        String[] columnNames = {"Type", "Material", "Location", "Duration (days)", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0);
        rentalHistoryTable = new JTable(tableModel);

        // Set up layout
        JScrollPane scrollPane = new JScrollPane(rentalHistoryTable);
        add(scrollPane);

        // Load rental history from database
        loadRentalHistory();

        // Show frame
        setVisible(true);
    }

    private void loadRentalHistory() {
        // Connect to database
        String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String username = "root";
        String password = "";
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();

            // Execute query
            String query = "SELECT * FROM rental_requests ";
            ResultSet rs = stmt.executeQuery(query);

            // Add results to table model
            while (rs.next()) {
                String type = rs.getString("type");
                String material = rs.getString("material");
                String location = rs.getString("location");
                int duration = rs.getInt("duration");
                String status = rs.getString("status");
                Object[] rowData = {type, material, location, duration, status};
                tableModel.addRow(rowData);
            }

            // Close connections
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new RentalHistoryForm();
    }
}
