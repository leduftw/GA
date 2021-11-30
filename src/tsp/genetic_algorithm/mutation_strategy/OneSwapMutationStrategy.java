package tsp.genetic_algorithm.mutation_strategy;

import tsp.Path;
import tsp.genetic_algorithm.Individual;

import java.util.Random;

public class OneSwapMutationStrategy implements MutationStrategy {
    @Override
    public void mutate(Individual individual) {
        Individual clone = new Individual(individual);

        Path path = clone.getPath();

        Random random = new Random();
        int i = random.nextInt(path.size());
        int j = random.nextInt(path.size());

        path.swapCities(i, j);

        if (path.getDistance() < individual.getPath().getDistance()) {
            individual.setPath(path);
        }
    }
}
