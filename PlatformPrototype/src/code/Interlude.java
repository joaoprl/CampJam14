package code;

import states.GameStateManager;

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
	float overlayOpacity, overlayOpacityGlobal;

	String[] strings;
	Point position;
	long fadeTime;
	long totalTime, waited;
	int nextState;
	int stringCounter;

	public Interlude(String[] strings, Point position, float fadeTime,
			int time, int nextState) {
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\Users\\João\\workspace\\CampJam14\\PlatformPrototype\\assets\\LHANDW.TTF"));
			font = font.deriveFont(25f);
		} catch (NullPointerException | FontFormatException | IOException e) {
			System.out.println(e.getMessage());
		}
		overlay = ResourceManager.getInstance().getSprite("overlay");
		overlayOpacity = 0;
		overlayOpacityGlobal = 1;

		this.strings = strings;

		this.position = position;
		this.fadeTime = (long) fadeTime * 1000;
		this.totalTime = time * 1000;
		this.waited = 0l;
		this.nextState = nextState;
		this.stringCounter = 0;
	}

	public void update(long wait) {
		waited += wait;

		if (waited >= fadeTime * (stringCounter + 1))
			stringCounter++;

		if ((float) waited < ((float) (((float) fadeTime) * (float) (stringCounter + 1)))&& stringCounter < strings.length) {
			overlayOpacity = ((float) waited - (float) fadeTime	* (stringCounter)) / ((float) fadeTime);
		}

		if (waited > totalTime - fadeTime) {
			overlayOpacityGlobal = ((float) (totalTime - waited)) / (float) fadeTime;
		}

		if (overlayOpacity < 0)
			overlayOpacity = 0f;
		if (overlayOpacity > 1)
			overlayOpacity = 1f;
		if (overlayOpacityGlobal < 0)
			overlayOpacityGlobal = 0f;
		if (overlayOpacityGlobal > 1)
			overlayOpacityGlobal = 1f;
		// if(waited >= totalTime) this.end();
	}

	public void draw(Graphics2D g) {
		if (g.getFont() != this.font)
			g.setFont(font);

		int y = 0, lastStringHeight = 0, lastPosition = 0;

		for (int i = 0; i < strings.length && i <= stringCounter; i++) {
			g.drawString(strings[i], position.x, position.y + y);
			lastPosition = position.y + y;
			y += lastStringHeight = (int) font.getStringBounds(strings[i], new FontRenderContext(new AffineTransform(), false, false)).getHeight();

		}
		overlay.draw(g, position.x, lastPosition - (int) (1.3 * lastStringHeight / 2), 1f, ((float) lastStringHeight) / 768f, 1f - overlayOpacity);

		overlay.draw(g, 0, 0, 1 - overlayOpacityGlobal);
	}

	private void end() {
		GameStateManager.getInstance().setState(nextState);
	}

}
