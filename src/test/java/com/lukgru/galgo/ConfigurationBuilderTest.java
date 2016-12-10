package com.lukgru.galgo;

import com.lukgru.galgo.mutation.MutationFunction;
import com.lukgru.galgo.population.PopulationAccessor;
import com.lukgru.galgo.crossover.CrossoverFunction;
import com.lukgru.galgo.runner.GeneticAlgorithmRunner;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.function.Function;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Łukasz on 2016-12-08.
 */
@RunWith(MockitoJUnitRunner.class)
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
    public void throwIfNoPopulationAccessor() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Population accessor cannot be null.");

        ConfigurationBuilder<Integer> configurationBuilder = prepareBuilder();
        configurationBuilder.withPopulationAccessor(null).runner();
    }

    @Test
    public void throwIfNoFitnessFunction() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Fitness function cannot be null.");

        ConfigurationBuilder<Integer> configurationBuilder = prepareBuilder();
        configurationBuilder.withFitnessFunction(null).runner();
    }

    @Test
    public void throwIfNoFitnessTarget() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Fitness target cannot be null.");

        ConfigurationBuilder<Integer> configurationBuilder = prepareBuilder();
        configurationBuilder.targeting(null).runner();
    }

    @Test
    public void throwIfNoCrossoverFunction() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Crossover function cannot be null.");

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