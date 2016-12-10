package com.lukgru.galgo.crossover;

/**
 * Created by Lukasz on 28.11.2016.
 */
@FunctionalInterface
public interface CrossoverFunction<T> {

    T apply(T t1, T t2);

}
