package com.lukgru.galgo.heavy.functions;

import org.junit.Test;

import java.util.stream.Stream;

import static com.lukgru.galgo.heavy.HeavyTestUtils.MINUTE;
import static com.lukgru.galgo.heavy.HeavyTestUtils.compareExecution;
import static com.lukgru.galgo.heavy.HeavyTestUtils.meetsCriteria;
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
        long timeDiff = compareExecution(tests::solveSimpleSingleVariableEquationWithZeroTarget,
                this::randomSolveSimpleSingleVariableEquationWithZeroTarget);
        //then
        assertTrue(timeDiff < 0);
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
        long timeDiff = compareExecution(tests::solveSimpleSingleVariableQuadraticEquationWithOneSolutionAndZeroTarget,
                this::randomSolveSimpleSingleVariableQuadraticEquationWithOneSolutionAndZeroTarget);
        //then
        assertTrue(timeDiff < 0);
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
        long timeDiff = compareExecution(tests::solveSimpleSingleVariableQuadraticEquationWithTwoDistantSolutionsAndZeroTarget,
                this::randomSolveSimpleSingleVariableQuadraticEquationWithTwoDistantSolutionsAndZeroTarget);
        //then
        assertTrue(timeDiff < 0);
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
