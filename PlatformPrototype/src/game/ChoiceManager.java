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
public class ChoiceManager
{
    private Choice choices[];
    
    public ChoiceManager()
    {
        choices = new Choice[PlayerManager.sharedManager().alivePlayers()];
    }
    
    public void addChoice(Choice choice)
    {
        choices[choices.length] = choice;
    }
    
    //TODO: Implement time is up
    /*public void timeIsUp()
    {
        int players = PlayerManager.sharedManager().alivePlayers();
        for (int i = 0; i < players; i++)
        {
            if (!chose[i])
            {
                choices[i] = new Choice()
                chose[i] = 
            }
        }
    }*/
    
    public Player getFirstPlayerWhoChoseYes()
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
    
    
}
