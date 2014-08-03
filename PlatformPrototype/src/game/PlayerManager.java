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
        int playerCount = 0;
        for (Player player : players)
        {
            playerCount += player.isDead() ? 0 : 1;
        }
        return playerCount;
    }
    
    public ArrayList<Player> getAlivePlayers()
    {
        ArrayList<Player> playerArray = new ArrayList<Player>();
        for (Player player : players)
        {
            if (!player.isDead())
            {
                playerArray.add(player);
            }
        }
        return playerArray;
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
    
    public void debug()
    {
        for (Player player : players)
        {
            player.debug();
        }
    }
}
