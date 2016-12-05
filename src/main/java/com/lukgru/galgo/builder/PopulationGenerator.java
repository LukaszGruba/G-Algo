package com.lukgru.galgo.builder;

import java.util.function.Supplier;

/**
 * Created by Lukasz on 05.12.2016.
 */
public class PopulationGenerator<T> {

    private Supplier<T> factory;

    public PopulationGenerator(Supplier<T> factory) {
        this.factory = factory;
    }

    public PopulationBuilder<Integer> withSize(int populationSize) {
        return new PopulationBuilder<>();
    }
}
