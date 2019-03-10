package logsread;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for LogAnalyzer class.
 *
 * @author (Yaroslav Rybiak)
 * @version (10.03.2019)
 */
public class LogAnalyzerTest {

    private LogAnalyzer la = new LogAnalyzer("resources/logsread/weblog1.log");

    @Test
    public void testLogAnalyzer() {
        LogEntry le1 = la.getLine(0);
        assertEquals("177.4.40.87", le1.getIpAddress());
        assertEquals(200, le1.getStatusCode());
        assertEquals(1127, le1.getBytesReturned());

        LogEntry le2 = la.getLine(1);
        assertEquals("177.4.40.87", le2.getIpAddress());
        assertEquals(200, le2.getStatusCode());
        assertEquals(3550, le2.getBytesReturned());
    }

    @Test
    public void testCountUniqueIPs() {
        assertEquals(75, la.countUniqueIPs());
    }

    @Test
    public void testUniqueIPVisitsOnDay() {
        assertEquals(7, la.uniqueIPVisitsOnDay("Mar 15"));
        assertEquals(16, la.uniqueIPVisitsOnDay("Mar 17"));
        assertEquals(5, la.uniqueIPVisitsOnDay("Mar 26"));
    }

    @Test
    public void testPrintAllHigherThanNum() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(404);
        assertEquals(list, la.printAllHigherThanNum(400));
    }

    @Test
    public void testCountUniqueIPsInRange() {
        assertEquals(4, la.countUniqueIPsInRange(300, 399));
    }
}
