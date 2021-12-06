package client;

import UI.IndexGUI;
import UI.ManageAppointmentGUI;
import UI.VaccinationStatusGUI;
import dataset.AppointmentData;
import dataset.VaccinationCentreData;
import dataset.VaccineData;

import javax.swing.*;
import java.util.Stack;

public class People extends User {

    private static String title;
    private static String[] peopleData;

    public People() {}

    public People(String[] peopleData) {
        super(peopleData);
        this.title = super.getTitle();
        this.peopleData = super.getUserData();
    }

    public void userPage() {
        JFrame indexPage = new IndexGUI(title, peopleData);
        indexPage.setLocationRelativeTo(null);
        indexPage.setVisible(true);
    }

    public static void manageAppointment(int currentUser)
    {
        ManageAppointmentGUI manageAppointmentGUI = new ManageAppointmentGUI(title,peopleData,currentUser);
        manageAppointmentGUI.setLocationRelativeTo(null);
        manageAppointmentGUI.setVisible(true);

    }

    public static void vaccinationStatus() {
        VaccinationStatusGUI vaccinationStatusGUI = new VaccinationStatusGUI(title, peopleData);
        vaccinationStatusGUI.setLocationRelativeTo(null);
        vaccinationStatusGUI.setVisible(true);
    }
}
