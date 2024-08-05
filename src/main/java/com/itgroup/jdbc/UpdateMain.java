package com.itgroup.jdbc;

import com.itgroup.bean.Product;
import com.itgroup.dao.ProductDao;

import java.util.Scanner;

public class UpdateMain {
    public static void main(String[] args) {
        // 특정 삼품을 수정
        ProductDao dao = new ProductDao();
        Product bean = new Product();

        Scanner scan = new Scanner(System.in);
        System.out.print("상품 번호 입력 : ");
        int pnum = scan.nextInt();

        System.out.print("상품 이름 입력 : ");
        String name = scan.next();

        bean.setPnum(pnum); // to be replaced by sequence
        bean.setCompany("AB 식품");
        bean.setName(name);
        bean.setImage01("aa.png");
        bean.setImage02("aa.png");
        bean.setImage03("cc.png");
        bean.setStock(9999);
        bean.setPrice(2345);
        bean.setCategory("bread");
        bean.setContents("별로에요~");
        bean.setPoint(15); // default value to be used
        bean.setInputdate("2024/07/17");

        int cnt = -1; // 실패한 경우를 가정
        cnt = dao.updateData(bean);
        if(cnt == -1){
            System.out.println("상품 수정에 실패하였습니다.");
        } else {
            System.out.println("상품 수정에 성공하였습니다.");
        }

        scan.close();
    }
}
