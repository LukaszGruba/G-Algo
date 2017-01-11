package com.lukgru.galgo.heavy;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Lukasz on 05.01.2017.
 */
public class HeavyTestUtils {

    public static final long MINUTE = 60 * 1000L;

    private HeavyTestUtils() {}

    public static boolean meetsCriteria(Double fitness, Double target, Double epsilon) {
        return Math.abs(fitness - target) < epsilon;
    }

    public static boolean isFaster(Runnable shouldBeFaster, Runnable shouldBeSlower) {
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(2);
            Future future1 = executorService.submit(shouldBeFaster);
            Future future2 = executorService.submit(shouldBeSlower);
            future1.get();
            boolean isFaster = !future2.isDone();
            future2.cancel(true);
            executorService.shutdown();
            return isFaster;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
//
//        long startTime = System.currentTimeMillis();
//        test1.run();
//        long switchTime = System.currentTimeMillis();
//        test2.run();
//        long stopTime = System.currentTimeMillis();
//
//        long test1Time = switchTime - startTime;
//        long test2Time = stopTime - switchTime;
//        System.out.println("GA = " + test1Time + "ms, random = " + test2Time + "ms");
//        return test1Time - test2Time;
    }

}
