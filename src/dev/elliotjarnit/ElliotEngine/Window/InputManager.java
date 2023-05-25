package src.dev.elliotjarnit.ElliotEngine.Window;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class InputManager {
    private KeyboardDispatcher dispatcher;
    public enum Key {
        A, B, C, D, E, F, G, H, I, J, K, L, M,
        N, O, P, Q, R, S, T, U, V, W, X, Y, Z,
        NUM_0, NUM_1, NUM_2, NUM_3, NUM_4,
        NUM_5, NUM_6, NUM_7, NUM_8, NUM_9,
        ESCAPE, F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12,
        GRAVE, MINUS, EQUALS, BACKSPACE, TAB, OPEN_BRACKET, CLOSE_BRACKET,
        BACKSLASH, CAPS_LOCK, SEMICOLON, QUOTE, ENTER, SHIFT, COMMA, PERIOD,
        SLASH, SPACE, INSERT, DELETE, HOME, END, PAGE_UP, PAGE_DOWN,
        PRINT_SCREEN, SCROLL_LOCK, PAUSE, UP, DOWN, LEFT, RIGHT,
        NUM_LOCK, NUMPAD_0, NUMPAD_1, NUMPAD_2, NUMPAD_3, NUMPAD_4, NUMPAD_5,
        NUMPAD_6, NUMPAD_7, NUMPAD_8, NUMPAD_9, NUMPAD_ADD, NUMPAD_SUBTRACT,
        NUMPAD_MULTIPLY, NUMPAD_DIVIDE, NUMPAD_DECIMAL, NUMPAD_ENTER,
        CONTROL, ALT, WINDOWS, MENU;
    }

    private final Map<Key, Boolean> keyDown = new HashMap<>();

    public void setup() {
        for (Key key : Key.values()) {
            keyDown.put(key, false);
        }
    }

    public boolean isKeyDown(Key key) {
        synchronized (this) {
            return keyDown.get(key);
        }
    }

    private class KeyboardDispatcher implements KeyEventDispatcher {
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            Key key = keyMap.get(e.getKeyCode());
            if (key != null) {
                if (e.getID() == KeyEvent.KEY_PRESSED) {
                    keyDown.put(key, true);
                } else if (e.getID() == KeyEvent.KEY_RELEASED) {
                    keyDown.put(key, false);
                }
                return true;
            }
            return false;
        }
    }

    private final Map<Integer, Key> keyMap = new HashMap<Integer, Key>() {{
        put(KeyEvent.VK_A, Key.A);
        put(KeyEvent.VK_B, Key.B);
        put(KeyEvent.VK_C, Key.C);
        put(KeyEvent.VK_D, Key.D);
        put(KeyEvent.VK_E, Key.E);
        put(KeyEvent.VK_F, Key.F);
        put(KeyEvent.VK_G, Key.G);
        put(KeyEvent.VK_H, Key.H);
        put(KeyEvent.VK_I, Key.I);
        put(KeyEvent.VK_J, Key.J);
        put(KeyEvent.VK_K, Key.K);
        put(KeyEvent.VK_L, Key.L);
        put(KeyEvent.VK_M, Key.M);
        put(KeyEvent.VK_N, Key.N);
        put(KeyEvent.VK_O, Key.O);
        put(KeyEvent.VK_P, Key.P);
        put(KeyEvent.VK_Q, Key.Q);
        put(KeyEvent.VK_R, Key.R);
        put(KeyEvent.VK_S, Key.S);
        put(KeyEvent.VK_T, Key.T);
        put(KeyEvent.VK_U, Key.U);
        put(KeyEvent.VK_V, Key.V);
        put(KeyEvent.VK_W, Key.W);
        put(KeyEvent.VK_X, Key.X);
        put(KeyEvent.VK_Y, Key.Y);
        put(KeyEvent.VK_Z, Key.Z);
        put(KeyEvent.VK_0, Key.NUM_0);
        put(KeyEvent.VK_1, Key.NUM_1);
        put(KeyEvent.VK_2, Key.NUM_2);
        put(KeyEvent.VK_3, Key.NUM_3);
        put(KeyEvent.VK_4, Key.NUM_4);
        put(KeyEvent.VK_5, Key.NUM_5);
        put(KeyEvent.VK_6, Key.NUM_6);
        put(KeyEvent.VK_7, Key.NUM_7);
        put(KeyEvent.VK_8, Key.NUM_8);
        put(KeyEvent.VK_9, Key.NUM_9);
        put(KeyEvent.VK_ESCAPE, Key.ESCAPE);
        put(KeyEvent.VK_F1, Key.F1);
        put(KeyEvent.VK_F2, Key.F2);
        put(KeyEvent.VK_F3, Key.F3);
        put(KeyEvent.VK_F4, Key.F4);
        put(KeyEvent.VK_F5, Key.F5);
        put(KeyEvent.VK_F6, Key.F6);
        put(KeyEvent.VK_F7, Key.F7);
        put(KeyEvent.VK_F8, Key.F8);
        put(KeyEvent.VK_F9, Key.F9);
        put(KeyEvent.VK_F10, Key.F10);
        put(KeyEvent.VK_F11, Key.F11);
        put(KeyEvent.VK_F12, Key.F12);
        put(KeyEvent.VK_BACK_QUOTE, Key.GRAVE);
        put(KeyEvent.VK_MINUS, Key.MINUS);
        put(KeyEvent.VK_EQUALS, Key.EQUALS);
        put(KeyEvent.VK_BACK_SPACE, Key.BACKSPACE);
        put(KeyEvent.VK_TAB, Key.TAB);
        put(KeyEvent.VK_OPEN_BRACKET, Key.OPEN_BRACKET);
        put(KeyEvent.VK_CLOSE_BRACKET, Key.CLOSE_BRACKET);
        put(KeyEvent.VK_BACK_SLASH, Key.BACKSLASH);
        put(KeyEvent.VK_CAPS_LOCK, Key.CAPS_LOCK);
        put(KeyEvent.VK_SEMICOLON, Key.SEMICOLON);
        put(KeyEvent.VK_QUOTE, Key.QUOTE);
        put(KeyEvent.VK_ENTER, Key.ENTER);
        put(KeyEvent.VK_SHIFT, Key.SHIFT);
        put(KeyEvent.VK_COMMA, Key.COMMA);
        put(KeyEvent.VK_PERIOD, Key.PERIOD);
        put(KeyEvent.VK_SLASH, Key.SLASH);
        put(KeyEvent.VK_SPACE, Key.SPACE);
        put(KeyEvent.VK_INSERT, Key.INSERT);
        put(KeyEvent.VK_DELETE, Key.DELETE);
        put(KeyEvent.VK_HOME, Key.HOME);
        put(KeyEvent.VK_END, Key.END);
        put(KeyEvent.VK_PAGE_UP, Key.PAGE_UP);
        put(KeyEvent.VK_PAGE_DOWN, Key.PAGE_DOWN);
        put(KeyEvent.VK_PRINTSCREEN, Key.PRINT_SCREEN);
        put(KeyEvent.VK_SCROLL_LOCK, Key.SCROLL_LOCK);
        put(KeyEvent.VK_PAUSE, Key.PAUSE);
        put(KeyEvent.VK_UP, Key.UP);
        put(KeyEvent.VK_DOWN, Key.DOWN);
        put(KeyEvent.VK_LEFT, Key.LEFT);
        put(KeyEvent.VK_RIGHT, Key.RIGHT);
        put(KeyEvent.VK_NUM_LOCK, Key.NUM_LOCK);
        put(KeyEvent.VK_NUMPAD0, Key.NUMPAD_0);
        put(KeyEvent.VK_NUMPAD1, Key.NUMPAD_1);
        put(KeyEvent.VK_NUMPAD2, Key.NUMPAD_2);
        put(KeyEvent.VK_NUMPAD3, Key.NUMPAD_3);
        put(KeyEvent.VK_NUMPAD4, Key.NUMPAD_4);
        put(KeyEvent.VK_NUMPAD5, Key.NUMPAD_5);
        put(KeyEvent.VK_NUMPAD6, Key.NUMPAD_6);
        put(KeyEvent.VK_NUMPAD7, Key.NUMPAD_7);
        put(KeyEvent.VK_NUMPAD8, Key.NUMPAD_8);
        put(KeyEvent.VK_NUMPAD9, Key.NUMPAD_9);
        put(KeyEvent.VK_ADD, Key.NUMPAD_ADD);
        put(KeyEvent.VK_SUBTRACT, Key.NUMPAD_SUBTRACT);
        put(KeyEvent.VK_MULTIPLY, Key.NUMPAD_MULTIPLY);
        put(KeyEvent.VK_DIVIDE, Key.NUMPAD_DIVIDE);
        put(KeyEvent.VK_DECIMAL, Key.NUMPAD_DECIMAL);
        put(KeyEvent.VK_CONTROL, Key.CONTROL);
        put(KeyEvent.VK_ALT, Key.ALT);
        put(KeyEvent.VK_WINDOWS, Key.WINDOWS);
        put(KeyEvent.VK_CONTEXT_MENU, Key.MENU);
    }};
}