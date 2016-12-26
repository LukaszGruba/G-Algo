package com.lukgru.galgo.runner.selection;

import com.lukgru.galgo.builder.population.TestPopulationProvider;
import com.lukgru.galgo.fitness.FitnessFunction;
import com.lukgru.galgo.population.Individual;
import com.lukgru.galgo.population.Population;
import org.junit.Test;

import java.util.List;

import static com.lukgru.galgo.builder.population.TestPopulationProvider.generatePopulation;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Lukasz on 26.12.2016.
 */
public class SimpleSelectionRunnerTest {
    
    @Test
    public void selectionShouldReturnTwoTimesSmallerPopulation() {
        //given
        int inputPopulationSize = 100;
        Population<Integer> inputPopulation = generatePopulation(() -> 1, () -> 1.0, inputPopulationSize);
        FitnessFunction<Integer> fitness = mock(FitnessFunction.class);
        when(fitness.getTarget()).thenReturn(0.0);
        
        //when
        Population<Integer> selectForReproduction = new SimpleSelectionRunner<>(fitness).selectForReproduction(inputPopulation);

        //then
        assertEquals(50, selectForReproduction.size());
    }

    @Test
    public void duringSelectionRoundPopulationUp() {
        //given
        int inputPopulationSize = 99;
        Population<Integer> inputPopulation = generatePopulation(() -> 1, () -> 1.0, inputPopulationSize);
        FitnessFunction<Integer> fitness = mock(FitnessFunction.class);
        when(fitness.getTarget()).thenReturn(0.0);

        //when
        Population<Integer> selectForReproduction = new SimpleSelectionRunner<>(fitness).selectForReproduction(inputPopulation);

        //then
        assertEquals(50, selectForReproduction.size());
    }

    @Test
    public void shouldSelectBest() {
        //given
        Population<Integer> population = TestPopulationProvider.<Integer>populationMockBuilder()
                .add(0, 0.0)
                .add(1, 1.0)
                .add(2, 2.0)
                .add(3, 3.0)
                .add(4, 4.0)
                .add(5, 5.0)
                .add(6, 6.0)
                .add(7, 7.0)
                .add(8, 8.0)
                .add(9, 9.0)
                .build();
        FitnessFunction<Integer> fitness = mock(FitnessFunction.class);
        when(fitness.getTarget()).thenReturn(10.0);

        //when
        Population<Integer> selectedForReproduction = new SimpleSelectionRunner<>(fitness).selectForReproduction(population);

        //then
        List<Integer> forReproduction = selectedForReproduction.getIndividuals().stream().map(Individual::getValue).collect(toList());
        assertEquals(5, selectedForReproduction.size());
        assertThat(forReproduction, hasItem(9));
        assertThat(forReproduction, hasItem(8));
        assertThat(forReproduction, hasItem(7));
        assertThat(forReproduction, hasItem(6));
        assertThat(forReproduction, hasItem(5));
    }

}