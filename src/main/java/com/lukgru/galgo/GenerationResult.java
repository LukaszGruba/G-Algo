package com.lukgru.galgo;

import java.util.Collection;

/**
 * Created by Lukasz on 28.11.2016.
 */
public class GenerationResult<T> {
    private T best;
    private int iterations;
    private Collection<T> lastPopulation;

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
