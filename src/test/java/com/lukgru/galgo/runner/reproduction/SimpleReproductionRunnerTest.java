package com.lukgru.galgo.runner.reproduction;

import com.lukgru.galgo.crossover.CrossoverFunction;
import com.lukgru.galgo.population.Individual;
import com.lukgru.galgo.population.Population;
import org.junit.Test;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.lukgru.galgo.builder.population.TestPopulationProvider.generatePopulation;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Lukasz on 15.12.2016.
 */
public class SimpleReproductionRunnerTest {

    @Test
    public void reproduceProperly() {
        //given
        Population<Integer> population = generatePopulation(
                1,2,
                3,4,
                5,6,
                7,8,
                9,10);
        CrossoverFunction<Integer> crossoverFunction = (a, b) -> (3*a + b) / 2;

        //when
        Population<Integer> children = new SimpleReproductionRunner<>(crossoverFunction).reproduce(population);

        //then
        List<Integer> childrenList = children.getIndividuals().stream().map(Individual::getValue).collect(toList());
        assertThat(childrenList, hasItem(2));
        assertThat(childrenList, hasItem(3));
        assertThat(childrenList, hasItem(6));
        assertThat(childrenList, hasItem(7));
        assertThat(childrenList, hasItem(10));
        assertThat(childrenList, hasItem(11));
        assertThat(childrenList, hasItem(14));
        assertThat(childrenList, hasItem(15));
        assertThat(childrenList, hasItem(18));
        assertThat(childrenList, hasItem(19));
    }

}