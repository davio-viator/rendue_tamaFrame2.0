package tamagoshis.graphics;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;


import dessin.Cercle;
import dessin.ObjetGraphique;
import dessin.Rectangle;
import tamagoshi.Tamagoshi;


@SuppressWarnings("serial")
public class TamaJPanel extends javax.swing.JPanel{
	
	private static ArrayList<ObjetGraphique> listeObjet;
	
	private Cercle tete;
	private Cercle oeilD;
	private Cercle oeilG;
	private Rectangle nez;
	private Cercle bouche;
	
	private Tamagoshi tama;
	
	
	public TamaJPanel() {
		listeObjet = new ArrayList<>();
		tete = new Cercle(new Point(200,160),100);
		listeObjet.add(tete);
		oeilG = new Cercle(new Point(160,110),20);
		listeObjet.add(oeilG);
		oeilD = new Cercle(new Point(240,110),20);
		listeObjet.add(oeilD);
		bouche = new Cercle(new Point(200,230),20);
		listeObjet.add(bouche);
		nez = new Rectangle(185,150,30,50);
		listeObjet.add(nez);
		ArrayList<String> nameList = new ArrayList<>();
		nameList.addAll( Arrays.asList("Hurricane","Sanoma","Daddario","Rudolph","the Red","Copacabana","Copa","Little Bear","Diva","Southside","Arizona","Sunrise","Yum Yum","Buffalo","Doctor Funk"));
		String name = nameList.get((int) (Math.random() * nameList.size()));
		tama = new Tamagoshi(name);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		for(ObjetGraphique og : listeObjet) {
			if (og.isVisible()) {
				og.dessineToi(g);
			}
		}
	}
	
	public Tamagoshi getTamagoshi() {
		return tama;
	}
	
	
	
}
