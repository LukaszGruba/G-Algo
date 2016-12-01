package com.lukgru.galgo.runner;

import java.util.Collection;

/**
 * Created by Lukasz on 28.11.2016.
 */
public class GenerationResult<T> {
    private final Collection<T> lastPopulation;
    private int iterations;
    private T best;

    public GenerationResult(Collection<T> lastPopulation, int iterations) {
        this.lastPopulation = lastPopulation;
        this.iterations = iterations;
    }

    public T getBest() {
        return best;
    }

    public int getIterations() {
        return iterations;
    }

    public Collection<T> getLastPopulation() {
        return lastPopulation;
    }
}
