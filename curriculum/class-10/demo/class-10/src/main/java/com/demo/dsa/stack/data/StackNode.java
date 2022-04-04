package com.demo.dsa.stack.data;

public class StackNode {

    private final String name;
    private final String number;

    private StackNode next;

    public StackNode(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public void setNext(StackNode next) {
        this.next = next;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public StackNode getNext() {
        return next;
    }

    @Override
    public String toString() {
        return "StackNode{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
