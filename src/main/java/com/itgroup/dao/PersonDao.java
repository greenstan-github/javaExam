package com.itgroup.dao;

import com.itgroup.bean.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PersonDao extends SuperDao{
    private int totalCount = 0;
    private List<Person> personList = null;

    public PersonDao() {
        personList = new ArrayList<Person>();
        this.fillData();
    }

    public int getTotalCount(String category) {
        int totalCnt = 0;
        String qry = "select count(*) as myCnt from products";
        boolean bool = category == null | category.equals("all") ;

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = super.getConnection();
            conn.setAutoCommit(false); // default value true is not reliable;
            pstmt = conn.prepareStatement(qry);
            totalCnt = pstmt.executeUpdate();
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

        return totalCount;
    }

    private String getEndName(){
        String end = "";
        Random rd = new Random();
        String[] names = {"호","준","희","경","길","영","현","병"};
        int idx = rd.nextInt(names.length);
        return names[idx];
    }

    private void fillData() {
        DecimalFormat df = new DecimalFormat("0000");
        for (int i = 1; i <= 100; i++) {
            personList.add(new Person(""+df.format(3*i-2),"철"+getEndName(),"김"));
            personList.add(new Person(""+df.format(3*i-1),"명"+getEndName(),"박"));
            personList.add(new Person(""+df.format(3*i-0),"유"+getEndName(),"최"));
        }
        totalCount = personList.size();
    }

    public List<Person> getAllData(int beginRow, int endRow) {
        return personList.subList(beginRow, endRow);
    }
}
