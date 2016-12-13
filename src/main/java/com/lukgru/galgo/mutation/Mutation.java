package com.lukgru.galgo.mutation;

import java.util.Objects;

/**
 * Created by Łukasz on 2016-12-03.
 */
public class Mutation<T> {

    private final MutationFunction<T> mutationFunction;
    private final Double probability;

    public Mutation(MutationFunction<T> mutationFunction, Double probability) {
        Objects.requireNonNull(mutationFunction, "Mutation function cannot be null.");
        Objects.requireNonNull(probability, "Mutation probability cannot be null");
        if (probability < 0.0d || probability > 1.0d) {
            throw new IllegalArgumentException("Probability of mutation has to be fraction between 0 and 1");
        }
        this.mutationFunction = mutationFunction;
        this.probability = probability;
    }

    public MutationFunction<T> getMutationFunction() {
        return this.mutationFunction;
    }

    public Double getProbability() {
        return this.probability;
    }
}
