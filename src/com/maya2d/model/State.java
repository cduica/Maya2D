package com.maya2d.model;

import java.awt.*;
import java.awt.geom.Point2D;

public class State {
    private Point2D position;
    private Color color;
    private double size;
    private boolean visibility;
    private int frame;

    public State(Point2D position, Color color, double size, boolean visibility, int frame) {
        this.position = position;
        this.color = color;
        this.size = size;
        this.visibility = visibility;
        this.frame = frame;
    }
}
