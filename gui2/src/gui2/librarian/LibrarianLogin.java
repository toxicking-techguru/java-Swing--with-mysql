package gui2.librarian;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class LibrarianLogin extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LibrarianLogin() {
        setTitle("Welcome Librarian ");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);

        JLabel usernameLabel = new JLabel("Username:");
        panel.add(usernameLabel);

        usernameField = new JTextField(10);
        usernameField.setPreferredSize(new Dimension(200, 30));
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        panel.add(passwordLabel);

        passwordField = new JPasswordField(10);
        passwordField.setPreferredSize(new Dimension(200, 30));
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(64, 30));
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            
            try {
                // Connect to the database
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "");

                // Create a statement to check if the entered username and password match with an entry in the users table
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM users_account WHERE username = ? AND password = ?");
                ps.setString(1, username);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();

                // If there is a matching entry, show a success message and open the dashboard
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    setVisible(false);
                    dispose();
                    
                    LibrarianDashboard dashboard = new LibrarianDashboard();
                    dashboard.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.");
                }

                // Close the database connection
                rs.close();
                ps.close();
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        panel.add(loginButton);

        add(panel);

        setVisible(true);
    }
}
