package com.lukgru.galgo.runner.selection;

import com.lukgru.galgo.population.Population;

/**
 * Created by Lukasz on 12.12.2016.
 */
public interface SelectionRunner<T> {

    Population<T> selectForReproduction(Population<T> population);
}
