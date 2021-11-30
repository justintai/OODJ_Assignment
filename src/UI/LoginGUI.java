package UI;

import client.People;
import client.User;
import dataset.UserData;
import personnel.Personnel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class LoginGUI extends JFrame {
    private JPanel mainPanel;
    private JLabel loginLabel;
    private JTextField userTF;
    private JButton registerBtn;
    private JButton loginBtn;
    private JLabel userLabel;
    private JLabel passLabel;
    private JPasswordField passTF;

    public LoginGUI(String title) {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(500, 400);

        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User.registerUser();
                dispose();
            }
        });

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String checkUser="", checkPass="";

                if(!userTF.getText().isEmpty() && !passTF.getText().isEmpty()) {
                    UserData data = new UserData();
                    String[] usrData = data.checkLogin(userTF.getText(), passTF.getText());

                    if(usrData != null){
                        if(usrData[11].equals("0")) {
                            People.peoplePage();
                            dispose();
                        }
                        else if(usrData[11].equals("1")) {
                            Personnel.personnelPage(usrData);
                            dispose();
                        }
                    }
                }
                else {
                    if(userTF.getText().isEmpty())
                        checkUser = "Username (IC No or Passport No).\n";

                    if(passTF.getText().isEmpty())
                        checkPass = "Your password.\n";

                    JOptionPane.showMessageDialog(new JFrame(), "Please enter: \n" + checkUser + checkPass,
                            "Register", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

}
