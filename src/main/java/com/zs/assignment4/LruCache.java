package com.zs.assignment4;

import java.util.HashMap;
import java.util.Map;

public class LruCache<K, V> {
    private final int capacity;
    private final Map<K, Node<K, V>> cache;
    private Node<K, V> head;
    private Node<K, V> tail;

    public LruCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = new Node<>(null, null);
        this.tail = new Node<>(null, null);
        head.next = tail;
        tail.prev = head;
    }

    private void addToFront(Node<K, V> node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private void remove(Node<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public V get(K key) {
        if (cache.containsKey(key)) {
            Node<K, V> temp = cache.get(key);
            remove(temp);
            addToFront(temp);
            return temp.value;
        }
        return null;
    }

    public void remove(K key) {
        Node<K, V> node = cache.get(key);
        if (node != null) {
            remove(node);
            cache.remove(key);
        }
    }

    public void put(K key, V value) {
        if (cache.containsKey(key)) {
            Node<K, V> temp = cache.get(key);
            remove(temp);
            cache.remove(temp.key);
        } else if (cache.size() == capacity) {
            Node<K, V> lruNode = tail.prev;
            remove(lruNode);
            cache.remove(lruNode.key);
        }
        Node<K, V> newNode = new Node<>(key, value);
        addToFront(newNode);
        cache.put(key, newNode);
    }

    public void printCache() {
        if (cache.isEmpty()) {
            System.out.println("Cache is Empty");
            return;
        }
        for (K key : cache.keySet()) {
            System.out.println("Key: " + key + " -> Value: " + cache.get(key).value);
        }
    }
}
