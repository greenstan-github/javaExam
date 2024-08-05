package com.itgroup.bean;

public class Star {
    private String name;
    private String image;

    public Star() {}

    public Star(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getName() { return name; }
    public String getImage() { return image; }

    @Override
    public String toString() {
        return "star{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
