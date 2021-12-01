package dataset;

import Global.Global;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VaccineData {
    private String name, manufacture, code;
    private int stock;
    private Stack<String[]> vaccineData = new Stack<String[]>();
    private List<Object> summary = new ArrayList<>();

    public VaccineData() {
        readAll();
    }

    public VaccineData(String code, String name, String manufacture, int stock) {
        this.code = code;
        this.name = name;
        this.manufacture = manufacture;
        this.stock = stock;

        getAllStringValues();
    }

    public Stack<String[]> getVaccineData() {
        return vaccineData;
    }

    public void getAllStringValues() {
        summary.add(code);
        summary.add(name);
        summary.add(manufacture);
        summary.add(stock);
    }

    public void writeAll() {
        File file = new File(Global.userFile);
        if(file.exists()) {
            readAll();
            String checkCode = "",
                    checkName = "",
                    checkMan = "";

            for(int i=0; i<vaccineData.size(); i++) {
                if(summary.get(0) != null) {
                    if(summary.get(0).equals(vaccineData.get(i)[0])){
                        checkCode = "This code is registered!\n";
                    }
                }
                else {
                    checkCode = "Enter vaccine code.";
                }
            }

            checkName = (summary.get(1) == null) ? "Enter vaccine name" : "";
            checkMan = (summary.get(2) == null) ? "Enter Manufacture" : "";

            // do write vaccine
            try(PrintWriter out = new PrintWriter(new FileWriter(Global.vaccineFile,true));) {
                out.println();
                for (Object str : summary) {
                    out.write(str + "%");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try(PrintWriter out = new PrintWriter(new FileWriter(Global.vaccineFile));) {
                writeAll();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void readAll() {
        String vacData[], line;
        try(BufferedReader in = new BufferedReader(new FileReader(Global.userFile));) {
            while((line = in.readLine()) !=null) {
                vacData = line.split("%");
                vaccineData.push(vacData);
            }
        }
        catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "File not exist!",
                    "Vaccine Data", JOptionPane.ERROR_MESSAGE);
        }
        catch (IOException ex) {
            Logger.getLogger(VaccineData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
