package com.recurjun.tree.data;

public abstract class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
    private final T data;
    private Node<T> leftNode;
    private Node<T> rightNode;

    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public Node<T> getLeftNode() {
        return leftNode;
    }

    public Node<T> getRightNode() {
        return rightNode;
    }

    public void setLeftNode(Node<T> leftNode) {
        this.leftNode = leftNode;
    }

    public void setRightNode(Node<T> rightNode) {
        this.rightNode = rightNode;
    }
}
