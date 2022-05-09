import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import practice.SlidingWindowPractice;
import solution.SlidingWindowSolution;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SlidingWindowTest {

    private static SlidingWindowPractice practice;
    private static SlidingWindowSolution solution;

    @BeforeAll
    static void setup() {
        practice = new SlidingWindowPractice();
        solution = new SlidingWindowSolution();
    }

    @ParameterizedTest
    @MethodSource
    void minimumSizeSubarraySum(int[] arr, int target) {
        assertEquals(solution.MinimumSizeSubarraySum(target, arr), practice.MinimumSizeSubarraySum(target, arr));
    }

    @ParameterizedTest
    @MethodSource
    void fruitsIntoBaskets(int[] arr) {
        assertEquals(solution.FruitsIntoBaskets(arr), practice.FruitsIntoBaskets(arr));
    }

    @ParameterizedTest
    @CsvSource({
            ",",
            "abcd,ab",
            "abc,jfieoabjducbijeapjbcajowk"
    })
    void permutationInString(String s1, String s2) {
        assertEquals(solution.PermutationInString(s1, s2), practice.PermutationInString(s1, s2));
    }

    private static Stream<Arguments> minimumSizeSubarraySum() {
        return Stream.of(
                Arguments.of(new int[]{}, 1),
                Arguments.of(new int[]{1, 2, 4, 3, 5}, 7));
    }

    private static Stream<Arguments> fruitsIntoBaskets() {
        return Stream.of(
                Arguments.of(new int[]{}),
                Arguments.of(new int[]{1, 2, 1}),
                Arguments.of(new int[]{1, 2, 2, 4, 2, 2, 7, 3}));
    }
}