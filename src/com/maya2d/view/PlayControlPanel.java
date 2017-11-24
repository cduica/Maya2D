package com.maya2d.view;

import com.maya2d.utilities.Assets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class PlayControlPanel extends JPanel implements MouseListener, MouseMotionListener, Subject {

    private Rectangle backButton;
    private Rectangle forwardButton;
    private Rectangle playButton;
    private int numFrames;
    private JTextField frameField;
    private java.util.List<Observer> observers;

    public PlayControlPanel(){
        addMouseListener(this);
        addMouseMotionListener(this);

        observers = new ArrayList<>();

        this.backButton = new Rectangle(145, 20, 20, 20);
        this.forwardButton = new Rectangle(195, 20, 20, 20);
        this.playButton = new Rectangle(170, 20, 20, 20);

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        JLabel frameLabel = new JLabel("Frames:");
        frameLabel.setForeground(Color.LIGHT_GRAY);
        this.add(Box.createRigidArea(new Dimension(10, 0)));
        this.add(frameLabel);
        this.setFocusable(true);
        frameField = new JTextField(10);
        frameField.setMaximumSize(new Dimension(50, 20));
        frameField.setBackground(Color.DARK_GRAY);
        frameField.setForeground(Color.LIGHT_GRAY);
        frameField.setCaretColor(Color.LIGHT_GRAY);
        frameField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER) {
                    // recreate frame ruler with new frames number
                    numFrames = Integer.parseInt(frameField.getText());
                    notifyObservers();
                    transferFocusBackward();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        this.add(Box.createRigidArea(new Dimension(10, 0)));
        this.add(frameField);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;

        try {
            g.drawImage(resizeImage(Assets.getInstance().BACK_BUTTON, 20, 20, BufferedImage.TYPE_INT_ARGB), 145, 20, this);
            g.drawImage(resizeImage(Assets.getInstance().PLAY_BUTTON, 20, 20, BufferedImage.TYPE_INT_ARGB), 170, 21, this);
            g.drawImage(resizeImage(Assets.getInstance().FORWARD_BUTTON, 20, 20, BufferedImage.TYPE_INT_ARGB), 195, 20, this);
        } catch (Exception e){
            e.printStackTrace();
        }

        g2d.setColor(new Color(0, 0, 0, 0));
        g2d.draw(backButton);
        g2d.draw(forwardButton);
        g2d.draw(playButton);
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int width, int height, int type) throws IOException {
        BufferedImage resizedImage = new BufferedImage(width, height, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(backButton.contains(e.getPoint())) {
            System.out.println("back pressed");
        } else if(forwardButton.contains(e.getPoint())) {
            System.out.println("forward pressed");
        } else if(playButton.contains(e.getPoint())){
            System.out.println("play pressed");
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

    }

    public int getNumFrames() {
        return numFrames;
    }

    public void setNumFrames(int numFrames) {
        this.numFrames = numFrames;
        this.frameField.setText("" + numFrames);
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
        for(int i = 0; i < observers.size(); ++i) {
            observers.get(i).update();
        }
    }
}
