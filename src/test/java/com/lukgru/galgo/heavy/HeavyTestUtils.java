package com.lukgru.galgo.heavy;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static java.util.stream.Collectors.toList;

/**
 * Created by Lukasz on 05.01.2017.
 */
public class HeavyTestUtils {

    public static final long MINUTE = 60 * 1000L;

    private HeavyTestUtils() {}

    public static boolean meetsCriteria(Double fitness, Double target, Double epsilon) {
        return Math.abs(fitness - target) < epsilon;
    }

    public static boolean isFaster(Runnable shouldBeFaster, Runnable... shouldBeSlower) {
        long fastTime = measureTime(shouldBeFaster);
        System.out.println("Fast = " + fastTime);
        return Arrays.stream(shouldBeSlower)
                .map(HeavyTestUtils::measureTime)
                .map(time -> {
                    System.out.println("Time = " + time);
                    return time;
                })
                .noneMatch(executionTime -> executionTime < fastTime);
    }

    public static boolean isFasterParallel(Runnable shouldBeFaster, Runnable... shouldBeSlower) {
        //TODO: use executors
        try {
            Thread fastThread = new Thread(shouldBeFaster);
            List<Thread> threads = Arrays.stream(shouldBeSlower)
                    .map(Thread::new)
                    .collect(toList());
            threads.forEach(Thread::start);
            fastThread.start();
            fastThread.join();
            boolean isFaster = threads.stream().allMatch(Thread::isAlive);
            threads.forEach(Thread::stop);
            return isFaster;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static long measureTime(Runnable runnable) {
        long start = System.currentTimeMillis();
        runnable.run();
        long end = System.currentTimeMillis();
        return end - start;
    }

    public static String randomStringUpTo20Chars() {
        return randomStringNChars((int)(20.0 * Math.random()));
    }

    public static String randomStringUpTo40Chars() {
        return randomStringNChars((int)(40.0 * Math.random()));
    }

    public static String randomStringUpToNChars(int n) {
        return randomStringNChars((int) (n * Math.random()));
    }

    public static String randomStringNChars(int length) {
        Random rnd = new Random();
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 .,()[]-";
        char[] chars = new char[length];
        for (int i=0 ; i<length ; i++) {
            chars[i] = alphabet.charAt(rnd.nextInt(alphabet.length()));
        }
        return new String(chars);
    }

    public static double wordsDistance(String word1, String word2) {
        int totalDiff = 0;
        int length1 = word1.length();
        int length2 = word2.length();
        for (int i = 0; i< length1 && i < length2; i++) {
            char c1 = word1.charAt(i);
            char c2 = word2.charAt(i);
            int diff = Math.abs(c1 - c2);
            if (diff > 0) {
                totalDiff += diff + 500;
            }
        }
        totalDiff += Math.abs(length2 - length1) * 1000;
        return totalDiff;
    }

}
