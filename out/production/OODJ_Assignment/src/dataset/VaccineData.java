package dataset;

import Global.Global;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VaccineData {

    private Stack<String[]> vaccineData = new Stack<String[]>();

    public VaccineData() {}

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
