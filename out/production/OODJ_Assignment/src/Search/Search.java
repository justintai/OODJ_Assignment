package Search;

import dataset.AppointmentData;
import dataset.UserData;
import dataset.VaccinationCentreData;
import dataset.VaccineData;

import java.util.Stack;

interface Search {
    public Stack<Integer> searchVaccine(String search);

    public Stack<Integer> searchCentre(String search);

    public Stack<Integer> searchPersonnel(String search);

    public Stack<Integer> searchPeople(String search);

    public Stack<Integer> searchAppointment(String search);
}
