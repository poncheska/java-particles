package project;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

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

        while(!Display.isCloseRequested()){

            Display.update();
        }
    }
}
