package algorythms;

import org.junit.Test;

import static algorythms.SearchImplementation.binarySearch;
import static algorythms.SearchImplementation.linearSearch;
import static org.junit.Assert.assertEquals;

public class SearchImplementationTest {

    @Test
    public void testBinarySearch() {
        String[] dicti1 = new String[7];
        dicti1[0] = "aaa";
        dicti1[1] = "bbb";
        dicti1[2] = "ccc";
        dicti1[3] = "ddd";
        dicti1[4] = "eee";
        dicti1[5] = "fff";
        dicti1[6] = "ggg";
        String[] dicti2 = null;
        String[] dicti3 = new String[0];
        String[] dicti4 = new String[2];
        dicti4[0] = "aaa";

        assertEquals(-1, binarySearch(dicti1, "hhh"));
        assertEquals(-1, binarySearch(dicti1, ""));
        assertEquals(-1, binarySearch(dicti1, "0"));
        assertEquals(-1, binarySearch(dicti1, "a"));

        assertEquals(-2, binarySearch(dicti1, null));
        assertEquals(-3, binarySearch(dicti2, "aaa"));
        assertEquals(-4, binarySearch(dicti3, "aaa"));
        assertEquals(-5, binarySearch(dicti4, "aaa"));

        assertEquals(0, binarySearch(dicti1, "aaa"));
        assertEquals(1, binarySearch(dicti1, "bbb"));
        assertEquals(2, binarySearch(dicti1, "ccc"));
        assertEquals(3, binarySearch(dicti1, "ddd"));
        assertEquals(4, binarySearch(dicti1, "eee"));
        assertEquals(5, binarySearch(dicti1, "fff"));
        assertEquals(6, binarySearch(dicti1, "ggg"));
    }

    @Test
    public void testLinearSearch() {
        String[] dicti1 = new String[7];
        dicti1[0] = "aaa";
        dicti1[1] = "bbb";
        dicti1[2] = "ccc";
        dicti1[3] = "ddd";
        dicti1[4] = "eee";
        dicti1[5] = "fff";
        dicti1[6] = "ggg";

        assertEquals(-1, linearSearch(dicti1, "hhh"));
        assertEquals(-1, linearSearch(dicti1, ""));
        assertEquals(-1, linearSearch(dicti1, "0"));
        assertEquals(-1, linearSearch(dicti1, "a"));

        assertEquals(0, linearSearch(dicti1, "aaa"));
        assertEquals(1, linearSearch(dicti1, "bbb"));
        assertEquals(2, linearSearch(dicti1, "ccc"));
        assertEquals(3, linearSearch(dicti1, "ddd"));
        assertEquals(4, linearSearch(dicti1, "eee"));
        assertEquals(5, linearSearch(dicti1, "fff"));
        assertEquals(6, linearSearch(dicti1, "ggg"));
    }
}
