package com.lukgru.galgo.builder.population;

import com.lukgru.galgo.model.Population;

/**
 * Created by Lukasz on 06.12.2016.
 */
public interface PopulationAccessor<T> {

    Population<T> getPopulation();

}
