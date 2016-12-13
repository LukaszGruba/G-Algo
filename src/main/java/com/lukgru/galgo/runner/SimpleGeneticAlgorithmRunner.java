package com.lukgru.galgo.runner;

import com.lukgru.galgo.crossover.CrossoverFunction;
import com.lukgru.galgo.fitness.FitnessFunction;
import com.lukgru.galgo.mutation.Mutation;
import com.lukgru.galgo.population.Population;
import com.lukgru.galgo.population.PopulationAccessor;
import com.lukgru.galgo.runner.mutation.SimpleMutationRunner;
import com.lukgru.galgo.runner.reproduction.SimpleReproductionRunner;
import com.lukgru.galgo.runner.selection.SimpleSelectionRunner;
import com.lukgru.galgo.runner.solution.SimpleSolutionSeeker;

import java.util.Objects;

/**
 * Created by Łukasz on 2016-11-28.
 */
public class SimpleGeneticAlgorithmRunner<T> implements GeneticAlgorithmRunner<T> {

    private PopulationAccessor<T> populationAccessor;
    private final FitnessFunction<T> fitnessFunction;
    private final CrossoverFunction<T> crossoverFunction;
    private final Mutation<T> mutation;

    public SimpleGeneticAlgorithmRunner(PopulationAccessor<T> populationAccessor, FitnessFunction<T> fitnessFunction,
                                        CrossoverFunction<T> crossoverFunction, Mutation<T> mutation) {
        this.populationAccessor = populationAccessor;
        this.fitnessFunction = fitnessFunction;
        this.crossoverFunction = crossoverFunction;
        Mutation<T> defaultMutation = new Mutation<>(a -> a, 0d);
        this.mutation = mutation != null ? mutation : defaultMutation;
        validate();
    }

    private void validate() {
        Objects.requireNonNull(populationAccessor, "Population accessor cannot be null.");
        Objects.requireNonNull(fitnessFunction, "Fitness function cannot be null.");
        Objects.requireNonNull(crossoverFunction, "Crossover function cannot be null.");
    }

    @Override
    public GenerationResult<T> generate() {
        Population<T> population = populationAccessor.getPopulation();
        int iteration = 0;
        do {
            Population<T> selectedForReproduction = selection(population, fitnessFunction);
            Population<T> newPopulation = reproduce(selectedForReproduction, crossoverFunction);
            mutate(newPopulation, mutation);
            population = newPopulation;
            iteration++;
        } while (!solutionFound(population, fitnessFunction));
        return new GenerationResult<>(population, iteration);
    }

    private Population<T> selection(Population<T> population, FitnessFunction<T> fitnessFunction) {
        return new SimpleSelectionRunner<>(fitnessFunction).selectForReproduction(population);
    }

    private Population<T> reproduce(Population<T> selectedForReproduction, CrossoverFunction<T> crossoverFunction) {
        return new SimpleReproductionRunner<>(crossoverFunction).reproduce(selectedForReproduction);
    }

    private void mutate(Population<T> newPopulation, Mutation<T> mutation) {
        new SimpleMutationRunner<>(mutation).mutate(newPopulation);
    }

    private boolean solutionFound(Population<T> population, FitnessFunction<T> fitnessFunction) {
        return new SimpleSolutionSeeker<>(fitnessFunction).isSolutionFound(population);
    }
}
