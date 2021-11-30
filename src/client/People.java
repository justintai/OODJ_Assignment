package client;

import UI.IndexGUI;
import UI.UpdateProfileGUI;

import javax.swing.*;

public class People extends User {

    private static String title;

    public People() {
        this.title = super.getTitle();
    }

    public static void peoplePage(String[] peopleData) {
        JFrame indexPage = new IndexGUI(title, peopleData);
        indexPage.setLocationRelativeTo(null);
        indexPage.setVisible(true);
    }
    public static void UpdateProfilePage(String[] updateData)
    {
        JFrame updateProfile = new UpdateProfileGUI(title, updateData);
        updateProfile.setLocationRelativeTo(null);
        updateProfile.setVisible(true);
    }
}
