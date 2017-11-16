package com.maya2d.model;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.HashMap;
import java.util.Map;

public class MayaRotator {

    private int x;
    private int y;
    private Map<Shape, Color> colors;
    private Ellipse2D circle;

    public MayaRotator(int x, int y){
        this.x = x;
        this.y = y;
        colors = new HashMap<>();
        circle = new Ellipse2D.Double(x - 50, y - 50, 100, 100);
        colors.put(circle, Color.GREEN);
    }

    public Color getColor(Shape s){
        return colors.get(s);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Ellipse2D getCircle(){
        return this.circle;
    }
}
