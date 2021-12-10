package GraphicsEngine;

import java.awt.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Map;

/**
 * Gestion de l'interface graphique.
 * l'affichage est mis a jour 60 fois par secondes
 * Chaque frame realise l'affichage defini dans les fonctions Draw
 * Gestion d'une priorite d'affichage, agissant comme des calques de Photoshop
 * @author Maxime Emonnot
 */
public class GraphicsSystem extends JPanel {
    /**
     * Interface pour les fonctions lambda de l'affichage.
     * Appels dans paintComponent(Graphics)
     * @author Maxime Emonnot
     * @see GraphicsSystem#paintComponent(Graphics)
     */
    private interface Lambda{
        void func(Graphics g);
    }
    
    /**
     * Constructeur prive dans le cadre du patron de conception Singleton.
     * Applique le systeme graphique au JFrame de Window
     * @author Maxime Emonnot
     * @throws Exceptions.ProjectException Erreur d'instanciation de Window
     */
    private GraphicsSystem() throws Exceptions.ProjectException{
        CoreSystem.Window.GetInstance().getFrame().add(this);
    }

 /**
     * Recuperation d'instance, dans le cadre du patron de conception Singleton.
     * @author Maxime Emonnot
     * @return L'instance GraphicsSystem de l'application
     * @throws Exceptions.ProjectException Erreur d'instanciation de Window
     */
    public synchronized static GraphicsSystem GetInstance() throws Exceptions.ProjectException {
        if (INSTANCE == null)
            INSTANCE = new GraphicsSystem();
        return INSTANCE;
    }

    /**
     * Methode override de Swing
     * Permet la mise a jour de l'affichage
     * Appelle toutes les fonctions lambda graphiques
     * @author Maxime Emonnot
     */ 
    @Override
    public void paintComponent(Graphics g){
        
        synchronized(renderMap){
            super.paintComponent(g);
            setBackground(backgroundColor);
            
            renderMap.forEach((e) -> e.getValue().func(g));
            renderMap.clear();
            }
        }
        
    /**
     * Trie les differents appels d'affichage puis execute la mise a jour de l'affichage
     * Execution 60 fois par secondes
     * @author Maxime Emonnot
     * @see GraphicsSystem#paintComponent(Graphics)
     */
    public void Render(){
        synchronized(renderMap){
            renderMap.sort((e0, e1) -> e0.getKey() - e1.getKey());
            repaint();
        }
    } 

    /**
     * Definit la couleur du fond.
     * @author Maxime Emonnot
     * @param c La nouvelle couleur du fond
     */
    public void SetBackgroundColor(Color c){
        synchronized (renderMap){
            renderMap.add(Map.entry(0, (Graphics g) -> {
                g.setColor(c);
                g.fillRect(0, 0, 1280, 720);
            }));
        }
    }

    /**
     * Dessine un pixel d'une couleur donnee a une position donnee
     * @author Maxime Emonnot
     * @param p Les coordonnees du pixel
     * @param c La couleur du pixel
     */
    public void DrawPixel(Point p, Color c){
        DrawPixel(p.x, p.y, c);
    }
    /**
     * Surcharge fonctionnelle. Dessine un pixel selon des coordonnees et une couleur donnees
     * @author Maxime Emonnot
     * @param x Position X du pixel
     * @param y Position Y du pixel
     * @param c Couleur du pixel
     */
    public void DrawPixel(int x, int y, Color c){
        DrawPixel(x, y, c, 0);
    }
    /**
     * Surcharge fonctionelle. Dessine un pixel selon des coordonnees, une couleur et une priorite donnees
     * @author Maxime Emonnot
     * @param x Position X du pixel
     * @param y Position Y du pixel
     * @param c Couleur du pixel
     * @param priority Priorite d'affichage du pixel
     */
    public void DrawPixel(int x, int y, Color c, int priority){
        synchronized (renderMap){
            renderMap.add(Map.entry(priority, (Graphics g) -> {
                g.setColor(c);
                g.drawLine(x, y, x, y);
            }));
        }
    }

    /**
     * Dessine une ligne selon un point de depart, un point d'arrivee et une couleur donnes
     * @author Maxime Emonnot
     * @param p0 Point de depart de la ligne
     * @param p1 Point d'arrivee de la ligne
     * @param c Couleur de la ligne
     */
    public void DrawLine(Point p0, Point p1, Color c){
        DrawLine(p0, p1, c, 0);
    }
    /**
     * Surcharge fonctionnelle. Dessine une ligne selon un point de depart, un point d'arrivee, une couleur et une priorite donnes
     * @author Maxime Emonnot
     * @param p0 Point de depart de la ligne
     * @param p1 Point d'arrivee de la ligne
     * @param c Couleur de la ligne
     * @param priority Priorite d'affichage de la ligne
     */
    public void DrawLine(Point p0, Point p1, Color c, int priority){
        DrawLine(p0.x, p0.y, p1.x, p1.y, c, priority);
    }
    /**
     * Surchage fonctionnelle. Dessine une ligne selon des coordonnees de depart, des coordonnees d'arrivee et une couleur donnees
     * @author Maxime Emonnot
     * @param x0 Position X du point de depart de la ligne
     * @param y0 Position Y du point de depart de la ligne
     * @param x1 Position X du point d'arrivee de la ligne
     * @param y1 Position Y du point d'arrivee de la ligne
     * @param c Couleur de la ligne
     */
    public void DrawLine(int x0, int y0, int x1, int y1, Color c){
        DrawLine(x0, y0, x1, y1, c, 0);
    }
    /**
     * Surcharge fonctionnelle. Dessine une ligne selon des coordonnes de depart, des coordonnees d'arrivee, une couleur et une priorite donnees
     * @author Maxime Emonnot
     * @param x0 Position X du point de depart de la ligne
     * @param y0 Position Y du point de depart de la ligne
     * @param x1 Position X du point d'arrivee de la ligne
     * @param y1 Position Y du point d'arrivee de la ligne
     * @param c Couleur de la ligne
     * @param priority Priorite d'affichage de la ligne
     */
    public void DrawLine(int x0, int y0, int x1, int y1, Color c, int priority){
        synchronized (renderMap){
            renderMap.add(Map.entry(priority, (Graphics g) -> {
                g.setColor(c);
                g.drawLine(x0, y0, x1, y1);
            }));
        }
    }

    /**
     * Dessine les contours d'un rectangle selon un rectangle et une couleur donnes
     * @author Maxime Emonnot
     * @param r Rectangle a dessiner
     * @param c Couleur du rectangle
     */
    public void DrawRect(Rectangle r, Color c){
        DrawRect(r, c, 0);
    }
    /**
     * Surcharge fonctionnelle. Dessine les contours d'un rectangle selon un rectangle, une couleur et une priorite donnes
     * @author Maxime Emonnot
     * @param r Rectangle a dessiner
     * @param c Couleur du rectangle
     * @param priority Priorite d'affichage du rectangle
     */
    public void DrawRect(Rectangle r, Color c, int priority){
        DrawRect(r.x, r.y, r.width, r.height, c, priority);
    }
    /**
     * Surcharge fonctionnelle. Dessine les contours d'un rectangle selon les coordonnees du coin en haut a gauche, de la largeur, de la hauteur du rectangle et une couleur donnees
     * @author Maxime Emonnot
     * @param x Position X du coin haut-gauche du rectangle
     * @param y Position Y du coin haut-gauche du rectangle
     * @param w Largeur du rectangle
     * @param h Hauteur du rectangle
     * @param c Couleur du rectangle
     */
    public void DrawRect(int x, int y, int w, int h, Color c){
        DrawRect(x, y, w, h, c, 0);
    }
    /**
     * Surcharge fonctionnelle. Dessine les conteurs d'un rectangle sleon les cooredonnes du coin en haut a gauche, de la longeur, de la hauteur du rectangle, une couleur et une priorite donnes
     * @author Maxime Emonnot
     * @param x Position X du coin haut-gauche du rectangle
     * @param y Position Y du coin haut-gauche du rectangle
     * @param w Largeur du rectangle
     * @param h Hauteur du rectangle
     * @param c Couleur du rectangle
     * @param priority Priorite d'affichage du rectangle
     */
    public void DrawRect(int x, int y, int w, int h, Color c, int priority){
        synchronized (renderMap){
        renderMap.add(Map.entry(priority, (Graphics g) -> {
            g.setColor(c);
            g.drawRect(x, y, w, h);
        }));
        }
    }

    /**
     * Dessine un rectangle plein selon un rectangle et une couleur donnes
     * @author Maxime Emonnot
     * @param r Rectangle a dessiner
     * @param c Couleur du rectangle
     */
    public void DrawFilledRect(Rectangle r, Color c){
        DrawFilledRect(r, c, 0);
    }
    /**
     * Surcharge fonctionnelle. Dessine un rectangle plein selon un rectangle, une couleur et une priorite donnes
     * @author Maxime Emonnot
     * @param r Rectangle a dessiner
     * @param c Couleur du rectangle
     * @param priority Priorite d'affichage du rectangle
     */
    public void DrawFilledRect(Rectangle r, Color c, int priority){
        DrawFilledRect(r.x, r.y, r.width, r.height, c, priority);
    }
    /**
     * Surcharge fonctionnelle. Dessine un rectangle plein selon les coordonnees du coin en haut a gauche, de la largeur, de la hauteur du rectangle et une couleur donnees
     * @author Maxime Emonnot
     * @param x Position X du coin haut-gauche du rectangle
     * @param y Position Y du coin haut-gauche du rectangle
     * @param w Largeur du rectangle
     * @param h Hauteur du rectangle
     * @param c Couleur du rectangle
     */
    public void DrawFilledRect(int x, int y, int w, int h, Color c){
        DrawFilledRect(x, y, w, h, c, 0);
    }
    /**
     * Surcharge fonctionnelle. Dessine un rectangle plein selon les coordonnees du coin en haut a gauche, de la largeur, de la hauteur du rectangle, une couleur et une priorite donnees 
     * @author Maxime Emonnot
     * @param x Position X du coin haut-gauche du rectangle
     * @param y Position Y du coin haut-gauche du rectangle
     * @param w Largeur du rectangle
     * @param h Hauteur du rectangle
     * @param c Couleur du rectangle
     * @param priority Priorite d'affichage du rectangle 
     */
    public void DrawFilledRect(int x, int y, int w, int h, Color c, int priority){
        synchronized (renderMap){
            renderMap.add(Map.entry(priority,(Graphics g) -> {
                g.setColor(c);
                g.fillRect(x, y, w, h);
            }));
        }
    }

    /**
     * Dessine les bords d'un rectangle aux coins arrondis selon un rectangle, une largeur d'arrondi, une hauteur d'arrondi et une couleur donnes
     * @author Maxime Emonnot
     * @param r Rectangle a dessiner
     * @param aw Largeur de l'arrondi aux coins
     * @param ah Hauteur de l'arrondi aux coins
     * @param c Couleur du rectangle arrondi
     */
    public void DrawRoundRect(Rectangle r, int aw, int ah, Color c){
        DrawRoundRect(r, aw, ah, c, 0);
    }
    /**
     * Surcharge fonctionnelle. Dessine les bords d'un rectangle aux coins arrondis selon un rectangle, une largeur d'arrondi, une hauteur d'arrondi, une couleur et une priorite donnes
     * @author Maxime Emonnot
     * @param r Rectangle a dessiner
     * @param aw Largeur de l'arrondi aux coins
     * @param ah Hauteur de l'arrondi aux coins
     * @param c Couleur du rectangle arrondi
     * @param priority Priorite d'affichage du rectangle arrondi
     */
    public void DrawRoundRect(Rectangle r, int aw, int ah, Color c, int priority){
        DrawRoundRect(r.x, r.y, r.width, r.height, aw, ah, c, priority);
    }
    /**
     * Surcharge fonctionnelle. Dessine les bords d'un rectangle aux coins arrondis selon les coordonnees du coins en haut à gauche, de la largeur, de la hauteur du rectangle, une largeur d'arrondi, une hauteur d'arrondi et une couleur donnees
     * @author Maxime Emonnot
     * @param x Position X du coin haut-gauche du rectangle
     * @param y Position Y du coin haut-gauche du rectangle
     * @param w Largeur du rectangle
     * @param h Hauteur du rectangle
     * @param aw Largeur de l'arrondi aux coins
     * @param ah Hauteur de l'arrondi aux coins
     * @param c Couleur du rectangle arrondi
     */
    public void DrawRoundRect(int x, int y, int w, int h, int aw, int ah, Color c){
        DrawRoundRect(x, y, w, h, aw, ah, c, 0);
    }
    /** 
     * Surcharge fonctionnelle. Dessine les bords d'un rectangle aux coins arrondis selon les coordonnees du coins en haut à gauche, de la largeur, de la hauteur du rectangle, une largeur d'arrondi, une hauteur d'arrondi, une couleur et une priorite donnees
     * @author Maxime Emonnot
     * @param x Position X du coin haut-gauche du rectangle
     * @param y Position Y du coin haut-gauche du rectangle
     * @param w Largeur du rectangle
     * @param h Hauteur du rectangle
     * @param aw Largeur de l'arrondi aux coins
     * @param ah Hauteur de l'arrondi aux coins
     * @param c Couleur du rectangle arrondi
     * @param priority Priorite d'affichage du rectangle arrondi
     */
    public void DrawRoundRect(int x, int y, int w, int h, int aw, int ah, Color c, int priority){
        synchronized (renderMap){
        renderMap.add(Map.entry(priority, (Graphics g) -> {
            g.setColor(c);
            g.drawRoundRect(x, y, w, h, aw, ah);
        }));
        }
    }

    /**
     * Dessine un rectangle plein aux coins arrondis selon un rectangle, une largeur d'arrondi, une hauteur d'arrondi et une couleur donnes
     * @author Maxime Emonnot
     * @param r Rectangle a dessiner
     * @param aw Largeur de l'arrondi aux coins
     * @param ah Hauteur de l'arrondi aux coins
     * @param c Couleur du rectangle arrrondi
     */
    public void DrawFilledRoundRect(Rectangle r, int aw, int ah, Color c){
        DrawFilledRoundRect(r, aw, ah, c, 0);
    }
    /**
     * Surcharge fonctionnelle. Dessine un rectangle plein aux coins arrondis selon un rectangle, une largeur d'arrondi, une hauteur d'arrondi et une couleur donnes
     * @author Maxime Emonnot
     * @param r Rectangle a dessiner
     * @param aw Largeur de l'arrondi aux coins
     * @param ah Hauteur de l'arrondi aux coins
     * @param c Couleur du rectangle arrondi
     * @param priority Priorite d'affichage du rectangle arrondi
     */
    public void DrawFilledRoundRect(Rectangle r, int aw, int ah, Color c, int priority){
        DrawFilledRoundRect(r.x, r.y, r.width, r.height, aw, ah, c, priority);
    }
    /**
     * Surcharge fonctionnelle. Dessine un rectangle plein aux coins arrondis selon les coordonnees du coin en haut a gauche, la largeur, la hauteur du rectangle, une largeur d'arrondi, une hauteur d'arrondi et une couleur donnees
     * @author Maxime Emonnot
     * @param x Position X du coin haut-gauche du rectangle
     * @param y Position Y du coin haut-gauche du rectangle
     * @param w Largeur du rectangle
     * @param h Hauteur du rectangle
     * @param aw Largeur de l'arrondi aux coins
     * @param ah Hauteur de l'arrondi aux coins
     * @param c Couleur du rectangle arrondi
     */
    public void DrawFilledRoundRect(int x, int y, int w, int h, int aw, int ah, Color c){
        DrawFilledRoundRect(x, y, w, h, aw, ah, c, 0);
    }
    /**
     * Surcharge fonctionnelle. Dessine un rectangle plein aux coins arrondis selon les coordonnees du coin en haut a gauche, la largeur, la hauteur du rectangle, une largeur d'arrondi, une hauteur d'arrondi, une couleur et une priorite donnees
     * @author Maxime Emonnot
     * @param x Position X du coin haut-gauche du rectangle
     * @param y Position Y du coin haut-gauche du rectangle
     * @param w Largeur du rectangle
     * @param h Hauteur du rectangle
     * @param aw Largeur de l'arrondi aux coins
     * @param ah Hauteur de l'arrondi aux coins
     * @param c Couleur du rectangle arrondi
     * @param priority Priorite d'affichage du rectangle arrondi
     */
    public void DrawFilledRoundRect(int x, int y, int w, int h, int aw, int ah, Color c, int priority){
        synchronized (renderMap){
            renderMap.add(Map.entry(priority,(Graphics g) -> {
                g.setColor(c);
                g.fillRoundRect(x, y, w, h, aw, ah);
            }));
        }
    }

    /**
     * Affiche du texte selon un texte, une position et une couleur donnes
     * Police par defaut : Arial Bold, mode Plain, taille 16
     * @author Maxime Emonnot
     * @param text Texte a afficher
     * @param p Position du texte
     * @param c Couleur du texte
     */
    public void DrawText(String text, Point p, Color c){
        DrawText(text, p, new Font("Arial Bold", Font.PLAIN, 16), c);
    }
    /**
     * Surcharge fonctionnelle. Affiche du texte selon un texte, une position, une police d'ecriture et une couleur donnes
     * @author Maxime Emonnot
     * @param text Texte a afficher
     * @param p Position du texte
     * @param f Police du texte
     * @param c Couleur du texte
     */
    public void DrawText(String text, Point p, Font f, Color c){
        DrawText(text, p, f, c, 0);
    }
    /**
     * Surcharge fonctionnelle. Affiche du texte selon un texte, une position, une couleur et une priorite donnes
     * Police par defaut : Arial Bold, mode Plain, taille 16
     * @author Maxime Emonnot
     * @param text Texte a afficher
     * @param p Position du texte
     * @param c Couleur du texte
     * @param priority Priorite d'affichage du texte
     */
    public void DrawText(String text, Point p, Color c, int priority){
        DrawText(text, p, new Font("Arial Bold", Font.PLAIN, 16), c, priority);
    }
    /**
     * Surcharge fonctionnelle. Affiche du texte selon un texte, une position, une police d'ecriture, une couleur et une priorite donnes
     * @author Maxime Emonnot
     * @param text Texte a afficher
     * @param p Position du texte
     * @param f Police du texte
     * @param c Couleur du texte
     * @param priority Priorite d'affichage du texte
     */
    public void DrawText(String text, Point p, Font f, Color c, int priority){
        DrawText(text, p.x, p.y, f, c, priority);
    }
    /**
     * Surcharge fonctionnelle. Affiche du texte selon un texte, des coordonnees et une couleur donnes
     * Police par defaut : Arial Bold, mode Plain, taille 16
     * @author Maxime Emonnot
     * @param text Texte a afficher
     * @param x Position X du texte
     * @param y Position Y du texte
     * @param c Couleur du texte
     */
    public void DrawText(String text, int x, int y, Color c){
        DrawText(text, x, y, new Font("Arial Bold", Font.PLAIN, 16), c);
    }
    /**
     * Surcharge fonctionnelle. Affiche du texte selon un texte, des coordonnees, une police d'ecriture et une couleur donnes
     * @author Maxime Emonnot
     * @param text Texte a afficher
     * @param x Position X du texte
     * @param y Position Y du texte
     * @param f Police du texte
     * @param c Couleur du texte
     */
    public void DrawText(String text, int x, int y, Font f, Color c){
        DrawText(text, x, y, f, c, 0);
    }
    /**
     * Surcharge fonctionnelle. Affiche du texte selon un texte, des coordonnees, une couleur et une priorite donnes
     * Police par defaut : Arial Bold, mode Plain, taille 16
     * @author Maxime Emonnot
     * @param text Texte a afficher
     * @param x Position X du texte
     * @param y Position Y du texte
     * @param c Couleur du texte
     * @param priority Priorite d'affichage du texte
     */
    public void DrawText(String text, int x, int y, Color c, int priority){
        DrawText(text, x, y, new Font("Arial Bold", Font.PLAIN, 16), c, priority);
    }
    /**
     * Surcharge fonctionnelle. Affiche du texte selon un texte, des coordonnees, une police d'ecriture, une couleur et une priorite donnes
     * @author Maxime Emonnot
     * @param text Texte a afficher
     * @param x Position X du texte
     * @param y Position Y du texte
     * @param f Police du texte
     * @param c Couleur du texte
     * @param priority Priorite d'affichage du texte
     */
    public void DrawText(String text, int x, int y, Font f, Color c, int priority){
        synchronized (renderMap){
            renderMap.add(Map.entry(priority,(Graphics g) -> {
                g.setFont(f);
                g.setColor(c);
                g.drawString(text, x, y);
            }));
        }
    }

    /**
     * Affiche un Sprite selon un Sprite et une position donnes
     * @author Maxime Emonnot
     * @param s Sprite a afficher
     * @param p Position du Sprite (coin haut-gauche)
     */
    public void DrawSprite(Sprite s, Point p){
        DrawSprite(s, p, 0);
    }
    /**
     * Surcharge fonctionnelle. Affiche un Sprite selon un Sprite, une position et une priotrite donnes
     * @param s Sprite a afficher
     * @param p Position du Sprite (coin haut-gauche)
     * @param priority Priorite d'affichage du Sprite
     * @author Maxime Emonnot
     */
    public void DrawSprite(Sprite s, Point p, int priority){
        DrawSprite(s, p.x, p.y, priority);
    }
    /**
     * Surcharge fonctionnelle. Affiche un Sprite selon un Sprite et des coordonnees donnes
     * @author Maxime Emonnot
     * @param s Sprite a afficher
     * @param x Position X du Sprite (coin haut-gauche)
     * @param y Position Y du Sprite (coin haut-gauche)
     */
    public void DrawSprite(Sprite s, int x, int y){
        DrawSprite(s, x, y, 0);
    }
    /**
     * Surcharge fonctionnelle. Affiche un Sprite selon un Sprite, des coordonnes et une priorite donnes
     * @author Maxime Emonnot
     * @param s Sprite a afficher
     * @param x Position X du Sprite (coin haut-gauche)
     * @param y Position Y du Sprite (coin haut-gauche)
     * @param priority Priorite d'affichage du Sprite
     */
    public void DrawSprite(Sprite s, int x, int y, int priority){
        DrawSprite(s, x, y, s.GetWidth(), s.GetHeight(), priority);
    }
    /**
     * Surcharge fonctionnelle. Affiche un Sprite selon un Sprite et un rectangle de destination donnes
     * @author Maxime Emonnot
     * @param s Sprite a afficher
     * @param rect Rectangle de destination d'affichage du Sprite
     */
    public void DrawSprite(Sprite s, Rectangle rect){
        DrawSprite(s, rect, 0);
    }
    /**
     * Surchage fonctionnelle. Affiche un Sprite selon un Sprite, un rectangle de destination et une priorite donnes
     * @author Maxime Emonnot
     * @param s Sprite a afficher
     * @param rect Rectangle de destination d'affichage du Sprite
     * @param priority Priorite d'affichage du Sprite
     */
    public void DrawSprite(Sprite s, Rectangle rect, int priority){
        DrawSprite(s, rect.x, rect.y, rect.width, rect.height, priority);
    }
    /**
     * Surcharge fonctionnelle. Affiche un Sprite selon un Sprite, des coordonnes, la largeur et la hauteur du rectangle de destination donnes
     * @author Maxime Emonnot
     * @param s Sprite a afficher
     * @param x Position X du coin haut-gauche du rectangle de destination du Sprite
     * @param y Position Y du coin haut-gauche du rectangle de destination du Sprite
     * @param w Largeur du rectangle de destination du Sprite
     * @param h Hauteur du rectangle de destination du Sprite
     */
    public void DrawSprite(Sprite s, int x, int y, int w, int h){
        DrawSprite(s, x, y, w, h, 0);
    }
    /**
     * Surcharge fonctionnelle. Affiche un Sprite selon un Sprite, des coordonnes, la longeur, la hauteur du rectangle de desination et une priorite donnes
     * @author Maxime Emonnot
     * @param s Sprite a afficher
     * @param x Position X du coin haut-gauche du rectangle de destination du Sprite
     * @param y Position Y du coin haut-gauche du rectangle de destination du Sprite
     * @param w Largeur du rectangle de destination du Sprite
     * @param h Hauteur du rectangle de destination du Sprite
     * @param priority Priorite d'affichage du Sprite
     */
    public void DrawSprite(Sprite s, int x, int y, int w, int h, int priority){
        DrawSprite(s, x, y, w, h, 0, 0, s.GetWidth(), s.GetHeight(), priority);
    }
    /**
     * Surcharge fonctionnelle. Affiche un Sprite selon un Sprite, un rectangle de destination et un rectangle de source donnes
     * @author Maxime Emonnot
     * @param s Sprite a afficher
     * @param dest Rectangle de destination du Sprite
     * @param src Rectangle de source du Sprite
     */
    public void DrawSprite(Sprite s, Rectangle dest, Rectangle src){
        DrawSprite(s, dest, src, 0);
    }
    /**
     * Surcharge fonctionnelle. Affiche un Sprite selon un Sprite, un rectangle de destination, un rectangle de source et une priorite donnes
     * @author Maxime Emonnot
     * @param s Sprite a afficher
     * @param dest Rectangle de destination du Sprite
     * @param src Rectangle de source du Sprite
     * @param priority Priorite d'affichage du Sprite
     */
    public void DrawSprite(Sprite s, Rectangle dest, Rectangle src, int priority){
        DrawSprite(s, dest.x, dest.y, dest.width, dest.height, src.x, src.y, src.width, src.height, priority);
    }
    /**
     * Surcharge fonctionnelle. Affiche un Sprite selon un Sprite, des coonrdonnes, la largeur, la hauteur du rctangle de destination, des coordonnes, la largeur et la hauteur du rectangle de source donnes
     * @author Maxime Emonnot
     * @param s Sprite a afficher
     * @param dx Position X du coin haut-gauche du rectangle de destination du Sprite
     * @param dy Position Y du coin haut-gauche du rectangle de destination du Sprite
     * @param dw Largeur du rectangle de destination du Sprite
     * @param dh Hauteur du rectangle de destination du Sprite
     * @param sx Position X du coin haut-gauche du rectangle de source du Sprite
     * @param sy Position Y du coin haut-gauche du rectangle de source du Sprite
     * @param sw Largeur du rectangle de source du Sprite
     * @param sh Hauteur du rectangle de source du Sprite
     */
    public void DrawSprite(Sprite s, int dx, int dy, int dw, int dh, int sx, int sy, int sw, int sh){
        DrawSprite(s, dx, dy, dw, sx, sy, sw, sh, 0);
    }
    /**
     * Surcharge fonctionnelle. Affiche un Sprite selon un Sprite, des coonrdonnes, la largeur, la hauteur du rctangle de destination, des coordonnes, la largeur, la hauteur du rectangle de source et une priorite donnes
     * @author Maxime Emonnot
     * @param s Sprite a afficher
     * @param dx Position X du coin haut-gauche du rectangle de destination du Sprite
     * @param dy Position Y du coin haut-gauche du rectangle de destination du Sprite
     * @param dw Largeur du rectangle de destination du Sprite
     * @param dh Hauteur du rectangle de destination du Sprite
     * @param sx Position X du coin haut-gauche du rectangle de source du Sprite
     * @param sy Position Y du coin haut-gauche du rectangle de source du Sprite
     * @param sw Largeur du rectangle de source du Sprite
     * @param sh Hauteur du rectangle de source du Sprite
     * @param priority Priorite d'affichage du Sprite
     */
    public void DrawSprite(Sprite s, int dx, int dy, int dw, int dh, int sx, int sy, int sw, int sh, int priority){
        synchronized (renderMap){
            renderMap.add(Map.entry(priority, (Graphics g) -> g.drawImage(s.GetSprite(), dx, dy, dx + dw, dy + dh, sx, sy, sx + sw, sy + sh, null)));
        }
    }

    private static GraphicsSystem INSTANCE = null;

    private Color backgroundColor = Color.BLACK;
    private ArrayList<Map.Entry<Integer, Lambda>> renderMap = new ArrayList<Map.Entry<Integer, Lambda>>();    
}
