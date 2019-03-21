package project;

import javax.swing.*;
import java.awt.*;

public class Menu implements Runnable{
    public void run(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(3, 2));
        JSpinner width = new JSpinner();
        width.setValue(1280);
        JSpinner height = new JSpinner();
        height.setValue(720);
        JCheckBox fullScreen = new JCheckBox("");
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

}
