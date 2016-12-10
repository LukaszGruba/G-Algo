package com.lukgru.galgo;

import com.lukgru.galgo.builder.mutation.MutationFunction;
import com.lukgru.galgo.runner.GenerationResult;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by Lukasz on 04.12.2016.
 */
//TODO: remove ignore when implementing
@Ignore("no implementation yet")
public class GAlgoTest {
    
    @Test
    public void solveSimpleSingleVariableEquation() {
        //given
        Collection<Integer> initialPopulation = Arrays.asList(-100,-75,-50,-25,0,25,50,75,100);
        
        //when
        GenerationResult<Integer> generationResult = GAlgo.fromPopulation(initialPopulation)
                .withFitnessFunction(x -> (14 * x) - 28).targeting(0)
                .withCrossover((a, b) -> (a + b) / 2)
                .withMutationFunction(a -> -a).withMutationProbability(0.1)
                .runner().generate();

        //then
        Integer solution = generationResult.getBest();
        assertEquals(2, solution.intValue());
    }

    @Test
    public void solveSimpleSingleVariableQuadraticEquationWithOneSolution() {
        //given
        Collection<Integer> initialPopulation = Arrays.asList(-100,-75,-50,-25,0,25,50,75,100);

        //when
        GenerationResult<Integer> generationResult = GAlgo.fromPopulation(initialPopulation)
                .withFitnessFunction(x -> (x*x) - (2 * x) + 1).targeting(0)
                .withCrossover((a, b) -> (a + b) / 2)
                .withMutationFunction(a -> -a).withMutationProbability(0.1)
                .runner().generate();

        //then
        Integer solution = generationResult.getBest();
        assertEquals(1, solution.intValue());
    }

    @Test
    public void solveSimpleSingleVariableQuadraticEquationWithTwoDistantSolutions() {
        //given
        Collection<Integer> initialPopulation = Arrays.asList(-100,-75,-50,-25,0,25,50,75,100);

        //when
        GenerationResult<Integer> generationResult = GAlgo.fromPopulation(initialPopulation)
                .withFitnessFunction(x -> (x - 90) * (x + 20)).targeting(0)
                .withCrossover((a, b) -> (a + b) / 2)
                .withMutationFunction(a -> -a).withMutationProbability(0.1)
                .runner().generate();

        //then
        Integer solution = generationResult.getBest();
        assertTrue(solution == 90 || solution == -20);
    }

    @Test
    public void solveSimpleTwoVariableEquation() {
        //given
        Collection<VariablesPair> initialPopulation = Arrays.asList(
                new VariablesPair(100, 100),
                new VariablesPair(90, 90),
                new VariablesPair(80, 80),
                new VariablesPair(70, 70),
                new VariablesPair(60, 60),
                new VariablesPair(50, 50),
                new VariablesPair(40, 40),
                new VariablesPair(30, 30),
                new VariablesPair(20, 20),
                new VariablesPair(10, 10),
                new VariablesPair(0, 0),
                new VariablesPair(-10, -10),
                new VariablesPair(-20, -20),
                new VariablesPair(-30, -30),
                new VariablesPair(-40, -40),
                new VariablesPair(-50, -50),
                new VariablesPair(-60, -60),
                new VariablesPair(-70, -70),
                new VariablesPair(-80, -80),
                new VariablesPair(-90, -90),
                new VariablesPair(-100, -100)
        );
        MutationFunction<VariablesPair> mutationFunction = a -> new VariablesPair(
                (int) (Double.valueOf(a.x) * (0.5d + Math.random())),
                (int) (Double.valueOf(a.y) * (0.5d + Math.random())));

        //when
        GenerationResult<VariablesPair> generationResult = GAlgo.fromPopulation(initialPopulation)
                .withFitnessFunction(v -> (v.x - 90) * (v.y + 20)).targeting(0)
                .withCrossover((a, b) -> new VariablesPair(a.x - b.x / 2, a.y - b.y / 2))
                .withMutationFunction(mutationFunction)
                .withMutationProbability(0.1)
                .runner().generate();

        //then
        VariablesPair best = generationResult.getBest();
        assertEquals(90, best.x);
        assertEquals(-20, best.y);
    }

    private class VariablesPair {
        private int x;
        private int y;

        public VariablesPair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}