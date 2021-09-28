package CoreSystem;

import java.util.LinkedList;
import java.util.Queue;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseWheelListener, MouseMotionListener {
    public enum EventType{
        LCLick,
        LPress,
        LRelease,
        MClick,
        MPress,
        MRelease,
        RClick,
        RPress,
        RRelease,
        WheelUp,
        WheelDown,
        Move,
        None
    }

    private Mouse(){}

    public synchronized static Mouse GetInstance(){
        if (INSTANCE == null)
            INSTANCE = new Mouse();
        return INSTANCE;
    }

    public EventType Read(){
        if (buffer.size() > 0){
            return buffer.remove();
        }
        return EventType.None;
    }

    public int GetMousePosX(){
        return x;
    }
    public int GetMousePosY(){
        return y;
    }
    public Point GetMousePos(){
        return new Point(x, y);
    }

    public boolean LeftIsPressed(){
        return bLeftIsPressed;
    }
    public boolean MiddleIsPressed(){
        return bMiddleIsPressed;
    }
    public boolean RightIsPressed(){
        return bRightIsPressed;
    }
    private void TrimBuffer(){
        if (buffer.size() > sizeBuffer){
            buffer.remove();
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        x = e.getX();
        y = e.getY();

        switch(e.getButton()){
        case MouseEvent.BUTTON1:
            buffer.add(EventType.LCLick);
            break;
        case MouseEvent.BUTTON2:
            buffer.add(EventType.MClick);
            break;
        case MouseEvent.BUTTON3:
            buffer.add(EventType.RClick);
            break;
        default:
            break;
        }
        TrimBuffer();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        x = e.getX();
        y = e.getY();

        switch(e.getButton()){
        case MouseEvent.BUTTON1:
            buffer.add(EventType.LPress);
            bLeftIsPressed = true;
            break;
        case MouseEvent.BUTTON2:
            buffer.add(EventType.MPress);
            bMiddleIsPressed = true;
            break;
        case MouseEvent.BUTTON3:
            buffer.add(EventType.RPress);
            bRightIsPressed = true;
            break;
        default:
            break;
        }
        TrimBuffer();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        x = e.getX();
        y = e.getY();

        switch(e.getButton()){
        case MouseEvent.BUTTON1:
            buffer.add(EventType.LRelease);
            bLeftIsPressed = false;
            break;
        case MouseEvent.BUTTON2:
            buffer.add(EventType.MRelease);
            bMiddleIsPressed = false;
            break;
        case MouseEvent.BUTTON3:
            buffer.add(EventType.RRelease);
            bRightIsPressed = false;
            break;
        default:
            break;
        }
        TrimBuffer();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        x = e.getX();
        y = e.getY();

        buffer.add(EventType.Move);
        TrimBuffer();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        // TODO Auto-generated method stub
        x = e.getX();
        y = e.getY();
        if (e.getWheelRotation() < 0){
            buffer.add(EventType.WheelUp);
        }
        else{
            buffer.add(EventType.WheelDown);
        }
        TrimBuffer();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        x = e.getX();
        y = e.getY();
        buffer.add(EventType.Move);
        TrimBuffer();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        x = e.getX();
        y = e.getY();
        buffer.add(EventType.Move);
        TrimBuffer();
    }

    private static Mouse INSTANCE = null;
    
    private boolean bLeftIsPressed = false;
    private boolean bMiddleIsPressed = false;
    private boolean bRightIsPressed = false;
    private int x = 0;
    private int y = 0;
    private final static short sizeBuffer = 4;
    private Queue<EventType> buffer = new LinkedList<EventType>();
}
