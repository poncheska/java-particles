package project;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Application {
    public static void main(String[] args){
        Menu menu = new Menu();
        menu.run();
        try {
            Display.setDisplayMode(new DisplayMode(menu.getWidth(), menu.getHeight()));
            Display.create();
        }catch (LWJGLException e){
            e.printStackTrace();
            System.exit(0);
        }

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, menu.getWidth(), menu.getHeight(), 0, 1 ,-1 );
        GL11.glClearColor(0, 0 ,0 ,1);

        BouncingBalls p = new BouncingBalls(100);

        while(!Display.isCloseRequested()){
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
            p.render();


            Display.update();
            Display.sync(60);
        }
    }
}
