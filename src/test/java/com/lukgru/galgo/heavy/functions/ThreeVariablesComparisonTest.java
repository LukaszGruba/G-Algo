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

    private ThreeVariablesHeavyTest tests = new ThreeVariablesHeavyTest();

    @Test(timeout = 2 * MINUTE)
    public void simpleThreeVariableEquationWithZeroTargetComparison() {
        //given
        boolean isFaster = isFaster(
                tests::solveSimpleThreeVariableEquationWithZeroTarget,
                this::randomSolveSimpleThreeVariableEquationWithZeroTarget);

        //then
        assertTrue(isFaster);
    }

    private void randomSolveSimpleThreeVariableEquationWithZeroTarget() {
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
}
