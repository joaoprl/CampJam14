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
 
        this.name = Resources.getStringForKey("player" + playerID + "Name");
        this.health = Resources.getIntForKey("player" + playerID + "Health");

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
    
}
