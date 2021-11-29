package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Index extends JFrame{
    private JPanel indexPanel;
    private JButton joinVaccinationButton;
    private JButton checkVaccinationAppointmentButton;
    private JButton vaccinationStatusButton;

    public Index(String title) {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(indexPanel);
        this.setSize(500, 400);

        joinVaccinationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                
            }
        });
    }

}
