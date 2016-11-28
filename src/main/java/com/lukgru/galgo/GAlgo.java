package com.lukgru.galgo;

import java.util.Collection;

/**
 * Created by Lukasz on 28.11.2016.
 */
public class GAlgo {

    private GAlgo() {}

    public static <T> Population<T> fromPopulation(Collection<T> population) {
        return new Population<T>(population);
    }
}
