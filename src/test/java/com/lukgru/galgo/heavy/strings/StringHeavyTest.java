package com.lukgru.galgo.heavy.strings;

import com.lukgru.galgo.GAlgo;
import com.lukgru.galgo.crossover.CrossoverFunction;
import com.lukgru.galgo.heavy.HeavyTestUtils;
import com.lukgru.galgo.mutation.MutationFunction;
import com.lukgru.galgo.population.Individual;
import com.lukgru.galgo.runner.GenerationResult;
import org.junit.Test;

import java.util.Random;

import static com.lukgru.galgo.heavy.HeavyTestUtils.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by Lukasz on 15.01.2017.
 */
public class StringHeavyTest {

    private Random random = new Random();

    @Test(timeout = MINUTE)
    public void guessSingleWord7Chars() {
        //given
        String word = "Gandalf";
        CrossoverFunction<String> crossover = (s1, s2) -> {
            int splitIndex1 = random.nextInt(s1.length());
            int splitIndex2 = random.nextInt(s2.length());
            return s1.substring(0, splitIndex1) + s2.substring(splitIndex2, s2.length());
        };
        MutationFunction<String> mutation = s -> {
            int i = random.nextInt(s.length());
            String c = randomStringNChars(1);
            char[] chars = s.toCharArray();
            chars[i] = c.charAt(0);
            return new String(chars);
        };
        double epsilon = 0.0000001;

        //when
        GenerationResult<String> result = GAlgo.fromGeneratedPopulation(HeavyTestUtils::randomStringUpTo20Chars)
                .withSize(100)
                .withFitnessFunction(s -> wordsDistance(word, s))
                .targeting(0.0)
                .withEpsilon(epsilon)
                .withCrossover(crossover)
                .withMutationFunction(mutation)
                .withMutationProbability(0.05)
                .runner().generate();

        //then
        Individual<String> best = result.getBest();
        assertEquals(word, best.getValue());
    }

    @Test(timeout = MINUTE)
    public void guessTwoWords13Chars() {
        //given
        String word = "Gandalf Szary";
        CrossoverFunction<String> crossover = (s1, s2) -> {
            int splitIndex1 = random.nextInt(s1.length());
            int splitIndex2 = random.nextInt(s2.length());
            return s1.substring(0, splitIndex1) + s2.substring(splitIndex2, s2.length());
        };
        MutationFunction<String> mutation = s -> {
            int i = random.nextInt(s.length());
            String c = randomStringNChars(1);
            char[] chars = s.toCharArray();
            chars[i] = c.charAt(0);
            return new String(chars);
        };
        double epsilon = 0.0000001;

        //when
        GenerationResult<String> result = GAlgo.fromGeneratedPopulation(HeavyTestUtils::randomStringUpTo20Chars)
                .withSize(100)
                .withFitnessFunction(s -> wordsDistance(word, s))
                .targeting(0.0)
                .withEpsilon(epsilon)
                .withCrossover(crossover)
                .withMutationFunction(mutation)
                .withMutationProbability(0.05)
                .runner().generate();

        //then
        Individual<String> best = result.getBest();
        assertEquals(word, best.getValue());
    }

    @Test(timeout = MINUTE)
    public void guessThreeWords21Chars() {
        //given
        String word = "Gandalf Szary czaruje";
        CrossoverFunction<String> crossover = (s1, s2) -> {
            int splitIndex1 = random.nextInt(s1.length());
            int splitIndex2 = random.nextInt(s2.length());
            return s1.substring(0, splitIndex1) + s2.substring(splitIndex2, s2.length());
        };
        MutationFunction<String> mutation = s -> {
            int i = random.nextInt(s.length());
            String c = randomStringNChars(1);
            char[] chars = s.toCharArray();
            chars[i] = c.charAt(0);
            return new String(chars);
        };
        double epsilon = 0.0000001;

        //when
        GenerationResult<String> result = GAlgo.fromGeneratedPopulation(HeavyTestUtils::randomStringUpTo40Chars)
                .withSize(1000)
                .withFitnessFunction(s -> wordsDistance(word, s))
                .targeting(0.0)
                .withEpsilon(epsilon)
                .withCrossover(crossover)
                .withMutationFunction(mutation)
                .withMutationProbability(0.05)
                .runner().generate();

        //then
        Individual<String> best = result.getBest();
        assertEquals(word, best.getValue());
    }

    @Test(timeout = MINUTE)
    public void guessFourWords27Chars() {
        //given
        String word = "Gandalf Szary czaruje mocno";
        CrossoverFunction<String> crossover = (s1, s2) -> {
            int splitIndex1 = random.nextInt(s1.length());
            int splitIndex2 = random.nextInt(s2.length());
            return s1.substring(0, splitIndex1) + s2.substring(splitIndex2, s2.length());
        };
        MutationFunction<String> mutation = s -> {
            int i = random.nextInt(s.length());
            String c = randomStringNChars(1);
            char[] chars = s.toCharArray();
            chars[i] = c.charAt(0);
            return new String(chars);
        };
        double epsilon = 0.0000001;

        //when
        GenerationResult<String> result = GAlgo.fromGeneratedPopulation(HeavyTestUtils::randomStringUpTo40Chars)
                .withSize(1000)
                .withFitnessFunction(s -> wordsDistance(word, s))
                .targeting(0.0)
                .withEpsilon(epsilon)
                .withCrossover(crossover)
                .withMutationFunction(mutation)
                .withMutationProbability(0.05)
                .runner().generate();

        //then
        Individual<String> best = result.getBest();
        assertEquals(word, best.getValue());
    }

    @Test(timeout = MINUTE)
    public void guessSentence28Chars() {
        //given
        String word = "Jas je kielbase i Jas mlaska";
        CrossoverFunction<String> crossover = (s1, s2) -> {
            int splitIndex1 = random.nextInt(s1.length());
            int splitIndex2 = random.nextInt(s2.length());
            return s1.substring(0, splitIndex1) + s2.substring(splitIndex2, s2.length());
        };
        MutationFunction<String> mutation = s -> {
            int i = random.nextInt(s.length());
            String c = randomStringNChars(1);
            char[] chars = s.toCharArray();
            chars[i] = c.charAt(0);
            return new String(chars);
        };
        double epsilon = 0.0000001;

        //when
        GenerationResult<String> result = GAlgo.fromGeneratedPopulation(HeavyTestUtils::randomStringUpTo40Chars)
                .withSize(1000)
                .withFitnessFunction(s -> wordsDistance(word, s))
                .targeting(0.0)
                .withEpsilon(epsilon)
                .withCrossover(crossover)
                .withMutationFunction(mutation)
                .withMutationProbability(0.05)
                .runner().generate();

        //then
        Individual<String> best = result.getBest();
        assertEquals(word, best.getValue());
    }

    @Test
    public void guessComplexSentence216Chars() {
        //given
        String word = "Wilfrid Stalker Sellars (May 20, 1912 - July 2, 1989) was an American philosopher and prominent developer of critical realism,[2] who revolutionized both the content and the method of philosophy in the United States.";
        CrossoverFunction<String> crossover = (s1, s2) -> {
            int oneFourthOfLength = s1.length() / 4;
            int splitIndex1 = random.nextInt(Math.max(1, oneFourthOfLength));
            int splitIndex2 = oneFourthOfLength + random.nextInt(Math.max(1, oneFourthOfLength));
            int splitIndex3 = 2 * oneFourthOfLength + random.nextInt(Math.max(1, oneFourthOfLength));
            return s1.substring(0, splitIndex1)
                    + s2.substring(splitIndex1, splitIndex2)
                    + s1.substring(splitIndex2, splitIndex3)
                    + s2.substring(splitIndex3, s2.length());
        };
        MutationFunction<String> mutation = s -> {
            char[] chars = s.toCharArray();
            int index = random.nextInt(s.length());
            String c = randomStringNChars(1);
            chars[index] = c.charAt(0);
            return new String(chars);
        };
        double epsilon = 0.0000001;

        //when
        GenerationResult<String> result = GAlgo.fromGeneratedPopulation(() -> randomStringNChars(216))
                .withSize(100)
                .withFitnessFunction(s -> wordsDistance(word, s))
                .targeting(0.0)
                .withEpsilon(epsilon)
                .withCrossover(crossover)
                .withMutationFunction(mutation)
                .withMutationProbability(0.05)
                .runner().generate();

        //then
        Individual<String> best = result.getBest();
        assertEquals(word, best.getValue());
    }
}
