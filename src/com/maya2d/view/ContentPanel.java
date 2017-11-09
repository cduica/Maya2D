package com.maya2d.view;

import javax.swing.*;
import java.awt.*;

public class ContentPanel extends JPanel {


    private static final int DRAWING_SIZE = 1350;
    private static final int SUBDIVISIONS = 30;
    private static final int SUBDIVISION_SIZE = DRAWING_SIZE / SUBDIVISIONS;

    public ContentPanel(){
        this.setBackground(new Color(65, 65, 65));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(new Color(85, 85, 85));
        for (int i = 1; i < SUBDIVISIONS; i++) {
            int x = i * SUBDIVISION_SIZE;
            g2.drawLine(x, 0, x, getSize().height);
        }
        for (int i = 1; i < SUBDIVISIONS; i++) {
            int y = i * SUBDIVISION_SIZE;
            g2.drawLine(0, y, getSize().width, y);
        }
    }

}
