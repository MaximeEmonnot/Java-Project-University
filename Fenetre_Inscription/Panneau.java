package Fenetre_Inscription;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class Panneau extends JPanel {

	 public void paintComponent(Graphics g) {
		 g.setColor(Color.white);
		 g.fillRect(0,0,this.getWidth(),this.getHeight());
	 }
	

}
