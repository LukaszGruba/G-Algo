package com.lukgru.galgo.runner;

import com.lukgru.galgo.model.CrossoverFunction;
import com.lukgru.galgo.model.FitnessFunction;
import com.lukgru.galgo.model.Mutation;
import com.lukgru.galgo.model.Population;

/**
 * Created by Łukasz on 2016-11-28.
 */
public class SimpleGeneticAlgorithmRunner<T> implements GeneticAlgorithmRunner<T> {

    private final FitnessFunction<T> fitnessFunction;
    private final CrossoverFunction<T> crossoverFunction;
    private final Mutation<T> mutation;
    private final Population<T> initialPopulation;

    public SimpleGeneticAlgorithmRunner(Population<T> initialPopulation, FitnessFunction<T> fitnessFunction, CrossoverFunction<T> crossoverFunction, Mutation<T> mutation) {
        this.initialPopulation = initialPopulation;
        this.fitnessFunction = fitnessFunction;
        this.crossoverFunction = crossoverFunction;
        this.mutation = mutation;
    }

    @Override
    public GenerationResult<T> generate() {
        Population<T> population = initialPopulation;
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

    private boolean solutionFound(Population<T> population, FitnessFunction<T> fitnessFunction) {
        //TODO: add implementation
        return true;
    }

    private void mutate(Population<T> newPopulation) {
        //TODO: add implementation
    }

    private Population<T> reproduce(Population<T> selectedForReproduction, CrossoverFunction<T> crossoverFunction) {
        //TODO: add implementation
        return null;
    }

    private Population<T> selection(Population<T> population, FitnessFunction<T> fitnessFunction) {
        //TODO: add implementation
        return null;
    }
}
