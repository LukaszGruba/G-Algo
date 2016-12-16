package com.lukgru.galgo.runner.solution;

import com.lukgru.galgo.fitness.FitnessFunction;
import com.lukgru.galgo.population.Individual;
import com.lukgru.galgo.population.Population;

/**
 * Created by Lukasz on 12.12.2016.
 */
public class SimpleSolutionSeeker<T> implements SolutionSeeker<T> {

    private FitnessFunction<T> fitnessFunction; //TODO: maybe just target value is sufficient
    private final int epsilon;

    public SimpleSolutionSeeker(FitnessFunction<T> fitnessFunction, int epsilon) {
        this.fitnessFunction = fitnessFunction;
        this.epsilon = epsilon;
    }

    @Override
    public boolean isSolutionFound(Population<T> population) {
        return population.getIndividuals().stream()
                .filter(this::meetsSolutionCriteria)
                .findFirst()
                .isPresent();
    }

    protected boolean meetsSolutionCriteria(Individual<T> individual) {
        Integer target = fitnessFunction.getTarget();
        Integer fitnessScore = individual.getFitnessScore();
        return Math.abs(target - fitnessScore) < epsilon;
    }
}
