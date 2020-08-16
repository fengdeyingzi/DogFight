package dogfight_Z;

//import java.awt.event.KeyEvent;
import android.view.KeyEvent;
import android.view.View;

import java.awt.event.KeyListener;

public class ContinueListener extends KeyListener {
	protected Game myGame;
	protected Thread gameThread;
	public    boolean paused;
	private	  long pauseTime;
	
	public ContinueListener(Game gme, Thread game_thread)
	{
		pauseTime = 0;
		myGame = gme;
		gameThread = game_thread;
		paused = false;
	}
	
	@Override
	public void keyTyped(KeyEvent e)
	{
		// TODO 自动生成的方法存根
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void keyPressed(KeyEvent e)
	{
		// TODO 自动生成的方法存根
		if(e.getKeyCode() == KeyEvent.KEYCODE_P)
		{
			paused = !paused;
			
			if(paused)
			{
				pauseTime = System.currentTimeMillis() / 1000;
				gameThread.suspend();
				myGame.bgmThread.suspend();
			}
			else
			{
				pauseTime = System.currentTimeMillis() / 1000 - pauseTime;
				gameThread.resume();
				myGame.gameStopTime += pauseTime;
				myGame.bgmThread.resume();
			}
		}
		else if(e.getKeyCode() == KeyEvent.KEYCODE_ESCAPE)
			System.exit(0);
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		// TODO 自动生成的方法存根

	}

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN) {
			if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
				this.keyPressed(event);
			} else if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
				this.keyPressed(event);
			} else if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
				this.keyPressed(event);
			} else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
				this.keyPressed(event);
			} else if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
				this.keyPressed(event);
			} else if (keyCode == KeyEvent.KEYCODE_W) {
				this.keyPressed(event);
			} else if (keyCode == KeyEvent.KEYCODE_S) {
				this.keyPressed(event);
			} else if (keyCode == KeyEvent.KEYCODE_A) {
				this.keyPressed(event);
			} else if (keyCode == KeyEvent.KEYCODE_D) {
				this.keyPressed(event);
			} else if (keyCode == KeyEvent.KEYCODE_C) {
				this.keyPressed(event);
			} else if (keyCode == KeyEvent.KEYCODE_V) {
				this.keyPressed(event);
			} else if (keyCode == KeyEvent.KEYCODE_Q) {
				this.keyPressed(event);
			} else if (keyCode == KeyEvent.KEYCODE_F) {
				this.keyPressed(event);
			} else if (keyCode == KeyEvent.KEYCODE_E) {
				this.keyPressed(event);
			} else if (keyCode == KeyEvent.KEYCODE_R) {
				this.keyPressed(event);
			} else if (keyCode == KeyEvent.KEYCODE_ESCAPE) {
				this.keyPressed(event);
			} else if (keyCode == KeyEvent.KEYCODE_P) {
				this.keyPressed(event);
			}
		} else if (event.getAction() == KeyEvent.ACTION_UP) {
			if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
				this.keyReleased(event);
			} else if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
				this.keyReleased(event);
			} else if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
				this.keyReleased(event);
			} else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
				this.keyReleased(event);
			} else if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
				this.keyReleased(event);
			} else if (keyCode == KeyEvent.KEYCODE_W) {
				this.keyReleased(event);
			} else if (keyCode == KeyEvent.KEYCODE_S) {
				this.keyReleased(event);
			} else if (keyCode == KeyEvent.KEYCODE_A) {
				this.keyReleased(event);
			} else if (keyCode == KeyEvent.KEYCODE_D) {
				this.keyReleased(event);
			} else if (keyCode == KeyEvent.KEYCODE_C) {
				this.keyReleased(event);
			} else if (keyCode == KeyEvent.KEYCODE_V) {
				this.keyReleased(event);
			} else if (keyCode == KeyEvent.KEYCODE_Q) {
				this.keyReleased(event);
			} else if (keyCode == KeyEvent.KEYCODE_F) {
				this.keyReleased(event);
			} else if (keyCode == KeyEvent.KEYCODE_E) {
				this.keyReleased(event);
			} else if (keyCode == KeyEvent.KEYCODE_R) {
				this.keyReleased(event);
			} else if (keyCode == KeyEvent.KEYCODE_ESCAPE) {
				this.keyReleased(event);
			} else if (keyCode == KeyEvent.KEYCODE_P) {
				this.keyReleased(event);
			}
		}

		return true;
	}
}
