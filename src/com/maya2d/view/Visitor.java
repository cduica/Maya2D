package com.maya2d.view;

import com.maya2d.model.Component;
import com.maya2d.model.ImageComposite;
import com.maya2d.model.ShapeComposite;

public interface Visitor {
    void visitShape(ShapeComposite c);
    void visitImage(ImageComposite c);
}
