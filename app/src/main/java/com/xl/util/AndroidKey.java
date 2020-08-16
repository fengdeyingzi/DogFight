package com.xl.util;

import android.view.KeyEvent;

import com.xl.view.GamePadView;

import java.util.HashMap;

public class AndroidKey {


    //获取安卓key
    public static int getAndroidKey(int keycode){
        HashMap<Integer,Integer> map_key = new HashMap<>();
        map_key.put(0, KeyEvent.KEYCODE_0);
        map_key.put(1,KeyEvent.KEYCODE_1);
        map_key.put(2,KeyEvent.KEYCODE_2);
        map_key.put(3,KeyEvent.KEYCODE_3);
        map_key.put(4,KeyEvent.KEYCODE_4);
        map_key.put(5,KeyEvent.KEYCODE_5);
        map_key.put(6,KeyEvent.KEYCODE_6);
        map_key.put(7,KeyEvent.KEYCODE_7);
        map_key.put(8,KeyEvent.KEYCODE_8);
        map_key.put(9,KeyEvent.KEYCODE_9);
        map_key.put(10,KeyEvent.KEYCODE_STAR);
        map_key.put(11,KeyEvent.KEYCODE_POUND);
//        map_key.put(12,KeyEvent.KEYCODE_DPAD_UP);
//        map_key.put(13,KeyEvent.KEYCODE_DPAD_DOWN);
//        map_key.put(14,KeyEvent.KEYCODE_DPAD_LEFT);
//        map_key.put(15,KeyEvent.KEYCODE_DPAD_RIGHT);
        map_key.put(12,KeyEvent.KEYCODE_W);
        map_key.put(13,KeyEvent.KEYCODE_S);
        map_key.put(14,KeyEvent.KEYCODE_A);
        map_key.put(15,KeyEvent.KEYCODE_D);

        map_key.put(16,KeyEvent.KEYCODE_ESCAPE);
        map_key.put(17,KeyEvent.KEYCODE_MENU);
        map_key.put(18,KeyEvent.KEYCODE_BACK);
        map_key.put(19,KeyEvent.KEYCODE_CALL);
        map_key.put(20,KeyEvent.KEYCODE_DPAD_CENTER);

        Integer key = map_key.get(keycode);
        return key!=null ? key:0;
    }
}
