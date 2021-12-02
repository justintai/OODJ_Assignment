package personnel;

import UI.PersonnelGUIs.AddVaccineGUI;
import UI.PersonnelGUIs.ManagePeopleGUI;
import UI.PersonnelGUIs.ManageVaccineGUI;
import UI.PersonnelGUIs.AddCentreGUI;
import UI.PersonnelGUIs.ManageCentreGUI;
import UI.PersonnelGUIs.PersonnelGUI;
import client.User;
import dataset.VaccinationCentreData;
import dataset.VaccineData;

import javax.swing.*;
import java.util.Stack;

public class Personnel extends User {

    private static String title;
    private static String[] usrData;

    public Personnel(String[] usrData) {
        this.title = super.getTitle();
        this.usrData = usrData;
    }

    public static void personnelPage() {
        JFrame personnelPage = new PersonnelGUI(title, usrData);
        personnelPage.setLocationRelativeTo(null);
        personnelPage.setVisible(true);
    }

    public static void managePeoplePage() {
        JFrame managePeoplePage = new ManagePeopleGUI(title);
        managePeoplePage.setLocationRelativeTo(null);
        managePeoplePage.setVisible(true);
    }

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
}
