package gui2.admin;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AdminPanel extends JDialog {

    public AdminPanel() {
        setTitle("Admin Panel");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Create buttons and add action listeners to them
        JButton addUserButton = new JButton("Add User Account");
        addUserButton.setBackground(Color.GREEN);
        addUserButton.setForeground(Color.WHITE);
        addUserButton.addActionListener(e -> {
            showAddUserForm();
        });

        JButton deleteUserButton = new JButton("Delete User Account");
        deleteUserButton.setBackground(Color.RED);
        deleteUserButton.setForeground(Color.WHITE);
        deleteUserButton.addActionListener(e -> {
            showDeleteUserForm();
        });

        JButton addEmployeeButton = new JButton("Add Branch Employees");
        addEmployeeButton.setBackground(Color.BLUE);
        addEmployeeButton.setForeground(Color.WHITE);
        addEmployeeButton.addActionListener(e -> {
            showAddEmployeeForm();
        });

        JButton deleteBranch = new JButton("Delete Branch");
        deleteBranch.setBackground(Color.ORANGE);
        deleteBranch.setForeground(Color.WHITE);
        deleteBranch.addActionListener(e -> {
            deleteBranchForm();
        });

        JButton customerRegButton = new JButton("Register Customer");
        customerRegButton.setBackground(Color.MAGENTA);
        customerRegButton.setForeground(Color.WHITE);
        customerRegButton.addActionListener(e -> {
            showCustomerRegForm();
        });

        // Add the buttons to the frame
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(addUserButton);
        buttonPanel.add(deleteUserButton);
        buttonPanel.add(addEmployeeButton);
        buttonPanel.add(deleteBranch);
        buttonPanel.add(customerRegButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        add(mainPanel);

        setVisible(true);
    }



    private void showAddUserForm() {
        // Create the form components
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);

        JLabel userTypeLabel = new JLabel("User Type:");
        String[] userTypes = {"Branch Manager", "Librarian", "Customer"};
        JComboBox<String> userTypeComboBox = new JComboBox<>(userTypes);

        // Create the form and add the components
        JPanel formPanel = new JPanel(new GridLayout(3, 2));
        formPanel.add(usernameLabel);
        formPanel.add(usernameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        formPanel.add(userTypeLabel);
        formPanel.add(userTypeComboBox);

        // Show the form in a dialog
        int result = JOptionPane.showConfirmDialog(this, formPanel, "Add User Account", JOptionPane.OK_CANCEL_OPTION);

        // Check if the user clicked the OK button
        if (result == JOptionPane.OK_OPTION) {
            // Get the user input from the form
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String userType = (String) userTypeComboBox.getSelectedItem();

            // Insert the user information into the MySQL database
            try {
                // Load the MySQL JDBC driver
                Class.forName("com.mysql.jdbc.Driver");

                // Connect to the MySQL database
                String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL";
                String user = "root";
                String pass = "";
                Connection conn = DriverManager.getConnection(url, user, pass);

                // Insert the user information into the users table
                String sql = "INSERT INTO users_account (username, password, user_type) VALUES (?, ?, ?)";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, username);
                statement.setString(2, password);
                statement.setString(3, userType);
                statement.executeUpdate();

                // Close the database connection
                conn.close();

                // Show a success message
                JOptionPane.showMessageDialog(this, "User account created successfully.");

            } catch (ClassNotFoundException | SQLException ex) {
                // Show an error message if there was an error connecting to the database
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
    }

  
     private void showDeleteUserForm() {
    try {
        // Load the MySQL JDBC driver
        Class.forName("com.mysql.jdbc.Driver");

        // Connect to the MySQL database
        String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String user = "root";
        String pass = "";
        Connection conn = DriverManager.getConnection(url, user, pass);

        // Get a list of all user accounts from the database
        String sql = "SELECT username FROM users_account";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        // Create a list box to display the user accounts
        JList<String> userList = new JList<>();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        while (resultSet.next()) {
            listModel.addElement(resultSet.getString("username"));
        }
        userList.setModel(listModel);

        // Create a form panel with the list box and a delete button
        JPanel formPanel = new JPanel(new BorderLayout());
        formPanel.add(new JScrollPane(userList), BorderLayout.CENTER);
        JButton deleteButton = new JButton("Delete User Account");
        deleteButton.addActionListener(e -> {
            try {
                // Get the selected user account from the list box
                String selectedUser = userList.getSelectedValue();

                // Delete the user account from the MySQL database
                String deleteSql = "DELETE FROM users_account WHERE username = ?";
                PreparedStatement deleteStatement = conn.prepareStatement(deleteSql);
                deleteStatement.setString(1, selectedUser);
                int rowsDeleted = deleteStatement.executeUpdate();

                // Show a success message
                JOptionPane.showMessageDialog(this, rowsDeleted + " user account deleted successfully.");

                // Update the list box
                listModel.removeElement(selectedUser);
            } catch (SQLException ex) {
                // Show an error message if there was an error deleting the user account
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
        formPanel.add(deleteButton, BorderLayout.SOUTH);

        // Show the delete user form in a dialog
        int result = JOptionPane.showConfirmDialog(this, formPanel, "Delete User Account", JOptionPane.OK_CANCEL_OPTION);

        // Close the database connection
        conn.close();

        // Check if the user clicked the OK button
        if (result == JOptionPane.OK_OPTION) {
            // Do nothing
        }
    } catch (ClassNotFoundException | SQLException ex) {
        // Show an error message if there was an error connecting to the database
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
    }
}
     
     private void showAddEmployeeForm() {
    // Create the form components
    JLabel libraryLabel = new JLabel("Library:");
    String[] libraries = {"Central Library", "Branch Library 1", "Branch Library 2"};
    JComboBox<String> libraryComboBox = new JComboBox<>(libraries);

    JLabel firstNameLabel = new JLabel("First Name:");
    JTextField firstNameField = new JTextField(20);

    JLabel lastNameLabel = new JLabel("Last Name:");
    JTextField lastNameField = new JTextField(20);

    JLabel addressLabel = new JLabel("Address:");
    JTextField addressField = new JTextField(20);

    JLabel phoneNumberLabel = new JLabel("Phone Number:");
    JTextField phoneNumberField = new JTextField(20);

    JLabel designationLabel = new JLabel("Designation:");
    JTextField designationField = new JTextField(20);

    JLabel experienceLabel = new JLabel("Experience:");
    JTextField experienceField = new JTextField(20);

    // Create the form and add the components
    JPanel formPanel = new JPanel(new GridLayout(7, 2));
    formPanel.add(libraryLabel);
    formPanel.add(libraryComboBox);
    formPanel.add(firstNameLabel);
    formPanel.add(firstNameField);
    formPanel.add(lastNameLabel);
    formPanel.add(lastNameField);
    formPanel.add(addressLabel);
    formPanel.add(addressField);
    formPanel.add(phoneNumberLabel);
    formPanel.add(phoneNumberField);
    formPanel.add(designationLabel);
    formPanel.add(designationField);
    formPanel.add(experienceLabel);
    formPanel.add(experienceField);

    // Show the form in a dialog
    int result = JOptionPane.showConfirmDialog(this, formPanel, "Add Employee", JOptionPane.OK_CANCEL_OPTION);

    // Check if the user clicked the OK button
    if (result == JOptionPane.OK_OPTION) {
        // Get the user input from the form
        String library = (String) libraryComboBox.getSelectedItem();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String address = addressField.getText();
        String phoneNumber = phoneNumberField.getText();
        String designation = designationField.getText();
        String experience = experienceField.getText();

        // Insert the employee information into the MySQL database
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Connect to the MySQL database
            String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL";
            String user = "root";
            String pass = "";
            Connection conn = DriverManager.getConnection(url, user, pass);

            // Insert the employee information into the employees table
            String sql = "INSERT INTO employees (library, first_name, last_name, address, phone_number, designation, experience) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, library);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setString(4, address);
            statement.setString(5, phoneNumber);
            statement.setString(6, designation);
            statement.setString(7, experience);
            statement.executeUpdate();

            // Close the database connection
            conn.close();

            // Show a success message
            JOptionPane.showMessageDialog(this, "Employee added successfully.");

        } catch (ClassNotFoundException | SQLException ex) {
            // Show an error message if there was an error connecting to the database
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}


    private void deleteBranchForm() {
    try {
        // Load the MySQL JDBC driver
        Class.forName("com.mysql.jdbc.Driver");

        // Connect to the MySQL database
        String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String user = "root";
        String pass = "";
        Connection conn = DriverManager.getConnection(url, user, pass);

        // Get a list of all user accounts from the database
        String sql = "SELECT library FROM employees";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        // Create a list box to display the user accounts
        JList<String> userList = new JList<>();
        DefaultListModel<String> listModel = new DefaultListModel<>();
        while (resultSet.next()) {
            listModel.addElement(resultSet.getString("library"));
        }
        userList.setModel(listModel);

        // Create a form panel with the list box and a delete button
        JPanel formPanel = new JPanel(new BorderLayout());
        formPanel.add(new JScrollPane(userList), BorderLayout.CENTER);
        JButton deleteButton = new JButton("Delete library");
        deleteButton.addActionListener(e -> {
            try {
                // Get the selected user account from the list box
                String selectedUser = userList.getSelectedValue();

                // Delete the user account from the MySQL database
                String deleteSql = "DELETE FROM employees WHERE library = ?";
                PreparedStatement deleteStatement = conn.prepareStatement(deleteSql);
                deleteStatement.setString(1, selectedUser);
                int rowsDeleted = deleteStatement.executeUpdate();

                // Show a success message
                JOptionPane.showMessageDialog(this, rowsDeleted + " Branch deleted successfully.");

                // Update the list box
                listModel.removeElement(selectedUser);
            } catch (SQLException ex) {
                // Show an error message if there was an error deleting the user account
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
        formPanel.add(deleteButton, BorderLayout.SOUTH);

        // Show the delete user form in a dialog
        int result = JOptionPane.showConfirmDialog(this, formPanel, "Delete Branch", JOptionPane.OK_CANCEL_OPTION);

        // Close the database connection
        conn.close();

        // Check if the user clicked the OK button
        if (result == JOptionPane.OK_OPTION) {
            // Do nothing
        }
    } catch (ClassNotFoundException | SQLException ex) {
        // Show an error message if there was an error connecting to the database
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
    }
}
    
private void showCustomerRegForm() {
    // Create the form components
    

    JLabel firsNameLabel = new JLabel("First Name:");
    JTextField firstNameField = new JTextField(20);

    JLabel lasNameLabel = new JLabel("Last Name:");
    JTextField lastNameField = new JTextField(20);

    JLabel addresLabel = new JLabel("Address:");
    JTextField addressField = new JTextField(20);

    JLabel phonNumberLabel = new JLabel("Phone Number:");
    JTextField phoneNumberField = new JTextField(20);

    JLabel UsernameLabel = new JLabel("Username:");
    JTextField designatioField = new JTextField(20);

    JLabel PasswordLabel = new JLabel("Password:");
    JTextField experienceField = new JPasswordField(20);

    // Create the form and add the components
    JPanel formPanel = new JPanel(new GridLayout(6, 2));
    formPanel.add(firsNameLabel);
    formPanel.add(firstNameField);
    formPanel.add(lasNameLabel);
    formPanel.add(lastNameField);
    formPanel.add(addresLabel);
    formPanel.add(addressField);
    formPanel.add(phonNumberLabel);
    formPanel.add(phoneNumberField);
    formPanel.add(UsernameLabel);
    formPanel.add(designatioField);
    formPanel.add(PasswordLabel);
    formPanel.add(experienceField);

    // Show the form in a dialog
    int result = JOptionPane.showConfirmDialog(this, formPanel, "Register Customer", JOptionPane.OK_CANCEL_OPTION);

    // Check if the user clicked the OK button
    if (result == JOptionPane.OK_OPTION) {
        // Get the user input from the form
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String address = addressField.getText();
        String phoneNumber = phoneNumberField.getText();
        String user_name = designatioField.getText();
        String password = experienceField.getText();

        // Insert the employee information into the MySQL database
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Connect to the MySQL database
            String url = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL";
            String user = "root";
            String pass = "";
            Connection conn = DriverManager.getConnection(url, user, pass);

            // Insert the employee information into the employees table
            String sql = "INSERT INTO customer_reg (first_name, last_name, address, phone_number, username, password) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            //statement.setString(1, library);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, address);
            statement.setString(4, phoneNumber);
            statement.setString(5, user_name);
            statement.setString(6, password);
            //statement.setString(7, password);
            statement.executeUpdate();

            // Close the database connection
            conn.close();

            // Show a success message
            JOptionPane.showMessageDialog(this, "customers regesterd  successfully.");

        } catch (ClassNotFoundException | SQLException ex) {
            // Show an error message if there was an error connecting to the database
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}

}




