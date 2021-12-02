package UI.PersonnelGUIs;

import client.Global.Global;
import client.Dataset.VaccineData;
import client.Personnel.Personnel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Stack;

public class AddVaccineGUI extends JFrame {
    private JPanel addVaccinePanel;
    private JButton backButton;
    private JButton saveButton;
    private JButton deleteButton;
    private JTextField vacCodeTF;
    private JTextField vacNameTF;
    private JTextField manufactureTF;
    private JTextField stockTF;
    private JLabel vaccineTitle;
    private Stack<String[]> allData;
    private int editLine;

    public AddVaccineGUI(String title, int editLine, int isEdit) {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(addVaccinePanel);
        this.setSize(500, 500);

        this.editLine = editLine;
        stockTF.setText("0");

        Boolean deleteVisible = (isEdit == 0) ? false : true;
        deleteButton.setVisible(deleteVisible);

        Boolean editable = (isEdit == 0) ? true : false;
        vacCodeTF.setEditable(editable);

        String titleLabel = (isEdit == 0) ? "Add Vaccine" : "Edit Vaccine";
        vaccineTitle.setText(titleLabel);

        String saveTitle = (isEdit == 0) ? "Save" : "Update";
        saveButton.setText(saveTitle);

        File file = new File(Global.vaccineFile);
        if(file.exists()) {
            VaccineData vaccineData = new VaccineData();
            allData = vaccineData.getVaccineData();
        }

        if(editLine != -1) {
            if(allData != null) {
                vacCodeTF.setText(allData.get(editLine)[0]);
                vacNameTF.setText(allData.get(editLine)[1]);
                manufactureTF.setText(allData.get(editLine)[2]);
                stockTF.setText(allData.get(editLine)[3]);
            }
        }

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    Personnel.manageVaccinePage();
                    dispose();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isEdit == 0) {
                    nonEdit();
                }
                else if(isEdit == 1) {
                    isEdited();
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choose = JOptionPane.showConfirmDialog(null,
                        "Are you sure to delete?",
                        "Delete Vaccine",
                        JOptionPane.YES_NO_OPTION);

                if(choose == JOptionPane.YES_OPTION) {
                    Stack<String[]> newData = new Stack<String[]>();
                    for(int i=0; i<allData.size(); i++) {
                        if(i != editLine) {
                            newData.push(allData.get(i));
                        }
                    }

                    Personnel.updateVaccine(newData);
                    Personnel.manageVaccinePage();
                    dispose();
                }
            }
        });
    }

    private void nonEdit() {
        try {
            String code = null,
                    name = null,
                    manufacture = null;
            int stock = Integer.parseInt(stockTF.getText());

            String checkCode = "",
                    checkName = "",
                    checkMan = "";

            if(vacCodeTF.getText() != null) {
                if(allData != null){
                    for(int i=0; i<allData.size(); i++) {
                        if(vacCodeTF.getText().equals(allData.get(i)[0])){
                            checkCode = "This code had been registered!\n";
                        }
                        else {
                            code = vacCodeTF.getText();
                        }
                    }
                }
                else {
                    code = vacCodeTF.getText();
                }
            }
            else {
                checkCode = "Enter vaccine code.\n";
            }

            if(vacNameTF.getText() != null) {
                name = vacNameTF.getText();
            }
            else {
                checkName = "Enter vaccine name\n";
            }

            if(manufactureTF.getText() != null) {
                manufacture = manufactureTF.getText();
            }
            else {
                checkMan = "Enter Manufacture\n";
            }

            if(checkCode == "" && checkName == "" && checkMan == "") {
                Personnel.addVaccine(code, name, manufacture, stock);

                JOptionPane.showMessageDialog(new JFrame(), "The vaccine had been added.",
                        "Add Vaccine", JOptionPane.INFORMATION_MESSAGE);

                Personnel.manageVaccinePage();
                dispose();
            }
            else {
                JOptionPane.showMessageDialog(null,
                        "Please follow the requirement(s): \n" +
                                checkCode + checkName + checkMan,
                        "Add Vaccine", JOptionPane.WARNING_MESSAGE);
            }
        }
        catch (NumberFormatException z) {
            JOptionPane.showMessageDialog(null, "Enter integer number for stock.",
                    "Add Vaccine", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void isEdited() {
        try {
            String code = null,
                    name = null,
                    manufacture = null;
            int stock = Integer.parseInt(stockTF.getText());

            String checkCode = "",
                    checkName = "",
                    checkMan = "";

            if(vacCodeTF.getText() != null) {
                if(allData != null){
                    for(int i=0; i<allData.size(); i++) {
                        if(i != editLine){
                            if(vacCodeTF.getText().equals(allData.get(i)[0])){
                                checkCode = "This code had been registered!\n";
                            }
                            else {
                                code = vacCodeTF.getText();
                            }
                        }
                        else {
                            code = vacCodeTF.getText();
                        }
                    }
                }
                else {
                    code = vacCodeTF.getText();
                }
            }
            else {
                checkCode = "Enter vaccine code.\n";
            }

            if(vacNameTF.getText() != null) {
                name = vacNameTF.getText();
            }
            else {
                checkName = "Enter vaccine name\n";
            }

            if(manufactureTF.getText() != null) {
                manufacture = manufactureTF.getText();
            }
            else {
                checkMan = "Enter Manufacture\n";
            }

            if(checkCode == "" && checkName == "" && checkMan == "") {
                String[] newDate = {code, name, manufacture, String.valueOf(stock)};

                for(int i = 0; i < allData.size(); i++) {
                    if (i == editLine) {
                        for (int z = 0; z < allData.get(i).length; z++) {
                            allData.get(i)[z] = newDate[z];
                        }
                    }
                }

                Personnel.updateVaccine(allData);
                JOptionPane.showMessageDialog(new JFrame(), "The vaccine had been updated.",
                        "Edit Vaccine", JOptionPane.INFORMATION_MESSAGE);

                Personnel.manageVaccinePage();
                dispose();
            }
            else {
                JOptionPane.showMessageDialog(null,
                        "Please follow the requirement(s): \n" +
                                checkCode + checkName + checkMan,
                        "Add Vaccine", JOptionPane.WARNING_MESSAGE);
            }
        }
        catch (NumberFormatException z) {
            JOptionPane.showMessageDialog(null, "Enter integer number for stock.",
                    "Add Vaccine", JOptionPane.WARNING_MESSAGE);
        }
    }
}
