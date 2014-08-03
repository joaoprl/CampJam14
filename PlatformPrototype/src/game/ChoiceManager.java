/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

import java.util.ArrayList;
import game.PlayerManager;

/**
 *
 * @author Francesco
 */
public class ChoiceManager
{
    private Choice choices[];
    private int scene;
    private Player endangeredPlayer;
    
    public ChoiceManager(int scene)
    {
        setScene(scene);
    }
    
    public void setScene(int scene)
    {
        choices = new Choice[PlayerManager.sharedManager().alivePlayers()];
        this.scene = scene;
        int endangeredPlayerID = -1;
        switch (scene)
        {
            case 1:
                endangeredPlayerID = 2; //Peronio (Ghost)
                break;
            case 2:
                endangeredPlayerID = 3; //Shima
                break;
            case 3:
                endangeredPlayerID = 1; //Barney
                break;
            case 4:
                endangeredPlayerID = 0; //Alegria
                break;
            case 5:
                endangeredPlayerID = 2;
                break;
        }
        endangeredPlayer = PlayerManager.sharedManager().getPlayerById(endangeredPlayerID);
    }
    
    public void addChoice(Choice choice)
    {
        if (choice.getPlayer() != endangeredPlayer)
        {
            for (Choice c : choices)
            {
                if (c.getPlayer() == choice.getPlayer())
                {
                    //player already chose
                    return;
                }
            }
            choices[choices.length] = choice;
            System.out.println("Player " + choice.getPlayer().getShowableID() + " chose " + choice.getChoice());
        }

    }
    
    public void timeIsUp()
    {
        compareChoices();
    }
   
    public void compareChoices()
    {
        //Endangered damage damage
        if (nonGhostHelpers() == 0)
        {
            endangeredPlayer.kill();
        }
        else if (nonGhostHelpers() == 1)
        {
            endangeredPlayer.loseHP(2);
        }
        else if (nonGhostHelpers() == 2)
        {
            endangeredPlayer.loseHP(1);
        }
        
        //Ghost damage
        if(ghostDidHelp())
        {
            int hurtByGhostID = -1;
            switch (scene)
            {
            case 1:
                hurtByGhostID = -1; //Nobody
                break;
            case 2:
                hurtByGhostID = 0; //Alegria
                break;
            case 3:
                hurtByGhostID = 3; //Shima
                break;
            case 4:
                hurtByGhostID = 1; //Barney
                break;
            case 5:
                hurtByGhostID = -1;
                break;
            }
            
            if (scene == 5)
            {
                Player badLuckBrian = getFirstPlayerWhoChoseYes();
                if (badLuckBrian != null)
                {
                    badLuckBrian.kill();
                }
            }
            else
            {
                PlayerManager.sharedManager().getPlayerById(hurtByGhostID).loseHP(1);               
            }
        }
        
        //Helpers damage
        for (Player p : nonGhostHelpersArray())
        {
            //TODO: check
            if (!p.isGhost())
            {
                p.loseHP(1);
            }
        }        
    }
    
    private Player getFirstPlayerWhoChoseYes()
    {        
        for (Choice choice : choices)
        {
            if (choice.getChoice())
            {
                return choice.getPlayer();
            }
        }
        return null;
    }
    
    private boolean ghostDidHelp()
    {
        for (Choice choice : choices)
        {
            if (choice.getPlayer().isGhost() && choice.getChoice())
            {
                return true;
            }
        }
        return false;
    }
    
    private int nonGhostHelpers()
    {
        int nonGhostHelpers = 0;
        for (Choice choice : choices)
        {
            if (!choice.getPlayer().isGhost() && choice.getChoice())
            {
                nonGhostHelpers++;
            }
        }
        return nonGhostHelpers;
    }
    
    private ArrayList<Player> nonGhostHelpersArray()
    {
        ArrayList<Player> nonGhostHelpers = new ArrayList<Player>();
        for (Choice choice : choices)
        {
            if (!choice.getPlayer().isGhost() && choice.getChoice())
            {
                nonGhostHelpers.add(choice.getPlayer());
            }
        }
        return nonGhostHelpers;
    }
    
    public int choiceCount()
    {
        return choices.length;
    }
}
