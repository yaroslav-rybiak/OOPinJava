package old;

import edu.duke.FileResource;

class WordLengths {

    static int[] countWordLengths(FileResource resource, int[] counts) {

        int[] newCount = new int[counts.length + 1];

        for (String word : resource.words()) {
            int length;
            if (!Character.isLetter(word.charAt(0)) & !Character.isLetter(word.charAt(word.length() - 1)))
                length = word.length() - 2;
            else if (!Character.isLetter(word.charAt(0)) || !Character.isLetter(word.charAt(word.length() - 1)))
                length = word.length() - 1;
            else
                length = word.length();

            if (length < newCount.length) {
                newCount[length]++;
            } else {
                newCount[newCount.length - 1]++;
            }
        }
        return newCount;
    }
}
