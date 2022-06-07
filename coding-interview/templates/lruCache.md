# LRU Cache

## Summary

Design a data structure that has a set capacity and discards the least recently used items first.

The get and put methods must run in O(1) time complexity.

Methods:
- constructor with capacity parameter
- get(key): returns the value of the key, or -1 if key doesn't exist
- put(key, value): updates the value of the key if exists, else add the pair to the cache

## Example Problems

- LRU Cache

## Template

```java
public class LruCache {

    // Doubly Linked List Node.
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
    }

    // Contains key value pairs.
    private Map<Integer, DLinkedNode> cache = new HashMap<>();
    
    private int size;
    
    private int capacity;

    // Used for keeping track of how recently items have been used.
    private DLinkedNode head, tail;

    // Add new nodes right after head.
    private void addNode(DLinkedNode node) {

        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    // Remove node by making neighbors point to each other.
    private void removeNode(DLinkedNode node) {

        DLinkedNode prev = node.prev;
        DLinkedNode next = node.next;

        prev.next = next;
        next.prev = prev;
    }

    // Move a certain node to the head.
    private void moveToHead(DLinkedNode node) {
        
        removeNode(node);
        addNode(node);
    }

    // Pop the tail node.
    private DLinkedNode popTail() {

        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }

    public LruCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;

        head = new DLinkedNode();
        tail = new DLinkedNode();

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) return -1;

        moveToHead(node);

        return node.value;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);

        if(node == null) {
            DLinkedNode newNode = new DLinkedNode();
            newNode.key = key;
            newNode.value = value;

            cache.put(key, newNode);
            addNode(newNode);

            ++size;

            if(size > capacity) {
                DLinkedNode tail = popTail();
                cache.remove(tail.key);
                --size;
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }
}
```