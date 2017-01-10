package com.lukgru.galgo.heavy;

/**
 * Created by Lukasz on 05.01.2017.
 */
public class HeavyTestUtils {

    public static final long MINUTE = 60 * 1000L;

    private HeavyTestUtils() {}

    public static boolean meetsCriteria(Double fitness, Double target, Double epsilon) {
        return Math.abs(fitness - target) < epsilon;
    }

    public static long compareExecution(Runnable test1, Runnable test2) {
        long startTime = System.currentTimeMillis();
        test1.run();
        long switchTime = System.currentTimeMillis();
        test2.run();
        long stopTime = System.currentTimeMillis();

        long test1Time = switchTime - startTime;
        long test2Time = stopTime - switchTime;
        System.out.println("GA = " + test1Time + "ms, random = " + test2Time + "ms");
        return test1Time - test2Time;
    }

}
