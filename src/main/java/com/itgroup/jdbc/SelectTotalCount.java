package com.itgroup.jdbc;

import com.itgroup.bean.Product;
import com.itgroup.dao.ProductDao;

import java.util.List;
import java.util.Scanner;

public class SelectTotalCount {
    public static void main(String[] args) {
        // 전체 또는 카테고리별 상품의 갯수를 반환해 줍니다.

        Scanner scan = new Scanner(System.in);
        System.out.println("input category : " + "all\t" + "beverage\t" + "bread\t" + "macaron\t" +  "wine\t" + "cake");
        String category = scan.next();

        // 모든 상품 조회하기
        ProductDao dao = new ProductDao();
        int totalCnt = dao.getTotalCount(category);
        if(category.equals("all")){
            System.out.println("상품의 전체 개수 :" + totalCnt);
        } else {
            String msg= "카테고리 %의 개수 : %d\n";
            System.out.printf(msg, category, totalCnt);
        }



    }
}
