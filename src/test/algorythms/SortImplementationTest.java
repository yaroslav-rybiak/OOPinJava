package algorythms;

import org.junit.Test;

import static algorythms.SortImplementation.collectionSort;
import static algorythms.SortImplementation.mergeSort;
import static org.junit.Assert.assertEquals;

public class SortImplementationTest {

    @Test
    public void testCollectionSort() {
        int[] assorted1 = new int[5];
        assorted1[0] = 8;
        assorted1[1] = 1;
        assorted1[2] = 29;
        assorted1[3] = 22;
        assorted1[4] = 15;

        int[] sorted1 = collectionSort(assorted1);

        assertEquals(1, sorted1[0]);
        assertEquals(8, sorted1[1]);
        assertEquals(15, sorted1[2]);
        assertEquals(22, sorted1[3]);
        assertEquals(29, sorted1[4]);

        int[] assorted2 = new int[2];
        assorted2[0] = 8;
        assorted2[1] = 1;

        int[] sorted2 = collectionSort(assorted2);

        assertEquals(1, sorted2[0]);
        assertEquals(8, sorted2[1]);

        int[] assorted3 = new int[1];
        assorted3[0] = 1;

        int[] sorted3 = collectionSort(assorted3);

        assertEquals(1, sorted3[0]);
    }

    @Test
    public void testMergeSort() {
        int[] arr1 = {8, 1, 29, 22, 15};
        mergeSort(arr1, 0, 4);
        assertEquals(1, arr1[0]);
        assertEquals(8, arr1[1]);
        assertEquals(15, arr1[2]);
        assertEquals(22, arr1[3]);
        assertEquals(29, arr1[4]);

        int[] arr2 = {1};
        mergeSort(arr1, 0, 0);
        assertEquals(1, arr2[0]);

    }
}
