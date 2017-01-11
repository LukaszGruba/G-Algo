package com.lukgru.galgo.heavy;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(1 + shouldBeSlower.length);
            Future fast = executorService.submit(shouldBeFaster);
            List<Future> slowFutures = Arrays.stream(shouldBeSlower).map(executorService::submit).collect(toList());
            fast.get();
            boolean isFaster = slowFutures.stream().filter(Future::isDone).findFirst().isPresent();
            slowFutures.forEach(future -> future.cancel(true));
            executorService.shutdown();
            return isFaster;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }

}
