import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class BabyNames {

    private static int countBoys(CSVParser parser) {
        int countM = 0;
        for(CSVRecord r: parser) {
            if(r.get(1).equals("M")) {
                countM++;
            }
        }
        return countM;
    }

    public static void main(String[] args) {
        FileResource fr = new FileResource("resources/babynames/us_babynames_test/example-small.csv");
        System.out.println("Number of boys: " + countBoys(fr.getCSVParser(false)));
    }
}
