package Search;

import dataset.AppointmentData;
import dataset.UserData;
import dataset.VaccinationCentreData;
import dataset.VaccineData;

import java.util.Locale;
import java.util.Stack;
import java.util.regex.Pattern;

public class PersonnelSearch implements Search {

    private Stack<String[]> allData = new Stack<>();
    private Stack<Integer> indexNum = new Stack<>();

    public PersonnelSearch() {}

    public Stack<Integer> searchVaccine(String search) {
        VaccineData vacData = new VaccineData();
        allData = vacData.getVaccineData();
        int index=-1;

        for(int i=0; i<allData.size(); i++) {
            for(int j=0; j<allData.get(i).length-1; j++) {
                if(allData.get(i)[j].toLowerCase().contains(search.toLowerCase())) {
                    if(indexNum.search(i) == -1){
                        indexNum.push(i);
                    }
                }
            }
        }

        return indexNum;
    }

    public Stack<Integer> searchCentre(String search) {
        VaccinationCentreData vaccinationCentreData = new VaccinationCentreData();
        allData = vaccinationCentreData.getCentreData();
        int index=-1;

        for(int i=0; i<allData.size(); i++) {
            for(int j=0; j<allData.get(i).length-2; j++) {
                /*j != 3 to don't search for address*/
                if(j != 3) {
                    if(allData.get(i)[j].toLowerCase().contains(search.toLowerCase())) {
                        if(indexNum.search(i) == -1){
                            indexNum.push(i);
                        }
                    }
                }
            }
        }

        return indexNum;
    }

    public Stack<Integer> searchPersonnel(String search) {
        UserData userData = new UserData();
        allData = userData.getUserData();
        int index=-1;

        if(search != null && search != "null") {
            for(int i=0; i<allData.size(); i++) {
                for(int j=0; j<allData.get(i).length-9; j++) {
                    if(allData.get(i)[j].toLowerCase().contains(search.toLowerCase()) && allData.get(i)[11].equals("1")) {
                        if(indexNum.search(i) == -1){
                            indexNum.push(i);
                        }
                    }
                }
            }
        }
        else {
            for(int i=0; i<allData.size(); i++) {
                if(allData.get(i)[11].equals("1")) {
                        indexNum.push(i);
                }
            }
        }


        return indexNum;
    }

    public Stack<Integer> searchPeople(String search) {
        UserData userData = new UserData();
        allData = userData.getUserData();
        int index=-1;

        if(search != null && search != "null") {
            for(int i=0; i<allData.size(); i++) {
                for(int j=0; j<allData.get(i).length-9; j++) {
                    if(allData.get(i)[j].toLowerCase().contains(search.toLowerCase()) && allData.get(i)[11].equals("0")) {
                        if(indexNum.search(i) == -1){
                            indexNum.push(i);
                        }
                    }
                }
            }
        }
        else {
            for(int i=0; i<allData.size(); i++) {
                if(allData.get(i)[11].equals("0")) {
                    indexNum.push(i);
                }
            }
        }


        return indexNum;
    }

    public Stack<Integer> searchAppointment(String search) {
        AppointmentData appointmentData = new AppointmentData();
        allData = appointmentData.getAppointmentData();
        int index=-1;

        if(search != null && search != "null") {
            for(int i=0; i<allData.size(); i++) {
                for(int j=0; j<allData.get(i).length; j++) {
                    if(j != 4 && j<10) {
                        if(allData.get(i)[j].toLowerCase().contains(search.toLowerCase())) {
                            if(indexNum.search(i) == -1){
                                indexNum.push(i);
                            }
                        }
                    }
                    else if(j == 10 || j == 11) {
                        if(search.toLowerCase().equals("1st dose")) {
                            if (allData.get(i)[10].equals("0") &&
                                    allData.get(i)[12].equals("1") &&
                                    allData.get(i)[11].equals("0")){
                                if(indexNum.search(i) == -1){
                                    indexNum.push(i);
                                }
                            }
                        }
                        else if(search.toLowerCase().equals("2nd dose")) {
                            if (allData.get(i)[10].equals("1") &&
                                    allData.get(i)[12].equals("1") &&
                                    allData.get(i)[11].equals("0")){
                                if(indexNum.search(i) == -1){
                                    indexNum.push(i);
                                }
                            }
                        }
                        else if(search.toLowerCase().equals("complete")) {
                            if (allData.get(i)[10].equals("1") &&
                                    allData.get(i)[12].equals("1") &&
                                    allData.get(i)[11].equals("1")){
                                if(indexNum.search(i) == -1){
                                    indexNum.push(i);
                                }
                            }
                        }
                    }
                    else if(j == 12) {
                        if(search.toLowerCase().equals("confirm")) {
                            if (allData.get(i)[j].equals("1")){
                                if(indexNum.search(i) == -1){
                                    indexNum.push(i);
                                }
                            }
                        }
                        else if(search.toLowerCase().equals("reject")) {
                            if (allData.get(i)[j].equals("2")){
                                if(indexNum.search(i) == -1){
                                    indexNum.push(i);
                                }
                            }
                        }
                        else if(search.toLowerCase().equals("pending")) {
                            if (allData.get(i)[j].equals("0")){
                                if(indexNum.search(i) == -1){
                                    indexNum.push(i);
                                }
                            }
                        }
                    }

                }
            }
        }
        else {
            for(int i=0; i<allData.size(); i++) {
                if(allData.get(i)[11].equals("0")) {
                    indexNum.push(i);
                }
            }
        }


        return indexNum;
    }
}
