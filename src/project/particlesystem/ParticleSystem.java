package project.particlesystem;

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
            particleList.add(Particle.generateParticle());
        }
    }


    private int counter = 200;
    public void render() {
        List<Link> delLinkList = new ArrayList<>();


        for (Link link : linkList) {
            link.render();
            if(link.getLength()>counter && !link.isEnding()){
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
                        Link.distance(particle1.getPosition(),particle.getPosition())<counter) {
                    linkList.add(new Link(particle, particle1));
                }
            }
        }

    }
}
