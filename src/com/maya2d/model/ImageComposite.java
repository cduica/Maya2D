package com.maya2d.model;

import com.maya2d.view.DrawingVisitor;
import com.maya2d.view.Visitor;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageComposite extends Component {

    private BufferedImage image;
    private double x;
    private double y;

    public ImageComposite(BufferedImage image){
        this.image = image;
    }

    @Override
    public void rotate(double angle) {

    }

    @Override
    public void expand(double factor) {

    }

    @Override
    public void accept(Visitor v) {
        v.visitImage(this);
    }

    public boolean contains(Point p) {
        if(p.getX() > x && p.getX() < x + image.getWidth() && p.getY() > y && p.getY() < x + image.getHeight()){
            return true;
        }
        return false;
    }

    public BufferedImage getImage() {
        return image;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }
}
