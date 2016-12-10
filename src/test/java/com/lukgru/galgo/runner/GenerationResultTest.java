package com.lukgru.galgo.runner;

import com.lukgru.galgo.model.Individual;
import com.lukgru.galgo.model.Population;
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

    private static final int BEST_SCORE = Integer.MAX_VALUE;
    private static final int WORST_SCORE = 0;

    
    @Test
    public void shouldReturnBestWithHigherFitnessScore() {
        //given
        Collection<Individual<Integer>> individuals = mockIndividuals();
        Population finalPopulation = mock(Population.class);
        when(finalPopulation.getIndividuals()).thenReturn(individuals);

        //when
        GenerationResult<Integer> result = new GenerationResult<>(finalPopulation, 100);
        int best = result.getBest();

        //then
        assertEquals(4, best);
    }

    private Collection<Individual<Integer>> mockIndividuals() {
        return Arrays.asList(
                mockIndividual(1, 15),
                mockIndividual(2, 12),
                mockIndividual(3, 3),
                mockIndividual(4, BEST_SCORE),
                mockIndividual(5, 7182),
                mockIndividual(6, WORST_SCORE),
                mockIndividual(7, 23),
                mockIndividual(8, 19999)
        );
    }

    private <T> Individual<T> mockIndividual(T value, int fitnessScore) {
        Individual individual = mock(Individual.class);
        when(individual.getValue()).thenReturn(value);
        when(individual.getFitnessScore()).thenReturn(fitnessScore);
        return individual;
    }

}