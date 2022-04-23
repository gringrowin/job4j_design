package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (capacity * LOAD_FACTOR < count) {
            expand();
        }
        MapEntry<K, V> element = new MapEntry<>(key, value);
        int index = indexFor(hash(key.hashCode()));
        boolean rsl = table[index] == null;
        if (rsl) {
            table[index] = element;
            count++;
            modCount++;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode == 0 ? 0 : hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash % (table.length - 1);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] oldTable = table;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> el : oldTable) {
            if (el != null) {
                table[indexFor(hash(el.key.hashCode()))] = el;
            }
        }
    }

    @Override
    public V get(K key) {
        int index = indexFor(hash(key.hashCode()));
        return table[index] != null && table[index].key.equals(key)
                ? table[index].value : null;
    }

    @Override
    public boolean remove(K key) {
        int indexRemove = indexFor(hash(key.hashCode()));
        boolean rsl = table[indexRemove] != null && table[indexRemove].key.equals(key);
        if (rsl) {
            table[indexRemove] = null;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            int point = 0;
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (point < table.length && table[point] == null) {
                    point++;
                }
                return point <= count;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[point++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }
}
