package com.itgroup.jdbc;

import com.itgroup.bean.Product;
import com.itgroup.dao.ProductDao;
import com.itgroup.utility.Paging;

import java.util.List;
import java.util.Scanner;

public class SelectPagination {
    public static void main(String[] args) {
        // 검색 모드와 페이지네이션 기능 구현
        Scanner scan = new Scanner(System.in);

        System.out.println("input category : " + "all\t" + "beverage\t" + "bread\t" + "macaron\t" +  "wine\t" + "cake");
        String mode = scan.next(); // search mode

        System.out.print("몇 페이지 볼꺼니? : ");
        String pageNum = scan.next();

        System.out.print("페이지 당 몇 건씩 볼꺼니? : ");
        String pageSize = scan.next();

        ProductDao dao= new ProductDao();
        int totalCnt = dao.getTotalCount(mode);

        String url = "prList.jsp";
        String keyword = "";

        Paging pageInfo = new Paging(pageNum, pageSize, totalCnt, url, mode, keyword);
        pageInfo.displayInformation();

        List<Product> productList = dao.getPaginationData(pageInfo);

        productList.forEach((bean)->{
            ShowData.printBean(bean);
        });

    }
}
