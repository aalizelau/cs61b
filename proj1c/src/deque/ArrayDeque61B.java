package deque;

import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.lang.Math;
import java.util.ArrayList;

public class ArrayDeque61B<T> implements Deque61B<T>{
    public T[] items;
    public int size;
    public int nextFirst;
    public int nextLast;

    public ArrayDeque61B() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    public void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = get(i); // Using the get method to retrieve elements
        }
        items = newArray;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    @Override
    public void addFirst(T x) {
        if (size == items.length){
            resize(items.length *2);
        }
        items[nextFirst]=x;
        nextFirst = Math.floorMod(nextFirst-1,items.length);
        size++;
    }

    @Override
    public void addLast(T x) {
        if (size == items.length){
            resize(items.length *2);
        }
        items[nextLast]=x;
        nextLast = Math.floorMod(nextLast+1,items.length);
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        int index = Math.floorMod(nextFirst+1,items.length);
        for (int i = 0; i < size; i++) {
            returnList.add(items[index]);
            index = Math.floorMod(index+1,items.length);
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

    public int internalCapacity() {
        return items.length;
    }

    @Override
    public T removeFirst() {
        if (this.isEmpty()){
            return null;
        }
        if (items.length >15 && items.length * 0.25 > size){
            resize(items.length/2);
        }
        int firstindex = Math.floorMod(nextFirst+1,items.length);
        T deletedItem = items[firstindex];

        items[firstindex] = null;
        nextFirst = firstindex;
        size--;
        return deletedItem;
    }

    @Override
    public T removeLast() {
        if (this.isEmpty()){
            return null;
        }
        if (items.length >15 && items.length * 0.25 > size){
            resize(items.length/2);
        }
        int lastIndex = Math.floorMod(nextLast-1,items.length);
        T deletedItem = items[lastIndex];

        items[lastIndex] = null;
        nextLast = lastIndex;
        size--;
        return deletedItem;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        int actualIndex = Math.floorMod(nextFirst + 1 + index, items.length);
        return items[actualIndex];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}

