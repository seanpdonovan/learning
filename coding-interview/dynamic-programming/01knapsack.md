# 0/1 Knapsack Pattern

## Example Problems

- 0/1 Knapsack

## 0/1 Knapsack

### Problem Statement

Given two integer arrays to represent weights and profits of ‘N’ items, we need to find a subset of these items which will give us maximum profit such that their cumulative weight is not more than a given number ‘C’. Write a function that returns the maximum profit. Each item can only be selected once, which means either we put an item in the knapsack or skip it.

### Brute Force

Try all combinations of given items and choose one with max profit and weight not exceeding capacity.

Time complexity: O(2^n)

Space complexity: O(n)

```java
class Knapsack {

    public int solveKnapsack(int[] profits, int[] weights, int capacity) {
        return knapsackRecursive(profits, weights, capacity, 0);
    }

    private int knapsackRecursive(int[] profits, int[] weights, int capacity, int currentIndex) {
        if (capacity <= 0 || currentIndex >= profits.length)
            return 0;
        
        // Find potential profit after including current item.
        // Skip if current item's weight would be over capacity.
        int profit1 = 0;
        if (weights[currentIndex] <= capacity) {
            profit1 = profits[currentIndex] + knapsackRecursive(profits, weights, capacity - weights[currentIndex], currentIndex + 1);
        }

        // Find potential profit after excluding current item.
        int profit2 = knapsackRecursive(profits, weights, capacity, currentIndex + 1);

        return Math.max(profit1, profit2);
    }
}
```

### Top Down with Memoization

Store results of previous calculations.

Time complexity: O(N*C) number of items times capacity.

Space complexity: O(N*C) space used in memoization array.

```java
class Knapsack {

    public int solveKnapsack(int[] profits, int[] weights, int capacity) {
        // Memoize results for every index and capacity.
        Integer[][] dp = new Integer[profits.length][capacity + 1];
        return knapsackRecursive(profits, weights, capacity, 0);
    }

    private int knapsackRecursive(int[] profits, int[] weights, int capacity, int currentIndex) {
        if (capacity <= 0 || currentIndex >= profits.length)
            return 0;
        
        // Return memoized result if exists.
        if (dp[currentIndex][capacity] != null)
            return dp[currentIndex][capacity];

        // Find potential profit after including current item.
        // Skip if current item's weight would be over capacity.
        int profit1 = 0;
        if (weights[currentIndex] <= capacity) {
            profit1 = profits[currentIndex] + knapsackRecursive(profits, weights, capacity - weights[currentIndex], currentIndex + 1);
        }

        // Find potential profit after excluding current item.
        int profit2 = knapsackRecursive(profits, weights, capacity, currentIndex + 1);

        dp[currentIndex][capacity] = Math.max(profit1, profit2);
        return dp[currentIndex][capacity];
    }
}
```

### Bottom Up

Populate dp[][] from bottom up.
Find the max profit for every subarray and capacity.
dp[i][c] represents max profit for capacity calculated from the first i items.
For each item at index i and capacity c:
1. exclude the item at i and take profit from dp[i-1][c]
2. include the item at i if its weight is not exceeding capacity. profits[i] + dp[i-1][c-weights[i]]
Solution will be dp[i][c] = max(dp[i-1][c], profits[i] + dp[i-1][c-weights[i]])

```java
class Knapsack {
    
    public int solveKnapsack(int[] profits, int[] weights, int capacity) {
        // Basic checks.
        if (capacity <= 0 || profits.length == 0 || weights.length != profits.length)
            return 0;

        int n = profits.length;
        
        // Row: the current index used when accessing profits and weights.
        // Col: the current capacity from 0 to input capacity.
        // Value: the max profit for the given index and capacity.
        int[][] dp = new int[n][capacity + 1];

        // Populate first column with 0, since there is no capacity.
        for(int i = 0; i < n; i++)
            dp[i][0] = 0;

        // Populate first row with first profit, if weight of item is not exceeding capacity.
        for(int c = 0; c <= capacity; c++) {
            if(weights[0] <= c)
                dp[0][c] = profits[0];
        }

        // Process all subarrays and capacities.
        for(int i = 1; i < n; i++) {
            for(int c = 1; c <= capacity; c++) {
                int profit1= 0;
                int profit2 = 0;
                
                // Get potential profit if including item.
                // Skip if item weight exceeds capacity.
                if(weights[i] <= c)
                    profit1 = profits[i] + dp[i - 1][c - weights[i]];
                
                // Get potential profit if excluding the item.
                profit2 = dp[i - 1][c];
                
                // Set cell to the greater potential profit.
                dp[i][c] = Math.max(profit1, profit2);
            }
        }

        // Max profit will be in the bottom-right corner.
        return dp[n - 1][capacity];
    }
}
```