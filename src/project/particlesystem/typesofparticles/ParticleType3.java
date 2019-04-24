package project.particlesystem.typesofparticles;

import org.lwjgl.util.vector.Vector3f;
import project.particlesystem.Particle;

public class ParticleType3 extends Particle {
    private final int TYPE = 3;
    private int[] maxLinks = {1,3,0};
    private int[] links = {0,0,0};
    private final Vector3f RGB = new Vector3f(0.0f,0.0f,1.0f);
    private float[] animArgs = {1f,12f,6f,5f,24f,10f};
    private float radius = 5;


    public ParticleType3(float x, float y){
        super(x,y);
        setMaxLinks(maxLinks);
        setRadius(radius);
    }

    public void render(){ abstractRender(radius,RGB.x,RGB.y,RGB.z,animArgs); }

    public int getTYPE() {
        return TYPE;
    }

    public Vector3f getColor(){return RGB; }
}
