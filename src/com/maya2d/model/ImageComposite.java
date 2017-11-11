package com.maya2d.model;

import com.maya2d.view.DrawingVisitor;
import com.maya2d.view.Visitor;

import java.awt.image.BufferedImage;

public class ImageComposite extends Component {

    private BufferedImage image;

    public ImageComposite(BufferedImage image){
        this.image = image;
    }

    @Override
    public void rotate(double angle) {

    }

    @Override
    public void expand(double factor) {

    }

    @Override
    public void accept(Visitor v) {
        v.visitImage(this);
        notifyObservers();
    }

    public BufferedImage getImage() {
        return image;
    }
}
