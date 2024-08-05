package com.itgroup.controller;

import com.itgroup.bean.Star;
import com.itgroup.utility.Utility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.*;

public class LabSelectedItemEventController implements Initializable {

    @FXML public ImageView ImgView;
    @FXML public ComboBox starCombo;
    @FXML public Label labelStar;

    // 탈렌트 이름과 이미지 정보를 저장하고 있는 Map 컬렉션
    private Set<String> nameSet = new HashSet<>();
    private Map<String, String> starMap = new HashMap<>();
    private List<Star> starList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillDataFromFile();

        // 탈렌트들을 저장할 리스트 컬렉션
        ObservableList<String> dataList = FXCollections.observableArrayList(nameSet);
        starCombo.setItems(dataList);

        // 우측 콤보 박스에서 사용할 이벤트 입니다.
        starCombo.setOnAction(event -> {
            String selectedItem = (String)starCombo.getSelectionModel().getSelectedItem();
            String imageName = starMap.get(selectedItem);

            System.out.println("Selected Item : " + selectedItem);
            System.out.println("Image : " + imageName);

            if(imageName != null){
                // 해당 이미지를 보여 주세요.
                String imageFile = Utility.IMAGE_PATH + imageName ;
                System.out.println(imageFile);
                String myUrl = getClass().getResource(imageFile).toString(); // error occurs

                Image image = new Image(myUrl) ;
                ImgView.setImage(image);
                //labelStar
            }else{
                ImgView.setImage(null);
            }
        });

    }

    private void fillDataFromFile() {
        // 특정 텍스트 파일에서 관련 정보들을 읽어 들입니다.
        String pathName = System.getProperty("user.dir") ;
        pathName += Utility.DATA_PATH ;

        System.out.println(pathName);
        BufferedReader br = null ;
        try{
            br = new BufferedReader(new FileReader(new File(pathName, "Talent.txt")));

            String oneline = null ;
            while((oneline = br.readLine()) != null){
                System.out.println(oneline);
                String[] myItem = oneline.split("/");
                nameSet.add(myItem[0]);
                starMap.put(myItem[0], myItem[1]);
            }

            System.out.println("탈렌트 리스트 : ");
            System.out.println(starList);

        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            try{
                if(br!=null){br.close();}
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }


}
