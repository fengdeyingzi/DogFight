package com.xl.game.tool;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.io.RandomAccessFile;

//import javax.microedition.lcdui.Graphics;

public class SkyFontGraphics {
    public static final int HCENTER = 1;
    public static final int VCENTER = 2;
    public static final int LEFT = 4;
    public static final int RIGHT = 8;
    public static final int TOP = 16;
    public static final int BOTTOM = 32;
    private static SkyFontTool fontTool;

    //初始化
    public static void init(RandomAccessFile file){
        fontTool = new SkyFontTool(file);
    }

    public static int getCharWidth(char c){
        return fontTool.xl_font_sky16_charWidth(c);
    }

    public static int getCharHeight(char c){
        return fontTool.xl_font_sky16_charHeight(c);
    }

    public static int getStringWidth(String text){
        return fontTool.xl_font_sky16_textWidthHeight(text) >> 16;
    }

    public static int getStringHeight(String text){
        return fontTool.xl_font_sky16_textWidthHeight(text)&0xffff;
    }

    public static void drawString(Canvas canvas, String text, int x, int y, int anchor, Paint drawPaint){
        if (anchor == 0) {
            anchor = LEFT | TOP;
        }
        int dx = x;
        int dy = y;

//        if ((anchor & Graphics.LEFT) != 0) {
//            drawPaint.setTextAlign(Paint.Align.LEFT);
//        } else if ((anchor & Graphics.RIGHT) != 0) {
//            drawPaint.setTextAlign(Paint.Align.RIGHT);
//            int wh = fontTool.xl_font_sky16_textWidthHeight(text);
//            dx -= (wh>>16);
//        } else if ((anchor & Graphics.HCENTER) != 0) {
//            drawPaint.setTextAlign(Paint.Align.CENTER);
//            int wh = fontTool.xl_font_sky16_textWidthHeight(text);
//            dx -= ((wh>>16)&0xffff)/2;
//        }
//
//        if ((anchor & Graphics.TOP) != 0) {
//            y -= drawPaint.ascent();
//        } else if ((anchor & Graphics.BOTTOM) != 0) {
//            y -= drawPaint.descent();
//            dy -= fontTool.xl_font_sky16_charHeight('影');
//        } else if ((anchor & Graphics.VCENTER) != 0) {
//            int h = fontTool.xl_font_sky16_charHeight('影');
//            dy -= h/2;
//            y -= drawPaint.ascent() + (drawPaint.descent() - drawPaint.ascent()) / 2;
//        }

        drawPaint.setStyle(Paint.Style.FILL);

        //xlfont
        if(fontTool!=null){
            fontTool.xl_font_sky16_drawText(canvas, text, dx,dy, drawPaint.getColor());
        }
        else{
            canvas.drawText(text, x, y, drawPaint);
        }
        drawPaint.setStyle(Paint.Style.STROKE);
    }
}
