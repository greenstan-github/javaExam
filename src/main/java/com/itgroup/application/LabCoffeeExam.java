package com.itgroup.application;

import com.itgroup.utility.Utility;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LabCoffeeExam extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        String fxmlFile = Utility.FXML_PATH + "LabCoffeeExam.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));

        Parent cntr = fxmlLoader.load();
        Scene scene = new Scene(cntr);

        String myStyle = getClass().getResource(Utility.CSS_PATH + "coffeeStyle.css").toString();
        scene.getStylesheets().add(myStyle);

        stage.setTitle("Coffee Exam Programing");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
