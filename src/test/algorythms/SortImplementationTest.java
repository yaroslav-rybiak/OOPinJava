package algorythms;

import org.junit.Test;

import static algorythms.SortImplementation.collectionSort;
import static org.junit.Assert.assertEquals;

public class SortImplementationTest {

    @Test
    public void testCollectionSort() {
        int[] assorted = new int[5];
        assorted[0] = 8;
        assorted[1] = 1;
        assorted[2] = 29;
        assorted[3] = 22;
        assorted[4] = 15;

        int[] sorted = collectionSort(assorted);

        assertEquals(1, sorted[0]);
        assertEquals(8, sorted[1]);
        assertEquals(15, sorted[2]);
        assertEquals(22, sorted[3]);
        assertEquals(29, sorted[4]);

    }
}
