package UI;

import javax.swing.*;

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
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
    }

    public static void main(String[] args) {
        JFrame frame = new LoginGUI("COVID-19 Vaccination System");
        frame.setVisible(true);
    }
}
