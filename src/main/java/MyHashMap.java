import java.util.*;

public class MyHashMap<K, V> implements Map<K, V> {

    private class Element<K, V> implements Entry<K, V> {

        K key;
        V value;

        Element(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            return this.value = value;
        }

    }

    private int bucketSize = 1024;
    private LinkedList<Element<K, V>>[] table;
    private Set<K> keys;

    MyHashMap() {
        table = new LinkedList[bucketSize];
        keys = new HashSet<>();
    }

    public int myHash(Object key) {
        return key.hashCode() & (bucketSize - 1);
    }

    @Override
    public int size() {
        return keys.size();
    }

    @Override
    public boolean isEmpty() {
        return keys.size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return keys.contains(key);
    }

    @Override
    public boolean containsValue(Object value) {
        for (K key: keys)
            for (Element<K, V> elem: table[myHash(key)])
                if (elem.getValue().equals(value))
                    return true;
        return false;
    }

    @Override
    public V get(Object key) {
        if (!containsKey(key))
            return null;
        for (Element<K, V> elem: table[myHash(key)])
            if (elem.getKey().equals(key))
                return elem.getValue();
        return null;
    }

    @Override
    public V put(K key, V value) {
        if (containsKey(key))
            for (Element<K, V> elem: table[myHash(key)])
                if (elem.getKey().equals(key))
                    return elem.setValue(value);
        if (!keys.add(key))
            return null;
        if (table[myHash(key)] == null)
            table[myHash(key)] = new LinkedList<>();
        table[myHash(key)].push(new Element<>(key, value));
        return value;
    }

    @Override
    public V remove(Object key) {
        if (!containsKey(key))
            return null;
        if (keys.remove(key))
            for (Element<K, V> elem: table[myHash(key)])
                if (elem.getKey().equals(key)) {
                    V ret = elem.getValue();
                    table[myHash(key)].remove(elem);
                    return ret;
                }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        m.forEach(this::put);
    }

    @Override
    public void clear() {
        table = new LinkedList[bucketSize];
        keys = new HashSet<>();
    }

    @Override
    public Set<K> keySet() {
        return keys;
    }

    @Override
    public Collection<V> values() {
        Collection<V> ans = new HashSet<V>();
        for (K key: keys)
            for (Element<K, V> elem: table[myHash(key)])
                ans.add(elem.getValue());
        return ans;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> ans = new HashSet<>();
        for (K key: keys)
            for (Element<K, V> elem: table[myHash(key)])
                ans.add(elem);
        return ans;
    }

    public static void main(String[] args) {
        MyHashMap<String, String> myHashMap = new MyHashMap<>();

        myHashMap.put("hossein", "yousefi");
        myHashMap.put("abc", "cdf");
        myHashMap.put("abc", "abc");
        myHashMap.put("cdf", "111");
        myHashMap.put("qrs", "222");
        myHashMap.put("hello", "world");
        myHashMap.remove("hello");
        myHashMap.remove("something!");

        System.out.println(myHashMap.get("hossein"));
        System.out.println(myHashMap.get("something else!"));

        System.out.println("\n{ ");
        for (Entry<String, String> e: myHashMap.entrySet())
            System.out.println(e.getKey() + ": " + e.getValue());
        System.out.println("}");

        myHashMap.clear();

        System.out.println(myHashMap.size());
    }
}
