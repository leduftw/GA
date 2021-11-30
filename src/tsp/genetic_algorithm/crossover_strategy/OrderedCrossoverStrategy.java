package tsp.genetic_algorithm.crossover_strategy;

import tsp.Path;
import tsp.genetic_algorithm.Individual;
import tsp.util.Pair;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class OrderedCrossoverStrategy implements CrossoverStrategy {
    @Override
    public Pair<Individual, Individual> crossover(Individual parent1, Individual parent2) {
        Path chromosomesParent1 = parent1.getPath();
        Path chromosomesParent2 = parent2.getPath();

        Random random = new Random();
        int firstCrossoverPoint = random.nextInt(chromosomesParent1.size());
        int secondCrossoverPoint = firstCrossoverPoint +
                random.nextInt(chromosomesParent1.size() - firstCrossoverPoint);

        Individual child1 = new Individual(parent1.getPath().getTravellingSalesmanProblem());
        child1.setMutationStrategy(parent1.getMutationStrategy());

        Path middleChromosomesChild1 = chromosomesParent2.slice(firstCrossoverPoint, secondCrossoverPoint + 1);
        Set<Integer> middleChromosomesChild1Set = new HashSet<>();
        for (int i = 0; i < middleChromosomesChild1.size(); i++) {
            middleChromosomesChild1Set.add(middleChromosomesChild1.getCity(i));
        }

        Path rightChromosomesChild1 = new Path(parent1.getPath().getTravellingSalesmanProblem());
        int lastPosition = 0, filled = 0;
        while (filled < chromosomesParent1.size() - secondCrossoverPoint - 1) {
            if (!middleChromosomesChild1Set.contains(chromosomesParent1.getCity(lastPosition))) {
                rightChromosomesChild1.addCity(chromosomesParent1.getCity(lastPosition));
                filled++;
            }

            lastPosition++;
        }

        Path leftChromosomesChild1 = new Path(parent1.getPath().getTravellingSalesmanProblem());
        filled = 0;
        while (filled < firstCrossoverPoint) {
            if (!middleChromosomesChild1Set.contains(chromosomesParent1.getCity(lastPosition))) {
                leftChromosomesChild1.addCity(chromosomesParent1.getCity(lastPosition));
                filled++;
            }

            lastPosition++;
        }

        Path chromosomesChild1 = leftChromosomesChild1.concatenate(middleChromosomesChild1).concatenate(rightChromosomesChild1);
        child1.setPath(chromosomesChild1);

        Individual child2 = new Individual(parent1.getPath().getTravellingSalesmanProblem());
        child2.setMutationStrategy(parent2.getMutationStrategy());

        Path middleChromosomesChild2 = chromosomesParent1.slice(firstCrossoverPoint, secondCrossoverPoint + 1);
        Set<Integer> middleChromosomesChild2Set = new HashSet<>();
        for (int i = 0; i < middleChromosomesChild2.size(); i++) {
            middleChromosomesChild2Set.add(middleChromosomesChild2.getCity(i));
        }

        Path rightChromosomesChild2 = new Path(parent1.getPath().getTravellingSalesmanProblem());
        lastPosition = 0;
        filled = 0;
        while (filled < chromosomesParent2.size() - secondCrossoverPoint - 1) {
            if (!middleChromosomesChild2Set.contains(chromosomesParent2.getCity(lastPosition))) {
                rightChromosomesChild2.addCity(chromosomesParent2.getCity(lastPosition));
                filled++;
            }

            lastPosition++;
        }

        Path leftChromosomesChild2 = new Path(parent1.getPath().getTravellingSalesmanProblem());
        filled = 0;
        while (filled < firstCrossoverPoint) {
            if (!middleChromosomesChild2Set.contains(chromosomesParent2.getCity(lastPosition))) {
                leftChromosomesChild2.addCity(chromosomesParent2.getCity(lastPosition));
                filled++;
            }

            lastPosition++;
        }

        Path chromosomesChild2 = leftChromosomesChild2.concatenate(middleChromosomesChild2).concatenate(rightChromosomesChild2);
        child2.setPath(chromosomesChild2);

        return new Pair<>(child1, child2);
    }
}
