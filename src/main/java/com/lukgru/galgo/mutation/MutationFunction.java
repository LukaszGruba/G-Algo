package com.lukgru.galgo.mutation;

/**
 * Created by Lukasz on 07.12.2016.
 */
@FunctionalInterface
//TODO: remove this interface and use JDK ones
public interface MutationFunction<T> {

    T mutate(T individual);
}
