package com.itgroup.application;

import com.itgroup.utility.Utility;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LabPaginationTest extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        String fxmlFile = Utility.FXML_PATH + "LabPaginationTest.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));

        Parent cntr = fxmlLoader.load();
        Scene scene = new Scene(cntr);
        stage.setTitle("Pagination Test Programing");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
