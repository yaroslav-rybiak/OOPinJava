package old;

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class CountryCSVAnalyser {

    private static void countryInfo(CSVParser parser, String country) {
        int i = 0;
        for (CSVRecord record : parser) {
            if (record.get("Country").equals(country)) {
                i++;
                System.out.println(String.format("%s: %s: %s", country, record.get("Exports"), record.get("Value (dollars)")));
            }
        }
        if (i == 0) System.out.println("No info");
    }

    private static void listExportersTwoProducts(CSVParser parser, String product1, String prouct2) {
        int i = 0;
        for (CSVRecord record : parser) {
            if (record.get("Exports").contains(product1) && record.get("Exports").contains(product1)) {
                System.out.println(String.format("%s. %s exports %s and %s", ++i, record.get("Country"), product1, prouct2));
            }
        }
    }

    private static void bigExporters(CSVParser parser, String dollars) {
        int i = 0;
        for (CSVRecord record : parser) {
            if (record.get("Value (dollars)").length() >= dollars.length()) {
                System.out.println(String.format("%s. %s", ++i, record.get("Country")));
            }
        }
    }

    private static void numberOfExporters(CSVParser parser, String item) {
        int i = 0;
        for (CSVRecord record : parser) {
            if (record.get("Exports").contains(item)) {
                i++;
            }
        }
        System.out.println(String.format("%s is exported by %s countries", item, i));
    }


    public static void main(String[] args) {
        File file = new File("resources/exportdata.csv");
        FileResource fr = new FileResource(file);

        countryInfo(fr.getCSVParser(), "Narnia");
        countryInfo(fr.getCSVParser(), "China");
        bigExporters(fr.getCSVParser(), "$999,999,999,999");
        bigExporters(fr.getCSVParser(), "$100,000,000,000");
        listExportersTwoProducts(fr.getCSVParser(), "computers", "coffee");
        listExportersTwoProducts(fr.getCSVParser(), "diamonds", "machinery");
        numberOfExporters(fr.getCSVParser(), "gold");
        numberOfExporters(fr.getCSVParser(), "sand");
        countryInfo(fr.getCSVParser(), "Nauru");
        countryInfo(fr.getCSVParser(), "Ukraine");

    }
}
