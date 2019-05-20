import java.io.FileNotFoundException;
import java.io.*;
import java.util.Scanner;


class Main {

    static String getElemFromFile(String fileName) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder("");
        Scanner scanner = new Scanner(new File(fileName));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            sb.append(line);
        }

        return sb.toString();
    }


    public static void main(String[] args) throws FileNotFoundException {
        long timeStart_Naive, timeStart_KMP, timeStart_RK, timeEnd__Naive, timeEnd_KMP, timeEnd_RK;

        String patter = getElemFromFile("wzorzec.txt").replaceAll("\\s+","");
        String text = getElemFromFile("tekst.txt").replaceAll("\\s+","");

        String patter2 = getElemFromFile("wzorzec1.txt").replaceAll("\\s+","");
        String text2 = getElemFromFile("tekst1.txt").replaceAll("\\s+","");



        Naive n = new Naive(patter, text);
        System.out.println("Naive");
        //time measurement
        timeStart_Naive = System.nanoTime();
        n.find_pattern_Naive();
        timeEnd__Naive = System.nanoTime();

        System.out.println("========================>");

        Knuth_Morris_Pratt kmp = new Knuth_Morris_Pratt(patter, text);
        System.out.println("KMP");
        //time measurement
        timeStart_KMP = System.nanoTime();
        kmp.KMPSearch();
        timeEnd_KMP = System.nanoTime();

        System.out.println("========================>");

        RabinKarp rk = new RabinKarp(patter, text);
        System.out.println("RK");
        //time measurement
        timeStart_RK = System.nanoTime();
        rk.RKsearch(101);
        timeEnd_RK = System.nanoTime();


        System.out.println("\n\n\t\t--- TIME --:");
        System.out.println("Naive: " + (timeEnd__Naive - timeStart_Naive));
        System.out.println("Knuth Morris Pratt : " + (timeEnd_KMP - timeStart_KMP));
        System.out.println("Rabin Karp: " + (timeEnd_RK - timeStart_RK));


        //===================================================================================
        System.out.println("\n\n\t\t=========== Pattern 2 =============");

        n = new Naive(patter2, text2);
        System.out.println("Naive");
        //time measurement
        timeStart_Naive = System.nanoTime();
        n.find_pattern_Naive();
        timeEnd__Naive = System.nanoTime();

        System.out.println("========================>");

        kmp = new Knuth_Morris_Pratt(patter2, text2);
        System.out.println("KMP");
        //time measurement
        timeStart_KMP = System.nanoTime();
        kmp.KMPSearch();
        timeEnd_KMP = System.nanoTime();

        System.out.println("========================>");

        rk = new RabinKarp(patter2, text2);
        System.out.println("RK");
        //time measurement
        timeStart_RK = System.nanoTime();
        rk.RKsearch(101);
        timeEnd_RK = System.nanoTime();



        System.out.println("\n\n\t\t--- TIME --:");
        System.out.println("Naive: " + (timeEnd__Naive - timeStart_Naive));
        System.out.println("Knuth Morris Pratt : " + (timeEnd_KMP - timeStart_KMP));
        System.out.println("Rabin Karp: " + (timeEnd_RK - timeStart_RK));


    }
}