package com.maya2d.utilities;

public class PositionInterpolator {

    public double[] createPositionInterpolation(double start, double end, int steps) {
        double[] positions = new double[steps];

        double delta = (end - start)/steps;

        for(int i = 0; i < steps; ++i) {
            positions[i] = start;
            start+=delta;
        }

        return positions;
    }

}
