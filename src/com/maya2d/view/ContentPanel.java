package com.maya2d.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ContentPanel extends JPanel implements MouseListener, MouseMotionListener {


    private static final int DRAWING_SIZE = 1350;
    private static final int SUBDIVISIONS = 30;
    private static final int SUBDIVISION_SIZE = DRAWING_SIZE / SUBDIVISIONS;
    private int planeHeight = 1280;
    private int planeWidth = 4000;
    private Camera camera;
    private double mPosX = 0;
    private double mPosY = 0;
    private Graphics g;
    private boolean altPressed;

    public ContentPanel(){
        this.setBackground(new Color(65, 65, 65));
        this.camera = new Camera(0, 0);
        addMouseMotionListener( this );
        addMouseListener( this );
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // do nothing
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ALT){
                    System.out.println("Alt pressed");
                    altPressed = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ALT){
                    System.out.println("Alt pressed");
                    altPressed = false;
                }
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.g = g;
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(new Color(85, 85, 85));
        for (int i = 1; i < SUBDIVISIONS; i++) {
            int x = i * SUBDIVISION_SIZE + (int)camera.getX();
            g2.drawLine(x, 0, x, getSize().height);
        }
        for (int i = 1; i < SUBDIVISIONS; i++) {
            int y = i * SUBDIVISION_SIZE - (int)camera.getY();
            g2.drawLine(0, y, getSize().width, y);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mPosX = e.getPoint().getX();
        mPosY = e.getPoint().getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(altPressed)
            updateCameraPosition(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    private void updateCameraPosition(MouseEvent e){
        double deltaX = e.getPoint().getX() - mPosX;
        double deltaY = mPosY - e.getPoint().getY();

        mPosX = e.getPoint().getX();
        mPosY = e.getPoint().getY();

        camera.setX(deltaX + camera.getX());
        camera.setY(deltaY + camera.getY());
        System.out.println(camera.getX() + ", " + camera.getY());
        paintComponent(g);
        updateUI();
        //System.out.println(this.getWidth());
    }
}
