package gui2;
import gui2.admin.Admin;
import gui2.admin.BranchManagerLogin;
import gui2.admin.CustomerLogin;
import gui2.librarian.LibrarianLogin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Gui2 extends JFrame {

    public Gui2() {
        setTitle("Welcome Page");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create menus
        JMenu adminMenu = new JMenu("Admin");
        JMenu librarianMenu = new JMenu("Librarian");
        JMenu customerMenu = new JMenu("Customer");
        JMenu branchManagerMenu = new JMenu("Branch Manager");

        // Create menu items
        JMenuItem adminMenuItem1 = new JMenuItem("Welcome");

        JMenuItem librarianMenuItem1 = new JMenuItem("Welcome");


        JMenuItem customerMenuItem1 = new JMenuItem("Welcome");

        JMenuItem branchManagerMenuItem1 = new JMenuItem("Welcome");


        adminMenuItem1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new Admin();

                }
            });


  customerMenuItem1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new CustomerLogin();

                }
            });


        librarianMenuItem1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LibrarianLogin();

            }
        });

        branchManagerMenuItem1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new BranchManagerLogin();

                }
            });



        // Add menu items to menus
        adminMenu.add(adminMenuItem1);

        librarianMenu.add(librarianMenuItem1);
        customerMenu.add(customerMenuItem1);
        branchManagerMenu.add(branchManagerMenuItem1);


        // Add menus to menu bar
        menuBar.add(adminMenu);
        menuBar.add(librarianMenu);
        menuBar.add(customerMenu);
        menuBar.add(branchManagerMenu);

        // Set menu bar
        setJMenuBar(menuBar);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        // Add welcome label to panel
        JLabel welcomeLabel = new JLabel("Welcome to  high-level System! select options above.");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(welcomeLabel);

        add(panel);

        // Set background color of panel
        panel.setBackground(new Color(207, 241, 255));

        setVisible(true);
    }

    public static void main(String[] args) {
        new Gui2();
    }
}
