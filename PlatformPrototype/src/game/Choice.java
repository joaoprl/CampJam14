/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

/**
 *
 * @author Francesco
 */
public class Choice
{
    private Player player;
    private boolean choice;
    
    public Choice(Player player, boolean willHelp)
    {
        this.player = player;
        this.choice = willHelp;
    }
    
    public Player getPlayer()
    {
        return player;
    }
    
    public boolean getChoice()
    {
        return choice;
    }
}
