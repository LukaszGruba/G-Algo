package com.lukgru.galgo.runner.reproduction;

import com.lukgru.galgo.crossover.CrossoverFunction;
import com.lukgru.galgo.population.Individual;
import com.lukgru.galgo.population.Population;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static com.lukgru.galgo.builder.population.TestPopulationProvider.generatePopulation;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * Created by Lukasz on 15.12.2016.
 */
public class SimpleReproductionRunnerTest {

    @Test
    public void twoParentsShouldProduceTwoChildren() {
        //given
        Integer parent1 = 100;
        Integer parent2 = 1000;
        Population<Integer> parents = generatePopulation(parent1, parent2);
        CrossoverFunction<Integer> crossoverFunction = mock(CrossoverFunction.class);
        when(crossoverFunction.apply(anyInt(), anyInt())).thenReturn(1);

        //when
        Population<Integer> children = new SimpleReproductionRunner<>(crossoverFunction).reproduce(parents);

        //then
        assertEquals(2, children.size());
    }

    @Test()
    public void firstChildShouldBeCreatedByCrossingFirstParentWithSecondParent() {
        //given
        Integer parent1 = 100;
        Integer parent2 = 1000;
        CrossoverFunction<Integer> crossoverFunction = mock(CrossoverFunction.class);
        Population<Integer> parents = generatePopulation(parent1, parent2);

        //when
        new SimpleReproductionRunner<>(crossoverFunction).reproduce(parents);

        //then
        verify(crossoverFunction, times(1)).apply(parent1, parent2);
    }

    @Test
    public void secondChildShouldBeCreatedByCrossingSecondParentWithFirstParent() {
        //given
        Integer parent1 = 100;
        Integer parent2 = 1000;
        CrossoverFunction<Integer> crossoverFunction = mock(CrossoverFunction.class);
        Population<Integer> parents = generatePopulation(parent1, parent2);

        //when
        new SimpleReproductionRunner<>(crossoverFunction).reproduce(parents);

        //then
        verify(crossoverFunction, times(1)).apply(parent2, parent1);
    }

    @Test
    public void shouldReproduceIntsProperly() {
        //given
        Population<Integer> initialPopulation = generatePopulation(
                1,2,
                3,4,
                5,6,
                7,8,
                9,10);
        CrossoverFunction<Integer> crossoverFunction = (a, b) -> (3*a + b) / 2;

        //when
        Population<Integer> children = new SimpleReproductionRunner<>(crossoverFunction).reproduce(initialPopulation);

        //then
        List<Integer> childrenList = children.getIndividuals().stream().map(Individual::getValue).collect(toList());
        assertThat(childrenList, hasItem(2));
        assertThat(childrenList, hasItem(3));
        assertThat(childrenList, hasItem(6));
        assertThat(childrenList, hasItem(7));
        assertThat(childrenList, hasItem(10));
        assertThat(childrenList, hasItem(11));
        assertThat(childrenList, hasItem(14));
        assertThat(childrenList, hasItem(15));
        assertThat(childrenList, hasItem(18));
        assertThat(childrenList, hasItem(19));
    }

    @Test
    public void shouldReproduceObjectsProperly() {
        //given
        Population<ComplexObj> initialPopulation = generatePopulation(
                new ComplexObj(100, "aabbcc", 600.0, asList(1, 2, 3, 4)),
                new ComplexObj(200, "ccbbaa", 500.0, asList(2, 3, 4, 5)),
                new ComplexObj(-300, "abba", 12.75, asList(1, 2, 3, 4)),
                new ComplexObj(4000, "led zeppelin", 89.1, asList(12, 11, 10, 9)),
                new ComplexObj(100000, "", 200.0, asList(89, 0)),
                new ComplexObj(1000, "To be or not to be?", -3000.0, Collections.emptyList())
        );
        CrossoverFunction<ComplexObj> crossoverFunction = (a, b) -> {
            long longVal = (a.longVal + b.longVal) / 2;
            String stringVal = a.stringVal + " " + b.stringVal;
            Double doubleVal = a.doubleVal - b.doubleVal;
            List<Integer> listVal = Stream.concat(a.listVal.stream(), b.listVal.stream()).collect(toList());
            return new ComplexObj(longVal, stringVal, doubleVal, listVal);
        };

        //when
        Population<ComplexObj> children = new SimpleReproductionRunner<>(crossoverFunction).reproduce(initialPopulation);

        //then
        List<ComplexObj> childrenList = children.getIndividuals().stream().map(Individual::getValue).collect(toList());
        assertThat(childrenList, hasItem(new ComplexObj(150, "aabbcc ccbbaa", 100.0, asList(1, 2, 3, 4, 2, 3, 4, 5))));
        assertThat(childrenList, hasItem(new ComplexObj(150, "ccbbaa aabbcc", -100.0, asList(2, 3, 4, 5, 1, 2, 3, 4))));
        assertThat(childrenList, hasItem(new ComplexObj(1850, "abba led zeppelin", -76.35, asList(1, 2, 3, 4, 12, 11, 10, 9))));
        assertThat(childrenList, hasItem(new ComplexObj(1850, "led zeppelin abba", 76.35, asList(12, 11, 10, 9, 1, 2, 3, 4))));
        assertThat(childrenList, hasItem(new ComplexObj(50500, "To be or not to be? ", 3200.0, asList(89, 0))));
        assertThat(childrenList, hasItem(new ComplexObj(50500, " To be or not to be?", -3200.0, asList(89, 0))));

    }

    private class ComplexObj {
        private final long longVal;
        private final String stringVal;
        private final Double doubleVal;
        private final List<Integer> listVal;

        private ComplexObj(long longVal, String stringVal, Double doubleVal, List<Integer> listVal) {
            this.longVal = longVal;
            this.stringVal = stringVal;
            this.doubleVal = doubleVal;
            this.listVal = listVal;
        }
    }

}