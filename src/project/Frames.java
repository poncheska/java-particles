package project;

import java.awt.Font;


import org.lwjgl.Sys;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;



public class Frames {
    int currentFps;
    int fps;
    long lastFPS;
    TrueTypeFont font;
    boolean antiAlias = false;

    public void setLastFPS() {
        this.lastFPS = getTime();
        GL11.glDepthMask(false);
    }

    public void initFont()
    {

        Font awtFont = new Font("Verdana", Font.BOLD, 20);
        font = new TrueTypeFont(awtFont, antiAlias);

    }

    public void updateFPS()
    {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        font.drawString(10, 10, "FPS: " + currentFps, Color.yellow);
        if (getTime() - lastFPS > 1000)
        {
            currentFps = fps;
            fps = 0; //reset the FPS counter
            lastFPS += 1000; //add one second
        }
        fps++;
        GL11.glDisable(GL11.GL_BLEND);
    }

    public long getTime()
    {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

}
