package client;

import UI.IndexGUI;
import UI.UpdateProfileGUI;

import javax.swing.*;

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
}
