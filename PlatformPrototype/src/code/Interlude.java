package code;

import states.GameStateManager;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

import resources.ResourceManager;
import resources.Sprite;

public class Interlude {

	private Font font;
	private Sprite overlay;
	private float stringOpacity;
	private Boolean fadeOut;
	private int stringCounter;

	private String[] strings;
	private Point position;
	private long fadeTime;
	private long totalTime, waited;
	private int nextState;		
        
        private boolean isOver;

	public Interlude(String[] strings, Point position, float fadeTime, int time) {
		try {
			String path;
			path = "C:\\Users\\João\\workspace\\CampJam14\\PlatformPrototype\\";
			font = Font.createFont(Font.TRUETYPE_FONT, new File(path + "assets\\eurosti.ttf"));
			font = font.deriveFont(25f);
		} catch (NullPointerException | FontFormatException | IOException e) {
			System.out.println(e.getMessage());
		}
		overlay = ResourceManager.getInstance().getSprite("overlay");
		stringOpacity = 0;
		this.stringCounter = 0;
		fadeOut = false;
		
		this.strings = strings;
		this.position = position;
		this.fadeTime = (long) fadeTime * 1000;
		this.totalTime = time * 1000;
		this.waited = 0l;
	}

	public void update(long wait) {
		waited += wait;

		if (waited >= fadeTime * (stringCounter + 1))
			stringCounter++;

		if ((float) waited < ((float) (((float) fadeTime) * (float) (stringCounter + 1))) && stringCounter < strings.length) {
			stringOpacity = ((float) waited - (float) fadeTime	* (stringCounter)) / ((float) fadeTime);
		}

		if (waited > totalTime - fadeTime) {
			fadeOut = true;
			stringOpacity = ((float) (totalTime - waited)) / (float) fadeTime;
		}

		if (stringOpacity < 0)
			stringOpacity = 0f;
		if (stringOpacity > 1)
			stringOpacity = 1f;
		
		if(waited >= totalTime) isOver = true;
	}

	public void draw(Graphics2D g) {
		if (g.getFont() != this.font)
			g.setFont(font);

		int y = 0;

		for (int i = 0; i < strings.length && i <= stringCounter; i++) {
			if(stringCounter == i || fadeOut) g.setComposite(AlphaComposite.SrcOver.derive(stringOpacity));
			g.setColor(Color.white);
			g.drawString(strings[i], position.x, position.y + y);
			
			g.setComposite(AlphaComposite.SrcOver.derive(1f));
			
			y += (int) font.getStringBounds(strings[i], new FontRenderContext(new AffineTransform(), false, false)).getHeight();
		}
	}
        
        public boolean isOver(){
            return isOver;
        }

}
