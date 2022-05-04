# 217. Contains Duplicate

## Summary

Return true if input array contains a duplicate number, otherwise return false if every number is distinct.

</br>

## Brute Force

Have two nested for-loops and compare each number against every number after it.

</br>
Time complexity: O(n^2)

Space complexity: O(1)

</br>

## How can we optimize brute force?

Each time we see a number we can store it in a HashSet.

If the HashSet already contains the number, we can return true.

If we iterate through the entire list without returning true, we will return false at the end.

</br>
Time complexity: O(n)

Space complexity: O(n)