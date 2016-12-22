package com.lukgru.galgo.model;

import com.lukgru.galgo.fitness.FitnessFunction;
import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.*;

/**
 * Created by Lukasz on 02.12.2016.
 */
public class FitnessFunctionTest {

    @Test(expected = NullPointerException.class)
    public void cannotCreateFitnessFunctionWithNullFitnessFunction() {
        new FitnessFunction<>(null, 1.0);
    }

    @Test(expected = NullPointerException.class)
    public void cannotCreateFitnessFunctionWithNullTarget() {
        new FitnessFunction<>(a -> 1.0, null);
    }
    
    @Test
    public void shouldSetPropertiesProperly() {
        //given
        Function<Object, Double> function = a -> 1.0;
        Double target = 1.0;

        //when
        FitnessFunction<Object> fitnessFunction = new FitnessFunction<>(function, target);

        //then
        assertEquals(function, fitnessFunction.getFitnessFunction());
        assertEquals(target, fitnessFunction.getTarget());
    }
}