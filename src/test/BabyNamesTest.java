import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BabyNamesTest {

    @Test
    public void testGetRank() {
        assertEquals(336, BabyNames.getRank(2014, "Frank", "M"));
        assertEquals(1, BabyNames.getRank(2014, "Noah", "M"));
        assertEquals(7, BabyNames.getRank(2014, "Emily", "F"));
        assertEquals(1, BabyNames.getRank(2014, "Emma", "F"));
    }

    @Test
    public void testGetName() {
        assertEquals("Liam", BabyNames.getName(2014, 2, "M"));
        assertEquals("Daniel", BabyNames.getName(2014, 10, "M"));
        assertEquals("Zyriyah", BabyNames.getName(2014, 19067, "F"));
        assertEquals("Olivia", BabyNames.getName(2014, 2, "F"));
    }

    @Test
    public void testWhatIsNameInYear() {
        assertEquals("NO NAME", BabyNames.whatIsNameInYear("Qqqq", 2014, 1880, "F"));
        assertEquals("Mary", BabyNames.whatIsNameInYear("Emma", 2014, 1880, "F"));
        assertEquals("James", BabyNames.whatIsNameInYear("Mason", 2014, 1880, "M"));
        assertEquals("Charles", BabyNames.whatIsNameInYear("Jacob", 2014, 1880, "M"));
    }

    @Test
    public void testGetTotalBirthsRankedHigher() {
        assertEquals(1498074, BabyNames.getTotalBirthsRankedHigher(1990, "Drew", "M"));
        assertEquals(20799, BabyNames.getTotalBirthsRankedHigher(2014, "Olivia", "F"));
    }
}
