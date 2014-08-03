package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import main.Panel;
import resources.ResourceManager;
import resources.Sprite;

/*
 * @author Peronio
 */
public class GameStatePrepare extends GameState{

    private ResourceManager rm;
    private GameStateManager gsm;

    private Sprite thumbnailA;
    private Sprite thumbnailB;
    private Sprite thumbnailC;
    private Sprite thumbnailD;
    
    private Sprite ok;
    
    private boolean playerAOk;
    private boolean playerBOk;
    private boolean playerCOk;
    private boolean playerDOk;
    
    float rszx;
    float rszy;
    
    Font font;
    
    @Override
    public void init() {
        rm = ResourceManager.getInstance();
        gsm = GameStateManager.getInstance();
        
        thumbnailA = rm.getSprite("personagemAThumbnail");
        thumbnailB = rm.getSprite("personagemBThumbnail");
        thumbnailC = rm.getSprite("personagemCThumbnail");
        thumbnailD = rm.getSprite("personagemDThumbnail");
        
        ok         = rm.getSprite("ok");
        
        rszx = (float)(Panel.WIDTH/4)/thumbnailA.SPRITE_WIDTH;
        rszy = (float)(Panel.HEIGHT)/thumbnailA.SPRITE_HEIGHT;
        
        playerAOk = false;
        playerBOk = false;
        playerCOk = false;
        playerDOk = false;
        
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("assets\\eurosti.ttf"));
            font = font.deriveFont(50f);
        } catch (NullPointerException | FontFormatException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(long wait) {
        if(playerAOk && playerBOk && playerCOk && playerDOk)
            gsm.setState(GameStateManager.GAMESTATE_GAME);
    }

    @Override
    public void draw(Graphics2D g) {
        //g.setColor(Color.WHITE);
        thumbnailA.draw(g, 0, 0, rszx, rszy);
        thumbnailB.draw(g, Panel.WIDTH/4, 0, rszx, rszy);
        thumbnailC.draw(g, Panel.WIDTH/2, 0, rszx, rszy);
        thumbnailD.draw(g, 3*Panel.WIDTH/4, 0, rszx, rszy);
        
        
        g.setFont(font);
        //g.setColor(Color.BLACK);
        g.drawString("A | S", Panel.WIDTH/14, 4*Panel.HEIGHT/6);
        g.drawString("D | F", Panel.WIDTH/4 + Panel.WIDTH/14, 4*Panel.HEIGHT/6);
        g.drawString("G | H", Panel.WIDTH/2 + Panel.WIDTH/14, 4*Panel.HEIGHT/6);
        g.drawString("J | K", 3*Panel.WIDTH/4 + Panel.WIDTH/14, 4*Panel.HEIGHT/6);
        //g.setColor(Color.GREEN);
        g.drawString("HELP", Panel.WIDTH/2 - Panel.WIDTH/4, 7*Panel.HEIGHT/8);
        //g.setColor(Color.BLACK);
        g.drawString("|", Panel.WIDTH/2, 7*Panel.HEIGHT/8);
        //g.setColor(Color.RED);
        g.drawString("DON'T HELP", Panel.WIDTH/2 + Panel.WIDTH/12, 7*Panel.HEIGHT/8);
        //g.setColor(Color.BLACK);
        if(playerAOk)
            ok.draw(g, Panel.WIDTH/13, 5*Panel.HEIGHT/7);
        if(playerBOk)
            ok.draw(g, Panel.WIDTH/4 + Panel.WIDTH/13, 5*Panel.HEIGHT/7);
        if(playerCOk)
            ok.draw(g, Panel.WIDTH/2 + Panel.WIDTH/13, 5*Panel.HEIGHT/7);
        if(playerDOk)
            ok.draw(g, 3*Panel.WIDTH/4 + Panel.WIDTH/13, 5*Panel.HEIGHT/7);
    }

    @Override
    public void keyPressed(int k) {
        if(k == 65 || k == 83)
            playerAOk = true;
        if(k == 68 || k == 70)
            playerBOk = true;
        if(k == 71 || k == 72)
            playerCOk = true;
        if(k == 74 || k == 75)
            playerDOk = true;
                  
  
    }

    @Override
    public void keyReleased(int k) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

}
