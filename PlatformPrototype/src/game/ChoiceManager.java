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
    private ArrayList<Choice> choices;
    private int scene;
    private Player endangeredPlayer;
    
    public ChoiceManager(int scene)
    {
        setScene(scene);
    }
    
    public void setScene(int scene)
    {
        choices = new ArrayList<Choice>();
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
        if (choice == null || choice.getPlayer() == null) return;
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
            choices.add(choice);
            System.out.println("Player " + choice.getPlayer().getShowableID() + " chose " + choice.getChoice());
        }

    }
    
    public void timeIsUp()
    {
        compareChoices();
    }
   
    public ArrayList<Player> compareChoices()
    {
        System.out.println("========================");
        //PlayerManager.sharedManager().debug();
        //Endangered damage damage
        
        ArrayList<Player> playersWhoJustDied = new ArrayList<Player>();
        if (nonGhostHelpers() == 0 && scene != 1)
        {
            endangeredPlayer.kill();
            playersWhoJustDied.add(endangeredPlayer);
        }
        else if (nonGhostHelpers() == 1)
        {
            if (endangeredPlayer.getHP() <= 2) playersWhoJustDied.add(endangeredPlayer);
            endangeredPlayer.loseHP(2);
        }
        else if (nonGhostHelpers() == 2)
        {
            if (endangeredPlayer.getHP() <= 1) playersWhoJustDied.add(endangeredPlayer);
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
                    playersWhoJustDied.add(badLuckBrian);
                }
            }
            else
            {
                PlayerManager.sharedManager().getPlayerById(hurtByGhostID).loseHP(1);
                if (PlayerManager.sharedManager().getPlayerById(hurtByGhostID).getHP() <= 1) playersWhoJustDied.add(PlayerManager.sharedManager().getPlayerById(hurtByGhostID));
            }
        }
        
        //Helpers damage
        for (Player p : nonGhostHelpersArray())
        {
            if (p.getHP() <= 1) playersWhoJustDied.add(p);
            p.loseHP(1);
        }
        PlayerManager.sharedManager().debug();
        
        return playersWhoJustDied;
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
        return choices.size();
    }
}
