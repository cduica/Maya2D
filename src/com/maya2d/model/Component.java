package com.maya2d.model;

import com.maya2d.view.Observer;
import com.maya2d.view.Subject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Component implements Subject, Element {
    private String identifier;
    private List<State> states;
    private Component parent;
    private List<Component> children;
    private List<Observer> observers;

    public Component(){
        observers = new ArrayList<>();
        states = new ArrayList<>();
        children = new ArrayList<>();
    }

    public void add(Component c){
        children.add(c);
    }

    public void remove(Component c){
        children.remove(c);
    }

    public void addState(State s){
        states.add(s);
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

    @Override
    public void attach(Observer o) {
        observers.add(o);
    }

    @Override
    public void detach(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for(int i = 0; i < observers.size(); ++i){
            observers.get(i).update();
        }
    }

    public State getStateAtFrame(int i){
        return states.get(i);
    }
}
