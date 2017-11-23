package com.maya2d.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

public class FramePanel extends JPanel implements MouseListener, MouseMotionListener {

    private JScrollPane jScrollPane;
    private BufferedImage frameBar;
    private PlayControlPanel playControlPanel;
    private Graphics2D g2d;
    private int width;
    private int height;
    private int numFrames;
    private int size;
    private int frame;

    public FramePanel() {
        width = 1030;
        height = 40;
        this.setLayout(new BorderLayout());
        initFrames();
        updateFrameBar();
        this.add(jScrollPane);
        playControlPanel = new PlayControlPanel();
        playControlPanel.setPreferredSize(new Dimension(250, 60));
        playControlPanel.setBackground(new Color(25, 25, 25));
        this.add(playControlPanel, BorderLayout.EAST);
    }

    private void initFrames(){
        frameBar = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g2d = frameBar.createGraphics();
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(0, 0, frameBar.getWidth(), frameBar.getHeight());
        numFrames = 30;
        size = 1030/numFrames;
        g2d.setColor(Color.LIGHT_GRAY);
        for(int i = 0; i < numFrames; ++i) {
            int x = i*size;
            Line2D line = new Line2D.Double(x, 20, x, 40);
            g2d.draw(line);
            g2d.setFont(UIManager.getFont("Label.font"));
            g2d.drawString("" + i, x, 15);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        drawSelected(e);
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

    private void drawSelected(MouseEvent e) {
        g2d.setColor(Color.DARK_GRAY);
        Line2D line = new Line2D.Double(frame*size, 0, frame*size, height);
        g2d.draw(line);
        g2d.setColor(Color.LIGHT_GRAY);
        line = new Line2D.Double(frame*size, 20, frame*size, 40);
        g2d.draw(line);
        // get approx frame number
        frame = (int) e.getPoint().getX()/size;
        line = new Line2D.Double(frame*size, 0, frame*size, height);
        g2d.draw(line);
        updateFrameBar();
    }

    private void updateFrameBar(){
        if(jScrollPane!=null)
            this.remove(jScrollPane);
        jScrollPane = new JScrollPane(new JLabel(new ImageIcon(frameBar)));
        jScrollPane.setBackground(Color.DARK_GRAY);
        jScrollPane.addMouseMotionListener( this );
        jScrollPane.addMouseListener( this );
        jScrollPane.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.add(jScrollPane);
        this.validate();
    }
}
