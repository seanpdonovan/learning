# 268. Missing Number

## Summary

We are given an array of size **n**, with distinct numbers in the range from 0 to **n**.

Return the number that is missing.

</br>

## Solutions

### HashSet

We can convert the input array into a HashSet and then iterate from 0 to **n**. If the current iterator value is not in the HashSet return the current number.

</br>
Time complexity: O(n)

Space complexity: O(n)

</br>

### Compare "complete" sum with "missing" sum

First, sum up all numbers from 0 to **n** as the "complete" sum.

Second, sum up all numbers in the input array as the "missing" sum.

Return the difference between the "complete" sum and "missing" sum.

Time complexity: O(n)
Space complexity: O(1)

</br>

### Bit Manipulation

Utilize XOR operator...

TODO

</br>
Time complexity: O(n)

Space complexity: O(1)