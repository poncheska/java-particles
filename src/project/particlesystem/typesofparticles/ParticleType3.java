package project.particlesystem.typesofparticles;

import project.particlesystem.Particle;

public class ParticleType3 extends Particle {
    private final int TYPE = 3;
    private final float R = 0.0f;
    private final float G = 0.0f;
    private final float B = 1.0f;
    private float[] animArgs = {1f,12f,8f,3f,24f,8f};
    private float radius = 50;


    public ParticleType3(float x, float y){
        super(x,y);
    }

    public void render(){
        super.abstractRender(radius,R,G,B,animArgs);
    }

    public int getTYPE() {
        return TYPE;
    }
}
