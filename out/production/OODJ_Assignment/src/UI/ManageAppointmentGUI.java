package UI;

import client.People;
import dataset.Appointment;

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

    public ManageAppointmentGUI(String title, String[] userData, Stack<String[]> apptData, Stack<String[]> vacData, Stack<String[]> vacCentreData)
    {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(managePanel);
        this.setSize(700, 450);
        vac1LB.setVisible(false);
        vac2LB.setVisible(false);

        for(int i = 0; i < apptData.size(); i++)
        {
            if(userData[0].equals(apptData.get(i)[0]) && userData[1].equals("null"))
            {
                nameLB.setText("Name: " + apptData.get(i)[2]);
                icLB.setText("Ic No: " + apptData.get(i)[0]);
            }
            if(userData[1].equals(apptData.get(i)[1]) && userData[0].equals("null"))
            {
                nameLB.setText("Name: " + apptData.get(i)[2]);
                icLB.setText("Passport No: " + apptData.get(i)[1]);
            }
        }

        for(int i = 0; i < apptData.size(); i++)
        {
            if(userData[0].equals(apptData.get(i)[0]) || userData[1].equals(apptData.get(i)[1]))
            {
                for(int j = 0; j < vacData.size(); j++)
                {
                    if(apptData.get(i)[8].equals(vacData.get(j)[0]))
                    {
                        vacTypeLB.setText("Vaccine Type: " + vacData.get(j)[1]);
                    }
                }

                for(int k = 0; k < vacCentreData.size(); k++)
                {
                    if(apptData.get(i)[9].equals(vacCentreData.get(k)[0]))
                    {
                        locationLB.setText("<html>Location: " + vacCentreData.get(k)[1] + vacCentreData.get(k)[2] + "<br/>" + vacCentreData.get(k)[3] + "</html>");
                    }
                }


                if((!apptData.get(i)[6].equals("null")) && apptData.get(i)[7].equals("null"))
                {
                    System.out.println("3");
                    vac1LB.setVisible(true);
                    vac1LB.setText("First Dose: " + apptData.get(i)[6]);
                } else {
                    System.out.println("4");
                    vac1LB.setVisible(true);
                    vac2LB.setVisible(true);
                    vac1LB.setText("First Dose: " + apptData.get(i)[6]);
                    vac2LB.setText("Second Dose: " + apptData.get(i)[7]);
                }


            }
        }
        approveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int check = JOptionPane.showConfirmDialog(null,"Acceptance vaccine confirmation", "Confirmation", JOptionPane.YES_NO_OPTION);
                if(check == 0)
                {
                    for(int i = 0; i < apptData.size(); i++)
                    {
                        if(userData[0].equals(apptData.get(i)[0]) && userData[1].equals("null") || userData[1].equals(apptData.get(i)[1]) && userData[0].equals("null"))
                        {
                            Appointment appointment = new Appointment();
                            People people = new People(userData);
                            appointment.updateConfirmation(apptData.get(i),2);
                            people.peoplePage();
                            dispose();
                        }
                    }
                }
            }
        });
        rejectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int check = JOptionPane.showConfirmDialog(null,"Do you want to reject the vaccine?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if(check == 0)
                {
                    for(int i = 0; i < apptData.size(); i++)
                    {
                        if(userData[0].equals(apptData.get(i)[0]) || userData[1].equals(apptData.get(i)[1]))
                        {
                            Appointment appointment = new Appointment();
                            People people = new People(userData);
                            appointment.updateConfirmation(apptData.get(i),1);
                            people.peoplePage();
                            dispose();
                        }
                    }
                }

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                People people = new People(userData);
                people.peoplePage();
                dispose();
            }
        });
    }
}
