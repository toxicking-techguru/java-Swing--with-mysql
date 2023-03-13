package gui2.admin;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Admin extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public Admin() {
        setTitle("Admin Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // create components
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");

        // set layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(usernameLabel, gbc);

        gbc.gridx = 1;
        add(usernameField, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        add(passwordLabel, gbc);

        gbc.gridx = 1;
        add(passwordField, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(loginButton, gbc);

        // add action listener to login button
        loginButton.addActionListener(this);

        pack();
        setLocationRelativeTo(null); // center window on screen
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // get entered username and password
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // check if username and password are correct
        if (username.equals("admin") && password.equals("root")) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            dispose(); // close login window
            // open admin panel
            new AdminPanel();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password!");
        }
    }

    public static void main(String[] args) {
        new Admin();
    }
}



