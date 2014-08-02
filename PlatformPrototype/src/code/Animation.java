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
            int j = spritesTimeline.get(s).size()-1;
            while(spritesTimeline.get(s).get(j).time > currentTick) j--;
            if(spritesTimeline.get(s).size()-1 == j)
                sprites.get(s).draw(g, spritesTimeline.get(s).get(j).point.x, spritesTimeline.get(s).get(j).point.y);
            else{
                long t = 1 - (spritesTimeline.get(s).get(j+1).time-spritesTimeline.get(s).get(j).time);
                int dx = ((int)spritesTimeline.get(s).get(j+1).point.x-(int)spritesTimeline.get(s).get(j).point.x);
                int dy = ((int)spritesTimeline.get(s).get(j+1).point.y-(int)spritesTimeline.get(s).get(j).point.y);
                sprites.get(s).draw(g, 
                                    spritesTimeline.get(s).get(j).point.x + (int)(dx*t),
                                    spritesTimeline.get(s).get(j).point.y + (int)(dy*t));
            }
        }
    }
    
    public void addPointInTime(String id, long time, Point point){
        spritesTimeline.get(id).add(new PointInTime(time, point));
    }
    
    public boolean updateFrame(long delta){
        return false;
    }
    
    public void newPointInTime(String id, long time, Point point){
        
    }
    
    private class PointInTime{
        public long time;
        public Point point;
        public PointInTime(long time, Point point){
            this.time = time;
            this.point = point;
        }
    }
}
