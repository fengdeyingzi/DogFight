package graphic_Z.Keyboard_Mouse;

//import java.awt.AWTException;
//import java.awt.Robot;
import android.view.MotionEvent;
import android.view.View;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;

import graphic_Z.Common.SinglePoint;

public class MouseResponse implements MouseMotionListener
{
//	private Robot rbt;
	public   int PCScreenCenter_X;
	public   int PCScreenCenter_Y;
	private int flag = 0;
	protected LinkedList<SinglePoint> FrapsEventQueue_mouse;	//鼠标事件队列(引用，实体在EventManager中)
	public MouseResponse(LinkedList<SinglePoint> FrapsEventQueue)
	{
//		try
//		{
//			rbt = new Robot();
//		} catch (AWTException e)
//		{e.printStackTrace();}
		
		FrapsEventQueue_mouse = FrapsEventQueue;
		
//		PCScreenCenter_X = ((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().width / 2);
//		PCScreenCenter_Y = ((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().height / 2);
		
//		rbt.mouseMove(PCScreenCenter_X, PCScreenCenter_Y);
	}
	@Override
	public void mouseDragged(MouseEvent e)
	{
		++flag;
		if(FrapsEventQueue_mouse.size() < 1)
			FrapsEventQueue_mouse.addLast(new SinglePoint(e.getX(), e.getY()));
		
//		if(flag == 4)
//		{
//			rbt.mouseMove(PCScreenCenter_X, PCScreenCenter_Y);
//			flag = 0;
//		}
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		++flag;
		if(FrapsEventQueue_mouse.size() < 1)
			FrapsEventQueue_mouse.addLast(new SinglePoint(e.getX(), e.getY()));
		
//		if(flag == 4)
//		{
//			rbt.mouseMove(PCScreenCenter_X, PCScreenCenter_Y);
//			flag = 0;
//		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		MouseEvent e = new MouseEvent();
		e.setX((int)event.getX());
		e.setY((int)event.getY());
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			mouseDragged(e);
		}
		else if(event.getAction() == MotionEvent.ACTION_MOVE){
			mouseDragged(e);

		}
		else if(event.getAction() == MotionEvent.ACTION_UP){
			mouseDragged(e);
		}
		return true;
	}
}
