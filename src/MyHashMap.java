import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

public class MyHashMap<K, V> {
    private Node<K, V>[] table;
    private int size;
    private float threshold;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public MyHashMap() {
        table = new Node[DEFAULT_INITIAL_CAPACITY];
        threshold = table.length * DEFAULT_LOAD_FACTOR;
        size = 0;
    }

    public int size() {
        return size;
    }

    private Node<K, V> getNextNode(Node<K, V> current) {
        return current.getNext();
    }


    public void put(K key, V value) {
        if (key == null) {
            putForNullKey(value);
            return;
        }
        int hash = hash(key);
        int index = indexFor(hash, table.length);
        addNode(key, value, hash, index);
    }

    private void addNode(K key, V value, int hash, int index) {
        Node<K, V> newNode = new Node<>(hash, key, value, null);
        Node<K, V> current = table[index];
        if (current == null) {
            table[index] = newNode;
            size++;
            return;
        } else {
            do {
                if (current.hash == hash && (current.key == key || key.equals(current.key))) {
                    current.value = value;
                    return;
                }
                if (current.getNext() != null) {
                    current = current.getNext();
                } else {
                    break;
                }

            } while (current != null);
            current.setNext(newNode);
            size++;
        }
    }

    private void putForNullKey(V value) {
        addNode(null, value, 0, 0);
    }

    private static int indexFor(int h, int length) {
        return h & (length - 1);
    }

    private int hash(K key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public V remove(K key) {
        Node<K, V> removedNode = null;
        Node<K, V> node = null;
        for (int i = 0; i < table.length; i++) {

            if (table[i] != null) {
                if (table[i].key == key) {
                    removedNode = table[i];
                    if (table[i].getNext() == null) {
                        table[i] = null;

                    } else {
                        table[i] = table[i].getNext();
                    }
                    size--;
                    return removedNode.getValue();
                }
                node = table[i].getNext();
            }

            while (node != null) {
                if (node.key == key) {
                    removedNode = node;
                    if (node.getNext() == null) {
                        node = null;

                    } else {
                        node = node.getNext();
                    }
                    size--;
                    return removedNode.getValue();
                }
            }

        }
        return removedNode.getValue();
    }

    public V get(K key) {
        for (Node<K, V> node : table) {
            if (node != null) {
                if (node.key == key) {
                    return node.getValue();
                } else {
                    while (node != null) {
                        node = node.getNext();
                        if(node != null) {
                            if (node.key == key) {
                                return node.getValue();
                            }
                        }else{
                            break;
                        }
                    }
                }

            }
        }
        return null;
    }

    public void clear(){
        for (int i = 0; i < table.length; i++) {
            if(table[i] != null){
                Node<K,V> node = table[i].getNext();
                if(node != null){
                    while (node != null){
                        if(node.getNext() != null){
                            Node<K,V> next = node.getNext();
                            node.setNext(null);
                            node = next;
                        }else{
                            break;
                        }
                    }
                }
            }
            table[i] = null;
        }
        size = 0;
    }

    private static class Node<K, V> {
        int hash;
        K key;
        V value;
        Node<K, V> next;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public int getHash() {
            return hash;
        }

        public Node<K, V> getNext() {
            return next;
        }

        public int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public void setHash(int hash) {
            this.hash = hash;
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    @Override
    public String toString() {
        String begining = "MyHashMap{" +
                "size=" + size + ", nodes: [";
        StringJoiner joiner = new StringJoiner(", ", begining, "]}");
        for (Node<K, V> node : table) {
            if (node == null) {
                //joiner.add("null");
            } else {
                joiner.add(node.toString());
                while (node.getNext() != null) {
                    node = getNextNode(node);
                    joiner.add(node.toString());
                }
            }

        }

        return joiner.toString();
    }
}

class HashMapTester {
    public static void main(String[] args) {
        MyHashMap<Integer, String> map = new MyHashMap<>();
        System.out.println(map);
        map.put(1, "abc");
        System.out.println(map);
        map.put(1, "vmxcvn");
        System.out.println(map);
        map.put(2, "ksldjf");
        System.out.println(map);
        map.remove(1);
        System.out.println(map);
        for (int i = 0; i < 20; i++) {
            map.put(i, "abc" + i);
        }
        System.out.println(map);
        System.out.println(map.size());
        System.out.println("Testing get by key 1: " + map.get(1));
        System.out.println(map.size());
        System.out.println("Testing get by key 19: " + map.get(19));
        System.out.println(map.size());
        System.out.println("Testing get by key 25: " + map.get(25));
        System.out.println(map.size());
        System.out.println(map);
        System.out.println("Testing clearing");
        map.clear();
        System.out.println(map);
    }
}
