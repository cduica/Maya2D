package com.maya2d.model;

public interface Element {
    void accept(ComponentVisitor v);
}
