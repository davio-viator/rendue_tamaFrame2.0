package dessin;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;

public class Cercle extends ObjetGraphique{
	
	private Point centre;
	private int rayon;
	
	
	public Cercle(Point centre,int rayon) {
		this(centre,rayon,Color.black);
		setVisible(true);
	}
	
	public Cercle(Point centre, int rayon, Color couleur) {
		this.centre = centre;
		this.rayon = rayon;
		setCouleur(couleur); 
		setVisible(true);
		
	}

	@Override
	public void dessineToi(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(getCouleur());
		g.drawOval(centre.x-rayon,centre.y-rayon, rayon*2, rayon*2);
		//g.fillOval(centre.x,centre.y, rayon, rayon);
	}

	@Override
	public boolean contient(int x, int y) {
		// TODO Auto-generated method stub
		int newX = (x-centre.x)*(x-centre.x);
		int newY = (y-centre.y)*(y-centre.y);
		int newR = rayon*rayon;
		return (newX+newY<=newR);
		
	}

}
