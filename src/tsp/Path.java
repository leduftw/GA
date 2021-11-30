package tsp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Path {
    TravellingSalesmanProblem travellingSalesmanProblem;

    private List<Integer> cities = new ArrayList<>();

    public static Path getOrderedPath(TravellingSalesmanProblem travellingSalesmanProblem) {
        int numberOfCities = travellingSalesmanProblem.getNumberOfCities();
        int[] cities = new int[numberOfCities];
        for (int i = 0; i < numberOfCities; i++) {
            cities[i] = i;
        }

        return new Path(travellingSalesmanProblem, cities);
    }

    public Path(TravellingSalesmanProblem travellingSalesmanProblem) {
        this.travellingSalesmanProblem = travellingSalesmanProblem;
    }

    public Path(TravellingSalesmanProblem travellingSalesmanProblem, int[] cities) {
        this.travellingSalesmanProblem = travellingSalesmanProblem;
        for (int city : cities) {
            addCity(city);
        }
    }

    public Path(TravellingSalesmanProblem travellingSalesmanProblem, List<Integer> cities) {
        this.travellingSalesmanProblem = travellingSalesmanProblem;
        for (int city : cities) {
            addCity(city);
        }
    }

    public Path(Path toClone) {
        this(toClone.getTravellingSalesmanProblem(), toClone.getCities());
    }

    public void addCity(int city) {
        cities.add(city);
    }

    public int getCity(int i) {
        return cities.get(i);
    }

    public List<Integer> getCities() {
        return cities;
    }

    public int getDistance() {
        int distance = 0;
        for (int i = 0; i < cities.size() - 1; i++) {
            distance += travellingSalesmanProblem.getDistance(cities.get(i), cities.get(i + 1));
        }

        return distance;
    }

    public TravellingSalesmanProblem getTravellingSalesmanProblem() {
        return travellingSalesmanProblem;
    }

    public int size() {
        return cities.size();
    }

    // https://www.quora.com/How-would-you-explain-an-algorithm-that-generates-permutations-using-lexicographic-ordering
    public boolean nextPermutation() {
        int x = -1;
        for (int i = 0; i < size() - 1; i++) {
            if (getCity(i) < getCity(i + 1)) {
                x = i;
            }
        }

        if (x == -1) {
            return false;
        }

        int y = -1;
        for (int i = 0; i < size(); i++) {
            if (getCity(i) > getCity(x)) {
                y = i;
            }
        }

        swapCities(x, y);

        for (int i = x + 1, j = size() - 1; i < j; i++, j--) {
            swapCities(i, j);
        }

        return true;
    }

    public Path shuffle() {
        Random random = new Random();

        for (int i = size() - 1; i >= 1; i--) {
            int j = random.nextInt(i + 1);
            swapCities(i, j);
        }

        return this;
    }

    public Path swapCities(int i, int j) {
        int temp = cities.get(i);
        cities.set(i, cities.get(j));
        cities.set(j, temp);

        return this;
    }

    public Path slice(int startInclusive, int endExclusive) {
        Path sliced = new Path(travellingSalesmanProblem);
        for (int i = startInclusive; i < endExclusive; i++) {
            sliced.addCity(getCity(i));
        }

        return sliced;
    }

    public Path concatenate(Path other) {
        Path concatenated = new Path(this);
        for (int i = 0; i < other.size(); i++) {
            concatenated.addCity(other.getCity(i));
        }

        return concatenated;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Path: [" + getCity(0));
        for (int i = 1; i < size(); i++) {
            stringBuilder.append(", " + getCity(i));
        }

        stringBuilder.append("]\n");
        stringBuilder.append("Distance: " + getDistance());

        return stringBuilder.toString();
    }
}
