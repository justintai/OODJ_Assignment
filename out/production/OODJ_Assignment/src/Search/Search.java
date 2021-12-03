package Search;

import dataset.VaccinationCentreData;
import dataset.VaccineData;

import java.util.Locale;
import java.util.Stack;
import java.util.regex.Pattern;

public class Search {

    private Stack<String[]> allData = new Stack<>();
    private Stack<Integer> indexNum = new Stack<>();

    public Search() {}

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
}
