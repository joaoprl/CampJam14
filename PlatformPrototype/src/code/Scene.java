package code;

import java.awt.Graphics2D;

/*
 * @author Peronio
 */
public class Scene {
    Animation[] animations;
    int currentAnimation;
    boolean lastFrame;
    
    public Scene(Animation... animations){
        this.animations = animations;
        currentAnimation = 0;
        lastFrame = false;
    }
    
    public void update(long delta){
        //if (animations == null) return;
        animations[currentAnimation].updateFrame(delta);
        if(animations[currentAnimation].isOver() && currentAnimation < animations.length-1)
            currentAnimation++;
        else if (animations[currentAnimation].isOver() && currentAnimation == animations.length-1)
            lastFrame = true;
    }
    
    public void draw(Graphics2D g){
        //if (animations == null) return;
        animations[currentAnimation].drawFrame(g);
    }
    
    public boolean isOver(){
        return lastFrame;
    }
}
