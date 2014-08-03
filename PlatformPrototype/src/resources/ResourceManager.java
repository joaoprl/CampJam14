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
import resources.Sprite;

public class ResourceManager {
    private static final ResourceManager ResourceManagerSingleton = new ResourceManager();
    private final HashMap<String,Sprite> sprites;
    private final HashMap<String,AudioClip> clips;
    
    private ResourceManager(){
        System.out.println("ResourceManager Constructor Called!");
        
        sprites = new HashMap<>();
        clips   = new HashMap<>();
        
        String path = null;
        path = "C:\\Users\\João\\workspace\\CampJam14\\PlatformPrototype\\";
        
        loadSprite(path + "assets\\backgroundTimeBar.png", 100, 10, "background_time_bar");
        loadSprite(path + "assets\\loadingTimeBar.png", 94, 8, "loading_time_bar");
        
        loadSprite(path + "Sheets\\background_menu.png", 1600, 1200, "background_menu");      
        loadSprite(path + "Sheets\\Prologue\\prologue01.png", 1600, 600, "prologue01");
        loadSprite(path + "Sheets\\Prologue\\prologue02.png", 1600, 600, "prologue02");
        loadSprite(path + "Sheets\\Prologue\\prologue03.png", 1600, 600, "prologue03");
        loadSprite(path + "Sheets\\Prologue\\prologue04.png", 1600, 600, "prologue04");
        loadSprite(path + "Sheets\\Prologue\\prologue05.png", 1600, 600, "prologue05");
        loadSprite(path + "Sheets\\Prologue\\prologue06.png", 1600, 600, "prologue06");
        loadSprite(path + "Sheets\\Prologue\\prologue07.png", 1600, 600, "prologue07");
        loadSprite(path + "Sheets\\Prologue\\prologue08.png", 1600, 600, "prologue08");
        loadSprite(path + "Sheets\\Prologue\\prologue09.png", 1600, 600, "prologue09");
        loadSprite(path + "Sheets\\Prologue\\prologue10.png", 1600, 600, "prologue10");

        loadSprite(path + "assets\\personagemA.png", 300, 700, "personagemAThumbnail");
        loadSprite(path + "assets\\personagemB.png", 300, 700, "personagemBThumbnail");
        loadSprite(path + "assets\\personagemC.png", 300, 700, "personagemCThumbnail");
        loadSprite(path + "assets\\personagemD.png", 300, 700, "personagemDThumbnail");

        loadSprite(path + "Sheets\\scenes\\Scene1_1.png", 1600,600,"scene1_1");
        loadSprite(path + "Sheets\\scenes\\Scene2_1.png", 1600,600,"scene2_1");
        loadSprite(path + "Sheets\\scenes\\Scene3_1.png", 1600,600,"scene3_1");
        loadSprite(path + "Sheets\\scenes\\Scene4_1.png", 1600,600,"scene4_1");
        loadSprite(path + "Sheets\\scenes\\Scene5_1.png", 1600,600,"scene5_1");
        
        loadSprite(path + "assets\\A_3.png", 400, 600, "A_3");
        loadSprite(path + "assets\\B_4.png", 400, 600, "B_4");
        loadSprite(path + "assets\\C_2.png", 400, 600, "C_2");
        loadSprite(path + "assets\\D_1.png", 400, 600, "D_1");
        
        
        loadSprite(path + "assets\\ok.png", 60, 60, "ok");
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