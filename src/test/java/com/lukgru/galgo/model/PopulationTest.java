package com.lukgru.galgo.model;

import com.lukgru.galgo.population.Individual;
import com.lukgru.galgo.population.Population;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by Lukasz on 02.12.2016.
 */
public class PopulationTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotBeAbleToCreateNullPopulation() {
        new Population<>(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotBeAbleToCreateEmptyPopulation() {
        new Population<>(Collections.emptyList());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void populationShouldBeUnmodifiable() {
        //given
        Population<Integer> population = new Population<>(Arrays.asList(1, 2, 3));
        Collection<Individual<Integer>> individualCollection = population.getIndividuals();

        //when
        individualCollection.add(new Individual<>(4));
    }
    
    @Test
    public void shouldCreateIndividualsProperly() {
        //given
        List<Integer> integers = Arrays.asList(1, 2, 3);

        //when
        Population<Integer> population = new Population<>(integers);
        Collection<Individual<Integer>> individuals = population.getIndividuals();

        //then
        assertThat(individuals, hasItem(new Individual<>(1)));
        assertThat(individuals, hasItem(new Individual<>(2)));
        assertThat(individuals, hasItem(new Individual<>(3)));
    }
}