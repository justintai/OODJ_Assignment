package UI;

import Global.Global;
import Search.Search;
import dataset.VaccineData;
import personnel.Personnel;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Arrays;
import java.util.Stack;

public class ManageVaccineGUI extends JFrame{
    private JPanel vaccinePanel;
    private JButton backButton;
    private JButton addButton;
    private JTable vaccineTable;
    private JLabel dataTotal;
    private JTextField searchTF;
    private String searchWord;
    private Stack<String[]> allData = new Stack<>();
    private Stack<String[]> sortData = new Stack<>();

    public ManageVaccineGUI(String title) {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(vaccinePanel);
        this.setSize(500, 500);

        int totalData = 0;

        File file = new File(Global.vaccineFile);
        if(file.exists()){
            VaccineData vaccineData = new VaccineData();
            allData = vaccineData.getVaccineData();
            sortData = allData;
            totalData = allData.size();

            showTable();

            searchTF.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    searchWord = searchTF.getText();
                    searchTF.setText("");

                    Search search = new Search();
                    Stack<Integer> index = search.searchVaccine(searchWord);
                    System.out.println(allData.size());
                    if(index != null) {
                        sortData.clear();
                        System.out.println(allData.size());
                        for(int i=0; i<index.size(); i++) {
                            System.out.println(allData.size());
                            sortData.push(allData.get(index.get(i)));
                        }
                        dataTotal.setText("Total data: " + index.size());
                    }
                    else {
                        sortData = allData;
                        dataTotal.setText("Total data: " + allData.size());
                    }

                    System.out.println(sortData.size());
                }
            });
        }
        else {
            totalData = 0;
        }

        dataTotal.setText("Total data: " + totalData);

        vaccineTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Personnel.addVaccinePage(vaccineTable.getSelectedRow(), 1);
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

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Personnel.addVaccinePage(-1, 0);
                dispose();
            }
        });
    }

    private void showTable() {
        String[] colName = {"Vaccine Code", "Vaccine Name", "Manufacture", "Stock"};

        TableModel dataModel = new TableModel() {
            @Override
            public int getRowCount() {
                return sortData.size();
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
                return sortData.get(rowIndex)[columnIndex];
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                sortData.get(rowIndex)[columnIndex] = (String) aValue;
            }

            @Override
            public void addTableModelListener(TableModelListener l) {

            }

            @Override
            public void removeTableModelListener(TableModelListener l) {

            }
        };
        vaccineTable.setModel(dataModel);
    }
}
