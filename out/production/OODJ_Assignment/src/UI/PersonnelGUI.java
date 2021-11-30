package UI;

import javax.swing.*;

public class PersonnelGUI extends JFrame {
    private JPanel personnelPanel;
    private JLabel personnelTitle;
    private JLabel personnelEmail;

    public PersonnelGUI(String title, String[] usrData) {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(personnelPanel);
        this.setSize(500, 400);

        personnelTitle.setText("Welcome " + usrData[2]);
        personnelEmail.setText("Email: " + usrData[7]);
    }
}
