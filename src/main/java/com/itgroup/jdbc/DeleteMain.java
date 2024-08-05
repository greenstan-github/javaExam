package com.itgroup.jdbc;

import com.itgroup.bean.Product;
import com.itgroup.dao.ProductDao;

import java.util.Scanner;

public class DeleteMain {
    public static void main(String[] args) {
        // 특정 삼품 삭제
        ProductDao dao = new ProductDao();

        Scanner scan = new Scanner(System.in);
        System.out.print("상품 번호 입력 : ");
        int pnum = scan.nextInt();

        int cnt = -1; // 실패한 경우를 가정
        cnt = dao.deleteData(pnum);
        if(cnt == -1){
            System.out.println("상품 삭제에 실패하였습니다.");
        } else {
            System.out.println("상품 삭제에 성공하였습니다.");
        }

        scan.close();
    }
}
