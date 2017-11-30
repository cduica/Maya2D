package com.maya2d.model;

import com.maya2d.view.DrawingVisitor;
import com.maya2d.view.EditingState;
import com.maya2d.view.Observer;
import com.maya2d.view.Subject;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MayaCanvas implements Subject{
    // this class holds the model

    private Map<Point, Component> components;
    private DrawingVisitor v;
    private List<Observer> observers;
    private Component selected;
    private EditingState editingState;

    public MayaCanvas(DrawingVisitor v){
        editingState = EditingState.TRANSLATE;
        this.v = v;
        components = new HashMap<>();
        observers = new ArrayList<>();
    }

    public void add(Component c, Point p){
        components.put(p, c);
    }

    public Component get(Point p){
        return components.get(p);
    }

    public boolean contains(Point p){
        return components.containsKey(p);
    }

    public void remove(Component c){

        // im so sorry
        for(Map.Entry<Point, Component> e : components.entrySet()) {
            if(e.getValue().equals(c)){
                components.remove(e.getKey());
            }
        }
    }

    public void remove(Point p) {
        components.remove(p);
    }

    public void draw(){
        // add visitor code here
        for(Map.Entry<Point, Component> entry : components.entrySet()){
            entry.getValue().accept(v);
        }
    }

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

    public Component getSelected() {
        return selected;
    }

    public void setSelected(Component selected) {
        this.selected = selected;
        notifyObservers();
    }

    public int getNumComponents(){
        return components.size();
    }

    public void setEditingState(EditingState editingState){
        this.editingState = editingState;
    }

    public EditingState getEditingState(){
        return this.editingState;
    }

    public void setComponentsToFrame(int frame) {
        for(Map.Entry<Point, Component> e : components.entrySet()) {
            e.getValue().setCurrentStateToFrame(frame);
        }
    }
}
