package project.particlesystem.typesofparticles;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import project.particlesystem.Particle;

public class ParticleType1  extends Particle {
    private final int TYPE = 1;
    private int[] maxLinks = {2,1,1};
    private int[] links = {0,0,0};
    private final Vector3f RGB = new Vector3f(1.0f,0.0f,0.0f);
    private float[] animArgs = {1f,16f,10f,2f,32f,10f};
    private float radius = 5;


    public ParticleType1(float x, float y, int index){
        super(x,y,index);
        setMaxLinks(maxLinks);
        setRadius(radius);
    }

    public void render(){ abstractRender(radius,RGB.x,RGB.y,RGB.z,animArgs);}

    public int getTYPE() {
        return TYPE;
    }

    public Vector3f getColor(){return RGB; }
}
