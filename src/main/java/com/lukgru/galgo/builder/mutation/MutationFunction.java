package com.lukgru.galgo.builder.mutation;

/**
 * Created by Lukasz on 07.12.2016.
 */
@FunctionalInterface
public interface MutationFunction<T> {

    T mutate(T individual);
}
