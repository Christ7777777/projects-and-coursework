package edu.iastate.cs228.hw3;

import java.util.AbstractSequentialList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Implementation of the list interface based on linked nodes
 * that store multiple items per node.  Rules for adding and removing
 * elements ensure that each node (except possibly the last one)
 * is at least half full.
 */
public class MultiList<E extends Comparable<? super E>> extends AbstractSequentialList<E> {
    /**
     * Default number of elements that may be stored in each node.
     */
    private static final int DEFAULT_NODESIZE = 4;

    /**
     * Number of elements that can be stored in each node.
     */
    private final int nodeSize;

    /**
     * Dummy node for head.  It should be private but set to public here only
     * for grading purpose.  In practice, you should always make the head of a
     * linked list a private instance variable.
     */
    public Node head;

    /**
     * Dummy node for tail.
     */
    private Node tail;

    /**
     * Number of elements in the list.
     */
    private int size;

    /**
     * Constructs an empty list with the default node size.
     */
    public MultiList() {
        this(DEFAULT_NODESIZE);
    }

    /**
     * Constructs an empty list with the given node size.
     *
     * @param nodeSize number of elements that may be stored in each node, must be
     *                 an even number
     */
    public MultiList(int nodeSize) {
        if (nodeSize <= 0 || nodeSize % 2 != 0) throw new IllegalArgumentException();

        // dummy nodes
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.previous = head;
        this.nodeSize = nodeSize;
    }

    /**
     * Constructor for grading only.  Fully implemented.
     *
     * @param head
     * @param tail
     * @param nodeSize
     * @param size
     */
    public MultiList(Node head, Node tail, int nodeSize, int size) {
        this.head = head;
        this.tail = tail;
        this.nodeSize = nodeSize;
        this.size = size;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(E item) {
        add(size(), item);
        return true;
    }

    @Override
    public void add(int pos, E item) {
    	
        // checking null for item
        if (item == null) {
            throw new NullPointerException();
        }

        // checking, that given pos is in allowed bounds
        if (pos < 0 || pos > size()) {
            throw new IndexOutOfBoundsException();
        }

        if (head.next == tail) {
            Node newNode = new Node();
            tail.previous = newNode;
            newNode.next = tail;
            head.next = newNode;
            newNode.previous = head;
            newNode.addItem(item);
        } else {
            NodeInfo searchResult = find(pos);
            Node current = searchResult.node;
            int offset = searchResult.offset;

            if (offset == 0 && ((current.previous != head && current.previous.count < nodeSize) || (current == tail && current.previous.count == nodeSize) )) {
                if (current.previous != head && current.previous.count < nodeSize) {
                    current.previous.addItem(item);
                } else {
                    Node newNode = new Node();
                    current.previous.next = newNode;
                    newNode.previous = current.previous;
                    current.previous = newNode;
                    newNode.next = current;
                    newNode.addItem(item);
                }
            } else {
                if (current.count < nodeSize) {
                    current.addItem(offset, item);
                } else {
                    Node newNode = new Node();
                    current.next.previous = newNode;
                    newNode.next = current.next;
                    current.next = newNode;
                    newNode.previous = current;
                    for (int i = 0; i < nodeSize / 2; i++) {
                        newNode.addItem(current.data[nodeSize - nodeSize / 2 + i]);
                    }
                    for (int i = 0; i < nodeSize / 2; i++) {
                        current.removeItem(nodeSize - nodeSize / 2 + i);
                    }

                    if (offset <= nodeSize / 2) {
                        current.addItem(offset, item);
                    } else {
                        newNode.addItem(offset - nodeSize / 2, item);
                    }
                }
            }
        }
        size++;
    }

    @Override
    public E remove(int pos) {
        // checking, that given pos is in allowed bounds
        if (pos < 0 || pos >= size()) {
            throw new IndexOutOfBoundsException();
        }

        NodeInfo searchResult = find(pos);
        Node current = searchResult.node;
        int offset = searchResult.offset;

        E result = current.data[offset];
        if (current.next == tail && current.count == 1) {
            current.previous.next = current.next;
            current.next.previous = current.previous;
        } else {
            if (current.next == tail || current.count > nodeSize / 2) {
                current.removeItem(offset);
            } else {
                if (current.next.count > nodeSize / 2) {
                    current.removeItem(offset);
                    current.addItem(current.next.data[0]);
                    current.next.removeItem(0);
                } else {
                    Node toRemove = current.next;
                    current.next.next.previous = current;
                    current.next = current.next.next;
                    current.removeItem(offset);
                    for (int i = 0; i < toRemove.count; i++) {
                        current.addItem(toRemove.data[i]);
                    }
                }
            }
        }
        size--;
        return result;
    }

    /**
     * Sort all elements in the Multi list in NON-DECREASING order. You may do the following.
     * Traverse the list and copy its elements into an array, deleting every visited node along
     * the way.  Then, sort the array by calling the insertionSort() method.  (Note that sorting
     * efficiency is not a concern for this project.)  Finally, copy all elements from the array
     * back to the Multi list, creating new nodes for storage. After sorting, all nodes but
     * (possibly) the last one must be full of elements.
     * <p>
     * Comparator<E> must have been implemented for calling insertionSort().
     */
    public void sort() {
        // collecting data from multilist into the array
        Iterator<E> iterator = iterator();
        E[] array = (E[]) new Comparable[size()];
        int counter = 0;
        while (iterator.hasNext()) {
            array[counter] = iterator.next();
            counter++;
        }

        // sorting objects of array in natural order
        insertionSort(array, new Comparator<E>() {
            @Override
            public int compare(E o1, E o2) {
                return o1.compareTo(o2);
            }
        });

        // clearing multilist
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.previous = head;
        size = 0;

        // adding all elements one by one
        for (E element : array) {
            add(element);
        }
    }

    /**
     * Sort all elements in the Multi list in NON-INCREASING order. Call the bubbleSort()
     * method.  After sorting, all but (possibly) the last nodes must be filled with elements.
     * <p>
     * Comparable<? super E> must be implemented for calling bubbleSort().
     */
    public void sortReverse() {
        // collecting data from multilist into the array
        Iterator<E> iterator = iterator();
        E[] array = (E[]) new Comparable[size()];
        int counter = 0;
        while (iterator.hasNext()) {
            array[counter] = iterator.next();
            counter++;
        }

        // sorting objects of array in natural order
        bubbleSort(array);

        // clearing multilist
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.previous = head;
        size = 0;

        // inserting elements of the sorted array in reversed order to receive reversed result
        for (int i = array.length - 1; i >= 0; i--) {
            add(array[i]);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return listIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new MultiListIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new MultiListIterator(index);
    }

    /**
     * Returns a string representation of this list showing
     * the internal structure of the nodes.
     */
    public String toStringInternal() {
        return toStringInternal(null);
    }

    /**
     * Returns a string representation of this list showing the internal
     * structure of the nodes and the position of the iterator.
     *
     * @param iter an iterator for this list
     */
    public String toStringInternal(ListIterator<E> iter) {
        int count = 0;
        int position = -1;
        if (iter != null) {
            position = iter.nextIndex();
        }

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        Node current = head.next;
        while (current != tail) {
            sb.append('(');
            E data = current.data[0];
            if (data == null) {
                sb.append("-");
            } else {
                if (position == count) {
                    sb.append("| ");
                    position = -1;
                }
                sb.append(data.toString());
                ++count;
            }

            for (int i = 1; i < nodeSize; ++i) {
                sb.append(", ");
                data = current.data[i];
                if (data == null) {
                    sb.append("-");
                } else {
                    if (position == count) {
                        sb.append("| ");
                        position = -1;
                    }
                    sb.append(data.toString());
                    ++count;

                    // iterator at end
                    if (position == size && count == size) {
                        sb.append(" |");
                        position = -1;
                    }
                }
            }
            sb.append(')');
            current = current.next;
            if (current != tail)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Helper class for implementing finding indices
     */
    private class NodeInfo
    {
        public Node node;
        public int offset;
        public NodeInfo(Node node, int offset)
        {
            this.node = node;
            this.offset = offset;
        }

        NodeInfo previous() {
            if (node == head) {
                return null;
            }

            if (offset == 0 && node.previous == head) {
                return new NodeInfo(head, 0);
            }
            else if (offset == 0){
                return new NodeInfo(node.previous, node.previous.count - 1);
            }
            else {
                return new NodeInfo(node, offset - 1);
            }
        }

        NodeInfo next() {
            if (node == tail) {
                return null;
            }

            if (offset == node.count - 1 && node.next == tail) {
                return new NodeInfo(tail, 0);
            }
            else if (offset == node.count - 1){
                return new NodeInfo(node.next, 0);
            }
            else {
                return new NodeInfo(node, offset + 1);
            }
        }
    }

    // returns the node and offset for the given logical index
    NodeInfo find(int pos) {
        if (pos == -1) {
            return new NodeInfo(head, 0);
        }
        Node current = head.next;
        int offset = pos;
        while (offset >= current.count && offset > 0) {
            offset -= current.count;
            current = current.next;
        }
        return new NodeInfo(current, offset);
    }


    /**
     * Node type for this list.  Each node holds a maximum
     * of nodeSize elements in an array.  Empty slots
     * are null.
     */
    private class Node {
        /**
         * Array of actual data elements.
         */
        // Unchecked warning unavoidable.
        public E[] data = (E[]) new Comparable[nodeSize];

        /**
         * Link to next node.
         */
        public Node next;

        /**
         * Link to previous node;
         */
        public Node previous;

        /**
         * Index of the next available offset in this node, also
         * equal to the number of elements in this node.
         */
        public int count;

        /**
         * Adds an item to this node at the first available offset.
         * Precondition: count < nodeSize
         *
         * @param item element to be added
         */
        void addItem(E item) {
            if (count >= nodeSize) {
                return;
            }
            data[count++] = item;
            //useful for debugging
            //      System.out.println("Added " + item.toString() + " at index " + count + " to node "  + Arrays.toString(data));
        }

        /**
         * Adds an item to this node at the indicated offset, shifting
         * elements to the right as necessary.
         * <p>
         * Precondition: count < nodeSize
         *
         * @param offset array index at which to put the new element
         * @param item   element to be added
         */
        void addItem(int offset, E item) {
            if (count >= nodeSize) {
                return;
            }
            for (int i = count - 1; i >= offset; --i) {
                data[i + 1] = data[i];
            }
            ++count;
            data[offset] = item;
            //useful for debugging
//      System.out.println("Added " + item.toString() + " at index " + offset + " to node: "  + Arrays.toString(data));
        }

        /**
         * Deletes an element from this node at the indicated offset,
         * shifting elements left as necessary.
         * Precondition: 0 <= offset < count
         *
         * @param offset
         */
        void removeItem(int offset) {
            E item = data[offset];
            for (int i = offset + 1; i < nodeSize; ++i) {
                data[i - 1] = data[i];
            }
            data[count - 1] = null;
            --count;
        }
    }

    private class MultiListIterator implements ListIterator<E> {
        NodeInfo front;
        int frontIndex;
        NodeInfo back;
        int backIndex;
        int lastReturnedIndex;

        /**
         * Default constructor
         */
        public MultiListIterator() {
            this(0);
        }

        /**
         * Constructor finds node at a given position.
         *
         * @param pos
         */
        public MultiListIterator(int pos) {
            if (pos < 0 || pos > size()) {
                throw new IndexOutOfBoundsException();
            }

            front = find(pos);
            back = front.previous();
            frontIndex = pos;
            backIndex = pos-1;
            lastReturnedIndex = -1;
        }

        @Override
        public boolean hasNext() {
            return front.node != tail;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E result = front.node.data[front.offset];
            back = front;
            front = front.next();
            lastReturnedIndex = frontIndex;
            frontIndex++;
            backIndex++;
            return result;
        }

        @Override
        public boolean hasPrevious() {
            return back.node != head;
        }

        @Override
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            E result = back.node.data[back.offset];
            front = back;
            back = back.previous();
            lastReturnedIndex = backIndex;
            frontIndex--;
            backIndex--;
            return result;
        }

        @Override
        public int nextIndex() {
            return frontIndex;
        }

        @Override
        public int previousIndex() {
            return backIndex;
        }

        // Other methods you may want to add or override that could possibly facilitate
        // other operations, for instance, addition, access to the previous element, etc.
        //
        // ...
        //
        @Override
        public void remove() {
            if (lastReturnedIndex == -1) {
                throw new IllegalStateException();
            }

            MultiList.this.remove(lastReturnedIndex);
            if (lastReturnedIndex == backIndex) {
                front = find(frontIndex - 1);
                back = front.previous();
                frontIndex--;
                backIndex = frontIndex - 1;
            }
            else {
                front = find(frontIndex);
                back = front.previous();
            }
            lastReturnedIndex = -1;
        }

        @Override
        public void set(E element) {
            if (lastReturnedIndex == -1) {
                throw new IllegalStateException();
            }

            NodeInfo searchResult = find(lastReturnedIndex);
            searchResult.node.data[searchResult.offset] = element;
           // lastReturnedIndex = -1;
        }

        @Override
        public void add(E element) {
            MultiList.this.add(frontIndex, element);
            front = find(frontIndex+1);
            back = front.previous();
            frontIndex++;
            backIndex = frontIndex - 1;
            lastReturnedIndex = -1;
        }
    }


    /**
     * Sort an array arr[] using the insertion sort algorithm in the NON-DECREASING order.
     *
     * @param arr  array storing elements from the list
     * @param comp comparator used in sorting
     */
    private void insertionSort(E[] arr, Comparator<? super E> comp) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            E key = arr[i];
            int j = i - 1;

            while (j >= 0 && comp.compare(arr[j], key) > 0) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    /**
     * Sort arr[] using the bubble sort algorithm in the NON-INCREASING order. For a
     * description of bubble sort please refer to Section 6.1 in the project description.
     * You must use the compareTo() method from an implementation of the Comparable
     * interface by the class E or ? super E.
     *
     * @param arr array holding elements from the list
     */
    private void bubbleSort(E[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    E temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
