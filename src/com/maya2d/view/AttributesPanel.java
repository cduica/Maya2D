package com.maya2d.view;

import com.maya2d.model.MayaCanvas;
import com.maya2d.model.State;
import com.sun.codemodel.internal.JOp;
import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import javax.swing.plaf.basic.DefaultMenuLayout;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class AttributesPanel extends JPanel implements MouseListener, MouseMotionListener, Subject, Observer {

    private JTextField identifierField;
    private JTextField xField;
    private JTextField yField;
    private JTextField rotateField;
    private Rectangle red;
    private Rectangle orange;
    private Rectangle pink;
    private Rectangle green;
    private Rectangle blue;
    private Rectangle customButton;
    private java.util.List<Observer> observers;
    private MayaCanvas canvas;

    public AttributesPanel(){
        addMouseMotionListener( this );
        addMouseListener( this );
        observers = new ArrayList<>();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        // this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        JLabel identifierLabel = new JLabel("Identifier:");
        identifierLabel.setForeground(Color.LIGHT_GRAY);
        this.identifierField = new JTextField(18);
        this.identifierField.setMaximumSize(new Dimension(200, 22));
        identifierField.setBackground(Color.DARK_GRAY);
        identifierField.setForeground(Color.LIGHT_GRAY);
        identifierField.setCaretColor(Color.LIGHT_GRAY);
        JLabel colorLabel = new JLabel("Color:");
        colorLabel.setForeground(Color.LIGHT_GRAY);
        // Hardcoded layout again killl mee
        red = new Rectangle(14, 85, 20, 20);
        orange = new Rectangle(49, 85, 20, 20);
        pink = new Rectangle(84, 85, 20, 20);
        green = new Rectangle(119, 85, 20, 20);
        blue = new Rectangle(154, 85, 20, 20);
        customButton = new Rectangle(184, 85, 55, 20);
        JLabel xLabel = new JLabel("X: ");
        xLabel.setForeground(Color.LIGHT_GRAY);
        JLabel yLabel = new JLabel("Y: ");
        yLabel.setForeground(Color.LIGHT_GRAY);
        this.add(identifierLabel);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        Box identifierBox = Box.createHorizontalBox();
        identifierBox.add(Box.createHorizontalGlue());
        identifierBox.add(identifierField);
        identifierBox.add(Box.createHorizontalGlue());
        this.add(identifierBox);
        this.add(Box.createRigidArea(new Dimension(0, 12)));
        this.add(colorLabel);
        this.add(Box.createRigidArea(new Dimension(0, 45)));
        this.add(xLabel);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        xField = new JTextField(18);
        xField.setMaximumSize(new Dimension(100, 22));
        xField.setBackground(Color.DARK_GRAY);
        xField.setForeground(Color.LIGHT_GRAY);
        xField.setCaretColor(Color.LIGHT_GRAY);
        Box xBox = Box.createHorizontalBox();
        xBox.add(Box.createHorizontalGlue());
        xBox.add(xField);
        xBox.add(Box.createHorizontalGlue());
        this.add(xBox);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(yLabel);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        yField = new JTextField(18);
        yField.setMaximumSize(new Dimension(100, 22));
        yField.setBackground(Color.DARK_GRAY);
        yField.setForeground(Color.LIGHT_GRAY);
        yField.setCaretColor(Color.LIGHT_GRAY);
        Box yBox = Box.createHorizontalBox();
        yBox.add(Box.createHorizontalGlue());
        yBox.add(yField);
        yBox.add(Box.createHorizontalGlue());
        this.add(yBox);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        JLabel rotationLabel = new JLabel("Rotation: ");
        rotationLabel.setForeground(Color.LIGHT_GRAY);
        this.add(rotationLabel);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        rotateField = new JTextField(18);
        rotateField.setMaximumSize(new Dimension(100, 22));
        rotateField.setBackground(Color.DARK_GRAY);
        rotateField.setForeground(Color.LIGHT_GRAY);
        rotateField.setCaretColor(Color.LIGHT_GRAY);
        Box rotateBox = Box.createHorizontalBox();
        rotateBox.add(Box.createHorizontalGlue());
        rotateBox.add(rotateField);
        rotateBox.add(Box.createHorizontalGlue());
        this.add(rotateBox);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.RED);
        g2d.fill(red);
        g2d.setColor(Color.ORANGE);
        g2d.fill(orange);
        g2d.setColor(Color.PINK);
        g2d.fill(pink);
        g2d.setColor(Color.GREEN);
        g2d.fill(green);
        g2d.setColor(Color.BLUE);
        g2d.fill(blue);
        g2d.setColor(Color.BLACK);
        g2d.fill(customButton);
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.setFont(identifierField.getFont());
        g2d.drawString("Custom", 188, 100);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("D Rose");
        System.out.println(e.getPoint().getX() + ", " + e.getPoint().getY());
        if(!identifierField.contains(e.getPoint())){
            canvas.getSelected().setIdentifier(identifierField.getText());
            identifierField.transferFocusBackward();
//            xField.transferFocus();
//            yField.transferFocus();
//            rotateField.transferFocus();
        }
        if(red.contains(e.getPoint())){
            com.maya2d.model.Component c = canvas.getSelected();
            State s = c.getStateAtFrame(0);
            s.setColor(Color.RED);
            notifyObservers();
        } else if(orange.contains(e.getPoint())){
            com.maya2d.model.Component c = canvas.getSelected();
            State s = c.getStateAtFrame(0);
            s.setColor(Color.ORANGE);
            notifyObservers();
        } else if(pink.contains(e.getPoint())){
            com.maya2d.model.Component c = canvas.getSelected();
            State s = c.getStateAtFrame(0);
            s.setColor(Color.PINK);
            notifyObservers();
        } else if(green.contains(e.getPoint())){
            com.maya2d.model.Component c = canvas.getSelected();
            State s = c.getStateAtFrame(0);
            s.setColor(Color.GREEN);
            notifyObservers();
        } else if(blue.contains(e.getPoint())){
            com.maya2d.model.Component c = canvas.getSelected();
            State s = c.getStateAtFrame(0);
            s.setColor(Color.BLUE);
            notifyObservers();
        } else if(customButton.contains(e.getPoint())){
            com.maya2d.model.Component c = canvas.getSelected();
            State s = c.getStateAtFrame(0);
            String userInput = JOptionPane.showInputDialog("Enter custom color [0xRRGGBB]: ");
            int i =(int) Long.parseLong( userInput.substring( 2, userInput.length() ), 16 );
            s.setColor(new Color(i));
            notifyObservers();
        }
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
        if(red.contains(e.getPoint())|| orange.contains(e.getPoint()) ||
                pink.contains(e.getPoint()) || green.contains(e.getPoint()) ||
                blue.contains(e.getPoint()) || customButton.contains(e.getPoint())){
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        } else {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
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

    @Override
    public void update() {
        if(canvas.getSelected()!=null)
        identifierField.setText(canvas.getSelected().getIdentifier());
    }
}
