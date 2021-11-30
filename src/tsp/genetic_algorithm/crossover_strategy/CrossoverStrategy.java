package tsp.genetic_algorithm.crossover_strategy;

import tsp.genetic_algorithm.Individual;
import tsp.util.Pair;

public interface CrossoverStrategy {
    Pair<Individual, Individual> crossover(Individual parent1, Individual parent2);
}
