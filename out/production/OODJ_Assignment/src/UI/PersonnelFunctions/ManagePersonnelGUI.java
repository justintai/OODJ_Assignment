package UI.PersonnelFunctions;

import Global.Global;
import Search.PersonnelSearch;
import dataset.UserData;
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

public class ManagePersonnelGUI extends JFrame {
    private JPanel managePersonnelPanel;
    private JTextField searchTF;
    private JButton backButton;
    private JButton addButton;
    private JTable dataTable;
    private JLabel dataTotal;
    private String searchWord;
    private Stack<String[]> allData = new Stack<>();
    private Stack<String[]> sortData = new Stack<>();
    private Stack<Integer> index = null;

    public ManagePersonnelGUI(String title) {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(managePersonnelPanel);
        this.setSize(500, 500);

        int totalData = 0;

        File file = new File(Global.userFile);
        if(file.exists()){
            UserData userData = new UserData();
            allData = userData.getUserData();

            PersonnelSearch personnelData = new PersonnelSearch();
            index = personnelData.searchPersonnel("null");
            totalData = index.size();

            for (int i=0; i<index.size(); i++) {
                sortData.push(allData.get(index.get(i)));
            }

            showTable(sortData);

            searchTF.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    searchWord = searchTF.getText();
                    searchTF.setText("");

                    PersonnelSearch personnelSearch = new PersonnelSearch();
                    index = personnelSearch.searchPersonnel(searchWord);
                    Stack<String[]> oldData = allData;
                    Stack<String[]> newData = new Stack<>();

                    if(index != null) {
                        for (Integer integer : index) {
                            newData.push(oldData.get(integer));
                        }
                        dataTotal.setText("Total data: " + index.size());
                        sortData = newData;
                    }
                    else {
                        newData = oldData;
                        sortData = null;
                        dataTotal.setText("Total data: " + allData.size());
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

                Personnel personnel = new Personnel(allData.get(line),1);
                personnel.UpdateProfilePage(1);
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
                Personnel personnel = new Personnel();
                personnel.userPage();
                dispose();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Personnel.registerUser(1);
                dispose();
            }
        });
    }

    private void showTable(Stack<String[]> tableData) {
        String[] colName = {"Name", "Birthdate", "Gender", "Tel No.", "State"};

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
                if(columnIndex < 3) {
                    realCol = columnIndex + 2;
                }
                else if (columnIndex == 3){
                    realCol = columnIndex + 3;
                }
                else if (columnIndex > 3) {
                    realCol = columnIndex + 5;
                }
                return tableData.get(rowIndex)[realCol];
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                int realCol=0;
                if(columnIndex < 3) {
                    realCol = columnIndex + 2;
                }
                else if (columnIndex == 3){
                    realCol = columnIndex + 3;
                }
                else if (columnIndex > 3) {
                    realCol = columnIndex + 5;
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
