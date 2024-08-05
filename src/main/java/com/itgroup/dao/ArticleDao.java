package com.itgroup.dao;

import com.itgroup.bean.Article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ArticleDao extends SuperDao{

    public ArticleDao() {}

    // 상품들의 카테고리 정보를 중복 해제하여 조회
    public List<String> selectDistinctCategory() {
        Connection conn = null;
        String sql = "select distinct category from article";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<String> allData = new ArrayList<>();
        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String bean = rs.getString("category");
                allData.add(bean);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return allData;
    }
    //select distinct category from article;

    // 특정 카테고리만 필터링
    // select * from article where category = 'bread' order by name asc;
    public List<Article> selectDataByCategory(String category){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select * from article";
        sql += " " + "where category = ? order by name asc";

        List<Article> allData = new ArrayList<>();

        try {
            conn = super.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,category);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Article bean = this.makeBean(rs);
                allData.add(bean);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return allData;
    }

    private Article makeBean(ResultSet rs) {
        Article bean = new Article();
        try{
            bean.setCategory(rs.getString("category"));
            bean.setName(rs.getString("name"));
            bean.setImage(rs.getString("image"));

        } catch(Exception e){
            e.printStackTrace();

        }
        return bean;
    }
}
