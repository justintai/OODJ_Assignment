package client;

import UI.LoginGUI;

import javax.swing.*;
import java.util.Stack;

public class User {

    public static void main(String[] args) {
        JFrame frame = new LoginGUI("COVID-19 Vaccination System");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
