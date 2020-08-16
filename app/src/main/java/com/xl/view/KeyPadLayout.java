package com.xl.view;

import android.view.View;
import android.widget.FrameLayout;

/*
用于管理虚拟键盘切换的布局

*/

public class KeyPadLayout extends FrameLayout
{
    public KeyPadLayout(android.content.Context context) {
        super(context);
        initView();
    }

    public KeyPadLayout(android.content.Context context, android.util.AttributeSet attrs) {
        super(context,attrs);
        initView();
    }

    public KeyPadLayout(android.content.Context context, android.util.AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void setOnPadListener(OnPadListener listener)
    {
        for(int i=0;i<getChildCount();i++){
            View view = getChildAt(i);
            if(view instanceof GamePadView){
                ((GamePadView)view).setOnPadListener(listener);
            }
            if(view instanceof GamePadViewAll2){
                ((GamePadViewAll2)view).setOnPadListener(listener);
            }
            if(view instanceof GamePadViewAll){
                ((GamePadViewAll)view).setOnPadListener(listener);
            }
        }
    }
    
    private void initView(){
        
        
        addView(new View(getContext()));
        GamePadView padView8 = new GamePadView(getContext());
        padView8.setPad8(true);
        padView8.setUseMultiKey(true);
        padView8.setUseTimer(false);
        addView(padView8);
        GamePadView padView4 = new GamePadView(getContext());
        padView4.setPad8(false);
        padView4.setUseMultiKey(false);
        padView4.setUseTimer(false);
        addView(padView4);
        addView(new GamePadViewAll(getContext()));
        addView(new GamePadViewAll2(getContext()));


		switchKeyPad(1);
    }
    
    
    @Override
    public void addView(View child)
    {
        
        //检测是否有View在显示
        int size = getChildCount();
        for(int i=0;i<size;i++){
            if(getChildAt(i).getVisibility() == View.VISIBLE){
                getChildAt(i).setVisibility(View.GONE);
            }
        }
        super.addView(child);
    }
    
	public void switchKeyPad(int num){
		int size = getChildCount();
        int current=0;
        for(int i=0;i<size;i++)
        {
            if(getChildAt(i).getVisibility() == View.VISIBLE){
                current=i; 
                getChildAt(i).setVisibility(View.GONE);
                break;
            }
        }
        current++;
        if(current>=size)current=0;
        getChildAt(num).setVisibility(View.VISIBLE);
		
	}
    
    public void switchKeypad(){
        int size = getChildCount();
        int current=0;
        for(int i=0;i<size;i++)
        {
            if(getChildAt(i).getVisibility() == View.VISIBLE){
                current=i; 
                getChildAt(i).setVisibility(View.GONE);
                break;
            }
        }
        current++;
        if(current>=size)current=0;
        getChildAt(current).setVisibility(View.VISIBLE);
    }

    public int getCurrent(){
        int size = getChildCount();
        int current=0;
        for(int i=0;i<size;i++)
        {
            if(getChildAt(i).getVisibility() == View.VISIBLE){
                current=i;
                return i;
            }
        }
        return 0;
    }
    
    public void setAlpha(int alpha){
        for(int i=0;i<getChildCount();i++){
            
            View view = getChildAt(i);
            if(view instanceof GamePadView)
            {
                ((GamePadView)view).setAlpha(alpha);
            }
            if(view instanceof GamePadViewAll2){
                ((GamePadViewAll2)view).setAlpha(alpha);
            }
        }
    }
    
    
}
