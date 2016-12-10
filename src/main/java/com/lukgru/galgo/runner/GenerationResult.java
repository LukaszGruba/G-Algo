package com.lukgru.galgo.runner;

import com.lukgru.galgo.population.Individual;
import com.lukgru.galgo.population.Population;

/**
 * Created by Lukasz on 28.11.2016.
 */
public class GenerationResult<T> {
    private final Population<T> finalPopulation;
    private final int iterations;

    public GenerationResult(Population<T> finalPopulation, int iterations) {
        this.finalPopulation = finalPopulation;
        this.iterations = iterations;
    }

    public T getBest() {
        Individual<T> bestIndividual = finalPopulation.getIndividuals().stream()
                .max((indA, indB) -> indA.getFitnessScore() - indB.getFitnessScore())
                .get();
        return bestIndividual.getValue();
    }

    public int getIterations() {
        return iterations;
    }

    public Population<T> getFinalPopulation() {
        return finalPopulation;
    }
}
