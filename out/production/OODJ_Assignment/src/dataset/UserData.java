package dataset;

import javax.swing.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserData {
    private String name, birthday, gender, address, email, icNo, passport, state;
    protected String password;
    private int telNo, age, isAdmin = 0;
    private static String file = "src/dataset/data/userdata.txt";
    public int ln, userNo=0;

    public UserData() {};

    public UserData(String name, String birthday, String gender,
                    String address, String email, String icNo,
                    String passport, String state, String password, int age, int telNo) {
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
    }

    public RandomAccessFile readUserData() {
        try(RandomAccessFile raf = new RandomAccessFile(file, "rw");) {
            ln=0;
            for(int i=0;raf.readLine()!=null;i++){
                ln++;
            }
            System.out.println("number of lines:"+ln);

            for(int i=0;i<ln;i+=13) {
                userNo++;
            }
            System.out.println("User number: "+userNo);

            return(raf);
        }
        catch (FileNotFoundException ex) {
            try {
                PrintWriter out = new PrintWriter(new FileWriter(file));
                System.out.println("File wrote");
                readUserData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (IOException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void insertUserData() {
        try(PrintWriter out = new PrintWriter(new FileWriter(file, true));) {

            if(ln>0) {
                out.print("\r\n");
            }

            out.println("ICNo:"+icNo);
            out.println("PassportNo:"+passport);
            out.println("Name:"+name);
            out.println("Birthday:"+birthday);
            out.println("gender:"+gender);
            out.println("Age:"+age);
            out.println("TelNo:"+telNo);
            out.println("Email:"+email);
            out.println("Address:"+address);
            out.println("State:"+state);
            out.println("Password:"+password);
            out.println("isAdmin:"+isAdmin);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkLogin(String usr, String psd) {
        RandomAccessFile data = readUserData();

        try(RandomAccessFile raf = readUserData();) {
            for(int i=0; i<ln; i+=13) {
                String userID = raf.readLine().substring(5);
                String passport = raf.readLine().substring(11);
                String userName = raf.readLine().substring(5);
                for (int j = 1; j < 8; j++)
                    raf.readLine();

                String password = raf.readLine().substring(9);

                if (usr.equals(userID) & psd.equals(password)) {
                    JOptionPane.showMessageDialog(null, "Welcome " + userName);
                    break;
                } else if (usr.equals(passport) & psd.equals(password)) {
                    JOptionPane.showMessageDialog(null, "Welcome " + userName);
                    break;
                } else if (i == (ln - 12)) {
                    JOptionPane.showMessageDialog(null, "Incorrect username/password");
                    break;
                }

                for(int z=0; z<2; z++)
                    raf.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
