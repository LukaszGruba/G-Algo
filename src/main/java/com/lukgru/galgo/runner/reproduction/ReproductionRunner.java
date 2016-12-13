package com.lukgru.galgo.runner.reproduction;

import com.lukgru.galgo.population.Population;

/**
 * Created by Lukasz on 12.12.2016.
 */
public interface ReproductionRunner<T> {

    Population<T> reproduce(Population<T> selectedForReproduction);
    
}
