package gui2.admin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class RentalRequestForm extends JFrame implements ActionListener {

    private JLabel titleLabel, typeLabel, materialLabel, locationLabel, durationLabel, amountLabel;
    private JTextField durationField, amountField;
    private JComboBox<String> typeComboBox, materialComboBox, locationComboBox;
    private JButton submitButton, cancelButton;

    public RentalRequestForm() {
        // Set up frame
        setTitle("Place Rental Request");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create labels
        titleLabel = new JLabel("Place Rental Request");
        typeLabel = new JLabel("Type:");
        materialLabel = new JLabel("Material:");
        locationLabel = new JLabel("Location:");
        durationLabel = new JLabel("Duration (days):");
        amountLabel = new JLabel("Amount:");

        // Create input fields
        durationField = new JTextField(10);
        amountField = new JTextField(10);

        // Create dropdowns
        String[] typeOptions = {"Book", "Magazine"};
        typeComboBox = new JComboBox<String>(typeOptions);
        String[] materialOptions = {"Book 1", "Book 2", "Magazine 1", "Magazine 2"};
        materialComboBox = new JComboBox<String>(materialOptions);
        String[] locationOptions = {"Location 1", "Location 2", "Location 3"};
        locationComboBox = new JComboBox<String>(locationOptions);

        // Create buttons
        submitButton = new JButton("Submit");
        cancelButton = new JButton("Cancel");
        submitButton.addActionListener(this);
        cancelButton.addActionListener(this);

        // Set up layout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(titleLabel, constraints);

        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.EAST;
        panel.add(typeLabel, constraints);

        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(typeComboBox, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.anchor = GridBagConstraints.EAST;
        panel.add(materialLabel, constraints);

        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(materialComboBox, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.anchor = GridBagConstraints.EAST;
        panel.add(locationLabel, constraints);

        constraints.gridx = 1;
        constraints.anchor = GridBagConstraints.WEST;
        panel.add(locationComboBox, constraints);
    constraints.gridx = 0;
    constraints.gridy = 4;
    constraints.anchor = GridBagConstraints.EAST;
    panel.add(durationLabel, constraints);

    constraints.gridx = 1;
    constraints.anchor = GridBagConstraints.WEST;
    panel.add(durationField, constraints);

    constraints.gridx = 0;
    constraints.gridy = 5;
    constraints.anchor = GridBagConstraints.EAST;
    panel.add(amountLabel, constraints);

    constraints.gridx = 1;
    constraints.anchor = GridBagConstraints.WEST;
    panel.add(amountField, constraints);

    constraints.gridx = 0;
    constraints.gridy = 6;
    constraints.anchor = GridBagConstraints.CENTER;
    constraints.gridwidth = 2;
    panel.add(submitButton, constraints);

    constraints.gridy = 7;
    panel.add(cancelButton, constraints);

    add(panel);
    setVisible(true);
}

public void actionPerformed(ActionEvent e) {
    if (e.getSource() == submitButton) {
        // Get input values
        String type = (String) typeComboBox.getSelectedItem();
        String material = (String) materialComboBox.getSelectedItem();
        String location = (String) locationComboBox.getSelectedItem();
        int duration = Integer.parseInt(durationField.getText());
        double amount = Double.parseDouble(amountField.getText());

        // Add rental request to database
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "");
            PreparedStatement ps = con.prepareStatement("INSERT INTO rental_requests (type, material, location, duration, revenue) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, type);
            ps.setString(2, material);
            ps.setString(3, location);
            ps.setInt(4, duration);
            ps.setDouble(5, amount);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Rental request submitted successfully.");
            con.close();
            dispose();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error submitting rental request.");
        }
    } else if (e.getSource() == cancelButton) {
        dispose();
    }
}

public static void main(String[] args) {
    new RentalRequestForm();
}
}