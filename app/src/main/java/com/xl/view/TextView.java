package com.xl.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import graphic_Z.Keyboard_Mouse.MouseResponse;

public class TextView extends View {
    private static final String TAG = "TextView";
    String text;
    Paint paint = new Paint();
    ArrayList<MouseListener> list_mouse;
    ArrayList<MouseListener> list_mousewhell;
    ArrayList<MouseResponse> list_mousenotion;
    ArrayList<KeyListener> list_keylistener;

    public TextView(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        list_keylistener = new ArrayList<KeyListener>();
        list_mouse = new ArrayList<>();
        list_mousenotion = new ArrayList<>();
        list_mousewhell = new ArrayList<>();
        text = "";
        paint = new Paint();
        paint.setTextSize(9);
        paint.setTypeface(Typeface.MONOSPACE);
        paint.setColor(0xfff0f0f0);
        postInvalidate();
    }

    public void setText(String text) {
        this.text = text;
        postInvalidate();
    }

    public void setTextSize(float size) {
        paint.setTextSize(size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        String list_text[] = text.split("\n");
        float ix = 0;
        float iy = paint.getTextSize();


//        canvas.drawColor(0xff000000);
        for (int i = 0; i < list_text.length; i++) {
            canvas.drawText(list_text[i], ix, iy, paint);
            iy += paint.getTextSize() * 1.32;
        }

        super.onDraw(canvas);

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.i(TAG, "onKeyDown: "+keyCode + " size="+list_keylistener.size());
        for (int i = 0; i < list_keylistener.size(); i++) {
            KeyListener keyResponse = list_keylistener.get(i);
            if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
                keyResponse.keyPressed(event);
            } else if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
                keyResponse.keyPressed(event);
            } else if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
                keyResponse.keyPressed(event);
            } else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
                keyResponse.keyPressed(event);
            } else if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
                keyResponse.keyPressed(event);
            } else if (keyCode == KeyEvent.KEYCODE_W) {
                keyResponse.keyPressed(event);
            } else if (keyCode == KeyEvent.KEYCODE_S) {
                keyResponse.keyPressed(event);
            } else if (keyCode == KeyEvent.KEYCODE_A) {
                keyResponse.keyPressed(event);
            } else if (keyCode == KeyEvent.KEYCODE_D) {
                keyResponse.keyPressed(event);
            } else if (keyCode == KeyEvent.KEYCODE_C) {
                keyResponse.keyPressed(event);
            } else if (keyCode == KeyEvent.KEYCODE_V) {
                keyResponse.keyPressed(event);
            } else if (keyCode == KeyEvent.KEYCODE_Q) {
                keyResponse.keyPressed(event);
            } else if (keyCode == KeyEvent.KEYCODE_F) {
                keyResponse.keyPressed(event);
            } else if (keyCode == KeyEvent.KEYCODE_E) {
                keyResponse.keyPressed(event);
            } else if (keyCode == KeyEvent.KEYCODE_R) {
                keyResponse.keyPressed(event);
            } else if (keyCode == KeyEvent.KEYCODE_ESCAPE) {
                keyResponse.keyPressed(event);
            } else if (keyCode == KeyEvent.KEYCODE_P) {
                keyResponse.keyPressed(event);
            }

        }
        return true;
//        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.i(TAG, "onKeyUp: "+keyCode+" size="+list_keylistener.size());
        for (int i = 0; i < list_keylistener.size(); i++) {
            KeyListener keyResponse = list_keylistener.get(i);
            if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
                keyResponse.keyReleased(event);
            } else if (keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
                keyResponse.keyReleased(event);
            } else if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
                keyResponse.keyReleased(event);
            } else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
                keyResponse.keyReleased(event);
            } else if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
                keyResponse.keyReleased(event);
            } else if (keyCode == KeyEvent.KEYCODE_W) {
                keyResponse.keyReleased(event);
            } else if (keyCode == KeyEvent.KEYCODE_S) {
                keyResponse.keyReleased(event);
            } else if (keyCode == KeyEvent.KEYCODE_A) {
                keyResponse.keyReleased(event);
            } else if (keyCode == KeyEvent.KEYCODE_D) {
                keyResponse.keyReleased(event);
            } else if (keyCode == KeyEvent.KEYCODE_C) {
                keyResponse.keyReleased(event);
            } else if (keyCode == KeyEvent.KEYCODE_V) {
                keyResponse.keyReleased(event);
            } else if (keyCode == KeyEvent.KEYCODE_Q) {
                keyResponse.keyReleased(event);
            } else if (keyCode == KeyEvent.KEYCODE_F) {
                keyResponse.keyReleased(event);
            } else if (keyCode == KeyEvent.KEYCODE_E) {
                keyResponse.keyReleased(event);
            } else if (keyCode == KeyEvent.KEYCODE_R) {
                keyResponse.keyReleased(event);
            } else if (keyCode == KeyEvent.KEYCODE_ESCAPE) {
                keyResponse.keyReleased(event);
            } else if (keyCode == KeyEvent.KEYCODE_P) {
                keyResponse.keyReleased(event);
            }
        }
        return true;
    }

    /**
     * Implement this method to handle generic motion events.
     * 实现该方法来处理一般的MotionEvent；
     * 一般的motion events 描述，操纵杆的动作，鼠标honver、滚轮等
     *
     * @param event The generic motion event being processed.
     * @return True if the event was handled, false otherwise.
     */

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
//The input source is a pointing device associated with a display.
//输入源为可显示的指针设备，如：mouse pointing device(鼠标指针),stylus pointing device(尖笔设备)
        if (0 != (event.getSource() & InputDevice.SOURCE_CLASS_POINTER)) {
            switch (event.getAction()) {
                // process the scroll wheel movement...处理滚轮事件
                case MotionEvent.ACTION_SCROLL:
                    //获得垂直坐标上的滚动方向,也就是滚轮向下滚
                    if (event.getAxisValue(MotionEvent.AXIS_VSCROLL) < 0.0f) {
                        Log.i(TAG, "down");
                    }
                    //获得垂直坐标上的滚动方向,也就是滚轮向上滚
                    else {
                        Log.i(TAG, "up");
                    }
                    return true;
            }
        }
        return super.onGenericMotionEvent(event);
    }

    long time_down = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent: "+event.getAction()+" size = "+list_mouse.size());
        for(int i=0;i<list_mouse.size();i++){
            MouseListener listener = list_mouse.get(i);
            MouseEvent e = new MouseEvent();
            e.setX((int)event.getX());
            e.setY((int)event.getY());
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                time_down = System.currentTimeMillis();
                listener.mousePressed(e);
            }
            else if(event.getAction() == MotionEvent.ACTION_MOVE){

            }
            else if(event.getAction() == MotionEvent.ACTION_UP){
                time_down = System.currentTimeMillis();
                listener.mouseReleased(e);
                listener.mouseClicked(e);

            }
            else if(event.getAction() == MotionEvent.ACTION_HOVER_ENTER){

            }
        }
        for(int i=0;i<list_mousenotion.size();i++){
            MouseResponse mouseResponse = list_mousenotion.get(i);
            mouseResponse.onTouch(this, event);
        }
        for(int i=0;i<list_mousewhell.size();i++){
            MouseListener listener = list_mousewhell.get(i);
            MouseEvent e = new MouseEvent();
            e.setX((int)event.getX());
            e.setY((int)event.getY());
            if(event.getAction() == MotionEvent.ACTION_DOWN){
                time_down = System.currentTimeMillis();
                listener.mousePressed(e);
            }
            else if(event.getAction() == MotionEvent.ACTION_MOVE){

            }
            else if(event.getAction() == MotionEvent.ACTION_UP){
                time_down = System.currentTimeMillis();
                listener.mouseReleased(e);
                listener.mouseClicked(e);

            }
            else if(event.getAction() == MotionEvent.ACTION_HOVER_ENTER){

            }
        }

        return true;
    }

    public void addKeyListener(KeyListener continueListener) {
        list_keylistener.add(continueListener);
    }

    //添加鼠标滚轮监听器
    public void addMouseWheelListener(MouseListener mouse) {
        list_mousewhell.add(mouse);
    }

    //添加鼠标事件
    public void addMouseListener(MouseListener mouse) {
        list_mouse.add(mouse);
    }

    //添加鼠标运动监听器
    public void addMouseMotionListener(MouseResponse mouseResponse) {
        list_mousenotion.add(mouseResponse);
    }
}