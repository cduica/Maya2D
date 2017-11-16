package com.maya2d.model;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MayaExpander {

    private int x;
    private int y;
    private int width;
    private int height;
    private Map<Shape, Color> colors;

    public MayaExpander(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        colors = new HashMap<>();

        // top dot
        // bottom dot
        // left dot
        // right dot

        // top left dot
        // top right dot
        // bottom left dot
        // bottom right dot
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

    public Color getColor(Shape s){
        return colors.get(s);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
