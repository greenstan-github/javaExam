package com.itgroup.application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class Layout01 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        HBox container = new HBox();

        // insets class : 컨테이너 내부의 요소와 경계 사이의 간격(여백)을 정의 해주는 클래스
        container.setPadding(new Insets(20));
        container.setSpacing(30);

        String myStyle = "-fx-background-color:#336699;-fx-opacity:0.7;-fx-font-size:16;-fx-font-weight:700;";
        container.setStyle(myStyle);

        TextField textField = new TextField();
        textField.setPrefWidth(200);
        Button btn = new Button();
        btn.setText("확인");
        btn.setPrefWidth(60);
        btn.setOnAction((event)->{
            String text = textField.getText();
            System.out.println(text + "ㅎㅎㅎ");
            Platform.exit();
        });

        container.getChildren().add(textField);
        btn.setText("Verdana");
        container.getChildren().add(btn);

        Scene scene = new Scene(container, 640,420);

        stage.setScene(scene);
        stage.setTitle("레이아웃 01");
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
