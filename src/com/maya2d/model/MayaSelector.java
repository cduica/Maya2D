package com.maya2d.model;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MayaSelector {

    private double x;
    private double y;

    private Map<Shape, Color> colors;
    private java.util.List<Shape> shapes;
    private Rectangle2D rect;
    private Line2D lineY;
    private Polygon p1;
    private Line2D lineX;
    private Polygon p2;
    private Rectangle2D xy;

    public MayaSelector(double x, double y){
        this.x = x;
        this.y = y;

        colors = new HashMap<>();
        shapes = new ArrayList<>();
        rect = new Rectangle2D.Double(x, y, 15, 15);
        shapes.add(rect);
        colors.put(rect, Color.YELLOW);
        lineY = new Line2D.Double(x, y, x, y-50);
        shapes.add(lineY);
        colors.put(lineY, Color.GREEN);
        int[] xPoints1 = {(int)x-4, (int)x, (int)x+4};
        int[] yPoints1 = {(int)y-51, (int)y-10-51, (int)y-51};
        p1 = new Polygon(xPoints1, yPoints1, 3);
        shapes.add(p1);
        colors.put(p1, Color.GREEN);
        lineX = new Line2D.Double(x+15, y + 15, x+65, y + 15);
        shapes.add(lineX);
        colors.put(lineX, Color.RED);
        int[] xPoints2 = {(int)x+65, (int)x+65, (int)x+72};
        int[] yPoints2 = {(int)y+4+15, (int)y-4+15, (int)y+15};
        p2 = new Polygon(xPoints2, yPoints2, 3);
        shapes.add(p2);
        colors.put(p2, Color.RED);
        xy = new Rectangle2D.Double(x+50, y-50, 15, 15);
        shapes.add(xy);
        colors.put(xy, Color.BLUE);
    }

    public java.util.List<Shape> getShapes(){
        return shapes;
    }

    public Color getColor(Shape s){
        return colors.get(s);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Rectangle2D getRect() {
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

    public Rectangle2D getXy() {
        return xy;
    }
}
