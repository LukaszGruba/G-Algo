package com.lukgru.galgo.runner;

import com.lukgru.galgo.crossover.CrossoverFunction;
import com.lukgru.galgo.fitness.FitnessFunction;
import com.lukgru.galgo.mutation.Mutation;
import com.lukgru.galgo.population.Population;
import com.lukgru.galgo.population.PopulationAccessor;

/**
 * Created by Łukasz on 2016-11-28.
 */
//TODO: remove after providing implementation
@SuppressWarnings({
        "PMD.UnusedPrivateField",
        "PMD.UnusedFormalParameter"
})
public class SimpleGeneticAlgorithmRunner<T> implements GeneticAlgorithmRunner<T> {

    private final Mutation<T> DEFAULT_MUTATION = new Mutation<>(a -> a, 0d);

    private PopulationAccessor<T> populationAccessor;
    private final FitnessFunction<T> fitnessFunction;
    private final CrossoverFunction<T> crossoverFunction;
    private final Mutation<T> mutation;

    public SimpleGeneticAlgorithmRunner(PopulationAccessor<T> populationAccessor, FitnessFunction<T> fitnessFunction,
                                        CrossoverFunction<T> crossoverFunction, Mutation<T> mutation) {
        this.populationAccessor = populationAccessor;
        this.fitnessFunction = fitnessFunction;
        this.crossoverFunction = crossoverFunction;
        this.mutation = mutation != null ? mutation : DEFAULT_MUTATION;
        validate();
    }

    private void validate() {
        if (populationAccessor == null) {
            throw new IllegalArgumentException("Population accessor cannot be null.");
        }
        if (fitnessFunction == null) {
            throw new IllegalArgumentException("Fitness function cannot be null.");
        }
        if (crossoverFunction == null) {
            throw new IllegalArgumentException("Crossover function cannot be null.");
        }
    }

    @Override
    public GenerationResult<T> generate() {
        Population<T> population = populationAccessor.getPopulation();
        int iteration = 0;
        do {
            Population<T> selectedForReproduction = selection(population, fitnessFunction);
            Population<T> newPopulation = reproduce(selectedForReproduction, crossoverFunction);
            mutate(newPopulation);
            population = newPopulation;
            iteration++;
        } while (!solutionFound(population, fitnessFunction));
        return new GenerationResult<>(population, iteration);
    }

    private Population<T> selection(Population<T> population, FitnessFunction<T> fitnessFunction) {
        //TODO: add implementation
        return null;
    }

    private Population<T> reproduce(Population<T> selectedForReproduction, CrossoverFunction<T> crossoverFunction) {
        //TODO: add implementation
        return null;
    }

    private void mutate(Population<T> newPopulation) {
        //TODO: add implementation
    }

    private boolean solutionFound(Population<T> population, FitnessFunction<T> fitnessFunction) {
        //TODO: add implementation
        return true;
    }
}
