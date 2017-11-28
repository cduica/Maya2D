package com.maya2d.utilities;

import java.awt.*;

public class ColorInterpolator {

    /**
     * Creates a gradient via procedure taught in class
     * @param startRGB, endRGB, steps
     * @return
     */
    public Color[] createGradient(int startRGB, int endRGB, int steps){

        Color[] segments = new Color[steps];

        double red = 0;
        double green = 0;
        double blue = 0;

        int curr = startRGB;

        double deltaRed = findDeltaRed(startRGB, endRGB, steps);
        double deltaGreen = findDeltaGreen(startRGB, endRGB, steps);
        double deltaBlue = findDeltaBlue(startRGB, endRGB, steps);

        for(int i = 0; i < steps; i++){
            red+=deltaRed;
            green+=deltaGreen;
            blue+=deltaBlue;
            int aRGB = createARGB(curr, (int)red, (int)green, (int)blue);
            segments[i] = new Color(extractRed(aRGB), extractGreen(aRGB), extractBlue(aRGB));
        }

        return segments;
    }

    /**
     * Creates an aRGB value from the separate channels
     * @param curr
     * @param red
     * @param green
     * @param blue
     * @return
     */
    private int createARGB(int curr, int red, int green, int blue){
        int dRed = extractRed(curr) + red;
        int dGreen = extractGreen(curr) + green;
        int dBlue = extractBlue(curr) + blue;
        return 0xFF000000 | ((dRed) << 16)&0x00FF0000 | ((dGreen) << 8)&0x0000FF00 | dBlue;
    }

    private double findDeltaRed(int source, int destination, int size){
        return (double)(extractRed(destination) - extractRed(source)) / size;
    }

    private double findDeltaGreen(int source, int destination, int size){
        return (double)(extractGreen(destination) - extractGreen(source)) / size;
    }

    private double findDeltaBlue(int source, int destination, int size){
        return (double)(extractBlue(destination) - extractBlue(source)) / size;
    }

    private int extractRed(int aRGB){
        return (aRGB >>> 16)&0x000000FF;
    }

    private int extractGreen(int aRGB){
        return (aRGB >>> 8)&0x000000FF;
    }

    private int extractBlue(int aRGB){
        return aRGB&0x000000FF;
    }

}
