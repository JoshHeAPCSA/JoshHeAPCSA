##pseudocode.txt
1. Define constants: MINIMUM_LENGTH, CG_PERCENTAGE, NUM_NUCLEOTIDES, CODON_LENGTH
2. In main method:
    a. Print welcome message.
    b. Call startAnalysis("DNA.txt")
3. In startAnalysis method (String filename):
    a. Create a scanner to read the file.
    b. While the file lines:
        i. Read the sequence name (String).
        ii. Read the DNA sequence (String).
        iii. Process the sequence by calling helper methods.
            - countNucleotides(dna) -> returns int[] counts
            - calculateMassPercentage(counts) -> return double[] percentages
            - getCodons(dna) -> return String[] codons
            - isProtein(dna, counts) -> return boolean
        iv. Print output in the required format.
    c. Close the file Scanner.
4. countNucleotides(dna):
    - Initialize int[] counts of size 4 -> [0,0,0,0] for [A,C,G,T]
    - Loop over each character in dna:
        if A -> counts[0]++, if C -> counts[1]++, if G -> counts[2]++, if T -> counts[3]++.
    - Return counts
5. calculateMassPercentage(int[] counts):
    - sum = counts[0]*A_Mass + counts[1]*C_Mass + counts[2]*G_Mass + counts[3]*T_Mass
    - For each nucleotide i:
        percentage[i] = (counts[i] * Mass[i]) / sum * 100
    - return percentages
6. getCodons(dna):
    ##- Remove any extraneous characters if needed
    - Create a String[] codons of length dna.length() / 3
    - For i in range 0 to codons.length - 1:
        codons[i] = dna.substring(3*i, 3*i + 3)
    - return codons
7. isProtein(dna, counts):
    - Check if dna starts with "ATG"
    - Check if dna ends with "TAA" or "TAG" or "TGA"
    - Check if number of codons >= MINIMUM_LENGTH
    - Calculate CG mass percentage -> (counts[C] + counts[G]) / total nucleotides mass * 100 or from the mass percentages array directly
    - return true if all conditions are met, else false





## DNA.java
import java.io.file;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Author:
 * Class Period:
 * Date:
 * Description:
 * This program reads a DNA data file, processes each DNA sequence, and outputs the analysis:
 *   1) Count of each nucleotide
 *   2) Mass percentage of each nucleotide
 *   3) Lists of codons (groups of 3 nucleotides)
 *   4) Whether the sequence encodes a isProtein
 */

public class DNA {
    
    //Class constants
    public static final int MINIMUM_LENGTH = 4;
    public static final int CG_PERCENTAGE = 30;
    public static final int NUM_NUCLEOTIDES = 4;
    public static final int CODON_LENGTH = 3;

    //Mass of each nucleotide in the order A, C, G, T
    public static final double[] MASS = {
        135.128 // A
        111.103 // C
        151.128 // G
        127.107 // T
    };

    public static void main(String[] args) {
        System.out.printIn("Welcome to the DNA sequencer!");
        System.out.printIn("Here are the results for DNA.txt:\n");

        startAnalysis("DNA.txt");
    }
    public static void startAnalysis(String fileName) {
        File file = new File(fileName);
        Scanner input = null;

        try {
            input = new Scanner(file);
            while (input.hasNextLine()) {
                String seqName = input.nextLine().trim();
                if (!input.hasNextLine()) {
                    break;
                }
                String dna = input.nextLine().trim();
                //1. Count nucleotides
                int[] counts = countNucleotides(dna);
                //2. Calculate mass percentages
                double[] percentages = calculateMassPercentages(counts);
                //3. Split into codons
                String[] codons = getCodons(dna);
                //4. Check if it encodes a protein
                boolean isProteinSeq = isProtein(dna, counts);
                //Print the standard output
                System.out.printIn("Name:" + seqName);
                System.out.printIn("Nucleotides:" + dna);
                System.out.printIn("Nucleotide Counts: [" + counts[0] + "," + counts[1] + "," + counts[2] + "," + counts[3] + "]");
                System.out.printIn("Mass Percentages: [" + String.format("%.2f", percentages[0]) + "," + String.format("%.2f", percentages[1]) + "," + String.format("%.2f", percentages[2]) + "," + String.format("%.2f", percentages[3]) + "]");
                System.out.printIn("Codons: [");
                for (int i = 0; i < codons.length; i++) {
                    System.out.print(codons[i]);
                    if (i < codons.length - 1) {
                        System.out.print(",");
                    }
                }
                System.out.printIn("]");
                System.out.printIn("Encodes a Protein:" + (isProteinSeq ? "Yes" : "No"));

                //Extension: Reverse Complement Analysis
                String reverseComp = getReverseComplement(dna);
                int[] rcCounts = countNucleotides(reverseComp);
                double[] rcPercentages = calculateMassPercentages(rcCounts);
                String[] rcCodons = getCodons(reverseComp);
                boolean isProteinRC = isProtein(reverseComp, rcCounts);

                System.out.printIn("\nReverse Complement:" + reverseComp);
                System.out.printIn("RC Nucleotide Counts: [" + rcCounts[0] + "," + rcCounts[1] + "," + rcCounts[2] + "," + rcCounts[3] + "]");
                System.out.printIn("RC Mass Percentages: [" + String.format("%.2f", rcPercentages[0]) + "," + String.format("%.2f", rcPercentages[1]) + "," + String.format("%.2f", rcPercentages[2]) + "," + String.format("%.2f", rcPercentages[3]) + "]");
                System.out.print("RC Codons: [");
                for (int i = 0; i < rcCodons.length; i++) {
                    System.out.print(rcCodons[i]);
                    if (i < rcCodons.length - 1) {
                        System.out.print(",");
                    }
                }
                System.out.printIn("]");
                System.out.printIn("Encodes a Protein (RC) + (isProteinRC ? "Yes" : "No"));
            }
        } Catch (FileNotFoundException e) {
            System.out.printIn("Could not find file:" + fileName);
        } finally {
            if (input != null) {
                input.close();
            }
        }
    }

    public static int[] countNucleotides(String dna) {
        int counts = new int[NUM_NUCLEOTIDES];
        for (int i = 0; i < dna.length(); i++) {
            char ch = dna.charAt(i);
            if (ch == 'A') {
                counts[0]++;
            } else if (ch == 'C') {
                counts[1]++;
            } else if (ch == 'G') {
                counts[2]++;
            } else if (ch == 'T') {
                counts[3]++;
            }
        }
        return counts;
    }

    public static double[] calculateMassPercentages(int[] counts) {
        double totalMass = 0.0;
        for (int i = 0; i < NUM_NUCLEOTIDES; i++) {
            if (totalMass == 0) {
                percentages[i] = 0.0;
            } else {
                percentages[i] = (counts[i] * MASS[i]) / totalMass * 100.0;
            }
        }
        return percentages;
    }

    public static String[] getCodons(String dna) {
        int codonCount = dna.length() / CODON_LENGTH;
        String[] codons = new String[codonCount];
        for (int i = 0; i < codonCount; i++) {
            codons[i] = dna.substring(i * CODON_LENGTH, i * CODON_LENGTH + CODON_LENGTH);
        }
        return codons;
    }

    public static boolean isProtein(String dna, int[] counts) {
        if (!dna.startsWith("ATG")) {
            return false;
        }
        if (dna.length() < 3) {
            return false;
        }
        String last3 = dna.substring(dna.length() - 3);
        if (!(last3.equals("TAA") || last3.equals("TAG") || last3.equals("TGA"))) {
            return false;
        }
        int codonCount = dna.length() / CODON_LENGTH;
        if (codonCount < MINIMUM_LENGTH) {
            return false;
        }
        double[] percentages = calculateMassPercentages(counts);
        double cgMassPct = percentages[1] + percentages[2];
        return (cgMassPct >= CG_PERCENTAGE);
    }

    /**
     * getReverseComplement (Extension):
     * - Computes the reverse complement of the given DNA string.
     * Each nucleotide is replaced by its complement:
     * A -> T
     * T -> A
     * C -> G
     * G -> C  
     * The the entire string is reversed.
     */
    
    public static String getReverseComplement(String dna) {
        StringBuilder complement = new StringBuilder();
        for (int i = 0; i < dna.length(); i++) {
            char ch = dna.charAt(i);
            if (ch == "A") {
                complement.append("T");
            } else if (ch == "T") {
                complement.append("A");
            } else if (ch == "G") {
                complement.append("C");
            } else if (ch == "C") {
                complement.append("G");
            } else {
                complement.append("ch");
            }
        }
        complement.reverse();
        return complement.toString();
    }
}