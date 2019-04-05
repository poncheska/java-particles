package project.bouncingballs;

import javax.swing.*;
import java.awt.*;

public class BBMenu implements Runnable{
    private int number = 80;
    private int divNumber = 100;
    private int maxNumber = 400;
    private int averageRadius = 20;

    public void run() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(4, 2));

        JLabel numberLabel = new JLabel("Number of balls(80):");
        JSlider number = new JSlider(SwingConstants.HORIZONTAL,20, 1000, 80);
        number.addChangeListener(e -> {
            this.number = number.getValue();
            if(this.number > this.maxNumber){
                number.setValue(this.maxNumber);
            }
            numberLabel.setText("Number of balls("+ this.number +"):");
        });

        JLabel divNumberLabel = new JLabel("Minimum number to combine(100):");
        JSlider divNumber = new JSlider(SwingConstants.HORIZONTAL,10, 500, 100);
        divNumber.addChangeListener(e -> {
            this.divNumber = divNumber.getValue();
            if(this.divNumber > this.maxNumber){
                divNumber.setValue(this.maxNumber);
            }
            divNumberLabel.setText("Minimum number to combine("+ this.divNumber +"):");
        });

        JLabel maxNumberLabel = new JLabel("Maximum number of balls(400):");
        JSlider maxNumber = new JSlider(SwingConstants.HORIZONTAL,200, 1000, 400);
        maxNumber.addChangeListener(e -> {
            this.maxNumber = maxNumber.getValue();
            maxNumberLabel.setText("Maximum number of balls("+ this.maxNumber +"):");
            number.setValue(Math.min(this.maxNumber, this.number));
            divNumber.setValue(Math.min(this.maxNumber, this.divNumber));
        });

        JLabel averageRadiusLabel = new JLabel("Average radius(20):");
        JSlider averageRadius = new JSlider(SwingConstants.HORIZONTAL,4, 50, 20);
        averageRadius.addChangeListener(e -> {
            this.averageRadius = averageRadius.getValue();
            averageRadiusLabel.setText("Average radius("+ this.averageRadius +"):");
        });
        jPanel.add(numberLabel);
        jPanel.add(number);
        jPanel.add(maxNumberLabel);
        jPanel.add(maxNumber);
        jPanel.add(divNumberLabel);
        jPanel.add(divNumber);
        jPanel.add(averageRadiusLabel);
        jPanel.add(averageRadius);
        if (JOptionPane.showConfirmDialog(null, jPanel, "Bouncing Balls mode settings", JOptionPane.OK_CANCEL_OPTION) != JOptionPane.OK_OPTION) {
            return;
        }
    }

    public int getAverageRadius() {return averageRadius; }

    public int getDivNumber() {return divNumber; }

    public int getMaxNumber() {return maxNumber; }

    public int getNumber() {return number; }
}
