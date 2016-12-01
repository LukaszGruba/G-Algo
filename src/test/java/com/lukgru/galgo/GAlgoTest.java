package com.lukgru.galgo;

import com.lukgru.galgo.runner.GenerationResult;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Lukasz on 28.11.2016.
 */
public class GAlgoTest {

    @Test
    public void testAPI() {
        //given
        Collection<Integer> initPopulation = Arrays.asList(1, 10, 100, 1000, 10000, 100000);

        //when
        GenerationResult result = GAlgo.fromPopulation(initPopulation)
                .withFitnessFunction((a) -> 123 - a).targeting(0)
                .withCrossover((a, b) -> (a + b) / 2)
                .withMutation((a) -> (a), 0.05d)
                .runner().generate();

        //then
        result.getBest();
        result.getIterations();
        result.getLastPopulation();
    }
}