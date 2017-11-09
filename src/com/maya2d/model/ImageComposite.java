package com.maya2d.model;

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
    public void accept(ComponentVisitor v) {

    }

}
