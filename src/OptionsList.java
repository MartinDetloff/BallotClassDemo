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
    private ArrayList<Boolean> selected;


    /**
     * Constructor to init the Options list
     * @param options the options ie List (Option1 , Option2, Option3)
     * @param numberOfSelections the number of max selections for the current proposition
     */
    public OptionsList(ArrayList<String> options, int numberOfSelections){
//        System.out.println("This is the number of selections " + numberOfSelections);
        this.maxSelections = numberOfSelections;
        this.currentSelections = 0;
        this.indexSelected = new ArrayList<>();
        this.options = options;
        this.selected = new ArrayList<>();
        fillBooleansList();
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
    public String getSelectedOption(int index){
        return this.options.get(index);
    }

    /**
     * Method to fill the booleans list with all false
     */
    private void fillBooleansList(){
        for (String option : options){
            selected.add(false);
        }
    }
    /**
     * Method to select an option
     * @param selectionIndex the selected index
     * @return ..
     */
    public boolean selectOptions(int selectionIndex){
        if((currentSelections < maxSelections) && (options.size() > selectionIndex)){
            selected.set(selectionIndex, true); // add the index to the array of selected indexes
            System.out.println("Option selected " + getSelectedOption(selectionIndex));
            currentSelections++; // update the current selections
        }else if (currentSelections >= maxSelections){
            System.out.println("Cannot add anymore options");
        }else{
            System.out.println("Invalid, selected index is out of bounds");
        }
        return (currentSelections < maxSelections + 1);
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

