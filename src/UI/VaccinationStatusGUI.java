package UI;

import Search.PeopleSearch;
import client.People;
import dataset.AppointmentData;
import dataset.VaccinationCentreData;
import dataset.VaccineData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class VaccinationStatusGUI extends JFrame{
    private JPanel vaccinationStatusPanel;
    private JTextField searchTF;
    private JTextField nameTF;
    private JTextField icTF;
    private JTextField passportTF;
    private JTextField centreTF;
    private JTextField vaccineTF;
    private JTextField statusTF;
    private JTextField searchIDTF;
    private JTextField searchStatusTF;
    private JButton backButton;
    private String searchWord;
    private Stack<String[]> vaccineData = new Stack<>();
    private Stack<String[]> centreData = new Stack<>();
    private Stack<String[]> appointmentData = new Stack<>();
    private Stack<Integer> index = null;

    public VaccinationStatusGUI(String title, String[] userdata) {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(vaccinationStatusPanel);
        this.setSize(500, 500);

        nameTF.setEditable(false);
        icTF.setEditable(false);
        passportTF.setEditable(false);
        centreTF.setEditable(false);
        vaccineTF.setEditable(false);
        statusTF.setEditable(false);
        searchStatusTF.setEditable(false);
        searchIDTF.setEditable(false);

        nameTF.setText(userdata[2]);
        if(!userdata[0].equals("null")) {
            icTF.setText(userdata[0]);
            passportTF.setText("");
        }
        if(!userdata[1].equals("null")) {
            passportTF.setText(userdata[1]);
            icTF.setText("");
        }

        VaccinationCentreData vacCentreData = new VaccinationCentreData();
        centreData = vacCentreData.getCentreData();
        VaccineData vacData = new VaccineData();
        vaccineData = vacData.getVaccineData();
        AppointmentData appData = new AppointmentData();
        appointmentData = appData.getAppointmentData();

        for(int i=0; i<appointmentData.size(); i++) {
            if(userdata[0].equals(appointmentData.get(i)[0])) {
                for(int j=0; j<centreData.size(); j++) {
                    if(appointmentData.get(i)[9].equals(centreData.get(j)[0])) {
                        centreTF.setText(centreData.get(j)[1]);
                    }
                }

                for(int z=0; z<vaccineData.size(); z++) {
                    if(appointmentData.get(i)[8].equals(vaccineData.get(z)[0])) {
                        vaccineTF.setText(vaccineData.get(z)[1]);
                    }
                }

                String status = "";
                status = statusDetection(i);
                statusTF.setText(status);
            }
        }

        searchTF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchWord = searchTF.getText();
                searchTF.setText("");
                String status = "";

                PeopleSearch search = new PeopleSearch();
                index = search.searchAppointment(searchWord);

                if(!index.empty()) {
                    searchIDTF.setText(searchWord);
                    status = statusDetection(index.get(0));
                    searchStatusTF.setText(status);
                }
                else {
                    searchIDTF.setText("");
                    searchStatusTF.setText("");
                    JOptionPane.showMessageDialog(new JFrame(),
                            "This user cannot found!",
                            "Vaccination Status", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                People.peoplePage();
                dispose();
            }
        });
    }

    private String statusDetection(int num) {
        String status = "";
        if(appointmentData.get(num)[12].equals("2")) {
            status = "Reject";
        }
        else if(appointmentData.get(num)[12].equals("0")) {
            status = "Pending";
        }
        else if(appointmentData.get(num)[12].equals("1") &&
                appointmentData.get(num)[10].equals("0") &&
                appointmentData.get(num)[11].equals("0")) {
            status = "1st dose";
        }
        else if(appointmentData.get(num)[12].equals("1") &&
                appointmentData.get(num)[10].equals("1") &&
                appointmentData.get(num)[11].equals("0")) {
            status = "2nd dose";
        }
        else if(appointmentData.get(num)[12].equals("1") &&
                appointmentData.get(num)[10].equals("1") &&
                appointmentData.get(num)[11].equals("1")) {
            status = "Complete";
        }

        return status;
    }
}
