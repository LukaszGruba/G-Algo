package com.lukgru.galgo.heavy.functions;

import org.junit.Test;

import java.util.stream.Stream;

import static com.lukgru.galgo.heavy.HeavyTestUtils.*;
import static java.lang.Math.pow;
import static java.lang.Math.random;
import static org.junit.Assert.assertTrue;

/**
 * Created by Lukasz on 10.01.2017.
 */
public class FiveVariablesComparisonTest {

    private FiveVariablesHeavyTest tests = new FiveVariablesHeavyTest();

    @Test(timeout = 2 * MINUTE)
    public void simpleFiveVariableEquationWithOneSolutionAndZeroTargetComparison() {
        //given
        boolean isFaster = isFaster(
                tests::solveSimpleFiveVariableEquationWithOneSolutionAndZeroTarget,
                this::randomSolveSimpleFiveVariableEquationWithOneSolutionAndZeroTarget
        );

        //then
        assertTrue(isFaster);
    }

    private void randomSolveSimpleFiveVariableEquationWithOneSolutionAndZeroTarget() {
        //given
        Double target = 0.0;
        Double epsilon = 1.0;

        //then
        Stream.generate(() -> new VariablesTuple(
                random() * 200 - 100,
                random() * 200 - 100,
                random() * 200 - 100,
                random() * 200 - 100,
                random() * 200 - 100
        ))
                .map(a ->
                        pow(a.v[0] - 90.0, 2) + pow(a.v[1] + 20.0, 2) + pow(a.v[2] + 18.0, 2) + pow(a.v[3] - 55, 2) + pow(a.v[4] - 78, 2))
                .filter(a -> meetsCriteria(a, target, epsilon))
                .findFirst();
    }

    @Test(timeout = 2 * MINUTE)
    public void simpleFiveVariablesEquationWithInfiniteSolutionsAndZeroTargetComparison() {
        //given
        boolean isFaster = isFaster(
                tests::solveFiveVariablesEquationWithInfiniteSolutionsAndZeroTarget,
                this::randomSolveFiveVariablesEquationWithInfiniteSolutionsAndZeroTarget
        );

        //then
        assertTrue(isFaster);
    }

    private void randomSolveFiveVariablesEquationWithInfiniteSolutionsAndZeroTarget() {
        //given
        Double target = 0.0;
        Double epsilon = 0.00001;

        //then
        Stream.generate(() -> new VariablesTuple(
                random() * 200 - 100,
                random() * 200 - 100,
                random() * 200 - 100,
                random() * 200 - 100,
                random() * 200 - 100
        ))
                .map(a ->
                        pow(a.v[0] - 90.0, 2) + pow(a.v[1] + 20.0, 2) + pow(a.v[2] + 18.0, 2) + pow(a.v[3] - 55, 2) + pow(a.v[4] - 78, 2))
                .filter(a -> meetsCriteria(a, target, epsilon))
                .findFirst();
    }
}
