package com.lukgru.galgo;

import com.lukgru.galgo.builder.mutation.MutationFunction;
import com.lukgru.galgo.builder.population.PopulationAccessor;
import com.lukgru.galgo.model.CrossoverFunction;
import com.lukgru.galgo.runner.GeneticAlgorithmRunner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;

import java.util.function.Function;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Łukasz on 2016-12-08.
 */
public class ConfigurationBuilderTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private PopulationAccessor<Integer> populationAccessor;

    @Mock
    private Function<Integer, Integer> fitnessFunction;

    @Mock
    private CrossoverFunction<Integer> crossoverFunction;

    @Mock
    private MutationFunction<Integer> mutationFunction;

    @Test
    public void throwIfNoPopulation() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Population has to be provided.");

        ConfigurationBuilder<Integer> configurationBuilder = prepareBuilder();
        configurationBuilder.withPopulationAccessor(null).runner();
    }

    @Test
    public void throwIfNoFitnessFunction() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Fitness function has to be provided.");

        ConfigurationBuilder<Integer> configurationBuilder = prepareBuilder();
        configurationBuilder.withFitnessFunction(null);
    }

    @Test
    public void throwIfNoCrossoverFunction() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Crossover function has to be provided.");

        ConfigurationBuilder<Integer> configurationBuilder = prepareBuilder();
        configurationBuilder.withCrossover(null).runner();
    }

    @Test
    public void buildSuccessfullyWithoutMutationProvided() {
        //given
        ConfigurationBuilder<Integer> configurationBuilder = prepareBuilder();

        //when
        GeneticAlgorithmRunner<Integer> runner = configurationBuilder.withMutationFunction(null)
                .withMutationProbability(null)
                .runner();

        //then
        assertNotNull(runner);
    }

    private ConfigurationBuilder<Integer> prepareBuilder() {
        return new ConfigurationBuilder<Integer>()
                .withPopulationAccessor(populationAccessor)
                .withFitnessFunction(fitnessFunction).targeting(0)
                .withCrossover(crossoverFunction)
                .withMutationFunction(mutationFunction)
                .withMutationProbability(0.05d);
    }
}