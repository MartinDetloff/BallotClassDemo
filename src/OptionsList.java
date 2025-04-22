import java.util.ArrayList;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Will contain the candidates, a boolean to confirm the selection and the index for the selection*/
class OptionsList{
    private ArrayList<String> options;
    private int maxSelections;
    private int currentSelections;
    private ArrayList<Integer> indexSelected;


    /**
     * Constructor to init the Options list
     * @param options the options ie List (Option1 , Option2, Option3)
     * @param numberOfSelections the number of max selections for the current proposition
     */
    public OptionsList(ArrayList<String> options, int numberOfSelections){
        this.maxSelections = numberOfSelections;
        this.currentSelections = 0;
        this.indexSelected = new ArrayList<>();
        this.options = options;

    }

    /**
     * Simple getter for the options list
     * @return list of options
     */
    public ArrayList<String> getOptions(){
        return this.options;
    }

    /**
     * Simple getter for the indexes selected
     * @return the indexes selected
     */
    public ArrayList<Integer> getIndexSelected(){
        return this.indexSelected;
    }

//    public void insertOptions(String opt, int size){
//        candidates.add(opt);
//        if(candidates.size() == size){}
//    }

    /**
     * Method to select an option
     * @param selectionIndex the selected index
     * @return ..
     */
    public boolean selectOptions(int selectionIndex){
        if((currentSelections < maxSelections) && (options.size() < selectionIndex)){
            indexSelected.add(selectionIndex); // add the index to the array of selected indexes
            currentSelections++; // update the current selections
        }else if (currentSelections >= maxSelections){
            System.out.println("Cannot add anymore options");
        }else{
            System.out.println("Invalid, selected index is out of bounds");
        }
        return (currentSelections < maxSelections);
    }

    /**
     * Method to clear the options to make space for new selections
     */
    public void clearOptions(){
        indexSelected.clear();
    }

    /**
     * Method to replace a selected value for a new option should the
     * voter decide to change their mind
     * */
    public void replace(int replacedI, int replacement){
        indexSelected.remove(replacedI);
        indexSelected.add(replacement);
    }
}

