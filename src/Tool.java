import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;


public class Tool extends Application{
    private List<NewProp> allProps = new ArrayList<>();
    @Override
    public void start(Stage primaryStage) {
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
        full.append("/b\n");
        return full.toString();
    }

    private String eachProp(NewProp prop){
        StringBuilder eachProp = new StringBuilder();
        eachProp.append("/p\n");
        eachProp.append("/t ").append(prop.getTitle()).append("/t").append("\n");
        eachProp.append("/d ").append(prop.getDescription()).append("/d").append("\n");
        eachProp.append("/s ").append(prop.getSelections()).append("/s").append("\n");
        eachProp.append("/o ").append(prop.getNumOptions()).append("/o").append("\n");
        eachProp.append("/p\n");
        return eachProp.toString();
    }
}
