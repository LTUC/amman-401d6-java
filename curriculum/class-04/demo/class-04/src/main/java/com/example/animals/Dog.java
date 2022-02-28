package com.example.animals;

public class Dog {

    String name; // it is by default package private aka protected

    public Dog(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void test() {
        Duck duckky = new Duck("Orange", "Alabio", false);
        duckky.name = "george";
        duckky.test();
    }
}
