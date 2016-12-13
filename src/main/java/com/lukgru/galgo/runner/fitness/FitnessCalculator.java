package com.lukgru.galgo.runner.fitness;

import com.lukgru.galgo.population.Population;

/**
 * Created by Lukasz on 13.12.2016.
 */
public interface FitnessCalculator<T> {
    void compute(Population<T> population);
}
