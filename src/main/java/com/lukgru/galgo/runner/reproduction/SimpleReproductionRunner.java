package com.lukgru.galgo.runner.reproduction;

import com.lukgru.galgo.crossover.CrossoverFunction;
import com.lukgru.galgo.population.Individual;
import com.lukgru.galgo.population.Population;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

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
        Collection<T> children = new ArrayList<>();
        getParentsPairs(individuals).forEach(pair -> {
            children.add(crossoverFunction.apply(pair.parent1.getValue(), pair.parent2.getValue()));
            children.add(crossoverFunction.apply(pair.parent2.getValue(), pair.parent1.getValue()));
        });
        return new Population<>(children);
    }

    private List<ParentsPair> getParentsPairs(Collection<Individual<T>> parents) {
        Iterator<Individual<T>> parentsIterator = parents.iterator();
        List<ParentsPair> pairs = new ArrayList<>();
        while (parentsIterator.hasNext()) {
            Individual<T> parent1 = parentsIterator.next();
            Individual<T> parent2 = parentsIterator.hasNext() ? parentsIterator.next() : parent1;
            ParentsPair pair = new ParentsPair(parent1, parent2);
            pairs.add(pair);
        }
        return pairs;
    }

    private class ParentsPair {
        private final Individual<T> parent1;
        private final Individual<T> parent2;

        private ParentsPair(Individual<T> parent1, Individual<T> parent2) {
            this.parent1 = parent1;
            this.parent2 = parent2;
        }
    }
}
