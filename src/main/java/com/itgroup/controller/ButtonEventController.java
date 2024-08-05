package com.itgroup.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class ButtonEventController implements Initializable {

    @FXML private TextArea textArea; //fx:id="textarea"
    @FXML private Button btnOK;
    @FXML private Button btnCancel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void btnOKAction(ActionEvent event){
        String value = btnOK.getText();
        textArea.appendText(value + "\n");
    }

    public void btnCancelAction(ActionEvent event){
        String value = btnCancel.getText();
        textArea.appendText(value + "\n");
    }

}
