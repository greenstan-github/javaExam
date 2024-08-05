package com.itgroup.controller;

import com.itgroup.bean.Person;
import com.itgroup.dao.PersonDao;
import com.itgroup.utility.Paging;
import com.itgroup.utility.Utility;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LabPaginationTestController implements Initializable {

    @FXML private Label pageStatus;
    @FXML private VBox vbox;
    @FXML private TableView<Person> tableView;
    @FXML private Pagination pagination;

    PersonDao dao = null;
    private String mode = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dao = new PersonDao();
        setTableColumns();
        setPagination(0);
    }

    private void setTableColumns() {
        // table column ops
        // tableView has the info of Person type, the element of array must the same one
        String[] field = {"num","firstName","lastName"};
        TableColumn tcol = null;
        for (int i = 0; i < field.length; i++) {
            tcol = tableView.getColumns().get(i);
            tcol.setCellValueFactory(new PropertyValueFactory<>(field[i]));
            if(field[i].equals("num")) tcol.setStyle("=fx-alignment:center;");
            if(field[i].equals("firstName") || field[i].equals("lastName"))  tcol.setStyle("=fx-alignment:left;");
        }
    }

    private void setPagination(int pageIndex) {
        System.out.println("pageIndex : " + pageIndex);
        // zero base : start with zero
        pagination.setCurrentPageIndex(pageIndex);
        // make pagination by using createPage method
        pagination.setPageFactory(this::createPage);

    }

    private Node createPage(int pageNumber) {
        int totalCnt = dao.getTotalCount(this.mode);
        //String _pageNumber, String _pageSize, int totalCount, String url, String mode, String keyword
        Paging pageInfo = new Paging(String.valueOf(pageNumber+1), "10", totalCnt, null, null, null);
        pageInfo.displayInformation();
        pagination.setPageCount(pageInfo.getTotalPage());
        fillTableData(pageInfo);
        vbox = new VBox(tableView);
        return vbox;
    }

    ObservableList<Person> dataList = null;

    private void fillTableData(Paging pageInfo) {
        // fill viewTable with data
        List<Person> personList = dao.getAllData(pageInfo.getBeginRow()-1, pageInfo.getEndRow());
        dataList = FXCollections.observableArrayList(personList);
        tableView.setItems(dataList);
        pageStatus.setText(pageInfo.getPagingStatus());
    }

}
