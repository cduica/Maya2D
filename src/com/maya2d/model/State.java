package com.maya2d.model;

import java.awt.*;
import java.awt.geom.Point2D;

public class State {
    private Point position;
    private Color color;
    private double size;
    private boolean visibility;
    private int frame;

    public State(Point position, Color color, double size, boolean visibility, int frame) {
        this.position = position;
        this.color = color;
        this.size = size;
        this.visibility = visibility;
        this.frame = frame;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setX(double x){
        position.setLocation(x, position.getY());
    }

    public void setY(double y){
        position.setLocation(position.getX(), y);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public int getFrame() {
        return frame;
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }
}
