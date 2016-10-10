

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by rgederin on 10/6/2016.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size = 0;
    private Item[] internalArray;

    public RandomizedQueue() {
        internalArray = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        //when internal array is full - resize it twice
        if (internalArray.length == size) {
            resize(2 * internalArray.length);
        }

        //add new element in internal array at last position and increment size of array
        internalArray[size++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        //chose random index in range [0, size)
        int randomIndex = StdRandom.uniform(size);

        //get item by random index
        Item item = internalArray[randomIndex];

        //replace last item in array to random index position
        internalArray[randomIndex] = internalArray[size - 1];

        //set last array element in null and decrement size
        internalArray[size - 1] = null;
        size--;

        // shrink size of array if necessary
        if (size > 0 && size == internalArray.length / 4) resize(internalArray.length / 2);

        return item;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        //chose random index in range [0, size]
        int randomIndex = StdRandom.uniform(size);

        //get item by random index
        Item item = internalArray[randomIndex];


        return item;
    }


    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++)
            copy[i] = internalArray[i];
        internalArray = copy;
    }

    public Iterator<Item> iterator() {
        // return an independent iterator over items in random order
        return new RandomIterator();
    }


    private class RandomIterator implements Iterator<Item> {

        // private int iteratorArray[] = new int[size];
        private int[] iteratorArray = new int[size];
        int current = 0;

        public RandomIterator() {
            for (int i = 0; i < size; i++) {
                iteratorArray[i] = i;
                StdRandom.shuffle(iteratorArray);
            }
        }

        public boolean hasNext() {
            return current != size;
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();

            return internalArray[iteratorArray[current++]];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        System.out.println(rq.iterator().hasNext());
        rq.enqueue(3);
        rq.enqueue(4);
        rq.enqueue(5);
        rq.enqueue(6);
        rq.enqueue(7);

        Iterator<Integer> iterator = rq.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }

}
