/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package states;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;

import resources.ResourceManager;
import code.Interlude;
import code.TimeBar;

/**
 *
 * @author Peronio
 */
public class GameStateTest extends GameState{
    // private int oldMouseX, oldMouseY;
    Interlude interlude;

    @Override
    public void init() {
    	String[] strings = new String[0];	
    	interlude = new Interlude(strings, new Point(100,100), 2, 10, 0);
    }

    @Override
    public void update(long wait) {  
    	interlude.update(wait);
    }

    @Override
    public void draw(Graphics2D g) {
    	// Imprimir coisas na tela =D
    	interlude.draw(g);
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

