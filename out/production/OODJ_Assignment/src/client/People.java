package client;

import UI.IndexGUI;
import UI.RegisterAppointmentGUI;
import dataset.Appointment;

import javax.swing.*;
import java.util.Stack;

public class People extends User {

    private static String title;
    private static String[] peopleData;
    private static Stack<String[]> apptData;


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

    public static void registerProgrammePage()
    {
        Appointment appointment = new Appointment();
        apptData = appointment.getAppointmentData();
        RegisterAppointmentGUI registerProgramme = new RegisterAppointmentGUI(title,peopleData,apptData);
        registerProgramme.setLocationRelativeTo(null);
        registerProgramme.setVisible(true);

    }
}
