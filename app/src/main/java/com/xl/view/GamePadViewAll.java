package com.xl.view;


/*
全键盘 方格
包含 上下左右
0 ok
13456789*#

*/

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.xl.game.tool.Coll;
import com.xl.game.tool.DisplayUtil;

import java.util.ArrayList;
//import com.xl.opmrcc.view.LogView;

public class GamePadViewAll extends View implements Runnable
{

    @Override
    public void run()
    {


    }


    private Paint paint_line[];
    private Paint paint_background[];
    private Paint paint_high;
    private Paint paint_text;
    private OnPadListener listener;

    private boolean useTimer=true; //是否使用定时器
    private boolean ispost; // 是否发送定时器
    private boolean isPad8=false;//是否使用8方向
    private boolean isUseKeySimulation=true;//是否使用模拟组合键
    private boolean isShowBack = true;//是否显示返回键
    //是否启用无冲突按键（适用于java）
    int _UP=12,_DOWN=13,_LEFT=14,_RIGHT=15;
    int _1=1,
            _2=2,
            _3=3,
            _4=4,
            _5=5,
            _6=6,
            _7=7,
            _8=8,
            _9=9;


    RectButton btn_up,btn_down,btn_left,btn_right,btn_sleft,btn_sright,btn_ok,btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_8,btn_9,btn_0,btn_star,btn_pound;
    ArrayList<RectButton> list_btn;
    Runnable runnable;
    //LogView log;
    //按钮类


    //////////////////////////////////////////////////
    public class RectButton
    {
        private int x;
        private int y;
        private int w,h;
        private boolean isStart; //激活状态
        private int touch_id;
        private boolean isDown;
        private String text;

        int id;

        public RectButton(int x,int y,int w,int h)
        {
            this.x=x;
            this.y=y;
            this.w=w;
            this.h=h;
        }

        public void setId(int id)
        {
            this.id=id;
        }

        public void setText(String text)
        {
            this.text = text;
        }

        public void setXY(int x,int y)
        {
            this.x=x;
            this.y=y;
        }

        public void setWidthHeight(int width,int height){
            this.w = width;
            this.h = height;
        }

        public void setWidth(int width)
        {
            this.w = width;
        }

        public void setHeight(int height)
        {
            this.h = height;
        }

        //判断按钮是否点中
        public boolean isColl(int x,int y)
        {
            if(Coll.isPointCollisionRect(x,y,this.x,this.y,this.w,this.h))
            {
                return true;
            }
            return false;
        }
        //判断按钮是否按下
        public boolean isDown()
        {
            return this.isDown || this.isStart;
        }

        //判断手指是否按下
        public boolean isDown(int id)
        {
            return (this.touch_id==id)?true:false;

        }

        //按钮按下
        public void keyDown()
        {
            if(this.isDown==false)
            {
                if(isUseKeySimulation)
                    keyUpAll();
                this.isDown=true;
                this.isStart=true;
                if(touch_id==0){
                    if(listener!=null)
                        listener.onKeyDown(id);
                }
                else if(isUseKeySimulation){
                    if(isUseKeySimulation)
                        listener.onKeyDown(id);
                }


            }
        }
        public void keyDown(int touch_id)
        {
            if(this.isDown==false)
            {
                if(isUseKeySimulation)
                    keyUpAll();
                this.isDown=true;
                this.isStart=true;
                this.touch_id=touch_id;

                if(touch_id==0){
                    if(listener!=null)
                        listener.onKeyDown(id);
                }
                else if(isUseKeySimulation){
                    if(listener!=null)
                        listener.onKeyDown(id);
                }

                invalidate();
            }
        }

        //按键move事件 如果按键处于激活状态，重新按下
        public void move()
        {
            if(isStart &&isUseKeySimulation)
            {
                if(!isDown){
                    keyUpAll();
                    isDown=true;
                    invalidate();
                    if(listener!=null){
                        listener.onKeyDown(id);
                    }
                }
            }
        }

        //按键释放 但仍保持激活
        public void keyUpAndIsStart()
        {
            if(this.isDown==true)
            {
                this.isDown=false;
                if(touch_id==0){
                    if(listener!=null){
                        listener.onKeyUp(id);
                    }

                }
                else if(isUseKeySimulation){
                    if(listener!=null)
                        listener.onKeyUp(id);
                }


                invalidate();
            }
        }

        //按钮释放
        public void keyUp()
        {
            if(this.isDown==true)
            {
                this.isDown=false;

                if(touch_id==0){
                    if(listener!=null){
                        listener.onKeyUp(id);
                    }

                }
                else if(isUseKeySimulation){
                    if(listener!=null)
                        listener.onKeyUp(id);
                }


                invalidate();
            }
            this.isStart=false;
        }
        //设置手指id
        public void setTouch(int id)
        {
            this.touch_id = id;
        }

        //获取手指id
        public int getTouch()
        {
            return this.touch_id;
        }

        public void draw(Canvas canvas)
        {
            int index=0;
            if(isDown )index=1;
            int jw=0;
            //画ab键
            canvas.drawRect(x+jw,y+jw,x+w-jw*2,y+h-jw*2,paint_background[index]);
            canvas.drawRect(x+jw,y+jw,x+w-jw*2,y+h-jw*2,paint_line[index]);
            //画文字
            drawText(canvas,text,x+w/2,y+h/2);
            //画高亮光标
            if(isDown)
            {

            }

        }

    }




////////////////////////////////////////


    public void switchKeypad()
    {
        if(isShown())
        {
            setVisibility(View.GONE);
        }
        else
        {
            setVisibility(View.VISIBLE);
        }
    }



    public GamePadViewAll(Context context)
    {
        super(context);
        initView();
    }
/*
    public void setLogView(LogView view){
        //this.log= view;
    }
    */

    ////////////////////////////////////////////////////
    private void initView()
    {
        this.runnable=this;
        this.isUseKeySimulation=true;

        int r = DisplayUtil.dip2px(getContext(),30);
        paint_high = new Paint();
        paint_high.setColor(0x8080baf0);
        paint_high.setAntiAlias(true);
        paint_line = new Paint[2];
        paint_line[0]= new Paint();
        paint_line[1] = new Paint();
        paint_line[0].setAntiAlias(true);
        paint_line[0].setStrokeWidth(DisplayUtil.dip2px(getContext(),2));
        paint_line[0].setColor(0xe0f0f0f0);
        paint_line[0].setStyle(Paint.Style.STROKE);

        paint_line[1].setAntiAlias(true);
        paint_line[1].setStrokeWidth(DisplayUtil.dip2px(getContext(),2));
        paint_line[1].setColor(0xe060a0f0);
        paint_line[1].setStyle(Paint.Style.STROKE);

        paint_background = new Paint[2];
        paint_background[0] = new Paint();
        paint_background[1] = new Paint();
        paint_background[0].setAntiAlias(true);
        paint_background[0].setColor(0x80808080);
        paint_background[0].setStyle(Paint.Style.FILL);

        paint_background[1].setAntiAlias(true);
        paint_background[1].setColor(0x60f0f0f0);
        paint_background[1].setStyle(Paint.Style.FILL);

        paint_text = new Paint();
        paint_text.setAntiAlias(true);
        paint_text.setColor(0xff60cef0);
        paint_text.setTextSize(DisplayUtil.sp2px(getContext(),20));
        paint_text.setTextAlign(Paint.Align.CENTER);


        int rect_w = DisplayUtil.dip2px(getContext(),60);

        int rect_h = rect_w;
        int rect_num_h = rect_w*2/3;

        this.btn_up = new RectButton(0,0,rect_w,rect_w);
        this.btn_down = new RectButton(0,0,rect_w,rect_w);
        this.btn_left = new RectButton(0,0, rect_w, rect_w);
        this.btn_right = new RectButton(0,0,rect_w,rect_w);
        this.btn_sleft = new RectButton(0,0,rect_w,rect_w);
        this.btn_sright = new RectButton(0,0, rect_w,rect_w);
        this.btn_ok = new RectButton(0,0,rect_w,rect_w);



        this.btn_1 = new RectButton(0,0,rect_w,rect_num_h);
        this.btn_2 = new RectButton(0,0,rect_w,rect_num_h);
        this.btn_3 = new RectButton(0,0,rect_w,rect_num_h);
        this.btn_4 = new RectButton(0,0,rect_w,rect_num_h);
        this.btn_5 = new RectButton(0,0,rect_w,rect_num_h);
        this.btn_6 = new RectButton(0,0,rect_w,rect_num_h);
        this.btn_7 = new RectButton(0,0,rect_w,rect_num_h);
        this.btn_8 = new RectButton(0,0,rect_w,rect_num_h);
        this.btn_9 = new RectButton(0,0,rect_w,rect_num_h);
        this.btn_0 = new RectButton(0,0,rect_w,rect_num_h);
        this.btn_star = new RectButton(0,0,rect_w,rect_num_h);
        this.btn_pound = new RectButton(0,0,rect_w,rect_num_h);



        this.btn_up.setId(_UP);
        this.btn_down.setId(_DOWN);
        this.btn_left.setId(_LEFT);
        this.btn_right.setId(_RIGHT);
        this.btn_1.setId(1);
        this.btn_2.setId(2);
        this.btn_3.setId(3);
        this.btn_4.setId(4);
        this.btn_5.setId(5);
        this.btn_6.setId(6);
        this.btn_7.setId(7);
        this.btn_8.setId(8);
        this.btn_9.setId(9);
        this.btn_0.setId(0);
        this.btn_star.setId(10);
        this.btn_pound.setId(11);
        this.btn_sleft.setId(17);
        this.btn_sright.setId(18);
        this.btn_ok.setId(20);

        list_btn = new ArrayList<RectButton>();
        list_btn.add(btn_0);
        list_btn.add(btn_1);
        list_btn.add(btn_2);
        list_btn.add(btn_3);
        list_btn.add(btn_4);
        list_btn.add(btn_5);
        list_btn.add(btn_6);
        list_btn.add(btn_7);
        list_btn.add(btn_8);
        list_btn.add(btn_9);
        list_btn.add(btn_star);
        list_btn.add(btn_pound);
        list_btn.add(btn_up);
        list_btn.add(btn_down);
        list_btn.add(btn_left);
        list_btn.add(btn_right);
        list_btn.add(btn_sleft);
        if(isShowBack)
        list_btn.add(btn_sright);
        list_btn.add(btn_ok);


        //设置文字
        this.btn_0.setText("0");
        this.btn_1.setText("1");
        this.btn_2.setText("2");
        this.btn_3.setText("3");
        this.btn_4.setText("4");
        this.btn_5.setText("5");
        this.btn_6.setText("6");
        this.btn_7.setText("7");
        this.btn_8.setText("8");
        this.btn_9.setText("9");
        this.btn_star.setText("*");
        this.btn_pound.setText("#");
        this.btn_up.setText("↑");
        this.btn_down.setText("↓");
        this.btn_left.setText("←");
        this.btn_right.setText("→");
        this.btn_sleft.setText("≡");
        this.btn_sright.setText("<");
        this.btn_ok.setText("OK");

    }

    public void setAlpha(int alpha)
    {
        paint_background[0].setAlpha(alpha/2);
        paint_line[0].setAlpha(alpha);
        paint_text.setAlpha(alpha);
        invalidate();
    }

    public void setOnPadListener(OnPadListener listener)
    {
        this.listener = listener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        int index=0;
        int x=0,y=0;
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN: //按键按下 不一定是1号手指 因为1号手指可能被其它view获得
                //获取手指排列序号
                index=event.getActionIndex();
                if(index >=0)
                {
                    x = (int)event.getX(index);
                    y= (int)event.getY(index);

                    for(int i=0;i<list_btn.size();i++)
                    {
                        RectButton btn = list_btn.get(i);
                        if(btn.isColl(x,y))
                        {
                            btn.keyDown(0);
                            return true;
                        }
                    }
                }

                break;
            case MotionEvent.ACTION_POINTER_1_DOWN: //1号手指按下
                //通过手指id寻找序号
                index=event.findPointerIndex(0);
                if(index >=0)
                {
                    x = (int)event.getX(index);
                    y= (int)event.getY(index);

                    for(int i=0;i<list_btn.size();i++)
                    {
                        RectButton btn = list_btn.get(i);
                        if(btn.isColl(x,y))
                        {
                            btn.keyDown(0);
                            return true;
                        }
                    }

                }

                break;
            case MotionEvent.ACTION_POINTER_2_DOWN: //2号手指按下
                //
                index=event.findPointerIndex(1);
                if(index >=0)
                {
                    x = (int)event.getX(index);
                    y= (int)event.getY(index);
                    for(int i=0;i<list_btn.size();i++)
                    {
                        RectButton btn = list_btn.get(i);
                        if(btn.isColl(x,y))
                        {
                            btn.keyDown(1);
                            return true;
                        }
                    }

                }

                break;
            case MotionEvent.ACTION_MOVE: //移动，需要获取手指id
                //获取手指索引
                //  log.log("move");
                for(int i=0;i<list_btn.size();i++)
                {
                    RectButton btn = list_btn.get(i);
                    btn.move();


                }

                return true;

            case MotionEvent.ACTION_POINTER_1_UP: //1号手指抬起
                //log.log("point 1 up");
                for(int i=0;i<list_btn.size();i++)
                {
                    RectButton btn = list_btn.get(i);
                    if(btn.getTouch()==0 && btn.isDown())
                    {
                        btn.keyUp();
                        return true;
                    }

                }
                break;
            case MotionEvent.ACTION_POINTER_2_UP:
                // log.log("point 2 up");
                for(int i=0;i<list_btn.size();i++)
                {
                    RectButton btn = list_btn.get(i);
                    if(btn.getTouch()==1 && btn.isDown())
                    {
                        btn.keyUp();
                        return true;
                    }

                }
                break;
            case MotionEvent.ACTION_UP: //按键释放 不一定是1号手指
                //log.log("up");
                for(int i=0;i<list_btn.size();i++)
                {
                    RectButton btn = list_btn.get(i);
                    btn.keyUp();
                }
                break;
        }
        invalidate();
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        // TODO: Implement this method
        super.onDraw(canvas);
        for(int i=0;i<list_btn.size();i++)
        {
            RectButton btn = list_btn.get(i);

            btn.draw(canvas);


        }
    }

    //判断是否有按键按下，如果有，那么弹起
    private void keyUpAll()
    {
        for(int i=0;i<list_btn.size();i++)
        {
            RectButton btn = list_btn.get(i);
            btn.keyUpAndIsStart();
        }
    }

    //绘制文字到中点
    private void drawText(Canvas canvas, String text, int x, int y)
    {
        float height = paint_text.getTextSize();
        Paint.FontMetrics fontMetrics = paint_text.getFontMetrics();
        // 计算文字高度
        float fontHeight = fontMetrics.bottom - fontMetrics.top;
        // 计算文字baseline
        float textBaseY = height - (height - fontHeight) / 2 - fontMetrics.bottom;
        canvas.drawText(text,x, y+(paint_text.getTextSize()*9/10)/2,paint_text);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom)
    {
        // TODO: Implement this method
        super.onLayout(changed, left, top, right, bottom);
        int width= right-left;
        int height = bottom -top;

        int rect_w= DisplayUtil.dip2px(getContext(),60);
        //最低位置
        height = Math.min(height, (int)(width*1.9));
        if(rect_w>width/7)rect_w=width/7;
        int rect_num_h = rect_w;
        int rect_h = rect_w;

        int start_x= width-rect_w*8;
        int start_num_x = width- rect_w*4;
        int start_y = height-rect_w*3;
        this.btn_star.setXY(start_num_x+rect_w*3, start_y);
        this.btn_0.setXY(start_num_x+rect_w*3, start_y+rect_num_h);
        this.btn_pound.setXY(start_num_x+rect_w*3,start_y+rect_num_h*2);

        this.btn_3.setXY(start_num_x+rect_w*2, start_y);
        this.btn_6.setXY(start_num_x+rect_w*2, start_y+rect_num_h);
        this.btn_9.setXY(start_num_x+rect_w*2, start_y+rect_num_h*2);

        this.btn_2.setXY(start_num_x+rect_w, start_y);
        this.btn_5.setXY(start_num_x+rect_w, start_y+rect_num_h);
        this.btn_8.setXY(start_num_x+rect_w, start_y+rect_num_h*2);

        this.btn_1.setXY(start_num_x, start_y);
        this.btn_4.setXY(start_num_x, start_y+rect_num_h);
        this.btn_7.setXY(start_num_x, start_y+rect_num_h*2);

        this.btn_sleft.setXY(0,start_y);
        this.btn_sright.setXY(rect_w*2, start_y);
        this.btn_left.setXY(0, start_y+rect_h);
        this.btn_up.setXY(rect_w,  start_y);
        this.btn_down.setXY(rect_w,start_y+rect_h*2);
        this.btn_ok.setXY(rect_w, start_y+rect_h);
        this.btn_right.setXY(rect_w*2,start_y+rect_h);

        //设置宽高
        this.btn_up.setWidthHeight(rect_w,rect_h);
        this.btn_down.setWidthHeight(rect_w,rect_h);
        this.btn_left.setWidthHeight(rect_w,rect_h);
        this.btn_right.setWidthHeight(rect_w,rect_h);
        this.btn_sleft.setWidthHeight(rect_w,rect_h);
        this.btn_sright.setWidthHeight(rect_w,rect_h);
        this.btn_ok.setWidthHeight(rect_w,rect_h);
        this.btn_0.setWidthHeight(rect_w,rect_num_h);
        this.btn_1.setWidthHeight(rect_w,rect_num_h);
        this.btn_2.setWidthHeight(rect_w,rect_num_h);
        this.btn_3.setWidthHeight(rect_w,rect_num_h);
        this.btn_4.setWidthHeight(rect_w,rect_num_h);
        this.btn_5.setWidthHeight(rect_w,rect_num_h);
        this.btn_6.setWidthHeight(rect_w,rect_num_h);
        this.btn_7.setWidthHeight(rect_w,rect_num_h);
        this.btn_8.setWidthHeight(rect_w,rect_num_h);
        this.btn_9.setWidthHeight(rect_w,rect_num_h);
        this.btn_star.setWidthHeight(rect_w,rect_num_h);
        this.btn_pound.setWidthHeight(rect_w,rect_num_h);



    }




}

