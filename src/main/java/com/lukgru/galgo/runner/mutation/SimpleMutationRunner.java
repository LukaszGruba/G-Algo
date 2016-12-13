package com.lukgru.galgo.runner.mutation;

import com.lukgru.galgo.mutation.Mutation;
import com.lukgru.galgo.population.Population;

/**
 * Created by Lukasz on 12.12.2016.
 */
public class SimpleMutationRunner<T> implements MutationRunner<T> {

    private Mutation<T> mutation;

    public SimpleMutationRunner(Mutation<T> mutation) {
        this.mutation = mutation;
    }

    @Override
    public void mutate(Population<T> population) {
        //TODO: implement
    }
}
