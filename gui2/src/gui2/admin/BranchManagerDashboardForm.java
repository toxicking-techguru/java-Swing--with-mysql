package gui2.admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class BranchManagerDashboardForm extends JFrame implements ActionListener {

    private JLabel titleLabel;
    private JButton viewMaterialCollectionButton, viewRentRequestsButton, viewTotalRevenueButton;

    public BranchManagerDashboardForm() {
        this.setTitle("Branch Manager Dashboard");
        this.setLayout(null);
        this.setSize(500, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        titleLabel = new JLabel("Branch Manager Dashboard");
        titleLabel.setBounds(150, 30, 200, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(titleLabel);

        viewMaterialCollectionButton = new JButton("View Material Collection");
        viewMaterialCollectionButton.setBounds(150, 80, 200, 30);
        viewMaterialCollectionButton.addActionListener(this);
        this.add(viewMaterialCollectionButton);

        viewRentRequestsButton = new JButton("View Rent Requests");
        viewRentRequestsButton.setBounds(150, 120, 200, 30);
        viewRentRequestsButton.addActionListener(this);
        this.add(viewRentRequestsButton);
    viewTotalRevenueButton = new JButton("View Total Revenue");
    viewTotalRevenueButton.setBounds(150, 160, 200, 30);
    viewTotalRevenueButton.addActionListener(this);
    this.add(viewTotalRevenueButton);

    this.setVisible(true);
}

@Override

public void actionPerformed(ActionEvent e) {
    if (e.getSource() == viewMaterialCollectionButton) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM materials");

            // Create a JTable to display the data
            JTable table = new JTable(buildTableModel(rs));
            JScrollPane scrollPane = new JScrollPane(table);
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(scrollPane, BorderLayout.CENTER);
            panel.setPreferredSize(new Dimension(500, 300));
            JOptionPane.showMessageDialog(null, panel);

            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    } else if (e.getSource() == viewRentRequestsButton) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM rental_requests");

            // Create a JTable to display the data
            JTable table = new JTable(buildTableModel(rs));
            JScrollPane scrollPane = new JScrollPane(table);
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(scrollPane, BorderLayout.CENTER);
            panel.setPreferredSize(new Dimension(500, 300));
            JOptionPane.showMessageDialog(null, panel);

            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    } else if (e.getSource() == viewTotalRevenueButton) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT SUM(revenue) AS total_revenue FROM rental_requests");
            if (rs.next()) {
                double totalRevenue = rs.getDouble("total_revenue");

                // Display the total revenue value
                JOptionPane.showMessageDialog(null, "Total Revenue: " + totalRevenue);
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

// Helper method to convert ResultSet to TableModel
private static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
    ResultSetMetaData metaData = rs.getMetaData();
    // Get column names
    Vector<String> columnNames = new Vector<String>();
    int columnCount = metaData.getColumnCount();
    for (int i = 1; i <= columnCount; i++) {
        columnNames.add(metaData.getColumnName(i));
    }
    // Get data
    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
    while (rs.next()) {
        Vector<Object> row = new Vector<Object>();
        for (int i = 1; i <= columnCount; i++) {
            row.add(rs.getObject(i));
        }
        data.add(row);
    }
    // Create TableModel
    return new DefaultTableModel(data, columnNames);
}
}