package com.maya2d.model;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MayaSelector {

    private int x;
    private int y;

    private Map<Shape, Color> colors;
    private java.util.List<Shape> shapes;
    private Rectangle rect;
    private Line2D lineY;
    private Polygon p1;
    private Line2D lineX;
    private Polygon p2;
    private Rectangle xy;

    public MayaSelector(int x, int y){
        this.x = x;
        this.y = y;
        colors = new HashMap<>();
        shapes = new ArrayList<>();
        rect = new Rectangle(x, y, 15, 15);
        shapes.add(rect);
        colors.put(rect, Color.YELLOW);
        lineY = new Line2D.Double(x, y, x, y-50);
        shapes.add(lineY);
        colors.put(lineY, Color.GREEN);
        int[] xPoints1 = {x-4, x, x+4};
        int[] yPoints1 = {y-51, y-10-51, y-51};
        p1 = new Polygon(xPoints1, yPoints1, 3);
        shapes.add(p1);
        colors.put(p1, Color.GREEN);
        lineX = new Line2D.Double(x+15, y + 15, x+65, y + 15);
        shapes.add(lineX);
        colors.put(lineX, Color.RED);
        int[] xPoints2 = {x+65, x+65, x+72};
        int[] yPoints2 = {y+4+15, y-4+15, y+15};
        p2 = new Polygon(xPoints2, yPoints2, 3);
        shapes.add(p2);
        colors.put(p2, Color.RED);
        xy = new Rectangle(x+50, y-50, 15, 15);
        shapes.add(xy);
        colors.put(xy, Color.BLUE);
    }

    public java.util.List<Shape> getShapes(){
        return shapes;
    }

    public Color getColor(Shape s){
        return colors.get(s);
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
