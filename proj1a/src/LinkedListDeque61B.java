import java.util.Deque;
import java.util.List;
import java.util.ArrayList;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    //instance variable
    public Node sentinel;
    public int size;

    //nested Node class
    public class Node{
        public Node prev;
        public T item;
        public Node next;

        public Node(Node prev, T item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    //constructor and empty list
    public LinkedListDeque61B(){
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
        }

    @Override
    public void addFirst(T x) {
        Node newNode = new Node(sentinel, x, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size ++;
    }

    @Override
    public void addLast(T x) {
        Node newNode = new Node(sentinel.prev, x, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size ++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node p = sentinel;
        for (int i = 0; i < size; i++) {
            returnList.add(p.next.item);
            p = p.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (this.isEmpty()){
            return null;
        }
        Node firstNode = sentinel.next;
        T deletedItem = firstNode.item;

        sentinel.next = firstNode.next;
        sentinel.next.prev = sentinel;

        firstNode.next = null;
        firstNode.prev = null;
        firstNode.item = null;

        size--;
        return deletedItem;
    }

    @Override
    public T removeLast() {
        if (this.isEmpty()){
            return null;
        }
        Node lastNode = sentinel.prev;
        T deletedItem = lastNode.item;

        sentinel.prev = lastNode.prev;
        sentinel.prev.next = sentinel;

        lastNode.prev = null;
        lastNode.item = null;
        lastNode.next = null;

        size--;
        return deletedItem;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        Node p = sentinel.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }

    @Override
    public T getRecursive(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(Node current, int index){
        if (index ==0){
            return current.item;
        }
        return getRecursiveHelper(current.next,index -1);
    }
}
