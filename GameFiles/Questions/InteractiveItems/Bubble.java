package GameFiles.Questions.InteractiveItems;

import java.awt.*;

import Exceptions.ProjectException;
import GraphicsEngine.Animation;
import GraphicsEngine.Sprite;

/**
 * Classe Bubble
 * <p>Bulle utilisee dans le cadre de BubbleGiftQuestion
 * <p>Apparition lors du clic sur un cadeau
 * @author Maxime Emonnot
 * @see Gift
 * @version 1.4.0
 */
public class Bubble {

    /**
     * Contructeur Bubble
     * <p>Initialise un vecteur aleatoire pour le deplacement de la bulle
     * <p>Initialise l'aniamtion de la bulle
     * @author Maxime Emonnot
     * @param startingDest Rectangle de départ (zone)
     * @param bIsRight Validite du cadeau (pour couleur de la bulle)
     */
    public Bubble(Rectangle startingDest, boolean bIsRight){
        if (bIsRight){
            anim = new Animation(new Rectangle(0, 32, 32, 32), 4, sprite, 0.2f);
        }
        else{
            anim = new Animation(new Rectangle(0, 0, 32, 32), 4, sprite, 0.2f);
        }

        int[] sizes = {16, 24, 32, 48};
        int[] dir = {-1, 1};

        vel = new Point(((int)(Math.random() * 4) + 1) * dir[(int)(Math.random() * 2)], ((int)(Math.random() * 4) + 1) * dir[(int)(Math.random() * 2)]);
        destRect.x = startingDest.x + (int)(Math.random() * startingDest.width);
        destRect.y = startingDest.y + (int)(Math.random() * startingDest.height); 
        destRect.width = sizes[(int)(Math.random() * 4)];
        destRect.height = destRect.width;
    }

    /**
     * Mise a jour de la position de la bulle, selon le vecteur aleatoire
     * @author Maxime Emonnot
     */
    public void Update(){
        destRect.x += (int)vel.getX();
        destRect.y += (int)vel.getY();
    }

    /**
     * Affichage de la bulle
     * @author Maxime Emonnot
     * @throws ProjectException Erreur lors de l'instanciation de GraphicsSystem
     */
    public void Draw() throws ProjectException{
        anim.Draw(destRect);
    }

    private static final Sprite sprite = new Sprite("Images/bubbles.png");
    private final Animation anim;
    private Rectangle destRect = new Rectangle(0, 0, 0, 0);
    private final Point vel;
}
