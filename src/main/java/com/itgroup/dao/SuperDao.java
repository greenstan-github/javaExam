package com.itgroup.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class SuperDao {
    private String url = null;
    private String id = null;
    private String pw = null;

    public SuperDao() {

        String driver = "oracle.jdbc.driver.OracleDriver";
        this.url = "jdbc:oracle:thin:@localhost:1521:xe";
        this.id = "oraman";
        this.pw = "oracle";

        try{
            // load driver onto memory (드라이버 로딩)
            Class.forName(driver); // 동적 객체 생성
            Class.forName(driver);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    protected Connection getConnection() {
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url,id,pw);
            if(conn == null) System.out.println("no connection made");
        } catch(Exception e){
            e.printStackTrace();
        }
        return conn;
    }
}
