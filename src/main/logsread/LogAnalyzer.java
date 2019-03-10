package logsread;

import edu.duke.FileResource;

import java.util.ArrayList;

/**
 * Allows to read server logs and to store data in java objects.
 *
 * @author (Yaroslav Rybiak)
 * @version (10.03.2019)
 */
public class LogAnalyzer {
    private ArrayList<LogEntry> records;

    LogAnalyzer(String filename) {
        FileResource fr = new FileResource(filename);
        records = new ArrayList<>();
        for (String line : fr.lines()) {
            records.add(WebLogParser.parseEntry(line));
        }
    }

    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

    LogEntry getLine(int number) {
        return records.get(number);
    }

    int countUniqueIPs() {
        ArrayList<String> unique = new ArrayList<>();
        String ip;
        for (LogEntry record : records) {
            ip = record.getIpAddress();
            if (!unique.contains(ip)) {
                unique.add(ip);
            }
        }
        return unique.size();
    }

    int uniqueIPVisitsOnDay(String day) {
        //date format "MMM DD"
        ArrayList<String> unique = new ArrayList<>();
        String ip;
        String date;
        for (LogEntry record : records) {
            ip = record.getIpAddress();
            date = record.getAccessTime().toString();
            if (date.contains(day) && !unique.contains(ip)) {
                unique.add(ip);
            }
        }
        return unique.size();
    }

    ArrayList<Integer> printAllHigherThanNum(int num) {
        ArrayList<Integer> higher = new ArrayList<>();
        int statusCode;
        for (LogEntry record : records) {
            statusCode = record.getStatusCode();
            if (statusCode > num && !higher.contains(statusCode)) {
                higher.add(statusCode);
            }
        }
        return higher;
    }

    int countUniqueIPsInRange(int min, int max) {
        ArrayList<String> uniqueInRange = new ArrayList<>();
        int statusCode;
        String ip;
        for (LogEntry record : records) {
            statusCode = record.getStatusCode();
            ip = record.getIpAddress();
            if ((statusCode >= min && statusCode <= max) && !uniqueInRange.contains(ip)) {
                uniqueInRange.add(ip);
            }
        }
        return uniqueInRange.size();
    }
}
