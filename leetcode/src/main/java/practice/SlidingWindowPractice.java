package practice;

import pattern.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

public class SlidingWindowPractice implements SlidingWindow {

    /**
     * Problem statement:
     * Find the smallest window whose sum is equal to or greater than the given target.
     */
    public int MinimumSizeSubarraySum(int target, int[] nums) {
        int minimumSize = Integer.MAX_VALUE;
        int currentSum = 0;

        int windowStart = 0;
        for (int windowEnd = 0; windowEnd < nums.length; ++windowEnd) {
            currentSum += nums[windowEnd];
            while (currentSum >= target) {
                minimumSize = Math.min(minimumSize, windowEnd - windowStart + 1);
                currentSum -= nums[windowStart++];
            }
        }

        return minimumSize == Integer.MAX_VALUE ? 0 : minimumSize;
    }

    /**
     * Problem statement:
     * Find the longest window in the given array with at most 2 distinct integers.
     *
     * @return The length of the appropriate window.
     */
    public int FruitsIntoBaskets(int[] fruits) {
        int longestWindow = 0;
        Map<Integer, Integer> counts = new HashMap<>();

        int windowStart = 0;
        for (int windowEnd = 0; windowEnd < fruits.length; ++windowEnd) {
            counts.put(fruits[windowEnd], counts.getOrDefault(fruits[windowEnd], 0) + 1);
            while (counts.size() > 2) {
                counts.put(fruits[windowStart], counts.get(fruits[windowStart]) - 1);
                if (counts.get(fruits[windowStart]) == 0) {
                    counts.remove(fruits[windowStart]);
                }
                windowStart++;
            }

            longestWindow = Math.max(longestWindow, windowEnd - windowStart + 1);
        }

        return longestWindow;
    }

    /**
     * Problem statement:
     * Determine if s2 contains any permutation of s1.
     */
    public boolean PermutationInString(String s1, String s2) {
        if (s1 == null || s1.length() == 0) return true;
        if (s1.length() > s2.length()) return false;

        Map<Character, Integer> charCounts = new HashMap<>();
        for (char c : s1.toCharArray()) {
            charCounts.put(c, charCounts.getOrDefault(c, 0) + 1);
        }

        int matched = 0;

        int windowStart = 0;
        for (int windowEnd = 0; windowEnd < s2.length(); windowEnd++) {
            char rightChar = s2.charAt(windowEnd);
            if (charCounts.containsKey(rightChar)) {
                charCounts.put(rightChar, charCounts.get(rightChar) - 1);
                if (charCounts.get(rightChar) == 0) {
                    matched++;
                }
            }

            if (matched == charCounts.size()) {
                return true;
            }

            if (windowEnd >= s1.length() - 1) {
                char leftChar = s2.charAt(windowStart++);
                if (charCounts.containsKey(leftChar)) {
                    if (charCounts.get(leftChar) == 0) {
                        matched--;
                    }
                    charCounts.put(leftChar, charCounts.get(leftChar) + 1);
                }
            }
        }

        return false;
    }
}
