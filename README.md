# Algorithms, Part I by Princeton University (via Coursera)

This course covers the essential information that every serious programmer needs to know about algorithms and data structures, with emphasis on applications and scientific performance analysis of Java implementations. Part I covers elementary data structures, sorting, and searching algorithms. Part II focuses on graph- and string-processing algorithms.

https://www.coursera.org/learn/algorithms-part1/home/welcome

- [Order of growth](#order-of-growth)
- [Binary search](#binary-search)
- [Stack and Queue](#stack-and-queue)
    * [Stack](#stack)
    * [Queue](#queue)

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

# Grades

5/5 Assignments Passed
94.8% Final Grade

  1. Union-Find -                     Programming Assignment: Percolation -       95%
  2. Stacks and Queues -              Deques and Randomized Queues -              95%
  3. Mergesort -                      Programming Assignment: Collinear Points -  97%
  4. Priority Queues -                Programming Assignment: 8 Puzzle -          87 %
  5. Geometric Applications of BSTs - Programming Assignment: Kd-Trees -          100%

