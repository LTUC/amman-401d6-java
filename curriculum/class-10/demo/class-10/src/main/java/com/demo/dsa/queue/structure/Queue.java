package com.demo.dsa.queue.structure;

import com.demo.dsa.queue.data.QueueNode;

import java.util.NoSuchElementException;

public class Queue {

    private static final int QUEUE_SIZE = 10;

    private QueueNode back;
    private QueueNode front;

    private int size;

    public Queue() { // default constructor
        size = 0;
    }

    public boolean add(QueueNode data) {
        if (isEmpty()) {
            front = data;
            back = data;
            size++;
            return true;
        } else {
            if (size < QUEUE_SIZE) {
                data.setNext(back);
                back = data;
                size++;
                return true;
            }
        }

        return false;
    }

    public QueueNode peek() {
        if (isEmpty()) {
            return null;
        } else{
            return front;
        }
    }

    public QueueNode remove() {
        QueueNode frontTemp;
        if (isEmpty()) {
            throw new NoSuchElementException("The queue is empty");
        } else {
            frontTemp = front; // stores the first node in the queue
            front = front.getNext(); // removes the first node
        }

        return frontTemp;
    }

    public QueueNode poll() {
        return null;
    }

    public QueueNode element() {
        return null;
    }

    public boolean isEmpty() {
        return front == null;
    }

    @Override
    public String toString() {
        // TODO: 3/15/22 implement a proper print method
        return "Queue{" +
                "back=" + back +
                ", front=" + front +
                ", size=" + size +
                '}';
    }
}
