import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;



public class NewProp {
        private GridPane ques = new GridPane();
        List<TextField> options = new ArrayList<>();
    public NewProp(){
        setProps();
        setOptions();
        TextField line = new TextField("-------------------------------------------------------------------------------------------------------------");
        line.setStyle("-fx-font-size: 32px; -fx-text-fill: blue;");
        line.setEditable(false);
        ques.add(line, 0, 9);
    }
    public GridPane getQues(){
        return ques;
    }
    private void setOptions(){
        int placement = 4;
        for (int i = 1; i <= 5; i++) {
            Label optionLabel = new Label("Enter option " + i + " :");
            TextField option = new TextField();
            option.setPrefWidth(300);
            option.setPrefHeight(25);
            options.add(option);
            HBox optionBox = new HBox(10,optionLabel,option);
            ques.add(optionBox, 0, placement);
            placement ++;
        }
    }
    private void setProps(){
        Label titleLabel = new Label("Enter Title : ");
        TextArea title = new TextArea();
        title.setPrefRowCount(2);
        title.setWrapText(true);
        title.setPrefWidth(300);
        title.setPrefHeight(25);
        HBox titleBox = new HBox(10,titleLabel,title);

        Label descriptionLabel = new Label("Enter Description : ");
        TextArea description = new TextArea();
        description.setPrefRowCount(3);
        description.setWrapText(true);
        description.setPrefWidth(300);
        description.setPrefHeight(25);
        HBox Desbox = new HBox(10,descriptionLabel,description);


        Label selectLabel = new Label("Enter Number of Selections :");
        TextField selections = new TextField();
        selections.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("\\d*")){
                selections.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        selections.setPrefWidth(300);
        selections.setPrefHeight(25);
        HBox Selbox = new HBox(10,selectLabel,selections);


        ques.add(titleBox, 0, 1);
        ques.add(Desbox, 0, 2);
        ques.add(Selbox, 0, 3);
    }
    public String getTitle(){
         return ((TextArea)ques.getChildren().getFirst()).getText();
    }
    public String getDescription(){
        return ((TextArea)ques.getChildren().get(1)).getText();
    }
    public String getSelections(){
        return ((TextArea)ques.getChildren().get(2)).getText();
    }
    public String getNumOptions(){
        StringBuilder opts = new StringBuilder();
        for(TextField option : options){
            opts.append(option.getText()).append("\n");
        }
        return opts.toString();
    }

}
