package logsread;

import edu.duke.FileResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

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
        HashMap<String, Integer> unique = new HashMap<>();
        String ip;
        for (LogEntry record : records) {
            ip = record.getIpAddress();
            if (!unique.containsKey(ip)) {
                unique.put(ip, 1);
            } else {
                unique.put(ip, unique.get(ip) + 1);
            }
        }
        return unique.size();
    }

    HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> unique = new HashMap<>();
        String ip;
        for (LogEntry record : records) {
            ip = record.getIpAddress();
            if (!unique.containsKey(ip)) {
                unique.put(ip, 1);
            } else {
                unique.put(ip, unique.get(ip) + 1);
            }
        }
        return unique;
    }

    int mostNumberVisitsByIP(HashMap<String, Integer> map) {
        int max = 0;
        String ip = "";
        for (Entry<String, Integer> entry : map.entrySet()) {
            if (max < entry.getValue()) {
                max = entry.getValue();
                ip = entry.getKey();
            }
        }
        return max;
    }

    int uniqueIPVisitsOnDay(String day) {
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

    ArrayList<String> iPsMostVisits(HashMap<String, Integer> map) {
        int max = mostNumberVisitsByIP(map);
        ArrayList<String> array = new ArrayList<>();
        for (Entry<String, Integer> e : map.entrySet()) {
            if (e.getValue() == max) {
                array.add(e.getKey());
            }
        }
        return array;
    }

    HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        String day;
        for (LogEntry entry : records) {
            day = entry.getAccessTime().toString().substring(4, 10);
            if (!map.containsKey(day)) {
                map.put(day, new ArrayList<>());
                map.get(day).add(entry.getIpAddress());
            } else {
                map.get(day).add(entry.getIpAddress());
            }
        }
        return map;
    }

    String dayWithMostIPVisits(HashMap<String, ArrayList<String>> map) {
        int max = 0;
        String day = "";
        for (Entry<String, ArrayList<String>> e : map.entrySet()) {
            if (e.getValue().size() > max) {
                max = e.getValue().size();
                day = e.getKey();
            }
        }
        return day;
    }

    ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> map, String day) {
        ArrayList<String> ips = new ArrayList<>();
        for (Entry<String, ArrayList<String>> e : map.entrySet()) {
            if (e.getKey().equals(day)) {
                for (String ip : e.getValue()) {
                    ips.add(ip);
                }
            }
        }
        HashMap<String, Integer> ipCount = new HashMap<>();
        for (String ip : ips) {
            if (!ipCount.containsKey(ip)) {
                ipCount.put(ip, 1);
            } else {
                ipCount.put(ip, ipCount.get(ip) + 1);
            }
        }

        int max = mostNumberVisitsByIP(ipCount);
        ArrayList<String> result = new ArrayList<>();
        for (Entry<String, Integer> e : ipCount.entrySet()) {
            if (e.getValue() == max) {
                result.add(e.getKey());
            }
        }

        return result;
    }
}
