package client;

import UI.LoginGUI;
import UI.RegisterAppointmentGUI;
import UI.RegisterGUI;
import UI.UpdateProfileGUI;
import dataset.AppointmentData;

import javax.swing.*;
import java.util.Stack;

public abstract class User {

    private static String title = "COVID-19 Vaccination System";
    private static String[] userData;

    public User(){}

    public User(String[] userData) {
        this.userData = userData;
    }

    public abstract void userPage();

    public static void main(String[] args) {
        loginPage();
    }

    public static void registerProgrammePage()
    {
        AppointmentData appointmentData = new AppointmentData();
        Stack<String[]> apptData = appointmentData.getAppointmentData();
        RegisterAppointmentGUI registerProgramme = new RegisterAppointmentGUI(title,userData, apptData);
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

    public static void UpdateProfilePage(int isAdmin)
    {
        JFrame updateProfile = new UpdateProfileGUI(title, userData, isAdmin);
        updateProfile.setLocationRelativeTo(null);
        updateProfile.setVisible(true);
    }

    public static void updateAppointment(Stack<String[]> data) {
        AppointmentData appointmentData = new AppointmentData();
        appointmentData.updateAppointment(data);
    }

    public static void logout() {
        System.gc();
        loginPage();
    }
}
