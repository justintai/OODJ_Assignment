package dataset;

import Global.Global;
import client.User;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.System.out;

public class Appointment {
    private char vaccineCode, centreCode;
    private int isDone1, isDone2, isRegister, isConfirm;
    private String passportNo, address, state, ICNo, telNo, name;
    private Date vac1, vac2;
    private Stack<String[]> apptData = new Stack<>();

    public Appointment()
    {
        readAllAppointment();
    }

    public Appointment(String passportNo, String address, String state, String ICNo, String telNo, String name, Date vac1, Date vac2, char vaccineCode, char centreCode, int isDone1, int isDone2, int isRegister, int isConfirm)
    {
        this.passportNo = passportNo;
        this.address = address;
        this.state = state;
        this.telNo = telNo;
        this.name = name;
        this.vac1 = vac1;
        this.vac2 = vac2;
        this.vaccineCode = vaccineCode;
        this.centreCode = centreCode;
        this.ICNo = ICNo;
        this.isDone1 = isDone1;
        this.isDone2 = isDone2;
        this.isRegister = isRegister;
        this.isConfirm = isConfirm;
    }

    public List<Object> getAllApointmentValues()
    {
        List<Object> data = new ArrayList<>();
        data.add(ICNo);
        data.add(passportNo);
        data.add(name);
        data.add(telNo);
        data.add(address);
        data.add(state);
        data.add(vac1);
        data.add(vac2);
        data.add(vaccineCode);
        data.add(centreCode);
        data.add(isDone1);
        data.add(isDone2);
        data.add(isRegister);
        data.add(isConfirm);

        return data;
    }

    public void readAllAppointment()
    {
        String usrData[], line;
        try(BufferedReader in = new BufferedReader(new FileReader(Global.registerAppointmentFile));) {
            while((line = in.readLine()) !=null) {
                usrData = line.split("%");
                apptData.push(usrData);
            }
        }
        catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "File not exist!",
                    "User Data", JOptionPane.ERROR_MESSAGE);
        }
        catch (IOException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Stack<String[]> getAppointmentData()
    {
        return apptData;
    }

    public void writeAllAppointment()
    {
        File user = new File(Global.registerAppointmentFile);
        if(user.exists())
        {
            List<Object> wrt = getAllApointmentValues();
            try(PrintWriter out = new PrintWriter(new FileWriter(Global.registerAppointmentFile, true));)
            {
                out.println();
                for(Object str : wrt)
                {
                    out.write(str + "%");
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        } else{
            List<Object> wrt = getAllApointmentValues();
            try(PrintWriter out = new PrintWriter(new FileWriter(Global.registerAppointmentFile));)
            {
                for(Object str : wrt)
                {
                    out.write(str + "%");
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void registerProgramme()
    {

    }
}


