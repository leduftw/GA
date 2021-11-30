package tsp.genetic_algorithm.selection_strategy;

import tsp.genetic_algorithm.Population;

public interface SelectionStrategy {
    Population select(Population population);
}
