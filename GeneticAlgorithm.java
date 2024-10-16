import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm {
    private List<Chromosome> population;
    private Random random = new Random();
    private FitnessEvaluator fitnessEvaluator = new FitnessEvaluator();

    public GeneticAlgorithm(List<Chromosome> initialPopulation) {
        this.population = initialPopulation;
    }

    // Selection: Pick the best-performing chromosome based on fitness
    public Chromosome selectParent(List<Chromosome> population, PerformanceMetrics metrics) {
        return population.stream()
                .max(Comparator.comparing(c -> fitnessEvaluator.evaluateFitness(c, metrics)))
                .orElseThrow();
    }

    // Crossover: Combine attributes from two parents
    public Chromosome crossover(Chromosome parent1, Chromosome parent2) {
        return new Chromosome(
                parent1.getRoutingStrategy(),
                parent2.getLoadBalancingStrategy(),
                random.nextBoolean() ? parent1.getCachingPolicy() : parent2.getCachingPolicy()
        );
    }

    // Mutation: Randomly mutate a chromosome
    public Chromosome mutate(Chromosome chromosome) {
        if (random.nextDouble() < 0.1) { // 10% mutation chance
            chromosome = new Chromosome("round-robin", "random", "ttl"); // example mutation
        }
        return chromosome;
    }

    // Evolve the population based on performance metrics
    public void evolve(PerformanceMetrics metrics) {
        List<Chromosome> newPopulation = new ArrayList<>();

        for (int i = 0; i < population.size(); i++) {
            Chromosome parent1 = selectParent(population, metrics);
            Chromosome parent2 = selectParent(population, metrics);

            Chromosome child = crossover(parent1, parent2);
            child = mutate(child);

            newPopulation.add(child);
        }

        population = newPopulation;
    }

    public List<Chromosome> getPopulation() {
        return population;
    }
}
