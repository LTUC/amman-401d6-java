package com.demo.dsa.stack.structure;

import com.demo.dsa.stack.data.StackNode;

import java.util.EmptyStackException;

public class Stack {

    private StackNode top;

    public Stack() {
        top = null;
    }

    public boolean empty() {
        return top == null;
    }

    public StackNode push(StackNode stackNode) {
        // step 1 - check if stack is empty
        if (empty()) {
            top = stackNode;
            return stackNode;
        } else {
            // make stack node point to what top is pointing to
            stackNode.setNext(top);

            // make top point to stack node
            top = stackNode;
            return stackNode;
        }
    }

    // TODO: 3/15/22 complete on your own time
    public StackNode pop() {
        return null;
    }

    public StackNode peek() {
        if (empty()) {
            throw new EmptyStackException();
        }

        return top;
    }

    // TODO: 3/15/22 complete this in your own time
    public int search(Object obj) {
        return 0;
    }

    @Override
    public String toString() {
        // TODO: 3/15/22 print all nodes of the stack
        return "Stack{" +
                "top=" + top +
                '}';
    }
}
