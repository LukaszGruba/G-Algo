package com.lukgru.galgo.model;

import java.util.function.Function;

/**
 * Created by Łukasz on 2016-12-03.
 */
public class Mutation<T> {

    private final Function<T, T> mutationFunction;
    private final Double probability;

    public Mutation(Function<T, T> mutationFunction, Double probability) {
        if (mutationFunction == null || probability == null) {
            throw new IllegalArgumentException("Mutation function nor mutation probability cannot be null");
        }
        if (probability < 0.0d || probability > 1.0d) {
            throw new IllegalArgumentException("Probability of mutation has to be fraction between 0 and 1");
        }
        this.mutationFunction = mutationFunction;
        this.probability = probability;
    }

    public Function<T, T> getMutationFunction() {
        return this.mutationFunction;
    }

    public Double getProbability() {
        return this.probability;
    }
}
