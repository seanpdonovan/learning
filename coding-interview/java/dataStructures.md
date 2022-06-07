# Java Data Structures

## ArrayList

Time complexity:
- access O(1)
- search O(n)
- insert at end O(1)
- insert at front O(n)
- remove O(n)

```java
// Import
import java.util.ArrayList;

// Creation
ArrayList<Integer> list = new ArrayList<>();
List<Integer> list = new ArrayList<>();

// Add
list.add(1);

// Update
list.set(0, 100);

// Remove
list.remove(0);
list.clear();

// Size
list.size();

// Access
for (int i = 0; i < list.size(); i++)
for (String s : list)

// Sorting
import java.util.Collections;
Collections.sort(list);
Collections.sort(list, Collections.reverseOrder());
```

## Heap

Time complexity:
- access max or min O(1)
- insert O(log(n))
- remove max or min O(log(n))

```java
// Import
import java.util.PriorityQueue;

// Creation
// Min heap
PriorityQueue<Integer> pq = new PriorityQueue<>();
// Max heap
PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder);
// Max heap of pairs. If pair values are the same, then sort a-z by key.
PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
    (a, b) -> a.getValue().equals(b.getValue()) ?
        a.getKey().compareTo(b.getKey()) :
        a.getValue() - b.getValue());

// Add
pq.add(10);

// View top
pq.peek();

// Remove and return top
pq.poll();

// Size
pq.size()
```

## HashMap

Time complexity:
- access O(1)
- search O(n)
- insert O(1)
- remove O(1)

```java
// Import
import java.util.HashMap;

// Creation
HashMap<String, String> hm = new HashMap<>();
Map<String, String> hm = new HashMap<>();

// Add
hm.put("foo", "ok");

// Update
hm.put("foo", hm.getOrDefault("foo", "run"));

// Remove
hm.remove("foo");

// Size
hm.size();

// Accessing
for (Map.Entry<String, String> entry : hm.entrySet()) {
    println(entry.getKey() + " " + entry.getValue());
}
for (String key : hm.keySet())
for (String value : hm.values())
```