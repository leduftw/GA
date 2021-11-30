package tsp.genetic_algorithm;

import tsp.Path;
import tsp.TravellingSalesmanProblem;
import tsp.genetic_algorithm.mutation_strategy.MutationStrategy;

public class Individual {
    private Path path;

    private MutationStrategy mutationStrategy;

    public Individual(TravellingSalesmanProblem travellingSalesmanProblem) {
        path = new Path(travellingSalesmanProblem);
    }

    public Individual(Path path) {
        this.path = path;
    }

    public Individual(Individual toClone) {
        path = toClone.getPath();
        mutationStrategy = toClone.getMutationStrategy();
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public double getFitness() {
        return 1.0 / path.getDistance();
    }

    public MutationStrategy getMutationStrategy() {
        return mutationStrategy;
    }

    public void setMutationStrategy(MutationStrategy mutationStrategy) {
        this.mutationStrategy = mutationStrategy;
    }

    public void mutate() {
        mutationStrategy.mutate(this);
    }
}
