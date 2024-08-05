package com.itgroup.bean;

public class Item {

    private String category;
    private String name;
    private String image;

    public Item() {}

    public Item(String category, String name, String image) {
        this.category = category;
        this.name = name;
        this.image = image;
    }

    public Item(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Item{" +
                "category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
