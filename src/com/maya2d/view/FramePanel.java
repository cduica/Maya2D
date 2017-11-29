package com.maya2d.view;

import com.maya2d.model.AnimationFrame;
import com.maya2d.model.MayaCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

public class FramePanel extends JPanel implements MouseListener, MouseMotionListener, Observer {

    private JScrollPane jScrollPane;
    private BufferedImage frameBar;
    private PlayControlPanel playControlPanel;
    private Graphics2D g2d;
    private int width;
    private int height;
    private int numFrames;
    private final int INTERVAL_SIZE = 35;
    private MayaCanvas canvas;
    private java.util.List<Integer> keyframedStates;

    public FramePanel() {
        numFrames = 30;
        this.setLayout(new BorderLayout());
        initFrames();
        updateFrameBar();
        // this.add(jScrollPane);
        playControlPanel = new PlayControlPanel();
        playControlPanel.setPreferredSize(new Dimension(250, 60));
        playControlPanel.setBackground(new Color(25, 25, 25));
        playControlPanel.setNumFrames(numFrames);
        playControlPanel.attach(this);
        this.add(playControlPanel, BorderLayout.EAST);
    }

    private void initFrames(){
        width = numFrames*INTERVAL_SIZE;
        height = 40;
        frameBar = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g2d = frameBar.createGraphics();
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(0, 0, frameBar.getWidth(), frameBar.getHeight());
        g2d.setColor(Color.LIGHT_GRAY);
        for(int i = 0; i < numFrames; ++i) {
            int x = i*INTERVAL_SIZE;
            Line2D line = new Line2D.Double(x, 20, x, 40);
            g2d.draw(line);
            g2d.setFont(UIManager.getFont("Label.font"));
            g2d.drawString("" + i, x, 15);
        }
    }

    private void drawKeyframedStates() {

        if(canvas.getSelected()==null)
            return;

        if(keyframedStates!=null) {
            for(int i = 0; i < keyframedStates.size(); ++i) {
                g2d.setColor(Color.DARK_GRAY);
                Line2D line = new Line2D.Double(keyframedStates.get(i)*INTERVAL_SIZE, 0, keyframedStates.get(i)*INTERVAL_SIZE, height);
                g2d.draw(line);
                g2d.setColor(Color.LIGHT_GRAY);
                line = new Line2D.Double(keyframedStates.get(i)*INTERVAL_SIZE, 20, keyframedStates.get(i)*INTERVAL_SIZE, 40);
                g2d.draw(line);
            }
        }

        keyframedStates = canvas.getSelected().getKeyFramedStates();

        g2d.setColor(Color.RED);
        for (int i = 0; i < keyframedStates.size(); ++i) {
            int x = keyframedStates.get(i);
            Line2D line = new Line2D.Double(x * INTERVAL_SIZE, 0, x * INTERVAL_SIZE, height);
            g2d.draw(line);
        }
        updateFrameBar();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        drawSelected(e);
        updateCurrentFrameInCanvas();
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

    public void updateCurrentFrameInCanvas() {
        canvas.setComponentsToFrame((AnimationFrame.getInstance().getFrame()));
    }

    private void drawSelected(MouseEvent e) {
        g2d.setColor(Color.DARK_GRAY);
        Line2D line = new Line2D.Double(AnimationFrame.getInstance().getFrame()*INTERVAL_SIZE, 0, AnimationFrame.getInstance().getFrame()*INTERVAL_SIZE, height);
        g2d.draw(line);
        g2d.setColor(Color.LIGHT_GRAY);
        line = new Line2D.Double(AnimationFrame.getInstance().getFrame()*INTERVAL_SIZE, 20, AnimationFrame.getInstance().getFrame()*INTERVAL_SIZE, 40);
        g2d.draw(line);

        // get approx frame number

        int frameNumber = (int) e.getPoint().getX() + jScrollPane.getHorizontalScrollBar().getValue();

        AnimationFrame.getInstance().setFrame(frameNumber/INTERVAL_SIZE);
        line = new Line2D.Double(AnimationFrame.getInstance().getFrame()*INTERVAL_SIZE, 0, AnimationFrame.getInstance().getFrame()*INTERVAL_SIZE, height);
        g2d.draw(line);
        drawKeyframedStates();
        updateFrameBar();
    }

    private void updateFrameBar(){

        int offset = 0;
        if(jScrollPane!=null) {
            this.remove(jScrollPane);
            offset = jScrollPane.getHorizontalScrollBar().getValue();
        }
        jScrollPane = new JScrollPane(new JLabel(new ImageIcon(frameBar)));
        jScrollPane.setBackground(Color.DARK_GRAY);
        jScrollPane.addMouseMotionListener( this );
        jScrollPane.addMouseListener( this );
        jScrollPane.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.add(jScrollPane);
        this.validate();
        jScrollPane.getHorizontalScrollBar().setValue(offset);
    }

    @Override
    public void update() {
        this.numFrames = playControlPanel.getNumFrames();
        initFrames();
        drawKeyframedStates();
        updateFrameBar();
    }

    public void setCanvas(MayaCanvas canvas) {
        this.canvas = canvas;
        playControlPanel.setCanvas(canvas);
    }

    public void setContentPanel(ContentPanel contentPanel) {
        playControlPanel.setContentPanel(contentPanel);
    }
}
