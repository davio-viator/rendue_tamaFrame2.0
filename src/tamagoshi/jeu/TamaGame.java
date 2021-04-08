/**
 * 
 */
package tamagoshi.jeu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;

import tamagoshi.GrosJoueur;
import tamagoshi.GrosMangeur;
import tamagoshi.Tamagoshi;
import tamagoshi.Utilisateur;

/**
 * @author socia
 *
 */
public class TamaGame {

	private ArrayList<Tamagoshi> allTamagoshis;
	private ArrayList<Tamagoshi> remainingTamagoshis;
	private ArrayList<String> nameList;

	/**
     * Constructeur de la classe TamaGame crée la liste des tamagoshis
     */
	public TamaGame() {
		super();
		this.allTamagoshis = new ArrayList<>() ;
		this.remainingTamagoshis = new ArrayList<>();
		this.nameList = new ArrayList<>();
		this.nameList.addAll( Arrays.asList("Hurricane","Sanoma","Daddario","Rudolph","the Red","Copacabana","Copa","Little Bear","Diva","Southside","Arizona","Sunrise","Yum Yum","Buffalo","Doctor Funk"));
	
	}

	/**
     * Méthode qui créer les objets Tamagoshis et les ajoutes dans les liste
     */
	public void init() {
		System.out.println("combien de tamagoshi voulez vous");
		int nombreTamagoshi = Integer.parseInt(Utilisateur.saisieClavier());
		System.out.println(nombreTamagoshi);
		Tamagoshi tamagoshi = null;
		for (int i = 0; i < nombreTamagoshi; i++) {
			String name = nameList.get((int) (Math.random() * nameList.size()));
			if (Math.random() > 0.5) {
				if (Math.random() > 0.5) {
					tamagoshi = new GrosJoueur(name);
				} else {
					tamagoshi = new GrosMangeur(name);
				}
			} else {
				tamagoshi = new Tamagoshi(name);
			}
			allTamagoshis.add(tamagoshi);
		}
		remainingTamagoshis.addAll(allTamagoshis);
	}

	/**
	 * Méthode décrivant le deroulement du jeu
	 */
	public void play() {
		int counter = 1;
		while (!remainingTamagoshis.isEmpty()) {
			System.out.println("-------------------------Cycle n°" + counter + "----------------------------");

			for (Tamagoshi t : remainingTamagoshis) {
				t.parle();
				System.out.println("");
			}
			interagir();
			for (Iterator<Tamagoshi> i = remainingTamagoshis.iterator(); i.hasNext();) {
				Tamagoshi t = i.next();
				if (!t.consommeEnergie() || !t.ennui() || t.viellir()) {
					i.remove();
					System.out.println(t.getName() + " a été suprimmer");
				}
			}
			counter++;
		}
		System.out.println(
				"-------------------------------------- Fin de partie ! ------------------------------------------");
		System.out.println(
				"--------------------------------------      Bilan!     ------------------------------------------");
		remerciment();
		System.out.println("score obtenue : " + getScore() + "%");
	}

	/**
	 * Methode qui sert a calculer et retourner le score de fin de partie du joueur
	 * 
	 * @return The score of the player at the end of the game
	 */
	public double getScore() {
		double sommeAge = 0;
		allTamagoshis.get(0);
		double sommeToutAge = allTamagoshis.size() * (Tamagoshi.getLifeTime() + 1);
		for (Tamagoshi t : allTamagoshis) {
			sommeAge += t.getAge() + 1;
		}
		return (sommeAge / sommeToutAge) * 100;
	}

	/**
	 * methode qui ser au tamagoshis de remercier ou non leur propriétaire a la fin
	 * de la partie en fonction de si ils ont survécu ou non
	 */
	public void remerciment() {
		for (Tamagoshi t : allTamagoshis) {
			String tamaClassName = t.getClass().getSimpleName();
			if (t.getAge() == Tamagoshi.getLifeTime()) {
				System.out.println(t.getName() + " qui etait un " + tamaClassName + " a survecu et vous remercie :)");
			} else {
				System.out.println(t.getName() + " qui etait un " + tamaClassName
						+ " n'est pas arrivé au bout et ne vous remercie pas :(");
			}
		}
	}

	/**
	 * Methode contenant toutes les interaction que le joueur a avec les Tamagoshis
	 */
	public void interagir() {
		int index = selectionTama("Nourrir quel tamagoshi ?");
		remainingTamagoshis.get(index).mange();
		System.out.println("");
		int indexChosen = selectionTama("Jouer avec quel tamagoshi ?");
		remainingTamagoshis.get(indexChosen).jouer();
	}

	public int selectionTama(String message) {
		System.out.println(message);
		int indexChosen;
		for (ListIterator<Tamagoshi> iterator = remainingTamagoshis.listIterator(); iterator.hasNext();) {
			System.out.print("(" + iterator.nextIndex() + ")" + iterator.next().getName() + "                  ");
		}

		System.out.println("\nVotre choix");
		indexChosen = Integer.parseInt(Utilisateur.saisieClavier());
		return indexChosen;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TamaGame t = new TamaGame();
		t.init();
		t.play();
	}

}
