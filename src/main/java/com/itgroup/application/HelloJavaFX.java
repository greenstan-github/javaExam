package com.itgroup.application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Arrays;

public class HelloJavaFX extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        VBox container = new VBox();
        container.setPrefWidth(550);
        container.setPrefHeight(350);
        container.setAlignment(Pos.CENTER);
        container.setSpacing(20);

        Label label = new Label();
        label.setText("Hello Java FX");
        label.setFont(new Font(30));

        Button button = new Button();
        button.setText("확인");
        button.setOnAction((event)->{
            System.out.println(event.toString());
            String text = label.getText();
            System.out.println(text + "ㅎㅎㅎ");
            Platform.exit(); // end of application
        });

        container.getChildren().add(label);
        container.getChildren().add(button);

        Scene scene = new Scene(container);
        stage.setScene(scene);
        stage.setTitle("First Application");

        stage.show();
    }

    public static void main(String[] args) {
        // launch 메소드 호출시 자동으로 start 메소드 호출
        launch(args);
    }
}
