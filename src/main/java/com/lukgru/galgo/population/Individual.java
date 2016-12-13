package com.lukgru.galgo.population;

import java.util.Objects;

/**
 * Created by Łukasz on 2016-12-03.
 */
public class Individual<T> {

    private final T value;
    private Integer fitnessScore;

    public Individual(T value) {
        Objects.requireNonNull(value, "Individual cannot be created with null value");
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }

    public Integer getFitnessScore() {
        return this.fitnessScore;
    }

    public void setFitnessScore(Integer fitnessScore) {
        this.fitnessScore = fitnessScore;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Individual<?> that = (Individual<?>) obj;

        if (!this.value.equals(that.value)) {
            return false;
        }
        return this.fitnessScore != null ? this.fitnessScore.equals(that.fitnessScore) : that.fitnessScore == null;

    }

    @Override
    public int hashCode() {
        int result = this.value.hashCode();
        result = 31 * result + (this.fitnessScore != null ? this.fitnessScore.hashCode() : 0);
        return result;
    }
}
