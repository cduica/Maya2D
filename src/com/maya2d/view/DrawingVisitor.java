package com.maya2d.view;

import com.maya2d.model.ImageComposite;
import com.maya2d.model.ShapeComposite;

public class DrawingVisitor implements Visitor {

    private ContentPanel contentPanel;

    public DrawingVisitor(ContentPanel contentPanel){
        this.contentPanel = contentPanel;
    }

    @Override
    public void visitShape(ShapeComposite c) {
        contentPanel.addShape(c);
    }

    @Override
    public void visitImage(ImageComposite c) {
        contentPanel.addImage(c);
    }
}
