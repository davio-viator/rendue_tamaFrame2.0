package tamagoshi.jeu;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import tamagoshi.GrosJoueur;
import tamagoshi.GrosMangeur;
import tamagoshi.Tamagoshi;
import tamagoshi.Utilisateur;
import tamagoshi.tamagoshis.graphics.TamaFrame;
import java.util.Properties;
import java.util.logging.*;

public class NewTamaGame {
	private ArrayList<Tamagoshi> allTamagoshis;
	private ArrayList<Tamagoshi> remainingTamagoshis;
	private ArrayList<String> nameList;
	private ArrayList<TamaFrame> tamaFrames;
	private int interactions = 0;
	private ArrayList<Properties> propertiesList;

	/**
     * Constructeur de la classe TamaGame crï¿½e la liste des tamagoshis
     */
	public NewTamaGame() {
		super();
		this.allTamagoshis = new ArrayList<>() ;
		this.remainingTamagoshis = new ArrayList<>();
		this.nameList = new ArrayList<>();
		this.tamaFrames = new ArrayList<>();
		this.nameList = new ArrayList<>();
		this.propertiesList = new ArrayList<>();
	
	}

	@Override
	public String toString() {
		return "NewTamaGame [allTamagoshis=" + allTamagoshis + ",\n remainingTamagoshis=" + remainingTamagoshis
				+ ",\n nameList=" + nameList + ",\n tamaFrames=" + tamaFrames + ",\n interactions=" + interactions
				+ ",\n propertiesList=" + propertiesList + "]";
	}

	/**
     *initialise la partie, permet de choisir le nombre de tamagoshi
     */
	public void init() {
		if(!tamaFrames.isEmpty()) {
			for(TamaFrame f : tamaFrames) {
				f.dispose();
		}
		}
		nameList.addAll( Arrays.asList("Hurricane","Sanoma","Daddario","Rudolph","the Red","Copacabana","Copa","Little Bear","Diva","Southside","Arizona","Sunrise","Yum Yum","Buffalo","Doctor Funk"));
		System.out.println("combien de tamagoshi voulez vous");
		String value = JOptionPane.showInputDialog("combien de tamagoshi voulez vous");
		if(value != null && isNumber(value) ) {
			int nombreTamagoshi = Integer.parseInt(value);
			
			if(nombreTamagoshi >0) {
				System.out.println("nombre tamagoshi : "+nombreTamagoshi);
				TamaFrame tamaFrame = null;
				for (int i = 0; i < nombreTamagoshi; i++) {
					Properties property = new Properties();
					System.out.println("new tamaFrame creer");
					//String name = nameList.get((int) (Math.random() * nameList.size()));
					String name = getRandomName(nameList);
					tamaFrame = new TamaFrame(this);
					property.setProperty("tamagoshi.name", name);
					if (Math.random() > 0.5) {
						if (Math.random() > 0.5) {
							GrosJoueur gm = new GrosJoueur(name);
							property.setProperty("tamagoshi.type", gm.getClass().getSimpleName());
							tamaFrame.setTamagoshi(gm); 
							propertiesList.add(property);
						} else {
							GrosMangeur gm = new GrosMangeur(name);
							property.setProperty("tamagoshi.type", gm.getClass().getSimpleName());
							tamaFrame.setTamagoshi(gm); 
							propertiesList.add(property);
						}
					} else {
						Tamagoshi tama = new Tamagoshi(name);
						property.setProperty("tamagoshi.type", tama.getClass().getSimpleName());
						tamaFrame.setTamagoshi(new Tamagoshi(name)); 
						propertiesList.add(property);
					}
					allTamagoshis.add(tamaFrame.getTamagoshi());
					tamaFrames.add(tamaFrame);
				}
				for(TamaFrame tf:tamaFrames) {
					tf.initi();
					tf.talk();
				}
				remainingTamagoshis.addAll(allTamagoshis);
			}
			else {
				String message = "Valeur positive uniquement ";
				JOptionPane.showMessageDialog(null, message,"input error",JOptionPane.INFORMATION_MESSAGE);
				init();
			}
			
			
		}else {
			String message = "Vous avez entrer une valeur incorrect ";
			JOptionPane.showMessageDialog(null, message,"input error",JOptionPane.INFORMATION_MESSAGE);
			init();
		}
		
		
	}
	
	/**
	 * give you a random from the list of name
	 * @param nameList
	 * @return a random name from the list of name given
	 */
	
	public String getRandomName(ArrayList<String> nameList) {
		return nameList.remove((int)(Math.random()*nameList.size()));
	}

	/**
	 * checks is the string given as a parameter is a number
	 * @param value
	 * @return if the number given was a number
	 */
	
	public boolean isNumber(String value) {
		Pattern p = Pattern.compile("-?\\d+(\\.\\d+)?");
		return p.matcher(value).matches();
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
	 * methode qui sert aux tamagoshis de remercier ou non leur propriï¿½taire a la fin
	 * de la partie en fonction de si ils ont survï¿½cu ou non
	 * @return 
	 */
	public String remerciment() {
		String remerciment = "";
		for (Tamagoshi t : allTamagoshis) {
			String tamaClassName = t.getClass().getSimpleName();
			if (t.getAge() == Tamagoshi.getLifeTime()) {
				String x = t.getName() + " qui etait un " + tamaClassName + " a survecu et vous remercie :)";
				remerciment+=x+"\n";
				
			} else {
				String x = t.getName() + " qui etait un " + tamaClassName + " n'est pas arrivivé au bout et ne vous remercie pas :(";
				remerciment+=x+"\n";
			}
		}
		return remerciment;
	}

	/**
	 * Methode contenant toutes les interaction que le joueur a avec les Tamagoshis
	 */
	/*public void interagir() {
		int index = selectionTama("Nourrir quel tamagoshi ?");
		remainingTamagoshis.get(index).mange();
		System.out.println("");
		int indexChosen = selectionTama("Jouer avec quel tamagoshi ?");
		remainingTamagoshis.get(indexChosen).jouer();
	}*/
	
	/**
	 * allows the user to change the state of a specific button
	 * @param nomButton name of the button that state will change
	 * @param state state of of the button
	 */
	
	public void interactionBoutton(String nomButton,boolean state) {
		for(TamaFrame tf:tamaFrames) {
			tf.buttonInteraction(nomButton, state);
		}
	}

	/**
	 * change the state of the button clicked to false and call the finInteraction function
	 * @param ButtonClic
	 */
	
	public void cliquer(String ButtonClic) {
		interactionBoutton(ButtonClic,false);
		interactions++;
		if(interactions%2 == 0) {
			finInteraction();
		}
		
	}
	
	/**
	 * handles what happens at the end of a turn
	 */

	public void finInteraction() {
		for (Iterator<TamaFrame> i = tamaFrames.iterator(); i.hasNext();) {
			TamaFrame tf = i.next();
			if (!tf.getTamagoshi().consommeEnergie() || !tf.getTamagoshi().ennui() || tf.getTamagoshi().viellir()) {
				//i.remove();
				tf.displayDeath();
				for(Properties p: propertiesList) {
					if(p.getProperty("tamagoshi.name").equals(tf.getTamagoshi().getName())) {
						if(tf.getTamagoshi().getAge() == Tamagoshi.getLifeTime()) {
							p.setProperty("tamagoshi.energyAtDeath", tf.getTamagoshi().getEnergy()+"");
							p.setProperty("tamagoshi.maxEnergy", tf.getTamagoshi().getMaxEnergy()+"");
							p.setProperty("tamagoshi.funAtDeath", tf.getTamagoshi().getFun()+"");
							p.setProperty("tamagoshi.maxFun", tf.getTamagoshi().getMaxFun()+"");
							p.setProperty("tamagoshi.ageOfDeath", tf.getTamagoshi().getAge()+" died of old age");
						}
						else {
						p.setProperty("tamagoshi.energyAtDeath", tf.getTamagoshi().getEnergy()+"");
						p.setProperty("tamagoshi.maxEnergy", tf.getTamagoshi().getMaxEnergy()+"");
						p.setProperty("tamagoshi.funAtDeath", tf.getTamagoshi().getFun()+"");
						p.setProperty("tamagoshi.maxFun", tf.getTamagoshi().getMaxFun()+"");
						p.setProperty("tamagoshi.ageOfDeath", tf.getTamagoshi().getAge()+"");
						}
					}
				}
				System.out.println(tf.getTamagoshi().getName() + " a ï¿½tï¿½ suprimmer");
				remainingTamagoshis.remove(tf.getTamagoshi());
				
			}else {
				tf.talk();
			}
			
		}
		if(interactions == 20 || remainingTamagoshis.isEmpty()) {
			handleEndOfGame();
		}
		for(TamaFrame f:tamaFrames) {
			System.out.println(f.getTamagoshi()+" "+f.isAlive());
			interactionBoutton("nourrir",true);
			interactionBoutton("jouer",true);
	
		}
		
	}
	
	/**
	 * handles what happens when the games is finished and show the messages
	 */
	
	public void handleEndOfGame() {
		String message = remerciment()+"your score was "+getScore()+"%" ;
		JOptionPane.showMessageDialog(null,message ,"bilan",JOptionPane.INFORMATION_MESSAGE);
		int b = -1;
		b = JOptionPane.showConfirmDialog(null, "Voulez vous rejouer ?","retry",JOptionPane.YES_NO_OPTION);
		if(b==0) {
			printProperties();
			restart();
		}else {
			printProperties();
			for(TamaFrame frame:tamaFrames) {
				frame.dispose();
			}
		}
	}
	
	/**
	 * restarts a game from scratch
	 */
	
	public void restart() {
		//dispose close frame
		// init to restart
		for(TamaFrame f:tamaFrames) {
			f.dispose();
		}
		interactions = 0;
		init();
	}
	
	/**
	 * create a file with the properties of all the tamagoshis inside of it
	 */
	public void printProperties() {
		try(OutputStream out = new FileOutputStream("Tamagoshis.properties")){
			for(Properties p :propertiesList) {
				p.store(out, "Properties of the tamagoshi "+p.getProperty("tamagoshi.name"));
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/*public int selectionTama(String message) {
		System.out.println(message);
		int indexChosen;
		for (ListIterator<Tamagoshi> iterator = remainingTamagoshis.listIterator(); iterator.hasNext();) {
			System.out.print("(" + iterator.nextIndex() + ")" + iterator.next().getName() + "                  ");
		}

		System.out.println("\nVotre choix");
		indexChosen = Integer.parseInt(Utilisateur.saisieClavier());
		return indexChosen;
	}*/

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NewTamaGame t = new NewTamaGame();
		t.init();
		//System.out.println(t.toString());
		//t.play();
	}

}
