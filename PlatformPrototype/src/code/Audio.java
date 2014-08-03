package code;

import java.util.ArrayList;

public class Audio {
	
	static ArrayList<AudioClip> audioList = new ArrayList<AudioClip>();		
	
	// Add update in GameStateManager
	
	private static void runAudioClip(String file, int id, Boolean loop){
		String path;
		path = "C:\\Users\\João\\workspace\\CampJam14\\PlatformPrototype\\";
		
		
		AudioClip audioClip = new AudioClip(path + file, id, loop);
		
		audioList.add(audioClip);
		
        new Thread(audioClip).start();
	}
	
	public static void play(String file, int id)
	{
		runAudioClip(file, id, false);
	}
	
	public static void loop(String file, int id)
	{
		runAudioClip(file, id, true);
	}
	
	public static void fadeout(int id)
	{		
		for(AudioClip audioClip : audioList)
			if(audioClip.getID() == id) audioClip.fadeout();
	}
	
	public static void update(long wait)
	{
		for(AudioClip audioClip : audioList)
			audioClip.update(wait);	
	}
}
