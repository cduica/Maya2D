package com.maya2d.model;

import com.maya2d.utilities.ColorInterpolator;
import com.maya2d.utilities.PositionInterpolator;
import com.maya2d.view.Observer;
import com.maya2d.view.Subject;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Component implements Subject, Element {

    private String identifier;
    private List<State> states;
    private State currentState;
    private Component parent;
    private List<Component> children;
    private List<Observer> observers;
    private int currentFrame;
    private ColorInterpolator colorInterpolator;
    private PositionInterpolator positionInterpolator;
    private List<State> keyFrames;

    public Component(){
        observers = new ArrayList<>();
        states = new ArrayList<>();
        children = new ArrayList<>();
        keyFrames = new ArrayList<>();
        colorInterpolator = new ColorInterpolator();
        positionInterpolator = new PositionInterpolator();
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

    public void setX(double x){
        if(currentState!=null)
            currentState.setX(x);
    }

    public void setY(double y){
        if(currentState!=null)
            currentState.setY(y);
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

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void firstState(){
        currentFrame = 0;
    }

    public void nextState(){
        if(states.size() < currentFrame) {
            currentState = states.get(currentFrame);
        }
        currentFrame++;
    }

    public void lastState(){
        currentFrame = states.size() - 1;
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentStateToFrame( int i ) {
        if(states.size() > i) {
            currentState = states.get(i);
        }
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public void keyCurrentFrame() {
        // this interpolates between the frames in the state array

        if(currentState==null)
            return;

        int frame = AnimationFrame.getInstance().getFrame();
        // fill with the same state up till the currently selected frame
        if(states.isEmpty()) {
            for(int i = 0; i <= frame; ++i) {
                State s = new State(currentState.getPosition(), currentState.getColor(), currentState.getSize(), true, i);
                states.add(s);
            }
        } else {
            State previous = states.get(states.size() - 1);
            int steps = Math.abs(frame - previous.getFrame());
            Color[] colors = colorInterpolator.createGradient(previous.getColor().getRGB(), currentState.getColor().getRGB(), steps);
            double[] xPositions = positionInterpolator.createPositionInterpolation(previous.getPosition().getX(), currentState.getPosition().getX(), steps);
            double[] yPositions = positionInterpolator.createPositionInterpolation(previous.getPosition().getY(), currentState.getPosition().getY(), steps);
            for(int i = 0; i < steps; ++i) {
                // create a new state for each step
                int frameNo = previous.getFrame() + i + 1;
                State s = new State(new Point((int)xPositions[i], (int)yPositions[i]), colors[i], previous.getSize(), true, frameNo);
                states.add(s);
            }
        }
        // add the currently keyframed state (the last one) to the array
        keyFrames.add(states.get(states.size() - 1));

    }

    // add some way to delete keyframes

    public List<Integer> getKeyFramedStates() {

        List<Integer> list = new ArrayList<>();

        for(int i = 0; i < keyFrames.size(); ++i) {
            list.add(keyFrames.get(i).getFrame());
        }

        return list;
    }
}
