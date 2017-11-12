package com.maya2d.model;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class MayaSelector {

    private int x;
    private int y;

    Color[] colors;
    java.util.List<Shape> shapes;
    Rectangle rect;
    Line2D lineY;
    Polygon p1;
    Line2D lineX;
    Polygon p2;
    Rectangle xy;

    public MayaSelector(int x, int y){
        this.x = x;
        this.y = y;
        colors = new Color[6];
        shapes = new ArrayList<>();
        rect = new Rectangle(x, y, 15, 15);
        shapes.add(rect);
        colors[0] = Color.WHITE;
        lineY = new Line2D.Double(x, y, x, y-50);
        shapes.add(lineY);
        colors[1] = Color.GREEN;
        int[] xPoints1 = {x-3, x, x+3};
        int[] yPoints1 = {y-50, y-10-50, y-50};
        p1 = new Polygon(xPoints1, yPoints1, 3);
        shapes.add(p1);
        colors[2] = Color.GREEN;
        lineX = new Line2D.Double(x+15, y + 15, x+65, y + 15);
        shapes.add(lineX);
        colors[3] = Color.RED;
        int[] xPoints2 = {x+65, x+65, x+71};
        int[] yPoints2 = {y+3+15, y-3+15, y+15};
        p2 = new Polygon(xPoints2, yPoints2, 3);
        shapes.add(p2);
        colors[4] = Color.RED;
        xy = new Rectangle(x+50, y-50, 15, 15);
        shapes.add(xy);
        colors[5] = Color.BLUE;
    }

    public java.util.List<Shape> getShapes(){
        return shapes;
    }

    public Color[] getColors(){
        return colors;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getRect() {
        return rect;
    }

    public Line2D getLineY() {
        return lineY;
    }

    public Polygon getP1() {
        return p1;
    }

    public Line2D getLineX() {
        return lineX;
    }

    public Polygon getP2() {
        return p2;
    }

    public Rectangle getXy() {
        return xy;
    }
}
