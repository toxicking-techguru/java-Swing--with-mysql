package gui2.librarian;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Branch1 extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public Branch1() {
        setTitle("Branch 1 Login");
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
           // if (username.equals("librarian1") && password.equals("1234")) {
             //   JOptionPane.showMessageDialog(null, "Login successful!");
               // setVisible(true);
                //dispose();
                 
                new Lib1AddMaterials();
                 
               // new Branch1();
               
               
            //} else {
           //     JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.");
            //}
        });
        panel.add(loginButton);

        add(panel);

        setVisible(true);
    }
}
