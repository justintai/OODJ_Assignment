package UI.PersonnelFunctions;

import client.User;
import personnel.Personnel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonnelGUI extends JFrame {
    private JPanel personnelPanel;
    private JLabel personnelTitle;
    private JLabel personnelEmail;
    private JButton logoutButton;
    private JButton manageVaccineButton;
    private JButton manageCentreButton;
    private JButton managePersonnelButton;
    private JButton managePeopleButton;
    private JButton manageAppointmentButton;

    public PersonnelGUI(String title, String[] usrData) {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(personnelPanel);
        this.setSize(500, 500);

        personnelTitle.setText("Welcome " + usrData[2]);
        personnelEmail.setText("Email: " + usrData[7]);

        managePeopleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Personnel.managePeoplePage();
                dispose();
            }
        });

        manageAppointmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        manageVaccineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Personnel.manageVaccinePage();
                dispose();
            }
        });

        manageCentreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Personnel.manageVaccinationCentre();
                dispose();
            }
        });

        managePersonnelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.logout();
                dispose();
            }
        });
    }
}
