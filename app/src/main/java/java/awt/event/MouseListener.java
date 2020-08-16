package java.awt.event;

public interface MouseListener  {

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     * 在组件上单击（按下并释放）鼠标按钮时调用。
     */
    public void mouseClicked(MouseEvent e);

    /**
     * Invoked when a mouse button has been pressed on a component.
     * 在组件上按下鼠标按下时调用。
     */
    public void mousePressed(MouseEvent e);

    /**
     * Invoked when a mouse button has been released on a component.
     * 在组件上释放鼠标按钮时调用。
     */
    public void mouseReleased(MouseEvent e);

    /**
     * Invoked when the mouse enters a component.
     * 当鼠标进入组件时调用。
     */
    public void mouseEntered(MouseEvent e);

    /**
     * Invoked when the mouse exits a component.
     * 当鼠标退出组件时调用。
     */
    public void mouseExited(MouseEvent e);
}