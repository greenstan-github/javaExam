package com.itgroup.jdbc;

import com.itgroup.bean.Product;

public class ShowData {

    public static void printBean(Product bean) {
        int pnum = bean.getPnum();
        String name = bean.getName() ;
        String company = bean.getCompany() ;
        String image01 = bean.getImage01() ;
        String image02 = bean.getImage02() ;
        String image03 = bean.getImage03() ;
        int stock = bean.getStock();
        int price = bean.getPrice();
        String category = bean.getCategory() ;
        String contents = bean.getContents();
        int point = bean.getPoint();
        String inputdate = bean.getInputdate() ;

        System.out.print("상품 번호 : " + pnum + "\t");
        System.out.print("상품명 : " + name + "\t");
        System.out.print("제조 회사 : " + company + "\t");
        System.out.print("이미지01 : " + image01 + "\t");
        System.out.print("이미지02 : " + image02 + "\t");
        System.out.print("이미지03 : " + image03 + "\t");
        System.out.print("재고 : " + stock + "\t");
        System.out.print("단가 : " + price + "\t");
        System.out.print("카테고리 : " + category + "\t");
        System.out.print("상품 설명 : " + contents + "\t");
        System.out.print("포인트 : " + point + "\t");
        System.out.print("입고 일자 : " + inputdate);

        System.out.println();
    }
}
