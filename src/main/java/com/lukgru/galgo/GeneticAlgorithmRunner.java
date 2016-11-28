package com.lukgru.galgo;

/**
 * Created by Łukasz on 2016-11-28.
 */
public interface GeneticAlgorithmRunner<T> {

    GenerationResult<T> generate();

}
