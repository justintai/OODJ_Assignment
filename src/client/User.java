package client;

import UI.LoginGUI;
import UI.RegisterAppointmentGUI;
import UI.RegisterGUI;
import UI.UpdateProfileGUI;
import dataset.Appointment;

import javax.swing.*;
import java.util.Stack;

public class User {

    private static String title = "COVID-19 Vaccination System";
    private static String[] userData;
    private static Stack<String[]> apptData;

    public User() {}

    public User(String[] userData) {
        this.userData = userData;
    }

    public static void main(String[] args) {
        loginPage();
    }

    public static void registerProgrammePage()
    {
        Appointment appointment = new Appointment();
        apptData = appointment.getAppointmentData();
        RegisterAppointmentGUI registerProgramme = new RegisterAppointmentGUI(title,userData,apptData);
        registerProgramme.setLocationRelativeTo(null);
        registerProgramme.setVisible(true);

    }

    public String[] getUserData() {
        return userData;
    }

    public static void loginPage() {
        JFrame frame = new LoginGUI(title);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void registerUser(int isAdmin) {
        JFrame registerPage = new RegisterGUI(title, isAdmin);
        registerPage.setLocationRelativeTo(null);
        registerPage.setVisible(true);
    }

    public String getTitle() {
        return title;
    }

    public static void UpdateProfilePage(int isAmin)
    {
        JFrame updateProfile = new UpdateProfileGUI(title, userData, isAmin);
        updateProfile.setLocationRelativeTo(null);
        updateProfile.setVisible(true);
    }

    public static void logout() {
        System.gc();
        loginPage();
    }
}
