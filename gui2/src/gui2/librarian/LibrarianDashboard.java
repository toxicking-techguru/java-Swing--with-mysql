package gui2.librarian;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LibrarianDashboard extends JFrame {

    public LibrarianDashboard() {
        setTitle("Librarian Dashboard");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // header panel
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        headerPanel.setBackground(Color.WHITE);

        JLabel headerLabel = new JLabel("Librarian Dashboard");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(headerLabel);

        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // center panel
        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        centerPanel.setBackground(Color.WHITE);

        JButton addMaterialsButton = new JButton("Add Materials");
        addMaterialsButton.addActionListener(e -> {
            // TODO: Implement code to open Lib1AddMaterials form
            new Lib1AddMaterials();
        });
        centerPanel.add(addMaterialsButton);

        JButton viewHistoryButton = new JButton("View and Update Requests");
        viewHistoryButton.addActionListener(e -> {
            // TODO: Implement code to open ViewHistory form
            new RentalHistoryForm_update();
        });
        centerPanel.add(viewHistoryButton);

       

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        add(mainPanel);

        setVisible(true);
    }
}
