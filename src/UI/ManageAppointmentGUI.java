package UI;

import client.People;
import dataset.AppointmentData;
import dataset.VaccinationCentreData;
import dataset.VaccineData;
import personnel.Personnel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class ManageAppointmentGUI extends JFrame {


    private JPanel managePanel;
    private JLabel nameLB;
    private JLabel icLB;
    private JLabel vac1LB;
    private JLabel vac2LB;
    private JLabel vacTypeLB;
    private JLabel locationLB;
    private JButton approveButton;
    private JButton rejectButton;
    private JButton backButton;
    private Stack<String[]> allData;
    private Stack<String[]> centreData;
    private Stack<String[]> vaccineData;

    public ManageAppointmentGUI(String title, String[] userData, int currentUser)
    {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(managePanel);
        this.setSize(700, 450);
        vac1LB.setVisible(false);
        vac2LB.setVisible(false);

        AppointmentData appointmentData = new AppointmentData();
        allData = appointmentData.getAppointmentData();
        VaccinationCentreData vacCentreData = new VaccinationCentreData();
        centreData = vacCentreData.getCentreData();
        VaccineData vacData = new VaccineData();
        vaccineData = vacData.getVaccineData();

        for(int i = 0; i < allData.size(); i++)
        {
            if(userData[0].equals(allData.get(i)[0]) && userData[1].equals("null"))
            {
                nameLB.setText("Name: " + allData.get(i)[2]);
                icLB.setText("Ic No: " + allData.get(i)[0]);
            }
            if(userData[1].equals(allData.get(i)[1]) && userData[0].equals("null"))
            {
                nameLB.setText("Name: " + allData.get(i)[2]);
                icLB.setText("Passport No: " + allData.get(i)[1]);
            }
        }

        for(int i = 0; i < allData.size(); i++)
        {
            if(userData[0].equals(allData.get(i)[0]) || userData[1].equals(allData.get(i)[1]))
            {
                for(int j = 0; j < allData.size(); j++)
                {
                    if(allData.get(i)[8].equals(allData.get(j)[0]))
                    {
                        vacTypeLB.setText("Vaccine Type: " + vaccineData.get(j)[1]);
                    }
                }

                for(int k = 0; k < centreData.size(); k++)
                {
                    if(allData.get(i)[9].equals(centreData.get(k)[0]))
                    {
                        locationLB.setText("<html>Location: " + centreData.get(k)[1] + centreData.get(k)[2] + "<br/>" + centreData.get(k)[3] + "</html>");
                    }
                }


                if((!allData.get(i)[6].equals("null")) && allData.get(i)[7].equals("null"))
                {
                    vac1LB.setVisible(true);
                    vac1LB.setText("First Dose: " + allData.get(i)[6]);
                } else {
                    vac1LB.setVisible(true);
                    vac2LB.setVisible(true);
                    vac1LB.setText("First Dose: " + allData.get(i)[6]);
                    vac2LB.setText("Second Dose: " + allData.get(i)[7]);
                }


            }
        }
        approveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                for(int i = 0; i < allData.size(); i++)
                {
                    if(!userData[0].equals(allData.get(i)[0]) || !userData[1].equals(allData.get(i)[1]))
                    {

                    } else {
                        if(allData.get(i)[12].equals("0"))
                        {
                            int check = JOptionPane.showConfirmDialog(null,"Acceptance vaccine confirmation", "Confirmation", JOptionPane.YES_NO_OPTION);
                            if(check == 0 && currentUser != -1)
                            {
                                allData.get(currentUser)[12] = "1";
                                People people = new People();
                                people.updateAppointment(allData);
                                JOptionPane.showMessageDialog(new JFrame(), "You had confirmed the vaccination appointment.",
                                        "Appointment", JOptionPane.INFORMATION_MESSAGE);
                                people.userPage();
                                dispose();
                            }
                        }
                        else if(allData.get(i)[12].equals("1")){
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "You Have Already Agreed to Join the Vaccination Programme !",
                                    "Vaccine Programme", JOptionPane.WARNING_MESSAGE);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "You Have Already Rejected the Vaccination Programme !",
                                    "Vaccine Programme", JOptionPane.WARNING_MESSAGE);
                        }
                    }

                }

            }
        });
        rejectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                for(int i = 0; i < allData.size(); i++)
                {
                    if(!userData[0].equals(allData.get(i)[0]) || !userData[1].equals(allData.get(i)[1]))
                    {

                    }
                    else {
                        if(allData.get(i)[12].equals("0"))
                        {
                            int check = JOptionPane.showConfirmDialog(null,
                                    "Do you want to reject the vaccine?", "Confirmation",
                                    JOptionPane.YES_NO_OPTION);
                            if(check == 0 && currentUser != -1)
                            {
                                allData.get(currentUser)[12] = "2";
                                People people = new People();
                                people.updateAppointment(allData);
                                JOptionPane.showMessageDialog(new JFrame(), "You had rejected the vaccination appointment.",
                                        "Appointment", JOptionPane.INFORMATION_MESSAGE);
                                people.userPage();
                                dispose();
                            }
                        }
                        else if(allData.get(i)[12].equals("1")){
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "You Have Already Agreed to Join the Vaccination Programme !",
                                    "Vaccine Programme", JOptionPane.WARNING_MESSAGE);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(new JFrame(),
                                    "You Have Already Rejected the Vaccination Programme !",
                                    "Vaccine Programme", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                People people = new People(userData);
                people.userPage();
                dispose();
            }
        });
    }
}
