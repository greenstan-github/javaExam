package com.itgroup.application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HelloJavaFX2 extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        String fxmlFile = "/com/itgroup/fxml/"+"HelloJavaFX2.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));

        Parent container = fxmlLoader.load();
        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        // launch 메소드 호출시 자동으로 start 메소드 호출
        launch(args);
    }
}
