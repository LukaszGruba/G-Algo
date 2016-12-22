package com.lukgru.galgo.runner.solution;

import com.lukgru.galgo.builder.population.TestPopulationProvider;
import com.lukgru.galgo.builder.population.TestPopulationProvider.PopulationMockBuilder;
import com.lukgru.galgo.population.Individual;
import com.lukgru.galgo.population.Population;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Lukasz on 16.12.2016.
 */
public class SimpleSolutionSeekerTest {

    @Test
    public void shouldMeetCriteriaForGreaterThanTarget() {
        //given
        double target = 0.0;
        double epsilon = 2.0;
        Individual<Integer> individual = mock(Individual.class);
        when(individual.getFitnessScore()).thenReturn(1d);

        //when
        boolean solutionFound = new SimpleSolutionSeeker(target, epsilon).meetsSolutionCriteria(individual);

        //then
        assertTrue(solutionFound);
    }

    @Test
    public void shouldMeetCriteriaForSmallerThanTarget() {
        //given
        double target = 0.0;
        double epsilon = 2.0;
        Individual<Integer> individual = mock(Individual.class);
        when(individual.getFitnessScore()).thenReturn(-1d);

        //when
        boolean solutionFound = new SimpleSolutionSeeker(target, epsilon).meetsSolutionCriteria(individual);

        //then
        assertTrue(solutionFound);
    }

    @Test
    public void shouldNotMeetCriteriaForGreaterThanTarget() {
        //given
        double target = 0.0;
        double epsilon = 1.0;
        Individual<Integer> individual = mock(Individual.class);
        when(individual.getFitnessScore()).thenReturn(2d);

        //when
        boolean solutionFound = new SimpleSolutionSeeker(target, epsilon).meetsSolutionCriteria(individual);

        //then
        assertFalse(solutionFound);
    }

    @Test
    public void shouldNotMeetCriteriaForSmallerThanTarget() {
        //given
        double target = 0.0;
        double epsilon = 1.0;
        Individual<Integer> individual = mock(Individual.class);
        when(individual.getFitnessScore()).thenReturn(-2d);

        //when
        boolean solutionFound = new SimpleSolutionSeeker(target, epsilon).meetsSolutionCriteria(individual);

        //then
        assertFalse(solutionFound);
    }

    @Test
    public void shouldFindSolution() {
        //given
        double target = 13.0;
        double epsilon = 1.0;
        PopulationMockBuilder<Integer> builder = TestPopulationProvider.populationMockBuilder();
        IntStream.range(-50, 50).forEach(i -> builder.add(i, i));
        Population<Integer> population = builder.build();

        //when
        boolean solutionFound = new SimpleSolutionSeeker(target, epsilon).isSolutionFound(population);

        //then
        assertTrue(solutionFound);
    }

    @Test
    public void shouldNotFindSolution() {
        //given
        double target = 12.5;
        double epsilon = 0.5d;
        PopulationMockBuilder<Integer> builder = TestPopulationProvider.populationMockBuilder();
        IntStream.range(-50, 50).forEach(i -> builder.add(i, i));
        Population<Integer> population = builder.build();

        //when
        boolean solutionFound = new SimpleSolutionSeeker(target, epsilon).isSolutionFound(population);

        //then
        assertFalse(solutionFound);
    }

}