package com.itgroup.bean;

public class MyObject {
    // class to save item name and img info
    private String name;
    private String image;

    public MyObject() {}

    public MyObject(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "MyObject{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
