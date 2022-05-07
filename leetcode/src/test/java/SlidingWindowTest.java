import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SlidingWindowTest {

    @Test
    void fruitsIntoBaskets() {
        assertEquals(0, SlidingWindow.FruitsIntoBaskets(new int[] {}));
    }
}