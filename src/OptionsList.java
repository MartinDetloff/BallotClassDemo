import java.util.ArrayList;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Will contain the candidates, a boolean to confirm the selection and the index for the selection*/
class OptionsList{
    private ArrayList<String> candidates;
    private int maxSelections;
    private int currentSelections;
    private ArrayList<Integer> indexSelected;

    public OptionsList(ArrayList<String> c, int numberOfSelections){
        maxSelections = numberOfSelections;
        currentSelections = 0;
        indexSelected = new ArrayList<>();
        candidates = c;
    }

    public ArrayList<String> getOptions(){
        return this.candidates;
    }

    public void insertOptions(String opt, int size){
        candidates.add(opt);
        if(candidates.size() == size){/*TODO: handle option insertion*/}
    }

    public boolean selectOptions(int selectionIndex){
        if((currentSelections < maxSelections) && (candidates.size() < selectionIndex)){
            indexSelected.add(selectionIndex); // add the index to the array of selected indexes
            currentSelections++; // update the current selections
        }else if (currentSelections >= maxSelections){
            /*TODO: handle this error*/}
        return (currentSelections < maxSelections);
    }

    public void clearOptions(){
        indexSelected.clear();
    }

}

