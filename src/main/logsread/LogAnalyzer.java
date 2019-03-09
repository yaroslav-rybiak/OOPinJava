package logsread;

import edu.duke.FileResource;

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
        records = new ArrayList<>();
    }

    public LogAnalyzer(String filename) {
        FileResource fr = new FileResource(filename);
        records = new ArrayList<>();
        for (String line: fr.lines()) {
            records.add(WebLogParser.parseEntry(line));
        }
    }

    public void readFile(String filename) {
        FileResource fr = new FileResource(filename);
        for (String line: fr.lines()) {
            records.add(WebLogParser.parseEntry(line));
        }
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

    public LogEntry getLine(int number) {
        return records.get(number);
    }


}
