package vigenere;

import edu.duke.FileResource;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VigenereCipherTest {


    @Test
    public void testEncrypt() {
        int[] key = {1, 2, 3, 4};
        VigenereCipher vc = new VigenereCipher(key);
        String encrypted = vc.encrypt("aaaa");
        assertEquals("bcde", encrypted);
    }

    @Test
    public void testDecrypt() {
        int[] key = {1, 2, 3, 4};
        VigenereCipher vc = new VigenereCipher(key);
        String decrypted = vc.decrypt("bcde");
        assertEquals("aaaa", decrypted);
    }
}
