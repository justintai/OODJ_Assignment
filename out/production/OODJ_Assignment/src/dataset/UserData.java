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
    private Stack<String[]> userData = new Stack<String[]>();



    public UserData() {
        readAll();
    }

    public UserData(String name, String birthday, String gender,
                    String address, String email, String icNo,
                    String passport, String state, String password,
                    int age, int telNo, int isAdmin) {
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
            System.out.println(wrt);
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

    public String[] updateUser() {
        List<Object> wrt = getAllStringValues();
        int num = 0;

        System.out.println(wrt);
        try(PrintWriter out = new PrintWriter(new FileWriter(Global.userFile,true));) {
            readAll();
            try(PrintWriter clean = new PrintWriter(new FileWriter(Global.userFile, false));)
            {
                clean.flush();
                clean.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            for(int i = 0; i < userData.size(); i++)
            {
                if(userData.get(i)[0].equals(icNo) || userData.get(i)[1].equals(passport))
                {
                    num = i;
                    for(int z = 0; z < userData.get(i).length; z++)
                    {
                        userData.get(i)[z] = wrt.get(z).toString();
                    }
                }

                for(int j = 0; j < userData.get(i).length; j++)
                {
                    out.write(userData.get(i)[j] + "%");
                }

                if(i<userData.size()-1)
                {
                    out.println();
                }
            }
            JOptionPane.showMessageDialog(new JFrame(), "User have been updated",
                    "Register", JOptionPane.INFORMATION_MESSAGE);
            return userData.get(num);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
