package old;

import java.util.HashMap;
import java.util.Map;

public class CodonCount {

    private HashMap<String, Integer> codons;

    public CodonCount() {
        codons = new HashMap<>();
    }

    void buildCodonMap(int start, String dna) {
        //start can be 0, 1 or 2
        codons.clear();
        String codon;
        for (int i = start; i <= dna.length() -3; i+=3) {
            codon = dna.substring(i, i+3);
            if(!codons.containsKey(codon)) {
                codons.put(codon, 1);
            } else {
                codons.put(codon, codons.get(codon) + 1);
            }
        }
    }

    void printCodonCounts(int min, int max) {
        for (Map.Entry e: codons.entrySet()) {
            int current = Integer.parseInt(e.getValue().toString());
            if(current >= min && current <= max) {
                System.out.println(e.getKey() + " " + e.getValue());
            }
        }
        System.out.println("Total codons: " + codons.size());
    }

    void printCodonCounts() {
        for (Map.Entry e: codons.entrySet()) {
            System.out.println(e.getValue() + " " + e.getKey());
        }
        System.out.println("Total codons: " + codons.size());
    }

    void getMostCommonCodon() {
        int max = 0;
        String mostCommonCodon = "";
        for (Map.Entry e: codons.entrySet()) {
            if(Integer.parseInt(e.getValue().toString()) > max) {
                max = Integer.parseInt(e.getValue().toString());
                mostCommonCodon = e.getKey().toString();
            }
        }
        System.out.println("Most commom codon is: " + mostCommonCodon + " " + max + " times");
    }

    public static void main(String[] args) {
        CodonCount cc = new CodonCount();
        cc.buildCodonMap(0, "CGTTCAAGTTCAA");
        cc.printCodonCounts();
        cc.printCodonCounts(2, 3);
        cc.getMostCommonCodon();
    }
}
