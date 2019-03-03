class CaesarCipher {

    String encrypt(String input, int key) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String newAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (alphabet.indexOf(currentChar) != -1) {
                sb.setCharAt(i, newAlphabet.charAt(alphabet.indexOf(currentChar)));
            }
            if (alphabet.toLowerCase().indexOf(currentChar) != -1) {
                sb.setCharAt(i, newAlphabet.toLowerCase().charAt(alphabet.toLowerCase().indexOf(Character.toLowerCase(currentChar))));
            }
        }
        return sb.toString();
    }

    String encryptTwoKeys(String input, int key1, int key2) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String newAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        String newAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        StringBuilder sb = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (alphabet.indexOf(currentChar) != -1) {
                if (i % 2 == 0) {
                    sb.setCharAt(i, newAlphabet1.charAt(alphabet.indexOf(currentChar)));
                } else {
                    sb.setCharAt(i, newAlphabet2.charAt(alphabet.indexOf(currentChar)));
                }
            }
            if (alphabet.toLowerCase().indexOf(currentChar) != -1) {
                if (i % 2 == 0) {
                    sb.setCharAt(i, newAlphabet1.toLowerCase().charAt(alphabet.toLowerCase().indexOf(Character.toLowerCase(currentChar))));
                } else {
                    sb.setCharAt(i, newAlphabet2.toLowerCase().charAt(alphabet.toLowerCase().indexOf(Character.toLowerCase(currentChar))));
                }
            }
        }
        return sb.toString();
    }
}
