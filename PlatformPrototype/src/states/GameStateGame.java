package states;

import code.Animation;
import code.Interlude;
import code.Scene;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/*
 * @author Peronio
 */
public class GameStateGame extends GameState{

    // Player health
    public static final int PLAYER_STATUS_NOINJURE       = 0;
    public static final int PLAYER_STATUS_LIGHTINJURE    = 1;
    public static final int PLAYER_STATUS_MIDLYINJURE    = 2;
    public static final int PLAYER_STATUS_HEAVYINJURE    = 3;
    public static final int PLAYER_STATUS_DEAD           = 4;
    private int[] players_health;
    
    // Player choices
    public static final int PLAYER_CHOICE_ABSTAIN        = 0;
    public static final int PLAYER_CHOICE_HELP           = 1;
    public static final int PLAYER_CHOICE_DONTHELP       = 2;
    private int[] players_choices;
    private boolean inputEnable;
    
    // Current substate
    private int currentState;
    private Scene gameScene;
    private Interlude gameInterlude;
    
    @Override
    public void init() {
        // Initialize player health
        players_health = new int[4];
        for(int i: players_health){
            i =  PLAYER_STATUS_NOINJURE;
        }
        
        // Initialize player choices
        players_choices = new int[4];
        for(int i: players_choices){
            i = PLAYER_CHOICE_ABSTAIN;
        }
        
        inputEnable = false;
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
    
    enum SCENE_ANIMATIONS{
        PROLOGUE,SCENE1,SCENE2,SCENE3,SCENE4,SCENE5,EPILOGUE;
        
        public Animation getAnimation(){
            Animation newAnimation = null;
            switch(this){
                case PROLOGUE:
                
                    break;
                case SCENE1:
                    break;
                case SCENE2:
                    break;
                case SCENE3:
                    break;
                case SCENE4:
                    break;
                case SCENE5:
                    break;
                case EPILOGUE:
                    break;
            }
            return newAnimation;
        }
    }
}
