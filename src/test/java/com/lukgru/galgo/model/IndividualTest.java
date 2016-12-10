package com.lukgru.galgo.model;

import com.lukgru.galgo.population.Individual;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Łukasz on 2016-12-03.
 */
public class IndividualTest {

    @Test(expected = NullPointerException.class)
    public void shouldNotBeAbleToCreateWithNullValue() {
        new Individual<>(null);
    }

    @Test
    public void testEquals() {
        Individual<Integer> i1 = new Individual<>(1);
        Individual<Integer> i2 = new Individual<>(1);
        Individual<Integer> i3 = new Individual<>(2);

        assertTrue(i1.equals(i1));
        assertTrue(i1.equals(i2));
        assertTrue(i2.equals(i1));
        assertFalse(i1.equals(i3));
        assertFalse(i3.equals(i1));
    }

}