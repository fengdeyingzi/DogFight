package com.xl.view;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.xl.game.tool.SkyFontTool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

import androidx.annotation.Nullable;

@SuppressLint("ViewConstructor")
public class SkyFontView extends View {
    private static final String TAG = "SkyFontView";
SkyFontTool fontTool;

    public SkyFontView(Context context){
        super(context);
        initView();
    }

    public SkyFontView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
        initView();
    }

    void initView(){

        try {
            fontTool = new SkyFontTool(new RandomAccessFile(getContext().getFilesDir().getPath()+File.separatorChar+"gb16_mrpoid.uc2", "r"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制文字
        fontTool.setCanvas(canvas);
        for(int i=0;i<30;i++)
        fontTool.xl_font_sky16_drawText(canvas, "测试文字", 30,30*i,0xff606060);

    }
}
