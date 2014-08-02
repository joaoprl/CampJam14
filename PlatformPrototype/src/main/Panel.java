package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import states.GameStateManager;

/**
 *
 * @author Peronio
 */
public class Panel extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener {

    // Panel dimensions
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public static final int SCALE = 2;
    
    // Main loop
    private Thread thread;
    private boolean running = false;
    private final int FPS = 60;
    private final long targetTime = 1000 / FPS;
    
    // Draw
    BufferedImage image;
    Graphics2D g;
    
    // Game State Manager
    GameStateManager gsm;
    
    public Panel(){
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        setFocusable(true);
        requestFocus();        
    }
    
    public void addNotify(){
        super.addNotify();
        if(thread == null){
            running = true;
            addKeyListener(this);
            addMouseListener(this);
            addMouseMotionListener(this);
            thread = new Thread(this);
            
            thread.start();
        }
    }
    
    public void init(){
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        gsm = GameStateManager.getInstance();
        
    }
    
    public void update(){
        gsm.update();
    }
    
    public void draw(){
        g.clearRect(0, 0, WIDTH, HEIGHT);
        gsm.draw(g);
    }
    
    public void drawToScreen(){
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
        g2.dispose();
    }
    
    @Override
    public void run(){
        
        init();

        long start;
        long elapsed;
        long wait;

        while(running){
            start = System.nanoTime();
            
            update();
            SwingUtilities.invokeLater(new Runnable(){
                @Override
                public void run(){
                    draw();
                    drawToScreen();
                }
            });
            elapsed = System.nanoTime() - start;
            wait = targetTime - elapsed / 1000000;

            if(wait < 0)wait = 5;
            try{
                Thread.sleep(wait);
            } catch(Exception e) {
                e.printStackTrace();
            }
            
        }
    }

    // Listeners
    @Override
    public void keyTyped(KeyEvent e) {
        // Do Nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        gsm.keyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        gsm.keyReleased(e.getKeyCode());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        gsm.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        gsm.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Do Nothing
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Do Nothing
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Do Nothing
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        gsm.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        gsm.mouseMoved(e);
    }
    
}
