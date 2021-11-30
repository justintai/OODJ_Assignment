package UI;

import javax.swing.*;

public class ManageVaccine extends JFrame{
    private JPanel vaccinePanel;

    public ManageVaccine(String title) {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(vaccinePanel);
        this.setSize(500, 500);
    }
}
