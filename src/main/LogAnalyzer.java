import java.util.ArrayList;

/**
 * Allows to read server logs and to store data in java objects.
 *
 * @author (Yaroslav Rybiak)
 * @version (09.03.2019)
 */
public class LogAnalyzer {
    private ArrayList<LogEntry> records;

    public LogAnalyzer() {
        // complete constructor
    }

    public void readFile(String filename) {
        // complete method
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }


}
