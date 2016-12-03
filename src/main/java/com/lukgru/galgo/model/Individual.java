package com.lukgru.galgo.model;

/**
 * Created by Łukasz on 2016-12-03.
 */
public class Individual<T> {

    private final T value;
    private Integer fitnessValue;

    public Individual(T value) {
        if (value == null) {
            throw new NullPointerException("Individual cannot be created with null value");
        }
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }

    public Integer getFitnessValue() {
        return this.fitnessValue;
    }

    public void setFitnessValue(Integer fitnessValue) {
        this.fitnessValue = fitnessValue;
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
        return this.fitnessValue != null ? this.fitnessValue.equals(that.fitnessValue) : that.fitnessValue == null;

    }

    @Override
    public int hashCode() {
        int result = this.value.hashCode();
        result = 31 * result + (this.fitnessValue != null ? this.fitnessValue.hashCode() : 0);
        return result;
    }
}
