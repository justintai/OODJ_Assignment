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
    private int telNo, age, isAdmin = 0;
    private int userNo;
    private Stack<String[]> UserData = new Stack<String[]>();

    public UserData() {}

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
                UserData.push(usrData);
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

    public int checkLogin(String userID, String password) {
        int loginAdmin = 3;
        String msg = "";

        for(int i=0; i<UserData.size(); i++) {
            if(userID.equals(UserData.get(i)[0]) & password.equals(UserData.get(i)[10])) {
                msg = "Welcome "+UserData.get(i)[2];
                loginAdmin = Integer.parseInt(UserData.get(i)[11]);
            }
            else if(userID.equals(UserData.get(i)[1]) & password.equals(UserData.get(i)[10])) {
                msg = "Welcome "+UserData.get(i)[2];
                loginAdmin = Integer.parseInt(UserData.get(i)[11]);
            }
            else  {
                msg = "Incorrect username/password.";
                loginAdmin = 3;
            }
        }

        JOptionPane.showMessageDialog(null,
                msg,
                "Login",
                JOptionPane.WARNING_MESSAGE);

        return loginAdmin;
    }
}
