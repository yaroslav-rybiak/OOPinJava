package vigenere;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CaesarCipherTest {


    @Test
    public void testEncryptString() {
        CaesarCipher cs = new CaesarCipher(17);
        String encrypted = cs.encrypt("FREE CAKE IN THE CONFERENCE ROOM!");
        String decrypted = cs.decrypt("WIVV TRBV ZE KYV TFEWVIVETV IFFD!");
        assertEquals("WIVV TRBV ZE KYV TFEWVIVETV IFFD!", encrypted);
        assertEquals("FREE CAKE IN THE CONFERENCE ROOM!", decrypted);
    }
}
