package tsp.util;

public class Util {
    public static void printAdjacencyMatrix(int[][] adjacencyMatrix) {
        System.out.println("Adjacency matrix:");
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                System.out.print(adjacencyMatrix[i][j] + "\t");
            }

            System.out.println();
        }
    }
}
