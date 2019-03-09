package logsread;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Unit tests for LogAnalyzer class.
 *
 * @author (Yaroslav Rybiak)
 * @version (09.03.2019)
 */
public class LogAnalyzerTest {

    @Test
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer("resources/logsread/weblog1.log");

        LogEntry le1 =  la.getLine(0);
        assertEquals("177.4.40.87", le1.getIpAddress());
        assertEquals(200, le1.getStatusCode());
        assertEquals(1127, le1.getBytesReturned());

        LogEntry le2 =  la.getLine(1);
        assertEquals("177.4.40.87", le2.getIpAddress());
        assertEquals(200, le2.getStatusCode());
        assertEquals(3550, le2.getBytesReturned());
    }
}
