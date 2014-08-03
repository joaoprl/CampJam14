package code;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class AudioClip implements Runnable{
	private Clip clip;
	private String file;
	private int id;
	private FloatControl volume;
	private Boolean fadeOut;
	private long waited;
	private Boolean loop;
	
	public AudioClip(String file, int id, Boolean loop)
	{
		this.file = file;
		this.id = id;
		this.loop = loop;
		this.fadeOut = false;
	}

	@Override
	public void run() {
		try
        {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(file)));
			
			if(loop) clip.loop(Clip.LOOP_CONTINUOUSLY);
			else clip.start();
			
			volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        }
        catch (Exception exc)
        {
            exc.printStackTrace(System.out);
        }
		
	}
	
	public void fadeout()
	{
		this.fadeOut = true;
	}
	
	public void update(long wait)
	{
		if(fadeOut)
		{
			waited += wait;
			if(waited > 1000)
			{
				volume.shift(volume.getValue(), volume.getValue() - 5, 1000000);
				waited = 0;
			}
			if(volume.getValue() <= volume.getMinimum()) stop();
		}
		
	}
	
	private void stop()
	{
		clip.stop();
		clip.flush();
		Thread.currentThread().interrupt();
	}
	
	public int getID()
	{
		return this.id;
	}

}
