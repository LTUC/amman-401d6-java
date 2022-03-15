package com.demo.dsa.queue.data;

public class QueueNode {

    private final String name;
    private final String number;

    private QueueNode next;

    public QueueNode(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public void setNext(QueueNode next) {
        this.next = next;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public QueueNode getNext() {
        return next;
    }

    @Override
    public String toString() {
        return "QueueNode{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
