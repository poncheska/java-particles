package project.bouncingballs;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;

import java.util.ArrayList;

public class ClickAnimation {
    private float minRadius;
    private float maxRadius;
    private final int CIRCLE_PARTS = 200;
    private final float ANIMATION_LENGTH = 20;
    private float parts = ANIMATION_LENGTH;
    private float delta;
    private float x;
    private float y;
    private ArrayList<Vector2f> randTrace = new ArrayList<>();
    public ClickAnimation(float x,float y, float r1, float r2){
        this.x = x;
        this.y =y;
        this.maxRadius =r2;
        this.minRadius =r1;
        this.delta = (r1 - r2)/ANIMATION_LENGTH;
    }

    public void render(){
        float radius = maxRadius + delta *parts;
        float partSize = radius/40;
        GL11.glColor4f((float)(parts/ANIMATION_LENGTH), (float)(parts/ANIMATION_LENGTH),
                (float)(parts/ANIMATION_LENGTH), 1.0f);
        GL11.glPointSize(partSize);
        GL11.glBegin(GL11.GL_POINTS);
        for(int i = 0; i < CIRCLE_PARTS;i++){
            if(Math.random()<0.51d){
                float r = radius * 0.9f + (float) (radius * 0.2f *Math.random());
                float angle =(float) (2f * Math.PI * i /CIRCLE_PARTS) ;
                GL11.glVertex2f((float) (x + r * Math.cos(angle)),
                        (float) (y + r * Math.sin(angle)));
                }
        }
        GL11.glEnd();
        parts --;
    }

    public float getParts() {return parts; }
}
