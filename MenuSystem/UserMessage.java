package MenuSystem;

import java.awt.*;

import Exceptions.ProjectException;

/**
 * Messages utilisateurs
 * <p>Messages temporaires offrant un retour utilisateur sur le succes et l'echec de ses actions
 * @author Maxime Emonnot
 * @version 1.3.0
 */
public class UserMessage {
    /**
     * Constructeur UserMessage
     * <p>Initialisation selon une position
     * @author Maxime Emonnot
     * @param _pos Position du message utilisateur
     */
    public UserMessage(Point _pos){
        pos = _pos;
    }

    /**
     * Mise a jour du timer pour la duree d'affichage du message utilisateur
     * @author Maxime Emonnot
     */
    public void Update(){
        timer -= CoreSystem.Timer.GetInstance().DeltaTime();
    }

    /**
     * Affichage du message utilisateur, tant que le timer est positif
     * @author Maxime Emonnot
     * @throws ProjectException Erreur lors de l'instanciation de GraphicsSystem
     */
    public void Draw() throws ProjectException{
        if (timer > 0.0f){
            GraphicsEngine.GraphicsSystem.GetInstance().DrawText(text, pos, color);
        }
    }

    /**
     * Initialisation du message a afficher, selon un texte, une couleur et un timer donnes
     * @author Maxime Emonnot
     * @param _text Texte a afficher
     * @param _color Couleur du texte a afficher
     * @param _timer Duree d'affichage du message
     */
    public void SetMessage(String _text, Color _color, float _timer){
        text = _text;
        color = _color;
        timer = _timer;
    }

    private String text = "";
    private Color color = Color.WHITE;
    private float timer = 0.0f;
    private Point pos;
}
