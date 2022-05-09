import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SlidingWindowTest {

    @Test
    void minimumSizeSubarraySum() {
        assertEquals(0, SlidingWindow.MinimumSizeSubarraySum(0, new int[]{}));
        assertEquals(2, SlidingWindow.MinimumSizeSubarraySum(7, new int[]{1, 2, 4, 3, 5}));
    }

    @Test
    void fruitsIntoBaskets() {
        assertEquals(0, SlidingWindow.FruitsIntoBaskets(new int[]{}));
        assertEquals(3, SlidingWindow.FruitsIntoBaskets(new int[]{1, 2, 1}));
        assertEquals(5, SlidingWindow.FruitsIntoBaskets(new int[]{1, 2, 2, 4, 2, 2, 7, 3}));
    }

    @Test
    void permutationInString() {
        assertEquals(true, SlidingWindow.PermutationInString("", ""));
        assertEquals(false, SlidingWindow.PermutationInString("abcd", "ab"));
        assertEquals(true, SlidingWindow.PermutationInString("abc", "jfieoabjducbijeapjbcajowk"));
    }
}