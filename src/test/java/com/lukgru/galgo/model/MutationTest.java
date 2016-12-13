package com.lukgru.galgo.model;

import com.lukgru.galgo.mutation.Mutation;
import com.lukgru.galgo.mutation.MutationFunction;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Łukasz on 2016-12-03.
 */
public class MutationTest {

    @Test(expected = NullPointerException.class)
    public void shouldBeImpossibleToCreateWithNullMutationFunction() {
        new Mutation<>(null, 0.5d);
    }
    
    @Test(expected = NullPointerException.class)
    public void shouldBeImpossibleToCreateWithNullProbabilityOfMutation() {
        new Mutation<>(a -> a, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldBeIllegalToCreateWithNegativeProbability() {
        new Mutation<>(a -> a, -0.01d);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldBeIllegalToCreateWithProbabilityGreaterThanOne() {
        new Mutation<>(a -> a, 1.01d);
    }

    @Test
    public void shouldBeAbleToCreateValidMutation() {
        //given
        MutationFunction<Integer> mutationFunction = a -> a;
        Double mutationProbability = 0.05;

        //when
        Mutation<Integer> mutation = new Mutation<>(mutationFunction, mutationProbability);

        //then
        assertEquals(mutationFunction, mutation.getMutationFunction());
        assertEquals(mutationProbability, mutation.getProbability());
    }
}