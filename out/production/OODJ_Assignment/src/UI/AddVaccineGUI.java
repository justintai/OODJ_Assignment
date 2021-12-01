package UI;

import personnel.Personnel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddVaccineGUI extends JFrame {
    private JPanel addVaccinePanel;
    private JButton backButton;
    private JButton saveButton;
    private JButton deleteButton;
    private JTextField vacCodeTF;
    private JTextField vacNameTF;
    private JTextField manufactureTF;
    private JTextField stockTF;

    public AddVaccineGUI(String title, int isEdit) {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(addVaccinePanel);
        this.setSize(500, 500);

        stockTF.setText("0");

        Boolean deleteVisible = (isEdit == 0) ? false : true;
        deleteButton.setVisible(deleteVisible);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Personnel.personnelPage();
                dispose();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String code = vacCodeTF.getText();
                    String name = vacNameTF.getName();
                    String manufacture = manufactureTF.getText();
                    int stock = Integer.parseInt(stockTF.getText());
                    Personnel.addVaccine(code, name, manufacture, stock);

                    Personnel.manageVaccinePage();
                    dispose();
                }
                catch (NumberFormatException z) {
                    JOptionPane.showMessageDialog(null, "Enter integer number for stock.",
                            "Add Vaccine", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
