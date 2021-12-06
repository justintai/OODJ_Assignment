package UI;

import client.User;
import dataset.UserData;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import personnel.Personnel;
import sun.nio.ch.Util;

import javax.swing.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Stack;

import static java.lang.System.exit;
import static java.lang.System.setIn;

public class RegisterGUI extends JFrame {
    private JPanel registerPanel;
    private JCheckBox foreignerCB;
    private JComboBox stateComboB;
    private JTextField nameTF;
    private JTextField icTF;
    private JTextField passportTF;
    private JTextField telTF;
    private JTextField emailTF;
    private JTextField ageTF;
    private JTextArea addressTF;
    private JButton registerBtn;
    private JButton backBtn;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JComboBox genderComboB;
    private JDatePickerImpl datePicker;
    private JDatePanelImpl datePanel;
    private int isAdmin;

    public RegisterGUI(String title, int admin) {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(registerPanel);
        this.setSize(500, 600);

        passportTF.setEditable(false);
        int adminDetect = (admin == 2) ? 0 : admin;
        this.isAdmin = adminDetect;

        genderComboB.addItem("Male");
        genderComboB.addItem("Female");

        stateComboB.addItem("Johor");
        stateComboB.addItem("Melaka");
        stateComboB.addItem("Kuala Lumpur");
        stateComboB.addItem("Pahang");

        // Check for foreigner
        foreignerCB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(foreignerCB.isSelected()) {
                    passportTF.setEditable(true);
                    icTF.setEditable(false);
                }
                else {
                    passportTF.setEditable(false);
                    icTF.setEditable(true);
                }
            }
        });

        // back to login page
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(admin == 1) {
                    Personnel.managePersonnelPage();
                }
                else if(admin == 2) {
                    Personnel.managePeoplePage();
                }
                else {
                    User.loginPage();
                }
                dispose();
            }
        });

        // register
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String checkName="",
                            checkBirthday="",
                            checkTel="",
                            checkAddress="",
                            checkEmail="",
                            checkAge="",
                            checkIc="",
                            checkPassport="",
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

                    Date d = (Date)datePicker.getModel().getValue();
                    if(d != null)
                    {
                        try {
                            birthday = new SimpleDateFormat("dd-MM-yyyy").format(d);
                        }
                        catch (NullPointerException nullPointerException) {
                            checkBirthday = "Enter valid date!\n";
                        }
                    }
                    else{
                        checkBirthday = "Enter birthday.\n";
                    }

                    gender = (String) genderComboB.getSelectedItem();

                    if(!telTF.getText().isEmpty() && Integer.parseInt(telTF.getText()) != 0) {
                        telNo = Integer.parseInt(telTF.getText());
                    }
                    else{
                        checkTel = "Enter telephone number.\n";
                    }

                    if(!addressTF.getText().isEmpty()) {
                        address = addressTF.getText();
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

                    UserData userData = new UserData();
                    Stack<String[]> data = userData.getUserData();
                    if(foreignerCB.isSelected()) {
                        icNo = null;
                        if(!passportTF.getText().isEmpty()){
                            for(int i=0; i<data.size(); i++) {
                                if (!passportTF.getText().equals(data.get(i)[1])) {
                                    passport = passportTF.getText();
                                } else {
                                    checkPassport = "This passport already registered.";
                                }
                            }
                        }
                        else{
                            checkPassport = "Enter passport number.\n";
                        }
                    }
                    else {
                        passport = null;
                        if(!icTF.getText().isEmpty()){
                            for(int i=0; i<data.size(); i++) {
                                if (!icTF.getText().equals(data.get(i)[0])) {
                                    icNo = icTF.getText();
                                } else {
                                    checkPassport = "This IC already registered.\n";
                                }
                            }
                        }
                        else {
                            checkIc = "Enter IC number.\n";
                        }
                    }

                    state = (String)stateComboB.getSelectedItem();

                    String pass = passwordField1.getText();
                    String conPassword = passwordField2.getText();
                    String password = null;
                    if(!pass.isEmpty() || !conPassword.isEmpty()){
                        if(pass.length() >= 8){
                            if(pass.equals(conPassword)){
                                password = pass;
                            }
                            else{
                                checkPassword = "The password not match!\n";
                            }
                        }
                        else {
                            checkPassword = "Password need more than 8 characters.";
                        }
                    }
                    else {
                        checkPassword = "Password fields cannot be empty!\n";
                    }

                    if(checkName == "" && checkBirthday == "" && checkTel == "" &&
                            checkAddress == "" && checkEmail == "" && checkAge == "" &&
                            checkIc == "" && checkPassport=="" && checkPassword == ""){
                        UserData user = new UserData(name, birthday, gender,
                                address, email, icNo, passport,
                                state, password, age, telNo, isAdmin);
                        user.writeAll();

                        JOptionPane.showMessageDialog(new JFrame(), "The user had been registered.",
                                "Register", JOptionPane.INFORMATION_MESSAGE);

                        if(admin == 1) {
                            Personnel.managePersonnelPage();
                        }
                        else if(admin == 2) {
                            Personnel.managePeoplePage();
                        }
                        else {
                            User.loginPage();
                        }
                        dispose();
                    }
                    else {
                        JOptionPane.showMessageDialog(new JFrame(), "Please fulfill the requirement(s):\n" +
                                        checkName + checkBirthday + checkTel +
                                        checkAddress + checkEmail + checkAge +
                                        checkIc  + checkPassport + checkPassword,
                                "Register", JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(new JFrame(), "Please enter integer number for tel no or age!",
                            "Register", JOptionPane.WARNING_MESSAGE);
                }
                catch (NullPointerException nullPointerException) {
                    JOptionPane.showMessageDialog(new JFrame(), "Please enter valid date!",
                            "Register", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    // for non-palette component
    private void createUIComponents() {
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
    }

    // format date picker date
    public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

        private String datePattern = "dd-MM-yyyy";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }

            return "";
        }
    }
}
