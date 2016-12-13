package com.lukgru.galgo.runner.reproduction;

import com.lukgru.galgo.crossover.CrossoverFunction;
import com.lukgru.galgo.population.Population;

/**
 * Created by Lukasz on 12.12.2016.
 */
public class SimpleReproductionRunner<T> implements ReproductionRunner<T> {

    private CrossoverFunction<T> crossoverFunction;

    public SimpleReproductionRunner(CrossoverFunction<T> crossoverFunction) {
        this.crossoverFunction = crossoverFunction;
    }

    @Override
    public Population<T> reproduce(Population<T> selectedForReproduction) {
        //TODO: implement
        return null;
    }
}