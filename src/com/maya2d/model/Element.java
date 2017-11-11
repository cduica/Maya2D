package com.maya2d.model;

import com.maya2d.view.Visitor;

public interface Element {
    void accept(Visitor v);
}
