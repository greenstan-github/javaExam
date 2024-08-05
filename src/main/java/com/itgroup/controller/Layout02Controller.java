package com.itgroup.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Layout02Controller implements Initializable {
    // hot today 2024-08-05
    // let's cool lunch today
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(getClass().getResource("132546"));
    }

    public void buttonClicked(ActionEvent event) {
        System.out.println("버튼을 누르셨습니다.");
    }
}
