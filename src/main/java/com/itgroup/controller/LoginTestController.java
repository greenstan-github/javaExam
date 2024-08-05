package com.itgroup.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;

import java.util.ResourceBundle;

public class LoginTestController implements Initializable {
    @FXML private Label fxmlResult;
    @FXML private TextField fxmlId;
    @FXML private PasswordField fxmlPassword;
    @FXML private Button btnOk, btnCancel ;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnOk.setOnAction((event)-> okAction(event));
        btnCancel.setOnAction((event)-> initAction(event));

        fxmlResult.textProperty().bind(fxmlId.textProperty());

    }

    private void initAction(ActionEvent event) { // 초기화 버튼
        fxmlId.setText("");
        fxmlPassword.setText("");
        fxmlId.requestFocus(); // 아이디 입력란에 포커스 주기
    }

    private void okAction(ActionEvent event) { // 로그인 버튼
        boolean isCheck = this.checkInputState();

        if(isCheck){ // 입력 조건에 충족함
            boolean loginCheck = this.getAccountCheck();
            if(loginCheck){
                System.out.println("로그인 성공");
            }else{
                System.out.println("로그인 실패");
            }

        }else{ // 입력 조건에 불충분
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("데이터 확인");
            alert.setHeaderText("입력 데이터 누락");
            alert.setContentText("아이디 또는 비번이 누락 되었습니다.");
            alert.showAndWait();
        }
    }

    private boolean getAccountCheck() {
        // 입력한 내용이 실제 계정 정보와 동일한지 체크합니다.
        // 다음 변수들은 실제 데이터 베이스에서 읽어 와야 하는 정보입니다.
        String id = "hong", password = "1234" ;
        if(id.equals(fxmlId.getText()) && password.equals(fxmlPassword.getText())){
            return true ;
        }else{
            return false ;
        }
    }

    private boolean checkInputState() {
        // 유효성 검사(validation check) : 입력되어야 할 데이터들이 제대로 잘 입력이 되었는 질를 확인하는 절차
        // 사용처 : 회원 가입, 게시물 등록, 상품 등록 등등
        // 입력 양식에 대한 유효성 검사를 수행합니다.

        if(fxmlId.getText().length() == 0 ||
                fxmlPassword .getText().length() == 0 ||
                fxmlId == null ||
                fxmlPassword == null){
            return false;

        }else{
            return true ;
        }
    }
}
