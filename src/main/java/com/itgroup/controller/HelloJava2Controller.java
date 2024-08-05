package com.itgroup.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;

public class HelloJava2Controller {
    public void helloJavaFx2(ActionEvent event) {
        System.out.println("HelloJava2 버튼을 누르셨습니다. bye bye ^^");
        Platform.exit();

    }
}
