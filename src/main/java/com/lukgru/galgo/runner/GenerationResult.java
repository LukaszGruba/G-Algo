package com.lukgru.galgo.runner;

import com.lukgru.galgo.population.Individual;
import com.lukgru.galgo.population.Population;

/**
 * Created by Lukasz on 28.11.2016.
 */
//TODO: add accessor for statistics
public class GenerationResult<T> {
    private final Population<T> finalPopulation;
    private final int iterations;
    private final double target;

    public GenerationResult(Population<T> finalPopulation, int iterations, double target) {
        this.finalPopulation = finalPopulation;
        this.iterations = iterations;
        this.target = target;
    }

    public Individual<T> getBest() {
        return finalPopulation.getIndividuals().stream()
                .min((indA, indB) -> {
                    Double fitA = Math.abs(target - indA.getFitnessScore());
                    Double fitB = Math.abs(target - indB.getFitnessScore());
                    return fitA.compareTo(fitB);
                })
                .orElse(null);
    }

    public int getIterations() {
        return iterations;
    }

    public Population<T> getFinalPopulation() {
        return finalPopulation;
    }
}
