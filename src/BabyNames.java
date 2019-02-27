import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

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

    public static void main(String[] args) {
//        FileResource fr = new FileResource("resources/babynames/us_babynames_test/example-small.csv");
//        totalBirths(fr.getCSVParser(false));

//        System.out.println(getRank(1880, "Mary", "F"));             //1
//        System.out.println(getRank(1880, "Emma", "F"));             //3
//        System.out.println(getRank(1880, "Wilma", "F"));            //942
//        System.out.println(getRank(1880, "John", "M"));             //1
//        System.out.println(getRank(1880, "James", "M"));            //3
//        System.out.println(getRank(1880, "Zachariah", "M"));        //1058
//        System.out.println(getRank(1880, "Nebuchadnezzar", "M"));   //-1

//        System.out.println(getName(1880, 1, "F"));   //Mary
//        System.out.println(getName(1880, 3, "F"));   //Emma
//        System.out.println(getName(1880, 942, "F")); //Wilma
//        System.out.println(getName(1880, 1, "M"));   //John
//        System.out.println(getName(1880, 3, "M"));   //James
//        System.out.println(getName(1880, 1058, "M"));//Zachariah
//        System.out.println(getName(1880, 1059, "M"));//NO NAME

        whatIsNameInYear("Isabella", 2012, 2014, "F");
    }
}
