import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class BabyNames {

    private static void totalBirths(CSVParser parser) {
        int countM = 0;
        int countF = 0;
        int countAll = 0;
        for (CSVRecord r : parser) {
            if (r.get(1).equals("M")) {
                countM++;
            } else if (r.get(1).equals("F")) {
                countF++;
            }
            countAll++;
        }
        System.out.println(String.format("%s boys + %s girls = %s beings", countM, countF, countAll));
    }

    private static int getRank(int year, String name, String sexMF) {
        String fileName = "resources/babynames/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(fileName);
        int lineNumber = 0;
        int rank = -1;
        for (CSVRecord r : fr.getCSVParser(false)) {
            if (r.get(1).equals(sexMF)) {
                lineNumber++;
                if (r.get(0).equals(name)) {
                    rank = lineNumber;
                }
            }
        }
        return rank;
    }

    private static String getName(int year, int rank, String sexMF) {
        String fileName = "resources/babynames/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(fileName);
        String name = "NO NAME";
        int i = 0;
        for (CSVRecord r : fr.getCSVParser(false)) {
            if (r.get(1).equals(sexMF)) {
                if (rank == ++i) {
                    name = r.get(0);
                }
            }
        }
        return name;
    }

    private static void whatIsNameInYear(String name, int year, int newYear, String sexMF) {
        String newName = getName(newYear, getRank(year, name, sexMF), sexMF);
        System.out.println(String.format("%s born in %s would be %s if she was born in %s.", name, year, newName, newYear));
    }

    private static int yearOfHighestRank(String name, String sexMF) {
        DirectoryResource dr = new DirectoryResource();
        int highestRank = -1;
        int yearOFhighestRank = -1;
        for (File nameFile : dr.selectedFiles()) {
            int year = Integer.parseInt(nameFile.getName().substring(3, 7));
            int currentRank = getRank(year, name, sexMF);
            if (highestRank == -1 && currentRank != -1) {
                highestRank = currentRank;
                yearOFhighestRank = year;
            } else {
                if (currentRank < highestRank & currentRank != -1) {
                    highestRank = currentRank;
                    yearOFhighestRank = year;
                }
            }

        }
        System.out.println(yearOFhighestRank);
        return highestRank;
    }

    private static double getAverageRank(String name, String sexMF) {
        DirectoryResource dr = new DirectoryResource();
        double rank = -1.0;
        double rankSum = 0;
        double nameCount = 0;
        for (File nameFile : dr.selectedFiles()) {
            int year = Integer.parseInt(nameFile.getName().substring(3, 7));
            double currentRank = getRank(year, name, sexMF);
            if (currentRank != -1) rankSum += currentRank;
            nameCount++;
        }
        if (nameCount != 0) rank = rankSum / nameCount;
        return rank;
    }

    private static void getTotalBirthsRankedHigher(int year, String name, String sexMF) {
        FileResource fr = new FileResource("resources/babynames/us_babynames_by_year/yob" + year + ".csv");
        int numberOfBirth = 0;
        for (CSVRecord r : fr.getCSVParser(false)) {
            if (r.get(1).equals(sexMF)) {
                if (!r.get(0).equals(name))
                    numberOfBirth += Integer.parseInt(r.get(2));
                else {
                    break;
                }
            }
        }
        System.out.println(numberOfBirth);
    }

    public static void main(String[] args) {
//        FileResource fr = new FileResource("resources/babynames/us_babynames_by_year/yob1905.csv");
//        totalBirths(fr.getCSVParser(false));
//        System.out.println(getRank(1971, "Frank", "M"));
//        System.out.println(getName(1982, 450, "M"));
//        whatIsNameInYear("Owen", 1974, 2014, "M");
//        System.out.println(yearOfHighestRank("Mich", "M"));
//        System.out.println(getAverageRank("Robert", "M"));
        getTotalBirthsRankedHigher(1990, "Drew", "M");
    }
}
