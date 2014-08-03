/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

import code.Resources;

/**
 *
 * @author Francesco
 */
public class Player
{
    private int health;
    private boolean isDead;
    private int playerID;
    private String name;
    //TODO: add keys
    
    public Player(int playerID)
    {
        this.playerID = playerID;
    
        this.name = Resources.getStringForKey("player" + getShowableID() + "Name", "constants");
        this.health = Resources.getIntForKey("player" + getShowableID() + "Health", "constants");
        this.isDead = false;

    }
    
    public boolean isDead()
    {
        return this.isDead;
    }
    
    public int getZeroBasedID()
    {
        return playerID;
    }
    public int getShowableID()
    {
        return playerID+1;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return name;
    }
    
    public boolean isGhost()
    {
        return (playerID == 2);
    }
    
    public int getHP()
    {
        return health;
    }
    public void loseHP(int HP)
    {
        this.health -= HP;
        if (health <= 0)
        {
            this.isDead = true;
        }
    }
    
    public void kill()
    {
        this.isDead = true;
        this.health = 0;
    }
    
    public void debug()
    {
        System.out.println("Player " + getShowableID() + " (" + getName() + ")");
        System.out.println(health + "HP, isDead = " + isDead);
    }
    
    public String getPlayerLetter()
    {
        if (playerID == 0)
            return "D";
        if (playerID == 1)
            return "C";
        if (playerID == 2)
            return "A";
        if (playerID == 3)
            return "B";
        return "~peronio";
    }
}
