import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class WeatherAnalyser {

    private static CSVRecord maxTempInFile(FileResource file) {
        CSVParser parser = file.getCSVParser();
        CSVRecord largestSoFar = null;
        for (CSVRecord record: parser) {
            if (largestSoFar == null) {
                largestSoFar = record;
            } else {
                double highestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
                double current = Double.parseDouble(record.get("TemperatureF"));
                if (current > highestTemp) largestSoFar = record;
            }
        }
        return largestSoFar;
    }

    public static void main(String[] args) {
        FileResource file1 = new FileResource("resources/data/2012/weather-2012-01-01.csv");
        FileResource file2 = new FileResource("resources/data/2012/weather-2012-01-02.csv");
        System.out.println(maxTempInFile(file1).get("TemperatureF"));
        System.out.println(maxTempInFile(file2).get("TemperatureF"));

    }
}
