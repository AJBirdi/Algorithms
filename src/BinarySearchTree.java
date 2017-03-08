//Basic binary search tree that supports insert, find, and delete operations.

public class BinarySearchTree {

    private Node root;

    public BinarySearchTree (Node root) {
       this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    private void setRoot(Node newRoot) {
        root = newRoot;
    }

    //Provided the ID of a node, find and return the node if it exists
    //If it doesn't exist, return a node with ID -1
    public Node find(int id) {
        Node current = root;

        //While the current node hasn't reached the bottom
        while(current != null) {
            //If the current node's ID is the ID we're searching for
            if(id == current.getId()) {
                return current;
            }
            //If the current ID is larger than the ID we're looking for
            else if(id < current.getId()) {
                current = current.getLeftChild();
            }
            //If the current ID is smaller than the ID we're looking for
            else if(id > current.getId()) {
                current = current.getRightChild();
            }
        }
        return new Node(-1);
    }

    //Provided a node, insert the node into the tree
    public void insert(Node insert) {
        Node current = root;
        boolean inserted = false;

        //Run while current hasn't reached the bottom of the tree and a node hasn't been inserted
        while(current != null && !inserted) {
            //If the current node has a larger ID than the node that will be inserted
            if (insert.getId() < current.getId()) {
                //If the current node's left child is not null
                if (current.getLeftChild() != null) {
                    current = current.getLeftChild();
                }
                //If the current node's right child is null
                else {
                    current.setLeftChild(insert);
                    insert.setParent(current);
                    inserted = true;
                }
            }
            //If the current node has a smaller ID than the node that will be inserted
            else if (insert.getId() > current.getId()) {
                //If the current node's right child is not null
                if (current.getRightChild() != null) {
                    current = current.getRightChild();
                }
                //If the current node's left child is not null
                else {
                    current.setRightChild(insert);
                    insert.setParent(current);
                    inserted = true;
                }
            }
        }
    }

    //Delete a node given it's ID
    public boolean delete(int id) {
        //Make sure the node exists
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
        //If the node is the root
        if(toDelete.getParent() == null) {
           System.out.println("Can't delete the root node.");
           return false;
        }

        //If the node has a parent
        if(toDelete.getParent() != null) {
            //If the node is the left child
            if(toDelete.getParent().getId() > toDelete.getId()) {
                toDelete.getParent().setLeftChild(null);
                return true;
            }
            //If the node is the right child
            else if(toDelete.getParent().getId() < toDelete.getId()) {
                toDelete.getParent().setRightChild(null);
                return true;
            }
        }
        return false;
    }

    //Deletes a node with a single child
    //side = true if the node to be deleted has no left child
    //side = false if the node to be deleted has no right child
    private boolean deleteSingleChild(Node toDelete, boolean side) {
        Node temp;

        //If the node only has a right child
        if(side) {
            temp = toDelete.getRightChild();
        }
        //If the node only has a left child
        else{
            temp = toDelete.getLeftChild();
        }

        //Make sure the node that will be deleted has a parent
        if(toDelete.getParent() != null) {
            temp.setParent((toDelete.getParent()));

            //If the node is the left child
            if(toDelete.getParent().getLeftChild() == toDelete) {
                toDelete.getParent().setLeftChild(temp);
                return true;
            }
            //If the node is the right child
            else if(toDelete.getParent().getRightChild() == toDelete) {
                toDelete.getParent().setRightChild(temp);
                return true;
            }
        }
        return false;
    }

    //Deletes a node with two children
    private boolean deleteTwoChildren(Node toDelete) {
        Node successor = getSuccessor(toDelete);

        //If the successor has 0 children
        if(successor.getChildCount() == 0) {
            //If the successor is a left child
            if(successor.getParent().getLeftChild() == successor) {
                successor.getParent().setLeftChild(null);
            }
            //If the successor is a right child
            else if(successor.getParent().getRightChild() == successor) {
                successor.getParent().setRightChild(null);
            }
        }
        //If the successor has a single child
        else if(successor.getChildCount() == 1) {
            //If the node has a parent, move the node's child up to its place to maintain the hierarchy
            if(successor.getParent() != null) {
                Node parent = successor.getParent();
                //If the successor is the left child
                if(successor.getParent().getLeftChild() == successor) {
                    successor.getParent().setLeftChild(successor.getRightChild());
                    parent.getLeftChild().setParent(parent);
                }
                //If the successor is the right child
                else if (successor.getParent().getRightChild() == successor) {
                    successor.getParent().setRightChild(successor.getRightChild());
                    parent.getRightChild().setParent(parent);
                }
            }
        }

        //If the node has a parent/isn't the root node of the tree
        if(toDelete.getParent() != null) {
            //The node to be deleted is the left child
            if(toDelete.getParent().getLeftChild() == toDelete) {
                toDelete.getParent().setLeftChild(successor);
            }
            //The node to be deleted is the right child
            else if(toDelete.getParent().getRightChild() == toDelete) {
                toDelete.getParent().setRightChild(successor);
            }
        }

        //If the node is the root
        if(toDelete.getParent() == null) {
            setRoot(successor);
        }
        //Move children from the to be deleted node to the successor
        successor.setLeftChild(toDelete.getLeftChild());
        successor.setRightChild(toDelete.getRightChild());

        toDelete.setLeftChild(null);
        toDelete.setRightChild(null);

        return true;
    }

    //Gets smallest node in the right subtree of the node to be deleted
    private Node getSuccessor(Node toDelete) {
        Node current = toDelete;

        //Since the node has to have 2 children, immediately go to the right subtree
        current = current.getRightChild();

        //Keep running down the left subtree of the first node in the right subtree until we reach the last node
        while(current.getChildCount() > 0 && current.getLeftChild() != null) {
            current = current.getLeftChild();
        }
        return current;
    }

    //TODO Secondary goal
    //public void inorderTraversal() {
    //}

    //TODO Secondary goal
    //public void postorderTraversal() {
    //}

    //TODO Secondary goal
    //public void preorderTraversal() {
    //}

    //TODO Secondary goal
    //public void breadthFirstSearch() {
    //}
}
