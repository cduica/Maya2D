package com.maya2d.view;

import com.maya2d.utilities.Assets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class EditingPanel extends JPanel implements MouseListener, MouseMotionListener {

    private Rectangle triangle;
    private Rectangle circle;
    private Rectangle square;
    private Rectangle roundSquare;

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
        g.drawImage(Assets.getInstance().TRIANGLE_ICON, 20, 9, this);
        g.drawImage(Assets.getInstance().CIRCLE_ICON, 60, 9, this);
        g.drawImage(Assets.getInstance().SQUARE_ICON, 100, 9, this);
        g.drawImage(Assets.getInstance().ROUND_SQUARE_ICON, 140, 9, this);
        g2d.setColor(new Color(0xFF, 0xFF, 0xFF, 0x00));
        g2d.draw(triangle);
        g2d.draw(circle);
        g2d.draw(square);
        g2d.draw(roundSquare);
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
    }

    @Override
    public void mousePressed(MouseEvent e) {

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

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
