package com.xl.view;
/*
 游戏全键盘 摇杆
 包含 上下左右
 0 ok
 1357*#

 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

import com.xl.game.tool.Coll;
import com.xl.game.tool.DisplayUtil;

public class GamePadView extends View implements Runnable {

    @Override
    public void run() {
        if (btn_pad.isStart() && btn_pad.getTouch() == 0) {
            if (btn_pad.isDown()) {
                btn_pad.keyUp();
                btn_pad.keyDown();
            }
            postDelayed(this, 500);
        }
    }


    private Paint paint_line[];
    private Paint paint_background[];
    private Paint paint_high;
    private Paint paint_text;
    private OnPadListener listener;
    private CirPadButton btn_pad; //摇杆
    private boolean useTimer = true; //是否使用定时器
    private boolean ispost; // 是否发送定时器
    private boolean isPad8 = false;//是否使用8方向
    private boolean isUseKeySimulation;//是否使用模拟组合键
    private boolean isUseMultiKey;//是否使用无冲突按键
    private boolean isShowBack = true; //是否显示返回键
    int _UP = 12, _DOWN = 13, _LEFT = 14, _RIGHT = 15;
    int _1 = 1,
            _2 = 2,
            _3 = 3,
            _4 = 4,
            _5 = 5,
            _6 = 6,
            _7 = 7,
            _8 = 8,
            _9 = 9;

    CirButton btn_a, btn_b, btn_soft_left, btn_soft_right;
    RectButton btn_1, btn_2, btn_3, btn_7, btn_9, btn_star, btn_pound;

    Runnable runnable;

    //按钮类
    public class CirButton {
        private int x;
        private int y;
        private int r;
        private int r_high; //高亮光标大小
        private int touch_id;
        private boolean isDown;
        private String text;
        int id;

        public CirButton(int r) {
            this.x = x;
            this.y = y;
            this.r = r;
            this.r_high = r + DisplayUtil.dip2px(getContext(), 24);
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setText(String text) {
            this.text = text;
        }

        public void setXY(int x, int y) {
            this.x = x;
            this.y = y;
        }

        //判断按钮是否点中
        public boolean isColl(int x, int y) {
            if (Coll.getLineSize(this.x, this.y, x, y) <= r) {
                return true;
            }
            return false;
        }

        //判断按钮是否按下
        public boolean isDown() {
            return this.isDown;
        }

        //判断手指是否按下
        public boolean isDown(int id) {
            return (this.touch_id == id) ? true : false;

        }

        //按钮按下
        private void keyDown() {
            if (this.isDown == false) {
                this.isDown = true;
                if (listener != null)
                    listener.onKeyDown(id);
            }
        }

        public void keyReDown() {
            if (this.isDown) {
                if (listener != null) {
                    listener.onKeyRepeated(id);
                }
            }
        }

        public void keyDown(int touch_id) {
            if (this.isDown == false) {
                if (isUseKeySimulation)
                    keyUpAll();
                this.isDown = true;
                this.touch_id = touch_id;

                if (listener != null)
                    if (touch_id == 0)
                        listener.onKeyDown(id);
                    else if (isUseKeySimulation) {
                        //
                        if (listener != null)
                            listener.onKeyDown(id);
                    }
            }
        }

        //圆形按钮释放
        public void keyUp() {
            if (this.isDown == true) {
                this.isDown = false;
                if (listener != null) {
                    if (touch_id == 0)
                        listener.onKeyUp(id);
                    else if (isUseKeySimulation)
                        listener.onKeyUp(id);
                }
            }
        }

        //设置手指id
        public void setTouch(int id) {
            this.touch_id = id;
        }

        //获取手指id
        public int getTouch() {
            return this.touch_id;
        }

        public void draw(Canvas canvas) {
            int index = 0;
            if (isDown) index = 1;
            //画ab键
            canvas.drawCircle(x, y, r * 5 / 6, paint_background[index]);
            canvas.drawCircle(x, y, r, paint_line[index]);
            ///
            if (text != null)
                drawText(canvas, text, x, y);
            //画高亮光标
            if (isDown) {
                canvas.drawCircle(x, y, r_high, paint_high);
            }

        }

    }

    //////////////////////////////////////////////////
    public class RectButton {
        private int x;
        private int y;
        private int w, h;

        private int touch_id;
        private boolean isDown;
        private String text;

        int id;

        public RectButton(int x, int y, int w, int h) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setText(String text) {
            this.text = text;
        }

        public void setXY(int x, int y) {
            this.x = x;
            this.y = y;
        }

        //判断按钮是否点中
        public boolean isColl(int x, int y) {
            if (Coll.isPointCollisionRect(x, y, this.x, this.y, this.w, this.h)) {
                return true;
            }
            return false;
        }

        //判断按钮是否按下
        public boolean isDown() {
            return this.isDown;
        }

        //判断手指是否按下
        public boolean isDown(int id) {
            return (this.touch_id == id) ? true : false;

        }

        //按钮按下
        public void keyDown() {
            if (this.isDown == false) {
                this.isDown = true;
                if (listener != null)
                    listener.onKeyDown(id);
            }
        }

        public void keyReDown() {
            if (this.isDown) {
                if (listener != null) {
                    listener.onKeyRepeated(id);
                }
            }
        }

        public void keyDown(int touch_id) {
            if (this.isDown == false) {
                this.isDown = true;
                this.touch_id = touch_id;

                if (listener != null && touch_id == 0)
                    listener.onKeyDown(id);
            }
        }

        //按钮释放
        public void keyUp() {
            if (this.isDown == true) {
                this.isDown = false;
                if (listener != null)
                    listener.onKeyUp(id);
            }
        }

        //设置手指id
        public void setTouch(int id) {
            this.touch_id = id;
        }

        //获取手指id
        public int getTouch() {
            return this.touch_id;
        }

        public void draw(Canvas canvas) {
            int index = 0;
            if (isDown) index = 1;
            //画ab键
            canvas.drawRect(x, y, x + w, y + h, paint_background[index]);
            canvas.drawRect(x, y, x + w, y + h, paint_line[index]);
            //画文字
            drawText(canvas, text, x + w / 2, y + h / 2);
            //画高亮光标
            if (isDown) {

            }

        }

    }


    //////////////////////////////////////////////////
    //按钮类 摇杆
    public class CirPadButton {
        private int x;
        private int y;
        private int r_pad; //底盘半径
        private int r_smallpad; //操纵杆
        private int round; //角度值
        private int size; //圆心到操纵杆距离
        private int touch_id;
        private boolean isDown; //摇杆是否按下
        private boolean isStart; //摇杆是否启动
        private int key; //按下的按键 上下左右
        boolean isKeep;//是否连按


        public CirPadButton(int r) {
            this.x = x;
            this.y = y;
            this.r_pad = r;
            this.r_smallpad = r / 2;
        }

        public void setXY(int x, int y) {
            this.x = x;
            this.y = y;
        }

        //判断按钮是否点中中间的摇杆
        public boolean isCollPad(int x, int y) {
            if (Coll.getLineSize(this.x, this.y, x, y) <= r_pad) {
                /*
                 //获取角度
                 setRound((int)Coll.getRadiam(this.x,this.y,x,y));
                 */
                return true;
                /*
                 //获取距离
                 if((int)Coll.getLineSize(x,y,this.x,this.y)<=r_smallpad)
                 {
                 return true;
                 }
                 */


            }
            return false;
        }

        public boolean isColl(int x, int y) {
            if (Coll.getLineSize(this.x, this.y, x, y) <= r_pad) {
                /*
                 //获取角度
                 setRound((int)Coll.getRadiam(this.x,this.y,x,y));
                 */
                //获取距离
                if ((int) Coll.getLineSize(x, y, this.x, this.y) > r_smallpad) {
                    return true;
                }

            }
            return false;
        }

        //移动事件
        public void move(int x, int y) {
            if (isStart) {
                //设置角度
                setRound((int) Coll.getRadiam(this.x, this.y, x, y));
                //设置距离
                setSize((int) Coll.getLineSize(x, y, this.x, this.y));
                if (isDown) {
                    if (getSize() >= r_smallpad) {
                        if (getDecitation(getRound()) != key) {
                            if (listener != null) {
                                if (touch_id == 0 && listener != null)
                                    listener.onKeyUp(key);
                                key = getDecitation(getRound());
                                if (touch_id == 0 && listener != null)
                                    listener.onKeyDown(key);
                            }
                        }
                    } else //如果没有点中，释放按键
                    {
                        isDown = false;
                        if (listener != null) {
                            listener.onKeyUp(key);
                        }

                    }


                } else //如果摇杆没有按下，判断是否按到方向键盘，若按到，则发送键盘事件
                {
                    if (getSize() >= r_smallpad) {
                        if (isUseKeySimulation)
                            keyUpAll();
                        isDown = true;
                        key = getDecitation(getRound());
                        if (listener != null) {
                            if (touch_id == 0) {

                                listener.onKeyDown(key);
                            } else if (isUseKeySimulation) {
                                listener.onKeyDown(key);
                            }
                        }
                    }
                }

            }

        }


        //判断按钮是否按下
        public boolean isDown() {
            return this.isDown;
        }

        //按钮按下
        public void keyDown() {

            if (!this.isDown) {
                if (isUseKeySimulation)
                    keyUpAll();
                this.isDown = true;
                this.isStart = true;
                //抬起其它按键
                if (touch_id == 0) {
                    if (listener != null)
                        listener.onKeyDown(key);
                } else if (isUseKeySimulation) {
                    if (listener != null)
                        listener.onKeyDown(key);
                }
            }

        }

        public void keyReDown() {
            if (isDown) {
                if (listener != null) {
                    listener.onKeyRepeated(key);
                }
            }
        }

        public void keyDown(int id, int x, int y) {
            //获取角度
            setRound((int) Coll.getRadiam(this.x, this.y, x, y));
            //获取距离
            setSize((int) Coll.getLineSize(x, y, this.x, this.y));
            //生成key
            key = getDecitation(getRound());
            this.touch_id = id;
            //this.keyDown();
        }

        //启动键盘
        public void startPad(int touch_id) {
            this.touch_id = touch_id;
            if (!isStart) {
                isStart = true;
            } else {
                if (isDown) {
                    isDown = false;
                    if (listener != null && this.touch_id == 0) {
                        //if(isUseKeySimulation)
                        //  keyUpAll();
                        listener.onKeyUp(key);

                    }
                }
            }


        }

        public boolean isStart() {
            return isStart;
        }


        //按钮释放，但仍保持激活
        public void keyUpisStart() {
            if (isDown) {
                this.isDown = false;

                if (listener != null) {
                    if (touch_id == 0) {
                        listener.onKeyUp(key);
                    } else if (isUseKeySimulation) {
                        listener.onKeyUp(key);
                    }
                }
            }

        }

        //按钮释放
        public void keyUp() {
            if (isStart) {
                isStart = false;
            }
            if (isDown) {
                this.isDown = false;

                if (listener != null && touch_id == 0) {
                    listener.onKeyUp(key);
                } else if (isUseKeySimulation) {
                    if (listener != null)
                        listener.onKeyUp(key);
                }
            }
            this.size = 0;
        }

        //设置角度值
        private void setRound(int r) {
            this.round = r;
            if (this.round >= 360) {
                this.round = this.round % 360;
            }
            if (this.round < 0) {
                this.round = this.round % 360 + 360;
            }
        }

        //获取角度值
        public int getRound() {
            return this.round;
        }

        //设置距离值
        private void setSize(int size) {
            this.size = size;
            /*
             if(this.size>r_pad)
             {
             this.size=r_pad;
             }*/
        }

        //获取距离值
        public int getSize() {
            return this.size;
        }


        //设置手指id
        private void setTouch(int id) {
            this.touch_id = id;
        }

        //获取手指id
        public int getTouch() {
            return this.touch_id;
        }


        public void draw(Canvas canvas) {
            Point point = new Point();
            int size = (this.size > r_pad ? r_pad : this.size);
            Coll.toSpin(x, y, size, size, round, point);
            int index = 0;
            if (isDown) // 画高亮光标
            {

                canvas.drawCircle(point.x, point.y, r_smallpad * 8 / 4, paint_high);
            }

            if (isStart) {
                index = 1;
            }

            //画摇杆键盘
            {
                canvas.drawCircle(x, y, r_pad, paint_line[index]);
                canvas.drawCircle(x, y, r_pad, paint_background[index]);
                canvas.drawCircle(point.x, point.y, r_smallpad, paint_line[index]);
                canvas.drawCircle(point.x, point.y, r_smallpad, paint_background[index]);
            }


            //绘制手指id
            paint_high.setTextSize(DisplayUtil.dip2px(getContext(), 12));
            if(isPad8){
                int r3 = r_pad*4/5;
                Point point_rightbottom = ex_toSpin(x,y, r3,r3, 45);
                Point point_leftbottom = ex_toSpin(x,y, r3,r3, 135);
                Point point_lefttop = ex_toSpin(x,y, r3,r3, 215);
                Point point_righttop = ex_toSpin(x,y, r3,r3, 315);
                drawText(canvas,"9", point_rightbottom.x, point_rightbottom.y);
                drawText(canvas,"7", point_leftbottom.x, point_leftbottom.y);
                drawText(canvas,"1", point_lefttop.x,point_lefttop.y);
                drawText(canvas,"3", point_righttop.x, point_righttop.y);
            }

            //canvas.drawText(""+getTouch(),80,80,paint_high);

        }

    }

    static float ex_toRad(float a)
    {
        return (a*3.14159265f)/180;
    }

    //点(x,y)旋转指定弧度r，得到旋转后的坐标
    //旋转一条水平线，得到旋转后的坐标
//参数：旋转中心点(px,py)，旋转横向半径rx，旋转纵向半径ry， 旋转后坐标指针(*x,*y)
    Point ex_toSpin(int px,int py,int rx,int ry,int r)
    {
        Point point = new Point();
		point.x=(int)( px+(rx)*Math.cos(ex_toRad(r)));
		point.y=(int)( py+(ry)*Math.sin(ex_toRad(r)));
		return point;
    }

    //根据角度获取方向
    /*
     1          2           3
     204 248    249 293    294 338
     4          5           6
     159 203               339 22
     7          8           9
     114 158    68 113     23 67
     */
    private int getDecitation(int span) {
        if (isPad8) {
            if ((span >= 339 && span <= 360) || (span >= 0 && span <= 22)) {
                return _RIGHT;
            } else if (span >= 23 && span <= 67) {
                return _9;
            } else if (span >= 68 && span <= 113) {
                return _DOWN;
            } else if (span >= 114 && span <= 158) {
                return _7;
            } else if (span >= 159 && span <= 203) {
                return _LEFT;
            } else if (span >= 204 && span <= 248) {
                return _1;
            } else if (span >= 249 && span <= 293) {
                return _UP;
            } else if (span >= 294 && span <= 338) {
                return _3;
            } else {
                return _UP;
            }
        } else {
            if ((span >= 315) || (span <= 45)) {
                return _RIGHT;
            } else if (span > 45 && span <= 135) {
                return _DOWN;
            } else if (span > 135 && span <= 225) {
                return _LEFT;
            } else {
                return _UP;
            }
        }
    }

    public void switchKeypad() {
        if (isShown()) {
            setVisibility(View.GONE);
        } else {
            setVisibility(View.VISIBLE);
        }
    }


    public GamePadView(Context context) {
        super(context);
        initView();
    }

    ////////////////////////////////////////////////////
    private void initView() {
        this.runnable = this;
        this.isUseKeySimulation = true;
        this.btn_pad = new CirPadButton(DisplayUtil.dip2px(getContext(), 50));
        int r = DisplayUtil.dip2px(getContext(), 30);
        paint_high = new Paint();
        paint_high.setColor(0x8080baf0);
        paint_high.setAntiAlias(true);
        paint_line = new Paint[2];
        paint_line[0] = new Paint();
        paint_line[1] = new Paint();
        paint_line[0].setAntiAlias(true);
        paint_line[0].setStrokeWidth(DisplayUtil.dip2px(getContext(), 2));
        paint_line[0].setColor(0xe0f0f0f0);
        paint_line[0].setStyle(Paint.Style.STROKE);

        paint_line[1].setAntiAlias(true);
        paint_line[1].setStrokeWidth(DisplayUtil.dip2px(getContext(), 2));
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
        paint_text.setTextSize(DisplayUtil.sp2px(getContext(), 20));
        paint_text.setTextAlign(Paint.Align.CENTER);
        this.btn_a = new CirButton(r);
        this.btn_b = new CirButton(r);
        this.btn_a.setId(20);
        this.btn_b.setId(0);
        int rect_w = DisplayUtil.dip2px(getContext(), 32);
        this.btn_1 = new RectButton(0, 0, rect_w, rect_w);
        this.btn_2 = new RectButton(0, 0, rect_w, rect_w);
        this.btn_3 = new RectButton(0, 0, rect_w, rect_w);
        this.btn_7 = new RectButton(0, 0, rect_w, rect_w);
        this.btn_9 = new RectButton(0, 0, rect_w, rect_w);
        this.btn_star = new RectButton(0, 0, rect_w, rect_w);
        this.btn_pound = new RectButton(0, 0, rect_w, rect_w);
        this.btn_soft_left = new CirButton(DisplayUtil.dip2px(getContext(), 40));
        this.btn_soft_right = new CirButton(DisplayUtil.dip2px(getContext(), 40));
        this.btn_1.setId(1);
        this.btn_2.setId(2);
        this.btn_3.setId(3);
        this.btn_7.setId(7);
        this.btn_9.setId(9);
        this.btn_star.setId(10);
        this.btn_pound.setId(11);

        this.btn_a.setText("OK");
        this.btn_b.setText("0");
        this.btn_1.setText("1");
        this.btn_2.setText("2");
        this.btn_3.setText("3");
        this.btn_7.setText("7");
        this.btn_9.setText("9");
        this.btn_star.setText("*");
        this.btn_pound.setText("#");


        this.btn_soft_left.setId(17);
        this.btn_soft_right.setId(18);
    }


    //设置键盘透明度
    public void setAlpha(int alpha) {
        paint_background[0].setAlpha(alpha / 2);
        paint_line[0].setAlpha(alpha);
        paint_text.setAlpha(alpha);
        invalidate();
    }

    //设置8方向键盘
    public void setPad8(boolean isPad8) {
        this.isPad8 = isPad8;
    }

    //设置是否使用定时器
    public void setUseTimer(boolean isUse){
        this.useTimer = isUse;
    }

    //是否使用无冲突按键（组合键，适用于java）
    public void setUseMultiKey(boolean isMultiKey) {
        this.isUseMultiKey = isMultiKey;
    }

    //设置按键监听
    public void setOnPadListener(OnPadListener listener) {
        this.listener = listener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index = 0;
        int x = 0, y = 0;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: //按键按下 不一定是1号手指 因为1号手指可能被其它view获得
                //获取手指排列序号
                index = event.getActionIndex();
                if (index >= 0) {
                    x = (int) event.getX(index);
                    y = (int) event.getY(index);

                    if (btn_pad.isCollPad(x, y)) {
                        //btn_pad.keyDown(0,x,y);
                        //btn_pad.move(x,y);
                        btn_pad.startPad(0);
                        if (!ispost && useTimer) {
                            postDelayed(this, 500);
                            ispost = true;
                        }
                    } else if (btn_a.isColl(x, y)) {
                        btn_a.keyDown(0);
                    } else if (btn_b.isColl(x, y)) {
                        btn_b.keyDown(0);
                    } else if (btn_soft_left.isColl(x, y)) {
                        btn_soft_left.keyDown(0);
                    } else if(btn_soft_right.isColl(x,y)){
                        if(isShowBack)
                        btn_soft_right.keyDown(0);
                    }

                    else if (btn_1.isColl(x, y)) {
                        btn_1.keyDown(0);
                    } else if (btn_2.isColl(x, y)) {
                        btn_2.keyDown(0);
                    } else if (btn_3.isColl(x, y)) {
                        btn_3.keyDown(0);
                    } else if (btn_7.isColl(x, y)) {
                        btn_7.keyDown(0);
                    } else if (btn_9.isColl(x, y)) {
                        btn_9.keyDown(0);
                    } else if (btn_star.isColl(x, y)) {
                        btn_star.keyDown(0);
                    } else if (btn_pound.isColl(x, y)) {
                        btn_pound.keyDown(0);
                    } else
                        return false;
                }

                break;
            case MotionEvent.ACTION_POINTER_1_DOWN: //1号手指按下
                //通过手指id寻找序号
                index = event.findPointerIndex(0);
                if (index >= 0) {
                    x = (int) event.getX(index);
                    y = (int) event.getY(index);

                    if (btn_pad.isCollPad(x, y)) {
                        //btn_pad.keyDown(0,x,y);
                        //btn_pad.move(x,y);
                        btn_pad.startPad(0);
                    } else if (btn_a.isColl(x, y)) {
                        btn_a.keyDown(0);
                    } else if (btn_b.isColl(x, y)) {
                        btn_b.keyDown(0);
                    } else
                        return false;
                }

                break;
            case MotionEvent.ACTION_POINTER_2_DOWN: //2号手指按下
                //
                index = event.findPointerIndex(1);
                if (index >= 0) {
                    x = (int) event.getX(index);
                    y = (int) event.getY(index);

                    if (btn_pad.isCollPad(x, y)) {
                        //btn_pad.keyDown(1,x,y);
                        //btn_pad.move(x,y);
                        btn_pad.startPad(1);
                    } else if (btn_a.isColl(x, y)) {
                        btn_a.keyDown(1);
                    } else if (btn_b.isColl(x, y)) {
                        btn_b.keyDown(1);
                    } else
                        return false;
                }

                break;
            case MotionEvent.ACTION_MOVE: //移动，需要获取手指id
                //获取手指索引
                index = event.findPointerIndex(btn_pad.getTouch());
                if (index >= 0) {
                    x = (int) event.getX(index);
                    y = (int) event.getY(index);
                    if (btn_pad.isStart())
                        btn_pad.move(x, y);
                    //重复按键 适用于java
                    if (btn_pad.isDown) {
                        btn_pad.keyReDown();
                    }
                    if (btn_1.isDown()) {
                        btn_1.keyReDown();
                    }
                    if (btn_2.isDown()) {
                        btn_2.keyReDown();
                    }
                    if (btn_3.isDown()) {
                        btn_3.keyReDown();
                    }
                    if (btn_7.isDown()) {
                        btn_7.keyReDown();
                    }
                    if (btn_9.isDown()) {
                        btn_9.keyReDown();
                    }
                    if (btn_a.isDown()) {
                        btn_a.keyReDown();
                    }
                    if (btn_b.isDown()) {
                        btn_b.keyReDown();
                    }
                } else
                    return false;
                break;
            case MotionEvent.ACTION_POINTER_1_UP: //1号手指抬起
                if (btn_pad.getTouch() == 0) {
                    btn_pad.keyUp();
                    if (ispost) {
                        removeCallbacks(this);
                        ispost = false;
                    }
                }
                if (btn_a.getTouch() == 0)
                    btn_a.keyUp();
                if (btn_b.getTouch() == 0)
                    btn_b.keyUp();
                break;
            case MotionEvent.ACTION_POINTER_2_UP:
                if (btn_pad.getTouch() == 1) {
                    btn_pad.keyUp();
                    if (ispost) {
                        removeCallbacks(this);
                        ispost = false;
                    }
                }
                if (btn_a.getTouch() == 1)
                    btn_a.keyUp();
                if (btn_b.getTouch() == 1)
                    btn_b.keyUp();
                break;
            case MotionEvent.ACTION_UP: //按键释放 不一定是1号手指
                if (ispost) {
                    removeCallbacks(this);
                    ispost = false;
                }
                btn_pad.keyUp();
                btn_a.keyUp();
                btn_b.keyUp();
                btn_soft_left.keyUp();
                btn_soft_right.keyUp();
                btn_1.keyUp();
                btn_2.keyUp();
                btn_3.keyUp();
                btn_7.keyUp();
                btn_9.keyUp();
                btn_star.keyUp();
                btn_pound.keyUp();
                break;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO: Implement this method
        super.onDraw(canvas);
        btn_pad.draw(canvas);
        btn_a.draw(canvas);
        btn_b.draw(canvas);
        btn_1.draw(canvas);
        btn_2.draw(canvas);
        btn_3.draw(canvas);
        btn_7.draw(canvas);
        btn_9.draw(canvas);
        btn_star.draw(canvas);
        btn_pound.draw(canvas);
        btn_soft_left.draw(canvas);
        if(isShowBack) btn_soft_right.draw(canvas);

        //画文字
        /*
        drawText(canvas,"OK",btn_a.x,btn_a.y);
        drawText(canvas,"0",btn_b.x,btn_b.y);
        drawText(canvas,"2",btn_2.x+btn_2.w/2,btn_2.y+btn_2.w/2);
        drawText(canvas,"1",btn_1.x+btn_1.w/2,btn_1.y+btn_1.w/2);
        drawText(canvas,"3",btn_3.x+btn_3.w/2,btn_3.y+btn_3.w/2);
        drawText(canvas,"7",btn_7.x+btn_7.w/2,btn_7.y+btn_7.w/2);
        drawText(canvas,"9",btn_9.x+btn_9.w/2,btn_9.y+btn_9.w/2);
        drawText(canvas,"*",btn_star.x+btn_star.w/2,btn_star.y+btn_star.w/2);
        drawText(canvas,"#",btn_pound.x+btn_pound.w/2,btn_pound.y+btn_pound.w/2);
        */
    }

    //判断是否有按键按下，如果有，那么弹起
    private void keyUpAll() {
        btn_pad.keyUpisStart();
        btn_a.keyUp();
        btn_b.keyUp();
        btn_soft_left.keyUp();
        btn_soft_right.keyUp();
        btn_1.keyUp();
        btn_2.keyUp();
        btn_3.keyUp();
        btn_7.keyUp();
        btn_9.keyUp();
        btn_star.keyUp();
        btn_pound.keyUp();
    }

    //绘制文字到中点
    private void drawText(Canvas canvas, String text, int x, int y) {
        float height = paint_text.getTextSize();
        Paint.FontMetrics fontMetrics = paint_text.getFontMetrics();
        // 计算文字高度
        float fontHeight = fontMetrics.bottom - fontMetrics.top;
        // 计算文字baseline
        float textBaseY = height - (height - fontHeight) / 2 - fontMetrics.bottom;
        canvas.drawText(text, x, y + (paint_text.getTextSize() * 9 / 10) / 2, paint_text);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        // TODO: Implement this method
        super.onLayout(changed, left, top, right, bottom);
        int width = right - left;
        int height = bottom - top;
        int cir_right = Math.max(width * 5 / 6, width - DisplayUtil.dip2px(getContext(), 90));
        //最低位置
        height = Math.min(height, (int) (width * 1.9));
        this.btn_pad.setXY(DisplayUtil.dip2px(getContext(), 90), height - DisplayUtil.dip2px(getContext(), 100));
        this.btn_b.setXY(cir_right - DisplayUtil.dip2px(getContext(), 60), height - DisplayUtil.dip2px(getContext(), 120));
        this.btn_a.setXY(cir_right, height - DisplayUtil.dip2px(getContext(), 80));
        this.btn_soft_left.setXY(0, height);
        this.btn_soft_right.setXY(right, height-DisplayUtil.dip2px(getContext(), 180));
        int rect_w = DisplayUtil.dip2px(getContext(), 32);
        int start_x = width - rect_w * 8;
        this.btn_1.setXY(start_x += rect_w, height - rect_w);
        this.btn_2.setXY(start_x += rect_w, height - rect_w);
        this.btn_3.setXY(start_x += rect_w, height - rect_w);
        this.btn_7.setXY(start_x += rect_w, height - rect_w);
        this.btn_9.setXY(start_x += rect_w, height - rect_w);
        this.btn_star.setXY(start_x += rect_w, height - rect_w);
        this.btn_pound.setXY(start_x += rect_w, height - rect_w);

    }


}
