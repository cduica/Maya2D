package com.maya2d.view;

import com.maya2d.model.MayaCanvas;
import com.maya2d.model.ShapeComposite;
import com.maya2d.utilities.Assets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class EditingPanel extends JPanel implements MouseListener, MouseMotionListener, Subject{

    private Rectangle triangle;
    private Rectangle circle;
    private Rectangle square;
    private Rectangle roundSquare;
    private Rectangle translateTool;
    private Rectangle rotateTool;
    private Rectangle expandTool;

    private BufferedImage currTriangleIcon = Assets.getInstance().TRIANGLE_ICON;
    private BufferedImage currCircleIcon = Assets.getInstance().CIRCLE_ICON;
    private BufferedImage currSquareIcon = Assets.getInstance().SQUARE_ICON;
    private BufferedImage currRoundSquareIcon = Assets.getInstance().ROUND_SQUARE_ICON;

    private BufferedImage translateToolIcon = Assets.getInstance().TRANSLATE_TOOL;
    private BufferedImage rotateToolIcon = Assets.getInstance().ROTATE_TOOL;
    private BufferedImage expandToolIcon = Assets.getInstance().EXPAND_TOOL;

    private boolean translatePressed;
    private boolean rotatePressed;
    private boolean expandPressed;

    private java.util.List<Observer> observers;

    private MayaCanvas canvas;

    public EditingPanel(){
        this.canvas = canvas;
        addMouseMotionListener( this );
        addMouseListener( this );
        observers = new ArrayList<>();
        this.setLayout(new BorderLayout());
        // hardcoded layout haha
        triangle = new Rectangle(20,25,25, 25);
        circle = new Rectangle(60, 25,25, 25);
        square = new Rectangle(100, 25,25, 25);
        roundSquare = new Rectangle(140,25,25, 25);
        translateTool = new Rectangle(191, 24, 28, 28);
        rotateTool = new Rectangle(230, 24, 28, 28);
        expandTool = new Rectangle(270, 24, 28, 28);
        try {
            translateToolIcon = resizeImage(translateToolIcon, 25, 25, BufferedImage.TYPE_INT_ARGB);
            rotateToolIcon = resizeImage(rotateToolIcon, 25, 25, BufferedImage.TYPE_INT_ARGB);
            expandToolIcon = resizeImage(expandToolIcon, 25, 25, BufferedImage.TYPE_INT_ARGB);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(0xFF, 0xFF, 0xFF, 0x00));
        g2d.draw(triangle);
        g2d.draw(circle);
        g2d.draw(square);
        g2d.draw(roundSquare);
        g.drawImage(currTriangleIcon, 20, 25, this);
        g.drawImage(currCircleIcon, 60, 25, this);
        g.drawImage(currSquareIcon, 100, 25, this);
        g.drawImage(currRoundSquareIcon, 140, 25, this);
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.drawString("Shapes", 20, 16);
        g2d.drawLine(179, 50, 179, 25);
        g.drawImage(translateToolIcon, 192, 25, this);
        g.drawImage(rotateToolIcon, 232, 25, this);
        g.drawImage(expandToolIcon, 272, 25, this);
        g2d.drawString("Tools", 190, 16);
        g2d.setColor(Color.YELLOW);
        if(translatePressed){
            g2d.draw(translateTool);
        }
        if(rotatePressed){
            g2d.draw(rotateTool);
        }
        if(expandPressed){
            g2d.draw(expandTool);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(triangle.contains(e.getPoint())) {
            System.out.println("Triangle clicked");
            Polygon p = new Polygon();
            ShapeComposite shapeComposite = new ShapeComposite(p);
            shapeComposite.setIdentifier("Shape " + canvas.getNumComponents());
            canvas.add(shapeComposite, new Point(-9999, -9999));
            canvas.notifyObservers();
        } else if(circle.contains(e.getPoint())){
            System.out.println("Circle clicked");
            Ellipse2D c = new Ellipse2D.Double();
            ShapeComposite shapeComposite = new ShapeComposite(c);
            shapeComposite.setIdentifier("Shape " + canvas.getNumComponents());
            canvas.add(shapeComposite, new Point(-9999, -9999));
            canvas.notifyObservers();
        } else if(square.contains(e.getPoint())){
            System.out.println("Square clicked");
            Rectangle r = new Rectangle();
            ShapeComposite shapeComposite = new ShapeComposite(r);
            shapeComposite.setIdentifier("Shape " + canvas.getNumComponents());
            canvas.add(shapeComposite, new Point(-9999, -9999));
            canvas.notifyObservers();
        } else if(roundSquare.contains(e.getPoint())){
            System.out.println("Round square clicked");
            RoundRectangle2D r = new RoundRectangle2D.Double();
            ShapeComposite shapeComposite = new ShapeComposite(r);
            shapeComposite.setIdentifier("Shape " + canvas.getNumComponents());
            canvas.add(shapeComposite, new Point(-9999, -9999));
            canvas.notifyObservers();
        }
        updateUI();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(triangle.contains(e.getPoint())) {
            currTriangleIcon = Assets.getInstance().TRIANGLE_ICON_PRESSED;
        } else if(circle.contains(e.getPoint())){
            currCircleIcon = Assets.getInstance().CIRCLE_ICON_PRESSED;
        } else if(square.contains(e.getPoint())){
            currSquareIcon = Assets.getInstance().SQUARE_ICON_PRESSED;
        } else if(roundSquare.contains(e.getPoint())){
            currRoundSquareIcon = Assets.getInstance().ROUND_SQUARE_ICON_PRESSED;
        } else if(translateTool.contains(e.getPoint())){
            translatePressed = !translatePressed;
        } else if(rotateTool.contains(e.getPoint())){
            rotatePressed = !rotatePressed;
        } else if(expandTool.contains(e.getPoint())){
            expandPressed = !expandPressed;
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(triangle.contains(e.getPoint())) {
            currTriangleIcon = Assets.getInstance().TRIANGLE_ICON;
        } else if(circle.contains(e.getPoint())){
            currCircleIcon = Assets.getInstance().CIRCLE_ICON;
        } else if(square.contains(e.getPoint())){
            currSquareIcon = Assets.getInstance().SQUARE_ICON;
        } else if(roundSquare.contains(e.getPoint())){
            currRoundSquareIcon = Assets.getInstance().ROUND_SQUARE_ICON;
        }
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(triangle.contains(e.getPoint()) || circle.contains(e.getPoint()) || square.contains(e.getPoint()) || roundSquare.contains(e.getPoint())) {
            this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        } else {
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height, int type) throws IOException {
        BufferedImage resizedImage = new BufferedImage(width, height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
    }

    @Override
    public void attach(Observer o) {
        observers.add(o);
    }

    @Override
    public void detach(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for(int i = 0; i < observers.size(); ++i){
            observers.get(i).update();
        }
    }

    public void setCanvas(MayaCanvas mayaCanvas){
        this.canvas = mayaCanvas;
    }
}
