package com.maya2d.view;

import com.maya2d.model.*;
import org.w3c.dom.css.Rect;

import javax.sound.sampled.Line;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

public class ContentPanel extends JPanel implements MouseListener, MouseMotionListener, Observer {


    private static final int DRAWING_SIZE = 1350;
    private static final int SUBDIVISIONS = 100;
    private static final int SUBDIVISION_SIZE = 45;
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
    private com.maya2d.model.Component selected;
    private MayaSelector mayaSelector;

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
            ShapeComposite shapeComposite = shapeComposites.get(i);
            State s = shapeComposite.getStateAtFrame(0);
            int x = (int) (s.getPosition().getX() + camera.getX());
            int y = (int) (s.getPosition().getY() - camera.getY());
            if(shapeComposite.getShape() instanceof Polygon){
                int[] xPoints = {x, x+23, x+46};
                int[] yPoints = {y, y-40, y};
                Polygon p = new Polygon(xPoints, yPoints, 3);
                shapeComposite.setShape(p);
                g2.setColor(s.getColor());
                g2.fill(p);
            } else if(shapeComposite.getShape() instanceof Ellipse2D){
                Ellipse2D c = new Ellipse2D.Double(x, y, 46, 46);
                shapeComposite.setShape(c);
                g2.setColor(s.getColor());
                g2.fill(c);
            } else if(shapeComposite.getShape() instanceof Rectangle){
                Rectangle r = new Rectangle(x, y, 46, 46);
                shapeComposite.setShape(r);
                g2.setColor(s.getColor());
                g2.fill(r);
            } else if(shapeComposite.getShape() instanceof RoundRectangle2D){
                RoundRectangle2D r = new RoundRectangle2D.Double(x, y, 46-8, 46-8, 25, 25);
                shapeComposite.setShape(r);
                g2.setColor(s.getColor());
                g2.fill(r);
            }

        }

        // paint images in the current camera context
        for(int i = 0; i < imageComposites.size(); ++i){

        }

        if(mayaSelector!=null){
            java.util.List<Shape> shapes = mayaSelector.getShapes();
            Rectangle rect = mayaSelector.getRect();
            Line2D lineY = mayaSelector.getLineY();
            Polygon p1 = mayaSelector.getP1();
            Line2D lineX = mayaSelector.getLineX();
            Polygon p2 = mayaSelector.getP2();
            Rectangle xy = mayaSelector.getXy();
            g2.setColor(mayaSelector.getColor(rect));
            g2.draw(rect);
            g2.setColor(mayaSelector.getColor(lineY));
            g2.draw(lineY);
            g2.setColor(mayaSelector.getColor(p1));
            g2.fill(p1);
            g2.setColor(mayaSelector.getColor(lineX));
            g2.draw(lineX);
            g2.setColor(mayaSelector.getColor(p2));
            g2.fill(p2);
            g2.setColor(mayaSelector.getColor(xy));
            g2.fill(xy);
        }
    }

    public void addImage(ImageComposite i){
        if(!imageComposites.contains(i))
            imageComposites.add(i);
    }

    public void addShape(ShapeComposite s){
        if(!shapeComposites.contains(s))
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

    public void createSelector(int x, int y){
        mayaSelector = new MayaSelector(x, y);
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        double x = e.getPoint().getX();
        double y = e.getPoint().getY();

        for(int i = 0; i < shapeComposites.size(); ++i){
            Shape s = shapeComposites.get(i).getShape();
            State state = shapeComposites.get(i).getStateAtFrame(0);
            if(s.contains(x, y)){
                canvas.setSelected(shapeComposites.get(i));
                System.out.println(s.getBounds().width/2);
                createSelector((int) x, (int) y-15);
                break;
            } else {
                canvas.setSelected(null);
            }
        }
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
        if(altPressed) {
            updateCameraPosition(e);
        }
        if(canvas.getSelected()!=null && mayaSelector!= null) {
            if (mayaSelector.getRect().contains(mPosX, mPosY)) {
                updateSelectedPositionXY(e);
            } else if (mayaSelector.getLineY().contains(mPosX, mPosY) || mayaSelector.getP1().contains(mPosX, mPosY)) {
                updateSelectedPositionY(e);
            } else if (mayaSelector.getLineX().contains(mPosX, mPosY) || mayaSelector.getP2().contains(mPosX, mPosY)) {
                updateSelectedPositionX(e);
            } else if (mayaSelector.getXy().contains(mPosX, mayaSelector.getXy().getY())) {
                updateSelectedPositionDiag(e);
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    private void updateSelectedPositionY(MouseEvent e){
        if(canvas.getSelected()!=null){
            double deltaY = mPosY - e.getPoint().getY();
            State s = canvas.getSelected().getStateAtFrame(0);
            Point position = s.getPosition();
            mPosY = e.getPoint().getY();
            int y = (int)(position.getY() - deltaY);
            Point newPosition = new Point( (int)position.getX(), y);
            s.setPosition(newPosition);
            canvas.remove(position);
            canvas.add(canvas.getSelected(), newPosition);
            int selectorX = (mayaSelector.getX());
            int selectorY = (int)(mayaSelector.getY() - deltaY);
            mayaSelector = new MayaSelector(selectorX, selectorY);
            repaint();
        }
    }

    private void updateSelectedPositionX(MouseEvent e){
        if(canvas.getSelected()!=null){
            double deltaX = e.getPoint().getX() - mPosX;
            State s = canvas.getSelected().getStateAtFrame(0);
            Point position = s.getPosition();
            mPosX = e.getPoint().getX();
            int x = (int)(position.getX() + deltaX);
            Point newPosition = new Point( x, (int) position.getY());
            s.setPosition(newPosition);
            canvas.remove(position);
            canvas.add(canvas.getSelected(), newPosition);
            int selectorX = (int)(mayaSelector.getX() + deltaX);
            int selectorY = (mayaSelector.getY());
            mayaSelector = new MayaSelector(selectorX, selectorY);
            repaint();
        }
    }

    private void updateSelectedPositionDiag(MouseEvent e){
        if(canvas.getSelected()!=null){
            double deltaX = e.getPoint().getX() - mPosX;
            double deltaY = mPosY - e.getPoint().getY();
            State s = canvas.getSelected().getStateAtFrame(0);
            Point position = s.getPosition();
            mPosX = e.getPoint().getX();
            int x = (int)(position.getX() + deltaX);
            int y = (int)(position.getY() - deltaX);
            Point newPosition = new Point( x, y);
            s.setPosition(newPosition);
            canvas.remove(position);
            canvas.add(canvas.getSelected(), newPosition);
            int selectorX = (int)(mayaSelector.getX() + deltaX);
            int selectorY = (int)(mayaSelector.getY() - deltaX);
            mayaSelector = new MayaSelector(selectorX, selectorY);
            repaint();
        }
    }

    private void updateSelectedPositionXY(MouseEvent e){
        if(canvas.getSelected()!=null){
            double deltaX = e.getPoint().getX() - mPosX;
            double deltaY = mPosY - e.getPoint().getY();
            State s = canvas.getSelected().getStateAtFrame(0);
            Point position = s.getPosition();
            mPosX = e.getPoint().getX();
            mPosY = e.getPoint().getY();
            int x = (int)(position.getX() + deltaX);
            int y = (int)(position.getY() - deltaY);
            Point newPosition = new Point( x, y);
            s.setPosition(newPosition);
            canvas.remove(position);
            canvas.add(canvas.getSelected(), newPosition);
            int selectorX = (int)(mayaSelector.getX() + deltaX);
            int selectorY = (int)(mayaSelector.getY() - deltaY);
            mayaSelector = new MayaSelector(selectorX, selectorY);
            repaint();
        }
    }

    private void updateCameraPosition(MouseEvent e){
        double deltaX = e.getPoint().getX() - mPosX;
        double deltaY = mPosY - e.getPoint().getY();

        mPosX = e.getPoint().getX();
        mPosY = e.getPoint().getY();
        int x = (int)(deltaX + camera.getX());
        int y = (int)(deltaY + camera.getY());
        camera.setX(x);
        camera.setY(y);
        if(mayaSelector!=null)
            mayaSelector = new MayaSelector((int)(mayaSelector.getX() + deltaX), (int)(mayaSelector.getY() - deltaY));
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
