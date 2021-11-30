package tsp.strategy;

import tsp.Path;
import tsp.TravellingSalesmanProblem;

public interface TravellingSalesmanProblemStrategy {
    Path solve(TravellingSalesmanProblem travellingSalesmanProblem);
}
