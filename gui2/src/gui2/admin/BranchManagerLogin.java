package gui2.admin;

import gui2.admin.BranchManagerDashboardForm;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class BranchManagerLogin  extends JFrame implements ActionListener {

    private JLabel titleLabel, usernameLabel, passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public BranchManagerLogin () {
        this.setTitle("Branch Manager Login");
        this.setLayout(null);
        this.setSize(500, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        titleLabel = new JLabel("Branch Manager Login");
        titleLabel.setBounds(150, 30, 200, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        this.add(titleLabel);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 80, 100, 30);
        this.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(150, 80, 200, 30);
        this.add(usernameField);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 120, 100, 30);
        this.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 120, 200, 30);
        this.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(200, 180, 100, 30);
        loginButton.addActionListener(this);
        this.add(loginButton);

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "");
                Statement stmt = con.createStatement();
                String query = "SELECT * FROM users_account WHERE username='" + username + "' AND password='" + password + "'";
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Login successful!");
                    BranchManagerDashboardForm dashboard = new BranchManagerDashboardForm();
                    dashboard.setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid username or password!");
                }
                con.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new BranchManagerLogin ();
    }
}
