package resources;

/**
 *
 * @author Peronio
 */
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class ResourceManager {
    private static final ResourceManager ResourceManagerSingleton = new ResourceManager();
    private final HashMap<String,Sprite> sprites;
    private final HashMap<String,AudioClip> clips;
    
    private ResourceManager(){
        System.out.println("ResourceManager Constructor Called!");
        
        sprites = new HashMap<>();
        clips   = new HashMap<>();
        
        loadSprite("assets\\earthIsoTile.png", 32, 32, "tile_earth_default");
        loadSprite("assets\\backgroundTimeBar.png", 100, 10, "background_time_bar");
        loadSprite("assets\\loadingTimeBar.png", 94, 8, "loading_time_bar");
        loadSprite("assets\\background_menu.png", 1600, 1200, "background_menu");

        //loadSprite("C:\\Users\\João\\workspace\\CampJam14\\PlatformPrototype\\assets\\earthIsoTile.png", 32, 32, "tile_earth_default");
        //loadSprite("C:\\Users\\João\\workspace\\CampJam14\\PlatformPrototype\\assets\\backgroundTimeBar.png", 100, 10, "background_time_bar");
        //loadSprite("C:\\Users\\João\\workspace\\CampJam14\\PlatformPrototype\\assets\\loadingTimeBar.png", 94, 8, "loading_time_bar");
        //loadSprite("C:\\Users\\João\\workspace\\CampJam14\\PlatformPrototype\\assets\\Overlay.png", 1024, 768, "overlay");

    }
    
    public static ResourceManager getInstance(){
        return ResourceManagerSingleton;
    }
    
    private void loadSprite(String url, int spriteWidth, int spriteHeight, String... ids){
        try {
            BufferedImage image = ImageIO.read(new File(url));
            for(int i = 0; i < ids.length; i++){
                sprites.put(ids[i], new Sprite(image.getSubimage(0, i*spriteHeight, image.getWidth(), spriteHeight), spriteWidth, spriteHeight));
            }
        } catch (Exception e){
            System.out.println("Problem loading sprite! " + e);
        }    
    }
    
    public Sprite getSprite(String id){
        return sprites.get(id);
    }
    
    private void loadClip(String url, String id){
        try{
            AudioClip clip = Applet.newAudioClip(new URL(url));
            clips.put(id, clip);
        } catch(Exception e){
            //eita
            System.out.println(e);
        }
    }
    
    public AudioClip getClip(String id){
        return clips.get(id);
    }
}