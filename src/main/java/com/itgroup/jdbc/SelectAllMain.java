package com.itgroup.jdbc;

import com.itgroup.bean.Product;
import com.itgroup.dao.ProductDao;

import java.util.List;

public class SelectAllMain {
    public static void main(String[] args) {
        // 모든 상품 조회하기
        ProductDao dao = new ProductDao();
        List<Product> allProduct = dao.selectAll("all");
        System.out.println(allProduct.size());

        allProduct.forEach((bean)->{
            ShowData.printBean(bean);
        });
    }
}
