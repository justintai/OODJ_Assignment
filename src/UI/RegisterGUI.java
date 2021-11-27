package UI;

import dataset.UserData;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
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

    public RegisterGUI(String title) {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(registerPanel);
        this.setSize(500, 600);

        passportTF.setEditable(false);

        genderComboB.addItem("Male");
        genderComboB.addItem("Female");

        stateComboB.addItem("Johor");
        stateComboB.addItem("Melaka");
        stateComboB.addItem("Kuala Lumpur");
        stateComboB.addItem("Pahang");

        // back to login page
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame mainPage = new LoginGUI(title);
                mainPage.setVisible(true);

                dispose();
            }
        });

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

        // register
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameTF.getText();
                    Date d = (Date)datePicker.getModel().getValue();
                    String birthday = new SimpleDateFormat("dd-MM-yyyy").format(d);
                    String gender = (String) genderComboB.getSelectedItem();
                    int telNo = Integer.parseInt(telTF.getText());
                    String address = addressTF.getText();
                    String email = emailTF.getText();
                    int age = Integer.parseInt(ageTF.getText());

                    String icNo, passport;
                    if(foreignerCB.isSelected()) {
                        icNo = null;
                        passport = passportTF.getText();
                    }
                    else {
                        icNo = icTF.getText();
                        passport = null;
                    }

                    String state = (String)stateComboB.getSelectedItem();
                    String pass = passwordField1.getText();
                    String conPassword = passwordField2.getText();
                    String password = null;
                    if(!pass.isEmpty() || !conPassword.isEmpty()){
                        if(pass.equals(conPassword)){
                            password = pass;
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(new JFrame(), "Password fields cannot be empty!",
                                "Register", JOptionPane.ERROR_MESSAGE);
                    }

                    UserData user = new UserData(name, birthday, gender, address, email, icNo, passport, state, password, age, telNo);
                    user.insertUserData();
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