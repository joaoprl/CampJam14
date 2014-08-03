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

    ResourceManager rm;
    GameStateManager gsm;
    
    Sprite background;
    
    @Override
    public void init() {
        rm = ResourceManager.getInstance();
        gsm = GameStateManager.getInstance();
        
        // Intialize background
        background = rm.getSprite("background_menu");
    }

    @Override
    public void update(long wait) {
    }

    @Override
    public void draw(Graphics2D g) {
        // Draw background
        background.draw(g, 0, 0, (float)Panel.WIDTH/background.SPRITE_WIDTH, (float)Panel.HEIGHT/background.SPRITE_HEIGHT);
    }

    @Override
    public void keyPressed(int k) {
        if(k == 65 || k == 83 || k == 68 || k == 70 || k == 71 || k == 72 || k == 74 || k == 75 || k == 76){
            gsm.setState(1);
        }
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
