package UI.PersonnelGUIs;

import javax.swing.*;

public class ManagePeopleGUI extends JFrame {
    private JPanel managePeoplePanel;

    public ManagePeopleGUI(String title){
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(managePeoplePanel);
        this.setSize(500, 500);
    }
}
