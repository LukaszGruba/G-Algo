package com.lukgru.galgo;

/**
 * Created by Łukasz on 2016-11-28.
 */
public class SimpleGeneticAlgorithmRunner<T> implements GeneticAlgorithmRunner<T> {

    private RunnerSettings<T> settings;

    public SimpleGeneticAlgorithmRunner(RunnerSettings<T> settings) {
        this.settings = settings;
    }

    @Override
    public GenerationResult<T> generate() {
        return null;
    }
}
