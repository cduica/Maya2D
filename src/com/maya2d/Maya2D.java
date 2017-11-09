package com.maya2d;

import com.maya2d.view.ContentFrame;
import com.maya2d.view.SplashScreen;

import javax.swing.*;

public class Maya2D {

    public static void main(String[] args) throws Exception {

        showSplash();
        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        createAndShowGUI();
                    }
                }
        );

    }

    public static void showSplash(){
        SplashScreen splash = new SplashScreen(1000);
        splash.showSplash();
    }

    public static void createAndShowGUI(){
        JFrame frame = new ContentFrame(1280, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
