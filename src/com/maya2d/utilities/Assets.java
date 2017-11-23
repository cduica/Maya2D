package com.maya2d.utilities;

import com.maya2d.model.ImageComposite;

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

    public BufferedImage TRIANGLE_ICON_PRESSED;
    public BufferedImage SQUARE_ICON_PRESSED;
    public BufferedImage ROUND_SQUARE_ICON_PRESSED;
    public BufferedImage CIRCLE_ICON_PRESSED;

    public BufferedImage TRANSLATE_TOOL;
    public BufferedImage ROTATE_TOOL;
    public BufferedImage EXPAND_TOOL;

    public BufferedImage PLAY_BUTTON;
    public BufferedImage BACK_BUTTON;
    public BufferedImage FORWARD_BUTTON;

    private Assets() {
        try {
            MAYA2D_LOGO = ImageIO.read(getClass().getResourceAsStream("/maya2dlogo.png"));
            TRIANGLE_ICON = ImageIO.read(getClass().getResourceAsStream("/triangle.png"));
            SQUARE_ICON = ImageIO.read(getClass().getResourceAsStream("/square.png"));
            ROUND_SQUARE_ICON = ImageIO.read(getClass().getResourceAsStream("/round_square.png"));
            CIRCLE_ICON = ImageIO.read(getClass().getResourceAsStream("/circle.png"));
            TRIANGLE_ICON_PRESSED = ImageIO.read(getClass().getResourceAsStream("/triangle_pressed.png"));
            SQUARE_ICON_PRESSED = ImageIO.read(getClass().getResourceAsStream("/square_pressed.png"));
            ROUND_SQUARE_ICON_PRESSED = ImageIO.read(getClass().getResourceAsStream("/round_square_pressed.png"));
            CIRCLE_ICON_PRESSED = ImageIO.read(getClass().getResourceAsStream("/circle_pressed.png"));
            TRANSLATE_TOOL = ImageIO.read(getClass().getResourceAsStream("/translate_tool.png"));
            ROTATE_TOOL = ImageIO.read(getClass().getResourceAsStream("/rotate_tool.png"));
            EXPAND_TOOL = ImageIO.read(getClass().getResourceAsStream("/expand_tool.png"));
            PLAY_BUTTON = ImageIO.read(getClass().getResourceAsStream("/play_button.png"));
            BACK_BUTTON = ImageIO.read(getClass().getResourceAsStream("/back_button.png"));
            FORWARD_BUTTON = ImageIO.read(getClass().getResourceAsStream("/forward_button.png"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}
