package com.lukgru.galgo.runner.selection;

import static java.util.stream.Collectors.toList;

import com.lukgru.galgo.fitness.FitnessFunction;
import com.lukgru.galgo.population.Individual;
import com.lukgru.galgo.population.Population;

import java.util.List;


/**
 * Created by Lukasz on 12.12.2016.
 */
public class SimpleSelectionRunner<T> implements SelectionRunner<T> {

    //TODO: replace with target
    private FitnessFunction<T> fitnessFunction;

    public SimpleSelectionRunner(FitnessFunction<T> fitnessFunction) {
        this.fitnessFunction = fitnessFunction;
    }

    @Override
    public Population<T> selectForReproduction(Population<T> population) {
        Double target = fitnessFunction.getTarget();
        List<T> selected = population.getIndividuals().stream()
                .sorted((indA, indB) -> {
                    Double fitnessA = Math.abs(target - indA.getFitnessScore());
                    Double fitnessB = Math.abs(target - indB.getFitnessScore());
                    return fitnessA.compareTo(fitnessB);
                })
                .map(Individual::getValue)
                .limit((int)Math.ceil(population.size() / 2.0))
                .collect(toList());
        return new Population<>(selected);
    }
    
}
