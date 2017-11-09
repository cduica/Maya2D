package com.maya2d.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContentFrame extends JFrame {

    private ContentPanel contentPanel;
    private FramePanel framePanel;
    private AttributesPanel attributesPanel;
    private EditingPanel editingPanel;

    public ContentFrame(int width, int height){
        this.setTitle("Maya2D");
        this.setSize(width, height);
        this.getContentPane().setBackground(new Color(65, 65, 65));
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - width) / 2;
        int y = (screen.height - height) / 2;
        setBounds(x, y, width, height);
        addMenu();
        contentPanel = new ContentPanel();
        contentPanel.setPreferredSize(new Dimension(400, 400));
        this.getContentPane().add(contentPanel, BorderLayout.CENTER);
        framePanel = new FramePanel();
        framePanel.setPreferredSize(new Dimension(1280, 60));
        framePanel.setBackground(new Color(35, 35, 35));
        this.getContentPane().add(framePanel, BorderLayout.SOUTH);
        attributesPanel = new AttributesPanel();
        attributesPanel.setPreferredSize(new Dimension(250, 800));
        attributesPanel.setBackground(new Color(45, 45, 45));
        this.getContentPane().add(attributesPanel, BorderLayout.EAST);
        editingPanel = new EditingPanel();
        editingPanel.setBackground(new Color(45, 45, 45));
        editingPanel.setPreferredSize(new Dimension(1280, 40));
        this.getContentPane().add(editingPanel, BorderLayout.NORTH);
    }

    private void addMenu(){
        JMenu fileMenu = new JMenu("File");

        JMenuItem newItem = new JMenuItem("New project         ");
        newItem.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        fileMenu.add(newItem);

        JMenuItem openItem = new JMenuItem("Open                ");
        openItem.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        fileMenu.add(openItem);

        JMenuItem saveItem = new JMenuItem("Save                ");
        saveItem.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        fileMenu.add(saveItem);

        JMenuItem exitItem = new JMenuItem("Exit                ");

        exitItem.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });

        fileMenu.add(exitItem);

        JMenu editMenu = new JMenu("Edit");

        JMenuItem undoItem = new JMenuItem("Undo                ");

        undoItem.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });

        editMenu.add(undoItem);

        JMenuItem redoItem = new JMenuItem("Redo                ");

        redoItem.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });

        editMenu.add(redoItem);

        JMenu keyMenu = new JMenu("Key");

        JMenuItem keyframeItem = new JMenuItem("Key current frame   ");

        keyframeItem.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });

        keyMenu.add(keyframeItem);


        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(keyMenu);
        this.setJMenuBar(menuBar);
    }

}