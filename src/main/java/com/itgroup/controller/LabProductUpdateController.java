package com.itgroup.controller;

import com.itgroup.bean.Product;
import com.itgroup.dao.ProductDao;
import com.itgroup.utility.Utility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class LabProductUpdateController implements Initializable {
    @FXML private TextField fxmlPnum; // to be hidden
    @FXML private TextField fxmlName;
    @FXML private TextField fxmlCompany;
    @FXML private TextField fxmlImage01;
    @FXML private TextField fxmlImage02;
    @FXML private TextField fxmlImage03;
    @FXML private TextField fxmlStock;
    @FXML private TextField fxmlPrice;
    @FXML private ComboBox<String> fxmlCategory;
    @FXML private TextField fxmlContents;
    @FXML private TextField fxmlPoint;
    @FXML private DatePicker fxmlInputdate;

    private Product oldBean = null;
    private Product newBean = null; // bean object to be updated in DB

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("수정할꺼야");    
    }

    public void onProductUpdate(ActionEvent event) {
        // update data
        System.out.println(event);
        boolean bool = validationCheck();

        if(bool == true){
            ProductDao dao = new ProductDao();
            int cnt = -1;
            cnt = dao.updateData(this.newBean);

            if(cnt == -1){
                System.out.println("수정 실패");
            } else {
                String[] msg = new String[]{"상품 수정","상품 수정 성공", "상품 수정을 성공했습니다."};
                Utility.showAlert(Alert.AlertType.INFORMATION,msg);

                Node source = (Node)event.getSource(); // 강등
                Stage stage = (Stage)source.getScene().getWindow(); // 강등
                stage.close();
            }
        } else {
            System.out.println("유효성 검사를 통과하지 못했습니다.");
        }
    }

    private boolean validationCheck() {
        int pnum = Integer.valueOf(fxmlPnum.getText().trim());

        boolean bool = false;

        String name = fxmlName.getText().trim();
        if(name.length() < 3 || name.length() > 10){
            String[] msg = new String[]{"유효성 검사","길이 제한 위배", "이름은 3글자 이상 10글자 이하이어야 합니다."};
            Utility.showAlert(Alert.AlertType.WARNING,msg);
            return false;
        }

        String company = fxmlCompany.getText().trim();
        if(company.length() < 3 || company.length() > 15){
            String[] msg = new String[]{"유효성 검사","길이 제한 위배", "제조 회사명은 3글자 이상 15글자 이하이어야 합니다."};
            Utility.showAlert(Alert.AlertType.WARNING,msg);
            return false;
        }

        String image01 = fxmlImage01.getText().trim();
        bool = (image01.toUpperCase().endsWith(".JPG") || image01.toUpperCase().endsWith(".PNG"));
        if(!bool){
            String[] msg = new String[]{"유효성 점검 : 이미지01", "확장자 점검", "이미지의 확장자는 'jpg' 또는 'png'이어야합니다."};
            Utility.showAlert(Alert.AlertType.WARNING,msg);
            return false;
        }

    /*F
        String image02 = fxmlImage01.getText().trim();
        if(image02.length() == 0){
            image02 = null;
        }
        if(image02 != null) {
            bool = (image02.toUpperCase().endsWith(".JPG") || image02.toUpperCase().endsWith(".PNG"));
            if (!bool) {
                String[] msg = new String[]{"유효성 점검 : 이미지02", "확장자 점검", "이미지의 확장자는 'jpg' 또는 'png'이어야합니다."};
                Utility.showAlert(Alert.AlertType.WARNING, msg);
                return false;
            }
        }

        String image03 = fxmlImage01.getText().trim();
        if(image03.length() == 0){
            image03 = null;
        }
        if(image03 != null) {
            bool = (image03.toUpperCase().endsWith(".JPG") || image03.toUpperCase().endsWith(".PNG"));
            if (!bool) {
                String[] msg = new String[]{"유효성 점검 : 이미지03", "확장자 점검", "이미지의 확장자는 'jpg' 또는 'png'이어야합니다."};
                Utility.showAlert(Alert.AlertType.WARNING, msg);
                return false;
            }
        }
    */

        String image02 = null ;
        if(fxmlImage02.getText() != null){
            image02 = fxmlImage02.getText().trim();

            bool = image02.toUpperCase().endsWith(".JPG") || image02.toUpperCase().endsWith(".PNG");
            if(!bool){
                String[] msg = new String[]{"유효성 검사 : 이미지02", "확장자 점검", "이미지의 확장자는 '.jpg' 또는 '.png' 이하이어야 합니다."} ;
                Utility.showAlert(Alert.AlertType.WARNING, msg);
                return false;
            }
        }

        String image03 = null ;
        if(fxmlImage03.getText() != null){
            image03 = fxmlImage03.getText().trim();

            bool = image03.toUpperCase().endsWith(".JPG") || image03.toUpperCase().endsWith(".PNG") ;
            if(!bool){
                String[] msg = new String[]{"유효성 검사 : 이미지03", "확장자 점검", "이미지의 확장자는 '.jpg' 또는 '.png' 이하이어야 합니다."} ;
                Utility.showAlert(Alert.AlertType.WARNING, msg);
                return false;
            }
        }

        int stock = 0;
        try{
            String _stock = fxmlStock.getText();
            stock = Integer.valueOf(_stock);

            if(stock < 10 || stock > 100){
                String[] msg = {"유효성 검사 재고 :","허용 숫자 위반", "재고는 10개이상 100개 이하로 입력해 주세요."};
                Utility.showAlert(Alert.AlertType.WARNING,msg);
                return false;
            }
        } catch(NumberFormatException e) {
            e.printStackTrace();
            String[] msg = {"유효성 검사 재고 :","무효한 숫자 형식", "올바른 숫자식을 입력해 주세요."};
            Utility.showAlert(Alert.AlertType.WARNING,msg);
            return false;
        }

        int price = 0;
        try{
            String _price = fxmlPrice.getText();
            price = Integer.valueOf(_price);

            if(price < 1000 || price > 10000){
                String[] msg = {"유효성 검사 단가 :","허용 숫자 위반", "단가는 1,000원이상 10,000원 이하로 입력해 주세요."};
                Utility.showAlert(Alert.AlertType.WARNING,msg);
                return false;
            }
        } catch(NumberFormatException e) {
            e.printStackTrace();
            String[] msg = {"유효성 검사 단가 :","무효한 숫자 형식","올바른 숫자식을 입력해 주세요."};
            Utility.showAlert(Alert.AlertType.WARNING,msg);
            return false;
        }

        String category = "";
        int selectedIndex = fxmlCategory.getSelectionModel().getSelectedIndex();
        if(selectedIndex == 0){
            String[] msg = {"유효성 검사 카테고리 :","카테고리 미선택","원하시는 카테고리를 반드시 선택해 주세요."};
            Utility.showAlert(Alert.AlertType.WARNING,msg);
            return false;
        } else {
            category = fxmlCategory.getSelectionModel().getSelectedItem();
            System.out.println("선택된 항목 : " + category);
        }

        String contents = fxmlContents.getText().trim();
        if(contents.length() < 5 || contents.length() > 30){
            String[] msg = new String[]{"유효성 검사","길이 제한 위배","상품 설명은 3글자 이상 30글자 이하이어야 합니다."};
            Utility.showAlert(Alert.AlertType.WARNING,msg);
            return false;
        }

        int point = 0;
        try{
            String _point = fxmlPoint.getText();
            point = Integer.valueOf(_point);

            if(point < 3 || point > 5){
                String[] msg = {"유효성 검사 포인트 :","허용 숫자 위반","포인트는 3원이상 5원이하로 입력해 주세요."};
                Utility.showAlert(Alert.AlertType.WARNING,msg);
                return false;
            }
        } catch(NumberFormatException e) {
            e.printStackTrace();
            String[] msg = {"유효성 검사 포인트 :","무효한 숫자 형식","올바른 숫자식을 입력해 주세요."};
            Utility.showAlert(Alert.AlertType.WARNING,msg);
            return false;
        }

        String inputDate = "";
        LocalDate _inputDate = fxmlInputdate.getValue();
        if(_inputDate == null){
            String[] msg = {"유효성 검사 입고일자 :","무효한 날짜 형식","올바른 입고 일자 형식을 입력해 주세요."};
            Utility.showAlert(Alert.AlertType.WARNING,msg);
        } else {
            inputDate = _inputDate.toString();
            inputDate = inputDate.replace("-","/");
        }

        this.newBean = new Product();
        newBean.setPnum(pnum); // oldBean.getPnum();
        newBean.setName(name);
        newBean.setCompany(company);
        newBean.setImage01(image01);
        newBean.setImage02(image02);
        newBean.setImage03(image03);
        newBean.setStock(stock);
        newBean.setPrice(price);
        // for eng key by kor value in the category ComboBox
        newBean.setCategory(Utility.getCategoryName(category,"value"));
        newBean.setContents(contents);

        // need to check to see and also check the dao
        newBean.setPoint(point);
        newBean.setInputdate(inputDate);

        return true;
    }

    public void setBean(Product bean) {
        this.oldBean = bean;
        fillPreviousData();

        fxmlPnum.setVisible(false);

    }

    private void fillPreviousData() {
        // transfer data to the update modal
        fxmlPnum.setText(String.valueOf(this.oldBean.getPnum()));
        fxmlName.setText(this.oldBean.getName());
        fxmlCompany.setText(this.oldBean.getCompany());
        fxmlImage01.setText(this.oldBean.getImage01());
        fxmlImage02.setText(this.oldBean.getImage02());
        fxmlImage03.setText(this.oldBean.getImage03());
        fxmlStock.setText(String.valueOf(this.oldBean.getStock()));
        fxmlPrice.setText(String.valueOf(this.oldBean.getPrice()));
        fxmlContents.setText(this.oldBean.getContents());
        fxmlPoint.setText(String.valueOf(this.oldBean.getPoint()));

        //fxmlCategory.setText(this.oldBean.getCategory());
        String category = this.oldBean.getCategory(); // value eng from bean
        // eng key from DB
        fxmlCategory.setValue(Utility.getCategoryName(category,"key"));

        // inputDate : get localDate type from string
        String inputDate = this.oldBean.getInputdate();
        if(inputDate == null || inputDate.equals("null")){
            // nothing to do
        } else {
            fxmlInputdate.setValue(Utility.getDatePicker(inputDate));
        }
    }
}
