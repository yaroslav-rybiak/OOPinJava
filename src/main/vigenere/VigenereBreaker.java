package vigenere;

import edu.duke.FileResource;

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
        String[] encryptedSlices = new String[klength];
        for (int i = 0; i < encryptedSlices.length; i++) {
            encryptedSlices[i] = sliceString(encrypted, i, encryptedSlices.length);
        }
        CaesarCracker cc = new CaesarCracker();
        for (int i = 0; i < key.length; i++) {
            key[i] = cc.getKey(encryptedSlices[i]);
        }

        return key;
    }

    public String breakVigenere(String path, int klength, char mostCommon) {
        FileResource fr = new FileResource(path);
        VigenereBreaker vb = new VigenereBreaker();
        String encrypted = fr.asString();
        int[] key = vb.tryKeyLength(encrypted, klength, mostCommon);
        VigenereCipher vc = new VigenereCipher(key);
        return vc.decrypt(encrypted);
    }

}
