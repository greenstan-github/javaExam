package com.itgroup.controller;

import com.itgroup.utility.Utility;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

import javax.swing.event.ChangeEvent;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CheckBoxRadioButtonController implements Initializable {

    @FXML private CheckBox changeImage01;
    @FXML private CheckBox changeImage02;
    @FXML private ImageView checkImageView;
    //@FXML private RadioButton breadRadio01, breadRadio02, breadRadio03;
    @FXML private ToggleGroup breadGroup;
    @FXML private ImageView radioImageView;
    @FXML private Button fxmlButtonExit;
    @FXML private Slider mySlider;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 슬라이더의 값이 변경되면 글자크기 변경함
        ChangeListener<Number> sliderListener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldNum, Number newNum){
                // observableValue : 값 변경이 된 객체
                // ondNum : 이전 값, newNum : 새로운 값
                String msg = "이점 값 : %f, 새로운 값 : %f\n";
                System.out.printf(msg, oldNum.doubleValue(), newNum.doubleValue());

                fxmlButtonExit.setFont(new Font(newNum.doubleValue()));
            }
        };
        mySlider.valueProperty().addListener(sliderListener);

        // 라디오 버튼이 토글되었을 때 반은 하는 리스너
        ChangeListener<Toggle> radioListner = new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle oldVal, Toggle newVal) {
                if(newVal != null){
                    // fxml 파일의 userData 속성을 참조하세요
                    String imgFile = Utility.IMAGE_PATH + newVal.getUserData().toString();
                    System.out.print("이미지 파일 이름 : " + imgFile + "\n");

                    String newImg = getClass().getResource(imgFile).toString();
                    Image targetImg = new Image(newImg);
                    radioImageView.setImage(targetImg);
                }
            }
        };
        breadGroup.selectedToggleProperty().addListener(radioListner);

    }

    public void handleChkAction(ActionEvent event) {
        // image changes as per checkbox checked
        String name= "";

        if(changeImage01.isSelected() && changeImage02.isSelected()){
            name = "geek-glasses-hair.gif";
        } else if(changeImage01.isSelected()){
            name = "geek-glasses.gif";
        } else if(changeImage02.isSelected()){
            name = "geek-hair.gif";
        } else {
            name = "geek.gif";
        }

        System.out.println("이미지 파일 이름 : \n");
        System.out.println(name);

        String url = super.getClass().getResource(Utility.IMAGE_PATH + name).toString();
        Image image = new Image(url);
        checkImageView.setImage(image);
    }

    public void handleButtonExit(ActionEvent event) {
        // if(confirm) exit program;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("종료 확인하기");
        alert.setHeaderText("지금 종료하려고 하시는군요.");
        alert.setContentText("프로그램을 종료하시겠습니까?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK) {
            System.out.println("OK 버튼을 눌러서 종료합니다.");
            Platform.exit();
        } else {
            System.out.println("Cancel을 누르셨군요.");
        }
    }
}
