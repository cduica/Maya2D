package com.maya2d.view;

import com.maya2d.utilities.Assets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class EditingPanel extends JPanel implements MouseListener, MouseMotionListener {

    private Rectangle triangle;
    private Rectangle circle;
    private Rectangle square;
    private Rectangle roundSquare;

    private BufferedImage currTriangle = Assets.getInstance().TRIANGLE_ICON;
    private BufferedImage currCircle = Assets.getInstance().CIRCLE_ICON;
    private BufferedImage currSquare = Assets.getInstance().SQUARE_ICON;
    private BufferedImage currRoundSquare = Assets.getInstance().ROUND_SQUARE_ICON;

    public EditingPanel(){
        addMouseMotionListener( this );
        addMouseListener( this );

        // hardcoded layout haha
        triangle = new Rectangle(20,9,25, 25);
        circle = new Rectangle(60, 9,25, 25);
        square = new Rectangle(100, 9,25, 25);
        roundSquare = new Rectangle(140,9,25, 25);
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
        g.drawImage(currTriangle, 20, 9, this);
        g.drawImage(currCircle, 60, 9, this);
        g.drawImage(currSquare, 100, 9, this);
        g.drawImage(currRoundSquare, 140, 9, this);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(triangle.contains(e.getPoint())) {
            System.out.println("Triangle clicked");
        } else if(circle.contains(e.getPoint())){
            System.out.println("Circle clicked");
        } else if(square.contains(e.getPoint())){
            System.out.println("Square clicked");
        } else if(roundSquare.contains(e.getPoint())){
            System.out.println("Round square clicked");
        }
        updateUI();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(triangle.contains(e.getPoint())) {
            currTriangle = Assets.getInstance().TRIANGLE_ICON_PRESSED;
        } else if(circle.contains(e.getPoint())){
            currCircle = Assets.getInstance().CIRCLE_ICON_PRESSED;
        } else if(square.contains(e.getPoint())){
            currSquare = Assets.getInstance().SQUARE_ICON_PRESSED;
        } else if(roundSquare.contains(e.getPoint())){
            currRoundSquare = Assets.getInstance().ROUND_SQUARE_ICON_PRESSED;
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(triangle.contains(e.getPoint())) {
            currTriangle = Assets.getInstance().TRIANGLE_ICON;
        } else if(circle.contains(e.getPoint())){
            currCircle = Assets.getInstance().CIRCLE_ICON;
        } else if(square.contains(e.getPoint())){
            currSquare = Assets.getInstance().SQUARE_ICON;
        } else if(roundSquare.contains(e.getPoint())){
            currRoundSquare = Assets.getInstance().ROUND_SQUARE_ICON;
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
        if(triangle.contains(e.getPoint()) || circle.contains(e.getPoint()) || square.contains(e.getPoint()) || roundSquare.contains(e.getPoint()))
            this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
