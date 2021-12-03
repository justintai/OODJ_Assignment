package UI;

import client.People;
import client.User;
import dataset.UserData;
import org.jdatepicker.impl.JDatePickerImpl;
import personnel.Personnel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class UpdateProfileGUI extends JFrame{
    private JTextField nameTF;
    private JTextField icTF;
    private JTextField passportTF;
    private JComboBox genderCB;
    private JTextField telTF;
    private JTextField emailTF;
    private JTextField ageTF;
    private JTextArea addressTA;
    private JComboBox stateCB;
    private JTextField passwordTF;
    private JButton backButton;
    private JButton updateButton;
    private JTextField conPasswordTF;
    private JPanel updatePanel;
    private JTextField birthdateTF;
    private int isAdmin;

    public UpdateProfileGUI( String title, String[] userData, int admin)
    {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(updatePanel);
        this.setSize(500, 600);

        icTF.setEditable(false);
        passportTF.setEditable(false);
        birthdateTF.setEditable(false);
        this.isAdmin = admin;

        if(!userData[0].equals("null")) {
            icTF.setText(userData[0]);
        }
        if(!userData[1].equals("null")) {
            passportTF.setText(userData[1]);
        }

        nameTF.setText(userData[2]);
        birthdateTF.setText(userData[3]);
        ageTF.setText(userData[5]);
        telTF.setText(userData[6]);
        emailTF.setText(userData[7]);
        addressTA.append(userData[8]);
        passwordTF.setText(userData[10]);
        conPasswordTF.setText(userData[10]);

        genderCB.addItem("Male");
        genderCB.addItem("Female");

        stateCB.addItem("Johor");
        stateCB.addItem("Melaka");
        stateCB.addItem("Kuala Lumpur");
        stateCB.addItem("Pahang");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(isAdmin == 1) {
                    Personnel.managePersonnelPage();
                }
                else {
                    People people = new People(userData);
                    people.peoplePage();
                }
                dispose();
            }
        });


        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String checkName="",
                            checkTel="",
                            checkAddress="",
                            checkEmail="",
                            checkAge="",
                            checkPassword="";

                    String name = null,
                            birthday = null,
                            gender = null,
                            address = null,
                            email = null,
                            state = null,
                            icNo = null,
                            passport = null;

                    int telNo = 0, age = 0;

                    if(!nameTF.getText().isEmpty()){
                        name = nameTF.getText();
                    }
                    else {
                        checkName = "Enter name.\n";
                    }

                    birthday = birthdateTF.getText();
                    if(!icTF.getText().isEmpty()) {
                        icNo = icTF.getText();
                        passport = "null";
                    }

                    if(!passportTF.getText().isEmpty()) {
                        passport = passportTF.getText();
                        icNo = "null";
                    }

                    gender = (String) genderCB.getSelectedItem();

                    if(!telTF.getText().isEmpty() && Integer.parseInt(telTF.getText()) != 0) {
                        telNo = Integer.parseInt(telTF.getText());
                    }
                    else{
                        checkTel = "Enter telephone number.\n";
                    }

                    if(!addressTA.getText().isEmpty()) {
                        address = addressTA.getText();
                    }
                    else{
                        checkAddress = "Enter address.\n";
                    }

                    if(!emailTF.getText().isEmpty()){
                        email = emailTF.getText();
                    }
                    else{
                        checkEmail = "Enter email.\n";
                    }

                    if(!ageTF.getText().isEmpty() && Integer.parseInt(ageTF.getText()) != 0){
                        age = Integer.parseInt(ageTF.getText());
                    }
                    else{
                        checkAge = "Enter age.\n";
                    }

                    state = (String)stateCB.getSelectedItem();

                    String pass = passwordTF.getText();
                    String conPassword = conPasswordTF.getText();
                    String password = null;
                    if(!pass.isEmpty() || !conPassword.isEmpty()){
                        if(pass.equals(conPassword)){
                            password = pass;
                        }
                        else{
                            checkPassword = "The password not match!\n";
                        }
                    }
                    else {
                        checkPassword = "Password fields cannot be empty!\n";
                    }

                    if(checkName == "" && checkTel == "" && checkAddress == ""
                        && checkEmail == "" && checkAge == "" && checkPassword == "")
                    {
                        UserData user = new UserData(name, birthday, gender,
                                address, email, icNo,
                                passport, state, password,
                                age, telNo, isAdmin);
                        String[] update = user.updateUser();

                        if(isAdmin == 1) {
                            Personnel.managePersonnelPage();
                        }
                        else {
                            People people = new People(update);
                            people.peoplePage();
                        }
                        dispose();
                    }
                    else {
                        JOptionPane.showMessageDialog(new JFrame(), "Please fulfill the requirement(s):\n" +
                                        checkName + checkTel + checkAddress +
                                        checkEmail + checkAge + checkPassword,
                                "Register", JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(new JFrame(), "Please enter integer number for tel no or age!",
                            "Register", JOptionPane.WARNING_MESSAGE);
                }
//                catch (NullPointerException nullPointerException) {
//                    JOptionPane.showMessageDialog(new JFrame(), "Please enter valid date!",
//                            "Register", JOptionPane.WARNING_MESSAGE);
//                }
            }
        });
    }
}
