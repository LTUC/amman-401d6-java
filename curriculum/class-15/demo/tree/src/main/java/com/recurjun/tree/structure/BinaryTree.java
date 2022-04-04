package com.recurjun.tree.structure;

import com.recurjun.queue.structure.Queue;
import com.recurjun.tree.data.BTNode;

public class BinaryTree {

    private BTNode root;

    public BTNode getRoot() {
        return root;
    }

    public void setRoot(BTNode root) {
        this.root = root;
    }

    public void levelOrderTraversalLoop() {
        if (root != null) {
            Queue<BTNode> queue = new Queue<>();
            queue.enqueue(root);

            BTNode node;
            while (!queue.isQueueEmpty()) {
                node = queue.dequeue();
                System.out.print(node.getData() + " => ");
                if (node.getLeft() != null) {
                    queue.enqueue(node.getLeft());
                }

                if (node.getRight() != null) {
                    queue.enqueue(node.getRight());
                }
            }
        } else {
            System.out.println("Tree empty");
        }
    }

    public void levelOrderTraversalRecursive() {
        if (root != null) {
            Queue<BTNode> queue = new Queue<>();
            queue.enqueue(root);
            levelOrderTraversalRecursive(queue);
        } else {
            System.out.println("Tree is empty");
        }
    }

    private void levelOrderTraversalRecursive(Queue<BTNode> queue) {
        // implemnt this
    }
}
