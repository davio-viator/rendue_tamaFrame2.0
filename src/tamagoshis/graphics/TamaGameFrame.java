package tamagoshis.graphics;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import tamagoshis.GrosJoueur;
import tamagoshis.GrosMangeur;
import tamagoshis.Tamagoshi;
//import tamagoshi.Utilisateur;
import tamagoshis.graphics.TamaFrame;
import java.util.Properties;
import java.util.logging.*;

public class TamaGameFrame {
	private ArrayList<Tamagoshi> allTamagoshis;
	private ArrayList<Tamagoshi> remainingTamagoshis;
	private ArrayList<String> nameList;
	private ArrayList<TamaFrame> tamaFrames;
	private int interactions = 0;
	private ArrayList<Properties> propertiesList;
	private Properties save;
	private Logger log;

	/**
     * Constructeur de la classe TamaGame crï¿½e la liste des tamagoshis
     */
	public TamaGameFrame() {
		this.allTamagoshis = new ArrayList<>() ;
		this.remainingTamagoshis = new ArrayList<>();
		this.nameList = new ArrayList<>();
		this.tamaFrames = new ArrayList<>();
		this.nameList = new ArrayList<>();
		this.propertiesList = new ArrayList<>();
		log = Logger.getLogger("test.test");
		Properties p = loadSave();
		if(p.toString().equals("{}")) {
			save = new Properties();
		}else {
			save = p;
		}
		log.setUseParentHandlers(false);
		log.addHandler(new ConsoleHandler());
		try {
			log.addHandler(new FileHandler("Game.log"));
		} catch (SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
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
	private void init() {
		nameList.addAll( Arrays.asList("Hurricane","Sanoma","Daddario","Rudolph","the Red","Copacabana","Copa","Little Bear","Diva","Southside","Arizona","Sunrise","Yum Yum","Buffalo","Doctor Funk"));
		String value = "";
		if(save!= null) {
			value = JOptionPane.showInputDialog(null,"choissiez la dificulter",save.getProperty("game.dificulty"));
		}else {
			log.info("the save didn't exist");
			value = JOptionPane.showInputDialog("choissiez la dificulter");		
		}
		if(value != null && isNumber(value) ) {
			int nombreTamagoshi = Integer.parseInt(value);
			
			if(nombreTamagoshi >0) {
				log.info("difficulty is : "+nombreTamagoshi);
				save.setProperty("game.dificulty", nombreTamagoshi+"");
				TamaFrame tamaFrame = null;
				for (int i = 0; i < nombreTamagoshi; i++) {
					Properties property = new Properties();
					//String name = nameList.get((int) (Math.random() * nameList.size()));
					String name = getRandomName(nameList);
					tamaFrame = new TamaFrame(this);
					property.setProperty("tamagoshi.name", name);
					if (Math.random() > 0.5) {
						log.info("a deviant tamagoshi was created");
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
						log.info("a normal tamagoshi was created");
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
				log.warning("the value need to be positive");
				String message = "Valeur positive uniquement ";
				JOptionPane.showMessageDialog(null, message,"input error",JOptionPane.INFORMATION_MESSAGE);
				init();
			}
		}else {
			String message = "Vous avez entrer une valeur incorrect ";
			JOptionPane.showMessageDialog(null, message,"input error",JOptionPane.INFORMATION_MESSAGE);
			log.warning("the value was a string");
			init();
		}
	}
	
	/**
	 * give you a random from the list of name
	 * @param nameList
	 * @return a random name from the list of name given
	 */
	
	private String getRandomName(ArrayList<String> nameList) {
		return nameList.remove((int)(Math.random()*nameList.size()));
	}

	/**
	 * checks is the string given as a parameter is a number
	 * @param value
	 * @return if the number given was a number
	 */
	private boolean isNumber(String value) {
		Pattern p = Pattern.compile("-?\\d+(\\.\\d+)?");
		return p.matcher(value).matches();
	}
	
	
	/**
	 * Methode qui sert a calculer et retourner le score de fin de partie du joueur
	 * 
	 * @return The score of the player at the end of the game
	 */
	private double getScore() {
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
	private String remerciment() {
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
	 * allows the user to change the state of a specific button
	 * @param nomButton name of the button that state will change
	 * @param state state of of the button
	 */
	
	private void interactionBoutton(String nomButton,boolean state) {
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

	private void finInteraction() {
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
				if(remainingTamagoshis.contains(tf.getTamagoshi())) {
					log.info(tf.getTamagoshi().getName()+" a ete supprimer");
				}
				remainingTamagoshis.remove(tf.getTamagoshi());
				
			}else {
				tf.talk();
			}
			
		}
		if(interactions == 20 || remainingTamagoshis.isEmpty()) {
			handleEndOfGame();
		}
		for(TamaFrame f:tamaFrames) {
			interactionBoutton("nourrir",true);
			interactionBoutton("jouer",true);
	
		}
		
	}
	
	/**
	 * handles what happens when the games is finished and show the messages
	 */
	
	private void handleEndOfGame() {
		String message = remerciment()+"your score was "+getScore()+"%" ;
		log.info("message des survivant\n"+message);
		log.info("le score : "+getScore());
		save.setProperty("game.score", getScore()+"");
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
			log.info("fermeture des tamaFrame");
		}
	}
	
	/**
	 * restarts a game from scratch
	 */
	
	private void restart() {
		//dispose close frame
		// init to restart
		for(TamaFrame f:tamaFrames) {
			f.dispose();
		}
		log.info("redemarrer le jeu");
		tamaFrames.clear();
		interactions = 0;
		init();
	}
	
	/**
	 * create a file with the properties of all the tamagoshis inside of it
	 */
	private void printProperties() {
		log.info("cree les fichier properties");
		try(OutputStream out = new FileOutputStream("Tamagoshis.properties")){
			for(Properties p :propertiesList) {
				p.store(out, "Properties of the tamagoshi "+p.getProperty("tamagoshi.name"));
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		try(OutputStream output = new FileOutputStream("Game.save")){
			save.store(output, "save of the game");
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Properties loadSave() {
		log.info("si un fichier de sauvgarde existe la charge");
		Properties props = new Properties();
		try(InputStream in = new FileInputStream("Game.save")){
			props.load(in);
		}catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}
	
	/**
	 * launch the tamagoshi game
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TamaGameFrame t = new TamaGameFrame();
		t.init();
	}

}
