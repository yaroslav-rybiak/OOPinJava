package vigenere;

import edu.duke.FileResource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

public class VigenereBreaker {

    private HashMap<String, HashSet<String>> languages;

    //constructor
    public VigenereBreaker() {
        languages = new HashMap<>();
        languages.put("Danish", readDictionary("resources/vigenere/dictionaries/Danish"));
        languages.put("Dutch", readDictionary("resources/vigenere/dictionaries/Dutch"));
        languages.put("English", readDictionary("resources/vigenere/dictionaries/English"));
        languages.put("French", readDictionary("resources/vigenere/dictionaries/French"));
        languages.put("German", readDictionary("resources/vigenere/dictionaries/German"));
        languages.put("Italian", readDictionary("resources/vigenere/dictionaries/Italian"));
        languages.put("Portuguese", readDictionary("resources/vigenere/dictionaries/Portuguese"));
        languages.put("Spanish", readDictionary("resources/vigenere/dictionaries/Spanish"));
    }

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
        for (String line : fr.lines()) {
            words.add(line.toLowerCase());
        }
        return words;
    }

    public int countWords(String message, HashSet<String> dictionary) {
        String[] words = message.split("\\W+");
        int amount = 0;
        for (String word : words) {
            word = word.toLowerCase();
            if (dictionary.contains(word)) amount++;
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
            if (countWords > max) {
                max = countWords;
                finalResult = decrypted;
            }
        }
        return finalResult;
    }

    public char mostCommonCharIn(HashSet<String> dictionary) {
        HashMap<Character, Integer> chars = new HashMap<>();
        for (String word : dictionary) {
            for (char c : word.toCharArray()) {
                if (!chars.containsKey(c)) {
                    chars.put(c, 1);
                } else {
                    chars.put(c, chars.get(c) + 1);
                }
            }
        }
        int max = 0;
        char result = '*';
        for (Map.Entry<Character, Integer> e : chars.entrySet()) {
            if (max < e.getValue()) {
                max = e.getValue();
                result = e.getKey();
            }
        }

        return result;
    }

    public String breakVigenere(String pathToEncryptedFile, String pathToDictionary, char mostCommon) {

        FileResource fr = new FileResource(pathToEncryptedFile);
        String encrypted = fr.asString();
        HashSet<String> dictionary = readDictionary(pathToDictionary);
        return breakForLanguage(encrypted, dictionary, mostCommon);
    }

    public String breakForAllLangs(String encrypted) {
        int max = 0;
        String result = "";
        String language = "";
        for (Entry<String, HashSet<String>> e : languages.entrySet()) {
            char c = mostCommonCharIn(e.getValue());
            String decrypted = breakForLanguage(encrypted, e.getValue(), c);
            int words = countWords(decrypted, e.getValue());
            if (max < words) {
                max = words;
                result = decrypted;
                language = e.getKey();
            }
        }
        return result;
    }
}
