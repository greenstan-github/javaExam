package com.itgroup.bean;

public class Article {
    private String category;
    private String name;
    private String image;

    public Article() {}

    public Article(String category, String name, String image) {
        this.category = category;
        this.name = name;
        this.image = image;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "Article{" +
                "category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
