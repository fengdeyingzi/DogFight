package net.yzjlb.dogfight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.xl.util.AndroidKey;
import com.xl.util.UnzipAssets;
import com.xl.view.KeyPadLayout;
import com.xl.view.MRKey;
import com.xl.view.OnPadListener;
import com.xl.view.TextView;

import java.io.IOException;

import dogfight_Z.GameRun;
import graphic_Z.Managers.EventManager;

public class MainActivity extends AppCompatActivity {
KeyPadLayout gamePadView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            UnzipAssets.unZip(this, "resources.zip", getFilesDir().getPath(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,
                WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        LinearLayout layout_root = findViewById(R.id.layout_root);
        getWindow().setBackgroundDrawable(new ColorDrawable(0xff202020));
        EventManager.activity = this;
        EventManager.mainScr = new TextView(this);
        layout_root.addView(EventManager.mainScr, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        GameRun.context = this;
        EventManager.mainScr.setText("test\ntest\n");
        EventManager.mainScr.setFocusable(true);
        EventManager.mainScr.setFocusableInTouchMode(true);
        GameRun.main(new String[]{});
        gamePadView = new KeyPadLayout(this);
        addContentView(gamePadView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        gamePadView.switchKeypad();
        gamePadView.setOnPadListener(new OnPadListener() {

            @Override
            public void onKeyDown(int keycode) {
                // TODO: Implement this method
//					emulator.vm_event(MrDefines.MR_KEY_PRESS, keycode, 0);
//                if(getCurrent()!=null){
//                    if(getCurrent() instanceof Canvas){
//                        Canvas canvas = (Canvas) getCurrent();
//                        canvas.postEvent(CanvasEvent.getInstance(canvas, CanvasEvent.KEY_PRESSED, MRKey.getJavaKey(keycode)));
//                    }
//                }
                EventManager.mainScr.onKeyDown(keycode,new KeyEvent(KeyEvent.ACTION_DOWN, AndroidKey.getAndroidKey(keycode)));
            }

            @Override
            public void onKeyRepeated(int keycode) {
//                if(getCurrent()!=null){
//                    if(getCurrent() instanceof Canvas){
//                        Canvas canvas = (Canvas) getCurrent();
//                        canvas.postEvent(CanvasEvent.getInstance(canvas, CanvasEvent.KEY_REPEATED, MRKey.getJavaKey(keycode)));
//                    }
//                }
            }

            @Override
            public void onKeyUp(int keycode) {
//                if(getCurrent()!=null){
//                    if(getCurrent() instanceof Canvas){
//                        Canvas canvas = (Canvas) getCurrent();
//                        canvas.postEvent(CanvasEvent.getInstance(canvas, CanvasEvent.KEY_RELEASED, MRKey.getJavaKey(keycode)));
//                    }
//                }
//					emulator.vm_event(MrDefines.MR_KEY_RELEASE, keycode, 0);
                EventManager.mainScr.onKeyUp(keycode,new KeyEvent(KeyEvent.ACTION_UP, AndroidKey.getAndroidKey(keycode)));
            }


        });
    }
}