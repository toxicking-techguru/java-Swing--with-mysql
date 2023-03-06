package gui2;

import gui2.librarian.Branch3;
import gui2.librarian.Branch2;
import gui2.librarian.Branch1;
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
        JMenuItem adminMenuItem1 = new JMenuItem("Admin Menu Item 1");
        JMenuItem adminMenuItem2 = new JMenuItem("Admin Menu Item 2");
        JMenuItem librarianMenuItem1 = new JMenuItem("branch 1");
        JMenuItem librarianMenuItem2 = new JMenuItem("branch 2");

        JMenuItem librarianMenuItem3 = new JMenuItem("branch 3");
        JMenuItem customerMenuItem1 = new JMenuItem("Customer Menu Item 1");
        JMenuItem customerMenuItem2 = new JMenuItem("Customer Menu Item 2");
        JMenuItem branchManagerMenuItem1 = new JMenuItem("Branch Manager Menu Item 1");
        JMenuItem branchManagerMenuItem2 = new JMenuItem("Branch Manager Menu Item 2");

        // Add action listener to branch 1 menu item
        librarianMenuItem1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Branch1();
                
            }
        });

        
        
        
        // Add action listener to branch 2 menu item
        librarianMenuItem2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              
                new Branch2();
            }
        });

       
        
        // Add action listener to branch 3 menu item
        librarianMenuItem3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              
                new Branch3();
            }
        });
        
        
        
        
        
        // Add menu items to menus
        adminMenu.add(adminMenuItem1);
        adminMenu.add(adminMenuItem2);
        librarianMenu.add(librarianMenuItem1);
        librarianMenu.add(librarianMenuItem2);
        librarianMenu.add(librarianMenuItem3);
        customerMenu.add(customerMenuItem1);
        customerMenu.add(customerMenuItem2);
        branchManagerMenu.add(branchManagerMenuItem1);
        branchManagerMenu.add(branchManagerMenuItem2);

        // Add menus to menu bar
        menuBar.add(adminMenu);
        menuBar.add(librarianMenu);
        menuBar.add(customerMenu);
        menuBar.add(branchManagerMenu);

        // Set menu bar
        setJMenuBar(menuBar);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        add(panel);

        // Set background color of panel
        panel.setBackground(new Color(207, 241, 255));

        setVisible(true);
    }

    public static void main(String[] args) {
        new Gui2();
    }
}
