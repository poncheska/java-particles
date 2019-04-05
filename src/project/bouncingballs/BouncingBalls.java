package project.bouncingballs;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.Renderable;
import org.lwjgl.util.vector.Vector2f;

import java.util.ArrayList;
import java.util.List;

public class BouncingBalls {
    private List<Ball> ballList = new ArrayList<>();
    private int divNumber;
    private int maxNumber;
    private float averageRadius;

    public BouncingBalls(int number, int divNumber, int maxNumber, int averageRadius){
        this.divNumber = divNumber;
        this.maxNumber = maxNumber;
        this.averageRadius = (float)averageRadius;
        initParticleList(number);
    }

    public void initParticleList(int number){
        for(int i = 0; i<number;i++){
            ballList.add(Ball.generateParticle(averageRadius));
        }
    }

    public void render(){
        List<Ball> delList1 = new ArrayList<>();
        List<Ball> delList2 = new ArrayList<>();
        for(Ball ball : ballList){
            ball.render();
            ball.tick();


            if(ball.getX() <= ball.getRadius() || ball.getX() >= Display.getWidth() - ball.getRadius()){
                if(ball.getX() < ball.getRadius()){
                    ball.setPosition(new Vector2f(ball.getRadius(), ball.getY()));
                }else if(ball.getX() > Display.getWidth() - ball.getRadius()){
                    ball.setPosition(new Vector2f(Display.getWidth() - ball.getRadius(), ball.getY()));
                }
                ball.hit(0, ball.getY());
                ball.setVelocity(new Vector2f(-1f* ball.getVelocity().x, ball.getVelocity().y));
            }


            if(ball.getY() <= ball.getRadius() || ball.getY() >= Display.getHeight() - ball.getRadius()){
                if(ball.getY() <= ball.getRadius()){
                    ball.setPosition(new Vector2f(ball.getX(), ball.getRadius()));
                }else if(ball.getY() >= Display.getHeight() - ball.getRadius()){
                    ball.setPosition(new Vector2f(ball.getX(), Display.getHeight() - ball.getRadius()));
                }
                ball.hit(ball.getX(), 0);
                ball.setVelocity(new Vector2f(ball.getVelocity().x, -1f* ball.getVelocity().y));
            }


            for(Ball ball1 : ballList){
                float distance = MathUtils.distance(ball.getX(), ball.getY(), ball1.getX(), ball1.getY());
                if(ball != ball1 && distance <= ball.getRadius() + ball1.getRadius()){
                    ball.setPosition(MathUtils.changePosition(ball.getX(), ball.getY(), ball1.getX(),
                            ball1.getY(), ball.getRadius(), ball1.getRadius()));
                    ball.hit(ball1.getX(), ball1.getY());
                    ball.setVelocity(MathUtils.velocityCalc(ball.getX(), ball.getY(), ball1.getX(),
                            ball1.getY(), ball.getVelocity()));
                    ball1.hit(ball.getX(), ball.getY());
                    ball1.setVelocity(MathUtils.velocityCalc(ball1.getX(), ball1.getY(), ball.getX(),
                            ball.getY(), ball1.getVelocity()));
                    if(!delList1.contains(ball) && !delList2.contains(ball) &&
                            !delList1.contains(ball1) && !delList2.contains(ball1)){
                        if(ballList.size() > 100 && ball.getVelocityNum() + ball1.getVelocityNum() > 14f){
                            delList2.add(ball);
                            delList2.add(ball1);

                        }else if(ballList.size() < 200 && ball.getVelocityNum() > 10f){
                            delList1.add(ball);
                        }
                    }
                }
            }
        }


        for(Ball del:delList1){
            ballList.add(new Ball(del.getX()-5f, del.getY()+5f,(float)(del.getRadius()/Math.sqrt(2))));
            ballList.add(new Ball(del.getX()+5f, del.getY()-5f,(float)(del.getRadius()/Math.sqrt(2))));
            ballList.remove(del);
        }


        for(int i = 0;i < delList2.size(); i += 2){
            ballList.add(new Ball((delList2.get(i).getX()+delList2.get(i+1).getX())/2,
                    (delList2.get(i).getY()+delList2.get(i+1).getY())/2,
                    (float)Math.sqrt(delList2.get(i).getRadius()*delList2.get(i).getRadius() +
                            delList2.get(i+1).getRadius()*delList2.get(i+1).getRadius())));
            ballList.remove(delList2.get(i));
            ballList.remove(delList2.get(i+1));
        }
    }
}
