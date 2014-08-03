package resources;

/**
 *
 * @author Peronio
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Sprite {
    
    // SpriteSheet attributes
    private final BufferedImage spriteSheet;
    public final int SPRITE_WIDTH;
    public final int SPRITE_HEIGHT;
    
    // Frame control
    public final static int ANIMATION_TICK_LIMIT = 20;
    private int currentFrame;
    private int tick;
    private boolean isAnimate;
    
    // Custom animations setting
    private HashMap<String, int[]> customAnimations;
    private int customAnimationFrame;
    private boolean playingCustomAnimation;
    private String currentCustomAnimation;
    
    public Sprite(BufferedImage spr, int sprSizeX, int sprSizeY){
        SPRITE_WIDTH = sprSizeX;
        SPRITE_HEIGHT = sprSizeY;
        currentFrame = 0;
        tick = 0;
        isAnimate = true;
        
        customAnimations = new HashMap<String, int[]>();
        
        spriteSheet = spr;
    }
    
    private void Animate(){
        tick++;
        if(tick > ANIMATION_TICK_LIMIT){
            tick = 0;
            
            if(!playingCustomAnimation){

                currentFrame++;
                if(currentFrame > (spriteSheet.getWidth()/SPRITE_WIDTH)-1){
                    currentFrame = 0;
                }
            } else {
                customAnimationFrame++;
                if(customAnimationFrame > customAnimations.get(currentCustomAnimation).length - 1){
                    customAnimationFrame = 0;
                }
                currentFrame = customAnimations.get(currentCustomAnimation)[customAnimationFrame];
            }
        }
    }
    
    public void draw(Graphics2D g2, int x, int y){
        this.draw(g2, x, y, 1f, 1f);
    }
    public void draw(Graphics2D g2, int x, int y, float widthResize, float heightResize){
    	this.draw(g2, x, y, widthResize, heightResize, 1f);
    }
    public void draw(Graphics2D g2, int x, int y, float opacity){
        this.draw(g2, x, y, 1f, 1f, opacity);
    }
    public void draw(Graphics2D g2, int x, int y, float widthResize, float heightResize, float opacity){
    	if((int)(SPRITE_WIDTH * widthResize) > 0 && (int)(SPRITE_HEIGHT * heightResize) > 0)
    	{
	        BufferedImage subimage = this.spriteSheet.getSubimage(currentFrame*SPRITE_WIDTH, 0, SPRITE_WIDTH, SPRITE_HEIGHT);        
	        g2.drawImage(subimage, x, y, new Color(0f,0f,0f,opacity), null);
	        g2.drawImage(subimage, x, y, (int)(SPRITE_WIDTH * widthResize), (int)(SPRITE_HEIGHT * heightResize), new Color(0f,0f,0f,opacity), null);
	        if(isAnimate)   Animate();
    	}
    }
    
    
    public void stopAnimation(){
        if(isAnimate)   isAnimate = false;
    }
    
    public void resumeAnimation(){
        if(!isAnimate)  isAnimate = true;
    }
    
    public void resetAnimation(){
        tick = 0;
        currentFrame = 0;
    }
    
    public boolean isAnimating(){
        return isAnimate;
    }   
    
    public void createCustomAnimation(String name, int... frames){
        customAnimations.put(name, frames);
    }
    
    public void playCustomAnimation(String name){
        tick = 0;
        customAnimationFrame = 0;
        currentCustomAnimation = name;
        currentFrame = customAnimations.get(name)[0];
        playingCustomAnimation = true;
    }
    
    public void playDefaultAnimation(){
        tick = 0;
        customAnimationFrame = 0;
        currentFrame = 0;
        playingCustomAnimation = false;
    }
    
    public String getCurrentCustomAnimation(){
        if(playingCustomAnimation)
            return currentCustomAnimation;
        return "default";
    }
}