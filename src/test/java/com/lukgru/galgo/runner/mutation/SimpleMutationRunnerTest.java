package com.lukgru.galgo.runner.mutation;

import com.lukgru.galgo.mutation.Mutation;
import com.lukgru.galgo.mutation.MutationFunction;
import com.lukgru.galgo.population.Individual;
import com.lukgru.galgo.population.Population;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

/**
 * Created by Lukasz on 14.12.2016.
 */
public class SimpleMutationRunnerTest {

    private static final int ONE = 1;

    @Test
    public void shouldMutateWithProperProbability() {
        //given
        Double probability = 0.1d;
        MutationFunction<Integer> mutationFunction = a -> a + 1;
        Mutation<Integer> mutation = new Mutation<>(mutationFunction, probability);
        Population<Integer> population = new Population<>(populationOfOnes(1000));
        
        //when
        new SimpleMutationRunner<>(mutation).mutate(population);
        
        //then
        Integer sum = population.getIndividuals().stream()
                .map(Individual::getValue)
                .reduce(0, (a, b) -> a + b);
        assertTrue(1050 < sum && sum < 1150);
    }
    
    private Collection<Integer> populationOfOnes(int size) {
        Collection<Integer> ones = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            ones.add(ONE);
        }
        return ones;
    }

}