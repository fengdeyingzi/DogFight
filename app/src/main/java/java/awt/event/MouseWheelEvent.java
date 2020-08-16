package java.awt.event;


public class MouseWheelEvent extends MouseEvent {


     public static final int WHEEL_UNIT_SCROLL = 0;


     public static final int WHEEL_BLOCK_SCROLL = 1;


    int scrollType;


    int scrollAmount;


    int wheelRotation;


    double preciseWheelRotation;

    /*
     * serialVersionUID
     */

    private static final long serialVersionUID = 6459879390515399677L;








    public int getScrollType() {
        return scrollType;
    }


    public int getScrollAmount() {
        return scrollAmount;
    }


    public int getWheelRotation() {
        return wheelRotation;
    }


    public double getPreciseWheelRotation() {
        return preciseWheelRotation;
    }


    public int getUnitsToScroll() {
        return scrollAmount * wheelRotation;
    }

    public String paramString() {
        String scrollTypeStr = null;

        if (getScrollType() == WHEEL_UNIT_SCROLL) {
            scrollTypeStr = "WHEEL_UNIT_SCROLL";
        }
        else if (getScrollType() == WHEEL_BLOCK_SCROLL) {
            scrollTypeStr = "WHEEL_BLOCK_SCROLL";
        }
        else {
            scrollTypeStr = "unknown scroll type";
        }
        return super.paramString()+",scrollType="+scrollTypeStr+
                ",scrollAmount="+getScrollAmount()+",wheelRotation="+
                getWheelRotation()+",preciseWheelRotation="+getPreciseWheelRotation();
    }
}
