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
                this::randomSolveSimpleFiveVariableEquationWithOneSolutionAndZeroTarget,
                this::bruteForceSolveSimpleFiveVariableEquationWithOneSolutionAndZeroTarget
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

    private void bruteForceSolveSimpleFiveVariableEquationWithOneSolutionAndZeroTarget() {
        //given
        Double target = 0.0;
        Double epsilon = 1.0;

        //then
        for (double d1 = -100.0; d1 < 100.0; d1 = d1 + 0.1) {
            for (double d2 = -100.0; d2 < 100.0; d2 = d2 + 0.1) {
                for (double d3 = -100.0; d3 < 100.0; d3 = d3 + 0.1) {
                    for (double d4 = -100.0; d4 < 100.0; d4 = d4 + 0.1) {
                        for (double d5 = -100.0; d5 < 100.0; d5 = d5 + 0.1) {
                            double result = pow(d1 - 90.0, 2) + pow(d2 + 20.0, 2) + pow(d3 + 18.0, 2) + pow(d4 - 55, 2) + pow(d5 - 78, 2);
                            if (meetsCriteria(result, target, epsilon)) return;
                        }
                    }
                }
            }
        }
    }

    @Test(timeout = 2 * MINUTE)
    public void simpleFiveVariablesEquationWithInfiniteSolutionsAndZeroTargetComparison() {
        //given
        boolean isFaster = isFaster(
                tests::solveFiveVariablesEquationWithInfiniteSolutionsAndZeroTarget,
                this::randomSolveFiveVariablesEquationWithInfiniteSolutionsAndZeroTarget,
                this::bruteForceSolveFiveVariablesEquationWithInfiniteSolutionsAndZeroTarget
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
                        (a.v[0] - 90.0) + (a.v[1] + 20.0) + (a.v[2] + 18.0) + (a.v[3] - 55) + (a.v[4] - 78))
                .filter(a -> meetsCriteria(a, target, epsilon))
                .findFirst();
    }

    private void bruteForceSolveFiveVariablesEquationWithInfiniteSolutionsAndZeroTarget() {
        //given
        Double target = 0.0;
        Double epsilon = 0.00001;

        //then
        for (double d1 = -100.0; d1 < 100.0; d1 = d1 + 0.1) {
            for (double d2 = -100.0; d2 < 100.0; d2 = d2 + 0.1) {
                for (double d3 = -100.0; d3 < 100.0; d3 = d3 + 0.1) {
                    for (double d4 = -100.0; d4 < 100.0; d4 = d4 + 0.1) {
                        for (double d5 = -100.0; d5 < 100.0; d5 = d5 + 0.1) {
                            double result = (d1 - 90.0) + (d2 + 20.0) + (d3 + 18.0) + (d4 - 55) + (d5 - 78);
                            if (meetsCriteria(result, target, epsilon)) {
                                System.out.println("yay");
                                return;
                            }
                        }
                    }
                }
            }
        }
    }
}
