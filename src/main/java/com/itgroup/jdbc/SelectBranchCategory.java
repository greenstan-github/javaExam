package com.itgroup.jdbc;

import com.itgroup.bean.Product;
import com.itgroup.dao.ProductDao;

import java.util.List;
import java.util.Scanner;

public class SelectBranchCategory {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("input category : " + "all\t" + "beverage\t" + "bread\t" + "macaron\t" +  "wine\t" + "cake");
        String category = scan.next();


        // 모든 상품 조회하기
        ProductDao dao = new ProductDao();
        List<Product> allProduct = dao.selectAll(category);
        System.out.println(allProduct.size());

        allProduct.forEach((bean)->{
            ShowData.printBean(bean);
        });
    }
}
