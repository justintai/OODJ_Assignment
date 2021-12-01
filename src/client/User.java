package client;

import UI.LoginGUI;
import UI.RegisterGUI;
import UI.UpdateProfileGUI;

import javax.swing.*;

public class User {

    private static String title = "COVID-19 Vaccination System";
    private static String[] userData;

    public User() {}

    public User(String[] userData) {
        this.userData = userData;
    }

    public static void main(String[] args) {
        loginPage();
    }

    public String[] getUserData() {
        return userData;
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

    public static void UpdateProfilePage()
    {
        JFrame updateProfile = new UpdateProfileGUI(title, userData);
        updateProfile.setLocationRelativeTo(null);
        updateProfile.setVisible(true);
    }

    public static void logout() {
        System.gc();
        loginPage();
    }
}
