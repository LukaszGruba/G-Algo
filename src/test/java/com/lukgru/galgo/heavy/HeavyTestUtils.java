package com.lukgru.galgo.heavy;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.MethodAccessor_Short;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Stream;

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
//        try {
//            ExecutorService executorService = Executors.newFixedThreadPool(1 + shouldBeSlower.length);
//            Future fast = executorService.submit(shouldBeFaster);
//            List<Future> slowFutures = Arrays.stream(shouldBeSlower).map(executorService::submit).collect(toList());
//            fast.get();
//            boolean isFaster = slowFutures.stream().filter(Future::isDone).findFirst().isPresent();
//            slowFutures.forEach(future -> future.cancel(true));
//            executorService.shutdown();
//            return isFaster;
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//            return false;
//        }
    }

    private static long measureTime(Runnable runnable) {
        long start = System.currentTimeMillis();
        runnable.run();
        long end = System.currentTimeMillis();
        return end - start;
    }

}
