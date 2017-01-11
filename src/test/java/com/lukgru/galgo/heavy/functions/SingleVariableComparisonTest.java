package com.lukgru.galgo.heavy.functions;

import org.junit.Test;

import java.util.stream.Stream;

import static com.lukgru.galgo.heavy.HeavyTestUtils.*;
import static java.lang.Math.random;
import static org.junit.Assert.assertTrue;

/**
 * Created by Lukasz on 10.01.2017.
 */
public class SingleVariableComparisonTest {

    private SingleVariableHeavyTest tests = new SingleVariableHeavyTest();

    @Test(timeout = 2 * MINUTE)
    public void singleVariableEquationWithZeroTargetComparison() {
        //when
        boolean isFaster = isFaster(tests::solveSimpleSingleVariableEquationWithZeroTarget,
                this::randomSolveSimpleSingleVariableEquationWithZeroTarget);
        //then
        assertTrue(isFaster);
    }

    private void randomSolveSimpleSingleVariableEquationWithZeroTarget() {
        //given
        Double target = 0.0;
        Double epsilon = 0.0001;

        //then
        Stream.generate(() -> random() * 200 - 100)
                .map(x -> (14.0 * x) - 28)
                .filter(x -> meetsCriteria(x, target, epsilon))
                .findFirst();
    }

    @Test(timeout = 2 * MINUTE)
    public void singleVariableQuadraticEquationWithOneSolutionAndZeroTargetComparison() {
        //when
        boolean isFaster = isFaster(tests::solveSimpleSingleVariableQuadraticEquationWithOneSolutionAndZeroTarget,
                this::randomSolveSimpleSingleVariableQuadraticEquationWithOneSolutionAndZeroTarget);
        //then
        assertTrue(isFaster);
    }

    private void randomSolveSimpleSingleVariableQuadraticEquationWithOneSolutionAndZeroTarget() {
        //given
        Double target = 0.0;
        Double epsilon = 0.0001;

        //then
        Stream.generate(() -> random() * 200 - 100)
                .map(x -> (x - 1) * (x - 1))
                .filter(x -> meetsCriteria(x, target, epsilon))
                .findFirst();
    }

    @Test(timeout = 2 * MINUTE)
    public void singleVariableQuadraticEquationWithTwoDistantSolutionsAndZeroTargetComparison() {
        //when
        boolean isFaster = isFaster(tests::solveSimpleSingleVariableQuadraticEquationWithTwoDistantSolutionsAndZeroTarget,
                this::randomSolveSimpleSingleVariableQuadraticEquationWithTwoDistantSolutionsAndZeroTarget);
        //then
        assertTrue(isFaster);
    }

    private void randomSolveSimpleSingleVariableQuadraticEquationWithTwoDistantSolutionsAndZeroTarget() {
        //given
        Double target = 0.0;
        Double epsilon = 0.0001;

        //then
        Stream.generate(() -> random() * 200 - 100)
                .map(x -> (x - 90.0) * (x + 20.0))
                .filter(x -> meetsCriteria(x, target, epsilon))
                .findFirst();

    }
}
