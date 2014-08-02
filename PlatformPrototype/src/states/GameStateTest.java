/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package states;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import resources.ResourceManager;
import code.TimeBar;

/**
 *
 * @author Peronio
 */
public class GameStateTest extends GameState{
    private int oldMouseX, oldMouseY;
    TimeBar timebar;

    @Override
    public void init() {
    	timebar = new TimeBar(0, 0, 7);
    }

    @Override
    public void update(long wait) {  
    	timebar.update(wait);    	
    }

    @Override
    public void draw(Graphics2D g) {
    	// Imprimir coisas na tela =D
    	timebar.draw(g);
    }

    @Override
    public void keyPressed(int k) {        
    }

    @Override
    public void keyReleased(int k) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {        
    }

    public void mousePressed(MouseEvent e) {
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
    
}
