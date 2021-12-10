package GraphicsEngine;

import javax.swing.ImageIcon;
import java.awt.Image;

/**
 * Gestion des Sprites
 * <p>Ouvre une image qui sera utilisee et affichee
 * @author Maxime Emonnot
 * @version 1.0.0
 */
public class Sprite {
    /**
     * Constructeur.
     * <p>Permet d'ouvrir une image pour l'enregistrer et l'afficher plus tard
     * @author Maxime Emonnot
     * @param path Chemin de l'image 
     */
    public Sprite(String path){
        sprite = new ImageIcon(path);
    }

    /**
     * Recuperation de l'image
     * @author Maxime Emonnot
     * @return L'image ouverte dans le constructeur
     */
    public Image GetSprite(){
        return sprite.getImage();
    }  
    /**
     * Recuperation de la largeur de l'image
     * @author Maxime Emonnot
     * @return La largeur de l'image ouverte dans le constructeur
     */
    public int GetWidth(){
        return sprite.getIconWidth();
    }
    /**
     * Recuperation de la hauteur de l'image
     * @author Maxime Emonnot
     * @return La hauteur de l'image ouverte dans le constructeur
     */
    public int GetHeight(){
        return sprite.getIconHeight();
    }

    private ImageIcon sprite;
}
