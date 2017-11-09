package com.maya2d.utilities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Assets {
    private static Assets ourInstance = new Assets();

    public static Assets getInstance() {
        return ourInstance;
    }

    public BufferedImage MAYA2D_LOGO;

    public BufferedImage TRIANGLE_ICON;
    public BufferedImage SQUARE_ICON;
    public BufferedImage ROUND_SQUARE_ICON;
    public BufferedImage CIRCLE_ICON;

    private Assets() {
        try {
            MAYA2D_LOGO = ImageIO.read(getClass().getResourceAsStream("/maya2dlogo.png"));
            TRIANGLE_ICON = ImageIO.read(getClass().getResourceAsStream("/triangle.png"));
            SQUARE_ICON = ImageIO.read(getClass().getResourceAsStream("/square.png"));
            ROUND_SQUARE_ICON = ImageIO.read(getClass().getResourceAsStream("/round_square.png"));
            CIRCLE_ICON = ImageIO.read(getClass().getResourceAsStream("/circle.png"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}
