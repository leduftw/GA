package tsp.genetic_algorithm.mutation_strategy;

import tsp.genetic_algorithm.Individual;

public interface MutationStrategy {
    void mutate(Individual individual);
}
