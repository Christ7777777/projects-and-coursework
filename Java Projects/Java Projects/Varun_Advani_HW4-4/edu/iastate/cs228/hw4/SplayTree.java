package edu.iastate.cs228.hw4;


import java.util.AbstractSet;
import java.util.Iterator;
import java.util.NoSuchElementException;



/**
 * @author Varun Advani
 */


/**
 * This class implements a splay tree.  Add any helper methods or implementation details
 * you'd like to include.
 */


public class SplayTree<E extends Comparable<? super E>> extends AbstractSet<E> {
    protected Node root;
    protected int size;

    public class Node  // made public for grading purpose
    {
        public E data;
        public Node left;
        public Node parent;
        public Node right;

        public Node(E data) {
            this.data = data;
        }

        @Override
        public Node clone() {
            return new Node(data);
        }
    }


    /**
     * Default constructor constructs an empty tree.
     */
    public SplayTree() {
        size = 0;
    }


    /**
     * Needs to call addBST() later on to complete tree construction.
     */
    public SplayTree(E data) {
        addBST(data);
        size = 1;
    }


    /**
     * Copies over an existing splay tree. The entire tree structure must be copied.
     * No splaying. Calls cloneTreeRec().
     *
     * @param tree
     */
    public SplayTree(SplayTree<E> tree) {
        root = cloneTreeRec(tree.root);
        root.parent = null;
        size = tree.size();
    }


    /**
     * Recursive method called by the constructor above.
     *
     * @param subTree
     * @return
     */
    public Node cloneTreeRec(Node subTree) {
        if (subTree == null) {
            return null;
        }
        Node newNode = new Node(subTree.data);
        Node newLeft = cloneTreeRec(subTree.left);
        if (newLeft != null) {
            newLeft.parent = newNode;
        }
        Node newRight = cloneTreeRec(subTree.right);
        if (newRight != null) {
            newRight.parent = newNode;
        }
        newNode.left = newLeft;
        newNode.right = newRight;
        return newNode;
    }


    /**
     * This function is here for grading purpose. It is not a good programming practice.
     *
     * @return element stored at the tree root
     */
    public E getRoot() {
        if (isEmpty()) {
            return null;
        }
        return root.data;
    }


    @Override
    public int size() {
        return size;
    }


    /**
     * Clear the splay tree.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }


    // ----------
    // BST method
    // ----------

    /**
     * Adds an element to the tree without splaying.  The method carries out a binary search tree
     * addition.  It is used for initializing a splay tree.
     * <p>
     * Calls link().
     *
     * @param data
     * @return true  if addition takes place
     * false otherwise (i.e., data is in the tree already)
     */
    public boolean addBST(E data) {
        Node currNode = root;
        Node parentNode = null;
        while (currNode != null) {
            parentNode = currNode;
            E currData = parentNode.data;
            int diff = currData.compareTo(data);
            if (diff > 0) {
                currNode = currNode.left;
            } else if (diff < 0) {
                currNode = currNode.right;
            } else {
                return false;
            }
        }
        currNode = new Node(data);
        link(parentNode, currNode);
        if (parentNode == null) {
            root = currNode;
        }
        size++;
        return true;
    }


    // ------------------
    // Splay tree methods
    // ------------------

    /**
     * Inserts an element into the splay tree. In case the element was not contained, this
     * creates a new node and splays the tree at the new node. If the element exists in the
     * tree already, it splays at the node containing the element.
     * <p>
     * Calls link().
     *
     * @param data element to be inserted
     * @return true  if addition takes place
     * false otherwise (i.e., data is in the tree already)
     */
    @Override
    public boolean add(E data) {
        Node currNode = root;
        Node parentNode = null;
        while (currNode != null) {
            parentNode = currNode;
            E currData = parentNode.data;
            int diff = currData.compareTo(data);
            if (diff > 0) {
                currNode = currNode.left;
            } else if (diff < 0) {
                currNode = currNode.right;
            } else {
                splay(currNode);
                return false;
            }
        }
        currNode = new Node(data);
        link(parentNode, currNode);
        splay(currNode);
        size++;
        return true;
    }

    /**
     * Determines whether the tree contains an element.  Splays at the node that stores the
     * element.  If the element is not found, splays at the last node on the search path.
     *
     * @param data element to be determined whether to exist in the tree
     * @return true  if the element is contained in the tree
     * false otherwise
     */
    public boolean contains(E data) {
        E result = findElement(data);
        return result != null;
    }


    /**
     * Finds the node that stores the data and splays at it.
     *
     * @param data
     */
    public void splay(E data) {
        contains(data);
    }


    /**
     * Removes the node that stores an element.  Splays at its parent node after removal
     * (No splay if the removed node was the root.) If the node was not found, the last node
     * encountered on the search path is splayed to the root.
     * <p>
     * Calls unlink().
     *
     * @param data element to be removed from the tree
     * @return true  if the
     * <p>
     * object is removed
     * false if it was not contained in the tree
     */
    public boolean remove(E data) {
        if (size == 0)
            return false;
        Node node = findEntry(data);
        if (node.data.compareTo(data) != 0) {
            splay(node);
            return false;
        }
        unlink(node);
        size--;
        if (node.parent != null) {
            splay(node.parent);
        }
        return true;
    }


    /**
     * This method finds an element stored in the splay tree that is equal to data as decided
     * by the compareTo() method of the class E.  This is useful for retrieving the value of
     * a pair <key, value> stored at some node knowing the key, via a call with a pair
     * <key, ?> where ? can be any object of E.
     * <p>
     * Calls findEntry(). Splays at the node containing the element or the last node on the
     * search path.
     *
     * @param data
     * @return element such that element.compareTo(data) == 0
     */
    public E findElement(E data) {
        Node node = findEntry(data);
        E result = null;
        if (node != null) {
            if (data.compareTo(node.data) == 0) {
                result = node.data;
            }
        }
        splay(node);
        return result;
    }


    /**
     * Finds the node that stores an element. It is called by methods such as contains(), add(), remove(),
     * and findElement().
     * <p>
     * No splay at the found node.
     *
     * @param data element to be searched for
     * @return node  if found or the last node on the search path otherwise
     * null  if size == 0.
     */
    public Node findEntry(E data) {
        Node parentNode = null;
        Node currNode = root;
        while (currNode != null) {
            parentNode = currNode;
            E currData = currNode.data;
            int diff = currData.compareTo(data);
            if (diff > 0) {
                currNode = currNode.left;
            } else if (diff < 0) {
                currNode = currNode.right;
            } else {
                return currNode;
            }
        }
        return parentNode;
    }


    /**
     * Join the two subtrees T1 and T2 rooted at root1 and root2 into one.  It is
     * called by remove().
     * <p>
     * Precondition: All elements in T1 are less than those in T2.
     * <p>
     * Access the largest element in T1, and splay at the node to make it the root of T1.
     * Make T2 the right subtree of T1.  The method is called by remove().
     *
     * @param root1 root of the subtree T1
     * @param root2 root of the subtree T2
     * @return the root of the joined subtree
     */
    public Node join(Node root1, Node root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }

        Node largest = root1;
        while (largest.right != null) {
            largest = largest.right;
        }
        splay(largest);
        largest.parent = null;
        link(largest, root2);
        return largest;
    }


    /**
     * Splay at the current node.  This consists of a sequence of zig, zigZig, or zigZag
     * operations until the current node is moved to the root of the tree.
     *
     * @param current node to splay
     */
    protected void splay(Node current) {
        if (current == null) {
            return;
        }

        while (current.parent != null) {
            if (current.parent.parent == null) {
                zig(current);
            } else if ((current.parent.parent.left == current.parent && current.parent.left == current) ||
                    (current.parent.parent.right == current.parent && current.parent.right == current)) {
                zigZig(current);
            } else {
                zigZag(current);
            }
        }
        root = current;
    }


    /**
	 * This method performs the zig operation on a node. Calls leftRotate() 
	 * or rightRotate().
	 * 
	 * @param current  node to perform the zig operation on
	 */
	protected void zig(Node current)
    {
		if(current == null || current.parent == null)
			throw new IllegalStateException();
		if(current.parent.right == current)
			leftRotate(current);
		else
			rightRotate(current);
	}

	
	/**
	 * This method performs the zig-zig operation on a node. Calls leftRotate() 
	 * or rightRotate().
	 * 
	 * @param current  node to perform the zig-zig operation on
	 */
	protected void zigZig(Node current)
	{
		Node parent = current.parent;
		Node grandParent = current.parent.parent;
		if(current == null || parent == null|| grandParent == null)
			throw new IllegalStateException();
		if(parent.left == current && grandParent.left == parent) {
			rightRotate(parent);
			rightRotate(current);
		}
		else if(parent.right == current && grandParent.right == parent) {
			leftRotate(parent);
			leftRotate(current);
		}
	}

	
    /**
	 * This method performs the zig-zag operation on a node. Calls leftRotate() 
	 * and rightRotate().
	 * 
	 * @param current  node to perform the zig-zag operation on
	 */
	protected void zigZag(Node current)
	{
		Node parent = current.parent;
		Node grandParent = current.parent.parent;
		if(current == null || parent == null || grandParent == null)
			throw new IllegalStateException();
		if(parent.left == current && grandParent.right == parent) {
			rightRotate(current);
			leftRotate(current);
		}
		else if(parent.right == current && grandParent.left == parent) {
			leftRotate(current);
			rightRotate(current);
		}
	}	
	
	/**
	 * Carries out a left rotation at a node such that after the rotation its former parent becomes its left child.
	 *
	 * @param current
	 */
	public void leftRotate(Node current) {
		if (current == null || current.parent == null || current == current.parent.left)
			throw new IllegalStateException();
		Node parent = current.parent;
		if (parent == root)
			root = current;
		link(parent.parent, current);
		parent.right = current.left;
		if (current.left != null)
			current.left.parent = parent;
		current.left = parent;
		parent.parent = current;
	}

	/**
	 * Carries out a right rotation at a node such that after the rotation its former parent becomes its right child.
	 *
	 * @param current
	 */
	public void rightRotate(Node current) {
		if (current == null || current.parent == null || current == current.parent.right)
			throw new IllegalStateException();
		Node parent = current.parent;
		if (parent == root)
			root = current;
		link(parent.parent, current);
		parent.left = current.right;
		if (current.right != null)
			current.right.parent = parent;
		current.right = parent;
		parent.parent = current;
	}


    /**
     * Establish the parent-child relationship between two nodes.
     * <p>
     * Called by addBST(), add(), leftRotate(), and rightRotate().
     *
     * @param parent
     * @param child
     */
    private void link(Node parent, Node child) {
        child.parent = parent;
        if (parent != null) {
            E parentData = parent.data;
            E childData = child.data;
            if (parentData.compareTo(childData) > 0) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        }
    }


    /**
     * Removes a node n by replacing the subtree rooted at n with the join of the node's
     * two subtrees.
     * <p>
     * Called by remove().
     *
     * @param n
     */
    private void unlink(Node n) {
        Node parent = n.parent;
        if (n.left != null) {
            n.left.parent = null;
        }
        if (n.right != null) {
            n.right.parent = null;
        }
        Node joined = join(n.left, n.right);
        if (joined == null) {
            if (parent == null) {
                root = null;
                return;
            }

            if (parent.data.compareTo(n.data) > 0) {
                parent.left = null;
            } else {
                parent.right = null;
            }
            return;
        }

        link(parent, joined);
        if (parent == null) {
            root = joined;
        }
    }


    /**
     * Perform BST removal of a node.
     * <p>
     * Called by the iterator method remove().
     *
     * @param n
     */
    public void unlinkBST(Node n) {
        if (n.left != null && n.right != null) {
            Node nextNode = successor(n);
            n.data = nextNode.data;
            n = nextNode;
        }
        Node newNode = null;

        if (n.left != null) {
            newNode = n.left;
        } else if (n.right != null) {
            newNode = n.right;
        }

        if (n.parent == null) {
            root = newNode;
        } else if (n == n.parent.right) {
            n.parent.right = newNode;
        } else if (n == n.parent.left) {
            n.parent.left = newNode;
        }

        if (newNode != null)
            newNode.parent = n.parent;

    }


    /**
     * Called by unlink() and the iterator method next().
     *
     * @param n
     * @return successor of n
     */
    public Node successor(Node n) {
        if (n.right != null) {
            Node curr = n.right;
            while (curr.left != null) {
                curr = curr.left;
            }
            return curr;
        } else {
            Node curr = n;
            Node parent = curr.parent;
            while (parent != null) {
                if (parent.left == curr) {
                    return parent;
                }
                curr = parent;
                parent = curr.parent;
            }
            return null;
        }
    }


    @Override
    public Iterator<E> iterator() {
        return new SplayTreeIterator();
    }


    /**
     * Write the splay tree according to the format specified in Section 2.2 of the project
     * description.
     * <p>
     * Calls toStringRec().
     */
    @Override
	public String toString() {
		return toStringRec(root, 0);
	}

	private String toStringRec(Node n, int depth) {
		String s = "";
		for (int i = 0; i < depth; i++)
			s += "    ";
		if (n == null)
			return s + n + "\n";
		s += n.data + "\n";
		if (n.left == null && n.right == null)
			return s;
		s += toStringRec(n.left, depth + 1);
		s += toStringRec(n.right, depth + 1);
		return s;
	}


    /**
     * Iterator implementation for this splay tree.  The elements are returned in
     * ascending order according to their natural ordering.  The methods hasNext()
     * and next() are exactly the same as those for a binary search tree --- no
     * splaying at any node as the cursor moves.  The method remove() behaves like
     * the class method remove(E data) --- after the node storing data is found.
     */
    private class SplayTreeIterator implements Iterator<E> {
        Node cursor;
        Node pending;

        public SplayTreeIterator() {
            pending = null;
            cursor = root;
            if (cursor == null) {
                return;
            }

            while (cursor.left != null) {
                cursor = cursor.left;
            }
        }

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public E next() {
            if (cursor == null) {
                throw new NoSuchElementException();
            }
            E result = cursor.data;
            pending = cursor;
            cursor = successor(cursor);
            return result;
        }

        /**
         * This method will join the left and right subtrees of the node being removed,
         * and then splay at its parent node.  It behaves like the class method
         * remove(E data) after the node storing data is found.  Place the cursor at the
         * parent (or the new root if removed node was the root).
         * <p>
         * Calls unlinkBST().
         */
        @Override
        public void remove() {
			if (pending == null)
				throw new IllegalStateException();
			unlinkBST(pending);
			size--;
			pending = null;
		}
    }
}







