import java.util.HashMap;
import java.util.Map;

public class SlidingWindow {

    /**
     * Problem statement:
     * Find the smallest window whose sum is equal to or greater than the given target.
     *
     * Solution summary:
     * Using the sliding window approach, we will add numbers from the right to a running sum.
     * Once we have a running sum that is >= target we will update our return value if appropriate
     * and then shrink our window from the left until our running sum is smaller than the target again.
     *
     * @return The length of the appropriate window.
     */
    static int MinimumSizeSubarraySum(int target, int[] nums) {
        // Keep track of the smallest window with a sum >= target.
        int minimumSize = Integer.MAX_VALUE;
        // Keep track of the sum of the current window.
        int currentSum = 0;

        // Sliding window pattern.
        int windowStart = 0;
        for (int windowEnd = 0; windowEnd < nums.length; ++windowEnd) {
            // Add the new number to our current sum.
            currentSum += nums[windowEnd];
            // While the sum of our current window is >= target,
            // shrink the window from the left and subtract the values from the current sum.
            while (currentSum >= target) {
                minimumSize = Math.min(minimumSize, windowEnd - windowStart + 1);
                currentSum -= nums[windowStart++];
            }
        }

        // Return 0 if we never found an appropriate window.
        return minimumSize == Integer.MAX_VALUE ? 0 : minimumSize;
    }

    /**
     * Problem statement:
     * Find the longest window in the given array with at most 2 distinct integers.
     *
     * Solution summary:
     * Using the sliding window approach, we will add fruit frequencies to a Map.
     * If the number of keys in our Map is greater than two, then we need to shrink our window from the left
     * until the number of keys is <= 2 again.
     * While the number of keys is <= 2, and before we move on to the next iteration of the loop, we will
     * update our returned variable if appropriate.
     *
     * @return The length of the appropriate window.
     */
    static int FruitsIntoBaskets(int[] fruits) {
        // Keep track of the longest window in the array that contains 2 unique numbers.
        int longestWindow = 0;
        // Keep track of the number of fruits by type in the current window.
        Map<Integer, Integer> counts = new HashMap<>();

        // Sliding window pattern.
        int windowStart = 0;
        for (int windowEnd = 0; windowEnd < fruits.length; ++windowEnd) {
            // Add a new fruit to our map or increment the fruit's count by 1.
            counts.put(fruits[windowEnd], counts.getOrDefault(fruits[windowEnd], 0) + 1);
            // If the number of unique fruits is greater than 2, then we need to remove fruits from the
            // start of our window until the number of unique fruits is 2 again.
            while (counts.size() > 2) {
                counts.put(fruits[windowStart], counts.get(fruits[windowStart]) - 1);
                // Remove the map entry if there are no more of this type of fruit in our window.
                if (counts.get(fruits[windowStart]) == 0) {
                    counts.remove(fruits[windowStart]);
                }
                windowStart++;
            }

            // Set our returned variable if our current window is larger than the previous largest window.
            longestWindow = Math.max(longestWindow, windowEnd - windowStart + 1);
        }

        return longestWindow;
    }

    /**
     * Problem statement:
     * Determine if s2 contains any permutation of s1.
     *
     * Solution summary:
     * Using the sliding window approach, we will keep a running count of character frequencies in a Map and a running
     * total of how many character frequencies have been matched in the current window.
     * If we have matched all character frequencies, we have found a permutation and can return "true".
     * We don't need to worry about characters not in the "pattern" string since we will be keeping our window the same
     * size as the "pattern" string.
     * Once the window is longer than the "pattern" string, we need to shrink it from the left and update the running
     * character frequencies in our Map.
     */
    static boolean PermutationInString(String s1, String s2) {
        if (s1.isEmpty()) return true;
        if (s1.length() > s2.length()) return false;

        // Keep track of how many times each character appears in s1.
        Map<Character, Integer> charCounts = new HashMap<>();
        for (char c : s1.toCharArray()) {
            charCounts.put(c, charCounts.getOrDefault(c, 0) + 1);
        }

        // Keep track of how many letter frequencies have been matched in the current window.
        int matched = 0;

        // Sliding window pattern.
        int windowStart = 0;
        for (int windowEnd = 0; windowEnd < s2.length(); windowEnd++) {
            char rightChar = s2.charAt(windowEnd);
            // If our map has right char as a key, we will decrement our count for that key.
            // If we have met the frequency for that character, we increment our matched counter.
            if (charCounts.containsKey(rightChar)) {
                charCounts.put(rightChar, charCounts.get(rightChar) - 1);
                if (charCounts.get(rightChar) == 0) {
                    matched++;
                }
            }

            // If we have matched all of the character frequencies we have found a permutation.
            if (matched == charCounts.size()) {
                return true;
            }

            // Once windowEnd is past the length of s1, we need to move our windowStart to the right.
            // If we are incrementing a character frequency that we had previously matched, we need to decrement matched.
            // We increment our map's character frequencies as we shift our windowStart to the right of the characters.
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
