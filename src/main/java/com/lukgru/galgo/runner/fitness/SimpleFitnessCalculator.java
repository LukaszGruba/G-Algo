package com.lukgru.galgo.runner.fitness;

import com.lukgru.galgo.fitness.FitnessFunction;
import com.lukgru.galgo.population.Population;

/**
 * Created by Lukasz on 13.12.2016.
 */
public class SimpleFitnessCalculator<T> implements FitnessCalculator<T> {

    private FitnessFunction<T> fitnessFunction;

    public SimpleFitnessCalculator(FitnessFunction<T> fitnessFunction) {
        this.fitnessFunction = fitnessFunction;
    }

    @Override
    public void compute(Population<T> population) {
        population.getIndividuals().forEach(individual -> {
            T value = individual.getValue();
            Double fitnessValue = fitnessFunction.getFitnessFunction().apply(value);
            individual.setFitnessScore(fitnessValue);
        });
    }
}
