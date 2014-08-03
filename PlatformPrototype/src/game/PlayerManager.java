/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

import java.util.ArrayList;

/**
 *
 * @author Francesco
 */
public class PlayerManager
{
    private static PlayerManager playerManager;
    private ArrayList<Player> players;
    
    private PlayerManager()
    {
        players = new ArrayList<Player>();
    }
    
    public static PlayerManager sharedManager()
    {
        if (playerManager == null)
        {
            playerManager = new PlayerManager();
        }
        return playerManager;
    }
    
    public int alivePlayers()
    {
        return players.size();
    }
    
    public Object[] getAlivePlayers()
    {
        return players.toArray();
    }
    
    public void addStandardPlayers()
    {
        for (int i = 0; i < 4; i++)
        {
            players.add(new Player(i));
        }
    }
    
    public Player getPlayerById(int playerID)
    {
        return players.get(playerID).isDead() ? null : players.get(playerID);
    }
}
