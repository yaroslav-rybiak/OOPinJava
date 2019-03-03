public class CaesarBreaker {

    int[] countLetters(String phrase) {

        int[] counters = new int[26];
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i < phrase.length(); i++) {
            int index = alphabet.indexOf(Character.toLowerCase(phrase.charAt(i)));
            if(index != -1) {
                counters[index]++;
            }
        }
        return counters;
    }

    int maxIndex(String phrase) {
        int[] counters = countLetters(phrase);
        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < counters.length; i++) {
            if (max < counters[i]) {
                max = counters[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    String decrypt(String encryptedString) {
        int eIndex = maxIndex(encryptedString);
        int key;
        if(eIndex >= 4) {
            key = eIndex - 4;
        } else {
            key = 26 - (4 - eIndex);
        }
        System.out.println(key);
        CaesarCipher cc = new CaesarCipher();
        return cc.encrypt(encryptedString, 26 - key);
    }

    String halfOfString(String message, int start) {
        StringBuilder sb = new StringBuilder();
        for(int i = start; i < message.length(); i+=2) {
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    int getKey(String message) {
        return maxIndex(message);
    }

    String decryptTwoKeys(String encryptedMessage) {
        String substring1 = halfOfString(encryptedMessage, 0);
        String substring2 = halfOfString(encryptedMessage, 1);

        String decrypt1 = decrypt(substring1);
        String decrypt2 = decrypt(substring2);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < decrypt1.length(); i++) {
            sb.append(decrypt1.charAt(i));
            if (i < decrypt2.length()) {
                sb.append(decrypt2.charAt(i));
            }
        }
        return sb.toString();
    }


}
