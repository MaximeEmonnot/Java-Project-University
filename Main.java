import Exceptions.ProjectException;

public class Main {
    public static void main(String[] args){
        try{
            long beginDelay = System.currentTimeMillis();
            CoreSystem.Window.GetInstance();
            while(System.currentTimeMillis() - beginDelay < 200){
            }
            GraphicsEngine.GraphicsSystem.GetInstance();
            while(System.currentTimeMillis() - beginDelay < 400){
            }
            
            GameFiles.Game theGame = new GameFiles.Game();
            while(true){
                long beginFrame = System.currentTimeMillis();
                theGame.Go();
                while(System.currentTimeMillis() - beginFrame < 17){}
            }
        }
        catch(Exceptions.ProjectException e){
            ProjectException.showMessageBox(e, "Project Exception has been caught");
        }
        catch (Exception e){
            ProjectException.showMessageBox(e, "Standard Exception has been caught");
        }
    }
}