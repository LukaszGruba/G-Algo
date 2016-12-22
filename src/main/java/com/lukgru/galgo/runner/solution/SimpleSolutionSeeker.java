package com.lukgru.galgo.runner.solution;

import com.lukgru.galgo.population.Individual;
import com.lukgru.galgo.population.Population;

/**
 * Created by Lukasz on 12.12.2016.
 */
public class SimpleSolutionSeeker implements SolutionSeeker {

    private final double target;
    private final double epsilon;

    public SimpleSolutionSeeker(double target, double epsilon) {
        this.target = target;
        this.epsilon = epsilon;
    }

    @Override
    public <T> boolean isSolutionFound(Population<T> population) {
        return population.getIndividuals().stream()
                .filter(this::meetsSolutionCriteria)
                .findFirst()
                .isPresent();
    }

    protected <T> boolean meetsSolutionCriteria(Individual<T> individual) {
        Double fitnessScore = individual.getFitnessScore();
        return Math.abs(target - fitnessScore) < epsilon;
    }
}
