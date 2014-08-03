package states;

import code.Animation;
import code.Interlude;
import code.Scene;
import game.Choice;
import game.ChoiceManager;
import game.Player;
import game.PlayerManager;
import java.applet.AudioClip;
import java.awt.Graphics2D;
import java.awt.Point;
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
    
    // Player choices
    public static final int PLAYER_CHOICE_ABSTAIN        = 0;
    public static final int PLAYER_CHOICE_HELP           = 1;
    public static final int PLAYER_CHOICE_DONTHELP       = 2;
    public static final int TIME_LIMIT                  = 10000;
    private int[] players_choices;
    private ChoiceManager choiceManager;
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
    
    private AudioClip[] scenesClips;
    
    @Override
    public void init() {
        // Initialize players
        PlayerManager.sharedManager().addStandardPlayers();
        choiceManager = new ChoiceManager(1);
        //TODO:change scenes!!!
        
        // Load musics!
        // lel
        
        // First state: prologue!
        inputEnable = false;
        currentState = STATE_PROLOGUE;
        //currentState = STATE_SCENE1;
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
                        if(choiceTime < 0)
                        {
                            choiceManager.timeIsUp();
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
        // Se jogo esta em cena e tempo de animacao jah acabou (tempo de decisao dos jogadores)
        //System.out.println(k);
        if(inScene && gameScene.isOver())
        {
            //System.out.println("oooi");
            Choice choice = null;
            if(k == 65 || k == 83)
            {
                choice = new Choice(PlayerManager.sharedManager().getPlayerById(0), k == 65);
            }
            if(k == 68 || k == 70)
            {
                choice = new Choice(PlayerManager.sharedManager().getPlayerById(1), k == 68);
            }
            if(k == 71 || k == 72)
            {
                choice = new Choice(PlayerManager.sharedManager().getPlayerById(2), k == 71);
            }
            if(k == 74 || k == 75)
            {
                choice = new Choice(PlayerManager.sharedManager().getPlayerById(3), k == 74);
            }
            
            choiceManager.addChoice(choice);
            
            if (choiceManager.choiceCount() < PlayerManager.sharedManager().alivePlayers()-1)
            {
                //falta decisao
            }
            else
            {
                choiceManager.compareChoices();
                nextState();
            }
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
    
    private void nextState(){
        if(inScene && currentState == STATE_PROLOGUE){
            // Scene 1
            inScene = true;
            currentState = STATE_SCENE1;
            gameScene = new Scene(sceneAnimations(currentState));
            return;
        } else if(inScene && currentState == STATE_SCENE5){
            // Epilogue
            inScene = true;
            currentState = STATE_EPILOGUE;
            gameScene = new Scene(sceneAnimations(currentState));
            return;
        } else if(inScene){
            // Interlude
            inScene = false;
            
        } else if(!inScene){
            // Next state
            currentState++;
            inScene = true;
            gameScene = new Scene(sceneAnimations(currentState));
        }
        
    }
    
    private Animation[] sceneAnimations(int i){
        Animation[] newAnimation = null;
        Animation anime;
        
        Point point = new Point(0,192);
    	float resizeX = 1024f/1600f, resizeY = 1024f/1600f;
    	long duration = 2l;
        switch(i){
            case STATE_PROLOGUE:          	
            	
            	newAnimation = new Animation[10];
            	
                anime = newAnimation[0] = new Animation(duration, resizeX, resizeY, "prologue01");
                anime.addPointInTime("prologue01", 0, new Point(0, 170));
                anime = newAnimation[1] = new Animation(duration, resizeX, resizeY, "prologue02");
                anime.addPointInTime("prologue02", 0, new Point(0, 170));
                anime = newAnimation[2] = new Animation(duration, resizeX, resizeY, "prologue03");
                anime.addPointInTime("prologue03", 0, new Point(0, 170));
                anime = newAnimation[3] = new Animation(duration, resizeX, resizeY, "prologue04");
                anime.addPointInTime("prologue04", 0, new Point(0, 170));
                
            	for(int j = 5; j < 10; j++) {
                    newAnimation[j - 1] = anime = new Animation(duration, resizeX, resizeY, "prologue0" + j);
                    anime.addPointInTime("prologue0" + j, 0, point);
                }
            	newAnimation[9] = anime = new Animation(duration, resizeX, resizeY, "prologue10");
                anime.addPointInTime("prologue10", 0, point);
                
                anime = newAnimation[0];
                anime.addPointInTime("prologue01", 2, new Point(0, 210));
                anime = newAnimation[1];
                anime.addPointInTime("prologue02", 2, new Point(0, 210));
                anime = newAnimation[2];
                anime.addPointInTime("prologue03", 2, new Point(0, 210));
                anime = newAnimation[3];
                anime.addPointInTime("prologue04", 2, new Point(0, 210));
        		
                        
                break;
            case STATE_SCENE1:
                newAnimation = new Animation[1];
                anime = newAnimation[0] = new Animation(10, resizeX, resizeY, "scene1_1");
                anime.addPointInTime("scene1_1", 0, point);
                break;
            case STATE_SCENE2:
                newAnimation = new Animation[1];
                anime = newAnimation[0] = new Animation(10, resizeX, resizeY, "scene2_1");
                anime.addPointInTime("scene2_1", 0, point);
                break;
            case STATE_SCENE3:
                newAnimation = new Animation[1];
                anime = newAnimation[0] = new Animation(10, resizeX, resizeY, "scene3_1");
                anime.addPointInTime("scene3_1", 0, point);
                break;
            case STATE_SCENE4:
                newAnimation = new Animation[1];
                anime = newAnimation[0] = new Animation(10, resizeX, resizeY, "scene4_1");
                anime.addPointInTime("scene4_1", 0, point);
                break;
            case STATE_SCENE5:
                newAnimation = new Animation[1];
                anime = newAnimation[0] = new Animation(10, resizeX, resizeY, "scene5_1");
                anime.addPointInTime("scene5_1", 0, point);
                break;
            case STATE_EPILOGUE:
                break;
            default:
                break;
        }
        return newAnimation;
    }
}
