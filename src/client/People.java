package client;

import UI.IndexGUI;
import UI.ManageAppointmentGUI;
import UI.RegisterAppointmentGUI;
import dataset.Appointment;
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

    public static void manageAppointment()
    {
        Appointment appointment = new Appointment();
        VaccineData vaccineData = new VaccineData();
        VaccinationCentreData vaccinationCentreData = new VaccinationCentreData();
        Stack<String[]> apptData = appointment.getAppointmentData();
        Stack<String[]> vacData = vaccineData.getVaccineData();
        Stack<String[]> vacCentreData = vaccinationCentreData.getCentreData();
        ManageAppointmentGUI manageAppointmentGUI = new ManageAppointmentGUI(title,peopleData,apptData,vacData,vacCentreData);
        manageAppointmentGUI.setLocationRelativeTo(null);
        manageAppointmentGUI.setVisible(true);

    }
}
