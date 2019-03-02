import org.junit.Test;

import static junit.framework.TestCase.*;

public class WordPlayTest {

    @Test
    public void testEmphasize() {
        assertEquals("dn* ctg+*+ctg+", WordPlay.emphasize("dna ctgaaactga", 'a'));
        assertEquals("M+ry Bell+ +br*c*d*br+", WordPlay.emphasize("Mary Bella Abracadabra", 'a'));

    }

    @Test
    public void testIsWovel() {
        assertFalse(WordPlay.isVowel('B'));
        assertFalse(WordPlay.isVowel('b'));
        assertFalse(WordPlay.isVowel('*'));
        assertFalse(WordPlay.isVowel('\t'));
        assertTrue(WordPlay.isVowel('A'));
        assertTrue(WordPlay.isVowel('a'));
        assertTrue(WordPlay.isVowel('O'));
        assertTrue(WordPlay.isVowel('o'));
        assertTrue(WordPlay.isVowel('U'));
        assertTrue(WordPlay.isVowel('u'));
        assertTrue(WordPlay.isVowel('I'));
        assertTrue(WordPlay.isVowel('i'));
        assertTrue(WordPlay.isVowel('E'));
        assertTrue(WordPlay.isVowel('e'));
    }

    @Test
    public void testReplaceVowels() {
        assertEquals("N**n", WordPlay.replaceVowels("Noun"));
        assertEquals("*mm*", WordPlay.replaceVowels("Emma"));
        assertEquals("************", WordPlay.replaceVowels("AaOoUuIiEeUu"));
    }
}
