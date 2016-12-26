package com.lukgru.galgo.runner.reproduction;

import static java.util.stream.Collectors.toList;

import com.lukgru.galgo.crossover.CrossoverFunction;
import com.lukgru.galgo.population.Individual;
import com.lukgru.galgo.population.Population;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by Lukasz on 12.12.2016.
 */
public class SimpleReproductionRunner<T> implements ReproductionRunner<T> {

    private CrossoverFunction<T> crossoverFunction;

    public SimpleReproductionRunner(CrossoverFunction<T> crossoverFunction) {
        this.crossoverFunction = crossoverFunction;
    }

    @Override
    public Population<T> reproduce(Population<T> selectedForReproduction) {
        Collection<Individual<T>> individuals = selectedForReproduction.getIndividuals();
        List<T> children = getParentsPairs(individuals)
                .flatMap(pair -> Stream.of(
                        crossoverFunction.apply(pair.parent1.getValue(), pair.parent2.getValue()),
                        crossoverFunction.apply(pair.parent2.getValue(), pair.parent1.getValue())
                ))
                .collect(toList());
        return new Population<>(children);
    }

    private Stream<ParentsPair> getParentsPairs(Collection<Individual<T>> parents) {
        Iterator<Individual<T>> parentsIter = parents.iterator();
        return IntStream.range(0, parents.size() / 2)
                .mapToObj(i -> new ParentsPair(parentsIter.next(), parentsIter.next()));
    }

    private class ParentsPair {
        final Individual<T> parent1;
        final Individual<T> parent2;

        private ParentsPair(Individual<T> parent1, Individual<T> parent2) {
            this.parent1 = parent1;
            this.parent2 = parent2;
        }
    }
}
