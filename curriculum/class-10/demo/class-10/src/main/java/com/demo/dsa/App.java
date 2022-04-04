package com.demo.dsa;

import com.demo.dsa.queue.data.QueueNode;
import com.demo.dsa.queue.structure.Queue;
import com.demo.dsa.stack.data.StackNode;
import com.demo.dsa.stack.structure.Stack;

import java.util.AbstractQueue;
import java.util.ArrayDeque;

public class App {

    public static void main(String[] args) {
        System.out.println("+++++++++++++ Welcome to STACKS AND QUEUES +++++++++++++");

        Stack stack = new Stack();

        if (stack.empty()) {
            System.out.println("The stack is empty");
        }

        stack.push(new StackNode("Jason", "555-555-555")); // first node in will reside at bottom
        stack.push(new StackNode("Osaid", "666-666-666"));
        stack.push(new StackNode("Roaa", "777-777-777")); // last node in will reside at the top

        System.out.println(stack);

        java.util.Stack<String> stack1 = new java.util.Stack<>();

        stack1.push("Jaosn");
        stack1.push("Roaaa");
        stack1.push("Ala");
        stack1.push("Jaosn");
        System.out.println(stack1);

        System.out.println("QUEUE OUTPUT");

        Queue queue = new Queue();
        queue.add(new QueueNode("Jason", "555-555-555")); // first in
        queue.add(new QueueNode("Osaid", "666-666-666"));
        queue.add(new QueueNode("Roaa", "777-777-777"));
        queue.add(new QueueNode("James", "007-007-007")); // last in

        if (queue.isEmpty()) {
            System.out.println("the queue is empty");
        } else {
            System.out.println(queue);
        }
    }
}
