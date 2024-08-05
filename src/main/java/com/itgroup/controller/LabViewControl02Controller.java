package com.itgroup.controller;

import com.itgroup.bean.Article;
import com.itgroup.bean.MyObject;
import com.itgroup.dao.ArticleDao;
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

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

public class LabViewControl02Controller implements Initializable {

    @FXML ListView listView;
    @FXML TableView<Article> tableView; // unless others regards as Object
    @FXML ImageView imageView;
    @FXML Button btnOk;
    @FXML Button btnCancel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ArticleDao dao = new ArticleDao();

        List<String> cateList = dao.selectDistinctCategory();
        ObservableList<String> dataList = FXCollections.observableArrayList(cateList);
        listView.setItems(dataList);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // set the value of each column of tableView to be linked with Article object
        // #0 col is name variable, #1 col is image variable

        TableColumn tcName = (TableColumn)tableView.getColumns().get(0); // in tableView
        tcName.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn tcImage = (TableColumn)tableView.getColumns().get(1); // in tableView
        tcImage.setCellValueFactory(new PropertyValueFactory<>("image"));

// https://docs.oracle.com/javafx/2/ui_controls/table-view.htm
//        TableColumn firstSubCol = new TableColumn("Primary");
//        TableColumn secondSubCol = new TableColumn("Secondary");
//        tcImage.getColumns().addAll(firstSubCol, secondSubCol);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ChangeListener<String> listListner = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldVal, String newVal) {
                String msg = "oldVal : " + oldVal + ", newVal : " + newVal;
                System.out.println(msg);

                List<Article> articleList = dao.selectDataByCategory(newVal);
                ObservableList tableData = FXCollections.observableArrayList(articleList); // data into tableView
                tableView.setItems(tableData);
                System.out.println(tableData);

                if(tableData != null){
                    tableView.setItems(tableData);
                }
            }
        };

        // 리스트 뷰의 아이템 정보가 변경되었을 때 listListner가 동작하도록 합니다.
        listView.getSelectionModel().selectedItemProperty().addListener(listListner);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ChangeListener<Article> tableListner = new ChangeListener<Article>() {
            @Override
            public void changed(ObservableValue<? extends Article> observableValue, Article oldAtcle, Article newArticle) {

                String msg = "oldVal : " + oldAtcle + ", newVal : " + newArticle;
                System.out.println(msg);

                // 처음의 oldVal는 null
                if(newArticle != null){
                    String imgPathFile = Utility.IMAGE_PATH + newArticle.getImage();
                    System.out.println("이미지 파일 :" + imgPathFile);
                    Image someImg = new Image(getClass().getResource(imgPathFile).toString());
                    imageView.setImage(someImg);
                }
            }
        };

        // 테이블 뷰의 아이템 정보가 변경되었을 때 listListner가 동작하도록 합니다.
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

        Article bean = tableView.getSelectionModel().getSelectedItem();
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

//    TableColumn.seCellFactory(new Callback<TableColumn<CheckDo, String>, TableCell<CheckDo, String>>()
//    {
//
//        @Override
//        public TableCell<CheckDo, String> call(TableColumn<CheckDo, String> p)
//        {
//
//            return new TableCell<CheckDo,String>()
//            {
//                @Override
//                public void updateItem(String item, boolean empty)
//                {
//                    super.updateItem(item, empty);
//                    if (!isEmpty())
//                    {
//                        System.out.println("Value of cell ="+this.getText());
//
//                        setText(item);
//                    }
//                }
//            };
//        }
//    });
}
