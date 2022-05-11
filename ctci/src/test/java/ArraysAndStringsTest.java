import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArraysAndStringsTest {

    private static ArraysAndStrings arraysAndStrings;

    @BeforeAll
    static void setup() {
        arraysAndStrings = new ArraysAndStrings();
    }

    @ParameterizedTest
    @CsvSource({
            "abcd,true",
            "aabc, false"
    })
    void isUnique(String str, boolean expected) {
        assertEquals(expected, arraysAndStrings.isUnique(str));
    }

    @ParameterizedTest
    @CsvSource({
            ",,true",
            ",a,false",
            "abc,acb,true",
            "abc,abd,false"
    })
    void checkPermutation(String s1, String s2, boolean expected) {
        assertEquals(expected, arraysAndStrings.checkPermutation(s1, s2));
    }

    @Test
    void urlify() {
        assertEquals("Mr%20John%20Smith", arraysAndStrings.urlify("Mr John Smith    ".toCharArray()));
    }

    @ParameterizedTest
    @CsvSource({
            "Tact coa,true",
            "Tact boa,false"
    })
    void palindromePermutation(String str, boolean expected) {
        assertEquals(expected, arraysAndStrings.palindromePermutation(str));
    }

    @ParameterizedTest
    @CsvSource({
            "pale,ple,true",
            "pales,pale,true",
            "pale,bale,true",
            "pale,bake,false"
    })
    void oneAway(String s1, String s2, boolean expected) {
        assertEquals(expected, arraysAndStrings.oneAway(s1, s2));
    }

    @ParameterizedTest
    @CsvSource({
            "aabcccccaaa,a2b1c5a3",
            "abcd,abcd"
    })
    void stringCompression(String str, String expected) {
        assertEquals(expected, arraysAndStrings.stringCompression(str));
    }

    @Test
    void rotateMatrix() {
        char[][] matrix = new char[][] {
                "ab".toCharArray(),
                "cd".toCharArray()
        };

        char[][] expected = new char[][] {
                "ca".toCharArray(),
                "db".toCharArray()
        };

        char[][] actual = arraysAndStrings.rotateMatrix(matrix);

        validateMatrixes(expected, actual);

        matrix = new char[][] {
                "abc".toCharArray(),
                "def".toCharArray(),
                "ghi".toCharArray()
        };

        expected = new char[][] {
                "gda".toCharArray(),
                "heb".toCharArray(),
                "ifc".toCharArray()
        };

        actual = arraysAndStrings.rotateMatrix(matrix);

        validateMatrixes(expected, actual);

        matrix = new char[][] {
                "abcd".toCharArray(),
                "efgh".toCharArray(),
                "ijkl".toCharArray(),
                "mnop".toCharArray()
        };

        expected = new char[][] {
                "miea".toCharArray(),
                "njfb".toCharArray(),
                "okgc".toCharArray(),
                "plhd".toCharArray()
        };

        actual = arraysAndStrings.rotateMatrix(matrix);

        validateMatrixes(expected, actual);
    }

    void validateMatrixes(char[][] expected, char[][] actual) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(expected[i][j], actual[i][j]);
            }
        }
    }
}