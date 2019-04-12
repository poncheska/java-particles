package project.particlesystem;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import java.util.Random;

public abstract class Particle {
    private final float R = 1.0f;
    private final float G = 1.0f;
    private final float B = 1.0f;
    private Vector2f position;
    private int animCount = 0;
    private float[] animArgs = {1f,16f,10f,2f,32f,10f};
    private float radius = 50;
    private final int CIRCLE_PARTS = 1000;

    public Particle(float x, float y){

        this.position = new Vector2f(x,y);

        Random r = new Random();
        animCount = r.nextInt(100);

    }

    public abstract void render();

    public abstract int getTYPE();

    public void abstractRender(float radius,float R,float G,float B, float[] animArgs){
        GL11.glColor4f(R, G, B, 1.0f);

        GL11.glBegin(GL11.GL_POLYGON);

        GL11.glVertex2f(position.getX() ,position.getY());

        for(int i = 0; i < CIRCLE_PARTS+1;i++){
            float angle =(float) (2f * Math.PI * i /CIRCLE_PARTS) ;
            float delta = deltaCalc(i,animArgs[0],animArgs[1],animArgs[2],animArgs[3],animArgs[4],animArgs[5]);
            GL11.glVertex2f((float) (position.getX() + radius *delta *Math.cos(angle)),
                    (float) (position.getY() + radius*delta* Math.sin(angle)));
        }
        GL11.glEnd();
        animCount += 1;
        animCount %= CIRCLE_PARTS;
    }

    private float deltaCalc(int i , float a1, float b1, float c1, float a2, float b2, float c2){
        return (float)(Math.sin((i + animCount*a1)*b1*Math.PI/CIRCLE_PARTS) + c1)/c1 *
                (float)(Math.sin((i + animCount*a2)*b2*Math.PI/CIRCLE_PARTS) + c2)/c2;
    }
}
