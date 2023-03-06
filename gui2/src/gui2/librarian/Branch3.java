package gui2.librarian;

import javax.swing.*;

public class Branch3 extends JFrame {

    public Branch3() {
        setTitle("Branch 1");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel label = new JLabel("This is Branch 1");
        add(label);

        setVisible(true);
    }
}
