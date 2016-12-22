package com.lukgru.galgo.runner.solution;

import com.lukgru.galgo.fitness.FitnessFunction;
import com.lukgru.galgo.population.Population;

/**
 * Created by Lukasz on 12.12.2016.
 */
@SuppressWarnings("PMD")
public class SimpleSolutionSeeker<T> implements SolutionSeeker<T> {

    private FitnessFunction<T> fitnessFunction;

    public SimpleSolutionSeeker(FitnessFunction<T> fitnessFunction) {
        this.fitnessFunction = fitnessFunction;
    }

    @Override
    public boolean isSolutionFound(Population<T> population) {
        //TODO: implement
        return false;
    }
}
