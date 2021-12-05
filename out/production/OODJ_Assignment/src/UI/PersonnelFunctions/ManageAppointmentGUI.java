package UI.PersonnelFunctions;

import Global.Global;
import Search.Search;
import dataset.AppointmentData;
import dataset.VaccinationCentreData;
import dataset.VaccineData;
import personnel.Personnel;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Stack;

public class ManageAppointmentGUI extends JFrame {
    private JTextField searchTF;
    private JTable dataTable;
    private JButton backButton;
    private JLabel dataTotal;
    private JPanel manageAppointmentPanel;
    private String searchWord;
    private Stack<String[]> allData = new Stack<>();
    private Stack<String[]> sortData = new Stack<>();
    private Stack<String[]> vaccineData = new Stack<>();
    private Stack<String[]> centreData = new Stack<>();
    private Stack<Integer> index = null;
    String[] colName = {"User ID", "Name", "Tel No", "State", "Vaccine Code", "Centre Name", "Status"};

    public ManageAppointmentGUI(String title) {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(manageAppointmentPanel);
        this.setSize(550, 500);

        int totalData = 0;
        sortData = null;

        File file = new File(Global.appointmentFile);
        if(file.exists()){
            AppointmentData appointmentData = new AppointmentData();
            allData = appointmentData.getAppointmentData();
            VaccineData vacData = new VaccineData();
            vaccineData = vacData.getVaccineData();
            VaccinationCentreData vacCentreData = new VaccinationCentreData();
            centreData = vacCentreData.getCentreData();

            totalData = allData.size();

            showTable(allData);

            searchTF.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    searchWord = searchTF.getText();
                    searchTF.setText("");

                    Search search = new Search();
                    index = search.searchAppointment(searchWord);
                    AppointmentData appointmentData = new AppointmentData();
                    Stack<String[]> oldData = appointmentData.getAppointmentData();
                    Stack<String[]> newData = new Stack<>();

                    if(index != null) {
                        for(int i=0; i<index.size(); i++) {
                            newData.push(oldData.get(index.get(i)));
                        }
                        dataTotal.setText("Total data: " + index.size());
                        sortData = newData;
                    }
                    else {
                        newData = oldData;
                        sortData = null;
                        dataTotal.setText("Total data: " + appointmentData.getAppointmentData().size());
                    }

                    showTable(newData);
                }
            });
        }
        else {
            totalData = 0;
        }

        dataTotal.setText("Total data: " + totalData);

        dataTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int line=-1;
                if(sortData != null) {
                    line = index.get(dataTable.getSelectedRow());
                    sortData = null;
                }
                else {
                    line = dataTable.getSelectedRow();
                }

                Personnel.editAppointmentPage(line);
                dispose();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Personnel.personnelPage();
                dispose();
            }
        });
    }

    private void showTable(Stack<String[]> tableData) {
        TableModel dataModel = new TableModel() {
            @Override
            public int getRowCount() {
                return tableData.size();
            }

            @Override
            public int getColumnCount() {
                return colName.length;
            }

            @Override
            public String getColumnName(int columnIndex) {
                return colName[columnIndex].toString();
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return getValueAt(0, columnIndex).getClass();
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {

                int realCol=0;
                String status = "";
                if(columnIndex == 0) {
                    if(tableData.get(rowIndex)[0].equals("null")){
                        realCol = columnIndex + 1;
                    }
                    else if(tableData.get(rowIndex)[1].equals("null")) {
                        realCol = columnIndex;
                    }
                    return tableData.get(rowIndex)[realCol];
                }
                else if(columnIndex == 1 || columnIndex == 2) {
                    realCol = columnIndex + 1;
                    return tableData.get(rowIndex)[realCol];
                }
                else if(columnIndex == 3) {
                    realCol = columnIndex + 2;
                    return tableData.get(rowIndex)[realCol];
                }
                else if(columnIndex == 4) {
                    realCol = columnIndex + 4;
                    for(int i=0; i<vaccineData.size(); i++) {
                        if(tableData.get(rowIndex)[realCol].equals(vaccineData.get(i)[0]))
                            return vaccineData.get(i)[1];
                    }
                }
                else if(columnIndex == 5) {
                    realCol = columnIndex + 4;
                    for(int i=0; i<centreData.size(); i++) {
                        if(tableData.get(rowIndex)[realCol].equals(centreData.get(i)[0]))
                            return centreData.get(i)[1];
                    }
                }
                else if(columnIndex == 6) {
                    if(tableData.get(rowIndex)[12].equals("2")) {
                        status = "reject";
                    }
                    else if(tableData.get(rowIndex)[12].equals("0")) {
                        status = "pending";
                    }
                    else if(tableData.get(rowIndex)[12].equals("1") &&
                            tableData.get(rowIndex)[10].equals("0") &&
                            tableData.get(rowIndex)[11].equals("0")) {
                        status = "1st dose";
                    }
                    else if(tableData.get(rowIndex)[12].equals("1") &&
                            tableData.get(rowIndex)[10].equals("1") &&
                            tableData.get(rowIndex)[11].equals("0")) {
                        status = "2nd dose";
                    }
                    else if(tableData.get(rowIndex)[12].equals("1") &&
                            tableData.get(rowIndex)[10].equals("1") &&
                            tableData.get(rowIndex)[11].equals("1")) {
                        status = "complete";
                    }
                    return status;
                }
                else {
                    realCol = columnIndex;
                }
                return tableData.get(rowIndex)[realCol];
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

            }

            @Override
            public void addTableModelListener(TableModelListener l) {

            }

            @Override
            public void removeTableModelListener(TableModelListener l) {

            }
        };
        dataTable.setModel(dataModel);
    }
}
