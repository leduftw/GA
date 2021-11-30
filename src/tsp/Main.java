package tsp;

import tsp.strategy.TravellingSalesmanProblemBruteForceStrategy;
import tsp.strategy.TravellingSalesmanProblemGeneticStrategy;
import tsp.strategy.TravellingSalesmanProblemStrategy;
import tsp.util.Util;

public class Main {

    public static final int NUMBER_OF_CITIES = 9;

    public static void main(String[] args) {
        TravellingSalesmanProblem travellingSalesmanProblem = new TravellingSalesmanProblem(NUMBER_OF_CITIES);
        TravellingSalesmanProblemStrategy bruteForceStrategy = new TravellingSalesmanProblemBruteForceStrategy();
        travellingSalesmanProblem.setTravellingSalesmanProblemStrategy(bruteForceStrategy);
        travellingSalesmanProblem.solve();

        System.out.println();
        Util.printAdjacencyMatrix(travellingSalesmanProblem.getAdjacencyMatrix());
        System.out.println();

        Path bruteForcePath = travellingSalesmanProblem.getBestPath();
        System.out.println(bruteForcePath);

        System.out.println("----------------------------------------------------------------------");
        System.out.println();

        TravellingSalesmanProblemStrategy geneticStrategy = new TravellingSalesmanProblemGeneticStrategy();
        travellingSalesmanProblem.setTravellingSalesmanProblemStrategy(geneticStrategy);
        travellingSalesmanProblem.solve();

        System.out.println();
        Util.printAdjacencyMatrix(travellingSalesmanProblem.getAdjacencyMatrix());
        System.out.println();

        Path geneticPath = travellingSalesmanProblem.getBestPath();
        System.out.println(geneticPath);
    }
}
