package gui2.librarian;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Lib1AddMaterials extends JFrame {

    private JTextField serialNumberField;
    private JTextField nameField;
    private JTextField registeredDateField;
    private JCheckBox isAvailabilityCheckBox;

    public Lib1AddMaterials() {
        setTitle("Add Materials");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        JLabel serialNumberLabel = new JLabel("Serial Number:");
        panel.add(serialNumberLabel);

        serialNumberField = new JTextField(20);
        serialNumberField.setPreferredSize(new Dimension(64, 22));
        JPanel serialPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        serialPanel.add(serialNumberField);
        panel.add(serialPanel);

        JLabel nameLabel = new JLabel("Name:");
        panel.add(nameLabel);

        nameField = new JTextField(20);
        nameField.setPreferredSize(new Dimension(64, 22));
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.add(nameField);
        panel.add(namePanel);

        JLabel registeredDateLabel = new JLabel("Registered Date:");
        panel.add(registeredDateLabel);

        registeredDateField = new JTextField(20);
        registeredDateField.setPreferredSize(new Dimension(64, 22));
        JPanel registeredPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        registeredPanel.add(registeredDateField);
        panel.add(registeredPanel);

        JLabel isAvailabilityLabel = new JLabel("Is Available:");
        panel.add(isAvailabilityLabel);

        isAvailabilityCheckBox = new JCheckBox();
        JPanel availabilityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        availabilityPanel.add(isAvailabilityCheckBox);
        panel.add(availabilityPanel);

        JLabel authorLabel = new JLabel("Author:");
        panel.add(authorLabel);

        JTextField authorField = new JTextField(20);
        authorField.setPreferredSize(new Dimension(64, 22));
        JPanel authorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        authorPanel.add(authorField);
        panel.add(authorPanel);

        JLabel pagesLabel = new JLabel("No. of Pages:");
        panel.add(pagesLabel);

        JTextField pagesField = new JTextField(20);
        pagesField.setPreferredSize(new Dimension(64, 22));
        JPanel pagesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pagesPanel.add(pagesField);
        panel.add(pagesPanel);

        JLabel languageLabel = new JLabel("Language:");
        panel.add(languageLabel);

        JTextField languageField = new JTextField(20);
        languageField.setPreferredSize(new Dimension(64, 22));
        JPanel languagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        languagePanel.add(languageField);
        panel.add(languagePanel);

        JLabel bindingLabel = new JLabel("Type of Binding:");
        panel.add(bindingLabel);

        JTextField bindingField = new JTextField(20);
        bindingField.setPreferredSize(new Dimension(64, 22));
        JPanel bindingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bindingPanel.add(bindingField);
        panel.add(bindingPanel);

        JButton addButton = new JButton("Add");
        addButton.setPreferredSize(new Dimension(80, 30));
        addButton.addActionListener(e -> {
            String serialNumber = serialNumberField.getText();
            String name = nameField.getText();
            String registeredDate = registeredDateField.getText();
            boolean isAvailability = isAvailabilityCheckBox.isSelected();
            String author = authorField.getText();
            int pages = Integer.parseInt(pagesField.getText());
            String language = languageField.getText();
            String binding = bindingField.getText();
               // TODO: add the data to the database using JDBC
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Open a connection
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "");

            // Create a prepared statement
            String sql = "INSERT INTO materials (serial_number, name, registered_date, availability, author, pages, language, binding) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            // Set the values for the prepared statement
            stmt.setString(1, serialNumber);
            stmt.setString(2, name);
            stmt.setString(3, registeredDate);
            stmt.setBoolean(4, isAvailability);
            stmt.setString(5, author);
            stmt.setInt(6, pages);
            stmt.setString(7, language);
            stmt.setString(8, binding);

            // Execute the prepared statement
            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Data added successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add data.");
            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "JDBC driver not found.");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        } finally {
            // Close the prepared statement and connection
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    // Ignore
                }
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    // Ignore
                }
            }
        }
    });
    panel.add(addButton);

    // Set the panel as the content pane of the JFrame
    setContentPane(panel);
    setVisible(true);
}

public static void main(String[] args) {
    // Start the GUI on the event dispatch thread
    SwingUtilities.invokeLater(() -> new Lib1AddMaterials());
}
}