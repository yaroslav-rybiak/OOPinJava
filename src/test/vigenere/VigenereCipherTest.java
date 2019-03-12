package vigenere;

import edu.duke.FileResource;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VigenereCipherTest {
    
    @Test
    public void testEncrypt() {
        int[] key = {17, 14, 12, 4};
        FileResource fr = new FileResource("resources/vigenere/titus-small.txt");
        String text = fr.asString();
        VigenereCipher vc = new VigenereCipher(key);
        assertEquals("Tcmp-pxety", vc.encrypt(text).substring(0, 10));
    }

    @Test
    public void testDecrypt() {
        int[] key = {1, 2, 3, 4};
        VigenereCipher vc = new VigenereCipher(key);
        String decrypted = vc.decrypt("bcde");
        assertEquals("aaaa", decrypted);
    }
}
