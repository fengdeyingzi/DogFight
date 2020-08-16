package graphic_Z.Managers;


import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.xl.view.TextView;

import dogfight_Z.ContinueListener;
import graphic_Z.Common.SinglePoint;
import graphic_Z.Keyboard_Mouse.KeyboardResponse;
import graphic_Z.Keyboard_Mouse.MouseResponse;
import graphic_Z.Worlds.TDWorld;

public class EventManager
{
	private static final String TAG = "EventManager";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1459883095946993721L;
	
	public static int PCScreenCenter_X;
	public static int PCScreenCenter_Y;
	
	public static TextView mainScr;
	
	protected KeyboardResponse keyResponse;
	protected MouseResponse	   mouseResponse;
	protected int			   maxKeyBufferSize = 6;
	
	protected char fraps_buffer[][];					//帧缓冲(引用)
	
	protected	LinkedList<SinglePoint>	EventFrapsQueue_mouse;	//鼠标事件队列，存储鼠标位移坐标(x,y)
	public		LinkedList<Integer> EventFrapsQueue_keyboard;	//键盘事件队列，存储键盘扫描码
	public static Activity activity;




	protected void onCreate(@Nullable Bundle savedInstanceState) {

//		super.onCreate(savedInstanceState);
		TDWorld.eventManager = this;
		Point size = new Point();
//        ViewTool.setStatusBarLightMode(this)
//		getWindowManager().getDefaultDisplay().getSize(size);
PCScreenCenter_X = (int)(size.x / 2);
PCScreenCenter_Y = (int)(size.y /2);
//	initView(6);
	}


	public EventManager(int maxKeyBuffer_size)
	{

		EventFrapsQueue_mouse	 = new LinkedList<SinglePoint>();
		EventFrapsQueue_keyboard = new LinkedList<Integer>();

		
		maxKeyBufferSize = maxKeyBuffer_size;
		
		keyResponse		= new KeyboardResponse(EventFrapsQueue_keyboard, maxKeyBufferSize);
		mouseResponse	= new MouseResponse(EventFrapsQueue_mouse);
		mouseResponse.PCScreenCenter_X = PCScreenCenter_X;
		mouseResponse.PCScreenCenter_Y = PCScreenCenter_Y;
		
//		PCScreenCenter_X = ((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().width / 2);
//		PCScreenCenter_Y = ((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().height / 2);
		

		
//		setSize(PCScreenCenter_X * 2, PCScreenCenter_Y * 2);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//---------------------------Setup GUI----------------------------------
//		setLocation(0, 0);
//		setUndecorated(true);
		//setOpacity(0.8f);
		



//		mainScr.setLocation(0, 0);
//		mainScr.setSize(PCScreenCenter_X * 2, PCScreenCenter_Y * 2);
//		mainScr.setEditable(false);
		mainScr.setFocusable(true);
		mainScr.setText("Loading...\nPlease wait.");
//		mainScr.setFont(new Font("Consolas", Font.PLAIN, 16));
		mainScr.setBackgroundColor(0xff000000);
//		mainScr.setForeground(new Color(255, 255, 255));
		//mainScr.setBackground(new Color(255, 200, 64));
		//mainScr.setForeground(new Color(0, 0, 0));
//		addKeyListener(keyResponse);
		mainScr.addMouseMotionListener(mouseResponse);
		mainScr.addKeyListener(keyResponse);

		

		//----------------------------------------------------------------------
		
		//--------------------------Hide Cursor---------------------------------
		URL classUrl = this.getClass().getResource("");  
//		Image imageCursor = Toolkit.getDefaultToolkit().getImage(classUrl);
//		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
//		                    imageCursor,  new Point(0, 0), "cursor"));
		//----------------------------------------------------------------------
		
		//setVisible(true);
		Log.i(TAG, "setContentView");
//		activity.setContentView(mainScr);
		mainScr.setBackgroundColor(0xff202020);
	}
	
	public EventManager()
	{
		this(6);
	}
	
	public void setScrZoom(int size)
	{
		mainScr.setTextSize(size);
	}



//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		if(keyCode == KeyEvent.KEYCODE_DPAD_UP){
//			keyResponse.keyPressed(event);
//		}
//		else if(keyCode == KeyEvent.KEYCODE_DPAD_DOWN){
//			keyResponse.keyPressed(event);
//		}
//		else if(keyCode == KeyEvent.KEYCODE_DPAD_LEFT){
//			keyResponse.keyPressed(event);
//		}
//		else if(keyCode == KeyEvent.KEYCODE_DPAD_RIGHT){
//			keyResponse.keyPressed(event);
//		}
//		else if(keyCode == KeyEvent.KEYCODE_DPAD_CENTER){
//			keyResponse.keyPressed(event);
//		}
//		else if(keyCode == KeyEvent.KEYCODE_W){
//			keyResponse.keyPressed(event);
//		}
//		else if(keyCode == KeyEvent.KEYCODE_S){
//			keyResponse.keyPressed(event);
//		}
//		else if(keyCode == KeyEvent.KEYCODE_A){
//			keyResponse.keyPressed(event);
//		}
//		else if(keyCode == KeyEvent.KEYCODE_D){
//			keyResponse.keyPressed(event);
//		}
//		else if(keyCode == KeyEvent.KEYCODE_C){
//			keyResponse.keyPressed(event);
//		}
//		else if(keyCode == KeyEvent.KEYCODE_V){
//			keyResponse.keyPressed(event);
//		}
//		else if(keyCode == KeyEvent.KEYCODE_Q){
//			keyResponse.keyPressed(event);
//		}
//		else if(keyCode == KeyEvent.KEYCODE_F){
//			keyResponse.keyPressed(event);
//		}
//		else if(keyCode == KeyEvent.KEYCODE_E){
//			keyResponse.keyPressed(event);
//		}
//		else if(keyCode == KeyEvent.KEYCODE_R){
//			keyResponse.keyPressed(event);
//		}
//		else if(keyCode == KeyEvent.KEYCODE_ESCAPE){
//			keyResponse.keyPressed(event);
//		}
//		else if(keyCode == KeyEvent.KEYCODE_P){
//			keyResponse.keyPressed(event);
//		}
//		else
//		return super.onKeyDown(keyCode, event);
//		return true;
//	}
//
//	@Override
//	public boolean onKeyUp(int keyCode, KeyEvent event) {
//		if(keyCode == KeyEvent.KEYCODE_DPAD_UP){
//			keyResponse.keyReleased(event);
//		}
//		else if(keyCode == KeyEvent.KEYCODE_DPAD_DOWN){
//			keyResponse.keyReleased(event);
//		}
//		else if(keyCode == KeyEvent.KEYCODE_DPAD_LEFT){
//			keyResponse.keyReleased(event);
//		}
//		else if(keyCode == KeyEvent.KEYCODE_DPAD_RIGHT){
//			keyResponse.keyReleased(event);
//		}
//		else if(keyCode == KeyEvent.KEYCODE_DPAD_CENTER){
//			keyResponse.keyReleased(event);
//		}
//		else if(keyCode == KeyEvent.KEYCODE_W){
//			keyResponse.keyReleased(event);
//		}
//		else if(keyCode == KeyEvent.KEYCODE_S){
//			keyResponse.keyReleased(event);
//		}
//		else if(keyCode == KeyEvent.KEYCODE_A){
//			keyResponse.keyReleased(event);
//		}
//		else if(keyCode == KeyEvent.KEYCODE_D){
//			keyResponse.keyReleased(event);
//		}
//		else if(keyCode == KeyEvent.KEYCODE_C){
//			keyResponse.keyReleased(event);
//		}
//		else if(keyCode == KeyEvent.KEYCODE_V){
//			keyResponse.keyReleased(event);
//		}
//		else if(keyCode == KeyEvent.KEYCODE_Q){
//			keyResponse.keyReleased(event);
//		}
//		else if(keyCode == KeyEvent.KEYCODE_F){
//			keyResponse.keyReleased(event);
//		}
//		else if(keyCode == KeyEvent.KEYCODE_E){
//			keyResponse.keyReleased(event);
//		}
//		else if(keyCode == KeyEvent.KEYCODE_R){
//			keyResponse.keyReleased(event);
//		}
//		else if(keyCode == KeyEvent.KEYCODE_ESCAPE){
//			keyResponse.keyReleased(event);
//		}
//		else if(keyCode == KeyEvent.KEYCODE_P){
//			keyResponse.keyReleased(event);
//		}
//		else
//			return super.onKeyUp(keyCode, event);
//		return true;
//	}

	public int popAKeyOpreation()
	{
		int keyCode = -1;
		if(EventFrapsQueue_keyboard.size() > 0)
		{
			keyCode = EventFrapsQueue_keyboard.poll();
			if(keyCode == KeyEvent.KEYCODE_ESCAPE)
				System.exit(0);
		}
		
		return keyCode;
	}
	
	public SinglePoint popAMouseOpreation()
	{
		if(EventFrapsQueue_mouse.size() > 0)
		{
			SinglePoint xy = new SinglePoint(EventFrapsQueue_mouse.poll());
			xy.x -= PCScreenCenter_X;
			xy.y -= PCScreenCenter_Y;
			
			return xy;
		}
		
		return new SinglePoint(0, 0);
	}



	public void setVisible(boolean b) {
		mainScr.setVisibility(b?View.VISIBLE:View.GONE);
	}
}
