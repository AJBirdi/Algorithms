
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

        //BinarySearchTree BST = new BinarySearchTree(new Node(7));
        //BST.insert(new Node(4));
        //BST.insert(new Node(12));
        //BST.insert(new Node(2));
        //BST.insert(new Node(6));
        //BST.insert(new Node(3));
        //BST.insert(new Node(5));
        //BST.insert(new Node(9));
        //BST.insert(new Node(19));
        //BST.insert(new Node(8));
        //BST.insert(new Node(11));
        //BST.insert(new Node(15));
        //BST.insert(new Node(20));

        System.out.println(BST.find(8));
        BST.delete(8);
        System.out.println(BST.find(8));
    }
}
