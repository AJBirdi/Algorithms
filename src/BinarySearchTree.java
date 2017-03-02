import java.util.LinkedList;
import java.util.List;

public class BinarySearchTree {

    private Node root;

    public BinarySearchTree (Node root) {
       this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    //Provided the ID of a node, find and return the node if it exists
    //If it doesn't exist, return a node with ID -1
    public Node find(int id) {
        Node current = root;
        while(current != null) {
            if(id == current.id) {
                return current;
            }
            else if(id < current.id) {
                current = current.getLeftChild();
            }
            else if(id > current.id) {
                current = current.getRightChild();
            }
        }
        return new Node(-1);
    }

    public void insert(Node insert) {
        Node current = root;
        boolean inserted = false;
        while(current != null && !inserted) {
            if (insert.id < current.id) {
                if (current.leftChild != null) {
                    current = current.leftChild;
                } else {
                    current.setLeftChild(insert);
                    insert.setParent(current);
                    inserted = true;
                }
            }
            else if (insert.id > current.id) {
                if (current.rightChild != null) {
                    current = current.rightChild;
                } else {
                    current.setRightChild(insert);
                    insert.setParent(current);
                    inserted = true;
                }
            }
        }
    }

    public boolean delete(int id) {
        Node toDelete;
        if(find(id).id == -1) {
            return false;
        }
        else {
            toDelete = new Node(id);
        }

        if(toDelete.rightChild == null && toDelete.leftChild == null) {
            return true;
        }
        return false;
    }

    //TODO Secondary goal
    public void inorderTraversal() {

    }

    //TODO Secondary goal
    public void postorderTraversal() {

    }

    //TODO Secondary goal
    public void preorderTraversal() {

    }

    //TODO Ultimate goal
    public void breadthFirstSearch() {

    }
}
