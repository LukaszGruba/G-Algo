package com.lukgru.galgo.heavy.functions;

import java.util.Arrays;

/**
 * Created by Lukasz on 05.01.2017.
 */
class VariablesTuple {
    double[] v;

    public VariablesTuple(double... v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return "VariablesTuple{values=" + Arrays.toString(v) + '}';
    }
}
