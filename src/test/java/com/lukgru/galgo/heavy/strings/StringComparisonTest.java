package com.lukgru.galgo.heavy.strings;

import com.lukgru.galgo.heavy.HeavyTestUtils;
import org.junit.Test;

import java.util.stream.Stream;

import static com.lukgru.galgo.heavy.HeavyTestUtils.MINUTE;
import static com.lukgru.galgo.heavy.HeavyTestUtils.isFasterParallel;
import static org.junit.Assert.assertTrue;

/**
 * Created by Lukasz on 21.01.2017.
 */
public class StringComparisonTest {

    private StringHeavyTest test = new StringHeavyTest();

    @Test(timeout = 2 * MINUTE)
    public void guessSingleWord7CharsComparison() {
        boolean faster = isFasterParallel(
                test::guessSingleWord7Chars,
                this::guessSingleWord7CharsRandom);
        assertTrue(faster);
    }

    private void guessSingleWord7CharsRandom() {
        String word = "Gandalf";
        Stream.generate(() -> HeavyTestUtils.randomStringNChars(word.length()))
                .anyMatch(s -> s.equals(word));
    }

    @Test(timeout = 2 * MINUTE)
    public void guessTwoWords13CharsComparison() {
        boolean faster = isFasterParallel(
                test::guessTwoWords13Chars,
                this::guessTwoWords13CharsRandom);
        assertTrue(faster);
    }

    private void guessTwoWords13CharsRandom() {
        String word = "Gandalf Szary";
        Stream.generate(() -> HeavyTestUtils.randomStringNChars(word.length()))
                .anyMatch(s -> s.equals(word));
    }

    @Test(timeout = 2 * MINUTE)
    public void guessThreeWords21CharsComparison() {
        boolean faster = isFasterParallel(
                test::guessThreeWords21Chars,
                this::guessThreeWords21CharsRandom);
        assertTrue(faster);
    }

    private void guessThreeWords21CharsRandom() {
        String word = "Gandalf Szary czaruje";
        Stream.generate(() -> HeavyTestUtils.randomStringNChars(word.length()))
                .anyMatch(s -> s.equals(word));
    }

    @Test(timeout = 2 * MINUTE)
    public void guessFourWords27CharsComparison() {
        boolean faster = isFasterParallel(
                test::guessFourWords27Chars,
                this::guessFourWords27CharsRandom);
        assertTrue(faster);
    }

    private void guessFourWords27CharsRandom() {
        String word = "Gandalf Szary czaruje mocno";
        Stream.generate(() -> HeavyTestUtils.randomStringNChars(word.length()))
                .anyMatch(s -> s.equals(word));
    }

    @Test(timeout = 2 * MINUTE)
    public void guessSentence28CharsComparison() {
        boolean faster = isFasterParallel(
                test::guessSentence28Chars,
                this::guessSentence28CharsRandom);
        assertTrue(faster);
    }

    private void guessSentence28CharsRandom() {
        String word = "Jas je kielbase i Jas mlaska";
        Stream.generate(() -> HeavyTestUtils.randomStringNChars(word.length()))
                .anyMatch(s -> s.equals(word));
    }

    @Test(timeout = 2 * MINUTE)
    public void guessComplexSentence216CharsComparison() {
        boolean faster = isFasterParallel(
                test::guessComplexSentence216Chars,
                this::guessComplexSentence216CharsRandom);
        assertTrue(faster);
    }

    private void guessComplexSentence216CharsRandom() {
        String word = "Wilfrid Stalker Sellars (May 20, 1912 - July 2, 1989) was an American philosopher" +
                " and prominent developer of critical realism,[2] who revolutionized both the content" +
                " and the method of philosophy in the United States.";
        Stream.generate(() -> HeavyTestUtils.randomStringNChars(word.length()))
                .anyMatch(s -> s.equals(word));
    }

    @Test(timeout = 2 * MINUTE)
    public void guessComplexSentence652CharsComparison() {
        boolean faster = isFasterParallel(
                test::guessComplexSentence652Chars,
                this::guessComplexSentence652CharsRandom);
        assertTrue(faster);
    }

    private void guessComplexSentence652CharsRandom() {
        String word = "Causal determinism is, roughly speaking, the idea that every event is necessitated by antecedent" +
                " events and conditions together with the laws of nature. The idea is ancient, but first became subject" +
                " to clarification and mathematical analysis in the eighteenth century. Determinism is deeply connected" +
                " with our understanding of the physical sciences and their explanatory ambitions, on the one hand, and" +
                " with our views about human free action on the other. In both of these general areas there is" +
                " no agreement over whether determinism is true (or even whether it can be known true or false)," +
                " and what the import for human agency would be in either case.";
        Stream.generate(() -> HeavyTestUtils.randomStringNChars(word.length()))
                .anyMatch(s -> s.equals(word));
    }

}
