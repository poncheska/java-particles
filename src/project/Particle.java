package project;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.opengl.GL11;
import java.util.Random;

public class Particle {
    private Vector2f velocity;
    private float radius;
    private Vector2f position;
    private boolean isHited = false;
    private float dcos;
    private float dsin;
    private int deformationCount;
    public Particle(float x, float y, float radius){
        position = new Vector2f(x,y);
        this.velocity = new Vector2f((float) (Math.random() * 2.0f - 1.0f)*2f, (float) (Math.random() * 2.0f - 1.0f)*2f);
        this.radius = radius;
        deformationCount = 0;
    }

    public void render(){
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glBegin(GL11.GL_POLYGON);
        if(this.isHited){
            deformation();
        }else {
        int parts = 50;
        for(int i = 0; i < parts;i++){
            float angle =(float) (2f * Math.PI * i /parts) ;
                GL11.glVertex2f((float) (position.getX() + radius * Math.cos(angle)),
                        (float) (position.getY() + radius * Math.sin(angle)));
            }
        }
        GL11.glEnd();
    }

    public void hit(float x, float y){
        this.isHited = true;
        float dx = x - position.getX();
        float dy = y - position.getY();
        float distance = (float)Math.sqrt(dx*dx + dy*dy);
        dcos = (float) (dx/distance);
        dsin = (float) (dy/distance);
    }

    private void deformation(){
        float d;
        deformationCount++;
        if(deformationCount<10){
            d = deformationCount*radius/20;
        }else {
            d = (20 - deformationCount)*radius/20;
        }
        int parts = 50;
        for(int i = 0; i < parts;i++){
            float angle =(float) (2f * Math.PI * i /parts) ;
            GL11.glVertex2f((float) (position.getX() + (dcos*(radius - d) * Math.cos(angle) - dsin*(radius + d) * Math.sin(angle))),
                    (float) (position.getY() + (dsin*(radius - d) * Math.cos(angle) + dcos*(radius + d) * Math.sin(angle))));
        }
        if(deformationCount == 20) {
            isHited = false;
            deformationCount = 0;
        }
    }


    public static Particle generateParticle(){
        Random random =new Random();
        float radius = (float) (Math.random() * 5.0f) + 5.0f;
        float x = random.nextInt((int)(Display.getWidth() - 2*radius -1)) + radius;
        float y = random.nextInt((int)(Display.getHeight() - 2*radius -1)) + radius;
        return new Particle(x, y, radius);
    }

    public void tick(){
        position.x += velocity.x;
        position.y += velocity.y;
    }

    public void setVelocity(Vector2f velocity) {this.velocity = velocity;}

    public Vector2f getVelocity(){return velocity; }

    public float getRadius(){return radius; }

    public void setPosition(Vector2f position){this.position = position; }

    public float getX() {return position.getX(); }

    public float getY() {return position.getY(); }
}
