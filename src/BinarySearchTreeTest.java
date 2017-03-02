
public class BinarySearchTreeTest {

    public static void main (String[] args) {
        BinarySearchTree BST = new BinarySearchTree(new Node(8));
        BST.insert(new Node(3));
        BST.insert(new Node(10));
        BST.insert(new Node(1));
        BST.insert(new Node(6));
        BST.insert(new Node(4));
        BST.insert(new Node(7));
        BST.insert(new Node(14));
        BST.insert(new Node(13));

        System.out.println(BST.getRoot().getLeftChild());
        System.out.println(BST.getRoot().getRightChild());
        System.out.println(BST.find(6));
        //System.out.println(BST.delete(6));
        //System.out.println(BST.find(6));
        System.out.println(BST.find(6).getParent().getParent().getParent());
    }
}
