
public class Node {
    private int id;
    private int childCount;
    private Node leftChild;
    private Node rightChild;
    private Node parent;

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

    public int getChildCount() {
        if(leftChild == null && rightChild == null) {
            return 0;
        }

        if((leftChild != null && rightChild == null) || (leftChild == null && rightChild != null)) {
            return 1;
        }
        else {
            return 2;
        }
    }

    //Returns string representation of children
    public String getChildren() {
        if(getChildCount() == 2) {
            return "Left child: " + getLeftChild() + "\nRight child: " + getRightChild();
        }
        else if(getChildCount() == 1) {
            if(getRightChild() == null) {
                return "Left child: " + getLeftChild();
            }
            else {
                return "Right child: " + getRightChild();
            }
        }
        else {
            return "No childrend.";
        }
    }

    @Override
    public boolean equals(Object object) {
        if(this == object){
            return true;
        }

        Node obj = (Node) object;
        if(this.getId() == obj.getId() && this.getLeftChild() == obj.getLeftChild() && this.getRightChild() == obj.getRightChild() && this.getParent() == obj.getParent()) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return id + "";
    }
}
