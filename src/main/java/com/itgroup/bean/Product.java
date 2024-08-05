package com.itgroup.bean;

// 상품 1개를 의미하는 자바빈 클래스
public class Product {
    private int pnum;
    private String name;
    private String company;
    private String image01;
    private String image02;
    private String image03;
    private int stock;
    private int price;
    private String category;
    private String contents;
    private int point;
    private String inputdate;

    public Product() {}

    public void setPnum(int pnum) {
        this.pnum = pnum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setImage01(String image01) {
        this.image01 = image01;
    }

    public void setImage02(String image02) {
        this.image02 = image02;
    }

    public void setImage03(String image03) {
        this.image03 = image03;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setInputdate(String inputdate) {
        this.inputdate = inputdate;
    }

    public int getPnum() {
        return pnum;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getImage01() {
        return image01;
    }

    public String getImage02() {
        return image02;
    }

    public String getImage03() {
        return image03;
    }

    public int getStock() {
        return stock;
    }

    public int getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getContents() {
        return contents;
    }

    public int getPoint() {
        return point;
    }

    public String getInputdate() {
        return inputdate;
    }

    @Override
    public String toString() {
        String SEPERATOR = "/";
        String temp = "";
        temp += pnum + SEPERATOR;
        temp += name + SEPERATOR;
        temp += company + SEPERATOR;
        temp += image01 + SEPERATOR;
        temp += (image02 == null ? "" : image02) + SEPERATOR;
        temp += (image03 == null ? "" : image03) + SEPERATOR;
        temp += stock + SEPERATOR;
        temp += price + SEPERATOR;
        temp += category + SEPERATOR;
        temp += contents + SEPERATOR;
        temp += point + SEPERATOR;
        temp += inputdate + SEPERATOR;

        return temp;
    }
}
