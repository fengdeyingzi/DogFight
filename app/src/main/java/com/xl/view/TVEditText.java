package com.xl.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.RequiresApi;

/**
 * @author LQR
 * @time 2020/3/31
 * @desc 解决Android8.0 EditText获取焦点后，无法转换焦点问题
 */
@SuppressLint("AppCompatCustomView")
public class TVEditText extends EditText {
    public TVEditText(Context context) {
        super(context);
    }

    public TVEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TVEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TVEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (isDirectKeyCode(keyCode)) {
            int direction = FOCUS_DOWN;
            switch (keyCode) {
                case KeyEvent.KEYCODE_DPAD_UP:
                    direction = FOCUS_UP;
                    break;
                case KeyEvent.KEYCODE_DPAD_DOWN:
                    direction = FOCUS_DOWN;
                    break;
                case KeyEvent.KEYCODE_DPAD_LEFT:
                    direction = FOCUS_LEFT;
                    break;
                case KeyEvent.KEYCODE_DPAD_RIGHT:
                    direction = FOCUS_RIGHT;
                    break;
            }
//            View nextFocus = FocusFinder.getInstance().findNextFocus((ViewGroup) getParent(), this, direction);
//
//            if (nextFocus != null) {
//                nextFocus.requestFocus();
//                return true;
//            }
if(direction == FOCUS_DOWN || direction==FOCUS_RIGHT){
    if(getSelectionStart()==getText().length()){
        View view = getParent().focusSearch(this, direction);
        if(view!=null){
            view.requestFocus();
            return true;
        }
    }
}
if(direction == FOCUS_LEFT || direction == FOCUS_UP){
    if(getSelectionStart()==0){
        View view = getParent().focusSearch(this, direction);
        if(view!=null){
            view.requestFocus();
            return true;
        }
    }
}





        }
        return super.onKeyDown(keyCode, event);
    }

    private boolean isDirectKeyCode(int keyCode) {
        return keyCode == KeyEvent.KEYCODE_DPAD_UP
                || keyCode == KeyEvent.KEYCODE_DPAD_DOWN
                || keyCode == KeyEvent.KEYCODE_DPAD_LEFT
                || keyCode == KeyEvent.KEYCODE_DPAD_RIGHT;
    }
}

