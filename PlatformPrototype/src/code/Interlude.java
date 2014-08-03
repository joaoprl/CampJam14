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

	Font font;
	Sprite overlay;
	float stringOpacity;
	Boolean fadeOut;
	int stringCounter;

	String[] strings;
	Point position;
	long fadeTime;
	long totalTime, waited;
	int nextState;		

	public Interlude(String[] strings, Point position, float fadeTime,
			int time, int nextState) {
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\Users\\João\\workspace\\CampJam14\\PlatformPrototype\\assets\\LHANDW.TTF"));
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
		this.nextState = nextState;
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
		
		// if(waited >= totalTime) this.end();
	}

	public void draw(Graphics2D g) {
		if (g.getFont() != this.font)
			g.setFont(font);

		int y = 0;

		for (int i = 0; i < strings.length && i <= stringCounter; i++) {
			if(stringCounter == i || fadeOut) g.setComposite(AlphaComposite.SrcOver.derive(stringOpacity));
			
			g.drawString(strings[i], position.x, position.y + y);
			
			g.setComposite(AlphaComposite.SrcOver.derive(1f));
			
			y += (int) font.getStringBounds(strings[i], new FontRenderContext(new AffineTransform(), false, false)).getHeight();
		}
	}

	private void end() {
		GameStateManager.getInstance().setState(nextState);
	}

}
