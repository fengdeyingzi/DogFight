/*
 * Copyright (c) 1996, 2013, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package java.awt.event;


import android.graphics.Point;
import android.os.Parcel;
import android.view.InputEvent;
import android.view.MotionEvent;

import java.io.IOException;
import java.io.ObjectInputStream;


public class MouseEvent  {


    public static final int MOUSE_FIRST         = 500;
    public int id;

    public static final int MOUSE_LAST          = 507;


    public static final int MOUSE_CLICKED = MOUSE_FIRST;


    public static final int MOUSE_PRESSED = 1 + MOUSE_FIRST; //Event.MOUSE_DOWN


    public static final int MOUSE_RELEASED = 2 + MOUSE_FIRST; //Event.MOUSE_UP


    public static final int MOUSE_MOVED = 3 + MOUSE_FIRST; //Event.MOUSE_MOVE


    public static final int MOUSE_ENTERED = 4 + MOUSE_FIRST; //Event.MOUSE_ENTER


    public static final int MOUSE_EXITED = 5 + MOUSE_FIRST; //Event.MOUSE_EXIT


    public static final int MOUSE_DRAGGED = 6 + MOUSE_FIRST; //Event.MOUSE_DRAG


    public static final int MOUSE_WHEEL = 7 + MOUSE_FIRST;


    public static final int NOBUTTON = 0;


    public static final int BUTTON1 = 1;


    public static final int BUTTON2 = 2;


    public static final int BUTTON3 = 3;


    int x;


    int y;


    private int xAbs;


    private int yAbs;


    int clickCount;


    int button = 1;


    boolean popupTrigger = false;

    /*
     * JDK 1.1 serialVersionUID
     */
    private static final long serialVersionUID = -991214153494842848L;


    private static int cachedNumberOfButtons;




    private static native void initIDs();

public MouseEvent(){
    super();

}


    public int getXOnScreen() {
        return xAbs;
    }


    public int getYOnScreen() {
        return yAbs;
    }


//    public MouseEvent(Component source, int id, long when, int modifiers,
//                      int x, int y, int clickCount, boolean popupTrigger,
//                      int button)
//    {
//        this(source, id, when, modifiers, x, y, 0, 0, clickCount, popupTrigger, button);
//        Point eventLocationOnScreen = new Point(0, 0);
//        try {
//            eventLocationOnScreen = source.getLocationOnScreen();
//            this.xAbs = eventLocationOnScreen.x + x;
//            this.yAbs = eventLocationOnScreen.y + y;
//        } catch (IllegalComponentStateException e){
//            this.xAbs = 0;
//            this.yAbs = 0;
//        }
//    }


//    public MouseEvent(Component source, int id, long when, int modifiers,
//                      int x, int y, int clickCount, boolean popupTrigger) {
//        this(source, id, when, modifiers, x, y, clickCount, popupTrigger, NOBUTTON);
//    }


    /* if the button is an extra button and it is released or clicked then in Xsystem its state
       is not modified. Exclude this button number from ExtModifiers mask.*/
    transient private boolean shouldExcludeButtonFromExtModifiers = false;




//    public MouseEvent(Component source, int id, long when, int modifiers,
//                      int x, int y, int xAbs, int yAbs,
//                      int clickCount, boolean popupTrigger, int button)
//    {
//        super(source, id, when, modifiers);
//        this.x = x;
//        this.y = y;
//        this.xAbs = xAbs;
//        this.yAbs = yAbs;
//        this.clickCount = clickCount;
//        this.popupTrigger = popupTrigger;
//        if (button < NOBUTTON){
//            throw new IllegalArgumentException("Invalid button value :" + button);
//        }
//        if (button > BUTTON3) {
//            if (!Toolkit.getDefaultToolkit().areExtraMouseButtonsEnabled()){
//                throw new IllegalArgumentException("Extra mouse events are disabled " + button);
//            } else {
//                if (button > cachedNumberOfButtons) {
//                    throw new IllegalArgumentException("Nonexistent button " + button);
//                }
//            }
//            // XToolkit: extra buttons are not reporting about their state correctly.
//            // Being pressed they report the state=0 both on the press and on the release.
//            // For 1-3 buttons the state value equals zero on press and non-zero on release.
//            // Other modifiers like Shift, ALT etc seem report well with extra buttons.
//            // The problem reveals as follows: one button is pressed and then another button is pressed and released.
//            // So, the getModifiersEx() would not be zero due to a first button and we will skip this modifier.
//            // This may have to be moved into the peer code instead if possible.
//
//            if (getModifiersEx() != 0) { //There is at least one more button in a pressed state.
//                if (id == MouseEvent.MOUSE_RELEASED || id == MouseEvent.MOUSE_CLICKED){
//                    shouldExcludeButtonFromExtModifiers = true;
//                }
//            }
//        }
//
//        this.button = button;
//
//        if ((getModifiers() != 0) && (getModifiersEx() == 0)) {
//            setNewModifiers();
//        } else if ((getModifiers() == 0) &&
//                (getModifiersEx() != 0 || button != NOBUTTON) &&
//                (button <= BUTTON3))
//        {
//            setOldModifiers();
//        }
//    }


    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }


    public Point getPoint() {
        int x;
        int y;
        synchronized (this) {
            x = this.x;
            y = this.y;
        }
        return new Point(x, y);
    }


    public synchronized void translatePoint(int x, int y) {
        this.x += x;
        this.y += y;
    }


    public int getClickCount() {
        return clickCount;
    }


    public int getButton() {
        return button;
    }


    public boolean isPopupTrigger() {
        return popupTrigger;
    }





    public String paramString() {
        StringBuilder str = new StringBuilder(80);

        switch(id) {
            case MOUSE_PRESSED:
                str.append("MOUSE_PRESSED");
                break;
            case MOUSE_RELEASED:
                str.append("MOUSE_RELEASED");
                break;
            case MOUSE_CLICKED:
                str.append("MOUSE_CLICKED");
                break;
            case MOUSE_ENTERED:
                str.append("MOUSE_ENTERED");
                break;
            case MOUSE_EXITED:
                str.append("MOUSE_EXITED");
                break;
            case MOUSE_MOVED:
                str.append("MOUSE_MOVED");
                break;
            case MOUSE_DRAGGED:
                str.append("MOUSE_DRAGGED");
                break;
            case MOUSE_WHEEL:
                str.append("MOUSE_WHEEL");
                break;
            default:
                str.append("unknown type");
        }



        return str.toString();
    }






    public int getDeviceId() {
        return 0;
    }


    public int getSource() {
        return 0;
    }


    public long getEventTime() {
        return 0;
    }


    public void writeToParcel(Parcel dest, int flags) {

    }
}
