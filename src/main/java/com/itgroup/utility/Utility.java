package com.itgroup.utility;

import javafx.scene.Parent;
import javafx.scene.control.Alert;

import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Utility {

    public static final String FXML_PATH = "/com/itgroup/fxml/";
    public static final String IMAGE_PATH = "/com/itgroup/images/";
    public static final String DEFAULT_IMAGE = "/com/itgroup/images/child08.png";
    public static final String NO_IMAGE_ELSE = "/com/itgroup/images/child07.png";

    public static final String CSS_PATH = "/com/itgroup/css/";
    public static final String DATA_PATH = "\\src\\main\\java\\com\\itgroup\\data\\";

    private final String fs = File.separator;
    //public static final String DATA_PATH = "${fs}src${fs}main${fs}java${fs}com${fs}itgroup${fs}data${fs}";

    public static void showAlert(Alert.AlertType alertType, String[] msg) {
        // 단순 메세지 박스를 보여주기 위한 유틸리티
        Alert alert = new Alert(alertType);
        alert.setTitle(msg[0]);
        alert.setHeaderText(msg[1]);
        alert.setContentText(msg[2]);
        alert.showAndWait();
    }

    public static LocalDate getDatePicker(String inputDate) {
        // casting str to localDate type the return it
        String[] arrDate = inputDate.split("-");
        int year = Integer.parseInt(inputDate.substring(0, 4)); // Integer.parseInt(arrDate[0]
        int month = Integer.parseInt(inputDate.substring(5, 7)); // Integer.parseInt(arrDate[1]
        int date = Integer.parseInt(inputDate.substring(8)); // Integer.parseInt(arrDate[2])
        return LocalDate.of(year, month, date);
    }

    private static Map<String, String> categoryMap = new HashMap<>();

    public static String getCategoryName(String category, String mode) {
        return makeMapData(category, mode);
    }

    private static String makeMapData(String category, String mode) {
        // mode : ["key","value"]
        categoryMap.put("음료수", "beverage");
        categoryMap.put("빵", "bread");
        categoryMap.put("마카롱", "macaron");
        categoryMap.put("케익", "cake");

        // impossible to search value for key
        if (mode.equals("value")) {
            return categoryMap.get(category);
        } else {
            for (String key : categoryMap.keySet()) {
                if (categoryMap.get(key).equals(category)) {
                    return key;
                }
            }
            return null;
        }
    }

//    public static Parent getFxmlParent(String fxmlName) throws Exception {
//        // return FXML top container as per fxmlName
//        Parent parent = null;
//        try {
//
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//        return parent;
//    }
}

