package com.lukgru.galgo.runner;

import com.lukgru.galgo.crossover.CrossoverFunction;
import com.lukgru.galgo.fitness.FitnessFunction;
import com.lukgru.galgo.mutation.Mutation;
import com.lukgru.galgo.population.Population;
import com.lukgru.galgo.population.PopulationAccessor;
import com.lukgru.galgo.runner.fitness.SimpleFitnessCalculator;
import com.lukgru.galgo.runner.mutation.SimpleMutationRunner;
import com.lukgru.galgo.runner.reproduction.SimpleReproductionRunner;
import com.lukgru.galgo.runner.selection.SimpleSelectionRunner;
import com.lukgru.galgo.runner.solution.SimpleSolutionSeeker;

import java.util.Objects;

/**
 * Created by Łukasz on 2016-11-28.
 */
//TODO: provide mechanism for statistics
//TODO: add logging
//TODO: enable providing timeout
public class SimpleGeneticAlgorithmRunner<T> implements GeneticAlgorithmRunner<T> {

    private PopulationAccessor<T> populationAccessor;
    private final FitnessFunction<T> fitnessFunction;
    private Double epsilon;
    private final CrossoverFunction<T> crossoverFunction;
    private final Mutation<T> mutation;

    public SimpleGeneticAlgorithmRunner(PopulationAccessor<T> populationAccessor, FitnessFunction<T> fitnessFunction,
                                        Double epsilon, CrossoverFunction<T> crossoverFunction, Mutation<T> mutation) {
        this.populationAccessor = populationAccessor;
        this.fitnessFunction = fitnessFunction;
        this.epsilon = epsilon;
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
        computeFitness(population, fitnessFunction);
        do {
            Population<T> selectedForReproduction = selection(population, fitnessFunction);
            Population<T> newPopulation = reproduce(selectedForReproduction, crossoverFunction);
            mutate(newPopulation, mutation);
            computeFitness(newPopulation, fitnessFunction);
            population = newPopulation;
            iteration++;
        } while (!solutionFound(population, fitnessFunction, epsilon));
        return new GenerationResult<>(population, iteration, fitnessFunction.getTarget());
    }

    //TODO: make below runners dependencies
    //TODO: population, fitnessFunction etc. should not be dependencies but parameters or should be kept in some aggregating object
    private void computeFitness(Population<T> population, FitnessFunction<T> fitnessFunction) {
        new SimpleFitnessCalculator<>(fitnessFunction).compute(population);
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

    private boolean solutionFound(Population<T> population, FitnessFunction<T> fitnessFunction, Double epsilon) {
        return new SimpleSolutionSeeker(fitnessFunction.getTarget(), epsilon).isSolutionFound(population);
    }
}
