package com.lukgru.galgo.model;

import com.lukgru.galgo.fitness.FitnessFunction;
import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.*;

/**
 * Created by Lukasz on 02.12.2016.
 */
public class FitnessFunctionTest {

    @Test(expected = IllegalArgumentException.class)
    public void cannotCreateFitnessFunctionWithNullFitnessFunction() {
        new FitnessFunction<>(null, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCreateFitnessFunctionWithNullTarget() {
        new FitnessFunction<>(a -> 1, null);
    }
    
    @Test
    public void shouldSetPropertiesProperly() {
        //given
        Function<Object, Integer> function = a -> 1;
        Integer target = 1;

        //when
        FitnessFunction<Object> fitnessFunction = new FitnessFunction<>(function, target);

        //then
        assertEquals(function, fitnessFunction.getFitnessFunction());
        assertEquals(target, fitnessFunction.getTarget());
    }
}