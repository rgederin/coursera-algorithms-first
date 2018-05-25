# Algorithms, Part I by Princeton University (via Coursera)

This course covers the essential information that every serious programmer needs to know about algorithms and data structures, with emphasis on applications and scientific performance analysis of Java implementations. Part I covers elementary data structures, sorting, and searching algorithms. Part II focuses on graph- and string-processing algorithms.

https://www.coursera.org/learn/algorithms-part1/home/welcome

- [Order of growth](#order-of-growth)
- [Binary search](#binary-search)
- [Stack and Queue](#stack-and-queue)
    * [Stack](#stack)
    * [Queue](#queue)
- [Sorting](#sorting)
    * [Selection sort](#selection-sort)
    * [Insertion sort](#insertion-sort)

# Order of growth


![order](https://github.com/rgederin/coursera-algorithms-first/blob/master/img/order1.png)

![order](https://github.com/rgederin/coursera-algorithms-first/blob/master/img/order2.png)


# Binary search


![bseacrh](https://github.com/rgederin/coursera-algorithms-first/blob/master/img/bsearch.png)

```
     public static int binarySearch(int[] a, int key) {
         int lo = 0, hi = a.length - 1;
         while (lo <= hi) {
             int mid = lo + (hi - lo) / 2;
 
             if (key < a[mid]) hi = mid - 1;
             
             else if (key > a[mid]) lo = mid + 1;
             
             else return mid;
         }
         return -1;
     }
    
    public static int binarySearchRecursive(int search, int[] array, int start, int end){
    
            int middle = (start + end)/2;
    
            if(end < start)
                return -1;
            
            if (search < array[middle])
                return binarySearchRecursive(search, array, start, middle - 1);
            
            if (search > array[middle])
                return binarySearchRecursive(search, array, middle + 1, end);
            
            if (search == array[middle])
                return middle;
            
            return -1;
    }
    
```

# Stack and Queue

![order](https://github.com/rgederin/coursera-algorithms-first/blob/master/img/s1.png)


## Stack


![order](https://github.com/rgederin/coursera-algorithms-first/blob/master/img/s2.png)


```
public class LinkedStackOfStrings {
        private Node first = null;

        private class Node {
            String item;
            Node next;
        }

        public boolean isEmpty() {
            return first == null;
        }

        public void push(String item) {
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
        }

        public String pop() {
            String item = first.item;
            first = first.next;
            return item;
        }
    }
```


![order](https://github.com/rgederin/coursera-algorithms-first/blob/master/img/s3.png)

![order](https://github.com/rgederin/coursera-algorithms-first/blob/master/img/s4.png)

![order](https://github.com/rgederin/coursera-algorithms-first/blob/master/img/s5.png)


## Queue

![order](https://github.com/rgederin/coursera-algorithms-first/blob/master/img/q1.png)

![order](https://github.com/rgederin/coursera-algorithms-first/blob/master/img/q2.png)

![order](https://github.com/rgederin/coursera-algorithms-first/blob/master/img/q3.png)


# Sorting

![sort](https://github.com/rgederin/coursera-algorithms-first/blob/master/img/sort1.png)

## Selection sort

![sort](https://github.com/rgederin/coursera-algorithms-first/blob/master/img/sort2.png)

![sort](https://github.com/rgederin/coursera-algorithms-first/blob/master/img/sort3.png)

![sort](https://github.com/rgederin/coursera-algorithms-first/blob/master/img/sort4.png)

```
/**
 * Selection sort:
 * 
 * 1. In iteration i, find index min of smallest remaining entry.
 * 2. Swap a[i] and a[min].
 */
public class SelectionSort {
    public static void sort(Comparable[] a) {
        int N = a.length;

        for (int i = 0; i < N; i++) {
            int min = i;

            for (int j = i + 1; j < N; j++)
                if (less(a[j], a[min]))
                    min = j;

            exchange(a, i, min);
        }
    }
}
```

From the comparions presented here, one might conclude that selection sort should never be used. It does not adapt to the data in any way (notice that the four animations above run in lock step), so its runtime is always quadratic.

However, selection sort has the property of minimizing the number of swaps. In applications where the cost of swapping items is high, selection sort very well may be the algorithm of choice.

**PROPERTIES:**

* Not stable
* O(1) extra space
* Θ(n2) comparisons
* Θ(n) swaps
* Not adaptive

https://www.toptal.com/developers/sorting-algorithms/selection-sort

## Insertion sort

![sort](https://github.com/rgederin/coursera-algorithms-first/blob/master/img/sort5.png)

![sort](https://github.com/rgederin/coursera-algorithms-first/blob/master/img/sort6.png)

![sort](https://github.com/rgederin/coursera-algorithms-first/blob/master/img/sort7.png)

```
/**
 * In iteration i, swap a[i] with each larger entry to its left.
 */
public class InsertionsSort {
    public static void sort(Comparable[] a) {
        int N = a.length;
        
        for (int i = 0; i < N; i++)
            
            for (int j = i; j > 0; j--)
                if (less(a[j], a[j - 1]))
                    exchange(a, j, j - 1);
                else break;
    }
}
```
Although it is one of the elementary sorting algorithms with O(n2) worst-case time, insertion sort is the algorithm of choice either when the data is nearly sorted (because it is adaptive) or when the problem size is small (because it has low overhead).

For these reasons, and because it is also stable, insertion sort is often used as the recursive base case (when the problem size is small) for higher overhead divide-and-conquer sorting algorithms, such as merge sort or quick sort

**PROPERTIES:**

* Stable
* O(1) extra space
* O(n2) comparisons and swaps
* Adaptive: O(n) time when nearly sorted
* Very low overhead


# Grades

5/5 Assignments Passed
94.8% Final Grade

  1. Union-Find -                     Programming Assignment: Percolation -       95%
  2. Stacks and Queues -              Deques and Randomized Queues -              95%
  3. Mergesort -                      Programming Assignment: Collinear Points -  97%
  4. Priority Queues -                Programming Assignment: 8 Puzzle -          87 %
  5. Geometric Applications of BSTs - Programming Assignment: Kd-Trees -          100%

