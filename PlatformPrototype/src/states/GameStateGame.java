package states;

import code.Interlude;
import code.Scene;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/*
 * @author Peronio
 */
public class GameStateGame extends GameState{

    // Player health
    public static final int PLAYER_STATUS_NOINJURE      = 0;
    public static final int PLAYER_STATUS_LIGHTINJURE   = 1;
    public static final int PLAYER_STATUS_MIDLYINJURE   = 2;
    public static final int PLAYER_STATUS_HEAVYINJURE   = 3;
    public static final int PLAYER_STATUS_DEAD          = 4;
    private int[] players_health;
    
    // Player choices
    public static final int PLAYER_CHOICE_ABSTAIN   = 0;
    public static final int PLAYER_CHOICE_HELP      = 1;
    public static final int PLAYER_CHOICE_DONTHELP  = 2;
    private int[] players_choices;
    
    // Game scenes and interlude
    enum SCENES{
        
    }
    
    private Scene gameScene;
    private Interlude gameInterlude;
    
    @Override
    public void init() {
        players_health = new int[4];
        for(int i: players_health){
            i =  PLAYER_STATUS_NOINJURE;
        }
        
        players_choices = new int[4];
        for(int i: players_choices){
            i = PLAYER_CHOICE_ABSTAIN;
        }
        
        
    }

    @Override
    public void update(long wait) {
    }

    @Override
    public void draw(Graphics2D g) {
    }

    @Override
    public void keyPressed(int k) {
    }

    @Override
    public void keyReleased(int k) {
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
