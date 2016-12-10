package com.lukgru.galgo.runner;

import com.lukgru.galgo.model.Individual;
import com.lukgru.galgo.model.Population;

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
                .max((a, b) -> a.getFitnessScore() - b.getFitnessScore())
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
