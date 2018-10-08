/*
 * Implementation of data structures and algorithms
 * Fall 2018
 * Short Project 3: Priority queues
 * Date: 23rd September,2018
 *
 * Team Members:
 * Shreyash Mane - ssm170730
 * Swapna Chintapalli - sxc180048
 *
 * */

// Starter code for bounded-size Binary Heap implementation
// Changed signature of heapSort changing <T> to <T extends Comparable<? super T>>

//package sxc180048;

import java.util.Comparator;
import java.util.Scanner;

public class BinaryHeap<T extends Comparable<? super T>> {
    T[] pq;
    Comparator<T> comp;
    int size;
    // Constructor for building an empty priority queue using natural ordering of T
    public BinaryHeap(T[] q) {

        // Use a lambda expression to create comparator from compareTo
        this(q, (T a, T b) -> a.compareTo(b));
    }

    // Constructor for building an empty priority queue with custom comparator
    public BinaryHeap(T[] q, Comparator<T> c) {
        pq = q;
        comp = c;
        this.size = 0;
    }

    /** Build a priority queue with a given array q, using q[0..n-1].
     *  It is not necessary that n == q.length.
     *  Extra space available can be used to add new elements.
     *  Implement this if solving optional problem.  To be called from heap sort.
     */
    private BinaryHeap(T[] q, Comparator<T> c, int n) {
        pq = q;
        comp = c;
        this.size = 0;
    }

    /*add(x): Adds element x to heap
     * 			     throws exception if heap is full */
    public void add(T x) {
        if(size == pq.length){
            throw new IndexOutOfBoundsException();
        }
        else{
            pq[size] = x;
            percolateUp(size);
            size++;
        }
    }

    /*offer(x) : returns true after adding element x to heap
     * 				    returns false if heap is full*/
    public boolean offer(T x) {

        if(size == pq.length){
            return false;
        }else{
            add(x);
            System.out.println("Size: "+size);
            return true;
        }
    }

    /*remove(): returns minimum element in the heap
     * 					  throws exception if queue is empty*/
    public T remove() {
        T min = pq[0];
        if (size != 0) {
            pq[0] = pq[size - 1];
            size--;
            percolateDown(0);

            return min;
        }else{
            throw new NullPointerException();
        }
    }

    /*poll(): returns and removes the minimum element in heap
     * 			   returns null if the queue is empty*/
    public T poll() {
        //if(pq.length == 0)
        if(pq[0] == null)
            return null;
        else
        {
            return remove();
        }
    }

    /*peek(): returns the minimum element in heap
     * 			   returns null if the queue is empty*/
    public T peek() {
        if(pq[0] == null)
            return null;
        else
            return pq[0];
    }

    /** pq[i] may violate heap order with parent */
    void percolateUp(int i) {
        T x = pq[i];
        while((i > 0) && pq[parent(i)].compareTo(x) == 1)
        {
            pq[i] = pq[parent(i)];
            i = parent(i);
        }
        pq[i] = x;

    }

    /** pq[i] may violate heap order with children */
    void percolateDown(int i) {
        T x = pq[i];
        int c = 2 * i + 1;
        while(c < (size - 1)){
            if((c < (size - 1)) && pq[c].compareTo(pq[c + 1]) == 1) {
                c = c + 1;
            }
            if(x.compareTo(pq[c]) == -1 || x.compareTo(pq[c]) == 0)
                break;
            pq[i] = pq[c];
            i = c;
            c = 2 * i + 1;
        }
        pq[i] = x;
    }

    // Assign x to pq[i].  Indexed heap will override this method
    void move(int i, T x) {
        pq[i] = x;
    }

    int parent(int i) {
        return (i-1)/2;
    }

    int leftChild(int i) {
        return 2*i + 1;
    }

    void printPriority(){
        for(int i = 0; i < pq.length; i++){
            System.out.print(" "+ pq[i] +" ");
        }

    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter size of Heap");
        int limit = in.nextInt();
        Integer[] q = new Integer[limit];
        BinaryHeap <Integer> priority = new BinaryHeap<>(q);

        while(in.hasNext()) {
            int com = in.nextInt();
            switch(com) {
                case 1:  // offer
                    int number = in.nextInt();
                    boolean status = priority.offer(number);
                    System.out.println("Queue offer success: "+status);
                    priority.printPriority();
                    break;
                case 2:  // poll
                    System.out.println("Poll: "+priority.poll());
                    priority.printPriority();
                    break;
                case 3: //peek
                    System.out.println("Peek: "+priority.peek());
                    break;
                case 4: //remove
                    System.out.println("Minimum: "+priority.remove());
                    priority.printPriority();
                    break;
                case 5: //add
                    number = in.nextInt();
                    priority.add(number);
                    priority.printPriority();
                    System.out.println();
                    break;
                default:  // Exit loop
                    break;
            }
        }
    }
// end of functions for team project
}