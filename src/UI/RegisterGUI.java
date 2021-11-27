package UI;

import javax.swing.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class RegisterGUI extends JFrame {
    private JPanel registerPanel;
    private JLabel registerTitle;
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
    private JFormattedTextField birthdayTF;
    private JComboBox genderComboB;

    public RegisterGUI(String title) {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(registerPanel);
        this.setSize(500, 600);

        DateFormat format = new SimpleDateFormat("dd/MMMM/yyyy");
        birthdayTF = new JFormattedTextField(format);
        passportTF.setEditable(false);

        genderComboB.addItem("Male");
        genderComboB.addItem("Female");

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

        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTF.getText();
                String birthday = birthdayTF.getText();
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
                String password = passwordField1.getText();
                String conPassword = passwordField2.getText();

                System.out.println(name +"\n"+ icNo+"\n"+passport+"\n"+ birthday+"\n"+gender+"\n"+ telNo+"\n"+  email+"\n"+ age
                        +"\n"+address+"\n"+state+"\n"+password+"\n"+conPassword);
            }
        });

    }

}
