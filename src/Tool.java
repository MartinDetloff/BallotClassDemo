import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Tool extends Application{
    private List<NewProp> allProps = new ArrayList<>();
    @Override
    public void start(Stage primaryStage)  {

        VBox questions = new VBox(20);
        NewProp newProp = new NewProp();
        GridPane firstQuestion = newProp.getQues();
        allProps.add(newProp);
        questions.getChildren().add(firstQuestion);

        Button submit = new Button("Submit");

        Button cancel = new Button("Cancel");

        Button add = new Button("+");

        HBox buttons = new HBox(10,submit,cancel,add);
        buttons.setAlignment(Pos.CENTER);
        questions.getChildren().add(buttons);

        cancel.setOnAction(e -> {System.exit(0);});
        add.setOnAction(e -> {addProp(questions);});
        submit.setOnAction(e -> {
            String mark = createMarkup();
            System.out.println(mark);
            try {
                FileWriter markUp = new FileWriter("Makrkup.txt");
                markUp.write(mark);
                markUp.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        ScrollPane scroll = new ScrollPane(questions);
        scroll.setFitToWidth(true);
        scroll.setPannable(true);

        Scene scene = new Scene(scroll, 500, 500);
        primaryStage.setTitle("Mark up Tool");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void addProp(VBox questions) {
        NewProp newProp = new NewProp();
        questions.getChildren().add(questions.getChildren().size() - 1,newProp.getQues());
        allProps.add(newProp);


    }

    private String createMarkup(){
        StringBuilder full = new StringBuilder();
        full.append("/b\n");
        for(NewProp prop : allProps ){
            full.append(eachProp(prop));
        }
        full.append("//b\n");
        return full.toString();
    }

    private String eachProp(NewProp prop){
        StringBuilder eachProp = new StringBuilder();
        String check = "good";
        if(!prop.getTitle().isEmpty() || !prop.getDescription().isEmpty() || !prop.getSelections().isEmpty()) {
            eachProp.append("/p\n");
            eachProp.append("/t\n").append(prop.getTitle()).append("\n//t\n");
            eachProp.append("/d\n").append(prop.getDescription()).append("\n//d\n");
            eachProp.append("/n\n").append(prop.getSelections()).append("\n//n\n");
            check = prop.getNumOptions();
            eachProp.append(prop.getNumOptions());
            eachProp.append("//p\n");
        }
        if(check.equalsIgnoreCase("None"))
        {
            return "";
        }
        return eachProp.toString();
    }
}
