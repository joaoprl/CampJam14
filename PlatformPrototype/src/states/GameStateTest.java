/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package states;

import code.Animation;
import code.TimeBar;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;

import resources.ResourceManager;

/**
 *
 * @author Peronio
 */
public class GameStateTest extends GameState{
    private int oldMouseX, oldMouseY;
    TimeBar timebar;

    Animation animaBarney;
    @Override
    public void init() {
    	timebar = new TimeBar(0, 0, 7);
        animaBarney = new Animation(10, "background_time_bar");
        animaBarney.addPointInTime("background_time_bar", 0, new Point(2, 2));
        animaBarney.addPointInTime("background_time_bar", 4, new Point(100,100));
        animaBarney.addPointInTime("background_time_bar", 6, new Point(400, -50));
        animaBarney.addPointInTime("background_time_bar", 7, new Point(0, 1000));
    }

    @Override
    public void update(long wait) {  
    	timebar.update(wait);
        animaBarney.updateFrame(wait);
    }

    @Override
    public void draw(Graphics2D g) {
    	// Imprimir coisas na tela =D
    	timebar.draw(g);
        animaBarney.drawFrame(g);
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
