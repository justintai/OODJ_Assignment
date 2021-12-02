package UI;

import client.People;
import dataset.Appointment;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
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

    public RegisterAppointmentGUI(String title, String [] userData, Stack<String[]> apptData)
    {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(registerPanel);
        this.setSize(700, 450);

        //read file check existence
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
        addressTA.setText(userData[8]);

        stateCB.addItem("Johor");
        stateCB.addItem("Melaka");
        stateCB.addItem("Kuala Lumpur");
        stateCB.addItem("Pahang");

        confirmaddCB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if(confirmaddCB.isSelected())
                {
                    addressTA.setEditable(false);
                }
                else
                {
                    addressTA.setEditable(true);
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
                try {
                    String address = null, name = null, icNo = null, passport = null, state = null, telno = null;
                    Date vac1 = null, vac2 = null;
                    char vacccineCode = 0, centreCode = 0;
                    int isDone1 = 0, isDone2 = 0, isRegister = 0, isConfirm = 0;
                    boolean checkaddress = true, checkTel = true;

//                    if(apptData.get(i)[13] == 1)
//                    {
//
//                    }
                    name = nameTF.getText();
                    state = (String) stateCB.getSelectedItem();
                    if (!icTF.getText().isEmpty()) {
                        icNo = icTF.getText();
                    } else {
                        passport = passportTF.getText();
                    }

                    if (!telTF.getText().isEmpty()) {
                        telno = telTF.getText();
                    } else {
                        checkTel = false;
                    }

                    if (!addressTA.getText().isEmpty()) {
                        address = addressTA.getText();
                    } else {
                        checkaddress = false;
                    }

                    if (checkaddress == true && checkTel == true && confirmaddCB.isSelected()) {
                        isRegister = 1;
                        JOptionPane.showConfirmDialog(null,"Do you want to proceed?", "Make your decision...", JOptionPane.YES_NO_OPTION);
                        Appointment appointment = new Appointment(passport, address, state, icNo, telno, name, vac1, vac2, vacccineCode, centreCode, isDone1, isDone2, isRegister, isConfirm);
                        appointment.writeAllAppointment();

                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "PLease fill in your address, Telno, and confirm your address!");
                    }
                }
                catch (NumberFormatException numberFormatException)
                {
                    JOptionPane.showMessageDialog(new JFrame(), "Please enter integer number!",
                            "Register", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

}
