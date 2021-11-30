package dataset;

import Global.Global;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserData {

    private String name, birthday, gender, address, email, icNo, passport, state;
    protected String password;
    private String[] identifyUser = null;
    private int telNo, age, isAdmin = 0;
    private int userNo;
    private Stack<String[]> userData = new Stack<String[]>();



    public UserData() {
        readAll();
    }

    public UserData(String name, String birthday, String gender, String address, String email, String icNo, String passport, String state, String password, int age, int telNo, int isAdmin) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.icNo = icNo;
        this.passport = passport;
        this.password = password;
        this.state = state;
        this.telNo = telNo;
        this.age = age;
        this.isAdmin = isAdmin;
    }

    // Object for register.
    public List<Object> getAllStringValues()
    {
        List<Object> summary = new ArrayList<>();
        summary.add(icNo);
        summary.add(passport);
        summary.add(name);
        summary.add(birthday);
        summary.add(gender);
        summary.add(age);
        summary.add(telNo);
        summary.add(email);
        summary.add(address);
        summary.add(state);
        summary.add(password);
        summary.add(isAdmin);

        return summary;
    }

    public void countLines() {
        try(BufferedReader in = new BufferedReader(new FileReader(Global.userFile));) {
            userNo=0;
            for(int i=0;in.readLine()!=null;i++){
                userNo++;
            }
            System.out.println("number of lines:"+userNo);
        }
        catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "File not exist!",
                    "User Data", JOptionPane.ERROR_MESSAGE);
        }
        catch (IOException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void readAll() {
        String usrData[], line;
        try(BufferedReader in = new BufferedReader(new FileReader(Global.userFile));) {
            while((line = in.readLine()) !=null) {
                usrData = line.split("%");
                userData.push(usrData);
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

    public Stack<String[]> getUserData() {
        return userData;
    }

    public void writeAll() {
        File user = new File(Global.userFile);
        if(user.exists())
        {
            List<Object> wrt = getAllStringValues();
            try(PrintWriter out = new PrintWriter(new FileWriter(Global.userFile,true));) {
                out.println();
                for (Object str : wrt) {
                    out.write(str + "%");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            List<Object> wrt = getAllStringValues();
            try(PrintWriter out = new PrintWriter(new FileWriter(Global.userFile));) {
                for (Object str : wrt) {
                    out.write(str + "%");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateUser() {
        List<Object> wrt = getAllStringValues();
        String[] usrData;
        String line;

        try(PrintWriter out = new PrintWriter(new FileWriter(Global.userFile,true));) {
            BufferedReader in = new BufferedReader(new FileReader(Global.userFile));
            while((line = in.readLine()) != null)
            {
                usrData = line.split("%");
                if(line.contains((CharSequence) wrt.get(0)))
                {
                    System.out.println(line);
                    System.out.println(String.valueOf(wrt));
                    line = line.replaceAll(line, String.valueOf(wrt));
                }

            }
            out.write(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] checkLogin(String userID, String password) {
        String[] loginAdmin = null;
        String msg = "";

        for(int i=0; i<userData.size(); i++) {
            if(userID.equals(userData.get(i)[0]) & password.equals(userData.get(i)[10])) {
                JOptionPane.showMessageDialog(null,
                        "Welcome "+userData.get(i)[2],
                        "Login",
                        JOptionPane.INFORMATION_MESSAGE);
                loginAdmin = userData.get(i);
                return loginAdmin;
            }
            else if(userID.equals(userData.get(i)[1]) & password.equals(userData.get(i)[10])) {
                JOptionPane.showMessageDialog(null,
                        "Welcome "+userData.get(i)[2],
                        "Login",
                        JOptionPane.INFORMATION_MESSAGE);
                loginAdmin = userData.get(i);
                return loginAdmin;
            }
            else  {
                msg = "Incorrect username/password.";
                loginAdmin = null;
            }
        }

        JOptionPane.showMessageDialog(null,
                msg,
                "Login",
                JOptionPane.WARNING_MESSAGE);

        return loginAdmin;
    }
}
