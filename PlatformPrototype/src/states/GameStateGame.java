package states;

import code.Animation;
import code.Audio;
import code.Interlude;
import code.PlayerGui;
import code.Scene;
import code.TimeBar;
import game.Choice;
import game.ChoiceManager;
import game.Player;
import game.PlayerManager;

import java.applet.AudioClip;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import main.Panel;

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
    private TimeBar timeBar;
    private PlayerGui pg;

    private AudioClip[] scenesClips;
    
    Boolean audioFlag;
    int oldState;
    private ArrayList<Player> playersWhoJustDied;
    private ArrayList<Player> helpingPlayers;
    
    @Override
    public void init() {
        // Initialize players
        PlayerManager.sharedManager().addStandardPlayers();

        // Load musics!
        // lel
        
        // First state: prologue!
        inputEnable = false;
        currentState = STATE_PROLOGUE;
        //currentState = STATE_SCENE1;
        gameScene = new Scene(sceneAnimations(currentState));
        inScene = true;
        
        timeBar = new TimeBar(new Point(Panel.WIDTH*4/5,Panel.HEIGHT*1/5), 10);
        pg = new PlayerGui();
        audioFlag = false;
    }

    @Override
    public void update(long wait) {
    	if(oldState != currentState) audioFlag = false;
    	
    	if(!inScene)
		{
			Audio.fadeout(2);
			if(currentState == STATE_SCENE5) Audio.fadeout(1);
		}
    	
    	if(currentState == STATE_SCENE1 && !audioFlag)
    	{
			Audio.loop("Audio\\Efeitos\\Fogo.wav", 2);
			audioFlag = true;    		
    	}
    	else if(currentState == STATE_SCENE2 && !audioFlag)
		{
    		Audio.loop("Audio\\Efeitos\\Afogamento.wav", 2);
    		audioFlag = true;		
		}
    	else if(currentState == STATE_SCENE3 && !audioFlag)
		{
    		Audio.loop("Audio\\Efeitos\\Quadro.wav", 2);
    		audioFlag = true;		
		}
    	else if(currentState == STATE_SCENE4 && !audioFlag)
		{
    		Audio.loop("Audio\\Efeitos\\Enforcamento.wav", 2);
    		audioFlag = true;		
		}
    	else if(currentState == STATE_SCENE5 && !audioFlag)
		{
    		Audio.loop("Audio\\Efeitos\\Fogo.wav", 2);
    		audioFlag = true;		
		}
    	oldState = currentState;
    	
        if(inScene){
            gameScene.update(wait);
            if(gameScene.isOver()){
                if(currentState != STATE_EPILOGUE && currentState != STATE_PROLOGUE){
                    if(!inputEnable){
                        // Enabling input, next state is Interlude
                        inputEnable = true;
                        choiceTime = TIME_LIMIT;
                    } else {
                        choiceTime -= wait;
                        System.out.println(choiceTime);
                        timeBar.update(wait);
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
            if (gameInterlude == null)
            {
                System.out.println(currentState);
            }
            gameInterlude.update(wait);
            if(gameInterlude.isOver()){
                nextState();
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {

        if(inScene)
        {
            gameScene.draw(g);
            if (currentState != STATE_PROLOGUE)
            {
                pg.draw(g);
            }
            if(gameScene.isOver())
            {
                timeBar.draw(g);
            }
        }
        else
        {
            gameInterlude.draw(g);
        }
    }

    @Override
    public void keyPressed(int k) {
        // Se jogo esta em cena e tempo de animacao jah acabou (tempo de decisao dos jogadores)
        //System.out.println(k);
        if(inScene && gameScene.isOver())
        {
            //System.out.println("oooi");
            helpingPlayers = new ArrayList<Player>();
            
            Choice choice = null;
            if(k == 65 || k == 83)
            {
                if (k == 65) helpingPlayers.add(PlayerManager.sharedManager().getPlayerById(0));
                choice = new Choice(PlayerManager.sharedManager().getPlayerById(0), k == 65);
            }
            if(k == 68 || k == 70)
            {
                if (k == 68) helpingPlayers.add(PlayerManager.sharedManager().getPlayerById(1));
                choice = new Choice(PlayerManager.sharedManager().getPlayerById(1), k == 68);
            }
            if(k == 71 || k == 72)
            {
                if (k == 71) helpingPlayers.add(PlayerManager.sharedManager().getPlayerById(2));
                choice = new Choice(PlayerManager.sharedManager().getPlayerById(2), k == 71);
            }
            if(k == 74 || k == 75)
            {
                if (k == 74) helpingPlayers.add(PlayerManager.sharedManager().getPlayerById(3));
                choice = new Choice(PlayerManager.sharedManager().getPlayerById(3), k == 74);
            }
            
            choiceManager.addChoice(choice);
            
            if (choiceManager.choiceCount() < PlayerManager.sharedManager().alivePlayers()-1)
            {
                //falta decisao
            }
            else
            {
                playersWhoJustDied = choiceManager.compareChoices();
                
                //playersWhoJustDied
                //helpingPlayers
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
            inputEnable = false;
            currentState = STATE_SCENE1;
            choiceManager = new ChoiceManager(1);
            gameScene = new Scene(sceneAnimations(currentState));
            return;
        } else if(inScene && currentState == STATE_SCENE5){
            // Epilogue
            inScene = true;
            inputEnable = false;
            currentState = STATE_EPILOGUE;
            gameScene = new Scene(sceneAnimations(currentState));
            return;
        } else if(inScene){
            // Interlude
            inScene = false;

            
            String[] strs = new String[4];
            for (int i = 0; i < 4; i++)
            {
                strs[i] = "";
            }
            
            int helping = 0;
            boolean ghost = false;
            String helpers  = "";
            for (Player player : helpingPlayers)
            {
                if (player.isGhost())
                {
                    ghost = true;
                }
                else
                {
                    helping ++;
                }
                helpers += player.getPlayerLetter() + ", ";
            }
            
            String dead = "";
            int deadCount = 0;
            for (Player player : PlayerManager.sharedManager().getDeadPlayers())
            {
                dead += player.getPlayerLetter() + ", ";
                deadCount++;
            }
            
            if (currentState == STATE_SCENE1)
            {
                if (helping == 0)
                {
                    strs[0] = "A was severely burned in the fire.";
                }
                else
                {
                    strs[0] = helpers + ", and A were severely burned in the fire";
                }
                if (deadCount > 0)
                {
                    strs[1] = dead + "died.";
                }
                strs[3] = "After the fire the children escaped to the bedroom.";
            }
            else if (currentState == STATE_SCENE2)
            {
                if (helping == 0)
                {
                    strs[0] = "Without help, B drowned and sinked to the bottom.";
                }
                else
                {
                    strs[0] = helpers + "and B nearly drowned.";
                }
                if (deadCount > 0)
                {
                    strs[1] = dead + "died.";
                }
                strs[3] = "From the bedroom the children escaped to the library.";
            }
            else if (currentState == STATE_SCENE3)
            {
                if (helping == 0)
                {
                    strs[0] = "D was choked to death as no one tried helped.";
                }
                else
                {
                    strs[0] = helpers + "and D barely escaped being choked to death.";
                }
                if (deadCount > 0)
                {
                    strs[1] = dead + "died.";
                }
                strs[3] = "From the bedroom the children escaped to the library.";
            }
            else if (currentState == STATE_SCENE4)
            {
                
            }
            else if (currentState == STATE_SCENE5)
            {
                
            }
            
            if (helping > 0) 
            {
                
            }
            

            
            //String[] strs = new String[4];
            /*strs[0] = "The survivors have escaped!";
            strs[1] = "Perônio";
            strs[2] = "muito";
            strs[3] = "gay";*/
            gameInterlude = new Interlude(strs, new Point(Panel.WIDTH / 10, (int)(Panel.HEIGHT * 0.4)), 3f, 15);

                
            inputEnable = false;
            /*String[] strs = new String[1];
            strs[0] = "hu3";
            gameInterlude = new Interlude(strs, new Point(10,10), 2f, 5);*/
            
        } else if(!inScene){
            // Next state
            currentState++;
            inScene = true;
            inputEnable = false;
            choiceManager = new ChoiceManager(currentState);
            timeBar = new TimeBar(new Point(Panel.WIDTH*4/5,Panel.HEIGHT*1/5), 10);
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
                anime = newAnimation[0] = new Animation(3, resizeX, resizeY, "scene1_1");
                anime.addPointInTime("scene1_1", 0, point);
                break;
            case STATE_SCENE2:
                newAnimation = new Animation[1];
                anime = newAnimation[0] = new Animation(3, resizeX, resizeY, "scene2_1");
                anime.addPointInTime("scene2_1", 0, point);
                break;
            case STATE_SCENE3:
                newAnimation = new Animation[1];
                anime = newAnimation[0] = new Animation(3, resizeX, resizeY, "scene3_1");
                anime.addPointInTime("scene3_1", 0, point);
                break;
            case STATE_SCENE4:
                newAnimation = new Animation[1];
                anime = newAnimation[0] = new Animation(3, resizeX, resizeY, "scene4_1");
                anime.addPointInTime("scene4_1", 0, point);
                break;
            case STATE_SCENE5:
                newAnimation = new Animation[1];
                anime = newAnimation[0] = new Animation(3, resizeX, resizeY, "scene5_1");
                anime.addPointInTime("scene5_1", 0, point);
                break;
            case STATE_EPILOGUE:
            	newAnimation = new Animation[1];
                anime = newAnimation[0] = new Animation(3, resizeX, resizeY, "epilogue");
                anime.addPointInTime("epliogue", 0, point);
                break;
            default:
                break;
        }
        return newAnimation;
    }
}
