package personnel;

import UI.PersonnelFunctions.*;
import client.User;
import dataset.AppointmentData;
import dataset.VaccinationCentreData;
import dataset.VaccineData;

import javax.swing.*;
import java.util.Stack;

public class Personnel extends User {

    private static String title;
    private static String[] personnelData;

    public Personnel(String[] usrData) {
        super(usrData);
        this.title = super.getTitle();
        this.personnelData = super.getUserData();
    }

    public Personnel(String[] usrData, int isAdmin) {
        super(usrData);
        this.title = super.getTitle();
    }

    public static void personnelPage() {
        JFrame personnelPage = new PersonnelGUI(title, personnelData);
        personnelPage.setLocationRelativeTo(null);
        personnelPage.setVisible(true);
    }

//    Manage People
    public static void managePeoplePage() {
        JFrame managePeoplePage = new ManagePeopleGUI(title);
        managePeoplePage.setLocationRelativeTo(null);
        managePeoplePage.setVisible(true);
    }

//    Manage Vaccine
    public static void manageVaccinePage() {
        JFrame manageVaccinePage = new ManageVaccineGUI(title);
        manageVaccinePage.setLocationRelativeTo(null);
        manageVaccinePage.setVisible(true);
    }

    public static void addVaccinePage(int editLine, int isEdit) {
        JFrame addVaccinePage = new AddVaccineGUI(title, editLine, isEdit);
        addVaccinePage.setLocationRelativeTo(null);
        addVaccinePage.setVisible(true);
    }

    public static void addVaccine(String code, String name, String manufacture, int stock) {
        VaccineData vaccine = new VaccineData(code, name, manufacture, stock);
        vaccine.writeAll();
    }

    public static void updateVaccine(Stack<String[]> data) {
        VaccineData vaccine = new VaccineData();
        vaccine.updateVaccineData(data);
    }

//    Manage Vaccination Centre
    public static void manageVaccinationCentre() {
        JFrame manageCentrePage = new ManageCentreGUI(title);
        manageCentrePage.setLocationRelativeTo(null);
        manageCentrePage.setVisible(true);
    }

    public static void addCentrePage(int editLine, int isEdit) {
        JFrame addCentrePage = new AddCentreGUI(title, editLine, isEdit);
        addCentrePage.setLocationRelativeTo(null);
        addCentrePage.setVisible(true);
    }

    public static void addCentre(String centreCode, String name, String state,
                                 String address, String vaccineCode, int maxStock,
                                 int stock, Stack<String[]> vacData) {
        VaccinationCentreData vaccinationCentreData = new VaccinationCentreData(centreCode, name, state,
                address, vaccineCode, maxStock, stock, vacData);
        vaccinationCentreData.writeAll();
    }

    public static void updateCentre(Stack<String[]> data) {
        VaccinationCentreData vaccinationCentreData = new VaccinationCentreData();
        vaccinationCentreData.updateVaccinationCentreData(data);
    }

//    Manage Personnel
    public static void managePersonnelPage() {
        JFrame managePersonnel = new ManagePersonnelGUI(title);
        managePersonnel.setLocationRelativeTo(null);
        managePersonnel.setVisible(true);
    }

//    Manage Appointment
    public static void manageAppointmentPage() {
        JFrame manageAppointment = new ManageAppointmentGUI(title);
        manageAppointment.setLocationRelativeTo(null);
        manageAppointment.setVisible(true);
    }

    public static void editAppointmentPage(int editLine) {
        JFrame editAppointment = new EditAppointmentGUI(title, editLine);
        editAppointment.setLocationRelativeTo(null);
        editAppointment.setVisible(true);
    }

    public static void updateAppointment(Stack<String[]> data) {
        AppointmentData appointmentData = new AppointmentData();
        appointmentData.updateAppointment(data);
    }
}
