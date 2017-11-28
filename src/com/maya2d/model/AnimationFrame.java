package com.maya2d.model;

public class AnimationFrame {
    private static AnimationFrame ourInstance = new AnimationFrame();

    private int frame;

    public static AnimationFrame getInstance() {
        return ourInstance;
    }

    private AnimationFrame() {
    }

    public int getFrame() {
        return frame;
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }
}
