package dataset;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserData {
    private String name, birthday, gender, address, email, icNo, passport, state;
    protected String password;
    private int telNo, age;
    private static String file = "src/dataset/data/userdata.txt";

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

    public void insertUserData() {
        try ()
        }
    }
}
