package dataset;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserData {
    private String name, birthday, gender, address, email, icNo, passport, state;
    protected String password;
    private int telNo, age;
    private static String file = "src/dataset/data/userdata.txt";
    private int ln;

    public UserData(String name, String birthday, String gender, String address, String email, String icNo, String passport, String state, String password, int age, int telNo) {
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

    void countLines(){
        try {
            ln=0;
            BufferedReader in = readUserData();
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

    public BufferedReader readUserData() {
        try(BufferedReader in = new BufferedReader(new FileReader(file));) {
            System.out.println("File exists!");
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

    public void insertUserData() {
        try(PrintWriter out = new PrintWriter(new FileWriter(file));) {
            for (int i=0; i<ln; i++) {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
