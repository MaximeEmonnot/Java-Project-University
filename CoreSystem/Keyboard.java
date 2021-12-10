package CoreSystem;

import java.util.LinkedList;
import java.util.Queue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.BitSet;

/**
 * Gestion des entrees clavier
 * Encapsulation des differents elements de Swing lies au clavier pour faciliter la gestion desdits evenements
 * Permet d'identifier le type d'evenement et la touche pressee.
 * Utilise patron de conception Singleton pour etre unique dans toute l'application
 * @author Maxime Emonnot
 * @version 1.0.0
 */
public class Keyboard implements KeyListener {

    /**
     * Evenement clavier.
     * Contient le type d'evenement, et la touche associee a l'evenement.
     * @author Maxime Emonnot
     */
    static public class Event{
        /**
         * Type d'evenement clavier
         * @author Maxime Emonnot
         */
        public enum Type{
            Pressed,
            Released,
            None
        }
        /**
         * Contructeur Event
         * @author Maxime Emonnot
         * @param _keycode Code de la touche
         * @param _type Type d'evenement clavier
         */
        public Event(int _keycode, Type _type){
            keycode = _keycode;
            type = _type;
        }

        public final int keycode;
        public final Type type;
    }

    /**
     * Constructeur prive dans le cadre du Singleton
     * @author Maxime Emonnot
     */
    private Keyboard(){}

    /**
     * Recuperation d'instance, dans le cadre du patron de conception Singleton.
     * @author Maxime Emonnot
     * @return L'instance Keyboard de l'application
     */
    public synchronized static Keyboard GetInstance(){
        if (INSTANCE == null)
            INSTANCE = new Keyboard();
        return INSTANCE;
    }

    /**
     * Recuperation de l'etat d'une touche.
     * @author Maxime Emonnot
     * @param keycode Code de la touche du clavier a tester
     * @return Vrai si la touche associee au code est pressee, Faux sinon
     */
    public boolean KeyIsPressed(int keycode){
        ReadKey();
        return keystates.get(keycode);
    }
    /**
     * Lecture et suppression du dernier evenement clavier quelconque enregistre.
     * @author Maxime Emonnot
     * @return Le dernier evenement enregistre s'il a lieu, un evenement nul sinon.
     */
    public Event ReadKey(){
        if (keyBuffer.size() > 0){
            return keyBuffer.poll();
        }
        return new Event(0, Event.Type.None);
    }
    /**
     * Lecture et suppression du dernier evenement d'ecriture enregistre.
     * @author Maxime Emonnot
     * @return Le dernier caractere enregistre s'il a lieu, le caractere nul sinon.
     */
    public char ReadChar(){
        if (charBuffer.size() > 0){
            return charBuffer.remove();
        }
        return 0;
    }
    /**
     * Recuperation de l'etat de la queue d'evenement
     * @author Maxime Emonnot
     * @return Vrai si la queue est vide, Faux sinon
     */
    public boolean IsEmpty(){
        return keyBuffer.isEmpty();
    }

    /**
     * Methode privee utilisee pour decharger les evenements enregistres en trop (eviter une liste infinie d'informations).
     * @author Maxime Emonnot
     * @param <T> Genericite pour les deux types de liste (caracteres et touches clavier)
     * @param buffer Queue a limiter
     * @see Keyboard#ReadKey()
     * @see Keyboard#ReadChar()
     */
    private <T> void TrimBuffer(Queue<T> buffer){
        if (buffer.size() > sizeBuffer){
            buffer.remove();
        }
    }

    /**Methode override de Swing
     * Enregistre l'evenement d'un caractere entre
     * Met egalement a jour l'etat de la touche dans le tableau
     * @author Maxime Emonnot
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        keystates.set(e.getKeyCode(), true);
        charBuffer.add(e.getKeyChar());
        TrimBuffer(charBuffer);
    }
    
    /**Methode override de Swing
     * Enregistre l'evenement d'une entree clavier
     * Met egalement a jour l'etat de la touche dans le tableau
     * @author Maxime Emonnot
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        keystates.set(e.getKeyCode(), true);
        keyBuffer.add(new Event(e.getKeyCode(), Event.Type.Pressed));
        TrimBuffer(keyBuffer);
    }
    
    /**Methode override de Swing
     * Enregistre l'evenement d'une touche relachee
     * Met egalement a jour l'etat de la touche dans le tableau
     * @author Maxime Emonnot
     */
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
