package project.particlesystem;

import org.lwjgl.util.vector.Vector2f;

import java.util.ArrayList;
import java.util.List;

public class ParticleSystem {

    List<Particle> particleList = new ArrayList<>();
    List<Link> linkList = new ArrayList<>();


    public ParticleSystem(int number){
        initParticleList(number);
    }

    private void initParticleList(int number){
        for(int i = 0; i<number;i++){
            particleList.add(Particle.generateParticle(i));
        }
    }


    private float linkRange = 100;
    public void render() {
        List<Link> delLinkList = new ArrayList<>();


        for (Link link : linkList) {
            link.render();
            if(link.getLength()> linkRange && !link.isEnding()){
                link.setEnding(true);
            }
            if(link.isDelete()) {
                delLinkList.add(link);
            }
        }

        for (Link link:delLinkList) {
                link.delete();
                linkList.remove(link);
        }

        for (Particle particle : particleList) {
            particle.tick();
        }
        for (Particle particle : particleList) {
            particle.render();
            for (Particle particle1 : particleList) {
                if (Particle.linkable(particle, particle1) && particle != particle1 &&
                        Link.distance(particle1.getPosition(),particle.getPosition())< linkRange &&
                        !particle.isLinkedWith(particle1.getIndex())) {
                    linkList.add(new Link(particle, particle1));

                }
                if(Link.distance(particle1.getPosition(),particle.getPosition())< linkRange){
                    particle.applyAcceleration(calculateAcceleration(particle.getPosition(),
                            particle1.getPosition(),particle.isLinkedWith(particle1.getIndex())));
                }
            }
        }

    }

    public Vector2f calculateAcceleration(Vector2f pos1, Vector2f pos2, boolean linked){
        float distance = Link.distance(pos1,pos2);

        if(linked){
            float x=(pos2.getX()-pos1.getX())*(distance-linkRange/2)/(linkRange/2)/500;
            float y=(pos2.getY()-pos1.getY())*(distance-linkRange/2)/(linkRange/2)/500;
            return new Vector2f(x,y);
        }

        float x=-(pos2.getX()-pos1.getX())*(distance-linkRange/2)/(linkRange/2)/500;
        float y=-(pos2.getY()-pos1.getY())*(distance-linkRange/2)/(linkRange/2)/500;
        return new Vector2f(x,y);
    }
}
