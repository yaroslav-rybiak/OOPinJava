package vigenere;

import edu.duke.FileResource;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CaesarCrackerTest {

    @Test
    public void testEncrypt() {
        CaesarCracker cc1 = new CaesarCracker();
        FileResource fr1 = new FileResource("resources/vigenere/titus-small_key5.txt");
        String encrypted1 = fr1.asString();
        String decrypted1 = cc1.decrypt(encrypted1);
        assertEquals("Coal-black", decrypted1.substring(0, 10));
    }

    @Test
    public void testDecrypt() {
        CaesarCracker cc2 = new CaesarCracker('a');
        FileResource fr2 = new FileResource("resources/vigenere/oslusiadas_key17.txt");
        String encrypted2 = fr2.asString();
        String decrypted2 = cc2.decrypt(encrypted2);
        assertEquals("assinalados", decrypted2.substring(21, 32));
    }
}
