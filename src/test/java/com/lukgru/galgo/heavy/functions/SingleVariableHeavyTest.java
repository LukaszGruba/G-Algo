package com.lukgru.galgo.heavy.functions;

import com.lukgru.galgo.GAlgo;
import com.lukgru.galgo.runner.GenerationResult;
import org.junit.Test;

import static com.lukgru.galgo.heavy.HeavyTestUtils.MINUTE;
import static com.lukgru.galgo.heavy.HeavyTestUtils.meetsCriteria;
import static java.lang.Math.random;
import static org.junit.Assert.assertTrue;

/**
 * Created by Lukasz on 05.01.2017.
 */
public class SingleVariableHeavyTest {

    @Test(timeout = MINUTE)
    public void singleVariableEquationWithZeroTarget() {
        //given
        Double target = 0.0;
        Double epsilon = 0.0001;

        //when
        GenerationResult<Double> generationResult = GAlgo
                .fromGeneratedPopulation(() -> random() * 200 - 100)
                .withSize(50)
                .withFitnessFunction(x -> (14.0 * x) - 28)
                .targeting(target)
                .withEpsilon(epsilon)
                .withCrossover((a, b) -> a + (b / 10))
                .withMutationFunction(a -> a - 0.5 + random()).withMutationProbability(0.1)
                .runner().generate();

        //then
        Double solution = generationResult.getBest().getValue();
        Double fitness = generationResult.getBest().getFitnessScore();
        System.out.println("Solution = " + solution + ", fitness = " + fitness + ", iterations = " + generationResult.getIterations());

        assertTrue(meetsCriteria(fitness, target, epsilon));
    }

    @Test(timeout = MINUTE)
    public void singleVariableQuadraticEquationWithOneSolutionAndZeroTarget() {
        //given
        Double target = 0.0;
        Double epsilon = 0.0001;

        //when
        GenerationResult<Double> generationResult = GAlgo
                .fromGeneratedPopulation(() -> random() * 200 - 100)
                .withSize(100)
                .withFitnessFunction(x -> (x - 1) * (x - 1))
                .targeting(target)
                .withEpsilon(epsilon)
                .withCrossover((a, b) -> a + (b / 10))
                .withMutationFunction(a -> a - 0.5 + random()).withMutationProbability(0.1)
                .runner().generate();

        //then
        Double solution = generationResult.getBest().getValue();
        Double fitness = generationResult.getBest().getFitnessScore();
        System.out.println("Solution = " + solution + ", fitness = " + fitness + ", iterations = " + generationResult.getIterations());
        assertTrue(meetsCriteria(fitness, target, epsilon));
    }

    @Test(timeout = MINUTE)
    public void singleVariableQuadraticEquationWithTwoDistantSolutionsAndZeroTarget() {
        //given
        Double target = 0.0;
        Double epsilon = 0.01;

        //when
        GenerationResult<Double> generationResult = GAlgo
                .fromGeneratedPopulation(() -> random() * 200 - 100)
                .withSize(50)
                .withFitnessFunction(x -> (x - 90.0) * (x + 20.0))
                .targeting(target)
                .withEpsilon(epsilon)
                .withCrossover((a, b) -> a + (b / 10))
                .withMutationFunction(a -> a - 0.5 + random()).withMutationProbability(0.1)
                .runner().generate();

        //then
        Double solution = generationResult.getBest().getValue();
        Double fitness = generationResult.getBest().getFitnessScore();
        System.out.println("Solution = " + solution + ", fitness = " + fitness + ", iterations = " + generationResult.getIterations());
        assertTrue(meetsCriteria(fitness, target, epsilon));
    }
}
