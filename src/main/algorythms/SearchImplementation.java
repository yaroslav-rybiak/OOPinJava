package algorythms;

public class SearchImplementation {

    static int linearSearch(String[] wordsAarray, String word) {
        for (int i = 0; i < wordsAarray.length; i++) {
            if (wordsAarray[i].equals(word)) {
                return i;
            } else {
            }
        }
        return -1;
    }

    static int binarySearch(String[] wordsAarray, String word) {
        if (word == null) {
            //Search query can not be null
            return -2;
        }
        if (wordsAarray == null) {
            //Dictionary can not be null
            return -3;
        }
        if (wordsAarray.length == 0) {
            //Dictionary can not be empty
            return -4;
        }
        for (String wordFromArray : wordsAarray) {
            if (wordFromArray == null) {
                //Dictionary can not contain nulls
                return -5;
            }
        }
        int min = 0;
        int max = wordsAarray.length - 1;
        while (min <= max) {
            int med = (min + max) / 2;
            int diff = word.compareTo(wordsAarray[med]);
            if (diff < 0) {
                max = med - 1;
            } else if (diff > 0) {
                min = med + 1;
            } else {
                return med;
            }
        }
        return -1;
    }
}
