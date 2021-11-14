package MenuSystem;

import java.awt.Rectangle;

import Exceptions.ProjectException;

import java.awt.Color;
import java.awt.Point;

/**
 * Boite de dialogue
 * Texte affiche dans un rectangle
 * @author Maxime Emonnot
 */
public class TextBox {
    /**
     * Constructeur TextBox
     * Initialise la boite de dialogue selon un rectangle de position
     * @author Maxime Emonnot
     * @param _rect Rectangle de position de la boite de dialogue
     */
    public TextBox(Rectangle _rect){
        rect = _rect;
    }
    /**
     * Constructeur TextBox
     * Initialise la boîte de dialogue selon un texte
     * @author Maxime Emonnot
     * @param _text Texte de la boite de dialogue
     */
    public TextBox(String _text){
        text = _text;
    }
    /**
     * Constructeur TextBox
     * Initialise la boite de dialogue selon un rectangle de position et un texte
     * @author Maxime Emonnot
     * @param _rect Rectangle de position de la boite de la dialogue
     * @param _text Texte de la boite de dialogue
     */
    public TextBox(Rectangle _rect, String _text){
        rect = _rect;
        text = _text;
    }
    
    /**
     * Affiche la boite de dialogue selon une couleur de texte, une couleur de contour et une couleur de fond donnees
     * @author Maxime Emonnot
     * @param textColor Couleur du texte de la boite de dialogue
     * @param boxColor Couleur du contour de la boite de dialogue
     * @param backgroundColor Couleur du fond de la boite de dialogue
     * @throws ProjectException Erreur lors de l'instanciation de GraphicsSystem
     */
    public void Draw(Color textColor, Color boxColor, Color backgroundColor) throws ProjectException{
        Draw(textColor, boxColor, backgroundColor, 0);
    }
    /**
     * Surcharge fonctionnelle. Affiche la boite de dialogue selon une couleur de texte, une couleur de contour, une couleur de fond et une priorite donnees
     * @author Maxime Emonnot
     * @param textColor Couleur du texte de la boite de dialogue
     * @param boxColor Couleur du contour de la boite de dialogue
     * @param backgroundColor Couleur du fond de la boite de dialogue
     * @param priority Priorite d'affichage de la boite de dialogue
     * @throws ProjectException Erreur lors de l'instanciation de GraphicsSystem
     */
    public void Draw(Color textColor, Color boxColor, Color backgroundColor, int priority) throws ProjectException{
        Draw(text, rect, textColor, boxColor, backgroundColor, priority);
    }  
    /**
     * Surchage fonctionnelle. Affiche la boite de dialogue selon un rectangle de position, une couleur de texte, une couleur de contour et une couleur de fond donnes
     * @author Maxime Emonnot
     * @param _rect Rectangle de position de la boite de dialogue
     * @param textColor Couleur du texte de la boite de dialogue
     * @param boxColor Couleur du contour de la boite de dialogue
     * @param backgroundColor Couleur du fond de la boite de dialogue
     * @throws ProjectException Erreur lors de l'instanciation de GraphicsSystem
     */
    public void Draw(Rectangle _rect, Color textColor, Color boxColor, Color backgroundColor)throws ProjectException {
        Draw(_rect, textColor, boxColor, backgroundColor, 0);
    }
    /**
     * Surchage fonctionnelle. Affiche la boite de dialogue selon un rectangle de position, une couleur de texte, une couleur de contour, une couleur de fond et une priorite donnes
     * @author Maxime Emonnot
     * @param _rect Rectangle de position de la boite de dialogue
     * @param textColor Couleur du texte de la boite de dialogue
     * @param boxColor Couleur du contour de la boite de dialogue
     * @param backgroundColor Couleur du fond de la boite de dialogue
     * @param priority Priorite d'affichage de la boite de dialogue
     * @throws ProjectException Erreur lors de l'instanciation de GraphicsSystem
     */
    public void Draw(Rectangle _rect, Color textColor, Color boxColor, Color backgroundColor, int priority)throws ProjectException {
        Draw(text, _rect, textColor, boxColor, backgroundColor, priority);
    }
    /**
     * Surcharge fonctionnelle. Affiche la boite de dialogue selon un texte, une couleur de texte, une couleur de contour et une couleur de fond donnes
     * @author Maxime Emonnot
     * @param _text Texte de la boite de dialogue
     * @param textColor Couleur du texte de la boite de dialogue
     * @param boxColor Couleur du contour de la boite de dialogue
     * @param backgroundColor Couleur du fond de la boite de dialogue
     * @throws ProjectException Erreur lors de l'instanciation de GraphicsSystem
     */
    public void Draw(String _text, Color textColor, Color boxColor, Color backgroundColor) throws ProjectException {
        Draw(_text, textColor, boxColor, backgroundColor, 0);
    }
    /**
     * Surcharge fonctionnelle. Affiche la boite de dialogue selon un texte, une couleur de texte, une couleur de contour, une couleur de fond et une priorite donnes
     * @author Maxime Emonnot
     * @param _text Texte de la boite de dialogue
     * @param textColor Couleur du texte de la boite de dialogue
     * @param boxColor Couleur du contour de la boite de dialogue
     * @param backgroundColor Couleur du fond de la boite de dialogue
     * @param priority Priorite d'affichage de la boite de dialogue
     * @throws ProjectException Erreur lors de l'instanciation de GraphicsSystem
     */
    public void Draw(String _text, Color textColor, Color boxColor, Color backgroundColor, int priority) throws ProjectException {
        Draw(_text, rect, textColor, boxColor, backgroundColor, priority);
    }
    /**
     * Surcharge fonctionnelle. Affiche la boite de dialogue selon un texte, un rectangle de position, une couleur de texte, une couleur de contour et une couleur de fond donnes
     * @author Maxime Emonnot
     * @param _text Texte de la boite de dialogue
     * @param _rect Rectangle de la boite de dialogue
     * @param textColor Couleur du texte de la boite de dialogue
     * @param boxColor Couleur du contour de la boite de dialogue
     * @param backgroundColor Couleur du fond de la boite de dialogue
     * @throws ProjectException Erreur lors de l'instanciation de GraphicsSystem
     */
    public void Draw(String _text, Rectangle _rect, Color textColor, Color boxColor, Color backgroundColor) throws ProjectException{
        Draw(_text, _rect, textColor, boxColor, backgroundColor, 0);
    }
    /**
     * Surcharge fonctionnelle. Affiche la boite de dialogue selon un texte, un rectangle de position, une couleur de texte, une couleur de contour, une couleur de fond et une priorite donnes
     * @author Maxime Emonnot
     * @param _text Texte de la boite de dialogue
     * @param _rect Rectangle de la boite de dialogue
     * @param textColor Couleur du texte de la boite de dialogue
     * @param boxColor Couleur du contour de la boite de dialogue
     * @param backgroundColor Couleur du fond de la boite de dialogue
     * @param priority Priorite d'affichage de la boite de dialogue
     * @throws ProjectException Erreur lors de l'instanciation de GraphicsSystem
     */
    public void Draw(String _text, Rectangle _rect, Color textColor, Color boxColor, Color backgroundColor, int priority) throws ProjectException{
        GraphicsEngine.GraphicsSystem.GetInstance().DrawFilledRect(_rect, backgroundColor, priority);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawRect(_rect, boxColor, priority + 1);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawText(_text, new Point(_rect.x + (int)((_rect.width - _text.length() * 8) / 2), _rect.y + _rect.height / 2), textColor, priority + 2);
    }

    /**
     * Methode override de Object
     * Deux TextBox sont egales si leur textes sont identiques
     * @author Maxime Emonnot
     */
    @Override
    public boolean equals(Object o){
        if (o instanceof TextBox){
            return text.equals(((TextBox)o).text);
        }
        return false;
    }
    /**
     * Methode override de Object
     * Le hashcode d'une TextBox est le hashcode de son texte
     * @author Maxime Emonnot
     */
    @Override
    public int hashCode(){
        return text.hashCode();
    }

    private Rectangle rect;
    private String text;
}
