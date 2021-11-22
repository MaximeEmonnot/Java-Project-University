package MenuSystem;

import java.awt.Rectangle;

import Exceptions.ProjectException;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

/**
 * Champs d'ecriture
 * Dans le cadre d'une interface Utilisateur
 * @author Maxime Emonnot
 */
public class TypingBox {
    /**
     * Constructeur TypingBox
     * Initilisation à partir d'un rectangle de position et d'un texte de description
     * @author Maxime Emonnot
     * @param _rect Rectangle de position de la TypingBox
     * @param _description Texte de description de la TypingBox
     */
    public TypingBox(Rectangle _rect, String _description){
        rect = _rect;
        description = _description;
    }  

    /**
     * Mise à jour de la TypingBox
     * Recuperation des caracteres tapes par l'utilisateur, avec gestion du focus
     * @author Maxime Emonnot
     * @see TypingBox#Timer()
     * @see TypingBox#Read()
     */
    public void Update(){
        if(CoreSystem.Mouse.GetInstance().LeftIsPressed()){
            bIsFocused = rect.contains(CoreSystem.Mouse.GetInstance().GetMousePos());
        }
        if (bIsFocused){
            Timer();
            Read();
        }
        else{
            text = text.replace("|", "");
        }
    }

    /**
     * Affiche la TypingBox et son contenu
     * Priorite d'affichage par defaut a 0
     * @author Maxime Emonnot
     * @throws ProjectException Erreur lors de l'instanciation de GraphicsSystem
     */
    public void Draw() throws ProjectException{
        Draw(0);
    }
    /**
     * Surcharge fonctionnelle. Affiche la TypingBox et son contenu selon une priorite donnee
     * @author Maxime Emonnot
     * @param priority Priorite d'affichage de la TypingBox
     * @throws ProjectException Erreur de lors de l'instanciation de GraphicsSystem
     */
    public void Draw(int priority) throws ProjectException{
        GraphicsEngine.GraphicsSystem.GetInstance().DrawFilledRect(rect, Color.WHITE, priority);
        GraphicsEngine.GraphicsSystem.GetInstance().DrawRect(rect, Color.BLACK, priority + 1);

        if (bIsPassword){
            String password = "";
            for (int i = 0; i < text.length(); i++){
                if (text.charAt(i) == '|'){
                    password += "|";
                }
                else{
                    password += "*";
                }
            }
            GraphicsEngine.GraphicsSystem.GetInstance().DrawText(password, new Point(rect.x + rect.width / 10, rect.y + rect.height / 2), Color.BLACK, priority + 2);
        }
        else{
            GraphicsEngine.GraphicsSystem.GetInstance().DrawText(text, new Point(rect.x + rect.width / 10, rect.y + rect.height / 2), Color.BLACK, priority + 2);
        }   
        if (text.replace("|", "").length() == 0){
            GraphicsEngine.GraphicsSystem.GetInstance().DrawText(description, new Point(rect.x + rect.width / 10 + 2, rect.y + rect.height / 2), new Font("Arial Bold", Font.ITALIC, 16), Color.GRAY, priority + 2);
        }
    }

    /**
     * Initalisation du mode password selon une valeur d'activation donnee
     * @author Maxime Emonnot
     * @param bMode Definit le mode du champ d'ecriture, mode password si Vrai, mode normal sinon
     */
    public void SetPasswordMode(boolean bMode){
        bIsPassword = bMode;
    }

    /**
     * Recuperation du texte tape par l'utilisateur
     * @author Maxime Emonnot
     * @return Texte entre par l'utilisateur
     */
    public String GetText(){
        return text.replace("|", "").replace("'", "\\'");
    }

    /**
     * Reinitialisation du texte entre par l'utilisateur
     * @author Maxime Emonnot
     */
    public void Clear(){
        text = "";
    }

    /**
     * Timer pour l'apparition de la barre d'ecriture lors du focus du champ.
     * Appels dans Update
     * @author Maxime Emonnot
     * @see TypingBox#Update()
     */
    private void Timer(){
        currentTimer += CoreSystem.Timer.GetInstance().DeltaTime();
        if (currentTimer > timer){
            if (text.endsWith("|")){
                text = text.substring(0, text.length() - 1);
            }
            else{
                text += "|";
            }
            currentTimer = 0.0f;
        }
    }

    /**
     * Routine de lecture des entrees utilisateurs, lors du focus du champ.
     * Appels dans Update
     * @author Maxime Emonnot
     * @see TypingBox#Update()
     */
    private void Read() {
        char c = CoreSystem.Keyboard.GetInstance().ReadChar();
        if (c != 0){
            text = text.replace("|", "");
            if (authorizedChar.contains(Character.toString(c))){
                text += c;
            }
            else if (c == 8 && text.length() > 0){
                text = text.substring(0, text.length() -1);
            }
        }
    }

    private Rectangle rect;

    private final float timer = 0.75f;
    private float currentTimer = 0.0f; 
    private String text = "";
    private final String description;
    private boolean bIsFocused = false;
    private boolean bIsPassword = false;

    private final static String authorizedChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789?!,;.:/\\\'\"°+-*@&éèêàùç²{([|])}=_><%$€#~ ";
}
