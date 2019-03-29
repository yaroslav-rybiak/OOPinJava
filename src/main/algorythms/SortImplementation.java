package algorythms;

public class SortImplementation {

    public static int[] collectionSort(int[] assorted) {
        for (int i = 0; i < assorted.length - 2; i++) {
            int[] mins = findMin(assorted, i);
            int min = mins[0];
            int minIndex = mins[1];
            int temp = assorted[i];
            assorted[i] = min;
            assorted[minIndex] = temp;

        }
        return assorted;
    }

    private static int[] findMin(int[] assorted, int start) {
        int[] result = new int[2];
        int min = assorted[start];
        int minIndex = start;
        for (int i = start; i < assorted.length; i++) {
            if (min > assorted[i]) {
                min = assorted[i];
                minIndex = i;
            }
        }
        result[0] = min;
        result[1] = minIndex;

        return result;
    }
}
