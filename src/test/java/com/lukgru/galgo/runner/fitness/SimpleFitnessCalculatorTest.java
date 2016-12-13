package com.lukgru.galgo.runner.fitness;

import com.lukgru.galgo.fitness.FitnessFunction;
import com.lukgru.galgo.population.Individual;
import com.lukgru.galgo.population.Population;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Lukasz on 13.12.2016.
 */
public class SimpleFitnessCalculatorTest {

    @Test
    public void shouldCalculateForEachInPopulation() {
        //given
        Population<Integer> population = new Population<>(Arrays.asList(1, 2, 3));
        FitnessFunction<Integer> fitness = new FitnessFunction<>(i -> (i*i) + (7*i) - 12, 0);

        //when
        new SimpleFitnessCalculator<>(fitness).compute(population);
        
        //then
        Collection<Individual<Integer>> individuals = population.getIndividuals();
        List<Integer> listOfComputedFitnessScores = individuals.stream()
                .map(Individual::getFitnessScore)
                .collect(Collectors.toList());
        assertThat(listOfComputedFitnessScores, hasItem(-4));
        assertThat(listOfComputedFitnessScores, hasItem(6));
        assertThat(listOfComputedFitnessScores, hasItem(18));
    }

}