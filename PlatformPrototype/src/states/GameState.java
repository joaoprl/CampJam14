/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package states;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/**
 *
 * @author Peronio
 */
public abstract class GameState {
    protected GameStateManager gsm;
    
    GameState(){
        gsm = GameStateManager.getInstance();
    }
    
    public abstract void init();
    public abstract void update();
    public abstract void draw(Graphics2D g);
    public abstract void keyPressed(int k);
    public abstract void keyReleased(int k);
    public abstract void mouseClicked(MouseEvent e);
    public abstract void mousePressed(MouseEvent e);
    public abstract void mouseDragged(MouseEvent e);
    public abstract void mouseMoved(MouseEvent e);
}
