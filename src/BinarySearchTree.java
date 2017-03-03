
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
            if(id == current.getId()) {
                return current;
            }
            else if(id < current.getId()) {
                current = current.getLeftChild();
            }
            else if(id > current.getId()) {
                current = current.getRightChild();
            }
        }
        return new Node(-1);
    }

    //TODO Refactor insert method into methods that insert based on left and right subtree
    //Provided a node, insert the node into the tree
    public void insert(Node insert) {
        Node current = root;
        boolean inserted = false;

        while(current != null && !inserted) {
            if (insert.getId() < current.getId()) {
                if (current.getLeftChild() != null) {
                    current = current.getLeftChild();
                }
                else {
                    current.setLeftChild(insert);
                    insert.setParent(current);
                    inserted = true;
                }
            }
            else if (insert.getId() > current.getId()) {
                if (current.getRightChild() != null) {
                    current = current.getRightChild();
                }
                else {
                    current.setRightChild(insert);
                    insert.setParent(current);
                    inserted = true;
                }
            }
        }
    }

    public boolean delete(int id) {
        if(find(id).getId() == -1) {
            return false;
        }

        Node toDelete = find(id);

        //If the node has no children
        if(toDelete.getRightChild() == null && toDelete.getLeftChild() == null) {
            return deleteNoChildren(toDelete);
        }
        //If the node has a left child, but no right child
        else if(toDelete.getRightChild() == null && toDelete.getLeftChild() != null) {
            return deleteSingleChild(toDelete, false);
        }
        //If the node has a right child, but no left child
        else if(toDelete.getRightChild() != null && toDelete.getLeftChild() == null) {
            return deleteSingleChild(toDelete, true);
        }
        //If the node has two children
        else if(toDelete.getLeftChild() != null && toDelete.getRightChild() != null) {
            return deleteTwoChildren(toDelete);
        }
        return false;
    }

    //Deletes a node with no children
    private boolean deleteNoChildren(Node toDelete) {
        if(toDelete.getParent().getId() > toDelete.getId()) {
            toDelete.getParent().setLeftChild(null);
            return true;
        }
        else if(toDelete.getParent().getId() < toDelete.getId()) {
            toDelete.getParent().setRightChild(null);
            return true;
        }
        return false;
    }

    //Deletes a node with a single child
    //Side = true if the node to be deleted has no left child and side = false if the node to be deleted has no right child
    private boolean deleteSingleChild(Node toDelete, boolean side) {
        Node temp;
        if(side) {
            temp = toDelete.getRightChild();
        }
        else{
            temp = toDelete.getLeftChild();
        }

        temp.setParent((toDelete.getParent()));

        if(toDelete.getParent().getLeftChild() == toDelete) {
            toDelete.getParent().setLeftChild(temp);
            return true;
        }
        else if(toDelete.getParent().getRightChild() == toDelete) {
            toDelete.getParent().setRightChild(temp);
            return true;
        }
        return false;
    }

    //Deletes a node with two children
    private boolean deleteTwoChildren(Node toDelete) {
        getSuccesor(toDelete);

        return false;
    }

    //Gets smallest node in the right subtree of the node to be deleted
    private Node getSuccesor(Node toDelete) {
        Node smallest = toDelete;
        Node original = toDelete;
        Node current = toDelete;

        current = current.getRightChild();

        while(current.getChildCount() > 0) {
            current = current.getLeftChild();
        }
        smallest = current;

        while((current.getParent() != original) && (current.getParent().getRightChild() != original)) {

        }
        return toDelete;
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

    //TODO Primary goal
    public void breadthFirstSearch() {

    }
}
