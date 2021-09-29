package MenuSystem;

import Exceptions.ProjectException;

public interface IMenu {
    public abstract void Update();
    public abstract void Update(CoreSystem.Mouse.EventType e);
    public abstract void Draw() throws ProjectException;
}
