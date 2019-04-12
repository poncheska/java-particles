package project.particlesystem.typesofparticles;

import org.lwjgl.util.vector.Vector2f;
import project.particlesystem.Particle;

public class ParticleType1  extends Particle {
    private final int TYPE = 1;
    private final float R = 1.0f;
    private final float G = 0.0f;
    private final float B = 0.0f;
    private float[] animArgs = {1f,16f,10f,2f,32f,10f};
    private float radius = 50;


    public ParticleType1(float x, float y){
        super(x,y);
    }

    public void render(){
        super.abstractRender(radius,R,G,B,animArgs);
    }

    public int getTYPE() {
        return TYPE;
    }
}
