package CoreSystem;

/**
 * Permet de gérer un Timer. Singleton pour que ce Timer soit unique pour toute l'application
 * @author Maxime Emonnot
 */
public class Timer {
    /**
     * Constructeur prive de Timer. Initalisation du Timer.
     * @author Maxime Emonnot
     */
    private Timer(){ 
        oldTime = System.currentTimeMillis();
    }
    
    /**
     * Dans le cadre du patron de conception Singleton.
     * @author Maxime Emonnot 
     * @return L'instance de Timer    
     */
    public synchronized static Timer GetInstance(){
        if (INSTANCE == null)
            INSTANCE = new Timer();
        return INSTANCE;
    }
    
    /**
     * Mise a jour du timer. Methode executee a chaque frame.
     * @author Maxime Emonnot
     */
    public void Update(){
        long newTime = System.currentTimeMillis();
        deltaTime = (newTime - oldTime) / 1000.0f;
        oldTime = newTime;
    }

    /**
     * Permet de recuperer le temps ecoule depuis la derniere frame. Tres utilise pour les differentes
     * animations et pour la mise a jour de l'affichage
     * @author Maxime Emonnot
     * @return Le temps ecoule depuis le dernier appel de Update()
     * @see Timer#Update
     */
    public float DeltaTime(){
        return deltaTime;
    }

    private static Timer INSTANCE = null;

    private long oldTime;
    private float deltaTime = 0.0f;
}
