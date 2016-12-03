package com.lukgru.galgo.model;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.*;

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

    @Test
    public void populationShouldBeUnmodifiable() {
        //given
        Population<Integer> population = new Population<>(Arrays.asList(1, 2, 3));
        Collection<Integer> populationElements = population.getPopulation();

        //when
        populationElements.add(4);
    }
}