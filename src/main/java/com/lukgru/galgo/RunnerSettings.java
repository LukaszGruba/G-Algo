package com.lukgru.galgo;

import java.util.Collection;
import java.util.function.Function;

/**
 * Created by Łukasz on 2016-11-28.
 */
public class RunnerSettings<T> {
    private Collection<T> population;
    private Function<T, Integer> fitnessFunction;
    private Integer fitnessFunctionTarget;
    private CrossoverFunction<T> crossoverFunction;
    private Function<T, T> mutationFunction;
    private double mutationProbability;

    public void setPopulation(Collection<T> population) {
        this.population = population;
    }

    public Collection<T> getPopulation() {
        return population;
    }

    public void setFitnessFunction(Function<T, Integer> fitnessFunction) {
        this.fitnessFunction = fitnessFunction;
    }

    public Function<T, Integer> getFitnessFunction() {
        return fitnessFunction;
    }

    public void setFitnessFunctionTarget(Integer fitnessFunctionTarget) {
        this.fitnessFunctionTarget = fitnessFunctionTarget;
    }

    public Integer getFitnessFunctionTarget() {
        return fitnessFunctionTarget;
    }

    public void setCrossoverFunction(CrossoverFunction<T> crossoverFunction) {
        this.crossoverFunction = crossoverFunction;
    }

    public CrossoverFunction<T> getCrossoverFunction() {
        return crossoverFunction;
    }

    public void setMutationFunction(Function<T, T> mutationFunction) {
        this.mutationFunction = mutationFunction;
    }

    public Function<T, T> getMutationFunction() {
        return mutationFunction;
    }

    public void setMutationProbability(double mutationProbability) {
        this.mutationProbability = mutationProbability;
    }

    public double getMutationProbability() {
        return mutationProbability;
    }
}
