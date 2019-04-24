package project.particlesystem.typesofparticles;

import org.lwjgl.util.vector.Vector3f;
import project.particlesystem.Particle;

public class ParticleType2 extends Particle {
    private final int TYPE = 2;
    private int[] maxLinks = {1,0,2};
    private int[] links = {0,0,0};
    private final Vector3f RGB = new Vector3f(0.0f,1.0f,0.0f);
    private float[] animArgs = {1f,18f,12f,4f,36f,12f};
    private float radius = 7;


    public ParticleType2(float x, float y){
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
