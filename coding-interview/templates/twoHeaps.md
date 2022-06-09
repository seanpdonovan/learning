# Two Heaps

## Example Problems

- Find the median of a number stream
- Sliding window median

## Find the median of a number stream

```java
class MedianFinder {

    // Keep track of smaller numbers.
    PriorityQueue<Integer> maxHeap;
    // Keep track of larger numbers.
    PriorityQueue<Integer> minHeap;
    
    public MedianFinder() { 
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
        minHeap = new PriorityQueue<>((a, b) -> a - b);
    }
    
    public void addNum(int num) {
        // If maxHeap is empty or new num is smaller than top of maxHeap.
        if (maxHeap.isEmpty() || maxHeap.peek() >= num)
            maxHeap.add(num);
        else
            minHeap.add(num);
        
        // Balance the heaps.
        // The size of maxHeap should be same as minHeap or 1 greater.
        if (maxHeap.size() > minHeap.size() + 1)
            minHeap.add(maxHeap.poll());
        else if (maxHeap.size() < minHeap.size())
            maxHeap.add(minHeap.poll());
    }
    
    public double findMedian() {
        // Return average of the two if the heap sizes are equal.
        if (maxHeap.size() == minHeap.size())
            return maxHeap.peek() / 2.0 + minHeap.peek() / 2.0;
        
        // Min heap will never have more than maxHeap.
        return maxHeap.peek();
    }
}
```

## Sliding Window Median

```java
class SlidingWindowMedian {

    // Keep track of smaller numbers.
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    // Keep track of larger numbers.
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    public double[] findSlidingWindowMedian(int[] nums, int k) {

        // Ex. array of 5 and k of 3 has output length of 2.
        double[] result = new double[nums.length - k + 1];

        for (int i = 0; i < nums.length; i++) {
            // If maxHeap is empty or num is smaller than top of maxHeap.
            if (maxHeap.size() == 0 || maxHeap.peek() >= nums[i]) {
                maxHeap.add(nums[i]);
            } else {
                minHeap.add(nums[i]);
            }
            rebalanceHeaps();

            // When we have k elements in our sliding window.
            if (i - k + 1 >= 0) {
                // Add median to result.
                if (maxHeap.size() == minHeap.size()) {
                    // Get average if count is even.
                    result[i - k + 1] = maxHeap.peek() / 2.0 + minHeap.peek() / 2.0;
                } else {
                    // maxHeap will never have fewer items than minHeap.
                    result[i - k + 1] = maxHeap.peek();
                }

                // Remove the element going out of the sliding window.
                int elementToBeRemoved = nums[i - k + 1];
                if (elementToBeRemoved <= maxHeap.peek()) {
                    maxHeap.remove(elementToBeRemoved);
                } else {
                    minHeap.remove(elementToBeRemoved);
                }
                rebalanceHeaps();
            }
        }
        return result;
    }

    private void rebalanceHeaps() {
        // Size of maxHeap should be same as minHeap or 1 greater.
        if (maxHeap.size() > minHeap.size() + 1)
            minHeap.add(maxHeap.poll());
        else if (maxHeap.size() < minHeap.size())
            maxHeap.add(minHeap.poll());
    }
}
```