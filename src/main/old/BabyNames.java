package old;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

class BabyNames {

    static int getRank(int year, String name, String sexMF) {
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

    static String getName(int year, int rank, String sexMF) {
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

    static String whatIsNameInYear(String name, int year, int newYear, String sexMF) {
        return getName(newYear, getRank(year, name, sexMF), sexMF);
    }

    static int yearOfHighestRank(String name, String sexMF) {
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

    static double getAverageRank(String name, String sexMF) {
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

    static int getTotalBirthsRankedHigher(int year, String name, String sexMF) {
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
        return numberOfBirth;
    }
}
