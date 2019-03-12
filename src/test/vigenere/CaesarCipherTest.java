package vigenere;

import edu.duke.FileResource;
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
    @Test
    public void testEncryptFile() {
        CaesarCipher cs = new CaesarCipher(17);
        FileResource fr1 = new FileResource("resources/vigenere/message1.txt");
        String encrypted1 = cs.encrypt(fr1).replace("\n", "");
        assertEquals("WIVV TRBV ZE KYV TFEWVIVETV IFFD!", encrypted1);

        FileResource fr2 = new FileResource("resources/vigenere/message2.txt");
        String encrypted2 = cs.encrypt(fr2).replace("\n", " ");
        String expected = "Uvri Fnve,   Ef drkkvi nyrk pfl drp yrmv yvriu, kyviv zj ef trbv ze kyv tfewvivetv iffd.  Kyv trbv zj r czv.  Gcvrjv bvvg nfibzex fe Tflijvir mzuvfj.     Kyrebj, Uivn ";
        assertEquals(expected, encrypted2);
    }
}
