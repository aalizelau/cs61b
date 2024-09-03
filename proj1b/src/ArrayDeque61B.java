import java.util.Deque;
import java.util.List;
import java.lang.Math;

public class ArrayDeque61B<T> implements Deque61B<T>{
    public T[] items;
    public int size;
    public int nextFirst;
    public int nextLast;

    public ArrayDeque61B() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 4;
    }

    @Override
    public void addFirst(T x) {
        items[nextFirst]=x;
        nextFirst = Math.floorMod(nextFirst-1,items.length);
        size++;
    }

    @Override
    public void addLast(T x) {
        items[nextLast]=x;
        nextLast = Math.floorMod(nextLast+1,items.length);
        size++;
    }

    @Override
    public List<T> toList() {
        return List.of();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
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
        return null;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
