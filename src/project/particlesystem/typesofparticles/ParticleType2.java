package project.particlesystem.typesofparticles;

import project.particlesystem.Particle;

public class ParticleType2 extends Particle {
    private final int TYPE = 2;
    private final float R = 0.0f;
    private final float G = 1.0f;
    private final float B = 0.0f;
    private float[] animArgs = {1f,18f,12f,4f,36f,12f};
    private float radius = 50;


    public ParticleType2(float x, float y){
        super(x,y);
    }

    public void render(){
        super.abstractRender(radius,R,G,B,animArgs);
    }

    public int getTYPE() {
        return TYPE;
    }
}
