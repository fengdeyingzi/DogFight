package java.awt.event;

import android.view.KeyEvent;
import android.view.View;

public abstract class KeyListener implements View.OnKeyListener {


    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     */
    public abstract void keyTyped(KeyEvent e);

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     */
    public abstract void keyPressed(KeyEvent e);

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     */
    public abstract void keyReleased(KeyEvent e);
}