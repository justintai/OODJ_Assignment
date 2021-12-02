package dataset;

import Global.Global;
import personnel.Personnel;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VaccinationCentreData {

    private String name, state, centreCode, address, vaccineCode;
    private int stock, maxStock;
    private Stack<String[]> centreData = new Stack<String[]>();
    private List<Object> summary = new ArrayList<>();

    public VaccinationCentreData() {
        readAll();
    }

    public VaccinationCentreData(String centreCode, String name, String state,
                                 String address, String vaccineCode, int maxStock,
                                 int stock, Stack<String[]> vacData) {
        this.centreCode = centreCode;
        this.name = name;
        this.state = state;
        this.address = address;
        this.vaccineCode = vaccineCode;
        this.maxStock = maxStock;
        this.stock = stock;

        getAllStringValues();
        Personnel.updateVaccine(vacData);
    }

    public Stack<String[]> getCentreData() {
        return centreData;
    }

    public void getAllStringValues() {
        summary.add(centreCode);
        summary.add(name);
        summary.add(state);
        summary.add(address);
        summary.add(vaccineCode);
        summary.add(stock);
        summary.add(maxStock);
    }

    public void writeAll() {
        File file = new File(Global.centreFile);
        if(file.exists())
        {
            try(PrintWriter out = new PrintWriter(new FileWriter(file,true));) {
                out.println();
                for (Object str : summary) {
                    out.write(str + "%");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            try(PrintWriter out = new PrintWriter(new FileWriter(file));) {
                for (Object str : summary) {
                    out.write(str + "%");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void readAll() {
        String cenData[], line;
        try(BufferedReader in = new BufferedReader(new FileReader(Global.centreFile));) {
            while((line = in.readLine()) !=null) {
                cenData = line.split("%");
                centreData.push(cenData);
            }
        }
        catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "File not exist!",
                    "Vaccination Centre Data", JOptionPane.ERROR_MESSAGE);
        }
        catch (IOException ex) {
            Logger.getLogger(VaccinationCentreData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateVaccinationCentreData(Stack<String[]> newData) {
        try(PrintWriter out = new PrintWriter(new FileWriter(Global.centreFile,true));) {
            try (PrintWriter clean = new PrintWriter(new FileWriter(Global.centreFile, false));) {
                clean.flush();
                clean.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            for(int i=0; i<newData.size(); i++) {
                for(int j = 0; j < newData.get(i).length; j++)
                {
                    out.write(newData.get(i)[j] + "%");
                }

                if(i<newData.size()-1){
                    out.println();
                }
            }
        }
        catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "File not exist!",
                    "Vaccination Centre Data", JOptionPane.ERROR_MESSAGE);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
