package com.maya2d.model;

import com.maya2d.view.Visitor;

import java.awt.*;

public class ShapeComposite extends Component {

    private Shape shape;

    public ShapeComposite(Shape shape){
        this.shape = shape;
    }

    @Override
    public void rotate(double angle) {

    }

    @Override
    public void expand(double factor) {

    }

    @Override
    public void accept(Visitor v) {
        v.visitShape(this);
        //notifyObservers();
    }

    public Shape getShape(){
        return this.shape;
    }

}
