import java.util.HashMap;
import java.util.Map;

public class SlidingWindow {

    /**
     * Find the smallest window whose sum is equal to or greater than the given target.
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
     * Find the longest window in the given array with at most 2 distinct integers.
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
}
