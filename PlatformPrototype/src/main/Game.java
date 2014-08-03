package main;

import code.Resources;
import javax.swing.JFrame;
import resources.ResourceManager;
import states.GameStateManager;

/**
 *
 * @author Peronio
 */
public class Game {
	private JFrame gameFrame;
    
    private GameStateManager gsm;
    private ResourceManager rm;
    
    public static void main(String args[]){
	    Resources.setStandardMap("pt_br");
        new Game();
    }
    
    Game(){
        gsm = GameStateManager.getInstance();
        rm = ResourceManager.getInstance();
        
        createGameFrame();        
    }
    
    private void createGameFrame(){
        gameFrame = new JFrame("Game Prototype");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setResizable(false);
        gameFrame.setContentPane(new Panel());
        gameFrame.pack();
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
        gameFrame.setSize(1024, 768);
        
    }
}
