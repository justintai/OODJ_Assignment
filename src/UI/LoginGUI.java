package UI;

import javax.swing.*;
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
                registerPage.setVisible(true);

                dispose();
            }
        });

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    /*public static void main(String[] args) {
        JFrame frame = new LoginGUI("COVID-19 Vaccination System");
        frame.setVisible(true);
    }*/
}
