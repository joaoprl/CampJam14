package code;

import java.awt.Color;
import java.awt.Graphics2D;

import main.Panel;
import resources.ResourceManager;
import resources.Sprite;

/*
 * @author Peronio
 */
public class PlayerGui
{
    private ResourceManager rm;

    private Sprite playerOk;
    private Sprite playerAThumbnail;
    private Sprite playerBThumbnail;
    private Sprite playerCThumbnail;
    private Sprite playerDThumbnail;
    
    float rszx;
    float rszy;
    
    public PlayerGui()
    {
        rm = ResourceManager.getInstance();
        
        playerOk         = rm.getSprite("ok");
        playerAThumbnail = rm.getSprite("A_3");
        playerBThumbnail = rm.getSprite("B_4");
        playerCThumbnail = rm.getSprite("C_2");
        playerDThumbnail = rm.getSprite("D_1");
        
        rszx = (float)(Panel.WIDTH/4)/playerAThumbnail.SPRITE_WIDTH/3;
        rszy = (float)(Panel.HEIGHT)/playerAThumbnail.SPRITE_HEIGHT/5;
    }
    
    public void draw(Graphics2D g)
    {
        //playerAThumbnail.draw()
        playerAThumbnail.draw(g, 2*Panel.WIDTH/6 - playerAThumbnail.SPRITE_WIDTH/3 , Panel.HEIGHT - playerAThumbnail.SPRITE_HEIGHT/4, rszx, rszy);
        playerBThumbnail.draw(g, 3*Panel.WIDTH/6 - playerAThumbnail.SPRITE_WIDTH/3, Panel.HEIGHT - playerAThumbnail.SPRITE_HEIGHT/4, rszx, rszy);
        playerCThumbnail.draw(g, 4*Panel.WIDTH/6 - playerAThumbnail.SPRITE_WIDTH/3, Panel.HEIGHT - playerAThumbnail.SPRITE_HEIGHT/4, rszx, rszy);
        playerDThumbnail.draw(g, 5*Panel.WIDTH/6 - playerAThumbnail.SPRITE_WIDTH/3, Panel.HEIGHT - playerAThumbnail.SPRITE_HEIGHT/4, rszx, rszy);
        
    }
    
}
