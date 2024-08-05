package com.itgroup.jdbc;

import com.itgroup.bean.Product;
import com.itgroup.dao.ProductDao;

import java.util.List;
import java.util.Scanner;

public class SelectOnlyOne {
    public static void main(String[] args) {

        // get product data as per pnum
        Scanner scan = new Scanner(System.in);
        System.out.println("input product no. : ");
        int pnum = scan.nextInt();

        // 모든 상품 조회하기
        ProductDao dao = new ProductDao();
        Product bean = dao.selectByPK(pnum);
        ShowData.printBean(bean);

    }
}
