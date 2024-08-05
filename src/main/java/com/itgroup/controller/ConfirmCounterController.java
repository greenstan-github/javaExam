package com.itgroup.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfirmCounterController implements Initializable {

    @FXML private TextArea textArea; //fx:id="textarea"
    @FXML private Button btnCfmCounter;

    private static int cnt;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cnt = 1;
    }

    public void cfmCounter(ActionEvent event) {
        String value = "확인 버튼 " + cnt + "번 눌러짐."; //btnCfmCounter.getText();
        textArea.appendText(value + "\n");
        cnt += 1;
    }
}
