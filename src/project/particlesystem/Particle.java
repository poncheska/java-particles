package project.particlesystem;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import project.bouncingballs.MathUtils;
import project.particlesystem.typesofparticles.ParticleType1;
import project.particlesystem.typesofparticles.ParticleType2;
import project.particlesystem.typesofparticles.ParticleType3;

import java.util.Random;

public abstract class Particle {
    private int[] maxLinks = new int[3];
    private int[] links = new int[3];
    private Vector2f position;
    private int animCount;
    private final int CIRCLE_PARTS = 1000;
    private Vector2f velocity;
    private float radius;

    public Particle(float x, float y){

        this.position = new Vector2f(x,y);

        Random r = new Random();
        animCount = r.nextInt(100);

        this.velocity = new Vector2f((float) (Math.random() * 2.0f - 1.0f)*2f, (float) (Math.random() * 2.0f - 1.0f)*2f);

    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public abstract void render();

    public abstract int getTYPE();

    public int[] getLinks() {return links; }

    public void increaseLinks(int type) {
        links[type - 1] ++;
    }

    public void reduceLinks(int type) {
        links[type - 1] --;
    }

    public void setLinks(int[] links) {this.links = links; }

    public Vector2f getPosition() {return position; }

    public void setMaxLinks(int[] maxLinks) {
        this.maxLinks = maxLinks;
    }

    public int[] getMaxLinks() {return maxLinks; }

    public void setPosition(Vector2f position) {this.position = position; }

    public abstract Vector3f getColor();

    public void tick(){
        position.x += velocity.x;
        position.y += velocity.y;
        if(position.x < radius || position.x > Display.getWidth() - radius){
            velocity.x = -velocity.x;
        }
        if(position.y < radius || position.y > Display.getHeight() - radius){
            velocity.y = -velocity.y;
        }
    }

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


    public static Particle generateParticle(){
        Random random =new Random();
        float x = random.nextInt((int)(Display.getWidth() - 2*7 -1)) + 7;
        float y = random.nextInt((int)(Display.getHeight() - 2*7 -1)) + 7;
        Particle particle = new ParticleType1(x,y);
        switch (random.nextInt(3)+1){
            case 1:
                particle = new ParticleType1(x,y);
                break;
            case 2:
                particle = new ParticleType2(x,y);
                break;
            case 3:
                particle = new ParticleType3(x,y);
                break;
        }
        return particle;
    }

    public static boolean linkable(Particle p1, Particle p2){
        return (p1.getLinks()[p2.getTYPE()-1]<p1.getMaxLinks()[p2.getTYPE()-1] &&
                p2.getLinks()[p1.getTYPE()-1]<p2.getMaxLinks()[p1.getTYPE()-1] &&
                MathUtils.distance(p1.getPosition().x,p1.getPosition().y,p2.getPosition().x,p2.getPosition().y)< 400);
    }

    public static void linking(Particle p1, Particle p2){
        p1.increaseLinks(p2.getTYPE());
        p2.increaseLinks(p1.getTYPE());
    }

    public static void separation(Particle p1, Particle p2){
        p1.reduceLinks(p2.getTYPE());
        p2.reduceLinks(p1.getTYPE());
    }
}
