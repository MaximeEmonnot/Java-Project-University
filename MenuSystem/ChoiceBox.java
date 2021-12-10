package MenuSystem;

import java.awt.Rectangle;

import Exceptions.ProjectException;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Implementation de JComboBox.
 * Permet la selection de plusieurs chaines de caracteres.
 * @author Maxime Emonnot
 * @version 1.2.0
 */
public class ChoiceBox {
    
    /**
     * Constructeur de la ChoiceBox
     * Intialisation a partir d'un rectangle de position et d'un texte de description
     * @author Maxime Emonnot
     * @param _rect Rectangle de position de la ChoiceBox
     * @param _description Description de la ChoiceBox
     */
    public ChoiceBox(Rectangle _rect, String _description){
        rect = _rect;
        description = _description;
    }

    /**
     * Misea jour de la ChoiceBox
     * Depend de l'evenement souris passe en parametre : si la souris clique dans le rectangle, les choix s'affichent, et si la souris clique sur l'un des choix, il est selectionne
     * @author Maxime Emonnot
     * @param e Evenement souris
     */
    public void Update(CoreSystem.Mouse.EventType e){
        if (bIsExpanding){
            Iterator<String> itr = choices.iterator();
            int i = 1;
            while(itr.hasNext()){
                String newChoice = itr.next();
                Rectangle choiceRect = new Rectangle(rect.x, rect.y, rect.width, rect.height);
                if (rect.y < 300){
                   choiceRect.y += i * choiceRect.height;
                   }
                else if (rect.y >= 300){
                   choiceRect.y -= i * choiceRect.height;
                }
                if (e == CoreSystem.Mouse.EventType.LPress && choiceRect.contains(CoreSystem.Mouse.GetInstance().GetMousePos())){
                    choice = newChoice;
                    break;
                }
                i++;
            }
        }
        if(e == CoreSystem.Mouse.EventType.LPress){
            bIsExpanding = !bIsExpanding && rect.contains(CoreSystem.Mouse.GetInstance().GetMousePos());
        }
    }
    /**
     * Affiche de la ChoiceBox et des differents choix
     * Priorite d'affichage par defaut a 0
     * @author Maxime Emonnot
     * @throws ProjectException Erreur d'instanciation de GraphicsSystem
     */
    public void Draw() throws ProjectException{
        Draw(0);
    }
    /**
     * Surcharge fonctionnelle. Affiche de la ChoiceBox et des differents choix selon une priorite donnee
     * @author Maxime Emonnot
     * @param priority Priorite d'affichage de la ChoiceBox
     * @throws ProjectException Erreur d'instanciation de GraphicsSystem
     */
    public void Draw(int priority) throws ProjectException{
        GraphicsEngine.GraphicsSystem.GetInstance().DrawFilledRect(rect, Color.WHITE, priority);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawRect(rect, Color.BLACK, priority + 1);
        if (choice.length() != 0){
            GraphicsEngine.GraphicsSystem.GetInstance().DrawText(choice, new Point(rect.x + rect.width / 10, rect.y + rect.height / 2), Color.BLACK, priority + 2);
        }
        else{
            GraphicsEngine.GraphicsSystem.GetInstance().DrawText(description, new Point(rect.x + rect.width / 10, rect.y + rect.height / 2), new Font("Arial Bold", Font.ITALIC, 16), Color.GRAY, priority + 2);
        }
        if (bIsExpanding){
            Iterator<String> itr = choices.iterator();
            int i = 1;
            while(itr.hasNext()){
                String newChoice = itr.next();
                Rectangle choiceRect = new Rectangle(rect.x, rect.y, rect.width, rect.height);
                if (rect.y < 300){
                    choiceRect.y += i * choiceRect.height;
                }
                else if (rect.y >= 300){
                    choiceRect.y -= i * choiceRect.height;
                }
                if (choiceRect.contains(CoreSystem.Mouse.GetInstance().GetMousePos())){
                    GraphicsEngine.GraphicsSystem.GetInstance().DrawFilledRect(choiceRect, Color.GRAY, priority);
                }
                else{
                    GraphicsEngine.GraphicsSystem.GetInstance().DrawFilledRect(choiceRect, Color.WHITE, priority);
                }
                GraphicsEngine.GraphicsSystem.GetInstance().DrawRect(choiceRect, Color.BLACK, priority + 1);
                GraphicsEngine.GraphicsSystem.GetInstance().DrawText(newChoice, new Point(choiceRect.x + choiceRect.width / 10, choiceRect.y + choiceRect.height / 2), Color.BLACK, priority + 2);
                i++;
            }
        }
    }

    /**
     * Ajoute un choix a la liste
     * @author Maxime Emonnot
     * @param choice Choix a ajouter a la liste
     */
    public void AddChoice(String choice){
        choices.add(choice);
    }

    /**
     * Initialise la liste de choix
     * @author Maxime Emonnot
     * @param newChoices Liste de choix
     */
    public void SetChoices(Set<String> newChoices){
        choices.clear();
        choices.addAll(newChoices);
    }

    /**
     * Recuperation du choix de l'utilisateur
     * @author Maxime Emonnot
     * @return Element selectionne
     */
    public String GetText(){
        return choice;
    }

    /**
     * Reinitialise le choix de l'utilisateur
     * @author Maxime Emonnot
     */
    public void Reset(){
        choice = "";
    }

    /**
     * Retourne l'etat de la ChoiceBox
     * @return Vrai si les choix sont ouverts, Faux sinon
     */
    public boolean IsExpanding(){
        return bIsExpanding;
    }

    private Rectangle rect;
    
    private boolean bIsExpanding = false;
    private Set<String> choices = new HashSet<String>();
    private String choice = "";
    private final String description;
}
