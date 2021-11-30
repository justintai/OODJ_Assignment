package client;

import UI.LoginGUI;
<<<<<<< Updated upstream
=======
import UI.RegisterGUI;
>>>>>>> Stashed changes

import javax.swing.*;

public class User {

    private static String title = "COVID-19 Vaccination System";

    public static void main(String[] args) {
        JFrame frame = new LoginGUI(title);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
