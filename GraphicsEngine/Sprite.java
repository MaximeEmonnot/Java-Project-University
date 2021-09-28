package GraphicsEngine;

import javax.swing.ImageIcon;
import java.awt.Image;

public class Sprite {
    public Sprite(String path){
        sprite = new ImageIcon(path);
    }

    public Image GetSprite(){
        return sprite.getImage();
    }  
    public int GetWidth(){
        return sprite.getIconWidth();
    }
    public int GetHeight(){
        return sprite.getIconHeight();
    }

    private ImageIcon sprite;
}
