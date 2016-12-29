package com.lukgru.galgo.runner;

import com.lukgru.galgo.population.Individual;
import com.lukgru.galgo.population.Population;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Łukasz on 2016-12-10.
 */
public class GenerationResultTest {

    @Test
    public void shouldReturnBestWithHigherFitnessScore() {
        //given
        Collection<Individual<Integer>> individuals = mockIndividuals();
        Population<Integer> finalPopulation = mock(Population.class);
        when(finalPopulation.getIndividuals()).thenReturn(individuals);
        double target = Double.MAX_VALUE;

        //when
        GenerationResult<Integer> result = new GenerationResult<>(finalPopulation, 100, target);
        int best = result.getBest().getValue();

        //then
        assertEquals(4, best);
    }

    @Test
    public void shouldReturnClosestToTarget() {
        //given
        Collection<Individual<Integer>> individuals = mockIndividuals();
        Population<Integer> finalPopulation = mock(Population.class);
        when(finalPopulation.getIndividuals()).thenReturn(individuals);
        double target = 0.0;

        //when
        GenerationResult<Integer> result = new GenerationResult<>(finalPopulation, 100, target);
        int best = result.getBest().getValue();

        //then
        assertEquals(6, best);
    }

    private Collection<Individual<Integer>> mockIndividuals() {
        return Arrays.asList(
                mockIndividual(1, 15.0),
                mockIndividual(2, 12.0),
                mockIndividual(3, 3.0),
                mockIndividual(4, Double.MAX_VALUE),
                mockIndividual(5, 7182.0),
                mockIndividual(6, 0.0),
                mockIndividual(7, 23.0),
                mockIndividual(8, 19999.0)
        );
    }

    private <T> Individual<T> mockIndividual(T value, double fitnessScore) {
        Individual<T> individual = mock(Individual.class);
        when(individual.getValue()).thenReturn(value);
        when(individual.getFitnessScore()).thenReturn(fitnessScore);
        return individual;
    }

}