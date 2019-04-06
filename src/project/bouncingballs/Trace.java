package project.bouncingballs;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;

import java.util.ArrayList;
import java.util.Random;

public class Trace {
    private final int TRACE_LENGTH = 10;
    private float radius;
    private float partSize;
    private ArrayList<Vector2f> randTrace = new ArrayList<>();
    private ArrayList<Vector2f> rightTrace = new ArrayList<>();
    private ArrayList<Vector2f> leftTrace = new ArrayList<>();

    public Trace(float radius){
        this.radius = radius;
        this.partSize = radius/20;
    }

    public void render(Vector2f velocity, Vector2f position){

        addTracePart(velocity, position);
        for(Vector2f tr:leftTrace){
            float color = (float)leftTrace.indexOf(tr)/(float)TRACE_LENGTH;
            GL11.glColor4f(color, color, color, 1.0f);
            GL11.glPointSize(partSize);
            GL11.glBegin(GL11.GL_POINTS);
            GL11.glVertex2f(tr.getX(), tr.getY());
            GL11.glEnd();
        }
        for(Vector2f tr:rightTrace){
            float color = (float)rightTrace.indexOf(tr)/(float)TRACE_LENGTH;
            GL11.glColor4f(color, color, color, 1.0f);
            GL11.glPointSize(partSize);
            GL11.glBegin(GL11.GL_POINTS);
            GL11.glVertex2f(tr.getX(), tr.getY());
            GL11.glEnd();
        }
        for(Vector2f tr:randTrace){
            float color = (float)randTrace.indexOf(tr)/(float)(TRACE_LENGTH*10);
            GL11.glColor4f(color, color, color, 1.0f);
            GL11.glPointSize(partSize);
            GL11.glBegin(GL11.GL_POINTS);
            GL11.glVertex2f(tr.getX(), tr.getY());
            GL11.glEnd();
        }


        if(leftTrace.size()>TRACE_LENGTH){
            leftTrace.remove(0);
        }
        if(rightTrace.size()>TRACE_LENGTH){
            rightTrace.remove(0);
        }
        while(randTrace.size()>TRACE_LENGTH*10){
            randTrace.remove(0);
        }
    }

    public void addTracePart(Vector2f velocity, Vector2f position){
        float lengthN = (float) Math.sqrt(velocity.getY()*velocity.getY() + velocity.getX()*velocity.getX());
        Vector2f vectorN = new Vector2f(-velocity.getY()/lengthN,velocity.getX()/lengthN);
        float lengthN1 = radius - partSize/2;
        leftTrace.add(new Vector2f(position.getX() + vectorN.getX()*lengthN1,
                position.getY() + vectorN.getY()*lengthN1));
        rightTrace.add(new Vector2f(position.getX() - vectorN.getX()*lengthN1,
                position.getY() - vectorN.getY()*lengthN1));
        for(int i = 0; i < (int) (Math.random()*20); i++){
            randTrace.add(new Vector2f(position.getX() + vectorN.getX()*(float)(lengthN1 - 2*lengthN1*Math.random())
                    -(float)(velocity.getX()/lengthN *radius * Math.random()),
                    position.getY() + vectorN.getY()*(float)(lengthN1 - 2*lengthN1*Math.random()
                            -(float)(velocity.getY()/lengthN *radius * Math.random()))));
        }
    }
}
