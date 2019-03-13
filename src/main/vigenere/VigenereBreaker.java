package vigenere;

public class VigenereBreaker {
    public String sliceString(String message, int start, int step) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < message.length(); i += step) {
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        return key;
    }

    public void breakVigenere() {
        //WRITE YOUR CODE HERE
    }

}
