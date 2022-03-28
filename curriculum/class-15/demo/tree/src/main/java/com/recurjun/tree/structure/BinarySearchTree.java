package com.recurjun.tree.structure;

import com.recurjun.tree.data.BinaryNode;
import com.recurjun.tree.data.Node;

public class BinarySearchTree<T extends Comparable<T>> {

    public enum TraversalOrder {
        INORDER,
        PREORDER,
        POSTORDER
    }

    private Node<T> root;
    private int size;

    public void insert(T data) {
        if (isTreeEmpty()) {
            root = new BinaryNode<>(data);
            size++;
        } else {
            insert(data, root);
        }
    }

    private void insert(T data, Node<T> root) {
        // implement this
    }

    public void traverse(TraversalOrder order) {
        switch (order) {
            case INORDER:
                inOrder(root);
                break;
            case PREORDER:
                preOrder(root);
                break;
            case POSTORDER:
                postOrder(root);
                break;
            default:
        }
    }

    private void inOrder(Node<T> treeNode) {
        if (treeNode == null) { // base case
            return;
        }

        inOrder(treeNode.getLeftNode()); // left

        printNode(treeNode); // root

        inOrder(treeNode.getRightNode()); // right
    }

    private void preOrder(Node<T> treeNode) {
        if (treeNode == null) {
            return;
        }

        printNode(treeNode);

        preOrder(treeNode.getLeftNode());

        preOrder(treeNode.getRightNode());
    }

    private void postOrder(Node<T> treeNode) {
        if (treeNode == null) {
            return;
        }

        postOrder(treeNode.getLeftNode());

        postOrder(treeNode.getRightNode());

        printNode(treeNode);
    }

    private void printNode(Node<T> node) {
        // implement this
    }

    public int size() {
        return size;
    }

    public boolean isTreeEmpty() {
        return root == null;
    }

    public boolean search(T searchTerm) {
        if (isTreeEmpty()) {
            return false;
        } else {
            return searchHelper(searchTerm, root);
        }
    }

    private boolean searchHelper(T searchTerm, Node<T> root) {
        // implement this

        return false;
    }
}
