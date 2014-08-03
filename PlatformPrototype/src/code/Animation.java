package code;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import resources.ResourceManager;
import resources.Sprite;

/*
 * @author Peronio
 */
public class Animation {
    // Animation assets
    HashMap<String, Sprite> sprites;
    HashMap<String, ArrayList<PointInTime>> spritesTimeline;
    String[] spriteIds;
    ResourceManager rm;
    
    // Time and play
    long animationDuration;
    long currentTick;
    
    public Animation(long duration, String... ids){
        sprites = new HashMap<String, Sprite>();
        rm = ResourceManager.getInstance();
        
        animationDuration = duration;
        currentTick = 0;
        
        // Initialize sprites
        for(String s: ids){
            sprites.put(s, rm.getSprite(s));
        }
        
        // Initialize positions
        spritesTimeline = new HashMap<String, ArrayList<PointInTime>>();
        for(String s: ids){
            spritesTimeline.put(s, new ArrayList<PointInTime>());
            spritesTimeline.get(s).add(new PointInTime(0, new Point(0,0)));
        }
        
        spriteIds = ids;
    }
    
    public void drawFrame(Graphics2D g){
        for(String s: spriteIds){
            int j = spritesTimeline.get(s).size() - 1;
            while(spritesTimeline.get(s).get(j).time > currentTick) {
                j -= 1;
            }
            if(spritesTimeline.get(s).size()-1 == j)
                sprites.get(s).draw(g, spritesTimeline.get(s).get(j).point.x, spritesTimeline.get(s).get(j).point.y);
            else{
                long t = (spritesTimeline.get(s).get(j+1).time-spritesTimeline.get(s).get(j).time);
                float dx = (spritesTimeline.get(s).get(j+1).point.x-spritesTimeline.get(s).get(j).point.x);
                float dy = (spritesTimeline.get(s).get(j+1).point.y-spritesTimeline.get(s).get(j).point.y);
                float vx = (dx / t);
                float vy = (dy / t);
                float dt = currentTick - spritesTimeline.get(s).get(j).time;
                sprites.get(s).draw(g, 
                                    spritesTimeline.get(s).get(j).point.x + (int)(vx*dt),
                                    spritesTimeline.get(s).get(j).point.y + (int)(vy*dt));
            }
        }
    }
    
    public void addPointInTime(String id, long time, Point point){
        spritesTimeline.get(id).add(new PointInTime(time, point));
    }
    
    public void updateFrame(long delta){
        currentTick += delta;
    }
    
    public boolean isOver(){
        return (currentTick > animationDuration);
    }
    
    private class PointInTime{
        public long time;
        public Point point;
        public PointInTime(long time, Point point){
            this.time = time * 1000;
            this.point = point;
        }
    }
}