package client.Search;

import client.Dataset.VaccineData;

import java.util.Stack;

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
}
