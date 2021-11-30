package tsp.genetic_algorithm;

import tsp.Path;
import tsp.TravellingSalesmanProblem;
import tsp.genetic_algorithm.crossover_strategy.CrossoverStrategy;
import tsp.genetic_algorithm.selection_strategy.SelectionStrategy;
import tsp.util.Pair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Population implements Iterable<Individual> {
    private TravellingSalesmanProblem travellingSalesmanProblem;

    private List<Individual> individuals = new ArrayList<>();

    private Individual bestIndividual;

    private SelectionStrategy selectionStrategy;

    private CrossoverStrategy crossoverStrategy;

    public Population(TravellingSalesmanProblem travellingSalesmanProblem) {
        this.travellingSalesmanProblem = travellingSalesmanProblem;
    }

    public Population(TravellingSalesmanProblem travellingSalesmanProblem, int sizeOfPopulation) {
        this.travellingSalesmanProblem = travellingSalesmanProblem;
        Individual initialIndividual = new Individual(Path.getOrderedPath(travellingSalesmanProblem));
        for (int i = 0; i < sizeOfPopulation; i++) {
            addIndividual(new Individual(new Path(initialIndividual.getPath()).shuffle()));
        }
    }

    public TravellingSalesmanProblem getTravellingSalesmanProblem() {
        return travellingSalesmanProblem;
    }

    public void addIndividual(Individual individual) {
        if (bestIndividual == null || individual.getFitness() > bestIndividual.getFitness()) {
            bestIndividual = individual;
        }

        individuals.add(individual);
    }

    public Individual getBestIndividual() {
        return bestIndividual;
    }

    public Individual getIndividual(int i) {
        return individuals.get(i);
    }

    public int size() {
        return individuals.size();
    }

    public SelectionStrategy getSelectionStrategy() {
        return selectionStrategy;
    }

    public void setSelectionStrategy(SelectionStrategy selectionStrategy) {
        this.selectionStrategy = selectionStrategy;
    }

    public CrossoverStrategy getCrossoverStrategy() {
        return crossoverStrategy;
    }

    public void setCrossoverStrategy(CrossoverStrategy crossoverStrategy) {
        this.crossoverStrategy = crossoverStrategy;
    }

    public Population naturalSelection() {
        return selectionStrategy.select(this);
    }

    public Population reproduce() {
        Population newGeneration = new Population(travellingSalesmanProblem);
        newGeneration.setSelectionStrategy(getSelectionStrategy());
        newGeneration.setCrossoverStrategy(getCrossoverStrategy());

        Random random = new Random();
        while (newGeneration.size() != size()) {
            Individual mother = getIndividual(random.nextInt(size()));
            Individual father = null;
            do {
                father = getIndividual(random.nextInt(size()));
            } while (father == mother);

            Pair<Individual, Individual> children = crossoverStrategy.crossover(mother, father);

            newGeneration.addIndividual(children.getFirst());
            newGeneration.addIndividual(children.getSecond());
        }

        return newGeneration;
    }

    public double getAverageFitness() {
        double fitnessSum = 0;
        for (Individual individual : individuals) {
            fitnessSum += individual.getFitness();
        }

        return fitnessSum / individuals.size();
    }

    @Override
    public String toString() {
        return "Population size: " + size() + "\n" +
                "Best individual:\n" +
                getBestIndividual().getPath().toString() + "\n" +
                "Best fitness: " + getBestIndividual().getFitness() + "\n" +
                "Average fitness: " + getAverageFitness() + "\n" +
                "-------------------------------------------------";
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Individual> iterator() {
        return individuals.iterator();
    }
}
