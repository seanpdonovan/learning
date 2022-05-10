import java.util.*;

public class ArraysAndStrings {

    /**
     * Determine if a string contains only unique characters.
     */
    public static boolean isUnique(String str) {
        Set<Character> chars = new HashSet<>();
        for (char c : str.toCharArray()) {
            if (chars.contains(c)) return false;
            chars.add(c);
        }

        return true;
    }

    /**
     * Determine if one string is a permutation of the other.
     */
    public static boolean checkPermutation(String s1, String s2) {
        if (s1 == null && s2 == null) return true;
        if (s1 == null || s2 == null) return false;
        if (s1.length() != s2.length()) return false;

        Map<Character, Integer> counts = new HashMap<>();
        for (char c : s1.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }

        for (char c : s2.toCharArray()) {
            if (!counts.containsKey(c)) return false;
            if (counts.get(c) == 0) return false;
            counts.put(c, counts.get(c) - 1);
        }

        return true;
    }

    /**
     * Given a character array (with enough padding provided at the end),
     * replace (in-place) the ' ' character with characters % 2 0.
     */
    public static String urlify(char[] str) {

        int end = str.length - 1;
        int i = str.length - 1;

        while (str[i] == ' ') {
            i--;
        }

        while (i >= 0) {
            if (str[i] != ' ') {
                str[end--] = str[i--];
            } else {
                str[end--] = '0';
                str[end--] = '2';
                str[end--] = '%';
                i--;
            }
            System.out.println(str.toString());
        }

        return new String(str);
    }

    /**
     * Determine if the given string is a permutation of a palindrome.
     */
    public static boolean palindromePermutation(String str) {

        boolean[] oddCounts = new boolean[26];
        Arrays.fill(oddCounts, false);
        for (char c : str.toCharArray()) {
            if (!Character.isAlphabetic(c)) continue;
            if (oddCounts[Character.toLowerCase(c) - 'a']) {
                oddCounts[Character.toLowerCase(c) - 'a'] = false;
            } else {
                oddCounts[Character.toLowerCase(c) - 'a'] = true;
            }
        }

        int oddCount = 0;
        for (int i = 0; i < 26; i++) {
            if (oddCounts[i]) {
                oddCount++;
            }
        }

        return oddCount < 2;
    }
}
