package deque;
import java.util.Comparator;


public class MaxArrayDeque61B <T> extends ArrayDeque61B<T>{
    private Comparator<T> comparator;
    private T max;

    public MaxArrayDeque61B(Comparator<T> c){
        super();
        this.comparator = c;
        this.max = null;
    }

    @Override
    public void addFirst(T x) {
        super.addFirst(x);
        if (max == null || comparator.compare(x, max) > 0) {
            max = x;
        }
    }

    @Override
    public void addLast(T x) {
        super.addLast(x);
        if (max == null || comparator.compare(x, max) > 0) {
            max = x;
        }
    }

    @Override
    public T removeFirst() {
        T removed = super.removeFirst();
        if (removed != null && removed.equals(max)) {
            updateMax();
        }
        return removed;
    }

    @Override
    public T removeLast() {
        T removed = super.removeLast();
        if (removed != null && removed.equals(max)) {
            updateMax();
        }
        return removed;
    }

    private void updateMax() {
        max = null;
        for (T item : this) {
            if (max == null || comparator.compare(item, max) > 0) {
                max = item;
            }
        }
    }

    public T max(){
        return max;
    }

    public T max(Comparator<T> c){
        if (c == null) {
            return max();
        }
        T currentMax = null;
        for (T item : this) {
            if (currentMax == null || c.compare(item, currentMax) > 0) {
                currentMax = item;
            }
        }
        return currentMax;
    }
}
