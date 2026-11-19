import java.lang.reflect.Array;
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

    protected BinaryNode<T> removalParent;

    // Creates an empty Binary Search Tree object
    public BinarySearchTree()
    {}

    // Returns true if this BST is empty; otherwise, returns false.
    public boolean isEmpty()
    {
        return root == null;
    }

    // Returns the number of elements in this BST.
    public int size()
    {
        return sizeIteration(root);
//        int counter = 0;
//        if (isEmpty()) {
//            return 0;
//        }
//
//        return count(counter);
    }

    public int sizeIteration(BinaryNode<T> node) {
        int size = 1;
        if (node.getLeft() != null) {
            size += sizeIteration(node.getLeft());
        }
        if (node.getRight() != null) {
            size += sizeIteration(node.getRight());
        }

        return size;
    }

//    public int count(int count) {
//
//        return 0;
//    }


    // Adds element to this BST. The tree retains its BST property.
    public void add (T element)
    {
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

    // The same as contains but returns the actual node instead of a boolean
    public BinaryNode<T> find(T element)
    {
        BinaryNode<T> pointer = root;
        if (pointer.getInfo().equals(element)) {
            removalParent = null;
            return pointer;
        }
        while (pointer != null) {

            if (pointer.getInfo().compareTo(element) > 0) {
                if (pointer.getLeft() == null) {
                    return null;
                } else {
                    if (pointer.getLeft().getInfo().equals(element)) {
                        removalParent = pointer;
                        return pointer.getLeft();
                    } else {
                        pointer = pointer.getLeft();
                    }
                }

            } else {
                if (pointer.getRight() == null) {
                    return null;
                } else {
                    if (pointer.getRight().getInfo().equals(element)) {
                        removalParent = pointer;
                        return pointer.getRight();
                    } else {
                        pointer = pointer.getRight();
                    }
                }
            }
        }
        return null;
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
        ArrayList<T> printOrder = new ArrayList<T>();

        preorderTraverseIteration(printOrder, root);

        return printOrder;
    }

    public void preorderTraverseIteration(List<T> printOrder, BinaryNode<T> node) {
        printOrder.add(node.getInfo());
        if (node.getLeft() != null) {
            preorderTraverseIteration(printOrder, node.getLeft());
        }
        if (node.getRight() != null) {
            preorderTraverseIteration(printOrder, node.getRight());
        }
    }


    // Returns a list of elements from an inorder traversal
    // LpR
    public List<T> inorderTraverse()
    {
        ArrayList<T> printOrder = new ArrayList<T>();

        inorderTraverseIteration(printOrder, root);

        return printOrder;
    }

    public void inorderTraverseIteration(List<T> printOrder, BinaryNode<T> node) {
        if (node.getLeft() != null) {
            inorderTraverseIteration(printOrder, node.getLeft());
        }
        printOrder.add(node.getInfo());
        if (node.getRight() != null) {
            inorderTraverseIteration(printOrder, node.getRight());
        }
    }

    // Returns a list of elements from a postorder traversal
    // LRp
    public List<T> postorderTraverse()
    {
        ArrayList<T> printOrder = new ArrayList<T>();

        postorderTraverseIteration(printOrder, root);

        return printOrder;
    }

    public void postorderTraverseIteration(List<T> printOrder, BinaryNode<T> node) {
        if (node.getLeft() != null) {
            postorderTraverseIteration(printOrder, node.getLeft());
        }
        if (node.getRight() != null) {
            postorderTraverseIteration(printOrder, node.getRight());
        }
        printOrder.add(node.getInfo());
    }

    // Removes an element e from this BST such that e.compareTo(element) == 0
    public void remove (T element)
    {
        BinaryNode<T> gettingReplaced = find(element);

        if (gettingReplaced == null) {
            return;
        }

        if (gettingReplaced == root && gettingReplaced.getLeft() == null) {
            root = gettingReplaced.getRight();
            return;
        }

        if (gettingReplaced.getLeft() == null && gettingReplaced.getRight() == null) {
            if (removalParent.getInfo().compareTo(gettingReplaced.getInfo()) > 0) {
                removalParent.setLeft(null);
            } else {
                removalParent.setRight(null);
            }
            return;
        }

        if (gettingReplaced.getLeft() == null ) {
            if (removalParent == null) {
                root = gettingReplaced.getRight();
            } else if (removalParent.getLeft() == gettingReplaced) {
                removalParent.setLeft(gettingReplaced.getRight());
            } else {
                removalParent.setRight(gettingReplaced.getRight());
            }
            return;
        }

        if (gettingReplaced.getRight() == null ) {
            if (removalParent == null) {
                root = gettingReplaced.getLeft();
            } else if (removalParent.getRight() == gettingReplaced) {
                removalParent.setRight(gettingReplaced.getLeft());
            } else {
                removalParent.setLeft(gettingReplaced.getLeft());
            }
            return;
        }

        BinaryNode<T> smallestSubNode = gettingReplaced.getRight();

        BinaryNode<T> smallestSubNodeParent = gettingReplaced.getLeft();
        while (smallestSubNode.getLeft() != null) {
            smallestSubNodeParent = smallestSubNode;
            smallestSubNode = smallestSubNode.getLeft();
        }

        smallestSubNode.setLeft(gettingReplaced.getLeft());
        if (smallestSubNode != gettingReplaced) {
            smallestSubNode.setRight(gettingReplaced.getRight());
        }
        if (removalParent.getInfo().compareTo(gettingReplaced.getInfo()) > 0) {
            removalParent.setLeft(smallestSubNode);
        } else {
            removalParent.setRight(smallestSubNode);
        }

    }


    // Restructures this BST to be optimally balanced
    public void balance()
    {
        ArrayList<T> allElements = (ArrayList<T>) inorderTraverse(); // objects

//        System

        int midElement = allElements.size() / 2;

        root = new BinaryNode<T>(allElements.get(midElement));

        for (int i = midElement + 1; i < allElements.size(); i++) {
            BinaryNode<T> temp = (BinaryNode<T>) allElements.get(i);
        }

//        connectBalanceNode(allElements, root, 1);
    }

    public void connectBalanceNode(ArrayList<T> allElements, BinaryNode<T> node, int level) {
//        int nodeInt = allElements.indexOf(node.getInfo());
//        int elementsSize = allElements.size()/(2*level);
//        System.out.println(elementsSize);
//
//        if (elementsSize < 0) {
//            return;
//        }
//
//

//        T leftNodeT = allElements.get((elementsSize/2));
//        BinaryNode<T> leftNode = new BinaryNode<T>(leftNodeT);
//
//        node.setLeft(leftNode);
//
//        T rightNodeT = allElements.get(allElements.size()/2 + elementsSize);
//        BinaryNode<T> rightNode = new BinaryNode<T> (rightNodeT);
//        node.setRight(rightNode);
//
//        System.out.println(toString());
//
//        connectBalanceNode(allElements, leftNode, level+1);
//        connectBalanceNode(allElements, rightNode, level+1);

    }
}