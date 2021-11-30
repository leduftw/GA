package tsp;

import tsp.strategy.TravellingSalesmanProblemStrategy;

public class TravellingSalesmanProblem {
    private TravellingSalesmanProblemStrategy travellingSalesmanProblemStrategy;

    private int numberOfCities;

    private int[][] adjacencyMatrix;

    private Path bestPath;

    public TravellingSalesmanProblem(int numberOfCities) {
        this.numberOfCities = numberOfCities;

        // Initializes all elements to zero
        adjacencyMatrix = new int[numberOfCities][numberOfCities];

        for (int i = 0; i < numberOfCities; i++) {
            for (int j = 0; j < i; j++) {
                adjacencyMatrix[i][j] = (int) (Math.random() * 500 + 1);
                adjacencyMatrix[j][i] = adjacencyMatrix[i][j];
            }
        }
    }

    public int getNumberOfCities() {
        return numberOfCities;
    }

    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public int getDistance(int firstCity, int secondCity) {
        return adjacencyMatrix[firstCity][secondCity];
    }

    public void setTravellingSalesmanProblemStrategy(TravellingSalesmanProblemStrategy travellingSalesmanProblemStrategy) {
        this.travellingSalesmanProblemStrategy = travellingSalesmanProblemStrategy;
    }

    public void solve() {
        bestPath = travellingSalesmanProblemStrategy.solve(this);
    }

    public Path getBestPath() {
        return bestPath;
    }
}
