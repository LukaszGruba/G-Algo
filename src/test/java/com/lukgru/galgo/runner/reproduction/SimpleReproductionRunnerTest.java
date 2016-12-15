package com.lukgru.galgo.runner.reproduction;

import com.lukgru.galgo.crossover.CrossoverFunction;
import com.lukgru.galgo.population.Population;
import org.junit.Test;

/**
 * Created by Lukasz on 15.12.2016.
 */
public class SimpleReproductionRunnerTest {

    @Test
    public void reproduceProperly() {
        //given
        Population<Integer> population = null; //TODO: provide mocked population creator for tests / common sample population
        CrossoverFunction<Integer> crossoverFunction = (a, b) -> (a + b) / 2;

        //when
        Population<Integer> children = new SimpleReproductionRunner<>(crossoverFunction).reproduce(population);

        //then

    }

}