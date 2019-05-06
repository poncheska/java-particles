package project.particlesystem;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

import java.util.ArrayList;
import java.util.List;

public class ParticleSystem {

    private List<Particle> particleList = new ArrayList<>();
    private List<Link> linkList = new ArrayList<>();
    private PMouse mouse = new PMouse();
    private boolean mouseButton1 = false;
    private boolean mouseOn;
    private float linkRange;



    public ParticleSystem(int number, boolean mouseOn,float linkRange){
        this.mouseOn = mouseOn;
        this.linkRange = linkRange;
        initParticleList(number);
    }

    private void initParticleList(int number){
        for(int i = 0; i<number;i++){
            particleList.add(Particle.generateParticle(i));
        }
    }


    private float clickPower = 0;

    public void render() {
        if(mouseOn) {
            if (clickPower < 1f && Mouse.isButtonDown(0)) {
                clickPower += 0.005f;
            }

            mouse.render(clickPower);
            if (!Mouse.isButtonDown(0) && mouseButton1) {
                Vector2f mousePos = new Vector2f(Mouse.getX(), Display.getHeight() - Mouse.getY());
                for (Particle particle : particleList) {
                    if (Link.distance(mousePos, particle.getPosition()) < mouse.getRadius()) {
                        float xa = (particle.getPosition().x - mousePos.x) /
                                Link.distance(mousePos, particle.getPosition()) * clickPower * 20;
                        float ya = (particle.getPosition().y - mousePos.y) /
                                Link.distance(mousePos, particle.getPosition()) * clickPower * 20;
                        particle.applyAcceleration(new Vector2f(xa, ya));
                    }
                }
                clickPower = 0;
            }


            mouseButton1 = Mouse.isButtonDown(0);
        }

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
            float x=(pos2.getX()-pos1.getX())*(distance-linkRange*3/4)/(linkRange/2)/500;
            float y=(pos2.getY()-pos1.getY())*(distance-linkRange*3/4)/(linkRange/2)/500;
            return new Vector2f(x,y);
        }

        float x=-(pos2.getX()-pos1.getX())*(distance-linkRange/2)/(linkRange/2)/500;
        float y=-(pos2.getY()-pos1.getY())*(distance-linkRange/2)/(linkRange/2)/500;
        return new Vector2f(x,y);
    }
}
