package com.maya2d.view;

import com.maya2d.model.ImageComposite;
import com.maya2d.model.MayaCanvas;
import com.maya2d.model.ShapeComposite;
import com.maya2d.model.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class ContentPanel extends JPanel implements MouseListener, MouseMotionListener, Observer {


    private static final int DRAWING_SIZE = 1350;
    private static final int SUBDIVISIONS = 30;
    private static final int SUBDIVISION_SIZE = DRAWING_SIZE / SUBDIVISIONS;
    private int planeHeight = 1280;
    private int planeWidth = 4000;
    private Camera camera;
    private double mPosX = 0;
    private double mPosY = 0;
    private boolean altPressed;
    private java.util.List<ImageComposite> imageComposites;
    private java.util.List<ShapeComposite> shapeComposites;
    private MayaCanvas canvas;
    private int currentFrame = 0;

    public ContentPanel(){
        this.canvas = canvas;
        imageComposites = new ArrayList<>();
        shapeComposites = new ArrayList<>();
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

        // paint shapes in the current camera context
        for (int i = 0; i < shapeComposites.size(); ++i){
            ShapeComposite c = shapeComposites.get(i);
            if(c.getShape() instanceof Rectangle){
                State s = c.getStateAtFrame(0);
                int x = (int) (s.getPosition().getX() + camera.getX());
                int y = (int) (s.getPosition().getY() - camera.getY());
                Rectangle r = new Rectangle(x, y, 45, 45);
                g2.setColor(s.getColor());
                g2.fill(r);
            }

        }

        // paint images in the current camera context
        for(int i = 0; i < imageComposites.size(); ++i){

        }
    }

    public void addImage(ImageComposite i){
        imageComposites.add(i);
    }

    public void addShape(ShapeComposite s){
        shapeComposites.add(s);
    }

    private void deleteImage(Point p){
        imageComposites.remove(canvas.get(p));
        canvas.remove(p);
    }

    private void deleteShape(Point p){
        shapeComposites.remove(canvas.get(p));
        canvas.remove(p);
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
        repaint();
        //System.out.println(this.getWidth());
    }

    @Override
    public void update() {
        if(canvas.contains(new Point(-9999, -9999))) {
            com.maya2d.model.Component newShape = canvas.get(new Point(-9999, -9999));
            Point p = new Point(getWidth()/2, getHeight()/2);
            State s = new State(p, new Color(0x00d1ff), 1.0, true, 0);
            newShape.addState(s);
            newShape.attach(this);
            canvas.remove(new Point(-9999, -9999));
            canvas.add(newShape, p);
        }
        canvas.draw();
        repaint();
    }

    public void setCanvas(MayaCanvas mayaCanvas){
        this.canvas = mayaCanvas;
    }
}
