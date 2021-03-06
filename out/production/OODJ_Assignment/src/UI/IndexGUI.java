package UI;

import client.People;
import client.User;
import dataset.AppointmentData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class IndexGUI extends JFrame{
    private JPanel indexPanel;
    private JButton joinVaccinationButton;
    private JButton checkVaccinationAppointmentButton;
    private JButton vaccinationStatusButton;
    private JButton editProfileButton;
    private JLabel nameLabel;
    private JLabel useridLabel;
    private JButton logoutButton;

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

        editProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                People.UpdateProfilePage(0);
                dispose();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                User.logout();
                dispose();
            }
        });

        joinVaccinationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                People.registerProgrammePage();
                dispose();
            }
        });

        checkVaccinationAppointmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AppointmentData appointmentData = new AppointmentData();
                Stack<String[]> apptData = appointmentData.getAppointmentData();
                for(int i = 0; i < apptData.size(); i++)
                {
                    if(!userData[0].equals(apptData.get(i)[0]) || !userData[1].equals(apptData.get(i)[1]))
                    {

                    } else {
                        if((!apptData.get(i)[8].equals("null")) && (!apptData.get(i)[9].equals("null")))
                        {
                            People.manageAppointment(i);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "Please Register Vaccination Programme First and Check Appointment Status in Join Vaccination",
                                    "Vaccine Programme", JOptionPane.WARNING_MESSAGE);
                        }
                    }

                }

            }
        });

        vaccinationStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                People.vaccinationStatus();
                dispose();
            }
        });
    }

}
