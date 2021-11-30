package client;

import UI.Index;

import javax.swing.*;

public class People extends User {

    private static String title;

    public People() {
        this.title = super.getTitle();
    }

    public static void peoplePage() {
        JFrame indexPage = new Index(title);
        indexPage.setLocationRelativeTo(null);
        indexPage.setVisible(true);
    }
}
