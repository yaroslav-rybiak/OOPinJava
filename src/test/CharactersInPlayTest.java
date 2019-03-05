import org.junit.Test;

import static org.junit.Assert.*;

public class CharactersInPlayTest {

    @Test
    public void testHasName() {
        CharactersInPlay cip = new CharactersInPlay();
        assertTrue(cip.hasName("HAMLET. To be or not to be?"));
        assertTrue(cip.hasName("HAMLET THE PRINCE. To be or not to be?"));
        assertTrue(cip.hasName(" ROSALIND. It is not the fashion to see the lady the epilogue;"));
        assertTrue(cip.hasName("Jaques. To see no pastime I. What you would have"));
        assertFalse(cip.hasName("With measure heap'd in joy, to th' measures fall."));
        assertFalse(cip.hasName("The Duke hath put on a religious life,"));
    }

    @Test
    public void testGetName() {
        CharactersInPlay cip = new CharactersInPlay();
        assertEquals("HAMLET", cip.getName("HAMLET. To be or not to be?"));
        assertEquals("HAMLET THE PRINCE", cip.getName("HAMLET THE PRINCE. To be or not to be?"));
        assertEquals(" ROSALIND", cip.getName(" ROSALIND. It is not the fashion to see the lady the epilogue;"));
        assertEquals("Jaques", cip.getName("Jaques. To see no pastime I. What you would have"));
        assertNull(cip.getName("With measure heap'd in joy, to th' measures fall."));
        assertNull(cip.getName("The Duke hath put on a religious life,"));
    }
}
