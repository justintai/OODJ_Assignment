package UI;

import client.People;
import dataset.AppointmentData;

import javax.swing.*;
import java.awt.event.*;
import java.util.Stack;

public class RegisterAppointmentGUI extends JFrame{
    private JTextField nameTF;
    private JTextField icTF;
    private JTextField passportTF;
    private JLabel Name;
    private JCheckBox confirmaddCB;
    private JButton applyButton;
    private JButton backButton;
    private JTextArea addressTA;
    private JPanel registerPanel;
    private JComboBox stateCB;
    private JTextField telTF;
    private JLabel successLB;

    public RegisterAppointmentGUI(String title, String [] userData, Stack<String[]> apptData)
    {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(registerPanel);
        this.setSize(700, 450);
        successLB.setVisible(false);
        //read file check existence
        for(int i = 0; i < apptData.size(); i++)
        {
            if(userData[0].equals(apptData.get(i)[0]) || userData[1].equals(apptData.get(i)[1]))
            {
                applyButton.setText("Update");
                addressTA.setText(apptData.get(i)[4]);
                stateCB.setSelectedItem(apptData.get(i)[5]);
            }
        }

        for(int i = 0; i < apptData.size(); i++) {
            if (userData[0].equals(apptData.get(i)[0]) || userData[1].equals(apptData.get(i)[1])) {
                if (apptData.get(i)[8].equals("null") || apptData.get(i)[9].equals("null")) {
                    successLB.setVisible(false);
                }
                else{
                    successLB.setVisible(true);
                }
            }
        }

        nameTF.setEditable(false);
        icTF.setEditable(false);
        passportTF.setEditable(false);

        if(userData[0].equals("null"))
        {
            icTF.setText("");
            passportTF.setText(userData[1]);
        }
        else if(userData[1].equals("null"))
        {
            passportTF.setText("");
            icTF.setText(userData[0]);
        }
        nameTF.setText(userData[2]);
        telTF.setText(userData[6]);


        stateCB.addItem("Johor");
        stateCB.addItem("Melaka");
        stateCB.addItem("Kuala Lumpur");
        stateCB.addItem("Pahang");

        confirmaddCB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if(confirmaddCB.isSelected())
                {
                    addressTA.setEnabled(false);
                    addressTA.setText(userData[8]);
                    stateCB.setEnabled(false);
                    stateCB.setSelectedItem(userData[9]);
                }
                else
                {
                    addressTA.setEnabled(true);
                    addressTA.setText("");
                    stateCB.setEnabled(true);
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

        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                People people = new People(userData);
                int found  = 0;
                for(int i = 0; i < apptData.size(); i++) {
                    if (userData[0].equals(apptData.get(i)[0]) || userData[1].equals(apptData.get(i)[1])) {

                        if (!apptData.get(i)[8].equals("null") || !apptData.get(i)[9].equals("null")) {
                            JOptionPane.showConfirmDialog(null, "You have succesfully join the vaccination programme, please proceed to vaccination appointment", "Vaccination Programme Approve!", JOptionPane.DEFAULT_OPTION);
                            people.peoplePage();
                            found = 1;
                            dispose();
                        }
                    }
                }

                if(found != 1)
                {
                    try {
                        String address = "null", name = "null", icNo = "null",
                                passport = "null", state = "null", vac1 = "null",
                                vac2 = "null", vaccineCode = "null", centreCode = "null";
                        int isDone1 = 0, isDone2 = 0,  isConfirm = 0, telno = 0, complete = 0;
                        boolean checkaddress = true, checkTel = true;

                        name = nameTF.getText();
                        state = (String) stateCB.getSelectedItem();
                        if (!icTF.getText().isEmpty()) {
                            icNo = icTF.getText();
                        } else {
                            passport = passportTF.getText();
                        }

                        if (!telTF.getText().isEmpty()) {
                            telno = Integer.parseInt(telTF.getText());
                        } else {
                            checkTel = false;
                        }

                        if (!addressTA.getText().isEmpty()) {
                            address = addressTA.getText();
                        } else {
                            checkaddress = false;
                        }

                        if (checkaddress == true && checkTel == true) {
                            int confirm = JOptionPane.showConfirmDialog(null,"Do you want to proceed?", "Confirmation", JOptionPane.YES_NO_OPTION);
                            if(confirm == 0)
                            {
                                AppointmentData appointmentData = new AppointmentData(passport, address, state, icNo,
                                        telno, name, vac1, vac2, vaccineCode,
                                        centreCode, isDone1, isDone2,  isConfirm);

                                for(int i = 0; i < apptData.size(); i++)
                                {
                                    if(userData[0].equals(apptData.get(i)[0]) || userData[1].equals(apptData.get(i)[1]))
                                    {
                                        complete = 1;
                                        appointmentData.updateAppointment();
                                    }
                                }

                                if(complete == 0)
                                {
                                    appointmentData.writeAllAppointment();
                                }

                                people.peoplePage();
                                dispose();
                            }

                        } else {
                            JOptionPane.showMessageDialog(new JFrame(), "PLease fill in your address, Telno, and confirm your address!");
                        }
                    }
                    catch (NumberFormatException numberFormatException)
                    {
                        JOptionPane.showMessageDialog(new JFrame(), "Please enter integer number!",
                                "Appointment", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
    }

}
