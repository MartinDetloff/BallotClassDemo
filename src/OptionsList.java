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
    private int[] indexSelected;

    public OptionsList(ArrayList<String> c, int numberOfSelections){
        maxSelections = numberOfSelections;
        currentSelections = 0;
        indexSelected = new int[numberOfSelections];
        candidates = c;
    }

    public ArrayList<String> getOptions(){
        return this.candidates;
    }

    public void insertOptions(String opt, int size){
        candidates.add(opt);
        if(candidates.size() == size){/*TODO: handle option insertion*/}
    }

    public boolean selectOptions(int select){
        if((currentSelections < maxSelections) && (candidates.size() < select)){
            indexSelected[currentSelections] = select;
            currentSelections++;
        }else {/*TODO: handle this error*/}
        return (currentSelections < maxSelections);
    }

}

