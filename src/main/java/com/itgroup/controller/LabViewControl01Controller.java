package com.itgroup.controller;

import com.itgroup.bean.MyObject;
import com.itgroup.utility.Utility;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

import javax.swing.event.ChangeEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class LabViewControl01Controller implements Initializable {

    @FXML ListView listView;
    @FXML TableView<MyObject> tableView; // unless others regards as Object
    @FXML ImageView imageView;
    @FXML Button btnOk;
    @FXML Button btnCancel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> dataList = FXCollections.observableArrayList("어린이", "음료수", "빵");
        listView.setItems(dataList);

        // set the value of each column of tableView to be linked with MyObject
        // #0 col is name variable, #1 col is image variable
        TableColumn tcName = (TableColumn)tableView.getColumns().get(0);
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn tcImage = (TableColumn)tableView.getColumns().get(1);
        tcImage.setCellValueFactory(new PropertyValueFactory<>("image"));

        ChangeListener<Number> listListner = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldNum, Number newNum){
                // observableValue : 값 변경이 된 객체
                // ondNum : 이전 값, newNum : 새로운 값
                String msg = "이점 값 : %f, 새로운 값 : %f\n";
                System.out.printf(msg, oldNum.doubleValue(), newNum.doubleValue());

                int menu = newNum.intValue();
                System.out.println("선택한 인덱스 : " + menu);
                // listListner.setFont(new Font(newNum.doubleValue()));

                ObservableList tableData = null; // data to insert into tableView

                switch(menu){
                    case 0: // 어린이
                        tableData = FXCollections.observableArrayList(
                                new MyObject("어린이01","child01.jpg"),
                                new MyObject("어린이02","child02.jpg"),
                                new MyObject("어린이03","child03.jpg"),
                                new MyObject("어린이04","child04.jpg"),
                                new MyObject("어린이05","child05.jpg"),
                                new MyObject("어린이06","child06.jpg"),
                                new MyObject("어린이07","child07.png"),
                                new MyObject("어린이08","child08.png")
                        );
                        break;
                    case 1: // 음료수
                        tableData = FXCollections.observableArrayList(
                                new MyObject("아메리카노01","americano01.png"),
                                new MyObject("아메리카노02","americano02.png"),
                                new MyObject("카프치노01","cappuccino01.png"),
                                new MyObject("카프치노01","cappuccino02.png"),
                                new MyObject("딸기주스","berry_juice.jpg"),
                                new MyObject("체리주스","cherry_juice_640.png")

                        );
                        break;
                    case 2: // 빵
                        tableData = FXCollections.observableArrayList(
                                new MyObject("차바타01","ciabatta_01.png"),
                                new MyObject("차바타01","ciabatta_02.png"),
                                new MyObject("차바타01","ciabatta_03.png"),
                                new MyObject("크로와상01","croissant_01.png"),
                                new MyObject("크로와상02","croissant_02.png"),
                                new MyObject("크로와상03","croissant_03.png")
                        );
                        break;
                }

                if(tableData !=null){
                    tableView.setItems(tableData);
                }

            }
        };

        // 리스트 뷰의 색인 정보가 변경되었을 때 listListner가 동작하도록 합니다.
        listView.getSelectionModel().selectedIndexProperty().addListener(listListner);


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ChangeListener<MyObject> tableListner = new ChangeListener<MyObject>() {
            public void changed(ObservableValue<? extends MyObject> observableValue, MyObject oldObj, MyObject newObj) {
                if(newObj != null){
                    String imgFile = Utility.IMAGE_PATH + newObj.getImage();
                    System.out.println("이미지 파일 :" + imgFile);
                    Image someImg = new Image(getClass().getResource(imgFile).toString());
                    imageView.setImage(someImg);
                }
            }
        };

        tableView.getSelectionModel().selectedItemProperty().addListener(tableListner);
    }

    public void handleBtnOkAction(ActionEvent event) {
        Object item = listView.getSelectionModel().getSelectedItems();
        if(item == null){
            String[] msg = {"리스트 선택 여부","리스트 항목 미체크", "리스트 뷰에서 항목을 선택해 주세요."};
            Utility.showAlert(Alert.AlertType.INFORMATION,msg);
            System.out.println("리스트 뷰 항목을 선택해 주세요");
        } else {
            System.out.println("리스트 뷰 선택된 항목 : " + item);
        }

        MyObject bean = tableView.getSelectionModel().getSelectedItem();
        if(bean != null) {
            System.out.println("선택된 뭄목 : " + bean.getName());
            System.out.println("선택된 이미지 : " + bean.getImage());
        } else {
            String[] msg = {"테이블 선택 여부","테이블 항목 미체크", "테이블 뷰에서 항목을 선택해 주세요."};
            Utility.showAlert(Alert.AlertType.INFORMATION,msg);
        }

    }

    public void handleBtnCancelAction(ActionEvent event) {
    }

    public void handleBtnCloseAction(ActionEvent event) {
        Platform.exit();;
    }
}
