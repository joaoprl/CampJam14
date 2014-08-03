package states;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import main.Panel;
import resources.ResourceManager;
import resources.Sprite;

/*
 * @author Peronio
 */
public class GameStateMenu extends GameState{

    private ResourceManager rm;
    private GameStateManager gsm;
    
    private Sprite background;
    private float rszx;
    private float rszy;
    
    @Override
    public void init() {
        rm = ResourceManager.getInstance();
        gsm = GameStateManager.getInstance();
        
        // Intialize background
        background = rm.getSprite("background_menu");
        rszx = (float)Panel.WIDTH/background.SPRITE_WIDTH;
        rszy = (float)Panel.HEIGHT/background.SPRITE_HEIGHT;
    }

    @Override
    public void update(long wait) {
    }

    @Override
    public void draw(Graphics2D g) {
        // Draw background
        background.draw(g, 0, 0, rszx, rszy);
    }

    @Override
    public void keyPressed(int k) {
        gsm.setState(GameStateManager.GAMESTATE_PREPARE);
    }

    @Override
    public void keyReleased(int k) {
        //lel
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //lel
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //lel
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //lel
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //lel
    }

}
