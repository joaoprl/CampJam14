package code;

import java.awt.Graphics2D;
import java.awt.Point;

import resources.ResourceManager;
import resources.Sprite;

public class TimeBar {
	Point position;
	long miliseconds, waited;
	float complete;
	Sprite backgroundBar, timeBar;
	Boolean active;
	
	/**
	 * Constrói uma barra de tempo
	 * @param position Posição inicial da barra de tempo
	 * @param seconds Tempo da barra
	 */
	public TimeBar(Point position, float seconds)
	{
		this.position = position;
		this.miliseconds = (long)(seconds * 1000);
		waited = 0;
		complete = 0f;
		backgroundBar = ResourceManager.getInstance().getSprite("background_time_bar");
		timeBar = ResourceManager.getInstance().getSprite("loading_time_bar");
		active = true;
	}
	/**
	 * Constrói uma barra de tempo
	 * @param x Posição x incial da barra de tempo
	 * @param y Posição y incial da barra de tempo
	 * @param seconds Tempo da barra
	 */
	public TimeBar(int x, int y, int seconds)
	{
		this(new Point(x, y), seconds);
	}
	
	/**
	 * Update da barra de tempo
	 * @param wait Tempo decorrido desde a última atualização
	 */
	public void update(long wait)
	{
		if(active)
		{
			waited += wait;
			if(waited >= miliseconds)
			{
				waited = miliseconds;
				active = false;				
			}
			complete = (float)waited / (float)miliseconds;
		}
		
	}
	
	/**
	 * @param g Imprime a barra de fundo e a barra de tempo
	 */
	public void draw(Graphics2D g)
	{
		backgroundBar.draw(g, position.x, position.y);
		timeBar.draw(g, position.x + (int)(complete * timeBar.SPRITE_WIDTH) + (backgroundBar.SPRITE_WIDTH - timeBar.SPRITE_WIDTH) / 2, 
				position.y + (backgroundBar.SPRITE_HEIGHT - timeBar.SPRITE_HEIGHT) / 2, 1f - complete, 1f);
	}
	
	/**
	 * @return true se o tempo se esgotou; false caso contrário
	 */
	public Boolean isOver()
	{
		return !active;
	}

}
