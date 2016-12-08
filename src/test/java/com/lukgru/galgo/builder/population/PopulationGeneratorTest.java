package com.lukgru.galgo.builder.population;

import com.lukgru.galgo.model.Population;
import org.junit.Test;

import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by Łukasz on 2016-12-08.
 */
public class PopulationGeneratorTest {
    
    @Test(expected = NullPointerException.class)
    public void throwIfNullGeneratorFunction() {
        new PopulationGenerator<>(null);
    }

    @Test(expected = IllegalStateException.class)
    public void throwIfSizeNotProvided() {
        //given
        Supplier<Integer> factoryMethod = () -> 1;

        //when
        PopulationGenerator<Integer> generator = new PopulationGenerator<>(factoryMethod);

        //then
        generator.getPopulation();
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIfNegativeSize() {
        //given
        int size = -1;
        Supplier<Integer> factoryMethod = () -> 1;

        //when
        new PopulationGenerator<>(factoryMethod).withSize(size);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwIfZeroSize() {
        //given
        int size = -1;
        Supplier<Integer> factoryMethod = () -> 1;

        //when
        new PopulationGenerator<>(factoryMethod).withSize(size);
    }


    @Test
    public void generateProperly() {
        //given
        final int size = 10;
        final int allElementsValue = 1;
        Supplier<Integer> factoryMethod = mock(Supplier.class);
        when(factoryMethod.get()).thenReturn(allElementsValue);

        //when
        PopulationAccessor<Integer> generator = new PopulationGenerator<>(factoryMethod).withSize(size);
        Population<Integer> population = generator.getPopulation();

        //then
        assertEquals(size, population.size());
        verify(factoryMethod, times(size)).get();
        population.getIndividuals().stream()
                .forEach(individual -> assertEquals(allElementsValue, individual.getValue().intValue()));
    }
}