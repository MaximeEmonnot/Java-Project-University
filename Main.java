import Exceptions.ProjectException;

public class Main {
    public static void main(String[] args){
        try{
            CoreSystem.Window.GetInstance();
            GameFiles.Game theGame = new GameFiles.Game();
            while(CoreSystem.Window.GetInstance().listensToEvents()){
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