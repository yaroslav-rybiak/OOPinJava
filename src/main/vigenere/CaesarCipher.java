package vigenere;

import edu.duke.FileResource;

class CaesarCipher {

    private int key;

    CaesarCipher(int key) {
        this.key = key;
    }

    String encrypt(String input) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //Compute the shifted alphabet
        String shiftedAlphabet = alphabet.substring(key) +
                alphabet.substring(0, key);
        //Count from 0 to < length of encrypted, (call it i)
        for (int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            boolean isUp = false;
            char currChar = Character.toUpperCase(encrypted.charAt(i));
            if (currChar == encrypted.charAt(i)) isUp = true;
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(currChar);
            //If currChar is in the alphabet
            if (idx != -1) {
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedAlphabet.charAt(idx);
                //Replace the ith character of encrypted with newChar
                if (isUp) {
                    encrypted.setCharAt(i, newChar);
                } else {
                    encrypted.setCharAt(i, Character.toLowerCase(newChar));
                }
            }
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }

    String decrypt(String input) {
        key = 26 - key;
        return encrypt(input);
    }

    String encrypt(FileResource fr) {
        return encrypt(fr.asString());
    }

    String decrypt(FileResource fr) {
        key = 26 - key;
        return encrypt(fr.toString());
    }
}

