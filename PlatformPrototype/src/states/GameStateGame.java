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
    public static final int TIME_LIMIT                  = 10;
    private int[] players_choices;
    private boolean inputEnable;
    private long choiceTime;
    
    // Current substate
    public static final int STATE_PROLOGUE               = 0;
    public static final int STATE_SCENE1                 = 1;
    public static final int STATE_SCENE2                 = 2;
    public static final int STATE_SCENE3                 = 3;
    public static final int STATE_SCENE4                 = 4;
    public static final int STATE_SCENE5                 = 5;
    public static final int STATE_EPILOGUE               = 6;
    private int currentState;
    private boolean inScene;
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
        
        // First state: prologue!
        inputEnable = false;
        currentState = STATE_PROLOGUE;
        gameScene = new Scene(sceneAnimations(currentState));
        inScene = true;
    }

    @Override
    public void update(long wait) {
        if(inScene){
            gameScene.update(wait);
            if(gameScene.isOver() && !inputEnable){
                if(currentState != STATE_EPILOGUE && currentState != STATE_PROLOGUE){
                    if(!inputEnable){
                        // Enabling input, next state is Interlude
                        inputEnable = true;
                        choiceTime = TIME_LIMIT;
                    } else {
                        choiceTime -= wait;
                        // Update Timebar
                        if(choiceTime < 0){
                            for(int i: players_choices){
                                i = i == PLAYER_CHOICE_ABSTAIN ? PLAYER_CHOICE_DONTHELP : i;
                            }
                            nextState();
                        }
                    }
                } else {
                    nextState();
                }

            }
        } else {
            // interlude.update()
        }
    }

    @Override
    public void draw(Graphics2D g) {
        gameScene.draw(g);
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
    
    private void nextState(){
        if(inScene){
            // new Interlude
        } else {
            
        }
        
    }
    
    private Animation[] sceneAnimations(int i){
        Animation[] newAnimation = null;
        switch(i){
            case STATE_PROLOGUE:
                break;
            case STATE_SCENE1:
                break;
            case STATE_SCENE2:
                break;
            case STATE_SCENE3:
                break;
            case STATE_SCENE4:
                break;
            case STATE_SCENE5:
                break;
            case STATE_EPILOGUE:
                break;
            default:
                //lel
                break;
        }
        return newAnimation;
    }
}
