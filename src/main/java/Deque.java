

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by rgederin on 10/6/2016.
 */
public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int size;
    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    public Deque() {
        // construct an empty deque
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        size++;
        if (isEmpty()) {
            first = new Node();
            first.item = item;
            last = first;
            return;
        }
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        oldFirst.prev = first;
    }

   /* private void addFirstInEmptyDeque(Item item) {
        first = new Node();
        first.item = item;
        last = first;
    }*/

   /* private void addFirstInNonEmptyDeque(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        oldFirst.prev = first;
    }*/

    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        size++;
        if (isEmpty()) {
            last = new Node();
            last.item = item;
            first = last;
            return;
        }
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.prev = oldLast;
        oldLast.next = last;
    }

   /* private void addLastInEmptyDeque(Item item) {
        last = new Node();
        last.item = item;
        first = last;
    }
*/
   /* private void addLastInNonEmptyDeque(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.prev = oldLast;
        oldLast.next = last;
    }
*/

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item item = first.item;

        if (size() == 1) {
            first = null;
            last = null;

        } else {
            first = first.next;
            first.prev = null;
        }

        size--;
        return item;
    }

    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        Item item = last.item;

        if (size() == 1) {
            first = null;
            last = null;
        } else {
            last = last.prev;
            last.next = null;
        }

        size--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }


    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {

    }

}
