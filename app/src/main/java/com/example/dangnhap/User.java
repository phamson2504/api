package com.example.dangnhap;

public class User {
    private int id;
    private String name;
    private int age;
    private String dep;

    public User(int id, String name, int age, String dep) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.dep = dep;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }
}
