package UI;

import javax.swing.*;

public class PersonnelGUI extends JFrame {
    private JPanel personnelPanel;
    private JLabel personnelTitle;

    public PersonnelGUI(String title) {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(personnelPanel);
        this.setSize(500, 400);
    }
}
