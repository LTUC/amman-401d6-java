package com.example.animals;

import com.example.App;

/*
This class models the behavior of ducks
this is a blueprint for creating ducks
This is also called a reference type
 */
public class Duck {

    public static final double PRICE = 23456.90; // CONSTANT in java

    // fields / properties / state variables
    private String color;
    private String type;
    private boolean canFly;
    private int age;
    private double weight;
    private double length;
    private double width;
    private char gender;
    private int speed;
    protected String name;

    public Duck(String color, String type, boolean canFly) {
        this.color = color;
        this.type = type;
        this.canFly = canFly;
    }

    // methods / functions / behaviors / actions / mutators / setters / getters
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isCanFly() {
        return canFly;
    }

    public void setCanFly(boolean canFly) {
        this.canFly = canFly;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Duck{" +
                "color='" + color + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", price='" + PRICE + '\'' +
                ", canFly=" + canFly +
                '}';
    }

    protected void test () {

    }

    public static void fly() {
        System.out.println("I am flying");
    }
}
