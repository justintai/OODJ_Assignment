package client;

import UI.IndexGUI;
import UI.ManageAppointmentGUI;
import dataset.AppointmentData;
import dataset.VaccinationCentreData;
import dataset.VaccineData;

import javax.swing.*;
import java.util.Stack;

public class People extends User {

    private static String title;
    private static String[] peopleData;


    public People(String[] peopleData) {
        super(peopleData);
        this.title = super.getTitle();
        this.peopleData = super.getUserData();
    }

    public static void peoplePage() {
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
}
