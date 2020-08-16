package graphic_Z.Keyboard_Mouse;


import android.view.KeyEvent;
import android.view.View;

import java.awt.event.KeyListener;
import java.util.LinkedList;

public class KeyboardResponse extends KeyListener {
	protected int maxKeyCodeBufferSize;
	protected LinkedList<Integer> FrapsEventQueue_keyboard;	//键盘事件队列(引用，实体在EventManager中)
	
	public KeyboardResponse(LinkedList<Integer> FrapsEventQueue, int MaxKeyCodeBufferSize)
	{
		FrapsEventQueue_keyboard = FrapsEventQueue;
		maxKeyCodeBufferSize = MaxKeyCodeBufferSize;
	}
	

	public void keyTyped(KeyEvent e)
	{
	}


	public void keyPressed(KeyEvent e)
	{
		if(FrapsEventQueue_keyboard.size() < maxKeyCodeBufferSize){
			FrapsEventQueue_keyboard.addLast(e.getKeyCode());
		}

		
//		if(FrapsEventQueue_keyboard.size() < maxKeyCodeBufferSize)
//			if(e.isShiftDown())FrapsEventQueue_keyboard.addLast(KeyEvent.VK_SHIFT);
//
//		if(FrapsEventQueue_keyboard.size() < maxKeyCodeBufferSize)
//			if(e.isControlDown())FrapsEventQueue_keyboard.addLast(KeyEvent.VK_CONTROL);
	}


	public void keyReleased(KeyEvent e)
	{
		if(FrapsEventQueue_keyboard.size() < maxKeyCodeBufferSize)
			FrapsEventQueue_keyboard.addLast(-e.getKeyCode());
		
//		if(FrapsEventQueue_keyboard.size() < maxKeyCodeBufferSize)
//			if(e.isShiftDown())FrapsEventQueue_keyboard.addLast(-KeyEvent.VK_SHIFT);
//
//		if(FrapsEventQueue_keyboard.size() < maxKeyCodeBufferSize)
//			if(e.isControlDown())FrapsEventQueue_keyboard.addLast(-KeyEvent.VK_CONTROL);
	}


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
