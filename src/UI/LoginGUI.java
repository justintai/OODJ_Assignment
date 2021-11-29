package UI;

import dataset.UserData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {
    private JPanel mainPanel;
    private JLabel loginLabel;
    private JTextField userTF;
    private JTextField passTF;
    private JButton registerBtn;
    private JButton loginBtn;
    private JLabel userLabel;
    private JLabel passLabel;

    public LoginGUI(String title) {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setSize(500, 400);

        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame registerPage = new RegisterGUI(title);
                registerPage.setLocationRelativeTo(null);
                registerPage.setVisible(true);

                dispose();
            }
        });

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String checkUser="", checkPass="";

                if(!userTF.getText().isEmpty() && !passTF.getText().isEmpty()) {
                    UserData data = new UserData();
                    data.checkLogin(userTF.getText(), passTF.getText());
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
