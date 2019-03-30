package algorythms;

public class SortImplementation {

    public static int[] collectionSort(int[] assorted) {
        for (int i = 0; i < assorted.length - 1; i++) {
            int minIndex = findMinIndex(assorted, i);
            assorted = swap(assorted, i, minIndex);
        }
        return assorted;
    }

    private static int findMinIndex(int[] assorted, int start) {
        int min = assorted[start];
        int minIndex = start;
        for (int i = start; i < assorted.length; i++) {
            if (min > assorted[i]) {
                min = assorted[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    private static int[] swap(int[] assorted, int a, int b) {
        int temp = assorted[a];
        assorted[a] = assorted[b];
        assorted[b] = temp;
        return assorted;
    }
}
