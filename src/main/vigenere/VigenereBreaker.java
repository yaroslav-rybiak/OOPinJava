package vigenere;

import edu.duke.FileResource;

import java.util.HashSet;

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

    public HashSet<String> readDictionary(String pathToDictionary) {
        FileResource fr = new FileResource(pathToDictionary);
        HashSet<String> words = new HashSet<>();
        for(String line: fr.lines()) {
            words.add(line.toLowerCase());
        }
        return words;
    }

    public int countWords(String message, HashSet<String> dictionary) {
        String[] words = message.split("\\W");
        int amount = 0;
        for (String word: words) {
            if(dictionary.contains(word)) amount++;
        }
        return amount;
    }

    public String breakForLanguage(String enrypted, HashSet<String> dictionary, char mostCommon) {
        VigenereCipher vc;
        String finalResult = "";
        int max = 0;
        for (int i = 1; i <= 100; i++) {
            int[] key = tryKeyLength(enrypted, i, mostCommon);
            vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(enrypted);
            int countWords = countWords(decrypted, dictionary);
            if(countWords > max) {
                max = countWords;
                finalResult = decrypted;
            }
        }
        return finalResult;
    }

    public String breakVigenere(String pathToEncryptedFile, String pathToDictionary, char mostCommon) {

        FileResource fr = new FileResource(pathToEncryptedFile);
        String encrypted = fr.asString();
        HashSet<String> dictionary = readDictionary(pathToDictionary);
        return breakForLanguage(encrypted, dictionary, mostCommon);
    }
}
