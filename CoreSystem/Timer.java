package CoreSystem;

public class Timer {
    private Timer(){ 
        oldTime = System.currentTimeMillis();
    }
    
    public synchronized static Timer GetInstance(){
        if (INSTANCE == null)
            INSTANCE = new Timer();
        return INSTANCE;
    }

    public void Update(){
        long newTime = System.currentTimeMillis();
        deltaTime = (newTime - oldTime) / 1000.0f;
        oldTime = newTime;
    }

    public float DeltaTime(){
        return deltaTime;
    }

    private static Timer INSTANCE = null;

    private long oldTime;
    private float deltaTime = 0.0f;
}
