package com.lukgru.galgo;

import com.lukgru.galgo.mutation.MutationFunction;
import com.lukgru.galgo.runner.GenerationResult;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Lukasz on 04.12.2016.
 */
//TODO: run this test in some special maven goal
public class GAlgoHeavyTest {

    private static final long TIMEOUT = 60 * 1000L; //one minute
    
    @Test(timeout = TIMEOUT)
    public void solveSimpleSingleVariableEquationWithZeroTarget() {
        //given
        Double target = 0.0;
        Double epsilon = 0.0001;

        //when
        GenerationResult<Double> generationResult = GAlgo
                .fromGeneratedPopulation(() -> Math.random() * 200 - 100)
                .withSize(50)
                .withFitnessFunction(x -> (14.0 * x) - 28)
                .targeting(target)
                .withEpsilon(epsilon)
                .withCrossover((a, b) -> a + (b / 2))
                .withMutationFunction(a -> -a).withMutationProbability(0.1)
                .runner().generate();

        //then
        Double solution = generationResult.getBest().getValue();
        Double fitness = generationResult.getBest().getFitnessScore();
        System.out.println("Solution = " + solution + ", fitness = " + fitness + ", iterations = " + generationResult.getIterations());

        assertTrue(meetsCriteria(fitness, target, epsilon));
    }

    @Test(timeout = TIMEOUT)
    public void solveSimpleSingleVariableQuadraticEquationWithOneSolutionAndZeroTarget() {
        //given
        Double target = 0.0;
        Double epsilon = 0.0001;

        //when
        GenerationResult<Double> generationResult = GAlgo
                .fromGeneratedPopulation(() -> Math.random() * 200 - 100)
                .withSize(100)
                .withFitnessFunction(x -> (x - 1) * (x - 1))
                .targeting(target)
                .withEpsilon(epsilon)
                .withCrossover((a, b) -> a + (b / 2))
                .withMutationFunction(a -> -a).withMutationProbability(0.1)
                .runner().generate();

        //then
        Double solution = generationResult.getBest().getValue();
        Double fitness = generationResult.getBest().getFitnessScore();
        System.out.println("Solution = " + solution + ", fitness = " + fitness + ", iterations = " + generationResult.getIterations());
        assertTrue(meetsCriteria(fitness, target, epsilon));
    }

    @Test(timeout = TIMEOUT)
    public void solveSimpleSingleVariableQuadraticEquationWithTwoDistantSolutionsAndZeroTarget() {
        //given
        Double target = 0.0;
        Double epsilon = 0.01;

        //when
        GenerationResult<Double> generationResult = GAlgo
                .fromGeneratedPopulation(() -> Math.random() * 200 - 100)
                .withSize(50)
                .withFitnessFunction(x -> (x - 90.0) * (x + 20.0))
                .targeting(target)
                .withEpsilon(epsilon)
                .withCrossover((a, b) -> a + (b / 2))
                .withMutationFunction(a -> -a).withMutationProbability(0.1)
                .runner().generate();

        //then
        Double solution = generationResult.getBest().getValue();
        Double fitness = generationResult.getBest().getFitnessScore();
        System.out.println("Solution = " + solution + ", fitness = " + fitness + ", iterations = " + generationResult.getIterations());
        assertTrue(meetsCriteria(fitness, target, epsilon));
    }

    @Test(timeout = TIMEOUT)
    public void solveSimpleTwoVariableEquationWithZeroTarget() {
        //given
        MutationFunction<VariablesPair> mutationFunction = a -> new VariablesPair(
                a.x * (0.5d + Math.random()),
                a.y * (0.5d + Math.random())
        );
        Double target = 0.0;
        Double epsilon = 0.0001;

        //when
        GenerationResult<VariablesPair> generationResult = GAlgo
                .fromGeneratedPopulation(() -> new VariablesPair(Math.random() * 200 - 100, Math.random() * 200 - 100))
                .withSize(100)
                .withFitnessFunction(v -> (v.x - 90.0) * (v.y + 20.0))
                .targeting(target)
                .withEpsilon(epsilon)
                .withCrossover((a, b) -> new VariablesPair(a.x + (b.x / 2), a.y + (b.y / 2)))
                .withMutationFunction(mutationFunction)
                .withMutationProbability(0.1)
                .runner().generate();

        //then
        VariablesPair solution = generationResult.getBest().getValue();
        Double fitness = generationResult.getBest().getFitnessScore();
        System.out.println("Solution = " + solution + ", fitness = " + fitness + ", iterations = " + generationResult.getIterations());
        assertTrue(meetsCriteria(fitness, target, epsilon));
    }

    @Test(timeout = TIMEOUT)
    public void solveSimpleThreeVariableEquationWithZeroTarget() {
        //given
        Double target = 0.0;
        Double epsilon = 0.0001;

        //when
        GenerationResult<VariablesTriplet> generationResult = GAlgo
                .fromGeneratedPopulation(() -> new VariablesTriplet(
                        Math.random() * 200 - 100,
                        Math.random() * 200 - 100,
                        Math.random() * 200 - 100
                ))
                .withSize(1000)
                .withFitnessFunction(v -> (v.x - 90.0) * (v.y + 20.0) * (v.z + 18.0))
                .targeting(target)
                .withEpsilon(epsilon)
                .withCrossover((a, b) -> new VariablesTriplet(
                        (a.x) + (b.x / 2),
                        (a.y) + (b.y / 2),
                        (a.z) + (b.z / 2)
                ))
                .withMutationFunction(a -> new VariablesTriplet(
                        a.x * (0.5d + Math.random()),
                        a.y * (0.5d + Math.random()),
                        a.z * (0.5d + Math.random())
                ))
                .withMutationProbability(0.1)
                .runner().generate();

        //then
        VariablesTriplet solution = generationResult.getBest().getValue();
        Double fitness = generationResult.getBest().getFitnessScore();
        System.out.println("Solution = " + solution + ", fitness = " + fitness + ", iterations = " + generationResult.getIterations());
        assertTrue(meetsCriteria(fitness, target, epsilon));
    }

    //TODO: add more challenging tests (e.g. with non zero target, single solution with many variables etc.)

    private boolean meetsCriteria(Double fitness, Double target, Double epsilon) {
        return Math.abs(fitness - target) < epsilon;
    }

    private class VariablesPair {
        private double x;
        private double y;

        public VariablesPair(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "[x = " + x + ", y = " + y + "]";
        }
    }

    private class VariablesTriplet {
        private double x;
        private double y;
        private double z;

        public VariablesTriplet(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public String toString() {
            return "[x = " + x + ", y = " + y + ", z = " + z + "]";
        }
    }
}