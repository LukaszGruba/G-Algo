package com.lukgru.galgo.runner;

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
        //TODO: return best individual
        return null;
    }

    public int getIterations() {
        return iterations;
    }

    public Population<T> getFinalPopulation() {
        return finalPopulation;
    }
}
