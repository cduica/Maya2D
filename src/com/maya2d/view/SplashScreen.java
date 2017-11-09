package com.maya2d.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SplashScreen extends JWindow {
    private int duration;

    public SplashScreen(int d) {
        duration = d;
    }

    public void showSplash() {
        JPanel content = (JPanel) getContentPane();
        content.setBackground(new Color(44, 44, 44));

        int width = 350;
        int height = 150;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        setBounds(x, y, width, height);
        BufferedImage logo = null;
        try {
            logo = ImageIO.read(getClass().getResourceAsStream("/maya2dlogo.png"));
        } catch (Exception e){
            e.printStackTrace();
        }

        JLabel logoLabel = new JLabel(new ImageIcon(logo));
        content.add(logoLabel, BorderLayout.CENTER);
        JLabel copyrt = new JLabel("",
                JLabel.CENTER);
        content.add(copyrt, BorderLayout.SOUTH);

        setVisible(true);

        try {
            Thread.sleep(duration);
        } catch (Exception e) {
        }

        setVisible(false);
    }

}