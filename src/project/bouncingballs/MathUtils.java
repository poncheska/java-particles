package project.bouncingballs;

import org.lwjgl.util.vector.Vector2f;

public class MathUtils {
    public static float distance(float x1, float y1, float x2, float y2){
        return (float) Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
    }

    public static Vector2f changePosition(float x1, float y1, float x2, float y2, float r1, float r2){
        float angle  = (float) Math.asin((y1-y2)/distance(x1,y1,x2,y2)) ;
        float x = x2 + (x1-x2)*(r1+r2+1f)/distance(x1,y1,x2,y2);
        float y = y2 + (y1-y2)*(r1+r2+1f)/distance(x1,y1,x2,y2);
        return new Vector2f(x,y);
    }

    public static Vector2f velocityCalc(float x1,float y1, float x2, float y2, Vector2f oldVelocity){
        float increment;
        if(Math.sqrt(oldVelocity.x*oldVelocity.x + oldVelocity.y*oldVelocity.y)< 12f){
            increment = 1.1f;
        }else{
            increment = (float) (0.5f + Math.random()*0.5f);
        }
        float projection = ((x1-x2)*oldVelocity.x + (y1 - y2)*oldVelocity.y)/distance(x1,y1,x2,y2);
        float x = (oldVelocity.x - 2 * projection *(x1 - x2)/distance(x1,y1,x2,y2))*increment;
        float y = (oldVelocity.y - 2 * projection *(y1 - y2)/distance(x1,y1,x2,y2))*increment;
        return new Vector2f(x,y);
    }
}
