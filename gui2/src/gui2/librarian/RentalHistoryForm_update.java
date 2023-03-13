package gui2.librarian;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RentalHistoryForm_update extends JFrame {

    private JTable rentalHistoryTable;
    private DefaultTableModel tableModel;
    private JButton updateButton;

    public RentalHistoryForm_update() {
        // Set up frame
        setTitle("Rental History");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set up table
        String[] columnNames = {"Type", "Material", "Location", "Duration (days)", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0);
        rentalHistoryTable = new JTable(tableModel);

        // Set up button
        updateButton = new JButton("Update Status");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = rentalHistoryTable.getSelectedRow();
                if (selectedRow != -1) {
                    String status = JOptionPane.showInputDialog("Enter new status:");
                    String id = (String) tableModel.getValueAt(selectedRow, 0);
                    updateStatus(id, status);
                    tableModel.setValueAt(status, selectedRow, 4);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row.");
                }
            }
        });

        // Set up layout
        JScrollPane scrollPane = new JScrollPane(rentalHistoryTable);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(updateButton);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

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
                String id = rs.getString("id");
                String type = rs.getString("type");
                String material = rs.getString("material");
                String location = rs.getString("location");
                int duration = rs.getInt("duration");
                String status = rs.getString("status");
                Object[] rowData = {id, type, material, location, duration, status};
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

    private void updateStatus(String id, String status) {
        // Connect to database
        String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String username = "root";
        String password = "";
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();

            // Execute update
            String updateQuery = "UPDATE rental_requests SET status='" + status + "' WHERE id='" + id + "'";
            int rowsUpdated = stmt.executeUpdate(updateQuery);
// Check if update was successful
if (rowsUpdated > 0) {
JOptionPane.showMessageDialog(null, "Status updated successfully.");
} else {
JOptionPane.showMessageDialog(null, "Failed to update status.");
}
        // Close connections
        stmt.close();
        conn.close();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

public static void main(String[] args) {
    new RentalHistoryForm_update();
}
}
