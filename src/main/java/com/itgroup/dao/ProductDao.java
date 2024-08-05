package com.itgroup.dao;

import com.itgroup.bean.Product;
import com.itgroup.utility.Paging;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao extends SuperDao{
    public ProductDao() {
        super();
    }

    public List<Product> selectAll(String category) {
        Connection conn = null;
        String sql = "select * from products";
        if(category == null || !category.equals("all")) {
            sql += " " + "where category = ?"; // PlaceHolder(?)
        }
        sql += " " + "order by pnum desc";

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<Product> allData = new ArrayList<>();

        try{
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);

            if(category == null || !category.equals("all")) {
                pstmt.setString(1, category);
            }

            rs = pstmt.executeQuery();

            while(rs.next()){
                Product bean = makeBean(rs);
                allData.add(bean);
            }

        } catch(Exception e){
            e.printStackTrace();
        } finally {
            try{
                if(rs != null) rs.close();
                if(pstmt != null) pstmt.close();
                if(conn != null) conn.close();
            } catch(Exception e){
                e.printStackTrace();
            }
        }

        return allData;
    }

    private Product makeBean(ResultSet rs) {
        Product bean = new Product();

        try{
            bean.setPnum(rs.getInt("pnum"));
            bean.setName(rs.getString("name"));
            bean.setCompany(rs.getString("company"));
            bean.setImage01(rs.getString("image01"));
            bean.setImage02(rs.getString("image02"));
            bean.setImage03(rs.getString("image03"));
            bean.setStock(rs.getInt("stock"));
            bean.setPrice(rs.getInt("price"));
            bean.setCategory(rs.getString("category"));
            bean.setContents(rs.getString("contents"));
            bean.setPoint(rs.getInt("point"));
            bean.setInputdate(String.valueOf(rs.getDate("inputdate")));

        } catch(Exception e){
            e.printStackTrace();
        }
        return bean;
    }

    public Product selectByPK(int pnum) {
        Connection conn = null;
        String sql = "select * from products";
        sql += " " + "where pnum = ?"; // PlaceHolder(?)

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Product bean = null;

        try{
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, pnum);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                bean = this.makeBean(rs);
            } // new Product();

        } catch(Exception e){
            e.printStackTrace();
        } finally {
            try{
                if(rs != null){rs.close(); }
                if(pstmt != null){pstmt.close(); }
                if(conn != null){conn.close(); }
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return bean;
    }

    public List<Product> template(){
        Connection conn = null;
        String sql = "select * from products";
        sql += " " + "order by pnum desc";

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<Product> allData = new ArrayList<>();

        try{
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);

            // if(category == null || !category.equals("all")) {
            //    pstmt.setString(1, category);
            //}

            rs = pstmt.executeQuery();

//            if(rs.next()) {
//                bean = this.makeBean(rs);
//            } // new Product();

            while(rs.next()){
                Product bean = this.makeBean(rs); // new Product();
                allData.add(bean);
            }

        } catch(Exception e){
            e.printStackTrace();
        } finally {
            try{
                if(rs != null){rs.close(); }
                if(pstmt != null){pstmt.close(); }
                if(conn != null){conn.close(); }
            } catch(Exception e){
                e.printStackTrace();
            }
        }

        return allData;
    }

    public int insertData(Product bean) {
        System.out.println(bean);
        int cnt = -1;

        Connection conn = null;
        PreparedStatement pstmt = null;
        String qry = " " + "INSERT INTO PRODUCTS (pnum, name, company, image01, image02, image03, stock, price, category, contents, point, inputdate)";
        qry += " " + "values(seqproduct.nextval,?,?,?,?,?,?,?,?,?,?,?)"; // i/o default

        try {
            conn = super.getConnection();
            conn.setAutoCommit(false); // default value true is not reliable;

            pstmt = conn.prepareStatement(qry);
            pstmt.setString(1,bean.getName());
            pstmt.setString(2,bean.getCompany());
            pstmt.setString(3,bean.getImage01());
            pstmt.setString(4,bean.getImage02());
            pstmt.setString(5,bean.getImage03());
            pstmt.setInt(6,bean.getStock());
            pstmt.setInt(7,bean.getPrice());
            pstmt.setString(8,bean.getCategory());
            pstmt.setString(9,bean.getContents());
            pstmt.setInt(10,bean.getPoint());
            pstmt.setString(11,bean.getInputdate());

            cnt = pstmt.executeUpdate();
            conn.commit();

        }catch(Exception e){
            e.printStackTrace();
            try {
                conn.rollback();
            }catch(SQLException err){
                throw new RuntimeException();
            }
        }finally{
            try{
                if(pstmt != null) pstmt.close();
                if(conn != null) conn.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        return cnt;
    }

    public int updateData(Product bean) {
        System.out.println(bean);
        int cnt = -1;

        Connection conn = null;
        PreparedStatement pstmt = null;
        String qry = "update products set name=?, company=?, image01=?, image02=?, image03=?, stock=?, price=?, category=?, contents=?, point=?, inputdate=?";
        qry += " " + "where pnum=?";


        try {
            conn = super.getConnection();
            conn.setAutoCommit(false); // default value true is not reliable;
            pstmt = conn.prepareStatement(qry);

            pstmt.setString(1,bean.getName());
            pstmt.setString(2,bean.getCompany());
            pstmt.setString(3,bean.getImage01());
            pstmt.setString(4,bean.getImage02());
            pstmt.setString(5,bean.getImage03());
            pstmt.setInt(6,bean.getStock());
            pstmt.setInt(7,bean.getPrice());
            pstmt.setString(8,bean.getCategory());
            pstmt.setString(9,bean.getContents());
            pstmt.setInt(10,bean.getPoint());
            pstmt.setString(11,bean.getInputdate());
            pstmt.setInt(12,bean.getPnum());

            cnt = pstmt.executeUpdate();
            conn.commit();

        }catch(Exception e){
            e.printStackTrace();
            try {
                conn.rollback();
            }catch(SQLException err){
                throw new RuntimeException();
            }
        }finally{
            try{
                if(pstmt != null) pstmt.close();
                if(conn != null) conn.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        return cnt;
    }

    public int deleteData(int pnum) {
        System.out.println("기본 키 : " + pnum);
        int cnt = -1;

        Connection conn = null;
        PreparedStatement pstmt = null;
        String qry = "delete from products";
        qry += " " + "where pnum=?";

        try {
            conn = super.getConnection();
            conn.setAutoCommit(false); // default value true is not reliable;
            pstmt = conn.prepareStatement(qry);
            pstmt.setInt(1,pnum);
            cnt = pstmt.executeUpdate();
            conn.commit();

        } catch(Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch(SQLException err){
                throw new RuntimeException();
            }
        } finally {
            try {
                if(pstmt != null) pstmt.close();
                if(conn != null) conn.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        return cnt;
    }

    public int getTotalCount(String category) {
        int totalCnt = 0;

        int cnt = -1;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String qry = "select count(*) as cnt from products";

        boolean bool = (category == null || category.equals("all"));
        if(!bool){
            qry += " " + "where category = ?";
        }

        try {
            conn = super.getConnection();
            conn.setAutoCommit(false); // default value true is not reliable;
            pstmt = conn.prepareStatement(qry);
            if(!bool){
                pstmt.setString(1,category);
            }

            rs = pstmt.executeQuery();

            if(rs.next()) {
                totalCnt = rs.getInt("cnt");
            }

        } catch(Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch(SQLException err){
                throw new RuntimeException();
            }
        } finally {
            try {
                if(rs != null) rs.close();
                if(pstmt != null) pstmt.close();
                if(conn != null) conn.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        return totalCnt;
    }

    public List<Product> getPaginationData(Paging pageInfo) {
        Connection conn = null;

        //페이징 처리시 사용할 코드
        String qry = "select pnum, name, company, image01, image02, image03, stock, price, category, contents, point, inputdate";
        qry += " " + "from (";
        qry += " " + "select pnum, name, company, image01, image02, image03, stock, price, category, contents, point, inputdate,";
        qry += " " + "rank() over(order by pnum desc) as ranking";
        qry += " " + "from products";

        String mode = pageInfo.getMode();
        Boolean bool = mode.equals(null) || mode.equals("null") || mode.equals("") || mode.equals("all");
        if(!bool){
            qry += " " + "where category = ?";
        }

        qry += " " + ")";
        qry += " " + "where ranking between ? and ?";

        // 페이징 처리시 사용 가능

        String sql = "SELECT *";
        sql += " " + "  FROM (";
        sql += " " + "    SELECT a.*, ROWNUM rnum";
        sql += " " + "    FROM ( SELECT * FROM products ORDER BY pnum desc ) a";
        sql += " " + "    WHERE ROWNUM <= ? + ?"; // :offset + :limit
        sql += " " + "  )";
        sql += " " + "  WHERE rnum > ?"; // :offset
        if(!bool) {
            sql += " " + " AND category = ?"; // apply search category
        }

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<Product> allData = new ArrayList<>();

        try{
            conn = super.getConnection();
            pstmt = conn.prepareStatement(qry);

            if(!bool) {
                pstmt.setString(1, mode);
                pstmt.setInt(2, pageInfo.getBeginRow());
                pstmt.setInt(3, pageInfo.getEndRow());
            } else{
                pstmt.setInt(1, pageInfo.getBeginRow());
                pstmt.setInt(2, pageInfo.getEndRow());
            }

//            if(!bool) {
//                pstmt.setInt(1, pageInfo.getBeginRow()-1);
//                pstmt.setInt(2, pageInfo.getEndRow());
//                pstmt.setInt(3, pageInfo.getBeginRow()-1);
//                pstmt.setString(4, mode);
//            } else{
//                pstmt.setInt(1, pageInfo.getBeginRow()-1);
//                pstmt.setInt(2, pageInfo.getEndRow());
//                pstmt.setInt(3, pageInfo.getBeginRow()-1);
//            }

            rs = pstmt.executeQuery();

            while(rs.next()){
                Product bean = makeBean(rs);
                allData.add(bean);
            }

        } catch(Exception e){
            e.printStackTrace();
        } finally {
            try{
                if(rs != null) rs.close();
                if(pstmt != null) pstmt.close();
                if(conn != null) conn.close();
            } catch(Exception e){
                e.printStackTrace();
            }
        }

        return allData;
    }

}
