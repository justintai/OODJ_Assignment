package client;

import UI.LoginGUI;
import UI.RegisterGUI;

import javax.swing.*;

public class User {

    private static String title = "COVID-19 Vaccination System";

    public static void main(String[] args) {
        loginPage();
    }

    public static void loginPage() {
        JFrame frame = new LoginGUI(title);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void registerUser() {
        JFrame registerPage = new RegisterGUI(title);
        registerPage.setLocationRelativeTo(null);
        registerPage.setVisible(true);
    }

    public String getTitle() {
        return title;
    }

    public static void logout() {
        System.gc();
        loginPage();
    }
}
