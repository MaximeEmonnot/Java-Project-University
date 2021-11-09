package CoreSystem;

import java.util.LinkedList;
import java.util.Queue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.BitSet;

public class Keyboard implements KeyListener {

    static public class Event{
        public enum Type{
            Pressed,
            Released,
            None
        }
        public Event(int _keycode, Type _type){
            keycode = _keycode;
            type = _type;
        }

        public final int keycode;
        public final Type type;
    }

    private Keyboard(){}

    public synchronized static Keyboard GetInstance(){
        if (INSTANCE == null)
            INSTANCE = new Keyboard();
        return INSTANCE;
    }

    public boolean KeyIsPressed(int keycode){
        ReadKey();
        return keystates.get(keycode);
    }
    public Event ReadKey(){
        if (keyBuffer.size() > 0){
            return keyBuffer.poll();
        }
        return new Event(0, Event.Type.None);
    }
    public char ReadChar(){
        if (charBuffer.size() > 0){
            return charBuffer.remove();
        }
        return 0;
    }
    public boolean IsEmpty(){
        return keyBuffer.isEmpty();
    }

    private <T> void TrimBuffer(Queue<T> buffer){
        if (buffer.size() > sizeBuffer){
            buffer.remove();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        keystates.set(e.getKeyCode(), true);
        charBuffer.add(e.getKeyChar());
        TrimBuffer(charBuffer);
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        keystates.set(e.getKeyCode(), true);
        keyBuffer.add(new Event(e.getKeyCode(), Event.Type.Pressed));
        TrimBuffer(keyBuffer);
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        keystates.set(e.getKeyCode(), false);
        keyBuffer.add(new Event(e.getKeyCode(), Event.Type.Released));
        TrimBuffer(keyBuffer);
    }
    
    private static Keyboard INSTANCE = null;

    private final static short sizeBuffer = 4;
    private BitSet keystates = new BitSet(256);
    private Queue<Event> keyBuffer = new LinkedList<Event>();
    private Queue<Character> charBuffer = new LinkedList<Character>();
}
