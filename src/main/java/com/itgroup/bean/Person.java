package com.itgroup.bean;

public class Person {
    private String num;
    private String firstName;
    private String lastName;

    public Person() {}

    public Person(String num, String firstName, String lastName) {
        this.num = num;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNum() {
        return num;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "num='" + num + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
