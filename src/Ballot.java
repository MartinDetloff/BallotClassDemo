import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

class Ballot{
    List<Proposition> propositions = new ArrayList<>();

    // This is going to take in a file and read from it and store all the data here
    public Ballot(File markUpFile) throws FileNotFoundException {
        // just call a method to read in the file line by line
        this.propositions = extractInfo(markUpFile);
    }

    /**
     * Method to parse in the file. It will return a list of propositions
     * @param markUpFile the markup file.
     * @return List of propositions
     * @throws FileNotFoundException ..
     */
    // Method to loop through the file and extract the information
    private ArrayList<Proposition> extractInfo(File markUpFile) throws FileNotFoundException {
        Scanner myReader = new Scanner(markUpFile);
        ArrayList<Proposition> propositions = new ArrayList<>();

        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();

            switch (line){
                case "/b":
                    System.out.println("Start of ballot");
                    while(myReader.hasNext()){
                        String nextLine = myReader.nextLine();
                        if (nextLine.equals("//b")){
                            System.out.println("End of ballot");
                            System.out.println();
                            break;
                        }

                        if (nextLine.equals("/p")){
                            StringBuilder title =  new StringBuilder();
                            StringBuilder description =  new StringBuilder();
                            ArrayList<String> options = new ArrayList<>();
                            int numberOfSelections = 0;

                            System.out.println("Start of proposition");

                            while (myReader.hasNext()){
                                String nextLine2 = myReader.nextLine();
                                if(nextLine2.equals("//p")){
                                    System.out.println("Title for proposition: " + title.toString());
                                    System.out.println("Description for proposition: " + description.toString());
                                    System.out.println("Options for proposition: " + options.get(0).toString());
                                    System.out.println("End of proposition");
                                    System.out.println();
                                    propositions.add(new Proposition(
                                            title.toString(),
                                            description.toString(),
                                            new OptionsList(options, numberOfSelections),
                                            numberOfSelections));
                                    break;
                                }
                                if (nextLine2.equals("/t")){
                                    System.out.println("Start of title");

                                    while (myReader.hasNext()){
                                        String nextLine3 = myReader.nextLine();

                                        if(nextLine3.equals("//t")){
                                            System.out.println(title.toString());
                                            System.out.println("End of title");
                                            System.out.println();
                                            break;
                                        }
                                        else {
                                            title.append(nextLine3);
                                        }
                                    }
                                }

                                if (nextLine2.equals("/d")){
                                    System.out.println("Start of description");

                                    while (myReader.hasNext()){
                                        String nextLine4 = myReader.nextLine();
                                        if(nextLine4.equals("//d")){
                                            System.out.println(description.toString());
                                            System.out.println("End of description");
                                            System.out.println();
                                            break;
                                        }
                                        else {
                                            description.append(nextLine4);

                                        }

                                    }
                                }

                                if (nextLine2.equals("/n")){
                                    System.out.println("Start of number of options");
                                    while (myReader.hasNext()){
                                        String nextLine5 = myReader.nextLine();
                                        if(nextLine5.equals("//n")){
                                            System.out.println("End of number of options");
                                            System.out.println();
                                            break;
                                        }
                                        else {
                                            numberOfSelections = Integer.parseInt(nextLine5);
                                            System.out.println(numberOfSelections);
                                        }
                                    }
                                }

                                if (nextLine2.equals("/o")){
                                    System.out.println("Start of option");
                                    String option = "";
                                    while (myReader.hasNext()){
                                        String nextLine6 = myReader.nextLine();
                                        if(nextLine6.equals("//o")){
                                            System.out.println(option.toString());
                                            options.add(option);
                                            System.out.println("End of of option");
                                            System.out.println();
                                            break;
                                        }
                                        else {
                                            option = nextLine6;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;

            }
        }
        return propositions;
    }

    /**
     * Method to print out the ballot to make sure it is working
     * @return null
     */
    public String toString(){
        System.out.println("The ballot is as follows: ");
        System.out.println();
        int j = 1;
        for (Proposition prop : propositions){
            System.out.println("Proposition " + j);
            System.out.println("Title: " + prop.getTitle());
            System.out.println("Description: " + prop.getDescription());
            int i = 1;
            for (String option : prop.getOptions().getOptions()){
                System.out.println("Option " + i + ": " + option);
                i++;
            }
            System.out.println("End Proposition");
            System.out.println();
            j++;
        }
        System.out.println("End Ballot");
        System.out.println();
        return null;
    }




    // todo: Need a method to print status of the ballot
}
