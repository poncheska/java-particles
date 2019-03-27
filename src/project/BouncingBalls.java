package project;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

import java.util.ArrayList;
import java.util.List;

public class BouncingBalls {
    private List<Particle> particleList = new ArrayList<>();

    public BouncingBalls(int number){
        initParticleList(number);
    }

    public void initParticleList(int number){
        for(int i = 0; i<number;i++){
            particleList.add(Particle.generateParticle());
        }
    }

    public void render(){
        List<Integer> delList = new ArrayList<>();
        for(Particle particle:particleList){
            particle.render();
            particle.tick();


            if(particle.getX() <= particle.getRadius() || particle.getX() >= Display.getWidth() - particle.getRadius()){
                if(particle.getX() < particle.getRadius()){
                    particle.setPosition(new Vector2f(particle.getRadius(),particle.getY()));
                }else if(particle.getX() > Display.getWidth() - particle.getRadius()){
                    particle.setPosition(new Vector2f(Display.getWidth() - particle.getRadius(),particle.getY()));
                }
                particle.hit(particle.getX(), 0);
                particle.setVelocity(new Vector2f(-1f*particle.getVelocity().x, particle.getVelocity().y));
            }


            if(particle.getY() <= particle.getRadius() || particle.getY() >= Display.getHeight() -particle.getRadius()){
                if(particle.getY() <= particle.getRadius()){
                    particle.setPosition(new Vector2f(particle.getX(), particle.getRadius()));
                }else if(particle.getY() >= Display.getHeight() -particle.getRadius()){
                    particle.setPosition(new Vector2f(particle.getX(), Display.getHeight() -particle.getRadius()));
                }
                particle.hit(0, particle.getY());
                particle.setVelocity(new Vector2f(particle.getVelocity().x, -1f*particle.getVelocity().y));
            }


            for(Particle particle1:particleList){
                float distance = distance(particle.getX(),particle.getY(),particle1.getX(),particle1.getY());
                if(particle != particle1 && distance <= particle.getRadius() +particle1.getRadius()){
                    particle.setPosition(changePosition(particle.getX(),particle.getY(),particle1.getX(),
                            particle1.getY(),particle.getRadius(),particle1.getRadius()));
                    particle.hit(particle1.getX(), particle1.getY());
                    particle.setVelocity(velocityCalc(particle.getX(),particle.getY(),particle1.getX(),
                            particle1.getY(), particle.getVelocity()));
                    particle1.setVelocity(velocityCalc(particle1.getX(),particle1.getY(),particle.getX(),
                            particle.getY(), particle1.getVelocity()));
                }
            }

            if(particleList.size() < 300 && Math.sqrt(particle.getVelocity().x*particle.getVelocity().x +
                    particle.getVelocity().y*particle.getVelocity().y) > 10f){
                delList.add(particleList.indexOf(particle));
            }
        }


        for(Integer del:delList){
            particleList.add(new Particle(particleList.get(del).getX()-5f,particleList.get(del).getY()+5f,
                    particleList.get(del).getRadius()*0.6f));
            particleList.add(new Particle(particleList.get(del).getX()+5f,particleList.get(del).getY()-5f,
                    particleList.get(del).getRadius()*0.6f));
            particleList.remove((int)del);
        }
    }

    public static float distance(float x1, float y1, float x2, float y2){
        return (float) Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
    }

    public static Vector2f changePosition(float x1,float y1, float x2, float y2, float r1, float r2){
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
            increment = (float) Math.random();
        }
        float projection = ((x1-x2)*oldVelocity.x + (y1 - y2)*oldVelocity.y)/distance(x1,y1,x2,y2);
        float x = (oldVelocity.x - 2 * projection *(x1 - x2)/distance(x1,y1,x2,y2))*increment;
        float y = (oldVelocity.y - 2 * projection *(y1 - y2)/distance(x1,y1,x2,y2))*increment;
        return new Vector2f(x,y);

    }

}
