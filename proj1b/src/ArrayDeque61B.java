import java.util.Deque;
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

    public void resize (int capacity){
        T[] newArray= (T[]) new Object[capacity];
        int oldIndex = nextFirst;
        for (int i=0; i<size; i++){
            oldIndex = (oldIndex + 1) % items.length;
            newArray[i] = items[oldIndex];
        }
        items = newArray;
        nextFirst=capacity -1;
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

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return items[index];
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
