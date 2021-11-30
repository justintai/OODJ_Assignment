package personnel;

import UI.ManagePeopleGUI;
import UI.ManageVaccine;
import UI.PersonnelGUI;
import client.User;

import javax.swing.*;

public class Personnel extends User {

    private static String title;

    public Personnel() {
        this.title = super.getTitle();
    }

    public static void personnelPage(String[] usrData) {
        JFrame personnelPage = new PersonnelGUI(title, usrData);
        personnelPage.setLocationRelativeTo(null);
        personnelPage.setVisible(true);
    }

    public static void managePeoplePage() {
        JFrame managePeoplePage = new ManagePeopleGUI(title);
        managePeoplePage.setLocationRelativeTo(null);
        managePeoplePage.setVisible(true);
    }

    public static void manageVaccine() {
        JFrame manageVaccinePage = new ManageVaccine(title);
        manageVaccinePage.setLocationRelativeTo(null);
        manageVaccinePage.setVisible(true);
    }
}
