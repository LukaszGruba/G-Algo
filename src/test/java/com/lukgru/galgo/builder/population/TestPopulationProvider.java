package com.lukgru.galgo.builder.population;

import com.lukgru.galgo.population.Individual;
import com.lukgru.galgo.population.Population;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Łukasz on 2016-12-15.
 */
public class TestPopulationProvider {

    public static <T> Population<T> generatePopulation(Supplier<T> supplier, int size) {
        List<T> individuals = Stream.generate(supplier)
                .limit(size)
                .collect(toList());
        return new Population<>(individuals);
    }

    public static <T> Population<T> generatePopulation(Supplier<T> valueSupplier, Supplier<Double> fitnessSupplier, int size) {
        Population<T> population = generatePopulation(valueSupplier, size);
        population.getIndividuals().forEach(i -> i.setFitnessScore(fitnessSupplier.get()));
        return population;
    }

    public static <T> Population<T> generatePopulation(T... values) {
        return new Population<>(Arrays.asList(values));
    }

    public static <T> PopulationMockBuilder<T> populationMockBuilder() {
        return new PopulationMockBuilder<>();
    }

    public static class PopulationMockBuilder<T> {
        private Collection<Individual<T>> individuals = new ArrayList<>();

        public PopulationMockBuilder<T> add(T value, double fitnessScore) {
            Individual<T> individual = mock(Individual.class);
            when(individual.getValue()).thenReturn(value);
            when(individual.getFitnessScore()).thenReturn(fitnessScore);
            individuals.add(individual);
            return this;
        }

        public Population<T> build() {
            Population population = mock(Population.class);
            when(population.getIndividuals()).thenReturn(individuals);
            when(population.size()).thenReturn(individuals.size());
            return population;
        }
    }

}
