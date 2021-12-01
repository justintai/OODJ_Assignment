package client;

import UI.IndexGUI;
import UI.UpdateProfileGUI;

import javax.swing.*;

public class People extends User {

    private static String title;
    private static String[] peopleData;

    public People(String[] peopleData) {
        this.title = super.getTitle();
        this.peopleData = peopleData;
    }

    public static void peoplePage() {
        JFrame indexPage = new IndexGUI(title, peopleData);
        indexPage.setLocationRelativeTo(null);
        indexPage.setVisible(true);
    }
    public static void UpdateProfilePage()
    {
        JFrame updateProfile = new UpdateProfileGUI(title, peopleData);
        updateProfile.setLocationRelativeTo(null);
        updateProfile.setVisible(true);
    }
}
