package Search;

import dataset.AppointmentData;
import dataset.VaccineData;

import java.util.Stack;

public class PeopleSearch implements Search {
    private Stack<String[]> allData = new Stack<>();
    private Stack<Integer> indexNum = new Stack<>();

    @Override
    public Stack<Integer> searchVaccine(String search) {
        return null;
    }

    @Override
    public Stack<Integer> searchCentre(String search) {
        return null;
    }

    @Override
    public Stack<Integer> searchPersonnel(String search) {
        return null;
    }

    @Override
    public Stack<Integer> searchPeople(String search) {
        return null;
    }

    public Stack<Integer> searchAppointment(String search) {
        AppointmentData appointmentData = new AppointmentData();
        allData = appointmentData.getAppointmentData();
        int index=-1;

        for(int i=0; i<allData.size(); i++) {
            if(allData.get(i)[0].equals(search) || allData.get(i)[1].equals(search)) {
                if(indexNum.search(i) == -1){
                    indexNum.push(i);
                }
            }
        }

        return indexNum;
    }
}
