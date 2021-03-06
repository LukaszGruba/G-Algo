package com.lukgru.galgo;

import com.lukgru.galgo.runner.GenerationResult;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Supplier;

/**
 * Created by Lukasz on 28.11.2016.
 */
@Ignore("API test - should only compile")
public class GAlgoAPITest {

    @Test
    public void testAPI() {
        //given
        Collection<Integer> initPopulation = Arrays.asList(1, 10, 100, 1000, 10000, 100000);

        //when
        GenerationResult result = GAlgo.fromPopulation(initPopulation)
                .withFitnessFunction((a) -> 123.0 - a).targeting(0.0).withEpsilon(0.1)
                .withCrossover((a, b) -> (a + b) / 2)
                .withMutationFunction((a) -> (a)).withMutationProbability(0.05d)
                .runner().generate();

        //then
        result.getBest();
        result.getIterations();
        result.getFinalPopulation();
    }

    @Test
    public void allowToGeneratePopulation() {
        //given
        Supplier<Integer> factory = () -> (int)(Math.random() * Integer.MAX_VALUE);
        int populationSize = 100;

        //then
        GenerationResult<Integer> result = GAlgo.fromGeneratedPopulation(factory)
                .withSize(populationSize)
                .withFitnessFunction((a) -> 123.0 - a).targeting(0.0)
                .withCrossover((a, b) -> (a + b) / 2)
                .withMutationFunction((a) -> (a)).withMutationProbability(0.05d)
                .runner().generate();
    }
}