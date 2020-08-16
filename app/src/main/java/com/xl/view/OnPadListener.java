package com.xl.view;

public interface OnPadListener
{
	public void onKeyDown(int keycode);
	public void onKeyRepeated(int keycode); //重复按下
	public void onKeyUp(int keycode);
}

