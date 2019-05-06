package project.particlesystem;

import javax.swing.*;
import java.awt.*;

public class PMenu  implements Runnable{
    private int number = 100;
    private int linkLength = 100;
    private boolean mouse = false;

    public void run() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(3, 2));

        JLabel numberLabel = new JLabel("Number of particles("+ this.number +"):");
        JSlider number = new JSlider(SwingConstants.HORIZONTAL,20, 200, 100);
        number.addChangeListener(e -> {
            this.number = number.getValue();
            numberLabel.setText("Number of particles("+ this.number +"):");
        });


        JLabel linkLengthLabel = new JLabel("Max link length(100):");
        JSlider linkLength = new JSlider(SwingConstants.HORIZONTAL,50, 200, 100);
        linkLength.addChangeListener(e ->  {
            this.linkLength = linkLength.getValue();
            linkLengthLabel.setText("Minimum number to combine("+ this.linkLength +"):");
        });


        JCheckBox mouse = new JCheckBox("");
        mouse.addChangeListener(e -> {
            this.mouse = mouse.isSelected();
        });

        jPanel.add(numberLabel);
        jPanel.add(number);
        jPanel.add(linkLengthLabel);
        jPanel.add(linkLength);
        jPanel.add(new Label("Mouse:"));
        jPanel.add(mouse);
        if (JOptionPane.showConfirmDialog(null, jPanel, "Bouncing Balls mode settings", JOptionPane.OK_CANCEL_OPTION) != JOptionPane.OK_OPTION) {
            return;
        }
    }

    public int getLinkLength() {return linkLength; }

    public int getNumber() {return number; }

    public boolean isMouse() {return mouse; }
}