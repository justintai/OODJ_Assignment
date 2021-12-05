package UI.PersonnelFunctions;

import Global.Global;
import client.People;
import dataset.AppointmentData;
import dataset.VaccinationCentreData;
import dataset.VaccineData;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import personnel.Personnel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Stack;

public class EditAppointmentGUI extends JFrame{
    private JPanel editAppointmentPanel;
    private JButton backButton;
    private JButton saveButton;
    private JButton deleteButton;
    private JCheckBox isDone1CheckBox;
    private JCheckBox isDone2CheckBox;
    private JTextField icTF;
    private JTextArea addressTF;
    private JComboBox vaccineCodeCB;
    private JDatePickerImpl dose1Date;
    private JTextField passportTF;
    private JTextField nameTF;
    private JTextField telTF;
    private JDatePickerImpl dose2Date;
    private JComboBox centreCB;
    private JTextField stateTF;
    private JDatePanelImpl datePanel1;
    private JDatePanelImpl datePanel2;
    private Stack<String[]> allData = new Stack<>();
    private Stack<String[]> centreData = new Stack<>();
    private Stack<String[]> vaccineData = new Stack<>();
    private static Stack<Integer> centreIndex = new Stack<>();
    private Stack<Integer> vaccineIndex = new Stack<>();
    private int editLine;

    public EditAppointmentGUI(String title, int editLine) {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(editAppointmentPanel);
        this.setSize(500, 600);

        this.editLine = editLine;
        VaccinationCentreData vacCentreData = new VaccinationCentreData();
        this.centreData = vacCentreData.getCentreData();
        VaccineData vacData = new VaccineData();
        this.vaccineData = vacData.getVaccineData();

        icTF.setEditable(false);
        passportTF.setEditable(false);
        nameTF.setEditable(false);
        telTF.setEditable(false);
        addressTF.setEditable(false);
        stateTF.setEditable(false);

        File file = new File(Global.vaccineFile);
        if(file.exists()) {
            AppointmentData appointmentData = new AppointmentData();
            allData = appointmentData.getAppointmentData();
        }

        centreCB.addItem("null");
        for (int i=0; i<centreData.size(); i++) {
            if(allData.get(editLine)[5].equals(centreData.get(i)[2])){
                centreCB.addItem(centreData.get(i)[0] + " - " + centreData.get(i)[1]);
                centreIndex.push(i);
            }
        }

        vaccineCodeCB.addItem("null");
        for (int i=0; i<vaccineData.size(); i++) {
            if(centreData.get(centreIndex.get(centreCB.getSelectedIndex()))[4].equals(vaccineData.get(i)[0])){
                vaccineCodeCB.addItem(vaccineData.get(i)[0] + " - " + vaccineData.get(i)[1]);
                vaccineIndex.push(i);
            }
        }

        if(editLine != -1) {
            if(allData != null) {
                String item = "null";

                if(!allData.get(editLine)[12].equals("1")){
                    isDone1CheckBox.setEnabled(false);
                    isDone2CheckBox.setEnabled(false);
                }

                icTF.setText(allData.get(editLine)[0]);
                passportTF.setText(allData.get(editLine)[1]);
                nameTF.setText(allData.get(editLine)[2]);
                telTF.setText(allData.get(editLine)[3]);
                addressTF.setText(allData.get(editLine)[4]);
                stateTF.setText(allData.get(editLine)[5]);

                for(int i=0; i<centreData.size(); i++) {
                    if(allData.get(editLine)[9].equals(centreData.get(i)[0]))
                        item = centreData.get(i)[0] + " - " + centreData.get(i)[1];
                }
                centreCB.setSelectedItem(item);

                for(int i=0; i<vaccineData.size(); i++) {
                    if(allData.get(editLine)[8].equals(vaccineData.get(i)[0]))
                        item = vaccineData.get(i)[0] + " - " + vaccineData.get(i)[1];
                }
                vaccineCodeCB.setSelectedItem(item);

                isDone1CheckBox.setSelected(allData.get(editLine)[10].equals("1"));
                isDone2CheckBox.setSelected(allData.get(editLine)[11].equals("1"));

                if(!allData.get(editLine)[6].equals("null") || !allData.get(editLine)[7].equals("null")){
                    try {
                        Stack<String[]> date = new Stack<>();
                        String[] dateData1 = allData.get(editLine)[6].split("-");
                        date.push(dateData1);
                        String[] dateData2 = allData.get(editLine)[7].split("-");
                        date.push(dateData2);

                        datePanel1.getModel().setDate(Integer.parseInt(date.get(0)[2]),
                                Integer.parseInt(date.get(0)[1]) - 1,
                                Integer.parseInt(date.get(0)[0]));
                        datePanel2.getModel().setDate(Integer.parseInt(date.get(1)[2]),
                                Integer.parseInt(date.get(1)[1]) - 1,
                                Integer.parseInt(date.get(1)[0]));

                        datePanel1.getModel().setSelected(true);
                        datePanel2.getModel().setSelected(true);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Personnel.manageAppointmentPage();
                dispose();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String checkVac1="",
                            checkVac2="",
                            checkVacCode="",
                            checkCentreCode = "";

                    String icNo = null,
                            passport = null,
                            name = null,
                            vac1 = null,
                            vac2 = null,
                            address = null,
                            state = null,
                            vacCode = null,
                            centreCode = null,
                            isDone1 = "0",
                            isDone2 = "0",
                            isConfirm = "0";

                    int telNo = Integer.parseInt(telTF.getText());
                    icNo = icTF.getText();
                    passport = passportTF.getText();
                    name = nameTF.getText();
                    address = addressTF.getText();
                    state = stateTF.getText();

                    isConfirm = allData.get(editLine)[12];

                    Date d1 = (Date)dose1Date.getModel().getValue();
                    if(d1 != null)
                    {
                        try {
                            vac1 = new SimpleDateFormat("dd-MM-yyyy").format(d1);
                        }
                        catch (NullPointerException nullPointerException) {
                            checkVac1 = "Enter valid date!\n";
                        }
                    }
                    else{
                        checkVac1 = "Enter 1st dose date.\n";
                    }

                    Date d2 = (Date)dose2Date.getModel().getValue();
                    if(d2 != null)
                    {
                        try {
                            vac2 = new SimpleDateFormat("dd-MM-yyyy").format(d2);
                        }
                        catch (NullPointerException nullPointerException) {
                            checkVac2 = "Enter valid date!\n";
                        }
                    }
                    else{
                        checkVac2 = "Enter 2nd dose date.\n";
                    }

                    if(!centreCB.getSelectedItem().equals("null")) {
                        centreCode = centreData.get(centreIndex.get(centreCB.getSelectedIndex()-1))[0];
                    }
                    else {
                        checkCentreCode = "Select a centre.\n";
                    }

                    if(!vaccineCodeCB.getSelectedItem().equals("null")) {
                        vacCode = vaccineData.get(vaccineIndex.get(vaccineCodeCB.getSelectedIndex()-1))[0];
                    }
                    else {
                        checkVacCode = "Select a vaccine.\n";
                    }

                    if(isConfirm.equals("1")) {
                        if(isDone1CheckBox.isSelected()) {
                            isDone1 = "1";
                        }

                        if(isDone2CheckBox.isSelected()) {
                            isDone2 = "1";
                        }
                    }

                    if(checkCentreCode == "" && checkVac1 == "" && checkVac2 == "" && checkVacCode == "") {
                        String[] newData = {icNo, passport, name,
                                String.valueOf(telNo), address, state,
                                vac1, vac2, vacCode,
                                centreCode, isDone1, isDone2, isConfirm};

                        for(int i = 0; i < allData.size(); i++) {
                            if (i == editLine) {
                                for (int z = 0; z < allData.get(i).length; z++) {
                                    allData.get(i)[z] = newData[z];
                                }
                            }
                        }

                        for(int j = 0; j < centreData.size(); j++) {
                            if (j == centreIndex.get(centreCB.getSelectedIndex() -1)) {
                                int newStock = Integer.parseInt(centreData.get(j)[5]) - 2;
                                centreData.get(j)[5] = String.valueOf(newStock);
                            }
                        }

                        Personnel.updateAppointment(allData);
                        Personnel.updateCentre(centreData);
                        JOptionPane.showMessageDialog(new JFrame(), "The appointment had been updated.",
                                "Edit Appointment", JOptionPane.INFORMATION_MESSAGE);

                        Personnel.manageAppointmentPage();
                        dispose();
                    }
                    else {
                        JOptionPane.showMessageDialog(null,
                                "Please follow the requirement(s): \n" +
                                        checkCentreCode + checkVac1 + checkVac2 + checkVacCode,
                                "Add Centre", JOptionPane.WARNING_MESSAGE);
                    }
                }
                catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(new JFrame(), "Tel no has invalid format",
                            "Edit Appointment", JOptionPane.WARNING_MESSAGE);
                }
                catch (NullPointerException nullPointerException) {
                    JOptionPane.showMessageDialog(new JFrame(), "Please enter valid date!",
                            "Edit Appointment", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int check = JOptionPane.showConfirmDialog(null,
                        "Are you sure to delete this appointment?", "Confirmation",
                        JOptionPane.YES_NO_OPTION);

                if(check == 0) {
                    allData.get(editLine)[6] = "null";
                    allData.get(editLine)[7] = "null";
                    allData.get(editLine)[8] = "null";
                    allData.get(editLine)[9] = "null";
                    allData.get(editLine)[10] = "0";
                    allData.get(editLine)[11] = "0";

                    Personnel.updateAppointment(allData);
                    JOptionPane.showMessageDialog(new JFrame(), "The appointment had been reset.",
                            "Edit Appointment", JOptionPane.INFORMATION_MESSAGE);
                    Personnel.manageAppointmentPage();
                    dispose();
                }
            }
        });

        dose1Date.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*Auto assign date to 2nd dose*/
                if(dose1Date.getModel().getValue() != "") {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    Date d1 = (Date) dose1Date.getModel().getValue();

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(d1);
                    cal.add(Calendar.DAY_OF_MONTH, 14);
                    String date2 = dateFormat.format(cal.getTime());
                    String[] dateData2 = date2.split("-");
                    dose2Date.getModel().setDate(Integer.parseInt(dateData2[2]),
                            Integer.parseInt(dateData2[1]) - 1,
                            Integer.parseInt(dateData2[0]));
                    dose2Date.getModel().setSelected(true);
                }
            }
        });

        centreCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vaccineCodeCB.removeAllItems();
                vaccineIndex.clear();
                vaccineCodeCB.addItem("null");
                for (int i=0; i<vaccineData.size(); i++) {
                    if(centreData.get(centreIndex.get(centreCB.getSelectedIndex()-1))[4].equals(vaccineData.get(i)[0])){
                        vaccineCodeCB.addItem(vaccineData.get(i)[0] + " - " + vaccineData.get(i)[1]);
                        vaccineIndex.push(i);
                    }
                }
            }
        });
    }

    // for non-palette component
    private void createUIComponents() {

        UtilDateModel model1 = new UtilDateModel(),
                      model2 = new UtilDateModel();

        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        datePanel1 = new JDatePanelImpl(model1, p);
        datePanel2 = new JDatePanelImpl(model2, p);
        dose1Date = new JDatePickerImpl(datePanel1, new DateLabelFormatter());
        dose2Date = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
    }

    // format date picker date
    public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

        private String datePattern = "dd-MM-yyyy";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }

            return "";
        }
    }
}
