import java.util.ArrayList;

/*
 * This will hold the details of each individual proposition including a list of the options*/
class Proposition{
    private String title, description;
    private OptionsList options;
    private int numberOfSelections;
    private int numberOfOptions;

    public Proposition(String title, String description, OptionsList options, int numberOfSelections) {
        this.title = title;
        this.description = description;
        this.options = options;
        this.numberOfOptions = options.getOptions().size();
        this.numberOfSelections = numberOfSelections;
    }

    public String getDescription() {
        return description;
    }
    public String getTitle() {
        return title;
    }
    public OptionsList getOptions() {
        return this.options;
    }
    public int getNumberOfSelections() {return numberOfSelections;}
    public boolean getOptionSelected(int index){return options.selectOptions(index);}
}