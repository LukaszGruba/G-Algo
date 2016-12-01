package com.lukgru.galgo.runner;

/**
 * Created by Łukasz on 2016-11-28.
 */
public interface GeneticAlgorithmRunner<T> {

    GenerationResult<T> generate();

}
