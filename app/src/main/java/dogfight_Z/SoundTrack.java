package dogfight_Z;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

//import javax.swing.JOptionPane;

import jmp123.demo.MiniPlayer;
import jmp123.output.Audio;

public class SoundTrack implements Runnable
{
	private ArrayList<String> soundTrack;
	private int currentPlayingIndex;
	
	public SoundTrack(String sountrack_info_file)
	{
		soundTrack = new ArrayList<String>();
		soundTrack.clear();
		currentPlayingIndex = 0;
		
		try(InputStreamReader reader = new InputStreamReader(new FileInputStream(sountrack_info_file), "GBK"))
		{
			String tmp;
			int ch;
			
			while(true)
			{
				tmp = "";
				while((ch = reader.read()) != '\n' && ch != -1)
					tmp = tmp + (char)ch;
				
				if(!tmp.equals(""))
					soundTrack.add(tmp);
				if(ch == -1) break;
			}
			
		}	catch(IOException exc){}
	}
	
	public void switchNext()
	{
		if(currentPlayingIndex < soundTrack.size()-1)
			++currentPlayingIndex;
		else currentPlayingIndex = 0;
	}
	
	public void switchPrevious()
	{
		if(currentPlayingIndex > 0)
			--currentPlayingIndex;
		else currentPlayingIndex = soundTrack.size()-1;
	}
	
	@Override
	public void run()
	{
		if(soundTrack.size() > 0)
		{
			MiniPlayer player = new MiniPlayer(new Audio());
			
			try
			{
				while(true)
				{
					player.open(soundTrack.get(currentPlayingIndex));
					player.run();
					switchNext();
				}
				
			}
			catch (IOException e)
			{
//xlerror				JOptionPane.showMessageDialog(null, e.toString());
				
				e.printStackTrace();
			}
		}
	}

}
