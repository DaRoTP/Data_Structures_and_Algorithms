import java.io.*;

import java.util.Scanner;

public class FileGetter {
    public static int[][] getElemFromFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("matrix.txt"));

        int[][] matrix = new int[getMatrixSize()][getMatrixSize()];
        int j = 0;
        String line = scanner.nextLine();
        while(scanner.hasNextLine()){
            line = scanner.nextLine();
            for(int i = 0; i < getMatrixSize(); i++){
                matrix[i][j] = Character.getNumericValue(line.charAt(i));
            }
            j++;
        }

        return matrix;
    }

    public static Graph getEdges(int[][] matrix) throws FileNotFoundException {
        Graph g = new Graph(getMatrixSize() + 1);
        for (int i = 0; i< getMatrixSize(); i++){
            for(int k = 0; k < getMatrixSize(); k++){
                if(matrix[i][k] == 1){
                    g.addEdge(i + 1, k + 1);
                }
            }
        }
        return g;
    }

    public static int getMatrixSize() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("matrix.txt"));
        String size = sc.nextLine();
        return Integer.parseInt(size);
    }
}