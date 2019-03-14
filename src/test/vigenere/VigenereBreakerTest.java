package vigenere;

import edu.duke.FileResource;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class VigenereBreakerTest {

    @Test
    public void testSliceString() {
        VigenereBreaker vb = new VigenereBreaker();
        assertEquals("adgjm", vb.sliceString("abcdefghijklm", 0, 3));
        assertEquals("behk", vb.sliceString("abcdefghijklm", 1, 3));
        assertEquals("cfil", vb.sliceString("abcdefghijklm", 2, 3));
        assertEquals("aeim", vb.sliceString("abcdefghijklm", 0, 4));
        assertEquals("bfj", vb.sliceString("abcdefghijklm", 1, 4));
        assertEquals("cgk", vb.sliceString("abcdefghijklm", 2, 4));
        assertEquals("dhl", vb.sliceString("abcdefghijklm", 3, 4));
        assertEquals("afk", vb.sliceString("abcdefghijklm", 0, 5));
        assertEquals("bgl", vb.sliceString("abcdefghijklm", 1, 5));
        assertEquals("chm", vb.sliceString("abcdefghijklm", 2, 5));
        assertEquals("di", vb.sliceString("abcdefghijklm", 3, 5));
        assertEquals("ej", vb.sliceString("abcdefghijklm", 4, 5));
    }

    @Test
    public void testTryKeyLength() {
        VigenereBreaker vb = new VigenereBreaker();
        FileResource fr = new FileResource("resources/vigenere/athens_keyflute.txt");
        int[] actual = vb.tryKeyLength(fr.asString(), 5, 'e');
        int[] expected = {5, 11, 20, 19, 4};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testBreakVigenere() {
        VigenereBreaker vb = new VigenereBreaker();
        String decrypted = vb.breakVigenere("resources/vigenere/athens_keyflute.txt", "resources/vigenere/dictionaries/English", 'e');
        assertEquals("SCENE II", decrypted.substring(0, 8));
    }
}
