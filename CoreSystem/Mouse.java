package CoreSystem;

import java.util.LinkedList;
import java.util.Queue;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseMotionListener;

/**
 * <p>Gestion des entrees souris.<p/>
 * <p>Encapsulation des differents evenements Swing lies a la souris pour faciliter la gestion desdits evenements.<p/>
 * <p>Permet d'identifier le type d'evenement ainsi que la position de la souris.<p/>
 * Utilise le patron de conception Singleton pour etre unique dans toute l'application.
 * @author Maxime Emonnot
 */
public class Mouse implements MouseListener, MouseWheelListener, MouseMotionListener {
    
    /**
     *<p>Enumerator possedant les differents types d'evenement souris.<p/>
     * Adaptation des evenements Swing pour l'encapsulation
     * @author Maxime Emonnot
     */
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

    /**
     * Constructeur prive dans le cadre du Singleton.
     * @author Maxime Emonnot
     */
    private Mouse(){}

    /**
     * Recuperation d'instance, dans le cadre du patron de conception Singleton.
     * @author Maxime Emonnot
     * @return L'instance Mouse de l'application
     */
    public synchronized static Mouse GetInstance(){
        if (INSTANCE == null)
            INSTANCE = new Mouse();
        return INSTANCE;
    }

    /**
     * Lecture et suppression du dernier evenement enregistre
     * @author Maxime Emonnot
     * @return Le dernier evenement souris s'il a lieu, l'evenement nul sinon.
     */
    public EventType Read(){
        if (buffer.size() > 0){
            return buffer.poll();
        }
        return EventType.None;
    }

    /**
     * Recuperation de la position horizontale de la souris.
     * @author Maxime Emonnot
     * @return L'abscisse du point de la souris.
     */
    public int GetMousePosX(){
        return x;
    }
    /**
     * Recuperation de la position verticale de la souris
     * @author Maxime Emonnot
     * @return L'ordonnee du point de la souris
     */
    public int GetMousePosY(){
        return y;
    }
    /**
     * Recuperation des coordonnees de la souris.
     * @author Maxime Emonnot
     * @return Le point de la souris
     */
    public Point GetMousePos(){
        return new Point(x, y);
    }

    /**
     * Recuperation de l'etat du bouton gauche de la souris.
     * @author Maxime Emonnot
     * @return Vrai si le bouton est presse, Faux sinon
     */
    public boolean LeftIsPressed(){
        return bLeftIsPressed;
    }
    /**
     * Recuperation de l'etat du bouton du milieu de la souris.
     * @author Maxime Emonnot
     * @return Vrai si le bouton est presse, Faux sinon
     */
    public boolean MiddleIsPressed(){
        return bMiddleIsPressed;
    }
    /**
     * Recuperation de l'etat du bouton droit de la souris.
     * @author Maxime Emonnot
     * @return Vrai si le bouton est presse, Faux sinon
     */
    public boolean RightIsPressed(){
        return bRightIsPressed;
    }
    /**
     * Methode privee utilisee pour decharger les evenements enregistres en trop (eviter une liste infinie d'informations).
     * @author Maxime Emonnot
     * @see Mouse#Read()
     */
    private void TrimBuffer(){
        if (buffer.size() > sizeBuffer){
            buffer.remove();
        }
    }
    /**
     * Methode override de Swing. 
     * Enregistre l'evenement Click pour les differents boutons de la souris.
     * Enregistre egalement la position du click (position de la souris au moment du click)
     * @author Maxime Emonnot
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        x = e.getX() - 10;
        y = e.getY() - 30;

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

    /**
     * Methode override de Swing.
     * Enregistre l'evenement Press pour les differents boutons de la souris.
     * Enregistre egalement la position du press (position de la souris lorsque l'on presse un bouton)
     * @author Maxime Emonnot
     */
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        x = e.getX() - 10;
        y = e.getY() - 30;

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

    /**
     * Methode override de Swing
     * Enregistre l'evenement Release pour les differents boutons de la souris.
     * Enregistre egalement la position du release (position de la souris au moment du relachement d'un bouton)
     * @author Maxime Emonnot
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        x = e.getX() - 10;
        y = e.getY() - 30;

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

    /**
     * Methode override de Swing
     * Enregistre l'evenement Move pour l'entree de la souris.
     * Enregistre egalement la position de la souris.
     * @author Maxime Emonnot
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        x = e.getX() - 10;
        y = e.getY() - 30;

        buffer.add(EventType.Move);
        TrimBuffer();
    }

    /**
     * Methode override de Swing
     * @author Maxime Emonnot
     */
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }
    /**
     * Methode override de Swing
     * Enregistre l'evenement MouseWheel pour l'etat de la molette.
     * Enregistre egalement la position au moment du scroll (vers le haut ou vers le bas)
     * @author Maxime Emonnot
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        // TODO Auto-generated method stub
        x = e.getX() - 10;
        y = e.getY() - 30;
        if (e.getWheelRotation() < 0){
            buffer.add(EventType.WheelUp);
        }
        else{
            buffer.add(EventType.WheelDown);
        }
        TrimBuffer();
    }

    /**
     * Methode override de Swing
     * Enregistre l'evenement Move lors d'un drag de la souris.
     * Enregistre egalement la position lors du drag de la souris.
     * @author Maxime Emonnot
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        x = e.getX() - 10;
        y = e.getY() - 30;
        buffer.add(EventType.Move);
        TrimBuffer();
    }

    /**
     * Methode override de Swing
     * Enregistre l'evenement Move lors du deplacement de la souris.
     * Enregistre egalement la position de la souris.
     * @author Maxime Emonnot
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        x = e.getX() - 10;
        y = e.getY() - 30;
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
