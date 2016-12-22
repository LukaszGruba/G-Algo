package com.lukgru.galgo.runner.selection;

import com.lukgru.galgo.fitness.FitnessFunction;
import com.lukgru.galgo.population.Population;

/**
 * Created by Lukasz on 12.12.2016.
 */
@SuppressWarnings("PMD")
public class SimpleSelectionRunner<T> implements SelectionRunner<T> {

    private FitnessFunction<T> fitnessFunction;

    public SimpleSelectionRunner(FitnessFunction<T> fitnessFunction) {
        this.fitnessFunction = fitnessFunction;
    }

    @Override
    public Population<T> selectForReproduction(Population<T> population) {
        //TODO: implement
        return null;
    }
    
}
