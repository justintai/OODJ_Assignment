package dataset;

import Global.Global;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserData {
    private String name, birthday, gender, address, email, icNo, passport, state;
    protected String password;
    private int telNo, age, isAdmin = 0;
    private static String file = Global.userFile;
    private int ln;

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

    static String stringConcat( String input )
    {
        String concat = input;
        concat.trim();
        return concat;
    }

    public List<Object> getAllStringValues()
    {
        List<Object> summary = new ArrayList<>();
        String addr = stringConcat(address);
        summary.add(icNo);
        summary.add(passport);
        summary.add(name);
        summary.add(birthday);
        summary.add(gender);
        summary.add(age);
        summary.add(telNo);
        summary.add(email);
        summary.add(addr);
        summary.add(state);
        summary.add(password);
        summary.add(isAdmin);

        return summary;
    }

    void countLines(){
        try {
            ln=0;
            BufferedReader in = readAll();
            for(int i=0;in.readLine()!=null;i++){
                ln++;
            }
            System.out.println("number of lines:"+ln);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public BufferedReader readAll() {
        try(BufferedReader in = new BufferedReader(new FileReader(file));) {
            System.out.println("File exists!");
            System.out.println(file);
            return(in);
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void writeAll() {
        File user = new File(file);
        if(user.exists())
        {
            List<Object> wrt = getAllStringValues();
            try(PrintWriter out = new PrintWriter(new FileWriter(file,true));) {
                out.println();
                for (Object str : wrt) {
                    out.write(str + "|");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            List<Object> wrt = getAllStringValues();
            try(PrintWriter out = new PrintWriter(new FileWriter(file));) {
                for (Object str : wrt) {
                    out.write(str + "|");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
