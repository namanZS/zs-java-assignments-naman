package com.zs.assignment4;
import java.util.*;
class LruCache<K,V> {
    class Node{
        K key;
        V val;
        Node next;
        Node prev;
        Node(K key, V val){
            this.key = key;
            this.val = val;
            this.next = null;
            this.prev = null;
        }
    }
    private int capacity;
    private Map<K,Node> map;
    private Node head;
    private Node tail;
    public LruCache(int capacity) {
        this.capacity = capacity;
        head = new Node(null,null);
        tail = new Node(null,null);
        head.next = tail;
        tail.prev = head;
        this.map = new HashMap<>();
    }
    private void addToFront(Node node){
        head.next.prev = node;
        node.next = head.next;
        head.next = node;
        node.prev = head;
    }
    private void remove(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;

    }
    public V get(K key) {
        if(map.containsKey(key)){
            Node temp = map.get(key);
            remove(temp);
            addToFront(temp);
            return temp.val;
        }
        return null;
    }
    public void remove(K key) {
        Node node = map.get(key);
        if (node != null) {
            remove(node);
            map.remove(key);
        }
    }

    public void put(K key, V value) {
        if(map.containsKey(key)){
            Node temp = map.get(key);
            remove(temp);
            map.remove(temp.key);
        }
        else if(map.size() == capacity){
            Node lruNode = tail.prev;
            remove(lruNode);
            map.remove(lruNode.key);
        }
        Node newNode = new Node(key, value);
        addToFront(newNode);
        map.put(key,newNode);
    }
    public void printCache(){
        if (map.isEmpty()){
            System.out.println("Cache is Empty");
            return;
        }
        for (K key:map.keySet()){
            System.out.println("Key :"+key+"-> Value : "+map.get(key).val);
        }
    }
}