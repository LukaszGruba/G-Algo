package com.lukgru.galgo.heavy.functions;

import org.junit.Test;

import java.util.stream.Stream;

import static com.lukgru.galgo.heavy.HeavyTestUtils.*;
import static java.lang.Math.random;
import static org.junit.Assert.assertTrue;

/**
 * Created by Lukasz on 10.01.2017.
 */
public class ThreeVariablesComparisonTest {

    private ThreeVariablesHeavyTest test = new ThreeVariablesHeavyTest();

    @Test(timeout = 2 * MINUTE)
    public void threeVariableEquationWithZeroTargetComparison() {
        //given
        boolean isFaster = isFaster(
                test::threeVariableEquationWithZeroTarget,
                this::threeVariableEquationWithZeroTargetRandom,
                this::threeVariableEquationWithZeroTargetBruteForce);

        //then
        assertTrue(isFaster);
    }

    private void threeVariableEquationWithZeroTargetRandom() {
        //given
        Double target = 0.0;
        Double epsilon = 0.0001;

        //then
        Stream.generate(() -> new VariablesTuple(
                random() * 200 - 100,
                random() * 200 - 100,
                random() * 200 - 100
        ))
                .map(a -> (a.v[0] - 90.0) * (a.v[1] + 20.0) * (a.v[2] + 18.0))
                .filter(a -> meetsCriteria(a, target, epsilon))
                .findFirst();
    }

    private void threeVariableEquationWithZeroTargetBruteForce() {
        //given
        Double target = 0.0;
        Double epsilon = 0.0001;

        //then
        for (double d1 = -100.0 ; d1 < 100.0 ; d1 = d1 + epsilon) {
            for (double d2 = -100.0 ; d2 < 100.0 ; d2 = d2 + epsilon) {
                for (double d3 = -100.0 ; d3 < 100.0 ; d3 = d3 + epsilon) {
                    double result = (d1 - 90.0) * (d2 + 20.0) * (d3 + 18.0);
                    if (meetsCriteria(result, target, epsilon)) return;
                }
            }
        }
    }
}
