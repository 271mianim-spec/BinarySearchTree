import java.util.*;

/**
 * This BinarySearchTree object defines a reference based binary search tree
 * 
 * @author  
 * @version 
 */

public class BinarySearchTree<T extends Comparable<T>> implements BSTInterface<T>
{

    protected BinaryNode<T> root;      // reference to the root of this BST

    protected int size;

    // Creates an empty Binary Search Tree object
    public BinarySearchTree()
    {
        this.size = 0;
    }

    // Returns true if this BST is empty; otherwise, returns false.
    public boolean isEmpty()
    {
        return root == null;
    }

    // Returns the number of elements in this BST.
    public int size()
    {
        return this.size;
//        int counter = 0;
//        if (isEmpty()) {
//            return 0;
//        }
//
//        return count(counter);
    }

//    public int count(int count) {
//
//        return 0;
//    }

    // Adds element to this BST. The tree retains its BST property.
    public void add (T element)
    {
        size++;
        BinaryNode<T> newNode = new BinaryNode<T>(element);
        if (isEmpty()) {
            root = newNode;

            return;
        }
        BinaryNode<T> pointer = root;
        while (pointer != null) {
            if (pointer.getInfo().compareTo(element) > 0) {
                if (pointer.getLeft() == null) {
                    pointer.setLeft(newNode);
                    break;
                } else {
                    pointer = pointer.getLeft();
                }
            } else {
                if (pointer.getRight() == null) {
                    pointer.setRight(newNode);
                    break;
                } else {
                    pointer = pointer.getRight();
                }

            }
        }

    }

    // Returns true if this BST contains an element e such that 
    // e.compareTo(element) == 0; otherwise, returns false.
    public boolean contains (T element)
    {
        BinaryNode<T> pointer = root;
        if (pointer.getInfo().equals(element)) {
            return true;
        }
        while (pointer != null) {

            if (pointer.getInfo().compareTo(element) > 0) {
                if (pointer.getLeft() == null) {
                    return false;
                } else {
                    if (pointer.getLeft().getInfo().equals(element)) {
                        return true;
                    } else {
                        pointer = pointer.getLeft();
                    }
                }

            } else {
                if (pointer.getRight() == null) {
                    return false;
                } else {
                    if (pointer.getRight().getInfo().equals(element)) {
                        return true;
                    } else {
                        pointer = pointer.getRight();
                    }
                }
            }
        }
        return false;
    }

    // Returns a graphical representation of the tree
    public String toString()
    {
        return toString(root, 0);
    }

    private String toString(BinaryNode<T> tree, int level)
    {
        String str = "";
        if (tree != null)
        {
            str += toString(tree.getRight(), level + 1);
            for (int i = 1; i <= level; ++i)
                str = str + "| ";
            str += tree.getInfo().toString() + "\n";
            str += toString(tree.getLeft(), level + 1);
        }
        return str;
    }

    // Returns a list of elements from a preorder traversal
    // pLR (print left right)
    public List<T> preorderTraverse()
    {
        return null;
    }

    // Returns a list of elements from an inorder traversal
    // LpR
    public List<T> inorderTraverse()
    {
        return null;
    }

    // Returns a list of elements from a postorder traversal
    // LRp
    public List<T> postorderTraverse()
    {
        return null;
    }

    // Removes an element e from this BST such that e.compareTo(element) == 0
    public void remove (T element)
    {
    }

    // Restructures this BST to be optimally balanced
    public void balance()
    {
    }
}