package tsp.genetic_algorithm.selection_strategy;

import tsp.genetic_algorithm.Individual;
import tsp.genetic_algorithm.Population;

import java.util.Arrays;

public class RouletteWheelSelectionStrategy implements SelectionStrategy {
    private double[] cumulativeProbabilityFunction;

    @Override
    public Population select(Population population) {
        calculateCumulativeProbabilityFunction(population);
        Population selected = new Population(population.getTravellingSalesmanProblem());
        selected.setSelectionStrategy(population.getSelectionStrategy());
        selected.setCrossoverStrategy(population.getCrossoverStrategy());

        // Selection is done in  O(nlogn) time
        for (int i = 0; i < population.size(); i++) {
            selected.addIndividual(new Individual(mapProbabilityToIndividual(population, Math.random())));
        }

        return selected;
    }

    private void calculateCumulativeProbabilityFunction(Population population) {
        // Roulette Wheel gives higher probabilities of surviving for those individuals with higher fitness!
        double fitnessSum = 0;
        for (int i = 0; i < population.size(); i++) {
            fitnessSum += population.getIndividual(i).getFitness();
        }

        cumulativeProbabilityFunction = new double[population.size()];
        cumulativeProbabilityFunction[0] = population.getIndividual(0).getFitness() / fitnessSum;
        for (int i = 1; i < population.size() - 1; i++) {
            cumulativeProbabilityFunction[i] = cumulativeProbabilityFunction[i - 1] +
                    population.getIndividual(i).getFitness() / fitnessSum;
        }

        cumulativeProbabilityFunction[population.size() - 1] = 1;
    }

    private Individual mapProbabilityToIndividual(Population population, double probability) {
        int ind = Arrays.binarySearch(cumulativeProbabilityFunction, probability);
        if (ind < 0) {
            ind = -ind - 1;
        }

        return population.getIndividual(ind);
    }
}
