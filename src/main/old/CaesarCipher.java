package old;

class CaesarCipher {

    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;

    CaesarCipher(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key) + alphabet.substring(0, key);
    }

    CaesarCipher(int key1, int key2) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
    }

    String encrypt(String input) {
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (alphabet.indexOf(currentChar) != -1) {
                sb.setCharAt(i, shiftedAlphabet1.charAt(alphabet.indexOf(currentChar)));
            }
            if (alphabet.toLowerCase().indexOf(currentChar) != -1) {
                sb.setCharAt(i, shiftedAlphabet1.toLowerCase().charAt(alphabet.toLowerCase().indexOf(Character.toLowerCase(currentChar))));
            }
        }
        return sb.toString();
    }

    String encryptTwoKeys(String input) {
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (alphabet.indexOf(currentChar) != -1) {
                if (i % 2 == 0) {
                    sb.setCharAt(i, shiftedAlphabet1.charAt(alphabet.indexOf(currentChar)));
                } else {
                    sb.setCharAt(i, shiftedAlphabet2.charAt(alphabet.indexOf(currentChar)));
                }
            }
            if (alphabet.toLowerCase().indexOf(currentChar) != -1) {
                if (i % 2 == 0) {
                    sb.setCharAt(i, shiftedAlphabet1.toLowerCase().charAt(alphabet.toLowerCase().indexOf(Character.toLowerCase(currentChar))));
                } else {
                    sb.setCharAt(i, shiftedAlphabet2.toLowerCase().charAt(alphabet.toLowerCase().indexOf(Character.toLowerCase(currentChar))));
                }
            }
        }
        return sb.toString();
    }
}
