package gui2.admin;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class CustomerLogin extends JFrame implements ActionListener{
    private JLabel userLabel, passLabel;
    private JTextField userField;
    private JPasswordField passField;
    private JButton loginButton;
    

    public CustomerLogin() {
        super("Customer Login Form");
        setSize(300, 150);
    
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        userLabel = new JLabel("Username:");
        passLabel = new JLabel("Password:");
        userField = new JTextField(20);
        passField = new JPasswordField(20);
        loginButton = new JButton("Login");
        

        setLayout(new GridLayout(4, 2));
        add(userLabel);
        add(userField);
        add(passLabel);
        add(passField);
        add(new JLabel(""));
        add(loginButton);
        add(new JLabel(""));
       
        setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = String.valueOf(passField.getPassword());

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "");
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("SELECT * FROM customer_reg WHERE username='" + username + "' AND password='" + password + "'");

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(CustomerLogin.this, "Login Successful!");
                        dispose();

                        
                        // Open new JFrame to display all records from "employees" table
                        JFrame viewFrame = new JFrame("Customer Dashboard");
                        JPanel panel = new JPanel(new BorderLayout());
                       // JScrollPane scrollPane = new JScrollPane(table);
                       // panel.add(scrollPane, BorderLayout.CENTER);
                       
                       //Rental button
                       JButton rentalButton = new JButton("Rental Request");
                       
                       rentalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open RentalRequestForm
                RentalRequestForm rentalForm = new RentalRequestForm();
                rentalForm.setVisible(true);
            }
        });
                       
                       
                       // View Rental Hisory Button
                       
                       JButton ViewHistoryButton = new JButton("Rental Historyt");
                       
                       ViewHistoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open RentalRequestForm
                RentalHistoryForm rentalHistoryForm = new RentalHistoryForm();
                rentalHistoryForm.setVisible(true);
            }
        });
                       

                        JButton viewButton = new JButton("View Data");
                        viewButton.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                

                                // Open DatabaseViewer
                                DatabaseViewer dbViewer = new DatabaseViewer();
                                dbViewer.setVisible(true);
                            }
                        });

                        panel.add(viewButton, BorderLayout.SOUTH);
                        panel.add(rentalButton, BorderLayout.CENTER);
                        panel.add(ViewHistoryButton, BorderLayout.NORTH);
                        viewFrame.getContentPane().add(panel);
                        viewFrame.setSize(500, 300);
                        viewFrame.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(CustomerLogin.this, "Invalid Login Credentials.");
                    }
                    rs.close();
                    stmt.close();
                    con.close();
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(CustomerLogin.this, "MySQL JDBC Driver not found .");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(CustomerLogin.this, ex.getMessage());
                }
            }
        });
        
        
    }


public static void main(String[] args) {
    CustomerLogin loginForm = new CustomerLogin();
    
}

   
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}