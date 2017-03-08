//Collection of operations to perform on binary search trees.

import java.util.LinkedList;
import java.util.List;

public class BSTOperations {
    private BSTOperations() {}

    private static List<Node> inorder;
    private static List<Node> preorder;
    private static List<Node> postorder;

    //public static BinarySearchTree DayStoutWarren(BinarySearchTree BST) {
    //    inOrderTraversal(BST);
    //    return BST;
    //}
    //Returns a pre-order linked list of nodes from a given binary search tree
    public static List<Node> preOrderTraversal(BinarySearchTree BST) {
        preorder = new LinkedList<Node>();
        preOrderTraversal(BST.getRoot());
        return preorder;
    }

    //Recursive implementation of a pre-order traversal
    private static boolean preOrderTraversal(Node current) {
        //Break if the node is null
        if(current == null) {
            return false;
        }
        //Recursively check the current node, then the left child, then finally the right child
        else {
            preorder.add(current);
            preOrderTraversal(current.getLeftChild());
            preOrderTraversal(current.getRightChild());
        }

        return true;
    }

    //Returns an in-order linked list of nodes from a given binary search tree
    public static List<Node> inOrderTraversal(BinarySearchTree BST) {
        inorder = new LinkedList<Node>();
        inOrderTraversal(BST.getRoot());
        return inorder;
    }

    //Recursive implementation of an in-order traversal
    private static boolean inOrderTraversal(Node current) {
        //Break if the node is null
        if(current == null) {
            return false;
        }
        //Recursively check the left child, then the current node, then finally the right child
        else {
            inOrderTraversal(current.getLeftChild());
            inorder.add(current);
            inOrderTraversal(current.getRightChild());
        }
        return true;
    }

    //Returns a post-order linked list of nodes from a given binary search tree
    public static List<Node> postOrderTraversal(BinarySearchTree BST) {
        postorder = new LinkedList<Node>();
        postOrderTraversal(BST.getRoot());
        return postorder;
    }

    //Recursive implementation of a post-order traversal
    private static boolean postOrderTraversal(Node current) {
        //Break if the node is null
        if(current == null) {
            return false;
        }
        //Recursively check the left child, then the right child, then finally the current node
        else{
            postOrderTraversal(current.getLeftChild());
            postOrderTraversal(current.getRightChild());
            postorder.add(current);
        }
        return true;
    }
}
