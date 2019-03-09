package logsread;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for LogEntry class.
 *
 * @author (Yaroslav Rybiak)
 * @version (09.03.2019)
 */
public class LogEntryTest {

    @Test
    public void testLogEntry() {
        LogEntry le1 = new LogEntry("1.2.3.4", new Date(), "example request 1", 200, 500);
        assertEquals("1.2.3.4", le1.getIpAddress());
        assertEquals("example request 1", le1.getRequest());
        assertEquals(200, le1.getStatusCode());
        assertEquals(500, le1.getBytesReturned());

        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        assertEquals("1.2.100.4", le2.getIpAddress());
        assertEquals("example request 2", le2.getRequest());
        assertEquals(300, le2.getStatusCode());
        assertEquals(400, le2.getBytesReturned());
    }
}
