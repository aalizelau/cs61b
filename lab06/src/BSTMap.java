import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    public class BSTNode {
        public BSTNode left;
        public BSTNode right;
        public K key;
        public V value;

        public BSTNode(K inputKey,V inputValue){
            this.key = inputKey;
            this.value = inputValue;
            this.left = null;
            this.right = null;
        }
    }
    private BSTNode root;
    private int size;

    public BSTMap(){
        this.root= null;
        size =0;
    }

    // A negative value if key1 is less than key2
    // A positive value if key1 is greater than key2
    private int compareKey(K key1, K key2) {
        return key1.compareTo(key2);
    }

    @Override
    public void put(K key, V value) {
        if (root ==null){
            root = new BSTNode(key, value);
            size ++;
        }
        else{
            putHelper(root,key,value);
        }
    }

    public void putHelper(BSTNode currentNode, K key, V value){
        if (compareKey(key, currentNode.key)<0) {
            if (currentNode.left == null) {
                currentNode.left = new BSTNode(key, value);
                size++;
            } else {
                putHelper(currentNode.left, key, value);
            }
        }
        else if(compareKey(key, currentNode.key)>0) {
            if (currentNode.right == null) {
                currentNode.right = new BSTNode(key, value);
                size++;
            } else {
                putHelper(currentNode.right, key, value);
            }
        }
        else{
            currentNode.value = value;
        }
    }

    @Override
    public V get(K key) {
        return getHelper(root, key);
    }

    private V getHelper(BSTNode currentNode, K key) {
        if (!containsKey(key)){
            return null;
        }
        int cmp = compareKey(key, currentNode.key);
        if (cmp < 0) {
            return getHelper(currentNode.left, key);
        } else if (cmp > 0) {
            return getHelper(currentNode.right, key);
        } else {
            return currentNode.value; // Key found
        }
    }

    @Override
    public boolean containsKey(K key) {
        return containsKeyHelper(root, key);
    }
    private boolean containsKeyHelper(BSTNode currentNode, K key) {
        if (currentNode==null){
            return false;
        }
        int cmp = compareKey(key, currentNode.key);
        if (cmp < 0) {
            return containsKeyHelper(currentNode.left, key);
        } else if (cmp > 0) {
            return containsKeyHelper(currentNode.right, key);
        } else {
            return true; // Key found
        }
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root=null;
        size=0;
    }

    @Override
    public Set<K> keySet() {
        return Set.of();
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }
}
