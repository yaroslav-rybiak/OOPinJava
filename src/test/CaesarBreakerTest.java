import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CaesarBreakerTest {

    private CaesarBreaker cb = new CaesarBreaker();

    @Test
    public void testCountLetters() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        int[] counters = cb.countLetters("Hello World");
        assertEquals(1, counters[4]);
        assertEquals(1, counters[7]);
        assertEquals(3, counters[11]);
        assertEquals(2, counters[14]);
    }

    @Test
    public void testMaxIndex() {
        assertEquals(11, cb.maxIndex("Hello"));
        assertEquals(0, cb.maxIndex("aabbbaaa"));
    }

    @Test
    public void testDecrypt() {
        CaesarBreaker cb = new CaesarBreaker();
        assertEquals("ABCDEEEEEE", cb.decrypt("BCDEFFFFFF"));
        assertEquals("ABCDEEEEEE", cb.decrypt("ZABCDDDDDD"));
    }

    @Test
    public void testHalfOfString() {
        assertEquals("Qk gs", cb.halfOfString("Qbkm Zgis", 0));
        assertEquals("bmZi", cb.halfOfString("Qbkm Zgis", 1));
    }

    @Test
    public void testGetKey() {
        assertEquals(4, cb.getKey("Heeeeeello"));
        assertEquals(4, cb.getKey("eebbbeeeee"));
    }

    @Test
    public void testDecryptTwoKeys() {
        assertEquals("Just a test string with lots of eeeeeeeeeeeeeeeees", cb.decryptTwoKeys("Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu"));
        assertEquals("Eren and Emily have evil eerie green ears", cb.decryptTwoKeys("Akag tjw Xibhr awoa aoee xakex znxag xwko"));
    }
}
