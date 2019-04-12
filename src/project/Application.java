package project;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import project.bouncingballs.BBMenu;
import project.bouncingballs.BouncingBalls;
import project.particlesystem.Particle;
import project.particlesystem.typesofparticles.*;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Application {
    public static void main(String[] args){
        Menu menu = new Menu();
        BBMenu bbMenu = new BBMenu();
        menu.run();
        try {
            if(menu.getFullScreen()){
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                Display.setDisplayMode(new DisplayMode(screenSize.width, screenSize.height));
            }else {
                Display.setDisplayMode(new DisplayMode(menu.getWidth(), menu.getHeight()));
            }
            Display.create();
        }catch (LWJGLException e){
            e.printStackTrace();
            System.exit(0);
        }

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1 ,-1 );
        GL11.glClearColor(0, 0 ,0 ,1);


        switch (menu.getMode()){
            case 1:
                bbMenu.run();

                BouncingBalls bb = new BouncingBalls(bbMenu.getNumber(),bbMenu.getDivNumber(),
                        bbMenu.getMaxNumber(),bbMenu.getAverageRadius(),bbMenu.isTrace(),bbMenu.isHoldMouse());

                while(!Display.isCloseRequested()){
                    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
                    bb.render();


                    Display.update();
                    Display.sync(60);
                }
                break;
            case 2:

                Particle[] particles = {new ParticleType1(200,200), new ParticleType2(400,200), new ParticleType3(600,200)};

                while(!Display.isCloseRequested()){
                    GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
                    for(Particle particle:particles) {
                        particle.render();
                    }

                    Display.update();
                    Display.sync(60);
                }
                break;
        }
    }
}
