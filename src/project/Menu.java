package project;

import javax.swing.*;
import java.awt.*;

public class Menu implements Runnable{
    private int width = 1280;
    private int height = 720;
    private boolean fullScreen = false;
    private int mode = 2;
    public void run(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout( 4, 2));


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

        JLabel modeLabel = new JLabel("Bouncing Balls");

        JSlider mode = new JSlider(SwingConstants.HORIZONTAL,1, 2, 2);
        mode.addChangeListener(e -> {
            switch(mode.getValue()){
                case(1):
                    modeLabel.setText("Bouncing Balls");
                    this.mode = 1;
                    break;
                case(2):
                    modeLabel.setText("Particle System");
                    this.mode = 2;
                    break;
            }
        });


        jPanel.add(new JLabel("Width:"));
        jPanel.add(width);
        jPanel.add(new JLabel("Height:"));
        jPanel.add(height);
        jPanel.add(new JLabel("Fullscreen:"));
        jPanel.add(fullScreen);
        jPanel.add(modeLabel);
        jPanel.add(mode);

        if (JOptionPane.showConfirmDialog(null, jPanel, "ParticleSystem settings", JOptionPane.OK_CANCEL_OPTION) != JOptionPane.OK_OPTION) {
            return;
        }
    }
    public boolean getFullScreen(){ return fullScreen; }

    public int getMode() { return mode; }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

}
