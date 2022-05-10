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
}