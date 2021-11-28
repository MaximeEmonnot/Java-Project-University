package MenuSystem;

import java.awt.Rectangle;

import Exceptions.ProjectException;

import java.awt.Color;
import java.awt.Point;

/**
 * Bouton interactif.
 * Permet d'executer une fonction lambda.
 * @author Maxime Emonnot
 */
public class Button {

    /**
     * Constructeur du bouton.
     * Initialisation a partir d'un rectangle de position, d'un texte et d'une fonction lambda
     * @author Maxime Emonnot
     * @param _rect Rectangle de position du bouton
     * @param _text Texte descriptif du bouton
     * @param _func Fonction execution lors du clic du bouton
     */
    public Button(Rectangle _rect, String _text, Lambda _func)
    {
        rect = _rect;
        text = _text;
        func = _func;
    }

    /**
     * Recuperation de l'etat du bouton selon un evenement souris.
     * @author Maxime Emonnot
     * @param e Evenement souris
     * @return Vrai si l'evenement de la souris resulte d'un clic sur le bouton, Faux sinon
     */
    public boolean OnClick(CoreSystem.Mouse.EventType e){
        bIsClicked = false;
        if (rect.contains(CoreSystem.Mouse.GetInstance().GetMousePos())){
            if (e == CoreSystem.Mouse.EventType.LPress){
                bIsClicked = true;
            }
        }
        return bIsClicked;
    }

    /**
     * Execution de la fonction enregistree dans le constructeur.
     * @author Maxime Emonnot
     * @see Button#Button(Rectangle, String, Lambda)
     */
    public void ComputeFunction(){
        func.func();
    }
    
    /**
     * Affichage du bouton selon une couleur donnee
     * @author Maxime Emonnot
     * @param c Couleur du bouton a afficher
     * @throws ProjectException Erreur lors de l'instanciation de GraphicsSystem
     */
    public void Draw(Color c) throws ProjectException{
        Draw(c, 0);
    }
    /**
     * Surcharge fonctionnelle. Affichage du bouton selon une couleur et une priorite donnees
     * @author Maxime Emonnot
     * @param c Couleur du bouton a afficher
     * @param priority Priorite d'affichage du bouton
     * @throws ProjectException Erreur lors de l'instanciation de GraphicsSystem
     */
    public void Draw(Color c, int priority) throws ProjectException{
        Draw(c, rect, priority);
    }
    /**
     * Surcharge fonctionnelle. Affichage du bouton selon une couleur et un rectangle donnes
     * @author Maxime Emonnot
     * @param c Couleur du bouton a afficher
     * @param _rect Nouveau rectangle pour afficher le bouton
     * @throws ProjectException Erreur lors de l'instanciation de GraphicsSystem
     */
    public void Draw(Color c, Rectangle _rect) throws ProjectException{
        Draw(c, _rect, 0);
    }
    /**
     * Surcharge fonctionnelle. Affichage du bouton selon une couleur, un rectangle et une priorite
     * @author Maxime Emonnot
     * @param c Couleur du bouton a afficher
     * @param _rect Nouveau rectangle pour afficher le bouton
     * @param priority Priorite d'affichage du bouton
     * @throws ProjectException Erreur lors de l'instanciation de GraphicsSystem
     */
    public void Draw(Color c, Rectangle _rect, int priority) throws ProjectException{
        rect = _rect;
        GraphicsEngine.GraphicsSystem.GetInstance().DrawFilledRoundRect(_rect, 10, 10, c, priority);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawRoundRect(_rect, 10, 10, Color.BLACK, priority + 1);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText(text, new Point(_rect.x + (int)((_rect.width - text.length() * 8) / 2), _rect.y + 3 *_rect.height / 5), Color.BLACK, priority + 2);
    }

    /**
     * Recuperation et reinitialisation de l'etat du bouton.
     * @author Maxime Emonnot
     * @return Vrai si le bouton vient d'etre clique, sinon Faux
     */
    public boolean IsClicked(){
        boolean out = bIsClicked;
        bIsClicked = false;
        return out;
    }

    /**
     * Recuperation du texte du bouton
     * @author Maxime Emonnot
     * @return Le texte de description du bouton
     */
    public String GetText(){
        return text;
    }

    /**
     * Methode override de Object.
     * Deux boutons sont egaux s'ils ont le meme texte de description
     * @author Maxime Emonnot
     */
    @Override
    public boolean equals(Object o){
        if (o instanceof Button){
            return text.equals(((Button)o).text);
        }
        return false;
    }

    /**
     * Methode override de Object
     * Le hashcode d'un bouton depend de son texte de description
     * @author Maxime Emonnot
     */
    @Override
    public int hashCode(){
        return text.hashCode();
    }

    private boolean bIsClicked = false;
    private Rectangle rect;
    private final String text;
    private Lambda func;
}
