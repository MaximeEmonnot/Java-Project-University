package GraphicsEngine;

import java.awt.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.HashMap;


public class GraphicsSystem extends JPanel {
    //Used to make arrays of Lambda functions
    private interface Lambda{
        void func(Graphics g);
    }
    
    private GraphicsSystem() throws Exceptions.ProjectException{
        CoreSystem.Window.GetInstance().getFrame().add(this);
    }

    public synchronized static GraphicsSystem GetInstance() throws Exceptions.ProjectException {
        if (INSTANCE == null)
            INSTANCE = new GraphicsSystem();
        return INSTANCE;
    }
    
    @Override
    public void paintComponent(Graphics g){
        
        synchronized(renderMap){
            super.paintComponent(g);
            setBackground(Color.BLACK);
            ArrayList<Lambda> lambdas = new ArrayList<Lambda>();
            lambdas.addAll(renderMap.values());
            for (int i = 0; i < lambdas.size(); i++){
                 lambdas.get(i).func(g);
            }
        }
        renderMap.clear();
    }
    
    public void Render(){
        repaint();
    } 

    public void DrawPixel(Point p, Color c){
        DrawPixel(p.x, p.y, c);
    }
    public void DrawPixel(int x, int y, Color c){
        DrawPixel(x, y, c, 0);
    }
    public void DrawPixel(int x, int y, Color c, int priority){
        synchronized (renderMap){
            renderMap.put(priority, (Graphics g) -> {
                g.setColor(c);
                g.drawLine(x, y, x, y);
            });
        }
    }

    public void DrawLine(Point p0, Point p1, Color c){
        DrawLine(p0, p1, c, 0);
    }
    public void DrawLine(Point p0, Point p1, Color c, int priority){
        DrawLine(p0.x, p0.y, p1.x, p1.y, c, priority);
    }
    public void DrawLine(int x0, int y0, int x1, int y1, Color c){
        DrawLine(x0, y0, x1, y1, c, 0);
    }
    public void DrawLine(int x0, int y0, int x1, int y1, Color c, int priority){
        synchronized (renderMap){
            renderMap.put(priority, (Graphics g) -> {
                g.setColor(c);
                g.drawLine(x0, y0, x1, y1);
            });
        }
    }

    public void DrawRect(Rectangle r, Color c){
        DrawRect(r, c, 0);
    }
    public void DrawRect(Rectangle r, Color c, int priority){
        DrawRect(r.x, r.y, r.width, r.height, c, priority);
    }
    public void DrawRect(int x, int y, int w, int h, Color c){
        DrawRect(x, y, w, h, c, 0);
    }
    public void DrawRect(int x, int y, int w, int h, Color c, int priority){
        synchronized (renderMap){
        renderMap.put(priority, (Graphics g) -> {
            g.setColor(c);
            g.drawRect(x, y, w, h);
        });
    }
    }

    public void DrawFilledRect(Rectangle r, Color c){
        DrawFilledRect(r, c, 0);
    }
    public void DrawFilledRect(Rectangle r, Color c, int priority){
        DrawFilledRect(r.x, r.y, r.width, r.height, c, priority);
    }
    public void DrawFilledRect(int x, int y, int w, int h, Color c){
        DrawFilledRect(x, y, w, h, c, 0);
    }
    public void DrawFilledRect(int x, int y, int w, int h, Color c, int priority){
        synchronized (renderMap){
            renderMap.put(priority,(Graphics g) -> {
                g.setColor(c);
                g.fillRect(x, y, w, h);
            });
        }
    }

    public void DrawRoundRect(Rectangle r, int aw, int ah, Color c){
        DrawRoundRect(r, aw, ah, c, 0);
    }
    public void DrawRoundRect(Rectangle r, int aw, int ah, Color c, int priority){
        DrawRoundRect(r.x, r.y, r.width, r.height, aw, ah, c, priority);
    }
    public void DrawRoundRect(int x, int y, int w, int h, int aw, int ah, Color c){
        DrawRoundRect(x, y, w, h, aw, ah, c, 0);
    }
    public void DrawRoundRect(int x, int y, int w, int h, int aw, int ah, Color c, int priority){
        synchronized (renderMap){
        renderMap.put(priority, (Graphics g) -> {
            g.setColor(c);
            g.drawRoundRect(x, y, w, h, aw, ah);
        });
    }
    }

    public void DrawFilledRoundRect(Rectangle r, int aw, int ah, Color c){
        DrawFilledRoundRect(r, aw, ah, c, 0);
    }
    public void DrawFilledRoundRect(Rectangle r, int aw, int ah, Color c, int priority){
        DrawFilledRoundRect(r.x, r.y, r.width, r.height, aw, ah, c, priority);
    }
    public void DrawFilledRoundRect(int x, int y, int w, int h, int aw, int ah, Color c){
        DrawFilledRoundRect(x, y, w, h, aw, ah, c, 0);
    }
    public void DrawFilledRoundRect(int x, int y, int w, int h, int aw, int ah, Color c, int priority){
        synchronized (renderMap){
            renderMap.put(priority,(Graphics g) -> {
                g.setColor(c);
                g.fillRoundRect(x, y, w, h, aw, ah);
            });
        }
    }

    public void DrawText(String text, Point p, Color c){
        DrawText(text, p, c, 0);
    }
    public void DrawText(String text, Point p, Color c, int priority){
        DrawText(text, p.x, p.y, c, priority);
    }
    public void DrawText(String text, int x, int y, Color c){
        DrawText(text, x, y, c, 0);
    }
    public void DrawText(String text, int x, int y, Color c, int priority){
        synchronized (renderMap){
            renderMap.put(priority,(Graphics g) -> {
                g.setColor(c);
                g.drawString(text, x, y);
            });
        }
    }

    public void DrawSprite(Sprite s, Point p){
        DrawSprite(s, p, 0);
    }
    public void DrawSprite(Sprite s, Point p, int priority){
        DrawSprite(s, p.x, p.y, priority);
    }
    public void DrawSprite(Sprite s, int x, int y){
        DrawSprite(s, x, y, 0);
    }
    public void DrawSprite(Sprite s, int x, int y, int priority){
        DrawSprite(s, x, y, s.GetWidth(), s.GetHeight(), priority);
    }
    public void DrawSprite(Sprite s, Rectangle rect){
        DrawSprite(s, rect, 0);
    }
    public void DrawSprite(Sprite s, Rectangle rect, int priority){
        DrawSprite(s, rect.x, rect.y, rect.width, rect.height, priority);
    }
    public void DrawSprite(Sprite s, int x, int y, int w, int h){
        DrawSprite(s, x, y, w, h, 0);
    }
    public void DrawSprite(Sprite s, int x, int y, int w, int h, int priority){
        DrawSprite(s, x, y, w, h, 0, 0, s.GetWidth(), s.GetHeight(), priority);
    }
    public void DrawSprite(Sprite s, Rectangle dest, Rectangle src){
        DrawSprite(s, dest, src, 0);
    }
    public void DrawSprite(Sprite s, Rectangle dest, Rectangle src, int priority){
        DrawSprite(s, dest.x, dest.y, dest.width, dest.height, src.x, src.y, src.width, src.height, priority);
    }
    public void DrawSprite(Sprite s, int dx, int dy, int dw, int dh, int sx, int sy, int sw, int sh){
        DrawSprite(s, dx, dy, dw, sx, sy, sw, sh, 0);
    }
    public void DrawSprite(Sprite s, int dx, int dy, int dw, int dh, int sx, int sy, int sw, int sh, int priority){
        synchronized (renderMap){
            renderMap.put(priority, (Graphics g) -> g.drawImage(s.GetSprite(), dx, dy, dx + dw, dy + dh, sx, sy, sx + sw, sy + sh, null));
        }
    }

    private static GraphicsSystem INSTANCE = null;

    private HashMap<Integer, Lambda> renderMap = new HashMap<Integer, Lambda>();    
}
