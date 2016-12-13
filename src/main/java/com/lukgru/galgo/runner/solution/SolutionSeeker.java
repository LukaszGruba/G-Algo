package com.lukgru.galgo.runner.solution;

import com.lukgru.galgo.population.Population;

/**
 * Created by Lukasz on 12.12.2016.
 */
public interface SolutionSeeker<T> {

    boolean isSolutionFound(Population<T> population);

}
