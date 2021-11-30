package personnel;

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
}
