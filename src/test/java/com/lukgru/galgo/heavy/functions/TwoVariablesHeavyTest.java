package com.lukgru.galgo.heavy.functions;

import com.lukgru.galgo.GAlgo;
import com.lukgru.galgo.runner.GenerationResult;
import org.junit.Test;

import static com.lukgru.galgo.heavy.HeavyTestUtils.MINUTE;
import static com.lukgru.galgo.heavy.HeavyTestUtils.meetsCriteria;
import static java.lang.Math.random;
import static org.junit.Assert.assertTrue;

/**
 * Created by Lukasz on 05.01.2017.
 */
public class TwoVariablesHeavyTest {

    @Test(timeout = MINUTE)
    public void twoVariableEquationWithZeroTarget() {
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
}
