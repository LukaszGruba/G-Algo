package com.lukgru.galgo;

import com.lukgru.galgo.builder.PopulationBuilder;
import com.lukgru.galgo.model.Individual;
import com.lukgru.galgo.runner.GenerationResult;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.function.DoubleSupplier;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by Lukasz on 28.11.2016.
 */
public class GAlgoAPITest {

    @Test
    public void testAPI() {
        //given
        Collection<Integer> initPopulation = Arrays.asList(1, 10, 100, 1000, 10000, 100000);

        //when
        GenerationResult result = GAlgo.fromPopulation(initPopulation)
                .withFitnessFunction((a) -> 123 - a).targeting(0)
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
        PopulationBuilder<Integer> populationBuilder = GAlgo.fromGeneratedPopulation(factory).withSize(populationSize);
    }
}