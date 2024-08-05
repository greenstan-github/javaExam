package com.itgroup.controller;

import com.itgroup.bean.Product;
import com.itgroup.dao.ProductDao;
import com.itgroup.utility.Paging;
import com.itgroup.utility.Utility;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.awt.SystemColor.window;

public class LabCoffeeExamController implements Initializable {

    private ProductDao dao = null;
    private String mode = null; // for field search
    ObservableList<Product> dataList = null;

    @FXML private ComboBox<String> fieldSearch;
    @FXML private Label lblChoice;
    @FXML private Label pageStatus;
    @FXML private TableView<Product> productTable;
    @FXML private ImageView imageView;
    @FXML private Pagination pagination;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.dao = new ProductDao();
        setTableColumns();
        setPagination(0); // page index

        // image view as per table click
        // 테이블 뷰의 1행을 클릭하면, 우측에 이미지를 보여 줍니다.
        ChangeListener<Product> tableListener = new ChangeListener<Product>() {
            @Override
            public void changed(ObservableValue<? extends Product> observableValue, Product oldValue, Product newValue) {
                if(newValue != null){
                    System.out.println("상품 정보");
                    System.out.println(newValue);

                    String imageFile = ""; // 해당 이미지의 fullPath + 이미지 이름
                    if(newValue.getImage01() != null){
                        imageFile = Utility.IMAGE_PATH + newValue.getImage01().trim() ;
                    }else{
                        imageFile = Utility.NO_IMAGE_ELSE; //.IMAGE_PATH + "noimage.jpg" ;
                    }

                    Image someImage = null ; // 이미지 객체
                    if(getClass().getResource(imageFile) == null){
                        imageView.setImage(null);
                    }else{
                        someImage = new Image(getClass().getResource(imageFile).toString());
                        System.out.println(imageView == null);
                        imageView.setImage(someImage);
                    }
                }
            }
        };

        productTable.getSelectionModel().selectedItemProperty().addListener(tableListener);

        setContextMenu(); // set context menu to TableView
    }

    private void setPagination(int pageIndex) {
        pagination.setCurrentPageIndex(pageIndex);
        pagination.setPageFactory(this::createPage);
    }

    private Node createPage(Integer pageIndex) {
        //String mode = null; // for search all categories or selected category
        int totalCnt = 0;
        totalCnt = dao.getTotalCount(this.mode);
        System.out.println("createPage(Integer pageIndex) => " + mode);

        Paging pageInfo = new Paging(String.valueOf(pageIndex+1),"10",totalCnt,null,mode,null);

        pagination.setPageCount(pageInfo.getTotalPage());
        fillTableData(pageInfo);

        VBox vbox = new VBox(productTable);
        return vbox;
    }

    private void fillTableData(Paging pageInfo){
        //ObservableList<Product> dataList = null;

        List<Product> productList = dao.getPaginationData(pageInfo);
        dataList = FXCollections.observableArrayList(productList);
        productTable.setItems(dataList);

        pageStatus.setText(pageInfo.getPagingStatus());
    }

    private void setTableColumns() {
        // list items to be shown from product bean class
        // 그외는 상세보기 페이지에서 구현할 수 있다
        String[] fields = {"pnum","name","company","category","inputdate"};
        String[] colNames = {"상품번호","이름","제조회사","카테고리","입고일자"};
        String[] aligns = {"CENTER-RIGHT","CENTER-LEFT","CENTER-LEFT","CENTER-LEFT","CENTER"};

        TableColumn tableColumn = null;

        for (int i = 0; i < fields.length; i++) {
            tableColumn = productTable.getColumns().get(i);
            tableColumn.setText(colNames[i]); // in Korean
            tableColumn.setCellValueFactory(new PropertyValueFactory<>(fields[i]));

            String myAlign = "-fx-alignment:" + aligns[i] + "; -fx-text-fill: #336699;";
            tableColumn.setStyle(myAlign);
        }
    }

    public void onInsert(ActionEvent event) throws Exception {
        // 상품 등록 모달
        String fxmlFile = Utility.FXML_PATH + "LabProductInsert.fxml";
        URL url = getClass().getResource(fxmlFile); // import java.net.URL
        FXMLLoader fxmlLoader = new FXMLLoader(url);

        Parent cntr = fxmlLoader.load(); // throws IOException rather than try/catch
        Scene scene = new Scene(cntr);
        Stage stage = new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene); // include scene on stage
        stage.setResizable(false);
        stage.setTitle("상품 등록하기");
        stage.showAndWait(); // 창 띄우고 대기

        setPagination(0);
    }

    public void onUpdate(ActionEvent event) throws IOException {
        // update the selected item (Product bean)
        int idx = productTable.getSelectionModel().getSelectedIndex();
        Product bean = productTable.getSelectionModel().getSelectedItem();
        System.out.println(idx + " : " + bean);

        if(idx >= 0){
            System.out.println("선택된 색인 번호 :" + idx);
            // 상품 등록 모달
            String fxmlFile = Utility.FXML_PATH + "LabProductUpdate.fxml";
            URL url = getClass().getResource(fxmlFile); // import java.net.URL
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            Parent cntr = fxmlLoader.load(); // throws IOException rather than try/catch

            // update : pass the info and idx to the controller by method
            LabProductUpdateController updateController = fxmlLoader.getController();
            updateController.setBean(bean);

            Scene scene = new Scene(cntr);
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene); // include scene on stage
            stage.setResizable(false);
            stage.setTitle("상품 수정하기");
            stage.showAndWait(); // 창 띄우고 대기

            setPagination(0); // hold it

        } else {
            String[] msg = {"상품 선택 확인","상품 미선택", "수정하고자 하는 상품을 선택해 주세요."};
            Utility.showAlert(Alert.AlertType.ERROR,msg);
        }

    }

    public void onDelete(ActionEvent event) {
        // update the selected item (Product bean)
        int idx = productTable.getSelectionModel().getSelectedIndex();
        System.out.println(idx + " : " + idx);

        if(idx >= 0){
            int pnum = productTable.getSelectionModel().getSelectedItem().getPnum();
            String name = productTable.getSelectionModel().getSelectedItem().getName();

            String[] msg = new String[]{"삭제 확인 메시지","삭제 상품 선택 대화 상자", "상품 번호 ( " + pnum + " ) " + name + " 상품을 정말로 삭제하시겠습니까?"};

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(msg[0]);
            alert.setHeaderText(msg[1]);
            alert.setContentText(msg[2]);

            Optional<ButtonType> rsp = alert.showAndWait();
            if(rsp.get() == ButtonType.OK){
                int cnt = -1;
                cnt = dao.deleteData(pnum);
                if(cnt >= 0){
                    System.out.println("삭제 성공");
                    Image defaultImg = new Image(getClass().getResource(Utility.DEFAULT_IMAGE).toString());
                    imageView.setImage(defaultImg);
                    setPagination(0);
                } else{
                    System.out.println("삭제 실패");
                }
            } else{
                System.out.println("삭제를 취소하였습니다.");
            }

        } else {
            String[] msg = new String[]{"삭제할 상품 선택 확인","삭제할 상품 미선택", "삭제하고자 하는 상품을 선택해 주세요."};
            Utility.showAlert(Alert.AlertType.WARNING,msg);
        }
    }

    public void choiceSelect(ActionEvent event) {
        // 특정 카테고리에 대한 필터링
        String category = fieldSearch.getSelectionModel().getSelectedItem();
        System.out.println("검색 카테고리 : ["+category+"]");

        // pass mode to instance variable
        this.mode = Utility.getCategoryName(category,"value");
        System.out.println("필드 검색 모드 : ["+mode+"]");
        lblChoice.setText(mode);

        setPagination(0);
    }

    public void onSaveFile(ActionEvent event) {
        //
        FileChooser chooser = new FileChooser();
        Button myBtn = (Button)event.getSource();
        Window window = myBtn.getScene().getWindow();
        // chooser.showSaveDialog(window);
        File savedFile = chooser.showSaveDialog(window);

        if(savedFile != null) {
            System.out.println("yes");
            FileWriter fw = null;
            BufferedWriter bw = null;

            try{
                fw = new FileWriter(savedFile);
                bw = new BufferedWriter(fw); // 승급

                for( Product bean: dataList ){
                    bw.write(bean.toString().trim());
                    bw.newLine();
                }

                System.out.println("파일 저장이 완료 되었습니다.");
            } catch(Exception e) {
                e.printStackTrace();
            } finally {
                try{
                    if(bw != null) bw.close();
                    if(fw != null) fw.close();
                } catch (Exception e2){
                    e2.printStackTrace();
                }
            }

        } else {
            System.out.println("파일 저장이 취소 되었습니다.");
        }
    }

    public void onClosing(ActionEvent event) {
        // 프로그램 종료
        Platform.exit();
    }

    private void setContextMenu(){
        // set context menu to TableView
        ContextMenu contextMenu = new ContextMenu();
        MenuItem menuItem01 = new MenuItem("단가 수직 막대");
        MenuItem menuItem02 = new MenuItem("단가 파이 그래프");
        MenuItem menuItem03 = new MenuItem("단가/재고 막대");

        // ok to input parameters as many as the dev wants
        contextMenu.getItems().addAll(menuItem01,menuItem02,menuItem03);
        productTable.setContextMenu(contextMenu);

        menuItem01.setOnAction((event)-> {
            try {
                makeBarChart();
            } catch(Exception e) {
                e.printStackTrace();
            }
        });

        menuItem02.setOnAction((event)-> {
            try {
                makePieChart();
            } catch(Exception e) {
                e.printStackTrace();
            }
        });

        menuItem03.setOnAction((event)-> {
            try {
                makeBarChartAll();
            } catch(Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void makeBarChartAll() throws Exception { // I/O Exception
        System.out.println("단가/재고 막대 그래프 그리기");
        FXMLLoader fxmlLoader = this.getFxmlLoader("BarChart.fxml");
        Parent parent = fxmlLoader.load();

        // make sure after load parent
        BarChartController controller = fxmlLoader.getController();
        // 해당 담당자 컨트롤러에게 그리고자 하는 정보를 넘겨줌.
        controller.makeChartAll(productTable.getItems());

        this.showModal(parent);
    }

    private void makePieChart() throws Exception { // I/O Exception
        System.out.println("단가 파이 그래프 그리기");
        FXMLLoader fxmlLoader = this.getFxmlLoader("PieChart.fxml");
        Parent parent = fxmlLoader.load();

        // make sure after load parent
        PieChartController controller = fxmlLoader.getController();
        // 해당 담당자 컨트롤러에게 그리고자 하는 정보를 넘겨줌.
        controller.makePieChart(productTable.getItems());

        this.showModal(parent);
    }

    private void makeBarChart() throws Exception{ // I/O Exception
        System.out.println("단가 수직 막대 그래프 그리기");
        FXMLLoader fxmlLoader = this.getFxmlLoader("BarChart.fxml");
        Parent parent = fxmlLoader.load();

        // make sure after load parent
        BarChartController controller = fxmlLoader.getController();
        // 해당 담당자 컨트롤러에게 그리고자 하는 정보를 넘겨줌.
        controller.makeChart(productTable.getItems());

        this.showModal(parent);
    }

    private void showModal(Parent parent){
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    private FXMLLoader getFxmlLoader(String fxmlName) throws Exception{
        // get ready as canvas
        String fileName = Utility.FXML_PATH + fxmlName;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fileName));
        return fxmlLoader;
    }

}
