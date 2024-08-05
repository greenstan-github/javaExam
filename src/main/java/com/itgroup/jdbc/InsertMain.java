package com.itgroup.jdbc;

import com.itgroup.bean.Product;
import com.itgroup.dao.ProductDao;

import java.util.Scanner;

public class InsertMain {
    public static void main(String[] args) {
        ProductDao dao = new ProductDao();
        Product bean = new Product();

        System.out.print("상품 이름 입력 : ");
        Scanner scan = new Scanner(System.in);
        String name = scan.next();

        bean.setPnum(0); // to be replaced by sequence
        bean.setCompany("AB 식품");
        bean.setName(name);
        bean.setImage01("xx.png");
        bean.setImage02("yy.png");
        bean.setImage03("zz.png");
        bean.setStock(1234);
        bean.setPrice(5678);
        bean.setCategory("bread");
        bean.setContents("엄청 맛나요");
        //bean.setPoint(); // default value to be used
        //bean.setInputdate());

        int cnt = -1; // 실패한 경우를 가정
        cnt = dao.insertData(bean);
        if(cnt == -1){
            System.out.println("상품 등록에 실패하였습니다.");
        } else {
            System.out.println("상품 등록에 성공하였습니다.");
        }

        scan.close();
    }
}
