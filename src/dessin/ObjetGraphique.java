package dessin;

import java.awt.Color;
import java.awt.Graphics;

public abstract class ObjetGraphique {

	private Color couleur;
	private boolean visible;

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}
	
	public ObjetGraphique() {
		couleur = Color.black;
	}
	
	public ObjetGraphique(Color couleur) {
		this.couleur = couleur;
	}
	
	public abstract void dessineToi(Graphics g);
	
	public abstract boolean contient(int x, int y);
	
}
