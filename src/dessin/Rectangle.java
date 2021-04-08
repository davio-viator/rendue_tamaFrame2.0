package dessin;

import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends ObjetGraphique {
	
	
	private java.awt.Rectangle r;
	

	public Rectangle(int x, int y, int largeur, int hauteur) {
		
		setCouleur(Color.black);
		r = new java.awt.Rectangle(x,y,largeur,hauteur);
		setVisible(true);
	}
	
	public Rectangle(Point p, int largeur, int hauteur) {
		this(p.x,p.y,largeur,hauteur);
		setVisible(true);
	}
	
	public Rectangle(Point p, int largeur,int hauteur,Color c) {
		
		setCouleur(c);
		r = new java.awt.Rectangle(p.x,p.y,largeur,hauteur);
		setVisible(true);
	}
	
	public Rectangle() {
		
		setCouleur(Color.black);
		setVisible(true);
	}
	
	
	@Override
	public boolean contient(int x, int y) {
		// TODO Auto-generated method stub
		return r.contains(x,y);
	}

	@Override
	public void dessineToi(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(getCouleur());
		g.drawRect(r.x, r.y, (int)r.getWidth(), (int)r.getHeight());
		
		//g.fillRect(this.x, this.y, this.largeur, this.hauteur);
	}
	
	
}
	
