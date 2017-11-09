package com.maya2d.model;

import java.util.Iterator;
import java.util.List;

public abstract class Component implements Element {
    private String identifier;
    private List<State> states;
    private Component parent;
    private List<Component> children;

    public void add(Component c){
        children.add(c);
    }

    public void remove(Component c){
        children.remove(c);
    }

    public Iterator<Component> makeIter(){
        return children.iterator();
    }

    public void translateX(double amount){

    }

    public void translateY(double amount){

    }

    public abstract void rotate(double angle);

    public abstract void expand(double factor);

}
