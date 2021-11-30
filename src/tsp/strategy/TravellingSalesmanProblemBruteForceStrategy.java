package tsp.strategy;

import tsp.Path;
import tsp.TravellingSalesmanProblem;

public class TravellingSalesmanProblemBruteForceStrategy implements TravellingSalesmanProblemStrategy {
    @Override
    public Path solve(TravellingSalesmanProblem travellingSalesmanProblem) {
        Path currentPath = Path.getOrderedPath(travellingSalesmanProblem);
        Path bestPath = null;

        int checked = 0;
        do {
            checked++;
            if (currentPath.getDistance() < (bestPath != null ? bestPath.getDistance() : Integer.MAX_VALUE)) {
                bestPath = new Path(currentPath);
                System.out.println(bestPath);
            }
        } while (currentPath.nextPermutation());

        System.out.println("\nBrute force search finished.");
        System.out.println("Checked " + checked + " paths.");

        return bestPath;
    }
}
