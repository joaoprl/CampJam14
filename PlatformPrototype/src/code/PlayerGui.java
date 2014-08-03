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
        
        rszx = (float)(Panel.WIDTH/4)/playerAThumbnail.SPRITE_WIDTH;
        rszy = (float)(Panel.HEIGHT)/playerAThumbnail.SPRITE_HEIGHT;
    }
    
    public void draw(Graphics2D g)
    {
        playerAThumbnail.draw(g, 0, 0, rszx, rszy);
        playerBThumbnail.draw(g, Panel.WIDTH/4, 0, rszx, rszy);
        playerCThumbnail.draw(g, Panel.WIDTH/2, 0, rszx, rszy);
        playerDThumbnail.draw(g, 3*Panel.WIDTH/4, 0, rszx, rszy);
        
    }
    
}
