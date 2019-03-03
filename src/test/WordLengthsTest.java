import edu.duke.FileResource;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WordLengthsTest {
    private FileResource resource = new FileResource("resources/smallHamlet.txt");
    private int[] counter = new int[11];

    @Test
    public void testCountWordLengths() {
        counter = WordLengths.countWordLengths(resource, counter);
        assertEquals(2, counter[2]);
        assertEquals(3, counter[3]);
        assertEquals(2, counter[4]);
        assertEquals(1, counter[5]);
        assertEquals(1, counter[6]);
        assertEquals(1, counter[7]);
        assertEquals(2, counter[8]);
        assertEquals(1, counter[11]);
    }
}
