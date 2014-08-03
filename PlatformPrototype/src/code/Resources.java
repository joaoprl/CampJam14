/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package code;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Francesco
 */
public class Resources
{
    private static Resources resources;
    private static Map<String, Map> maps;
    private static Map<String, Object> standardMap;
    
    private Resources()
    {
       maps = new HashMap<String, Map>();
       Map<String, Object> pt_br = new HashMap<String, Object>();
       pt_br.put("player1Name", "Alegria");
       pt_br.put("player1Health", 6);
       
       pt_br.put("player2Name", "Barney");
       pt_br.put("player2Health", 5);
       
       pt_br.put("player3Name", "Peronio");
       pt_br.put("player3Health", 5);
       
       pt_br.put("player4Name", "Shima");
       pt_br.put("player4Health", 3);
       
       
       maps.put("pt_br", pt_br);
    }
    
    public static void setStandardMap(String mapName)
    {
        check();
        standardMap = maps.get(mapName);
    }
    
    public static String getStringForKey(String key)
    {
        check();
        return getObjectForKey(key, standardMap).toString();
    }
    
    public static String getStringForKey(String key, String map)
    {
        check();
        return getObjectForKey(key, maps.get(map)).toString();
    }
    
    public static int getIntForKey(String key)
    {
        check();
        return (int)getObjectForKey(key, standardMap);
    }
    
    public static int getIntForKey(String key, String map)
    {
        check();
        return (int)getObjectForKey(key, maps.get(map));
    }
    
    private static Object getObjectForKey(String key, Map map)
    {
        check();
        return map.get(key);
    }
    
    private static void check()
    {
        if (resources == null)
        {
            resources = new Resources();
        }  
    }
}
