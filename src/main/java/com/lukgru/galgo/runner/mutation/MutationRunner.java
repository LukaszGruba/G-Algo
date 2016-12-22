package com.lukgru.galgo.runner.mutation;

import com.lukgru.galgo.population.Population;

/**
 * Created by Lukasz on 12.12.2016.
 */
public interface MutationRunner<T> {

    void mutate(Population<T> population);

}
