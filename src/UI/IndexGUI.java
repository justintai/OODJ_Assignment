package UI;

import client.People;
import client.User;
import dataset.UserData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IndexGUI extends JFrame{
    private JPanel indexPanel;
    private JButton joinVaccinationButton;
    private JButton checkVaccinationAppointmentButton;
    private JButton vaccinationStatusButton;
    private JButton editProfileButton;
    private JLabel nameLabel;
    private JLabel useridLabel;

    public IndexGUI(String title, String[] userData) {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(indexPanel);
        this.setSize(500, 400);

        if(!(userData[0].equals("null")))
        {
            useridLabel.setText("IC : " + userData[0]);
            nameLabel.setText("Name : " + userData[2]);
        }
        else if(!(userData[1].equals("null")))
        {
            useridLabel.setText("Passport : " + userData[1]);
            nameLabel.setText("Name : " + userData[2]);
        }



        joinVaccinationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                
            }
        });
        editProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                People.UpdateProfilePage(userData);
                dispose();
            }
        });
    }

}
