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
    private static Map<String, Map<String, Object>> maps;
    private static Map<String, Object> standardMap;
    
    private Resources()
    {
       maps = new HashMap<String, Map<String, Object>>();
       Map<String, Object> constants = new HashMap<String, Object>();
       constants.put("player1Name", "Alegria");
       constants.put("player1Health", 6);
       
       constants.put("player2Name", "Barney");
       constants.put("player2Health", 5);
       
       constants.put("player3Name", "Peronio");
       constants.put("player3Health", 5);
       
       constants.put("player4Name", "Shima");
       constants.put("player4Health", 3);
      
       // Panel dimensions
       constants.put("width", 1024);
       constants.put("height", 768);
       constants.put("scale", 1);
       
       //Main loop
       constants.put("FPS", 60);
       constants.put("targetTime", 1000/(int)constants.get("FPS"));

       maps.put("constants", constants);
       
       Map<String, Object> pt_br = new HashMap<String, Object>();
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
            System.out.println("created resources");
            resources = new Resources();
        }  
    }
}
