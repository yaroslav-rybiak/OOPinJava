package old;

import edu.duke.FileResource;
import edu.duke.StorageResource;

public class GeneAnalyser {

    private static int findMinimun(int a, int b, int c) {
        int min;
        if (a == -1 || (b != -1 && b < a)) min = b;
        else min = a;
        if (min == -1 || (c != -1 && c < min)) min = c;
        return min;
    }

    private static int findStopCodon(String dna, int startIndex, String stopCodon) {
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
        if (!dna.substring(startIndex + 3).contains(stopCodon)) {
            return -1;
        } else {
            while (dna.substring(startIndex + 3, stopIndex).length() % 3 != 0) {
                stopIndex = dna.indexOf(stopCodon, stopIndex + 1);
                if (stopIndex == -1) {
                    return stopIndex;
                }
            }
        }
        return stopIndex;
    }

    private static String findGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) return "";
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        if (taaIndex == tagIndex && tagIndex == tgaIndex && tgaIndex == dna.length()) return "";
        if (taaIndex == tagIndex && tagIndex == tgaIndex && tgaIndex == -1) return "";
        int stopIndex = findMinimun(taaIndex, tagIndex, tgaIndex);
        return dna.substring(startIndex, stopIndex + 3);
    }

    private static StorageResource getAllGenes(String dna) {
        StorageResource sr = new StorageResource();
        while (true) {
            String gene = findGene(dna);
            if (gene.equals("")) break;
            sr.add(gene);
            dna = dna.substring(dna.indexOf(gene) + gene.length());
        }
        return sr;
    }

    private static double cgRatio(String dna) {
        int cgAmount = 0;
        for (char c : dna.toCharArray()) {
            if (c == 'C' || c == 'G') cgAmount++;
        }

        return (double) cgAmount / dna.length();
    }

    private static void processGenes(StorageResource sr) {
        int i = 0;
        int j = 0;
        int k = 0;
        for (String gene : sr.data()) {
            if (gene.length() > 60) {
                i++;
            }
            if (cgRatio(gene) > 0.35) {
                j++;
            }
            if (gene.length() > k) k = gene.length();
        }
        System.out.println("Number of genes longer than 60: " + i);
        System.out.println("Number of genes with cgRatio > 0.35: " + j);
        System.out.println("Longest gene: " + k);
    }

    public static void main(String[] args) {
        FileResource fr = new FileResource("resources/brca1line.fa");
        String dna = fr.asString().toUpperCase();
        StorageResource sr = getAllGenes(dna);
        processGenes(sr);
    }
}
