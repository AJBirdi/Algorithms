
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

        System.out.println(BST.find(10));
        BST.delete(10);
        System.out.println(BST.find(8).getRightChild().getLeftChild().getLeftChild());
    }
}
