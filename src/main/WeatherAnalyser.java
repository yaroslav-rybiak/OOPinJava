import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class WeatherAnalyser {

    private static CSVRecord getColdestOfTwo(CSVRecord r1, CSVRecord r2) {
        if (r1 == null) return r2;
        else {
            double r1temp = Double.parseDouble(r1.get("TemperatureF"));
            double r2temp = Double.parseDouble(r2.get("TemperatureF"));
            if (r1temp == -9999) return r2;
            else if (r2temp == -9999) return r1;
            else {
                if (r1temp < r2temp) return r1;
                else return r2;
            }
        }
    }

    private static CSVRecord minTempInFile(FileResource file) {
        CSVParser parser = file.getCSVParser();
        CSVRecord coldestRow = null;
        for (CSVRecord currentRow : parser) {
            coldestRow = getColdestOfTwo(coldestRow, currentRow);
        }
        return coldestRow;
    }

    private static String fileWithColdestTemperature() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord coldestRow = null;
        String coldestFile = "";
        for (File weatherFile : dr.selectedFiles()) {
            FileResource weatherFileResource = new FileResource(weatherFile);
            CSVRecord currentRow = minTempInFile(weatherFileResource);
            if (coldestRow == null) {
                coldestRow = currentRow;
            }
            double coldestTemp = Double.parseDouble(coldestRow.get("TemperatureF"));
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            if (coldestTemp > currentTemp && currentTemp != -9999) {
                coldestFile = weatherFile.getName();
                coldestRow = currentRow;
                System.out.println("Temperature was " + coldestRow.get("TemperatureF"));
            }
        }
        return coldestFile;
    }

    private static CSVRecord getHottestOfTwo(CSVRecord r1, CSVRecord r2) {
        if (r1 == null) return r2;
        else {
            double r1temp = Double.parseDouble(r1.get("TemperatureF"));
            double r2temp = Double.parseDouble(r2.get("TemperatureF"));
            if (r1temp > r2temp) return r1;
            else return r2;
        }
    }

    private static CSVRecord maxTempInFile(FileResource file) {
        CSVParser parser = file.getCSVParser();
        CSVRecord hottestRow = null;
        for (CSVRecord currentRow : parser) {
            hottestRow = getHottestOfTwo(hottestRow, currentRow);
        }
        return hottestRow;
    }

    private static CSVRecord maxTempInManyFiles() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord hottestRow = null;
        for (File weatherFile : dr.selectedFiles()) {
            FileResource weatherFileResource = new FileResource(weatherFile);
            CSVRecord currentRow = maxTempInFile(weatherFileResource);
            hottestRow = getHottestOfTwo(hottestRow, currentRow);
        }
        return hottestRow;
    }

    private static CSVRecord getLowestHumidityOfTwo(CSVRecord r1, CSVRecord r2) {
        if (r1 == null) return r2;
        if (r1.get("Humidity").equals("N/A")) return r2;
        if (r2.get("Humidity").equals("N/A")) return r1;
        double r1hum = Double.parseDouble(r1.get("Humidity"));
        double r2hum = Double.parseDouble(r2.get("Humidity"));
        if (r1hum <= r2hum) return r1;
        else return r2;
    }

    private static CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestHumidityRow = null;
        for (CSVRecord currentRow : parser) {
            lowestHumidityRow = getLowestHumidityOfTwo(lowestHumidityRow, currentRow);
        }
        return lowestHumidityRow;
    }

    private static String lowestHumidityInManyFiles() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestHumidityRow = null;
        String lowestHumidityFile = "";
        for (File weatherFile : dr.selectedFiles()) {
            FileResource weatherFileResource = new FileResource(weatherFile);
            CSVRecord currentRow = lowestHumidityInFile(weatherFileResource.getCSVParser());
            if (lowestHumidityRow == null) {
                lowestHumidityRow = currentRow;
            }
            double lowestHumidity = Double.parseDouble(lowestHumidityRow.get("Humidity"));
            double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
            if (lowestHumidity > currentHumidity) {
                lowestHumidityFile = weatherFile.getName();
                lowestHumidityRow = currentRow;
                System.out.println("Humidity was " + lowestHumidityRow.get("Humidity") + " " + lowestHumidityRow.get("DateUTC"));
            }
        }
        return lowestHumidityFile;
    }

    private static double averageTemperatureInFile(CSVParser parser) {

        double number = 0;
        double sum = 0;
        double temp;
        for (CSVRecord r : parser) {
            number++;
            try {
                temp = Double.parseDouble(r.get("TemperatureF"));
            } catch (NumberFormatException e) {
                temp = 0;
            }
            if (temp != -9999) sum += temp;
        }

        return sum / number;
    }

    private static double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double number = 0;
        double sum = 0;
        double temp;
        for (CSVRecord record : parser) {
            double humidity = Double.parseDouble(record.get("Humidity"));
            if (humidity >= value) {
                number++;
                try {
                    temp = Double.parseDouble(record.get("TemperatureF"));
                } catch (NumberFormatException e) {
                    temp = 0;
                }
                if (temp != -9999) sum += temp;
            }
        }
        if (sum == 0) System.out.println("No temperatures with that humidity");
        return sum / number;
    }

    public static void main(String[] args) {
//        FileResource fr = new FileResource("resources/nc_weather/2014/weather-2014-05-01.csv");
//        System.out.println(minTempInFile(fr).get("TemperatureF"));
//
//        System.out.println("File with coldest weather: " + fileWithColdestTemperature());
//
//        FileResource fr = new FileResource("resources/nc_weather/2014/weather-2014-04-01.csv");
//        CSVParser parser = fr.getCSVParser();
//        System.out.println(lowestHumidityInFile(parser).get("DateUTC"));
//
//        System.out.println(lowestHumidityInManyFiles());
//
//        FileResource fr = new FileResource("resources/nc_weather/2014/weather-2014-06-01.csv");
//        CSVParser parser = fr.getCSVParser();
//        System.out.println(averageTemperatureInFile(parser));

        FileResource fr = new FileResource("resources/nc_weather/2014/weather-2014-03-30.csv");
        CSVParser parser = fr.getCSVParser();
        System.out.println(averageTemperatureWithHighHumidityInFile(parser, 80));
    }
}
