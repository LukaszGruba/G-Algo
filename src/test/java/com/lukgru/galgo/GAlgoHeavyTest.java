package com.lukgru.galgo;

import com.lukgru.galgo.runner.GenerationResult;
import org.junit.Test;

import java.util.Arrays;

import static java.lang.Math.pow;
import static java.lang.Math.random;
import static org.junit.Assert.assertTrue;

/**
 * Created by Lukasz on 04.12.2016.
 */
//TODO: create more comparisons with brute force or random
//TODO: add more challenging tests (e.g. with non zero target, single solution with many variables etc.)
public class GAlgoHeavyTest {

    private static final long MINUTE = 60 * 1000L;
    
    @Test(timeout = MINUTE)
    public void solveSimpleSingleVariableEquationWithZeroTarget() {
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
    public void solveSimpleSingleVariableQuadraticEquationWithOneSolutionAndZeroTarget() {
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
    public void solveSimpleSingleVariableQuadraticEquationWithTwoDistantSolutionsAndZeroTarget() {
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

    @Test(timeout = MINUTE)
    public void solveSimpleTwoVariableEquationWithZeroTarget() {
        //given
        Double target = 0.0;
        Double epsilon = 0.0001;

        //when
        GenerationResult<VariablesTuple> generationResult = GAlgo
                .fromGeneratedPopulation(() -> new VariablesTuple(
                        random() * 200 - 100,
                        random() * 200 - 100
                ))
                .withSize(1000)
                .withFitnessFunction(a -> (a.v[0] - 90.0) * (a.v[1] + 20.0))
                .targeting(target)
                .withEpsilon(epsilon)
                .withCrossover((a, b) -> new VariablesTuple(
                        a.v[0] + (b.v[0] / 10),
                        a.v[1] + (b.v[1] / 10)))
                .withMutationFunction(a -> new VariablesTuple(
                        a.v[0] - 0.5d + random(),
                        a.v[1] - 0.5d + random()
                ))
                .withMutationProbability(0.1)
                .runner().generate();

        //then
        VariablesTuple solution = generationResult.getBest().getValue();
        Double fitness = generationResult.getBest().getFitnessScore();
        System.out.println("Solution = " + solution + ", fitness = " + fitness + ", iterations = " + generationResult.getIterations());
        assertTrue(meetsCriteria(fitness, target, epsilon));
    }

    @Test(timeout = MINUTE)
    public void solveSimpleThreeVariableEquationWithZeroTarget() {
        //given
        Double target = 0.0;
        Double epsilon = 0.0001;

        //when
        GenerationResult<VariablesTuple> generationResult = GAlgo
                .fromGeneratedPopulation(() -> new VariablesTuple(
                        random() * 200 - 100,
                        random() * 200 - 100,
                        random() * 200 - 100
                ))
                .withSize(1000)
                .withFitnessFunction(a -> (a.v[0] - 90.0) * (a.v[1] + 20.0) * (a.v[2] + 18.0))
                .targeting(target)
                .withEpsilon(epsilon)
                .withCrossover((a, b) -> new VariablesTuple(
                        (a.v[0]) + (b.v[0] / 10),
                        (a.v[1]) + (b.v[1] / 10),
                        (a.v[2]) + (b.v[2] / 10)
                ))
                .withMutationFunction(a -> new VariablesTuple(
                        a.v[0] - 0.5d + random(),
                        a.v[1] - 0.5d + random(),
                        a.v[2] - 0.5d + random()
                ))
                .withMutationProbability(0.1)
                .runner().generate();

        //then
        VariablesTuple solution = generationResult.getBest().getValue();
        Double fitness = generationResult.getBest().getFitnessScore();
        System.out.println("Solution = " + solution + ", fitness = " + fitness + ", iterations = " + generationResult.getIterations());
        assertTrue(meetsCriteria(fitness, target, epsilon));
    }

    @Test(timeout = MINUTE)
    public void solveSimpleFiveVariableEquationWithOneSolutionAndZeroTarget() {
        //given
        Double target = 0.0;
        Double epsilon = 1.0;

        //when
        GenerationResult<VariablesTuple> generationResult = GAlgo
                .fromGeneratedPopulation(() -> new VariablesTuple(
                        random() * 200 - 100,
                        random() * 200 - 100,
                        random() * 200 - 100,
                        random() * 200 - 100,
                        random() * 200 - 100
                ))
                .withSize(100)
                .withFitnessFunction(a ->
                        pow(a.v[0] - 90.0, 2) + pow(a.v[1] + 20.0, 2) + pow(a.v[2] + 18.0, 2) + pow(a.v[3] - 55, 2) + pow(a.v[4] - 78, 2))
                .targeting(target)
                .withEpsilon(epsilon)
                .withCrossover((a, b) -> new VariablesTuple(
                        (a.v[0]) + (b.v[0] / 10),
                        (a.v[1]) + (b.v[1] / 10),
                        (a.v[2]) + (b.v[2] / 10),
                        (a.v[3]) + (b.v[3] / 10),
                        (a.v[4]) + (b.v[4] / 10)
                ))
                .withMutationFunction(a -> new VariablesTuple(
                        a.v[0] - 0.5d + random(),
                        a.v[1] - 0.5d + random(),
                        a.v[2] - 0.5d + random(),
                        a.v[3] - 0.5d + random(),
                        a.v[4] - 0.5d + random()
                ))
                .withMutationProbability(0.1)
                .runner().generate();

        //then
        VariablesTuple solution = generationResult.getBest().getValue();
        Double fitness = generationResult.getBest().getFitnessScore();
        System.out.println("Solution = " + solution + ", fitness = " + fitness + ", iterations = " + generationResult.getIterations());
        assertTrue(meetsCriteria(fitness, target, epsilon));
    }

    private boolean meetsCriteria(Double fitness, Double target, Double epsilon) {
        return Math.abs(fitness - target) < epsilon;
    }

    private class VariablesTuple {
        private double[] v;

        public VariablesTuple(double... v) {
            this.v = v;
        }

        @Override
        public String toString() {
            return "VariablesTuple{" +
                    "values=" + Arrays.toString(v) +
                    '}';
        }
    }
}