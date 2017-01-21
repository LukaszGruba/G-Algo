package com.lukgru.galgo.heavy.functions;

import org.junit.Test;

import java.util.stream.Stream;

import static com.lukgru.galgo.heavy.HeavyTestUtils.*;
import static java.lang.Math.random;
import static org.junit.Assert.assertTrue;

/**
 * Created by Lukasz on 10.01.2017.
 */
public class TwoVariablesComparisonTest {

    private TwoVariablesHeavyTest test = new TwoVariablesHeavyTest();

    @Test(timeout = 2 * MINUTE)
    public void twoVariableEquationWithZeroTargetComparison() {
        //when
        boolean isFaster = isFaster(
                test::twoVariableEquationWithZeroTarget,
                this::twoVariableEquationWithZeroTargetRandom,
                this::twoVariableEquationWithZeroTargetBruteForce);
        //then
        assertTrue(isFaster);
    }

    private void twoVariableEquationWithZeroTargetRandom() {
        //given
        Double target = 0.0;
        Double epsilon = 0.0001;

        //then
        Stream.generate(() -> new VariablesTuple(
                random() * 200 - 100,
                random() * 200 - 100
        ))
                .map(a -> (a.v[0] - 90.0) * (a.v[1] + 20.0))
                .filter(a -> meetsCriteria(a, target, epsilon))
                .findFirst();
    }

    private void twoVariableEquationWithZeroTargetBruteForce() {
        //given
        Double target = 0.0;
        Double epsilon = 0.0001;

        //then
        for (double d1 = 0.0; d1 < 100.0; d1 = d1 + epsilon) {
            for (double d2 = 0.0; d2 < 100.0; d2 = d2 + epsilon) {
                if (meetsCriteria((d1 - 90.0) * (d2 + 20.0), epsilon, target)) return;
            }
        }
    }
}
