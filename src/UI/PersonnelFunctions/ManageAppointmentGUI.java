package UI.PersonnelFunctions;

import Global.Global;
import Search.Search;
import dataset.AppointmentData;
import dataset.VaccinationCentreData;
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
    private Stack<Integer> index = null;
    String[] colName = {"Centre Code", "Name", "State", "Vaccine Code", "Stock"};

    public ManageAppointmentGUI(String title) {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(manageAppointmentPanel);
        this.setSize(500, 500);

        int totalData = 0;
        sortData = null;

        File file = new File(Global.appointmentFile);
        if(file.exists()){
            AppointmentData appointmentData = new AppointmentData();
            allData = appointmentData.getAppointmentData();
            totalData = allData.size();

            showTable(allData);

            searchTF.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    searchWord = searchTF.getText();
                    searchTF.setText("");

                    Search search = new Search();
                    index = search.searchCentre(searchWord);
                    VaccinationCentreData centreData = new VaccinationCentreData();
                    Stack<String[]> oldData = centreData.getCentreData();
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
                        dataTotal.setText("Total data: " + centreData.getCentreData().size());
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

                Personnel.addCentrePage(line, 1);
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
                Personnel.editAppointmentPage();
//                Personnel.personnelPage();
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
                if(columnIndex >= 3) {
                    realCol = columnIndex + 1;
                }
                else {
                    realCol = columnIndex;
                }
                return tableData.get(rowIndex)[realCol];
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

                int realCol=0;
                if(columnIndex >= 3) {
                    realCol = columnIndex + 1;
                }
                else {
                    realCol = columnIndex;
                }

                tableData.get(rowIndex)[realCol] = (String) aValue;
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
