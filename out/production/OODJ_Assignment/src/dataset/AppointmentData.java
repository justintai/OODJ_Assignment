package dataset;

import Global.Global;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppointmentData {
    private int isDone1, isDone2, isConfirm, telNo;
    private String passportNo, address, state, ICNo, name, vac1, vac2, vaccineCode, centreCode;
    private Stack<String[]> apptData = new Stack<>();

    public AppointmentData()
    {
        readAllAppointment();
    }

    public AppointmentData(String passportNo, String address, String state,
                           String ICNo, int telNo, String name, String vac1,
                           String vac2, String vaccineCode, String centreCode,
                           int isDone1, int isDone2, int isConfirm)
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
        this.isConfirm = isConfirm;
    }

    public List<Object> getAllAppointmentValues()
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
        data.add(isConfirm);

        return data;
    }

    public void readAllAppointment()
    {
        String usrData[], line;
        try(BufferedReader in = new BufferedReader(new FileReader(Global.appointmentFile));) {
            while((line = in.readLine()) !=null) {
                usrData = line.split("%");
                apptData.push(usrData);
            }
        }
        catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "File not exist!",
                    "Appointment Data", JOptionPane.ERROR_MESSAGE);
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
        File user = new File(Global.appointmentFile);
        if(user.exists())
        {
            List<Object> wrt = getAllAppointmentValues();
            try(PrintWriter out = new PrintWriter(new FileWriter(Global.appointmentFile, true));)
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
            JOptionPane.showMessageDialog(new JFrame(), "Appointment Created!", "Appointment", JOptionPane.INFORMATION_MESSAGE);

        } else{
            List<Object> wrt = getAllAppointmentValues();
            try(PrintWriter out = new PrintWriter(new FileWriter(Global.appointmentFile));)
            {
                for(Object str : wrt)
                {
                    out.write(str + "%");
                }
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(new JFrame(), "Appointment created!", "Appointment", JOptionPane.INFORMATION_MESSAGE);

        }
    }

    public void updateAppointment()
    {
        List<Object> wrt = getAllAppointmentValues();
        try(PrintWriter out = new PrintWriter(new FileWriter(Global.appointmentFile, true));)
        {
            readAllAppointment();
            try(PrintWriter clean = new PrintWriter(new FileWriter(Global.appointmentFile,false));)
            {
                clean.flush();
                clean.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            for(int i = 0; i < apptData.size(); i++)
            {
                if(apptData.get(i)[0].equals(wrt.get(0)) && apptData.get(i)[1].equals("null") ||
                        apptData.get(i)[1].equals(wrt.get(1)) && apptData.get(i)[0].equals("null"))
                {
                    for(int j = 0; j < apptData.get(i).length; j++)
                    {
                        apptData.get(i)[j] = wrt.get(j).toString();
                    }
                }

                for(int k = 0; k < apptData.get(i).length; k++)
                {
                    out.write(apptData.get(i)[k] + "%");
                }

                if(i < apptData.size()-1)
                {
                    out.println();
                }
            }
            JOptionPane.showMessageDialog(new JFrame(), "Appointment have been updated", "Appointment", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void updateAppointment(Stack<String[]> newData) {
        try(PrintWriter out = new PrintWriter(new FileWriter(Global.appointmentFile,true));) {
            try (PrintWriter clean = new PrintWriter(new FileWriter(Global.appointmentFile, false));) {
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
                    "Appointment Data", JOptionPane.ERROR_MESSAGE);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}


