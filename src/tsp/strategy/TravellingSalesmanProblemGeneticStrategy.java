package tsp.strategy;

import tsp.Path;
import tsp.TravellingSalesmanProblem;
import tsp.genetic_algorithm.Individual;
import tsp.genetic_algorithm.Population;
import tsp.genetic_algorithm.crossover_strategy.OrderedCrossoverStrategy;
import tsp.genetic_algorithm.mutation_strategy.MutationStrategy;
import tsp.genetic_algorithm.mutation_strategy.OneSwapMutationStrategy;
import tsp.genetic_algorithm.selection_strategy.RouletteWheelSelectionStrategy;

public class TravellingSalesmanProblemGeneticStrategy implements TravellingSalesmanProblemStrategy {
    private static final int SIZE_OF_POPULATION = 100;
    private static final int NUMBER_OF_GENERATIONS = 100;
    private static final double PROBABILITY_OF_MUTATION = 0.01;

    @Override
    public Path solve(TravellingSalesmanProblem travellingSalesmanProblem) {
        Population population = new Population(travellingSalesmanProblem, SIZE_OF_POPULATION);

        population.setSelectionStrategy(new RouletteWheelSelectionStrategy());

        population.setCrossoverStrategy(new OrderedCrossoverStrategy());

        MutationStrategy mutationStrategy = new OneSwapMutationStrategy();
        for (Individual individual : population) {
            individual.setMutationStrategy(mutationStrategy);
        }

        Population currentGeneration = population;
        for (int i = 0; i < NUMBER_OF_GENERATIONS; i++) {
            // Selection
            Population afterNaturalSelection = currentGeneration.naturalSelection();

            // Crossover
            currentGeneration = afterNaturalSelection.reproduce();

            // Mutation
            for (Individual individual : currentGeneration) {
                if (Math.random() < PROBABILITY_OF_MUTATION) {
                    individual.mutate();
                }
            }

            System.out.println("Generation #" + (i + 1) + ":");
            System.out.println(currentGeneration);
        }

        return currentGeneration.getBestIndividual().getPath();
    }
}
