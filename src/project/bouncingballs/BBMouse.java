package project.bouncingballs;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class BBMouse {
    private final int CIRCLE_PARTS = 100;
    private float radius = 100;
    private float time = 0;
    public void render(){
        time += Math.PI / 60;
        time %= 2*Math.PI;
        int radiusDelta = Mouse.getDWheel();
        float deltaPart = 0.1f;
        if(radius<=500 && radiusDelta<0){
            radius = Math.max(50, radius + radiusDelta*deltaPart);
        }else if(radius>=50 && radiusDelta>0){
            radius = Math.min(500, radius + radiusDelta*deltaPart);
        }
        GL11.glColor4f( 0.4f + 0.2f*(float)Math.cos(time), 0.4f + 0.2f*(float)Math.cos(time),
                0.4f + 0.2f*(float)Math.cos(time), 1.0f);
        GL11.glBegin(GL11.GL_LINES);
        for(int i = 0; i < CIRCLE_PARTS;i++){
            float angle =(float) (2f * Math.PI * i /CIRCLE_PARTS) ;
            GL11.glVertex2f((float) (Mouse.getX() + radius * Math.cos(angle)),
                    (float) (Display.getHeight() - Mouse.getY() + radius * Math.sin(angle)));
        }
        GL11.glEnd();
    }

    public float getRadius() {return radius; }
}
