
public class Node {
    int id;
    Node leftChild;
    Node rightChild;
    Node parent;

    public Node(int id) {
        this.id = id;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node left) {
       leftChild = left;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node right) {
        rightChild = right;
    }

    public int getId() {
        return id;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return id + "";
    }
}
