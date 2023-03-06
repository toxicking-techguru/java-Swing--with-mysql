package gui2.librarian;

import javax.swing.*;

public class Branch2 extends JFrame {

    public Branch2() {
        setTitle("Branch 1");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel label = new JLabel("This is Branch 2");
        add(label);

        setVisible(true);
    }
}
