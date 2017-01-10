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
public class TwoVariablesComparisonTest {

    private TwoVariablesHeavyTest tests = new TwoVariablesHeavyTest();

    @Test(timeout = 2 * MINUTE)
    public void simpleTwoVariableEquationWithZeroTargetComparison() {
        //when
        long timeDiff = compareExecution(
                tests::solveSimpleTwoVariableEquationWithZeroTarget,
                this::randomSolveSimpleTwoVariableEquationWithZeroTarget);
        //then
        assertTrue(timeDiff < 0);
    }

    private void randomSolveSimpleTwoVariableEquationWithZeroTarget() {
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

}
