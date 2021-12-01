package personnel;

import UI.AddVaccineGUI;
import UI.ManagePeopleGUI;
import UI.ManageVaccineGUI;
import UI.PersonnelGUI;
import client.User;
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
}
