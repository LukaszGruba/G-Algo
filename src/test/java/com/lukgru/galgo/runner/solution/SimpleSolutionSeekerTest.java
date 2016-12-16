package com.lukgru.galgo.runner.solution;

import com.lukgru.galgo.fitness.FitnessFunction;
import com.lukgru.galgo.population.Individual;
import com.lukgru.galgo.population.Population;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Lukasz on 16.12.2016.
 */
public class SimpleSolutionSeekerTest {

    @Test
    public void shouldMeetCriteriaForGreaterThanTarget() {
        //given
        FitnessFunction<Integer> fitnessFunction = mock(FitnessFunction.class);
        when(fitnessFunction.getTarget()).thenReturn(0);
        int epsilon = 2;
        Individual<Integer> individual = mock(Individual.class);
        when(individual.getFitnessScore()).thenReturn(1);

        //when
        boolean solutionFound = new SimpleSolutionSeeker<>(fitnessFunction, epsilon).meetsSolutionCriteria(individual);

        //then
        assertTrue(solutionFound);
    }

    @Test
    public void shouldMeetCriteriaForSmallerThanTarget() {
        //given
        FitnessFunction<Integer> fitnessFunction = mock(FitnessFunction.class);
        when(fitnessFunction.getTarget()).thenReturn(0);
        int epsilon = 2;
        Individual<Integer> individual = mock(Individual.class);
        when(individual.getFitnessScore()).thenReturn(-1);

        //when
        boolean solutionFound = new SimpleSolutionSeeker<>(fitnessFunction, epsilon).meetsSolutionCriteria(individual);

        //then
        assertTrue(solutionFound);
    }

    @Test
    public void shouldNotMeetCriteriaForGreaterThanTarget() {
        //given
        FitnessFunction<Integer> fitnessFunction = mock(FitnessFunction.class);
        when(fitnessFunction.getTarget()).thenReturn(0);
        int epsilon = 1;
        Individual<Integer> individual = mock(Individual.class);
        when(individual.getFitnessScore()).thenReturn(2);

        //when
        boolean solutionFound = new SimpleSolutionSeeker<>(fitnessFunction, epsilon).meetsSolutionCriteria(individual);

        //then
        assertFalse(solutionFound);
    }

    @Test
    public void shouldNotMeetCriteriaForSmallerThanTarget() {
        //given
        FitnessFunction<Integer> fitnessFunction = mock(FitnessFunction.class);
        when(fitnessFunction.getTarget()).thenReturn(0);
        int epsilon = 1;
        Individual<Integer> individual = mock(Individual.class);
        when(individual.getFitnessScore()).thenReturn(-2);

        //when
        boolean solutionFound = new SimpleSolutionSeeker<>(fitnessFunction, epsilon).meetsSolutionCriteria(individual);

        //then
        assertFalse(solutionFound);
    }

    @Test
    public void shouldFindSolution() {
        //given
        FitnessFunction<Integer> fitnessFunction = mock(FitnessFunction.class);
        when(fitnessFunction.getTarget()).thenReturn(0);
        int epsilon = 1;
        Population<Integer> population = null; //FIXME: should use test population generator

        //when
        boolean solutionFound = new SimpleSolutionSeeker<>(fitnessFunction, epsilon).isSolutionFound(population);

        //then
        assertTrue(solutionFound);
    }

    @Test
    public void shouldNotFindSolution() {
        //given
        FitnessFunction<Integer> fitnessFunction = mock(FitnessFunction.class);
        when(fitnessFunction.getTarget()).thenReturn(0);
        int epsilon = 1;
        Population<Integer> population = null; //FIXME: should use test population generator

        //when
        boolean solutionFound = new SimpleSolutionSeeker<>(fitnessFunction, epsilon).isSolutionFound(population);

        //then
        assertFalse(solutionFound);
    }

}