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
        if (root == null) {
            return 0;
        }

        return sizeIteration(root);
//        int counter = 0;
//        if (isEmpty()) {
//            return 0;
//        }
//
//        return count(counter);
    }

    private int sizeIteration(BinaryNode<T> node) {
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

        if (isEmpty()) {
            return false;
        }

        BinaryNode<T> pointer = root;
        if (pointer.getInfo().compareTo(element) == 0) {
            return true;
        }
        while (pointer != null) {

            if (pointer.getInfo().compareTo(element) > 0) {
                if (pointer.getLeft() == null) {
                    return false;
                } else {
                    if (pointer.getLeft().getInfo().compareTo(element) == 0) {
                        return true;
                    } else {
                        pointer = pointer.getLeft();
                    }
                }

            } else {
                if (pointer.getRight() == null) {
                    return false;
                } else {
                    if (pointer.getRight().getInfo().compareTo(element) == 0) {
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
        if (isEmpty()) {
            return null;
        }

        BinaryNode<T> pointer = root;
        if (pointer.getInfo().compareTo(element) == 0) {
            removalParent = null;
            return pointer;
        }
        while (pointer != null) {

            if (pointer.getInfo().compareTo(element) > 0) {
                if (pointer.getLeft() == null) {
                    return null;
                } else {
                    if (pointer.getLeft().getInfo().compareTo(element) == 0) {
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
                    if (pointer.getRight().getInfo().compareTo(element) == 0) {
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
        if (root == null) {
            return printOrder;
        }

        preorderTraverseIteration(printOrder, root);

        return printOrder;
    }

    private void preorderTraverseIteration(List<T> printOrder, BinaryNode<T> node) {
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

        if (root == null) {
            return printOrder;
        }

        inorderTraverseIteration(printOrder, root);

        return printOrder;
    }

    private void inorderTraverseIteration(List<T> printOrder, BinaryNode<T> node) {
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
        if (root == null) {
            return printOrder;
        }

        postorderTraverseIteration(printOrder, root);

        return printOrder;
    }

    private void postorderTraverseIteration(List<T> printOrder, BinaryNode<T> node) {
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

        BinaryNode<T> smallestSubNodeParent = gettingReplaced;


        while (smallestSubNode.getLeft() != null) {
            smallestSubNodeParent = smallestSubNode;
            smallestSubNode = smallestSubNode.getLeft();
        }
        if (smallestSubNodeParent != gettingReplaced) {
            smallestSubNodeParent.setLeft(smallestSubNode.getRight());
            smallestSubNode.setRight(gettingReplaced.getRight());
        }

        smallestSubNode.setLeft(gettingReplaced.getLeft());

        if (removalParent == null) {
            root = smallestSubNode;
        } else if (removalParent.getInfo().compareTo(gettingReplaced.getInfo()) > 0) {
            removalParent.setLeft(smallestSubNode);
        } else {
            removalParent.setRight(smallestSubNode);
        }



    }


    // Restructures this BST to be optimally balanced
    public void balance()
    {
        if (root == null || size() == 1) {
            return;
        }

        ArrayList<T> allElements = (ArrayList<T>) inorderTraverse(); // objects

        int midElement = allElements.size() / 2;

        root = new BinaryNode<T>(allElements.get(midElement));

        connectBalanceNode(allElements, root, 0, midElement - 1, true);
        connectBalanceNode(allElements, root, midElement+1, allElements.size() - 1, false);
    }

    private void connectBalanceNode(ArrayList<T> allElements, BinaryNode<T> node, int min, int max, boolean isLeft) {

        if (min > max) {
            return;
        }

        int midElement = (min+max)/2;
        BinaryNode<T> newNode = new BinaryNode<T>(allElements.get(midElement));

        if (isLeft) {
            node.setLeft(newNode);
        } else {
            node.setRight(newNode);
        }

        connectBalanceNode(allElements, newNode, min, midElement - 1, true);
        connectBalanceNode(allElements, newNode, midElement+1, max, false);

    }
}