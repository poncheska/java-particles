package project;

import javax.swing.*;
import java.awt.*;

public class Menu implements Runnable{
    private int width = 1280;
    private int height = 720;
    private boolean fullScreen;
    public void run(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(3, 2));


        JSpinner width = new JSpinner();
        width.setValue(this.width);
        width.addChangeListener(e -> {
            this.width = (int) width.getValue();
        });


        JSpinner height = new JSpinner();
        height.setValue(this.height);
        height.addChangeListener(e -> {
            this.height = (int) height.getValue();
        });



        JCheckBox fullScreen = new JCheckBox("");
        fullScreen.addChangeListener(e -> {
            this.fullScreen = fullScreen.isSelected();
        });

        jPanel.add(new JLabel("Width:"));
        jPanel.add(width);
        jPanel.add(new JLabel("Height:"));
        jPanel.add(height);
        jPanel.add(new JLabel("Fullscreen:"));
        jPanel.add(fullScreen);

        if (JOptionPane.showConfirmDialog(null, jPanel, "ParticleSystem settings", JOptionPane.OK_CANCEL_OPTION) != JOptionPane.OK_OPTION) {
            return;
        }
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

}
