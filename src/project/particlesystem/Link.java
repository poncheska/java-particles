package project.particlesystem;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;

public class Link {
    private final float SHIFT_VALUE = 2;
    private final float ANIM_STEP = 0.04f;
    private final float PART_SIZE = 1.5f;
    private Particle particle1;
    private Particle particle2;
    private Vector3f color1;
    private Vector3f color2;
    private boolean ending = false;
    private boolean delete = false;
    // x = percent of distance, y = shift
    private ArrayList<Vector2f> part1 = new ArrayList<>();
    private ArrayList<Vector2f> part2 = new ArrayList<>();

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public void setEnding(boolean ending) {
        this.ending = ending;
    }

    public boolean isEnding() {
        return ending;
    }

    public boolean isDelete() {
        return delete;
    }

    public Link(Particle particle1, Particle particle2){
        this.particle1 = particle1;
        this.particle2 = particle2;
        color1 = particle1.getColor();
        color2 = particle2.getColor();
        Particle.linking(particle1, particle2);
    }

    public void render(){
        ArrayList<Vector2f> delList1 = new ArrayList<>();
        ArrayList<Vector2f> delList2 = new ArrayList<>();

        Vector2f pos1 = particle1.getPosition();
        Vector2f pos2 = particle2.getPosition();

        Vector2f line = new Vector2f(pos1.getX() - pos2.getX(), pos1.getY() - pos2.getY());
        Vector2f nVector = new Vector2f(-line.getY()/distance(new Vector2f(0,0),line),
                line.getX()/distance(new Vector2f(0,0),line));


        if(!ending) {
            part1.add(new Vector2f(0f, (float) Math.cos(Math.random() * Math.PI)));
            part2.add(new Vector2f(0f, (float) Math.cos(Math.random() * Math.PI)));
        }
        if(part1.isEmpty() && part2.isEmpty()){
            delete = true;
        }

        for(Vector2f part:part1){
            GL11.glColor4f((1f - part.getX())*color1.x + part.getX()*color2.x,
                    (1f - part.getX())*color1.y + part.getX()*color2.y,
                    (1f - part.getX())*color1.z + part.getX()*color2.z, 1.0f);
            GL11.glPointSize(PART_SIZE);
            GL11.glBegin(GL11.GL_POINTS);
            GL11.glVertex2f(pos1.getX() - line.getX()*part.getX() + SHIFT_VALUE*nVector.getX()*part.getY(),
                    pos1.getY() - line.getY()*part.getX() + SHIFT_VALUE*nVector.getY()*part.getY());
            GL11.glEnd();

            if(part.getX() > 1f){
                delList1.add(part);
            }else{
                part.setX(part.getX() + ANIM_STEP);
            }
        }

        for(Vector2f del:delList1){
            part1.remove(del);
        }

        for(Vector2f part:part2){
            GL11.glColor4f((1f - part.getX())*color2.x + part.getX()*color1.x,
                    (1f - part.getX())*color2.y + part.getX()*color1.y,
                    (1f - part.getX())*color2.z + part.getX()*color1.z, 1.0f);
            GL11.glPointSize(PART_SIZE);
            GL11.glBegin(GL11.GL_POINTS);
            GL11.glVertex2f(pos2.getX() + line.getX()*part.getX() + SHIFT_VALUE*nVector.getX()*part.getY(),
                    pos2.getY() + line.getY()*part.getX() + SHIFT_VALUE*nVector.getY()*part.getY());
            GL11.glEnd();

            if(part.getX() > 1f){
                delList2.add(part);
            }else{
                part.setX(part.getX() + ANIM_STEP);
            }
        }

        for(Vector2f del:delList2){
            part2.remove(del);
        }

    }

    public float getLength(){
        return distance(particle1.getPosition(),particle2.getPosition());
    }

    public void delete(){
        Particle.separation(particle1,particle2);
    }

    public static float distance(Vector2f pos1, Vector2f pos2){
        return (float)Math.sqrt((pos1.getX() - pos2.getX())*(pos1.getX() - pos2.getX()) +
                (pos1.getY() - pos2.getY())*(pos1.getY() - pos2.getY()));
    }

}
