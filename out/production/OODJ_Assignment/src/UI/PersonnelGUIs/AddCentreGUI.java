package UI.PersonnelGUIs;

import client.Global.Global;
import client.Dataset.VaccinationCentreData;
import client.Dataset.VaccineData;
import client.Personnel.Personnel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Stack;

public class AddCentreGUI extends JFrame {
    private JPanel addCentrePanel;
    private JButton backButton;
    private JButton saveButton;
    private JTextField centreCodeTF;
    private JTextField centreNameTF;
    private JComboBox stateCB;
    private JTextArea addressTF;
    private JComboBox vaccineCodeCB;
    private JTextField vaccineStockTF;
    private JTextField vaccineMaxStockTF;
    private JButton deleteButton;
    private JLabel centreTitle;
    private Stack<String[]> allData;
    private Stack<String[]> vacData;
    private int editLine;

    public AddCentreGUI(String title, int editLine, int isEdit) {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(addCentrePanel);
        this.setSize(500, 500);

        VaccineData vaccineData = new VaccineData();
        vacData = vaccineData.getVaccineData();
        for(int i=0; i<vacData.size(); i++) {
            vaccineCodeCB.addItem(vacData.get(i)[0]);
        }

        stateCB.addItem("Johor");
        stateCB.addItem("Melaka");
        stateCB.addItem("Kuala Lumpur");
        stateCB.addItem("Pahang");

        this.editLine = editLine;
        vaccineMaxStockTF.setText("1000");

        Boolean deleteVisible = (isEdit == 0) ? false : true;
        deleteButton.setVisible(deleteVisible);

        Boolean editable = (isEdit == 0) ? true : false;
        centreCodeTF.setEditable(editable);

        String titleLabel = (isEdit == 0) ? "Add Centre" : "Edit Centre";
        centreTitle.setText(titleLabel);

        String saveTitle = (isEdit == 0) ? "Save" : "Update";
        saveButton.setText(saveTitle);

        File file = new File(Global.centreFile);
        if(file.exists()) {
            VaccinationCentreData centreData = new VaccinationCentreData();
            allData = centreData.getCentreData();
        }

        if(editLine != -1) {
            if(allData != null) {
                centreCodeTF.setText(allData.get(editLine)[0]);
                centreNameTF.setText(allData.get(editLine)[1]);
                stateCB.setSelectedItem(allData.get(editLine)[2]);
                addressTF.setText(allData.get(editLine)[3]);
                vaccineCodeCB.setSelectedItem(allData.get(editLine)[4]);
                vaccineStockTF.setText(allData.get(editLine)[5]);
                vaccineMaxStockTF.setText(allData.get(editLine)[6]);
            }
        }

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Personnel.manageVaccinationCentre();
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
                        "Delete Centre",
                        JOptionPane.YES_NO_OPTION);

                if(choose == JOptionPane.YES_OPTION) {
                    Stack<String[]> newData = new Stack<String[]>();
                    for(int i=0; i<allData.size(); i++) {
                        if(i != editLine) {
                            newData.push(allData.get(i));
                        }
                    }

                    Personnel.updateCentre(newData);
                    Personnel.manageVaccinationCentre();
                    dispose();
                }
            }
        });
    }

    private void nonEdit() {
        try {
            String centreCode = null,
                    name = null,
                    state = null,
                    address = null,
                    vaccineCode = null;

            int stock = 0, maxStock = Integer.parseInt(vaccineMaxStockTF.getText());

            state = (String) stateCB.getSelectedItem();
            vaccineCode = (String) vaccineCodeCB.getSelectedItem();

            String checkCode = "",
                    checkName = "",
                    checkAddress = "",
                    checkStock = "";

            if(centreCodeTF.getText() != null) {
                if(allData != null){
                    for(int i=0; i<allData.size(); i++) {
                        if(centreCodeTF.getText().equals(allData.get(i)[0])){
                            checkCode = "This code had been registered!\n";
                        }
                        else {
                            centreCode = centreCodeTF.getText();
                        }
                    }
                }
                else {
                    centreCode = centreCodeTF.getText();
                }
            }
            else {
                checkCode = "Enter centre code.\n";
            }

            if(centreNameTF.getText() != null) {
                name = centreNameTF.getText();
            }
            else {
                checkName = "Enter centre name\n";
            }

            if(addressTF.getText() != null) {
                address = addressTF.getText();
            }
            else {
                checkAddress = "Enter Address\n";
            }

            if(Integer.parseInt(vaccineStockTF.getText()) > maxStock) {
                checkStock = "Vaccine stock cannot more than maximum stock";
            }
            else {
                if(Integer.parseInt(vaccineStockTF.getText()) > Integer.parseInt(vacData.get(vaccineCodeCB.getSelectedIndex())[3])) {
                    checkStock = "Vaccine stock cannot more than current stock";
                }
                else{
                    stock = Integer.parseInt(vaccineStockTF.getText());
                    int newStock = Integer.parseInt(vacData.get(vaccineCodeCB.getSelectedIndex())[3]) - stock;
                    vacData.get(vaccineCodeCB.getSelectedIndex())[3] = String.valueOf(newStock);
                }
            }

            if(checkCode == "" && checkName == "" && checkAddress == "" && checkStock == "") {
                Personnel.addCentre(centreCode, name, state, address, vaccineCode, maxStock, stock, vacData);

                JOptionPane.showMessageDialog(new JFrame(), "The centre had been added.",
                        "Add Centre", JOptionPane.INFORMATION_MESSAGE);

                Personnel.manageVaccinationCentre();
                dispose();
            }
            else {
                JOptionPane.showMessageDialog(null,
                        "Please follow the requirement(s): \n" +
                                checkCode + checkName + checkAddress + checkStock,
                        "Add Centre", JOptionPane.WARNING_MESSAGE);
            }
        }
        catch (NumberFormatException z) {
            JOptionPane.showMessageDialog(null, "Enter integer number for stock.",
                    "Add Centre", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void isEdited() {
        try {
            String centreCode = null,
                    name = null,
                    state = null,
                    address = null,
                    vaccineCode = null;

            int stock = Integer.parseInt(vaccineStockTF.getText());
            int maxStock = Integer.parseInt(vaccineMaxStockTF.getText());

            state = (String) stateCB.getSelectedItem();
            vaccineCode = (String) vaccineCodeCB.getSelectedItem();

            String checkCode = "",
                    checkName = "",
                    checkAddress = "",
                    checkStock = "";

            if(centreCodeTF.getText() != null) {
                if(allData != null){
                    for(int i=0; i<allData.size(); i++) {
                        if(i != editLine){
                            if(centreCodeTF.getText().equals(allData.get(i)[0])){
                                checkCode = "This code had been registered!\n";
                            }
                            else {
                                centreCode = centreCodeTF.getText();
                            }
                        }
                        else {
                            centreCode = centreCodeTF.getText();
                        }
                    }
                }
                else {
                    centreCode = centreCodeTF.getText();
                }
            }
            else {
                checkCode = "Enter centre code.\n";
            }

            if(centreNameTF.getText() != null) {
                name = centreNameTF.getText();
            }
            else {
                checkName = "Enter centre name\n";
            }

            if(addressTF.getText() != null) {
                address = addressTF.getText();
            }
            else {
                checkAddress = "Enter Address\n";
            }

            if(stock > maxStock) {
                checkStock = "Vaccine stock cannot more that maximum stock";
            }
            else {

            }

            if(checkCode == "" && checkName == "" && checkAddress == "" && checkStock == "") {
                String[] newDate = {centreCode, name, state, address, vaccineCode,
                        String.valueOf(maxStock), String.valueOf(stock)};

                for(int i = 0; i < allData.size(); i++) {
                    if (i == editLine) {
                        for (int z = 0; z < allData.get(i).length; z++) {
                            allData.get(i)[z] = newDate[z];
                        }
                    }
                }

                Personnel.updateCentre(allData);
                JOptionPane.showMessageDialog(new JFrame(), "The centre had been updated.",
                        "Edit Centre", JOptionPane.INFORMATION_MESSAGE);

                Personnel.manageVaccinationCentre();
                dispose();
            }
            else {
                JOptionPane.showMessageDialog(null,
                        "Please follow the requirement(s): \n" +
                                checkCode + checkName + checkAddress + checkStock,
                        "Add Centre", JOptionPane.WARNING_MESSAGE);
            }
        }
        catch (NumberFormatException z) {
            JOptionPane.showMessageDialog(null, "Enter integer number for stock.",
                    "Add Centre", JOptionPane.WARNING_MESSAGE);
        }
    }
}
