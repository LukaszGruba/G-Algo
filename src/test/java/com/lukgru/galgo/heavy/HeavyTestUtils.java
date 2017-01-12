package com.lukgru.galgo.heavy;

import java.util.Arrays;
import java.util.List;

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
}
