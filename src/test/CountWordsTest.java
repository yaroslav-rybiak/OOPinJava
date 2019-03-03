import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CountWordsTest {

    @Test
    public void testCountWords() {
        Map<String, Integer> schopenhauer = CountWords.countWords("The more I learn about people, the more I like my dog.");
        assertEquals(9, schopenhauer.size());
        assertEquals((Integer) 2, schopenhauer.get("the"));
        assertEquals((Integer) 2, schopenhauer.get("i"));
        assertEquals((Integer) 1, schopenhauer.get("dog"));
        assertEquals((Integer) 1, schopenhauer.get("people"));

        Map<String, Integer> shaw = CountWords.countWords("Beware of false knowledge; it is more dangerous than ignorance.");
        assertEquals(10, shaw.size());
        assertEquals((Integer) 1, shaw.get("beware"));
        assertEquals((Integer) 1, shaw.get("knowledge"));
        assertEquals((Integer) 1, shaw.get("it"));
        assertEquals((Integer) 1, shaw.get("ignorance"));
    }
}
